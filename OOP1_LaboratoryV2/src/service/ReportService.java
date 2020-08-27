package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.AnalysisGroup;
import model.Analysis;
import model.DataBase;
import model.MedicalFinding;
import model.Patient;
import model.Salary;
import model.User;

public class ReportService {

	public ReportService() {
		
	}
	
	public void generateExpensesReport(LocalDate from, LocalDate to) {
		double expenses = 0;
		for (Salary s : SalaryService.getSalariesByDate(from, to)) {
			System.out.println(s.consoleView());
			expenses += s.getAmount();
		}
		System.out.println("=============================================");
		System.out.println("Ukupan rashod od isplata plata:");
		System.out.println("Period OD " + from + " -- DO " + to);
		System.out.println("Iznos rashoda: " + expenses);
	}
	
	public void generateIncomeReport(LocalDate from, LocalDate to) {
		double income = 0;
		for (MedicalFinding mf : DataBase.medicalFindings) {
			if(mf.getDate().isAfter(from) && mf.getDate().isBefore(to)) {
				System.out.println(mf.consoleView());
				income += mf.getPrice();
			}
		}
		System.out.println("=============================================");
		System.out.println("Ukupan prihod od nalaza i uzimanja uzoraka:");
		System.out.println("Period OD " + from + " -- DO " + to);
		System.out.println("Iznos prihoda: " + income);
	}
	
	
	public void generatePatientReport(LocalDate from, LocalDate to) {
		List<MedicalFinding> findings = MedicalFindingService.getByDate(from, to);
		List<String> reports = new ArrayList<String>();
		List<User> doneUsers = new ArrayList<User>();
		String header = "PACIJENT                       | BROJ NALAZA  | UKUPNA CENA ";
		header += "\n==============================================================";
		for (MedicalFinding mf : findings) {
			if (doneUsers.contains(mf.getPatient())) {
				continue;
			}
			String report = "";
			report += mf.getPatient().getFirstName() + " " + mf.getPatient().getLastName() + " [" + mf.getPatient().getLbo() + "]";
			Patient current = mf.getPatient();
			int numberOfAnalysis = 0;
			double price = 0;
			for (MedicalFinding mf2 : findings) {
				if (mf2.getPatient().equals(current)) {
					numberOfAnalysis++;
					price += mf2.getPrice();
				}
			}
			report += "       | " + numberOfAnalysis + "            | " + price ;
			reports.add(report);
			doneUsers.add(mf.getPatient());
		}
		
		System.out.println(header);
		for (String r : reports) {
			System.out.println(r + "\n");
		}
	}
	
	public void generatePatientReportFilterByGroup(LocalDate from, LocalDate to, List<AnalysisGroup> groups) {
		List<MedicalFinding> findings = MedicalFindingService.getByDate(from, to);
		List<String> reports = new ArrayList<String>();
		List<User> doneUsers = new ArrayList<User>();
		String header = "PACIJENT                       |";
		for (AnalysisGroup group : groups) {
			header += group + " | ";
		}
		header += "\n==============================================================";
		for (MedicalFinding mf : findings) {
			if (doneUsers.contains(mf.getPatient())) {
				continue;
			}
			String report = "";
			report += mf.getPatient().getFirstName() + " " + mf.getPatient().getLastName() + " [" + mf.getPatient().getLbo() + "]      ";
			int hematologija = 0;
			int biohemija = 0;
			int hormoni = 0;
			Patient current = mf.getPatient();
			for (MedicalFinding mf2 : findings) {
				if (mf2.getPatient().equals(current)) {
					for (Analysis a : mf2.getAnalysis()) {
						if (groups.contains(a.getAnalysisGroup())) {
							if (a.getAnalysisGroup() == AnalysisGroup.BIOHEMIJA) {
								biohemija++;
							} else if (a.getAnalysisGroup() == AnalysisGroup.HEMATOLOGIJA) {
								hematologija++;
							} else if (a.getAnalysisGroup() == AnalysisGroup.HORMONI) {
								hormoni++;
							}
						}
					}
				}
			}
			
			doneUsers.add(current);
			for (AnalysisGroup group : groups) {
				if (group == AnalysisGroup.BIOHEMIJA) {
					report += "  |   " + biohemija + "   "; 
				} else if (group == AnalysisGroup.HEMATOLOGIJA) {
					report += "  |      " + hematologija+ "   ";
				} else if (group == AnalysisGroup.HORMONI) {
					report += "  |      " + hormoni+ "   ";
				}
			}
			reports.add(report);
		}
		
		System.out.println(header);
		for (String r : reports) {
			System.out.println(r + "\n");
		}
	}
	
}
