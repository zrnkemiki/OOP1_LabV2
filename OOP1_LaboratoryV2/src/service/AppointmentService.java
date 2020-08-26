package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import enums.SubmissionStatus;
import enums.SubmissionType;
import model.Analysis;
import model.Appointment;
import model.DataBase;
import model.Laborant;
import model.MedicalFinding;
import model.MedicalTechnician;
import model.Patient;

public class AppointmentService {
	
	public void makeAppointment(Patient patient, Scanner sc) {
		MedicalFinding mfinding = new MedicalFinding();
		Appointment app = new Appointment();
		System.out.println("Zakazivanje novog termina: ");
		System.out.println("Unesite datum [yyyy-MM-dd]: ");
		app.setDate(IOHandler.dateInputAppointment(sc));
		System.out.println("1) Uzorkovanje kod kuce");
		System.out.println("2) Uzorkovanje u laboratoriji");
		int input = MenuService.chooseMenuOption(3, false, sc);
		if(input == 1) {
			app.setSubmissionType(SubmissionType.HOME);
			System.out.println("Da li zelite konkretno vreme? Napomena: Ova opcija se dodatno naplacuje! \n 1)Da \n 2)Ne");
			int opt = MenuService.chooseMenuOption(3, false, sc);
			if(opt == 1) {
				app.setTime(IOHandler.timeInput(sc));
			}
		}
		else if(input == 2) {
			app.setSubmissionType(SubmissionType.LABORATORY);
			app.setTime(null);
		}
		app.setSubmissionStatus(SubmissionStatus.NOT_READY);
		app.setId(DataBase.appointments.size()+1);
		boolean t = true;
		while(t) {
			Analysis a = new Analysis();
			a.setAnalysisGroup(MenuService.chooseAnalysisGroupMenu(sc));
			a.setName(MenuService.chooseAnalysisMenu(sc, a.getAnalysisGroup()));
			a.setReferenceValue(DataBase.getReferenceValueByParam(a.getName()));
			a.setId(DataBase.analysis.size()+1);
			mfinding.addAnalysis(a);
			mfinding.setPatient(patient);
			mfinding.setDate(LocalDate.now());
			DataBase.analysis.add(a);
			System.out.println("Da li zelite jos neku od analiza?");
			System.out.println("1) Da \n 2)Ne");
			input = MenuService.chooseMenuOption(3, false, sc);
			if (input == 2) {
				t = false;
				mfinding.setId(DataBase.medicalFindings.size() + 1);
				mfinding.setDone(false);
				DataBase.medicalFindings.add(mfinding);
				app.setMedicalFinding(mfinding);
				DataBase.appointments.add(app);
			}
		}
		DataBase.saveAppointment();
		DataBase.saveAnalysis();
		DataBase.saveMedicalFinding();
		
	}
	
	public void changeStatus(Appointment a, MedicalTechnician medicalTechnician) {
		a.setSubmissionStatus(SubmissionStatus.READY);
		DataBase.saveAppointment();
		medicalTechnician.setNumberOfVisits(medicalTechnician.getNumberOfVisits() + 1);
	}

	public ArrayList<Appointment> getTodayAppointments() {
		ArrayList<Appointment> app = new ArrayList<Appointment>();
		for (Appointment appointment : DataBase.appointments) {
			if (appointment.getDate().equals(LocalDate.now()) && (appointment.getSubmissionStatus() != SubmissionStatus.READY)){
				app.add(appointment);
			}
		}
		return app;
	}
	
	public ArrayList<Appointment> getTodayHomeAppointments() {
		ArrayList<Appointment> app = new ArrayList<Appointment>();
		for (Appointment appointment : DataBase.appointments) {
			if (appointment.getDate().equals(LocalDate.now()) && (appointment.getSubmissionStatus() != SubmissionStatus.READY) && appointment.getSubmissionType() == SubmissionType.HOME){
				app.add(appointment);
			}
		}
		return app;
	}
	
	public ArrayList<Appointment> getReadyAppointments() {
		ArrayList<Appointment> app = new ArrayList<Appointment>();
		for (Appointment appointment : DataBase.appointments) {
			if (appointment.getSubmissionStatus() == SubmissionStatus.READY){
				app.add(appointment);
			}
		}
		if (app.isEmpty()) {
			System.out.println("Nema rezervisanih termina.");
		}
		return app;
	}

	public ArrayList<Appointment> getFutureAppointments() {
		ArrayList<Appointment> app = new ArrayList<Appointment>();
		for (Appointment appointment : DataBase.appointments) {
			if (appointment.getDate().isAfter((LocalDate.now().minusDays(1)))){
				app.add(appointment);
			}
		}
		return app;
	}

	public void showAppointments(ArrayList<Appointment> futureAppointments) {
		for (Appointment appointment : futureAppointments) {
			System.out.println(appointment.consoleView());
		}
		
	}
		
	
}
