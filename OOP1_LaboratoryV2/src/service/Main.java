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
		
		System.out.println(DataBase.users.keySet());
		System.out.println(DataBase.referenceValues.keySet());
		System.out.println(DataBase.analysis.toString());
		System.out.println(DataBase.medicalFindings.toString());
		System.out.println(DataBase.appointments.toString());
		
		Scanner sc = new Scanner(System.in);
		
		MenuService menu = new MenuService();
		menu.start(sc);


	}

}
