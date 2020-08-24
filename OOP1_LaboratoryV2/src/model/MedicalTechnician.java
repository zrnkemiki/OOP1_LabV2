package model;

import java.time.LocalDate;

import enums.Sex;
import enums.StrucnaSprema;

public class MedicalTechnician extends User {
	private StrucnaSprema strucnaSprema;
	private LocalDate dateStarted;
	
		
	
	public MedicalTechnician() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicalTechnician(String firstName, String lastName, String username, String password, Sex sex, String lbo,
			String address, LocalDate dateOfBirth, String phoneNumber) {
		super(firstName, lastName, username, password, sex, lbo, address, dateOfBirth, phoneNumber);
		// TODO Auto-generated constructor stub
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
