package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.AnalysisGroup;
import model.Admin;
import model.Analysis;
import model.Appointment;
import model.DataBase;
import model.Laborant;
import model.MedicalFinding;
import model.MedicalTechnician;
import model.Patient;
import model.ReferenceValue;
import model.User;

public class MenuService {

	private ReferenceValueService referenceValueService;
	private AnalysisService analysisService;
	private AppointmentService appointmentService;
	private MedicalFindingService medicalFindingService;
	private SalaryService salaryService;
	private UserService userService;
	private ReportService reportService;

	public MenuService() {
		referenceValueService = new ReferenceValueService();
		analysisService = new AnalysisService();
		appointmentService = new AppointmentService();
		medicalFindingService = new MedicalFindingService();
		salaryService = new SalaryService();
		userService = new UserService();
		reportService = new ReportService();
	}

	public void start(Scanner sc) {
		User user;
		user = login(sc);
		while (true)
			userMenu(user, sc);

	}

	public void userMenu(User user, Scanner sc) {
		if (user instanceof Admin) {
			adminMenu(user, sc);
		} else if (user instanceof Patient) {
			patientMenu(user, sc);
		} else if (user instanceof MedicalTechnician) {
			medicalTechnicianMenu(user, sc);
		} else if (user instanceof Laborant) {
			laborantMenu(user, sc);
		}
	}

	public void adminMenu(User user, Scanner sc) {
		System.out.println("-------------------------------------\n");
		System.out.println("1) Plata");
		System.out.println("2) Izmena cenovnika");
		System.out.println("3) Dodavanje analize");
		System.out.println("4) Cenovnik svih analiza");
		System.out.println("5) Dodavanje specijalizacija laborantu");
		System.out.println("6) Izvestaji");
		System.out.println("0) Odjavljivanje.");
		int input = chooseMenuOption(7, true, sc);
		switch (input) {
		case 1:
			System.out.println("-------------------------------------\n");
			System.out.println("1) Isplati plate");
			System.out.println("0) Nazad");
			input = chooseMenuOption(2, true, sc);
			if (input == 1) {
				salaryService.paySalaries();
			}
			break;
		case 2:
			System.out.println("-------------------------------------\n");
			System.out.println("Izmena cenovnika");
			chooseReferenceValueMenu(sc).changePrice(sc);
			break;
		case 3:
			System.out.println("-------------------------------------\n");
			System.out.println("Dodavanje analize");
			referenceValueService.newReferenceValue(sc, chooseAnalysisGroupMenu(sc));
			break;
		case 4:
			System.out.println("-------------------------------------\n");
			referenceValueService.getAllReferenceValues();
			break;
		case 5:
			System.out.println("-------------------------------------\n");
			System.out.println("Cenovnih svih analiza");
			userService.addSpecialisation(sc);
			break;
		case 6:
			reportsMenu(sc);
			break;
		case 0:
			System.out.println("Uspesno ste se izlogovali. \n");
			user = null;
			start(sc);
			break;
		}
	}
	
	public void reportsMenu(Scanner sc) {
		System.out.println("1) Izvestaji broju nalaza i ceni za period");
		System.out.println("2) Izvestaji o grupi analiza period");
		System.out.println("0) Nazad");
		int input = chooseMenuOption(3, true, sc);
		if(input == 1) {
			System.out.println("Unesite datum OD [yyyy-MM-dd]: ");
			LocalDate from = IOHandler.dateInput(sc);
			System.out.println("Unesite datum DO [yyyy-MM-dd]: ");
			LocalDate until = IOHandler.dateInput(sc);
			reportService.generatePatientReport(from, until);
		}
		else if(input == 2) {
			List<AnalysisGroup> groups = new ArrayList<AnalysisGroup>();
			boolean flag = true;
			System.out.println("Unesite datum OD [yyyy-MM-dd]: ");
			LocalDate from = IOHandler.dateInput(sc);
			System.out.println("Unesite datum DO [yyyy-MM-dd]: ");
			LocalDate until = IOHandler.dateInput(sc);
			while(flag) {
				if(groups.size() == 3) {
					flag = false;
					reportService.generatePatientReportFilterByGroup(from, until, groups);
					break;
				}
				AnalysisGroup ag = chooseAnalysisGroupMenu(sc);
				if (groups.contains(ag)) {
					System.out.println("Ovu grupu ste vec izabrali. Izaberite drugu");
					break;
				}
				else {
					groups.add(ag);
				}
				
				System.out.println("Da li zelite izvestaj za jos neku od grupa?\n 1)Da \n2)Ne");
				input = chooseMenuOption(3, false, sc);
				if(input==2) {
					flag = false;
					reportService.generatePatientReportFilterByGroup(from, until, groups);
					break;
				}
				
			}
		}
	}

