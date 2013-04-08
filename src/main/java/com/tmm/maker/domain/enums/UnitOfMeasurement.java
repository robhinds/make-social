package com.tmm.maker.domain.enums;

public enum UnitOfMeasurement {
	GRAMS("grams"), 
	MILLIGRAMS("milligrams"), 
	KILOGRAMS("kilograms"), 
	OUNCES("ounces"), 
	POUNDS("pounds"), 
	MILLILITRES("millilitres"), 
	LITRES("litres"), 
	CENTIMETERS("centimeters"), 
	METERS("meters"), 
	MILLIMETERS("millimeters"), 
	TABLESPOONS("tablespoons"), 
	TEASPOONS("teaspoons"), 
	CUPS("cups");
	
	
	private String displayValue;
	
	UnitOfMeasurement(String dispVal){
		this.displayValue = dispVal;
	}
	
	public String getDisplayValue(){
		return displayValue;
	}
}
