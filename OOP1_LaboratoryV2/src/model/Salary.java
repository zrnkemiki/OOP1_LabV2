package model;

import java.time.LocalDate;

public class Salary {

	private int id;
	private User user;
	private LocalDate dateFrom;
	private LocalDate dateUntil;
	private double basic;
	private double amount;

	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Salary(int id, User user, LocalDate dateFrom, LocalDate dateUntil, double basic, double amount) {
		super();
		this.id = id;
		this.user = user;
		this.dateFrom = dateFrom;
		this.dateUntil = dateUntil;
		this.basic = basic;
		this.amount = amount;
	}

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateUntil() {
		return dateUntil;
	}

	public void setDateUntil(LocalDate dateUntil) {
		this.dateUntil = dateUntil;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return id + "|" + user.getLbo() + "|" + dateFrom + "|" + dateUntil
				+ "|" + basic + "|" + amount;
	}
	
	
	public String consoleView() {
		return "Zaposleni: " + user.getFirstName() + " " + user.getLastName() + " |Period plate OD: " + dateFrom + " -- DO: " + dateUntil
				+ "| Osnovica za ovaj mesec: " + basic + "RSD |Iznos isplacene plate: " + amount + "RSD";
	}
	
	

}