	public void patientMenu(User user, Scanner sc) {
		System.out.println("-------------------------------------\n");
		System.out.println("1) Pregled nalaza.");
		System.out.println("2) Zakazivanje termina.");
		System.out.println("3) Pregled cenovnika analiza");
		System.out.println("0) Odjavljivanje.");
		int input = chooseMenuOption(4, true, sc);
		switch (input) {
		case 1:
			System.out.println("-------------------------------------\n");
			System.out.println("Pregled nalaza");
			ArrayList<MedicalFinding> mf = medicalFindingService.getPatientMedicalFindings((Patient) user);
			if (mf.isEmpty()) {
				System.out.println("Nemate gotovih nalaza.");
				break;
			}
			System.out.println("Spisak nalaza koje mozete odstampati: ");
			medicalFindingService.exportMedicalFinding(chooseMedicalFindingMenu(mf, sc));
			break;
		case 2:
			appointmentService.makeAppointment((Patient) user, sc);

			break;
		case 3:
			System.out.println("Cenovnik svih analiza: ");
			referenceValueService.getAllReferenceValues();
			break;
		case 0:
			System.out.println("Uspesno ste se izlogovali. \n");
			user = null;
			start(sc);
			break;
		}
	}

	public void medicalTechnicianMenu(User user, Scanner sc) {
		System.out.println("-------------------------------------\n");
		System.out.println("1) Registracija pacijenta");
		System.out.println("2) Aktivacija naloga postojecem pacijentu");
		System.out.println("3) Pregled svih zakazanih termina");
		System.out.println("4) Oznaci uzet uzorak");
		System.out.println("5) Danasnji pregledi zakazani za kod kuce");
		System.out.println("0) Odjavljivanje");
		int input = chooseMenuOption(6, true, sc);
		ArrayList<Appointment> app;
		switch (input) {
		case 1:
			System.out.println("------------------------");
			System.out.println("Registracija novog pacijenta:");
			userService.createUser(sc);
			break;
		case 2:
			System.out.println("-------------------------------------\n");
			userService.activateUser(sc);
			break;
		case 3:
			appointmentService.showAppointments(appointmentService.getFutureAppointments());
			break;
		case 4:
			app = appointmentService.getTodayAppointments();
			if (app.isEmpty()) {
				System.out.println("Nema rezervisanih termina za danas.");
				break;
			}
			appointmentService.changeStatus(chooseAppointmentMenu(sc, app),(MedicalTechnician) user);

			break;
		case 5:
			app = appointmentService.getTodayHomeAppointments();
			if (app.isEmpty()) {
				System.out.println("Nema rezervisanih termina za danas.");
				break;
			}
			for (Appointment appointment : app) {
				System.out.println(appointment.consoleView() + " Adresa: "
						+ appointment.getMedicalFinding().getPatient().getAddress() + " Telefon: "
						+ appointment.getMedicalFinding().getPatient().getPhoneNumber());
			}

			break;
		case 0:
			System.out.println("Uspesno ste se izlogovali. \n");
			user = null;
			start(sc);
			break;
		}
	}

	public void laborantMenu(User user, Scanner sc) {
		System.out.println("-------------------------------------\n");
		System.out.println("1) Pregled analiza");
		System.out.println("2) Analize spremne za obradu");
		System.out.println("0) Odjavljivanje");
		int input = chooseMenuOption(3, true, sc);
		switch (input) {
		case 1:
			System.out.println("-------------------------------------\n");
			System.out.println("Sve analize koje pripadaju vasoj specijalizaciji:\n");
			analysisService.showAnalysis(analysisService.getAnalysis((Laborant) user,
					appointmentService.getFutureAppointments()));
			break;
		case 2:
			System.out.println("-------------------------------------\n");
			System.out.println("Analize spremne za obradu, izberite: ");
			ArrayList<Analysis> a1 = analysisService.getAnalysis((Laborant) user,
					appointmentService.getReadyAppointments());
			if (a1.isEmpty()) {
				break;
			}
			analysisService.doAnalysis(chooseAnalysis(sc, a1));
			break;
		case 0:
			System.out.println("Uspesno ste se izlogovali. \n");
			user = null;
			start(sc);
			break;
		}
	}

