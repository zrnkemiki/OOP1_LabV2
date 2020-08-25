package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Analysis;
import model.DataBase;
import model.MedicalFinding;
import model.Patient;

public class MedicalFindingService {
	
	public static ArrayList<MedicalFinding> getPatientMedicalFindings(Patient patient) {
		ArrayList<MedicalFinding> mfs = new ArrayList<MedicalFinding>();
		for (MedicalFinding mf : DataBase.medicalFindings) {
			if(mf.getPatient().getLbo().equals(patient.getLbo())) {
				mfs.add(mf);
			}
			
		}
		return mfs;
	}
	
	public static void exportMedicalFinding(MedicalFinding mf) {
		BufferedWriter bw;
		FileWriter fw;
		ArrayList<MedicalFinding> mfs = new ArrayList<MedicalFinding>();
		String data = "";
		data += "Pacijent: " + mf.getPatient().getFirstName() + " " + mf.getPatient().getLastName() + "\n";
		data += "Datum:" + mf.getDate().toString() + "\n\n";
		for (Analysis a : mf.getAnalysis()) {
			data += a.toString() + "\n";
 			
		}
		try {
			fw = new FileWriter("src/Data/" + mf.getPatient().getFirstName() + mf.getPatient().getLbo() + ".txt");
			bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

