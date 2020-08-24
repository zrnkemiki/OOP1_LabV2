package model;


import java.time.LocalDate;

import enums.Sex;

public abstract class User {
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Sex sex;
	private String lbo;
	private Address address;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	

}
