package com.tmm.maker.domain.enums;

public enum TimePeriods {
	LESS_THAN_ONE_HOUR("Less than an hour"), 
	ONE_TO_TWO_HOURS("One to two hours"), 
	TWO_TO_FIVE_HOURS("Two to five hours"), 
	LESS_THAN_ONE_DAY("Less than a day"), 
	ONE_TO_THREE_DAYS("One to three days"),
	LESS_THAN_ONE_WEEK("Less than a week"), 
	ONE_TO_TWO_WEEKS("One to two weeks"), 
	TWO_TO_FOUR_WEEKS("Two to four weeks"), 
	MORE_THAN_ONE_MONTH("More than a month");
	
	private String displayValue;
	
	TimePeriods(String dispVal){
		this.displayValue = dispVal;
	}
	
	public String getDisplayValue(){
		return displayValue;
	}
	
}
