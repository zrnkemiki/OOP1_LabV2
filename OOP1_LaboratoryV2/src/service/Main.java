package service;

import java.util.Scanner;

import model.DataBase;
import model.Salary;

public class Main {

	public static void main(String[] args) {
		
		DataBase db = new DataBase();
	
			
		System.out.println(DataBase.users.keySet());
		
		for (String key : DataBase.referenceValues.keySet()) {
			System.out.println(DataBase.referenceValues.get(key).toString());
		}
		System.out.println(DataBase.analysis.toString());
		System.out.println(DataBase.medicalFindings.toString());
		System.out.println(DataBase.appointments.toString());
		System.out.println(DataBase.priceList.consoleView());
		for (Salary s : DataBase.salaries) {
			System.out.println(s);
		}
		//System.out.println(DataBase.salaries.toString());
		
		Scanner sc = new Scanner(System.in);
		MenuService menu = new MenuService();
		menu.start(sc);
		sc.close();

	}

}
