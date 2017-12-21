package com.patientmanager;

import java.sql.SQLException;

public class Doctor extends Staff{

	public Doctor(int ID, String firstName, String lastName, String sex, String password) {
		super(ID, firstName, lastName, sex, password);
		// TODO Auto-generated constructor stub
	}
	
	public void addNewDoctor() throws SQLException{
		
		App.db.addNewDoctor(this);
		
	}
	
	public static void addRandomDoctors() throws SQLException{
		
		
		Doctor d1 = new Doctor(13, "gregory", "house", "male", "123456");
		Doctor d2 = new Doctor(14, "mehmet", "ozturk", "male", "123456");
		Doctor d3 = new Doctor(15, "veli", "cevdet", "male", "123456");
		Doctor d4 = new Doctor(16, "ayse", "gokkesen", "female", "123456");
		Doctor d5 = new Doctor(17, "aynur", "gokalp", "female", "123456");
		Doctor d6 = new Doctor(18, "fatma", "ozlem", "female", "123456");
		
		d1.addNewDoctor();
		d2.addNewDoctor();
		d3.addNewDoctor();
		d4.addNewDoctor();
		d5.addNewDoctor();
		d6.addNewDoctor();		
		
	}

}
