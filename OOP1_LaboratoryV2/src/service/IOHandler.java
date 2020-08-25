package service;

import java.time.LocalDate;
import java.util.Scanner;

import model.DataBase;

public class IOHandler {
	

	
	public static int intInput(Scanner input) {
		String unos;
		int broj;
		while (true)
		{
			unos = input.nextLine();
			try {
				broj = Integer.parseInt(unos);
				break;
			} catch (Exception e) {
				System.out.println("Only number input is accepted.");
			}
			
		}
		return broj;
	}
	
	public static LocalDate dateInput(Scanner input) {
		String unos;
		LocalDate date;
		while(true)
		{
			unos = input.nextLine();
			try {
				date = LocalDate.parse(unos);
				break;
			} catch(Exception e) {
				System.out.println("Date format not valid!");
			}
		}
		return date;
	}

	public static String stringInput(Scanner input) {
		String unos;
		String output;
		while(true)
		{
			unos = input.nextLine();
			try {
				output = unos;
				break;
			} catch(Exception e) {
				System.out.println("Format not valid!");
			}
		}
		return output;
	}

	public static String newLboInput(Scanner input) {
		String unos;
		String lbo = "";
		boolean temp = true;
		while(temp)
		{
			unos = input.nextLine();
			try {
				if(unos.length()!= 6) {
					System.out.println("LBO mora da sadrzi 6 cifara. Pokusajte ponovo:");
					temp = true;
				}
				else if(DataBase.users.containsKey(unos)) {
					System.out.println("LBO ne postoji. Pokusajte ponovo:");
					temp = true;
				}
				else{
					lbo = unos;
					break;
				}
			} catch(Exception e) {
				System.out.println("Format nije validan!");
			}
		}
		return lbo;
	}
	
	public static String existingLboInput(Scanner input) {
		String unos;
		String lbo = "";
		boolean temp = true;
		while(temp)
		{
			unos = input.nextLine();
			try {
				if(unos.length()!= 6) {
					System.out.println("LBO mora da sadrzi 6 cifara. Pokusajte ponovo:");
					temp = true;
				}
				else if(!DataBase.users.containsKey(unos)) {
					System.out.println("LBO ne postoji. Pokusajte ponovo:");
					temp = true;
				}
				else{
					lbo = unos;
					break;
				}
			} catch(Exception e) {
				System.out.println("Format nije validan!");
			}
		}
		return lbo;
	}

	public static double doubleInput(Scanner sc) {
		String unos;
		double broj;
		while (true)
		{
			unos = sc.nextLine();
			try {
				broj = Double.valueOf(unos);
				break;
			} catch (Exception e) {
				System.out.println("Only number input is accepted.");
			}
			
		}
		return broj;
	}


}
