package service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.AnalysisGroup;
import model.MedicalFinding;
import model.Patient;
import model.Salary;

public class ReportService {

	public ReportService() {
		
	}
	
	public void generateExpensesReport(LocalDate from, LocalDate to, List<DayOfWeek> days) {
		for (Salary s : SalaryService.getSalariesByDate(from, to)) {
			if (true) {
				//asd
			}
		}
	}
	
	public void generateIncomeReport() {
		
	}
	
	
	public void generatePatientReport(LocalDate from, LocalDate to, List<AnalysisGroup> groups) {
		List<MedicalFinding> findings = MedicalFindingService.getByDate(from, to);
		List<String> reports = new ArrayList<String>();
		String header = "PACIJENT                       | BROJ ANALIZA     | UKUPNA CENA ";
		for (MedicalFinding mf : findings) {
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
			report += "  | " + numberOfAnalysis + "  | " + price ;
			reports.add(report);
		}
		
		System.out.println(header);
		for (String r : reports) {
			System.out.println(r + "\n");
		}
	}
	
}
