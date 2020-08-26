package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import model.DataBase;

public class IOHandler {

	public static int intInput(Scanner input) {
		String unos;
		int broj;
		while (true) {
			unos = input.nextLine();
			try {
				broj = Integer.parseInt(unos);
				break;
			} catch (Exception e) {
				System.out.println("Format nije validan!");
				System.out.println("Pokusajte ponovo:");
			}

		}
		return broj;
	}

	public static LocalDate dateInput(Scanner input) {
		String unos;
		LocalDate date;
		while (true) {
			unos = input.nextLine();
			try {
				date = LocalDate.parse(unos);
				break;
			} catch (Exception e) {
				System.out.println("Format nije validan!");
				System.out.println("Pokusajte ponovo:");
			}
		}
		return date;
	}
	
	public static LocalDate dateInputAppointment(Scanner input) {
		String unos;
		LocalDate date;
		while (true) {
			unos = input.nextLine();
			try {
				date = LocalDate.parse(unos);
				if (date.isBefore(LocalDate.now())) {
					System.out.println("Unesite datum koji nije prosao");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("Format nije validan!");
				System.out.println("Pokusajte ponovo:");
			}
		}
		return date;
	}
	
	
	public static String stringInput(Scanner input) {
		String unos;
		String output;
		while (true) {
			unos = input.nextLine();
			try {
				output = unos;
				break;
			} catch (Exception e) {
				System.out.println("Format nije validan!");
				System.out.println("Pokusajte ponovo:");
			}
		}
		return output;
	}

	public static String newLboInput(Scanner input) {
		String unos;
		String lbo = "";
		boolean temp = true;
		while (temp) {
			unos = input.nextLine();
			try {
				if (unos.length() != 6) {
					System.out.println("LBO mora da sadrzi 6 cifara. Pokusajte ponovo:");
					temp = true;
				} else if (DataBase.users.containsKey(unos)) {
					System.out.println("LBO ne postoji. Pokusajte ponovo:");
					temp = true;
				} else {
					lbo = unos;
					break;
				}
			} catch (Exception e) {
				System.out.println("Format nije validan!");
				System.out.println("Pokusajte ponovo:");			}
		}
		return lbo;
	}

	public static String existingLboInput(Scanner input) {
		String unos;
		String lbo = "";
		boolean temp = true;
		while (temp) {
			System.out.println("Unesite korisnikov LBO:");
			unos = input.nextLine();
			try {
				if (unos.length() != 6) {
					System.out.println("LBO mora da sadrzi 6 cifara. Pokusajte ponovo:");
					temp = true;
				} else if (!DataBase.users.containsKey(unos)) {
					System.out.println("LBO ne postoji. Pokusajte ponovo:");
					temp = true;
				} else {
					lbo = unos;
					break;
				}
			} catch (Exception e) {
				System.out.println("Format nije validan!");
				System.out.println("Pokusajte ponovo:");			
			}
		}
		return lbo;
	}

	public static double doubleInput(Scanner sc) {
		String unos;
		double broj;
		while (true) {
			unos = sc.nextLine();
			try {
				broj = Double.valueOf(unos);
				break;
			} catch (Exception e) {
				System.out.println("Format nije validan!");
				System.out.println("Pokusajte ponovo:");			
			}

		}
		return broj;
	}

	public static LocalTime timeInput(Scanner sc) {
		String unos;
		LocalTime time = null;
		boolean temp = true;
		while (temp) {
			System.out.println("Molimo unesite vreme u formatu HH:mm");
			unos = sc.nextLine();
			try {
				time = LocalTime.parse(unos);
				if (time.isAfter(LocalTime.parse("18:00")) || time.isBefore(LocalTime.parse("08:00"))) {
					System.out.println("Laboratorija radi od 08h do 18h.");
					temp = true;
				} else {
					temp = false;
				}
			} catch (Exception e) {
				System.out.println("Format nije validan!");
				System.out.println("Pokusajte ponovo:");			
			}
		}
		return time;
	}

	public static String usernameInput(Scanner sc) {
		String unos;
		String username = "";
		boolean temp = true;
		while (temp) {
			System.out.println("Username:");
			unos = sc.nextLine();
			try {
				for (String key : DataBase.users.keySet()) {
					if (DataBase.users.get(key).getUsername().toLowerCase().equals(unos.toLowerCase())) {
						System.out.println("Username vec postoji, pokusajte ponovo:");
						temp = true;
					} 
					if (unos.equals("")) {
						System.out.println("Username ne moze biti prazno polje.");
						temp = true;

					} 
					
						
					

				}
				username = unos;
				break;

			} catch (Exception e) {
				System.out.println("Format nije validan!");
				System.out.println("Pokusajte ponovo:");
			}
		}
		return username;
	}

	public static String passwordInput(Scanner sc) {
		String unos;
		String password = "";
		boolean temp = true;
		while (temp) {
			System.out.println("Password:");
			unos = sc.nextLine();
			try {
				if (unos.equals("")) {
					System.out.println("Password ne moze biti prazno polje.");
					temp = true;

				} else {
					password = unos;
					break;
				}
			}
			catch (Exception e) {
				System.out.println("Format nije validan!");
				System.out.println("Pokusajte ponovo:");
			}
		}
		return password;
	}

}
