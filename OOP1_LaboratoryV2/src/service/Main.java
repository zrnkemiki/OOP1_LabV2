package service;

import java.util.Scanner;

import model.DataBase;

public class Main {

	public static void main(String[] args) {
		DataBase.loadUsers();
		DataBase.loadReferenceValues();
		DataBase.loadAnalysis();
		DataBase.loadMedicalFindings();
		DataBase.loadAppointment();
		DataBase.loadPriceList();
		
		System.out.println(DataBase.users.keySet());
		
		for (String key : DataBase.referenceValues.keySet()) {
			System.out.println(DataBase.referenceValues.get(key).toString());
		}
		System.out.println(DataBase.analysis.toString());
		System.out.println(DataBase.medicalFindings.toString());
		System.out.println(DataBase.appointments.toString());
		System.out.println(DataBase.priceList.consoleView());
		
		Scanner sc = new Scanner(System.in);
		
		MenuService menu = new MenuService();
		menu.start(sc);


	}

}
