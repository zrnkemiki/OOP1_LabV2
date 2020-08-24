package model;

import java.time.LocalDate;
import java.util.ArrayList;

import enums.AnalysisGroup;
import enums.Sex;
import enums.StrucnaSprema;

public class Laborant extends User {
	private StrucnaSprema strucnaSprema;
	private ArrayList<AnalysisGroup> specializations;
	private LocalDate dateStarted;
	
	public Laborant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Laborant(String firstName, String lastName, String username, String password, Sex sex, String lbo,
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
	public ArrayList<AnalysisGroup> getSpecializations() {
		return specializations;
	}
	public void setSpecializations(ArrayList<AnalysisGroup> specializations) {
		this.specializations = specializations;
	}
	public LocalDate getDateStarted() {
		return dateStarted;
	}
	public void setDateStarted(LocalDate dateStarted) {
		this.dateStarted = dateStarted;
	}
	
	
	
	
}
