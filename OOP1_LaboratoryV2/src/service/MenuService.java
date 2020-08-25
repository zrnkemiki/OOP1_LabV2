package service;

import java.util.ArrayList;
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
	public void start(Scanner sc) {
		User user;
		user = login(sc);
		while (true)
			userMenu(user, sc);

	}

	public void userMenu(User user, Scanner sc) {
		int input;
		if ( user instanceof Admin) {
			System.out.println("-------------------------------------\n");
			System.out.println("1) Plata");
			System.out.println("2) Izmena cenovnika");
			System.out.println("3) Dodavanje analize");
			System.out.println("4) Cenovnik svih analiza");
			System.out.println("0) Odjavljivanje.");
			input = chooseMenuOption(5, true, sc);
			switch (input) {
			case 1:
				System.out.println("-------------------------------------\n");
				System.out.println("1) Isplati plate");
				input = chooseMenuOption(1, true, sc);
				break;
			case 2:
				System.out.println("-------------------------------------\n");
				System.out.println("Izmena cenovnika");
				chooseReferenceValueMenu(sc).changePrice(sc);;
				break;
			case 3:
				System.out.println("-------------------------------------\n");
				System.out.println("Dodavanje analize");
				ReferenceValueService.newReferenceValue(sc, chooseAnalysisGroupMenu(sc));
				break;
			case 4:
				System.out.println("-------------------------------------\n");
				System.out.println("Cenovnih svih analiza");
				ReferenceValueService.getAllReferenceValues();
				break;
			case 0:
				System.out.println("Uspesno ste se izlogovali. \n");
				user = null;
				start(sc);
				break;
			}
		}

		else if (user instanceof Patient) {
			System.out.println("-------------------------------------\n");
			System.out.println("1) Pregled nalaza.");
			System.out.println("2) Zakazivanje termina.");
			System.out.println("0) Odjavljivanje.");
			input = chooseMenuOption(3, true, sc);
			switch (input) {
			case 1:
				System.out.println("-------------------------------------\n");
				System.out.println("Pregled nalaza");
				MedicalFindingService.exportMedicalFinding(chooseMedicalFindingMenu(MedicalFindingService.getPatientMedicalFindings((Patient)user), sc));
				break;
			case 2:
				AppointmentService.makeAppointment((Patient)user, sc);
				
				break;
			case 0:
				System.out.println("Uspesno ste se izlogovali. \n");
				user = null;
				start(sc);
				break;
			}
		} else if (user instanceof MedicalTechnician) {
			System.out.println("-------------------------------------\n");
			System.out.println("1) Registracija pacijenta.");
			System.out.println("2) Pregled analiza");
			System.out.println("3) Zakazivanje termina za pacijenta");
			System.out.println("4) Pregled svih zakazanih termina");
			System.out.println("5) Promena statusa danasnjih termina");
			System.out.println("0) Odjavljivanje");
			input = chooseMenuOption(6, true, sc);
			//AppointmentController a = new AppointmentController();
			switch (input) {
			case 1:
				System.out.println("------------------------");
				System.out.println("Registracija novog pacijenta:");
				//UserController uc = new UserController();
				//uc.createUser();
				break;
			case 2:
				System.out.println("-------------------------------------\n");
				//AnalysisController ac = new AnalysisController();
				System.out.println("Please enter patient LBO: ");
				String lbo = IOHandler.existingLboInput(sc);
				//ac.exportAnalysis(ac.getAnalysisByLBO(lbo));
				break;
			case 3:
				//a.makeAppointment(user);
				break;
			case 4:
				AppointmentService.showAppointments(AppointmentService.getFutureAppointments());
				break;
			case 5:
				AppointmentService.changeStatus(chooseAppointmentMenu(sc, AppointmentService.getTodayAppointments()));
				
				break;
			case 0:
				System.out.println("Uspesno ste se izlogovali. \n");
				user = null;
				start(sc);
				break;
			}
		} 
		else if (user instanceof Laborant) {
			System.out.println("-------------------------------------\n");
			System.out.println("1) Nova analiza.");
			System.out.println("2) Pregled analiza.");
			System.out.println("3) Analize spremne za obradu");
			System.out.println("0) Odjavljivanje.");
			input = chooseMenuOption(4, true, sc);
			//AnalysisController ac = new AnalysisController();
			//AppointmentController apc = new AppointmentController();
			switch (input) {
			case 1:
				System.out.println("------------------------");
				//ac = new AnalysisController();
				//ac.createAnalysisFromScratch();
				//System.out.println(DataBase.allAnalysis.keySet());
				break;
			case 2:
				System.out.println("-------------------------------------\n");
				System.out.println("1) TO-DO Pregled analiza");
				System.out.println("0) Izlazak iz programa");
				input = chooseMenuOption(2, true, sc);
				break;
			case 3:
				System.out.println("-------------------------------------\n");
				System.out.println("Analize spremne za obradu, izberite: ");
				ArrayList<Analysis> a =  AnalysisService.getAnalysis((Laborant)user, AppointmentService.getReadyAppointments());
				if(a.isEmpty()) {
					break;
				}
				AnalysisService.doAnalysis(chooseAnalysis(sc,a));
				break;
			case 0:
				System.out.println("Uspesno ste se izlogovali. \n");
				user = null;
				start(sc);
				break;
			}
		}
	}

	private MedicalFinding chooseMedicalFindingMenu(ArrayList<MedicalFinding> medicalFindings, Scanner sc) {
		for(int i=0;i<medicalFindings.size();i++) {
			System.out.println(i+1 + ")" + medicalFindings.get(i).consoleView());
		}
		return medicalFindings.get(chooseMenuOption(medicalFindings.size()+1, false, sc)-1); 
	}
	

	private ReferenceValue chooseReferenceValueMenu(Scanner sc) {
		ArrayList<ReferenceValue> rvs = new ArrayList<ReferenceValue>();
		for (String key : DataBase.referenceValues.keySet()) {
			rvs.add(DataBase.referenceValues.get(key));
		}
		for(int i=0;i<rvs.size();i++) {
			System.out.println(i+1 + ")" + rvs.get(i).consoleView());
		}
		System.out.println("Unesite broj parametra koji zelite da menjate.");
		return rvs.get(chooseMenuOption(rvs.size()+1, false, sc)-1); 
		
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
					System.out.println("Opcija koju ste uneli ne postoji.\nPokusajte ponovo.");
					System.out.println("-------------------------------------");
					izbor = -1;
				}
			} else {
				if (izbor <= 0 || izbor > range) {
					System.out.println("Opcija koju ste uneli ne postoji.Pokusajte ponovo.");
					System.out.println("-------------------------------------");
					izbor = -1;
				}

			}
		}
		return izbor;
	}
	
	public static AnalysisGroup chooseAnalysisGroupMenu(Scanner sc) {
		System.out.println("Izaberite grupu: ");
		System.out.println("Izaberite tip analize:");
		System.out.println("1. Biohemija");
		System.out.println("2. Hormoni");
		System.out.println("3. Hematologija");
		System.out.println("-------------------------");
		int input = MenuService.chooseMenuOption(4, false, sc);
		return AnalysisGroup.values()[input - 1];
	}
	
	public static Analysis chooseAnalysis(Scanner sc, ArrayList<Analysis> analysis) {
		for(int i=0;i<analysis.size();i++) {
			System.out.println(i+1 + ")" + analysis.get(i).consoleView());
		}
		return analysis.get(chooseMenuOption(analysis.size()+1, false, sc)-1); 
	}
	
	
	public static String chooseAnalysisMenu(Scanner sc, AnalysisGroup ag){
		ArrayList<String> params = DataBase.getAnalysisByGroup(sc, ag);
		for(int i=0;i<params.size();i++) {
			System.out.println(i+1 + ")" + params.get(i));
		}
		return params.get(chooseMenuOption(params.size()+1, false, sc)-1); 
	}
	
	public static Appointment chooseAppointmentMenu(Scanner sc, ArrayList<Appointment> apps){
		for(int i=0;i<apps.size();i++) {
			System.out.println(i+1 + ")" + apps.get(i));
		}
		return apps.get(chooseMenuOption(apps.size()+1, false, sc)-1); 
	}

	public User login(Scanner input) {

		User temp;

		String username;
		String password;
		boolean aktivan;

		while (true) {
			aktivan = true;
			System.out.println("\n**Welcome to OOP1 Laboratory**");
			System.out.println(" Please enter your username:");
			username = input.nextLine();
			if (username == "") {
				System.out.println("Username field cannot be empty.");
				continue;
			}
			System.out.println("Enter password:");
			password = input.nextLine();

			if (password == "") {
				System.out.println("Password field cannot be empty.");
				continue;
			}

			// ______________USERS LoGIN___________________
			for (String key : DataBase.users.keySet()) {
				if (DataBase.users.get(key).getUsername().equals(username)
						&& DataBase.users.get(key).getPassword().equals(password)) {

					System.out.println("Uspesno ste se logovali kao " + DataBase.users.get(key).getFirstName() + " " + DataBase.users.get(key).getLastName());
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
