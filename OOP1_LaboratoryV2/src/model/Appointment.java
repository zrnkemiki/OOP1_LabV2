package model;

import java.time.LocalDate;

import enums.SubmissionStatus;
import enums.SubmissionType;

public class Appointment {

	private int id;
	private LocalDate date;
	private SubmissionType submissionType;
	private SubmissionStatus submissionStatus;
	private MedicalFinding medicalFinding;

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointment(int id, LocalDate date, SubmissionType submissionType, SubmissionStatus submissionStatus,
			MedicalFinding medicalFinding) {
		super();
		this.id = id;
		this.date = date;
		this.submissionType = submissionType;
		this.submissionStatus = submissionStatus;
		this.medicalFinding = medicalFinding;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public SubmissionType getSubmissionType() {
		return submissionType;
	}

	public void setSubmissionType(SubmissionType submissionType) {
		this.submissionType = submissionType;
	}

	public SubmissionStatus getSubmissionStatus() {
		return submissionStatus;
	}

	public void setSubmissionStatus(SubmissionStatus submissionStatus) {
		this.submissionStatus = submissionStatus;
	}

	public MedicalFinding getMedicalFinding() {
		return medicalFinding;
	}

	public void setMedicalFinding(MedicalFinding medicalFinding) {
		this.medicalFinding = medicalFinding;
	}

	@Override
	public String toString() {
		return id + "|" + date + "|" + submissionType + "|" + submissionStatus + "|" + medicalFinding.getId();
	}
	

	public String consoleView() {
		return "Datum: " + date + "|Tip predaje: " + submissionType + "|Trenutni status:  " + submissionStatus;
	}

}
