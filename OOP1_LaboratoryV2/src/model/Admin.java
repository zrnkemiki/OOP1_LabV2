package model;

import java.time.LocalDate;

import enums.Sex;
import enums.StrucnaSprema;

public class Admin extends User{
	private StrucnaSprema strucnaSprema;
	private LocalDate dateStarted;
	
	
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(String firstName, String lastName, String username, String password, Sex sex, String lbo,
			String address, LocalDate dateOfBirth, String phoneNumber) {
		super(firstName, lastName, username, password, sex, lbo, address, dateOfBirth, phoneNumber);
		// TODO Auto-generated constructor stub
	}
	public StrucnaSprema getStrucnaSprema() {
		return strucnaSprema;
	}
	public void setStrucnaSprema(StrucnaSprema strucnaSprema) {
		this.strucnaSprema = strucnaSprema;
	}
	public LocalDate getDateStarted() {
		return dateStarted;
	}
	public void setDateStarted(LocalDate dateStarted) {
		this.dateStarted = dateStarted;
	}
	
	
}
