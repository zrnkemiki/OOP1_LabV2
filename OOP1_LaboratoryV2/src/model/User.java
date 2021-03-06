package model;


import java.time.LocalDate;
import enums.AnalysisGroup;
import enums.Sex;

public abstract class User {
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Sex sex;
	private String lbo;
	private String address;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String firstName, String lastName, String username, String password, Sex sex, String lbo,
			String address, LocalDate dateOfBirth, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.lbo = lbo;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public String getLbo() {
		return lbo;
	}
	public void setLbo(String lbo) {
		this.lbo = lbo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	private String checkType() {
		if(this instanceof Patient) {
			return "PACIJENT";
		}
		else if(this instanceof Laborant) {
			Laborant a = (Laborant) this;
			String spec = "";
			for (AnalysisGroup ag : a.getSpecializations()) {
				spec += ag.toString() + ",";
			}
			spec = spec.substring(0, spec.length() - 1);
			return "LABORANT|" + a.getStrucnaSprema() + "|" + spec + "|" +  a.getDateStarted();
		}
		else if(this instanceof MedicalTechnician) {
			MedicalTechnician mt = (MedicalTechnician) this;
			return "MEDICINSKI_TEHNICAR|" + mt.getStrucnaSprema() + "|" + mt.getDateStarted() + "|" + mt.getNumberOfVisits();
		}
		else {
			Admin a = (Admin) this;
			return "ADMIN|" + a.getStrucnaSprema();
		}
	}
	
	@Override
	public String toString() {
		return firstName + "|" + lastName + "|" + username + "|"
				+ password + "|" + lbo + "|" + sex + "|" + dateOfBirth + "|" + address
				+ "|" + phoneNumber + "|" + checkType();
	}
	
	
	
	
	

}
