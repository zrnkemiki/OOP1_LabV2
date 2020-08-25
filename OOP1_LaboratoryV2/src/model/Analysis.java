package model;

import enums.AnalysisGroup;
import enums.Sex;

public class Analysis {
	
	private int id;
	private AnalysisGroup analysisGroup;
	private double value;
	private String name;
	private boolean done;
	private ReferenceValue referenceValue;
	
	
	public Analysis() {
		super();
		this.done = false;
		// TODO Auto-generated constructor stub
	}
	
	public Analysis(int id, AnalysisGroup analysisGroup, double value, String name, boolean done,
			ReferenceValue referenceValue) {
		super();
		this.id = id;
		this.analysisGroup = analysisGroup;
		this.value = value;
		this.name = name;
		this.done = done;
		this.referenceValue = referenceValue;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AnalysisGroup getAnalysisGroup() {
		return analysisGroup;
	}
	public void setAnalysisGroup(AnalysisGroup analysisGroup) {
		this.analysisGroup = analysisGroup;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public ReferenceValue getReferenceValue() {
		return referenceValue;
	}
	public void setReferenceValue(ReferenceValue referenceValue) {
		this.referenceValue = referenceValue;
	}

	@Override
	public String toString() {
		return id + "|" + analysisGroup + "|" + value + "|" + name
				+ "|" + done + "|" + referenceValue.getId();
	}
	

	public String consoleView() {
		return "Grupa analize:  " + analysisGroup + "| Parametar:  " + name;
	}

	private String exportReferenceValue(Patient p) {
		if(p.getSex() == Sex.MALE) {
			return "Min vrednost: " + this.referenceValue.getMinMale() + " | Max vrednost: " + this.referenceValue.getMaxMale() + " | Jedinica mere: " + this.referenceValue.getUnit();
		}
		else {
			return "Min vrednost: " + this.referenceValue.getMinFemale() + " | Max vrednost: " + this.referenceValue.getMaxFemale() + "| Jedinica mere: " + this.referenceValue.getUnit(); 
		}
	}


	public String exportView(Patient patient) {
		return "Grupa analiza: " + analysisGroup + "| Parametar: " + name + " | Vasa vrednost: "  + String.format("%.2f",value) + " |#|"
				+ exportReferenceValue(patient);
			}
	
	
	
	
	
	

}
