package model;

import enums.AnalysisGroup;

public class ReferenceValue {

	private int id;
	private AnalysisGroup analysisGroup;
	private String name;
	private double minMale;
	private double maxMale;
	private double minFemale;
	private double maxFemale;
	private String unit;
	private double price;

	public ReferenceValue(int id, AnalysisGroup analysisGroup, String name, double minMale, double maxMale,
			double minFemale, double maxFemale, String unit, double price) {
		super();
		this.id = id;
		this.analysisGroup = analysisGroup;
		this.name = name;
		this.minMale = minMale;
		this.maxMale = maxMale;
		this.minFemale = minFemale;
		this.maxFemale = maxFemale;
		this.unit = unit;
		this.price = price;
	}

	public ReferenceValue() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMinMale() {
		return minMale;
	}

	public void setMinMale(double minMale) {
		this.minMale = minMale;
	}

	public double getMaxMale() {
		return maxMale;
	}

	public void setMaxMale(double maxMale) {
		this.maxMale = maxMale;
	}

	public double getMinFemale() {
		return minFemale;
	}

	public void setMinFemale(double minFemale) {
		this.minFemale = minFemale;
	}

	public double getMaxFemale() {
		return maxFemale;
	}

	public void setMaxFemale(double maxFemale) {
		this.maxFemale = maxFemale;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
