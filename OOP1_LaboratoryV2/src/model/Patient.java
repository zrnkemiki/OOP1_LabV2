package model;

import java.time.LocalDate;

import enums.Sex;

public class Patient extends User {

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(String firstName, String lastName, String username, String password, Sex sex, String lbo,
			String address, LocalDate dateOfBirth, String phoneNumber) {
		super(firstName, lastName, username, password, sex, lbo, address, dateOfBirth, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	
}
