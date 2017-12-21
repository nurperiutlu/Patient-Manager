package com.patientmanager;

public class TestResult {

	int ID;
	String testName;
	String testResult;
	int patientID;
	
	public TestResult(int ID, String testName, String testResult, int patientID){
		
		this.ID = ID;
		this.testName = testName;
		this.testResult = testResult;
		this.patientID = patientID;
		
	}
	
	
}
