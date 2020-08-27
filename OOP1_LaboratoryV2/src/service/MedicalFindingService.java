package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Sex;
import model.Analysis;
import model.Appointment;
import model.DataBase;
import model.MedicalFinding;
import model.Patient;

public class MedicalFindingService {
	
	public List<MedicalFinding> getPatientMedicalFindings(Patient patient) {
		List<MedicalFinding> mfs = new ArrayList<MedicalFinding>();
		for (MedicalFinding mf : DataBase.medicalFindings) {
			if(mf.isDone() && mf.getPatient().getLbo().equals(patient.getLbo())) {
				mfs.add(mf);
			}
			
		}
		return mfs;
	}
	
	public void exportMedicalFinding(MedicalFinding mf) {
		BufferedWriter bw;
		FileWriter fw;
		List<MedicalFinding> mfs = new ArrayList<MedicalFinding>();
		String data = "";
		String pol = "";
		LocalDate dateTaken = null;
		for (Appointment app : DataBase.appointments) {
			if(app.getMedicalFinding().getId() == mf.getId()) {
				dateTaken = app.getDate();
			}
		}
		if(mf.getPatient().getSex() == Sex.MALE) {
			pol = "MUSKI";
		}
		else {
			pol = "ZENSKI";
		}
		data += "Pacijent: " + mf.getPatient().getFirstName() + " " + mf.getPatient().getLastName() + "\n";
		data += "Pol:" + pol + "\n";
		data += "Datum rodjenja: " + mf.getPatient().getDateOfBirth()+ "\n";
		data += "Broj telefona: " + mf.getPatient().getPhoneNumber()+ "\n";
		data += "Datum uzorkovanja:" + dateTaken + "\n";
		data += "Datum nalaza:" + mf.getDate().toString() + "\n";
		data += "Cena: " + mf.getPrice() + "\n\n";
		data += "Rezultati: \n";
		for (Analysis a : mf.getAnalysis()) {
			data += a.exportView(mf.getPatient()) + "\n";
 			
		}
		try {
			fw = new FileWriter("src/Data/" + mf.getPatient().getFirstName() + mf.getPatient().getLbo() + ".txt");
			bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
			System.out.println("Postovani, vas nalaz je odstampan.");
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<MedicalFinding> getByDate(LocalDate from, LocalDate to) {
		List<MedicalFinding> found = new ArrayList<MedicalFinding>();
		for (MedicalFinding mf : DataBase.medicalFindings) {
			if (mf.getDate().isAfter(from) && mf.getDate().isBefore(to)) {
				found.add(mf);
			}
		}
		return found;
	}
}

