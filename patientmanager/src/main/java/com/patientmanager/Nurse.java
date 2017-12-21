package com.patientmanager;

import java.sql.SQLException;

public class Nurse extends Staff {

	public Nurse(int ID, String firstName, String lastName, String sex, String password) {
		super(ID, firstName, lastName, sex, password);
		// TODO Auto-generated constructor stub
	}

	
	public void addNewNurse() throws SQLException{
		
		App.db.addNewNurse(this);
		
	}
	
	public static void addRandomNurses() throws SQLException{
		
		
		Nurse n1 = new Nurse(7, "ahmet", "turk", "male", "123456");
		Nurse n2 = new Nurse(8, "mehmet", "ozturk", "male", "123456");
		Nurse n3 = new Nurse(9, "veli", "cevdet", "male", "123456");
		Nurse n4 = new Nurse(10, "ayse", "gokkesen", "female", "123456");
		Nurse n5 = new Nurse(11, "aynur", "gokalp", "female", "123456");
		Nurse n6 = new Nurse(12, "fatma", "ozlem", "female", "123456");
		
		n1.addNewNurse();
		n2.addNewNurse();
		n3.addNewNurse();
		n4.addNewNurse();
		n5.addNewNurse();
		n6.addNewNurse();		
		
	}
	
}
