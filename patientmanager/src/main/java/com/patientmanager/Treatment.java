package com.patientmanager;

import java.sql.SQLException;

public class Treatment {

	int ID;
	String medicines;
	int doctorID;
	int patientID;
	String prescription;
	String illnessName;
	int cost;

	public Treatment(int id, String medicines, int doctorID, int patientID, String prescription, String illnessName,
			int cost) {

		this.ID = id;
		this.medicines = medicines;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.prescription = prescription;
		this.illnessName = illnessName;
		this.cost = cost;

	}

	public void addNewTreatment() throws SQLException {

		App.db.addNewTreatment(this);

	}
	
	public static void addRandomTreatments() throws SQLException{
		Treatment t1 = new Treatment(7, "augmentin", 15, 3, "1 augmentin day night", "farenjit", 500);

		t1.addNewTreatment();		
	}

}
