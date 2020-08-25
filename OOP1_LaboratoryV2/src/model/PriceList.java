package model;

public class PriceList {

	private double homeVisit;
	private double homeVisitTime;
	private double earningBasic;
	private double homeVisitBonus;
	private double analysisBonus;
	private double SSS4_coefficient;
	private double SSS6_coefficient;
	private double SSS8_coefficient;
	private double specialisationBonus;

	public PriceList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PriceList(double homeVisit, double homeVisitTime, double earningBasic, double homeVisitBonus,
			double analysisBonus, double sSS4_coefficient, double sSS6_coefficient, double sSS8_coefficient,
			double specialisationBonus) {
		super();
		this.homeVisit = homeVisit;
		this.homeVisitTime = homeVisitTime;
		this.earningBasic = earningBasic;
		this.homeVisitBonus = homeVisitBonus;
		this.analysisBonus = analysisBonus;
		SSS4_coefficient = sSS4_coefficient;
		SSS6_coefficient = sSS6_coefficient;
		SSS8_coefficient = sSS8_coefficient;
		this.specialisationBonus = specialisationBonus;
	}

	public double getHomeVisit() {
		return homeVisit;
	}

	public void setHomeVisit(double homeVisit) {
		this.homeVisit = homeVisit;
	}

	public double getHomeVisitTime() {
		return homeVisitTime;
	}

	public void setHomeVisitTime(double homeVisitTime) {
		this.homeVisitTime = homeVisitTime;
	}

	public double getEarningBasic() {
		return earningBasic;
	}

	public void setEarningBasic(double earningBasic) {
		this.earningBasic = earningBasic;
	}

	public double getHomeVisitBonus() {
		return homeVisitBonus;
	}

	public void setHomeVisitBonus(double homeVisitBonus) {
		this.homeVisitBonus = homeVisitBonus;
	}

	public double getAnalysisBonus() {
		return analysisBonus;
	}

	public void setAnalysisBonus(double analysisBonus) {
		this.analysisBonus = analysisBonus;
	}

	public double getSSS4_coefficient() {
		return SSS4_coefficient;
	}

	public void setSSS4_coefficient(double sSS4_coefficient) {
		SSS4_coefficient = sSS4_coefficient;
	}

	public double getSSS6_coefficient() {
		return SSS6_coefficient;
	}

	public void setSSS6_coefficient(double sSS6_coefficient) {
		SSS6_coefficient = sSS6_coefficient;
	}

	public double getSSS8_coefficient() {
		return SSS8_coefficient;
	}

	public void setSSS8_coefficient(double sSS8_coefficient) {
		SSS8_coefficient = sSS8_coefficient;
	}

	public double getSpecialisationBonus() {
		return specialisationBonus;
	}

	public void setSpecialisationBonus(double specialisationBonus) {
		this.specialisationBonus = specialisationBonus;
	}

	@Override
	public String toString() {
		return "HOMEVISIT|" + homeVisit + "\n" + "HOMEVISITTIME|" + homeVisitTime + "\n" + "EARNINGBASIC|"
				+ earningBasic + "\n" + "HOMEVISITBONUS|" + homeVisitBonus + "\n" +"ANALYSISBONUS|" + analysisBonus
				+"\n" + "SSS4_COEFFICIENT|" + SSS4_coefficient + "\n" + "SSS6_COEFFICIENT|" + SSS6_coefficient + "\n"
				+ "SSS6_COEFFICIENT|" + SSS8_coefficient + "\n" + "SPECIALIZATIONBONUS|" + specialisationBonus;
	}
	
	
	public String consoleView() {
		return "1. HOMEVISIT|" + homeVisit + "\n" + "2. HOMEVISITTIME|" + homeVisitTime + "\n" + "3. EARNINGBASIC|"
				+ earningBasic + "\n" + "4. HOMEVISITBONUS|" + homeVisitBonus + "\n" +"5. ANALYSISBONUS|" + analysisBonus
				+"\n" + "6. SSS4_COEFFICIENT|" + SSS4_coefficient + "\n" + "7. SSS6_COEFFICIENT|" + SSS6_coefficient + "\n"
				+ "8. SSS6_COEFFICIENT|" + SSS8_coefficient + "\n" + "9. SPECIALIZATIONBONUS|" + specialisationBonus;
	}
	
	
	

}
