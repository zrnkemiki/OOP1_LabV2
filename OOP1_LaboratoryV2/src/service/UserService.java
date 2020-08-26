package service;

import java.util.Scanner;

import enums.AnalysisGroup;
import enums.Sex;
import model.DataBase;
import model.Laborant;
import model.Patient;
import model.User;

public class UserService {

	public User createUser(Scanner sc) {
		User u = new Patient();
		System.out.println("Molimo unesite ime pacijenta: ");
		u.setFirstName(IOHandler.stringInput(sc));
		System.out.println("Molimo unesite prezime pacijenta: ");
		u.setLastName(IOHandler.stringInput(sc));
		u.setPassword(u.getFirstName().toLowerCase());
		System.out.println("Unesite korisnikov LBO");
		u.setLbo(IOHandler.newLboInput(sc));
		System.out.println("Unesite pol korisnika: M/Z ");
		String sex = IOHandler.stringInput(sc);
		if (sex.toLowerCase().equals("m")) {
			u.setSex(Sex.MALE);
		} else if (sex.toLowerCase().equals("z")) {
			u.setSex(Sex.FEMALE);
		}
		System.out.println("Unesite datum rodjenja [yyyy-MM-dd]: ");
		u.setDateOfBirth(IOHandler.dateInput(sc));
		System.out.println(("Unesite adresu: "));
		u.setAddress(IOHandler.stringInput(sc));
		System.out.println("Unesite broj telefona: ");
		u.setPhoneNumber(IOHandler.stringInput(sc));
		System.out.println("Da li korisnik zeli pristup sistemu? \n 1)DA \n 2)NE");
		int input = MenuService.chooseMenuOption(3, false, sc);
		if (input == 1) {
			u.setUsername(IOHandler.usernameInput(sc));
			u.setPassword(IOHandler.passwordInput(sc));
		} else {
			u.setUsername(null);
			u.setPassword(null);
		}
		DataBase.users.put(u.getLbo(), u);
		DataBase.saveUser();

		return u;
	}

	public void activateUser(Scanner sc) {
		User u = DataBase.users.get(IOHandler.existingLboInput(sc));
		System.out.println("Da li korisnik sigurno zeli pristup sistemu? \n 1)DA \n 2)NE");
		int input = MenuService.chooseMenuOption(3, false, sc);
		if (input == 1) {
			u.setUsername(IOHandler.usernameInput(sc));
			u.setPassword(IOHandler.passwordInput(sc));
		} else {
			u.setUsername(null);
			u.setPassword(null);
		}
		DataBase.saveUser();
	}

	public void addSpecialisation(Scanner sc) {
		User u = DataBase.users.get(IOHandler.existingLboInput(sc));
		if (u instanceof Laborant) {
			Laborant l = (Laborant) u;
			System.out.println("Korisinik ima specijalizacije: ");
			for (int i = 0; i < l.getSpecializations().size(); i++) {
				System.out.println(i + 1 + ")" + l.getSpecializations().get(i));
			}
			boolean flag = true;
			while (flag) {
				System.out.println(
						"Odaberite specijalizaciju koju zelite da dodate: 1) BIOHEMIJA \n 2)HORMONI \n 3)HEMATOLOGIJA");
				int input = MenuService.chooseMenuOption(4, false, sc);
				if (input == 1) {
					if (l.getSpecializations().contains(AnalysisGroup.valueOf("BIOHEMIJA"))) {
						System.out.println("Korisnik vec poseduje ovu specijalizaciju.");
					} else {
						l.getSpecializations().add(AnalysisGroup.BIOHEMIJA);
					}
				} else if (input == 2) {
					if (l.getSpecializations().contains(AnalysisGroup.valueOf("HORMONI"))) {
						System.out.println("Korisnik vec poseduje ovu specijalizaciju.");
					} else {
						l.getSpecializations().add(AnalysisGroup.HORMONI);
					}
				} else if (input == 3) {
					if (l.getSpecializations().contains(AnalysisGroup.valueOf("HEMATOLOGIJA"))) {
						System.out.println("Korisnik vec poseduje ovu specijalizaciju.");
					} else {
						l.getSpecializations().add(AnalysisGroup.HEMATOLOGIJA);
					}
				}
				System.out.println("Da li zelite jos neku specijalizaciju:");
				System.out.println("1)DA \n 2)NE");
				input = MenuService.chooseMenuOption(4, false, sc);
				if (input == 2) {
					flag = false;
					break;
				}
			}
			DataBase.users.put(l.getLbo(), l);
			DataBase.saveUser();

		}
	}
}
