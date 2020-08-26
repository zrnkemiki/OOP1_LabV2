package model;

import java.time.LocalDate;

import enums.Sex;
import enums.StrucnaSprema;

public class MedicalTechnician extends User {
	private StrucnaSprema strucnaSprema;
	private LocalDate dateStarted;
	private int numberOfVisits;
		
	
	public MedicalTechnician() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public MedicalTechnician(StrucnaSprema strucnaSprema, LocalDate dateStarted, int numberOfVisits) {
		super();
		this.strucnaSprema = strucnaSprema;
		this.dateStarted = dateStarted;
		this.numberOfVisits = numberOfVisits;
	}



	public int getNumberOfVisits() {
		return numberOfVisits;
	}

	public void setNumberOfVisits(int numberOfVisits) {
		this.numberOfVisits = numberOfVisits;
	}

	public LocalDate getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(LocalDate dateStarted) {
		this.dateStarted = dateStarted;
	}

	public StrucnaSprema getStrucnaSprema() {
		return strucnaSprema;
	}

	public void setStrucnaSprema(StrucnaSprema strucnaSprema) {
		this.strucnaSprema = strucnaSprema;
	}
	
	
}
