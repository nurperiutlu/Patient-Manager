package com.patientmanager;

import java.sql.SQLException;

public class Patient extends Person{	
	
	public Patient(int ID, String firstName, String lastName, String sex, String password) {
		super(ID, firstName, lastName, sex, password);
		// TODO Auto-generated constructor stub
	}

	public void addNewPatient() throws SQLException{
		
		App.db.addNewPatient(this);
		
	}
	
	public static void addRandomPatients() throws SQLException{
		
		
		Patient p1 = new Patient(1, "ahmet", "turk", "male", "123456");
		Patient p2 = new Patient(2, "mehmet", "ozturk", "male", "123456");
		Patient p3 = new Patient(3, "veli", "cevdet", "male", "123456");
		Patient p4 = new Patient(4, "ayse", "gokkesen", "female", "123456");
		Patient p5 = new Patient(5, "aynur", "gokalp", "female", "123456");
		Patient p6 = new Patient(6, "fatma", "ozlem", "female", "123456");
		
		p1.addNewPatient();
		p2.addNewPatient();
		p3.addNewPatient();
		p4.addNewPatient();
		p5.addNewPatient();
		p6.addNewPatient();		
		
	}
	
	
}
