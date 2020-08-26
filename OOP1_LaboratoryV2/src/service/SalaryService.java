package service;

import java.time.LocalDate;
import java.time.Period;

import model.DataBase;
import model.Laborant;
import model.MedicalTechnician;
import model.Salary;
import model.User;

public class SalaryService {
	
	
	public SalaryService() {
	}

	public void paySalaries() {
		DataBase.loadPriceList(); //ucitaj trenutno aktivan cenovnik
		for (String key : DataBase.users.keySet()) {
			User u = DataBase.users.get(key);
			if(u instanceof Laborant) {
				Laborant l = (Laborant) u;
				Salary s = new Salary();
				s.setUser(l);
				s.setId(DataBase.salaries.size()+1);
				s.setBasic(DataBase.priceList.getEarningBasic());
				s.setDateFrom(LocalDate.now());
				s.setDateUntil(LocalDate.now().plusMonths(1));
				int workPeriod = Period.between(l.getDateStarted(), LocalDate.now()).getYears();
				if(workPeriod < 1) {
					workPeriod = 1;
				}
				s.setAmount(DataBase.priceList.getSSS6_coefficient() * s.getBasic() * workPeriod + (DataBase.priceList.getSpecialisationBonus() * l.getSpecializations().size()));
					
				DataBase.salaries.add(s);
			}
			else if(u instanceof MedicalTechnician) {
				MedicalTechnician mt = (MedicalTechnician) u;
				Salary s = new Salary();
				s.setUser(mt);
				s.setId(DataBase.salaries.size()+1);
				s.setBasic(DataBase.priceList.getEarningBasic());
				s.setDateFrom(LocalDate.now());
				s.setDateUntil(LocalDate.now().plusMonths(1));
				int workPeriod = Period.between(mt.getDateStarted(), LocalDate.now()).getYears();
				if(workPeriod < 1) {
					workPeriod = 1;
				}
				s.setAmount(DataBase.priceList.getSSS6_coefficient() * s.getBasic() * workPeriod + (DataBase.priceList.getHomeVisitBonus() * mt.getNumberOfVisits()));
				mt.setNumberOfVisits(0);
				DataBase.saveUser();
				DataBase.salaries.add(s);
			}
			
		}
		System.out.println("Plate uspesno isplacene.");
		DataBase.saveSalaries();
	}
}
