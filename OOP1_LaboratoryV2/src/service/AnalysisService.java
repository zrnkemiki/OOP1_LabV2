package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import enums.AnalysisGroup;
import enums.Sex;
import model.Analysis;
import model.Appointment;
import model.DataBase;
import model.Laborant;
import model.MedicalFinding;

public class AnalysisService {

	public static void doAnalysis(Analysis a) {
		MedicalFinding mfinding = null;

		for (MedicalFinding mf : DataBase.medicalFindings) {
			for (Analysis analysis : mf.getAnalysis()) {
				if (analysis.getId() == a.getId()) {
					mfinding = mf;
				}
			}
		}

		if (mfinding.getPatient().getSex() == Sex.MALE) {
			a.setValue(a.getReferenceValue().generateMaleValues());
		} else if (mfinding.getPatient().getSex() == Sex.FEMALE) {
			a.setValue(a.getReferenceValue().generateFemaleValues());
		}
		a.setDone(true);

		for (Analysis an1 : mfinding.getAnalysis()) {
			if(an1.getId() == a.getId()) {
				mfinding.setPrice(mfinding.getPrice() + an1.getReferenceValue().getPrice());
			}
		}

		boolean flag = true;
		for (Analysis an : mfinding.getAnalysis()) {
			if (an.isDone() == false) {
				flag = false;
			}
		}

		if (flag) {
			mfinding.setDone(true);
			System.out.println("Nalaz pacijenta " + mfinding.getPatient().getFirstName() + ""
					+ mfinding.getPatient().getLastName() + " je zavrsen i moze biti odstampan.");
			mfinding.setDate(LocalDate.now());

			for (Appointment app : DataBase.appointments) {
				if (app.getMedicalFinding().getId() == mfinding.getId()) {
					if (app.getTime() != null) {
						mfinding.setPrice(mfinding.getPrice() + DataBase.priceList.getHomeVisitTime());
					}
				}
			}
		}

		DataBase.saveAnalysis();
		DataBase.saveMedicalFinding();

	}

	public static ArrayList<Analysis> getAnalysis(Laborant laborant, ArrayList<Appointment> readyAppointments) {
		ArrayList<Analysis> analysisToDo = new ArrayList<Analysis>();
		for (Appointment app : readyAppointments) {
			for (Analysis analysis : app.getMedicalFinding().getAnalysis()) {
				if (!analysis.isDone() && laborant.getSpecializations().contains(analysis.getAnalysisGroup())) {
					analysisToDo.add(analysis);
				}
			}
		}
		if (analysisToDo.isEmpty()) {
			System.out.println("Nemate spremnih analiza iz vase oblasti za obradu.");
		}
		return analysisToDo;
	}

}