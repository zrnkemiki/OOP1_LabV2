package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import enums.AnalysisGroup;
import enums.Sex;
import enums.StrucnaSprema;
import enums.SubmissionStatus;
import enums.SubmissionType;

public class DataBase {

	public static HashMap<String, User> users = new HashMap<String, User>();
	public static HashMap<String, ReferenceValue> referenceValues = new HashMap<String, ReferenceValue>();
	public static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	public static ArrayList<MedicalFinding> medicalFindings = new ArrayList<MedicalFinding>();
	public static ArrayList<Analysis> analysis = new ArrayList<Analysis>();
	public static PriceList priceList = new PriceList();
	public static ArrayList<Salary> salaries = new ArrayList<Salary>();

	static BufferedWriter bw;
	static FileWriter fw;
	
	

	public DataBase() {
		loadUsers();
		loadReferenceValues();
		loadAnalysis();
		loadMedicalFindings();
		loadAppointment();
		loadPriceList();
		loadSalaries();
	}

	public void loadAnalysis() {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream("src/Data/analysis.txt"), "utf-8"));
			String s;
			while ((s = in.readLine()) != null) {
				s = s.trim();
				String[] tokens = s.split("\\|");
				Analysis a = new Analysis();
				a.setId(Integer.parseInt(tokens[0]));
				if (tokens[1].trim().equals("BIOHEMIJA")) {
					a.setAnalysisGroup(AnalysisGroup.BIOHEMIJA);
				} else if (tokens[1].trim().equals("HORMONI")) {
					a.setAnalysisGroup(AnalysisGroup.HORMONI);
				} else if (tokens[1].trim().equals("HEMATOLOGIJA")) {
					a.setAnalysisGroup(AnalysisGroup.HEMATOLOGIJA);
				}
				a.setValue(Double.valueOf(tokens[2]));
				a.setName(tokens[3]);
				a.setDone(Boolean.valueOf(tokens[4]));
				a.setReferenceValue(DataBase.getReferenceValueById(Integer.parseInt(tokens[5])));
				DataBase.analysis.add(a);
			}
			in.close();
		}

		catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static ArrayList<String> getAnalysisByGroup(Scanner sc, AnalysisGroup ag) {
		ArrayList<String> params = new ArrayList<String>();
		for (String key : DataBase.referenceValues.keySet()) {
			if (DataBase.referenceValues.get(key).getAnalysisGroup().equals(ag)) {
				params.add(DataBase.referenceValues.get(key).getName());
			}

		}
		return params;

	}

	public void loadMedicalFindings() {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream("src/Data/medicalFindings.txt"), "utf-8"));
			String s;
			while ((s = in.readLine()) != null) {
				s = s.trim();
				String[] tokens = s.split("\\|");
				MedicalFinding mf = new MedicalFinding();

				if (tokens[1] != null) {
					mf.setId(Integer.parseInt(tokens[0]));
					String[] ids = tokens[1].split("\\,");
					for (String id : ids) {
						for (Analysis a : DataBase.analysis) {
							if (a.getId() == Integer.parseInt(id)) {
								mf.addAnalysis(a);
							}
						}
					}
				}

				mf.setDate(LocalDate.parse((tokens[2])));
				mf.setPatient((Patient) DataBase.users.get(tokens[3]));
				mf.setPrice(Double.valueOf(tokens[4]));
				mf.setDone(Boolean.valueOf(tokens[5]));
				DataBase.medicalFindings.add(mf);
			}
			in.close();
		}

		catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static MedicalFinding getMedicalFindingByID(int id) {
		MedicalFinding mfinding = null;
		for (MedicalFinding mf : DataBase.medicalFindings) {
			if (mf.getId() == id) {
				mfinding = mf;
			}
		}
		return mfinding;
	}

	public void loadReferenceValues() {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream("src/Data/ReferenceValues.txt"), "utf-8"));
			String s;
			while ((s = in.readLine()) != null) {
				s = s.trim();
				String[] tokens = s.split("\\|");
				ReferenceValue rv = new ReferenceValue();
				rv.setId(Integer.parseInt(tokens[0]));
				if (tokens[1].trim().equals("BIOHEMIJA")) {
					rv.setAnalysisGroup(AnalysisGroup.BIOHEMIJA);
				} else if (tokens[1].trim().equals("HORMONI")) {
					rv.setAnalysisGroup(AnalysisGroup.HORMONI);
				} else if (tokens[1].trim().equals("HEMATOLOGIJA")) {
					rv.setAnalysisGroup(AnalysisGroup.HEMATOLOGIJA);
				}
				rv.setName(tokens[2].trim());
				if (tokens[3].trim().equals("NaN")) {
					rv.setMinMale(Double.NaN);
					rv.setMaxMale(Double.NaN);
					rv.setMinFemale(Double.parseDouble(tokens[5].trim()));
					rv.setMaxFemale(Double.parseDouble(tokens[6].trim()));
				} else if (tokens[5].trim().equals("NaN")) {
					rv.setMinFemale(Double.NaN);
					rv.setMaxFemale(Double.NaN);
					rv.setMinMale(Double.parseDouble(tokens[3].trim()));
					rv.setMaxMale(Double.parseDouble(tokens[4].trim()));
				} else {
					rv.setMinMale(Double.parseDouble(tokens[3].trim()));
					rv.setMaxMale(Double.parseDouble(tokens[4].trim()));
					rv.setMinFemale(Double.parseDouble(tokens[5].trim()));
					rv.setMaxFemale(Double.parseDouble(tokens[6].trim()));
				}
				rv.setUnit(tokens[7].trim());
				rv.setPrice(Double.parseDouble(tokens[8].trim()));
				DataBase.referenceValues.put(rv.getName(), rv);
			}
			in.close();
		}

		catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static ReferenceValue getReferenceValueById(int id) {
		ReferenceValue rv = null;
		for (String key : DataBase.referenceValues.keySet()) {
			if (DataBase.referenceValues.get(key).getId() == id) {
				rv = DataBase.referenceValues.get(key);
			}
		}
		return rv;
	}

	public static ReferenceValue getReferenceValueByParam(String param) {
		return referenceValues.get(param);
	}

	public static void loadPriceList() {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream("src/Data/priceList.txt"), "utf-8"));
			String s;
			while ((s = in.readLine()) != null) {
				s = s.trim();
				String[] tokens = s.split("\\|");
				if (tokens[0].contains("H")) {
					continue;
				}
				priceList.setHomeVisit(Double.valueOf(tokens[0].trim()));
				priceList.setHomeVisitTime(Double.valueOf(tokens[1].trim()));
				priceList.setEarningBasic(Double.valueOf(tokens[2].trim()));
				priceList.setHomeVisitBonus(Double.valueOf(tokens[3].trim()));
				priceList.setAnalysisBonus(Double.valueOf(tokens[4].trim()));
				priceList.setSSS4_coefficient(Double.valueOf(tokens[5].trim()));
				priceList.setSSS6_coefficient(Double.valueOf(tokens[6].trim()));
				priceList.setSSS8_coefficient(Double.valueOf(tokens[7].trim()));
				priceList.setSpecialisationBonus(Double.valueOf(tokens[8].trim()));

			}
			in.close();
		}

		catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void loadAppointment() {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream("src/Data/appointments.txt"), "utf-8"));
			String s;
			while ((s = in.readLine()) != null) {
				s = s.trim();
				String[] tokens = s.split("\\|");
				Appointment a = new Appointment();
				a.setId(Integer.parseInt(tokens[0]));
				a.setDate(LocalDate.parse(tokens[1]));
				if (tokens[2].equals("null")) {
					a.setTime(null);
				} else {
					a.setTime(LocalTime.parse(tokens[2].trim()));
				}
				if (tokens[3].trim().equals("HOME")) {
					a.setSubmissionType(SubmissionType.HOME);
				} else if (tokens[3].trim().equals("LABORATORY")) {
					a.setSubmissionType(SubmissionType.LABORATORY);
				}

				if (tokens[4].trim().equals("READY")) {
					a.setSubmissionStatus(SubmissionStatus.READY);
				} else if (tokens[4].trim().equals("NOT_READY")) {
					a.setSubmissionStatus(SubmissionStatus.NOT_READY);
				} else if (tokens[4].trim().equals("DONE")) {
					a.setSubmissionStatus(SubmissionStatus.DONE);
				}

				a.setMedicalFinding(getMedicalFindingByID(Integer.parseInt(tokens[5])));
				DataBase.appointments.add(a);
			}
			in.close();
		}

		catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void loadUsers() {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream("src/Data/users.txt"), "utf-8"));
			String s;
			while ((s = in.readLine()) != null) {
				s = s.trim();
				String[] tokens = s.split("\\|");
				if (tokens[9].equals("PACIJENT")) {
					Patient u = new Patient();
					u.setFirstName(tokens[0]);
					u.setLastName(tokens[1]);
					u.setUsername(tokens[2]);
					u.setPassword(tokens[3]);
					u.setLbo(tokens[4]);
					if (tokens[5].trim().equalsIgnoreCase("male")) {
						u.setSex(Sex.MALE);
					} else if (tokens[5].trim().equalsIgnoreCase("female")) {
						u.setSex(Sex.FEMALE);
					}
					u.setDateOfBirth(LocalDate.parse(tokens[6]));
					u.setAddress(tokens[7]);
					u.setPhoneNumber(tokens[8]);
					DataBase.users.put(u.getLbo(), u);
				} else if (tokens[9].equals("LABORANT")) {
					Laborant u = new Laborant();
					u.setFirstName(tokens[0]);
					u.setLastName(tokens[1]);
					u.setUsername(tokens[2]);
					u.setPassword(tokens[3]);
					u.setLbo(tokens[4]);
					if (tokens[5].trim().equalsIgnoreCase("male")) {
						u.setSex(Sex.MALE);
					} else if (tokens[5].trim().equalsIgnoreCase("female")) {
						u.setSex(Sex.FEMALE);
					}
					u.setDateOfBirth(LocalDate.parse(tokens[6]));
					u.setAddress(tokens[7]);
					u.setPhoneNumber(tokens[8]);
					if (tokens[10].trim().equals("SSS_4")) {
						u.setStrucnaSprema(StrucnaSprema.SSS_4);
					} else if (tokens[10].trim().equals("SSS_6")) {
						u.setStrucnaSprema(StrucnaSprema.SSS_6);
					} else if (tokens[10].trim().equals("SSS_8")) {
						u.setStrucnaSprema(StrucnaSprema.SSS_8);
					}
					try {
						String[] spec = tokens[11].split("\\,");
						ArrayList<AnalysisGroup> specialisations = new ArrayList<>();
						for (int i = 0; i < spec.length; i++) {
							if (spec[i].toUpperCase().trim().equals("BIOHEMIJA")) {
								specialisations.add(AnalysisGroup.BIOHEMIJA);
							} else if (spec[i].toUpperCase().trim().equals("HORMONI")) {
								specialisations.add(AnalysisGroup.HORMONI);
							} else if (spec[i].toUpperCase().trim().equals("HEMATOLOGIJA")) {
								specialisations.add(AnalysisGroup.HEMATOLOGIJA);
							}
						}
						u.setSpecializations(specialisations);

					} catch (Exception e) {
					}
					u.setDateStarted(LocalDate.parse(tokens[12]));
					// To-Do Specijalizacije
					// u.setSpecijalizacije(specijalizacije);
					DataBase.users.put(u.getLbo(), u);
				} else if (tokens[9].equals("ADMIN")) {
					Admin u = new Admin();
					u.setFirstName(tokens[0]);
					u.setLastName(tokens[1]);
					u.setUsername(tokens[2]);
					u.setPassword(tokens[3]);
					u.setLbo(tokens[4]);
					if (tokens[5].trim().equalsIgnoreCase("male")) {
						u.setSex(Sex.MALE);
					} else if (tokens[5].trim().equalsIgnoreCase("female")) {
						u.setSex(Sex.FEMALE);
					}
					u.setDateOfBirth(LocalDate.parse(tokens[6]));
					u.setAddress(tokens[7]);
					u.setPhoneNumber(tokens[8]);
					if (tokens[10].trim().equals("SSS_4")) {
						u.setStrucnaSprema(StrucnaSprema.SSS_4);
					} else if (tokens[10].trim().equals("SSS_6")) {
						u.setStrucnaSprema(StrucnaSprema.SSS_6);
					} else if (tokens[10].trim().equals("SSS_8")) {
						u.setStrucnaSprema(StrucnaSprema.SSS_8);
					}
					DataBase.users.put(u.getLbo(), u);

				} else if (tokens[9].equals("MEDICINSKI_TEHNICAR")) {
					MedicalTechnician u = new MedicalTechnician();
					u.setFirstName(tokens[0]);
					u.setLastName(tokens[1]);
					u.setUsername(tokens[2]);
					u.setPassword(tokens[3]);
					u.setLbo(tokens[4]);
					if (tokens[5].trim().equalsIgnoreCase("male")) {
						u.setSex(Sex.MALE);
					} else if (tokens[5].trim().equalsIgnoreCase("female")) {
						u.setSex(Sex.FEMALE);
					}
					u.setDateOfBirth(LocalDate.parse(tokens[6]));
					u.setAddress(tokens[7]);
					u.setPhoneNumber(tokens[8]);
					if (tokens[10].trim().equals("SSS_4")) {
						u.setStrucnaSprema(StrucnaSprema.SSS_4);
					} else if (tokens[10].trim().equals("SSS_6")) {
						u.setStrucnaSprema(StrucnaSprema.SSS_6);
					} else if (tokens[10].trim().equals("SSS_8")) {
						u.setStrucnaSprema(StrucnaSprema.SSS_8);
					}

					u.setDateStarted(LocalDate.parse(tokens[11]));
					u.setNumberOfVisits(Integer.parseInt(tokens[12]));
					// To-Do Specijalizacije
					// u.setSpecijalizacije(specijalizacije);
					DataBase.users.put(u.getLbo(), u);

				}

			}

			in.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadSalaries() {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream("src/Data/salaries.txt"), "utf-8"));
			String s;
			while ((s = in.readLine()) != null) {
				s = s.trim();
				String[] tokens = s.split("\\|");
				Salary e = new Salary();
				e.setId(Integer.parseInt(tokens[0].trim()));
				e.setUser(DataBase.users.get(tokens[1].trim()));
				e.setDateFrom(LocalDate.parse(tokens[2].trim()));
				e.setDateUntil(LocalDate.parse(tokens[3].trim()));
				e.setBasic(Double.valueOf(tokens[4].trim()));
				e.setAmount(Double.valueOf(tokens[5].trim()));

				DataBase.salaries.add(e);
			}

			in.close();
		}

		catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void saveAppointment() {
		String sadrzaj = "";
		for (Appointment a : DataBase.appointments) {
			sadrzaj += a.toString() + "\n";

		}
		try {
			fw = new FileWriter("src/Data/appointments.txt");
			bw = new BufferedWriter(fw);
			bw.write(sadrzaj);
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveAnalysis() {
		String sadrzaj = "";
		for (Analysis a : DataBase.analysis) {
			sadrzaj += a.toString() + "\n";

		}
		try {
			fw = new FileWriter("src/Data/analysis.txt");
			bw = new BufferedWriter(fw);
			bw.write(sadrzaj);
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveMedicalFinding() {
		String sadrzaj = "";
		for (MedicalFinding a : DataBase.medicalFindings) {
			sadrzaj += a.toString() + "\n";

		}
		try {
			fw = new FileWriter("src/Data/medicalFindings.txt");
			bw = new BufferedWriter(fw);
			bw.write(sadrzaj);
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void saveReferenceValue() {
		String sadrzaj = "";
		for (String key : DataBase.referenceValues.keySet()) {
			sadrzaj += DataBase.referenceValues.get(key).toString() + "\n";

		}
		try {
			fw = new FileWriter("src/Data/ReferenceValues.txt");
			bw = new BufferedWriter(fw);
			bw.write(sadrzaj);
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void saveUser() {
		String sadrzaj = "";
		for (String key : DataBase.users.keySet()) {
			sadrzaj += DataBase.users.get(key).toString() + "\n";

		}
		try {
			fw = new FileWriter("src/Data/users.txt");
			bw = new BufferedWriter(fw);
			bw.write(sadrzaj);
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void saveSalaries() {
		String sadrzaj = "";
		for (Salary s : DataBase.salaries) {
			sadrzaj += s.toString() + "\n";

		}
		try {
			fw = new FileWriter("src/Data/salaries.txt");
			bw = new BufferedWriter(fw);
			bw.write(sadrzaj);
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
