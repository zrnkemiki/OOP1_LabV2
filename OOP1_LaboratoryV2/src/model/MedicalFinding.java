package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class MedicalFinding {
	private int id;
	private ArrayList<Analysis> analysis;
	private LocalDate date;
	private Patient patient;

	public MedicalFinding() {
		super();
		this.analysis = new ArrayList<Analysis>();
		// TODO Auto-generated constructor stub
	}

	public MedicalFinding(int id, ArrayList<Analysis> analysis, LocalDate date, Patient patient) {
		super();
		this.id = id;
		this.analysis = analysis;
		this.date = date;
		this.patient = patient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Analysis> getAnalysis() {
		return analysis;
	}

	public void setAnalysis(ArrayList<Analysis> analysis) {
		this.analysis = analysis;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void addAnalysis(Analysis a) {
		this.analysis.add(a);
	}
	
	public String getAnalysisID() {
		String ids = "";
		for (Analysis a : analysis) {
			ids += a.getId() + ",";
		}
		ids.substring(0, ids.length() - 1);
		return ids;
	}
	

	@Override
	public String toString() {
		
		return id + "|" + getAnalysisID() + "|" + date + "|" + patient.getLbo();
	}
	
	

}