	private MedicalFinding chooseMedicalFindingMenu(ArrayList<MedicalFinding> medicalFindings, Scanner sc) {
		for (int i = 0; i < medicalFindings.size(); i++) {
			System.out.println(i + 1 + ")" + medicalFindings.get(i).consoleView());
		}
		return medicalFindings.get(chooseMenuOption(medicalFindings.size() + 1, false, sc) - 1);
	}

	private ReferenceValue chooseReferenceValueMenu(Scanner sc) {
		ArrayList<ReferenceValue> rvs = new ArrayList<ReferenceValue>();
		for (String key : DataBase.referenceValues.keySet()) {
			rvs.add(DataBase.referenceValues.get(key));
		}
		for (int i = 0; i < rvs.size(); i++) {
			System.out.println(i + 1 + ")" + rvs.get(i).consoleView());
		}
		System.out.println("Unesite broj parametra koji zelite da menjate.");
		return rvs.get(chooseMenuOption(rvs.size() + 1, false, sc) - 1);

	}

	public static int chooseMenuOption(int range, boolean saNulom, Scanner sc) {
		int izbor = -1;
		int unetiBroj;
		while (izbor == -1) {
			System.out.print("Zelite opciju: ");
			unetiBroj = IOHandler.intInput(sc);
			try {
				izbor = (unetiBroj);
			} catch (NumberFormatException e) {
				izbor = -1;
			}
			if (saNulom) {
				if (izbor < 0 || izbor >= range) {
					System.out.println("Opcija koju ste uneli ne postoji.\nPokusajte ponovo:");
					System.out.println("-------------------------------------");
					izbor = -1;
				}
			} else {
				if (izbor <= 0 || izbor > range) {
					System.out.println("Opcija koju ste uneli ne postoji. \nPokusajte ponovo:");
					System.out.println("-------------------------------------");
					izbor = -1;
				}

			}
		}
		return izbor;
	}

	public static AnalysisGroup chooseAnalysisGroupMenu(Scanner sc) {
		System.out.println("Izaberite grupu: ");
		System.out.println("1. Biohemija");
		System.out.println("2. Hematologija");
		System.out.println("3. Hormoni");
		System.out.println("-------------------------");
		int input = MenuService.chooseMenuOption(4, false, sc);
		return AnalysisGroup.values()[input - 1];
	}

	public static Analysis chooseAnalysis(Scanner sc, ArrayList<Analysis> analysis) {
		for (int i = 0; i < analysis.size(); i++) {
			System.out.println(i + 1 + ")" + analysis.get(i).consoleView());
		}
		return analysis.get(chooseMenuOption(analysis.size() + 1, false, sc) - 1);
	}

	public static String chooseAnalysisMenu(Scanner sc, AnalysisGroup ag) {
		ArrayList<String> params = DataBase.getAnalysisByGroup(sc, ag);
		for (int i = 0; i < params.size(); i++) {
			System.out.println(i + 1 + ")" + params.get(i));
		}
		return params.get(chooseMenuOption(params.size() + 1, false, sc) - 1);
	}

	public static Appointment chooseAppointmentMenu(Scanner sc, ArrayList<Appointment> apps) {
		for (int i = 0; i < apps.size(); i++) {
			System.out.println(i + 1 + ")" + apps.get(i).consoleView());
		}
		return apps.get(chooseMenuOption(apps.size() + 1, false, sc) - 1);
	}

	public User login(Scanner input) {

		User temp;

		String username;
		String password;
		boolean aktivan;

		while (true) {
			aktivan = true;
			System.out.println("\n**Dobrodošli u Laboratoriju OOP1-FTN**");
			System.out.println(" Unesite korisnicko ime:");
			username = input.nextLine();
			if (username.equals("")) {
				System.out.println("Polje za korisnicko ime ne moze biti prazno");
				continue;
			} else if (username.equals("null")) {
				System.out.println("Zabranjena vrednost");
				continue;
			}
			System.out.println("Unesite lozinku:");
			password = input.nextLine();

			if (password.equals("")) {
				System.out.println("Polje za lozinku ne moze biti prazno");
				continue;
			} else if (password.equals("null")) {
				System.out.println("Zabranjena vrednost");
				continue;
			}
			for (String key : DataBase.users.keySet()) {
				if (DataBase.users.get(key).getUsername().equals(username)
						&& DataBase.users.get(key).getPassword().equals(password)) {

					System.out.println("Uspesno ste se logovali kao " + DataBase.users.get(key).getFirstName() + " "
							+ DataBase.users.get(key).getLastName());
					temp = DataBase.users.get(key);

					aktivan = false;

					return temp;
				}
			}

			if (aktivan) {
				System.out.println("Korisnicko ime/Lozinka nisu dobri.");
			} else
				continue;
		}
	}
}
