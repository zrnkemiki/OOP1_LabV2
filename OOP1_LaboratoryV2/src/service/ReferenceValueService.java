package service;

import java.util.Scanner;

import enums.AnalysisGroup;
import model.DataBase;
import model.ReferenceValue;

public class ReferenceValueService {
	
	public static void newReferenceValue(Scanner sc, AnalysisGroup ag) {
		ReferenceValue rv = new ReferenceValue();
		System.out.println("Unesite ime parametra:");
		String param = IOHandler.stringInput(sc);
		rv.setName(param.toUpperCase());
		rv.setAnalysisGroup(ag);	
		System.out.println("Unesite min zensku vrednost");
		double value = IOHandler.doubleInput(sc);
		rv.setMinFemale(value);
		System.out.println("Unesite max zensku vrednost");
		value = IOHandler.doubleInput(sc);
		rv.setMaxFemale(value);
		System.out.println("Unesite min musku vrednost");
		value = IOHandler.doubleInput(sc);
		rv.setMinMale(value);
		System.out.println("Unesite max musku vrednost");
		value = IOHandler.doubleInput(sc);
		rv.setMaxMale(value);
		System.out.println("Unesite jedinicu mere");
		String unit = IOHandler.stringInput(sc);
		rv.setUnit(unit);
		System.out.println("Unesite cenu");
		value = IOHandler.doubleInput(sc);
		rv.setPrice(value);
		rv.setId(DataBase.referenceValues.size()+1);
		
		DataBase.referenceValues.put(rv.getName(), rv);
		DataBase.saveReferenceValue();
	}

	public static void getAllReferenceValues() {
		for (String key : DataBase.referenceValues.keySet()) {
			System.out.println("Grupa: " + DataBase.referenceValues.get(key).getAnalysisGroup() + "| Naziv: " + DataBase.referenceValues.get(key).getName() + "| Cena analize: " + DataBase.referenceValues.get(key).getPrice());
		}
		
	}
	

}
