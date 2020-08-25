package model;

import java.time.LocalDate;
import java.time.LocalTime;

import enums.SubmissionStatus;
import enums.SubmissionType;

public class Appointment {

	private int id;
	private LocalDate date;
	private LocalTime time;
	private SubmissionType submissionType;
	private SubmissionStatus submissionStatus;
	private MedicalFinding medicalFinding;

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointment(int id, LocalDate date, LocalTime time, SubmissionType submissionType,
			SubmissionStatus submissionStatus, MedicalFinding medicalFinding) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
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

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
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
	
	private String checkTime() {
		if(this.time == null) {
			return "";
		}
		else return ("| Vreme " +  this.time.toString());
	}

	@Override
	public String toString() {
		return id + "|" + date + "|" + time + "|"+ submissionType + "|" + submissionStatus + "|" + medicalFinding.getId();
	}

	public String consoleView() {
		return "Datum: " + date + checkTime() + "| Tip predaje: " + submissionType + "|Trenutni status:  " + submissionStatus;
	}

}
