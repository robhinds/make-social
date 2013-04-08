package com.tmm.maker.domain.enums;

public enum ProjectCategory {
	DIY("DIY"), 
	CRAFT("Craft"), 
	COOKERY("Cookery"), 
	KNITTING("Knitting"), 
	WOODWORK("Woodwork"), 
	METALWORK("Metalwork"), 
	ELECTRONICS("Electronics"), 
	SEWING("Sewing");
	
	
	private String displayValue;
	
	ProjectCategory(String dispVal){
		this.displayValue = dispVal;
	}
	
	public String getDisplayValue(){
		return displayValue;
	}
}
