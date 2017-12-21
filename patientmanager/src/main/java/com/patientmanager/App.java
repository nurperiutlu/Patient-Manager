package com.patientmanager;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {

	static Database db;

	public static void main(String[] args) throws SQLException {

		db = new Database();

		db.connectionToDerby();

		db.dropAllTables();

		db.createTables();
		Doctor.addRandomDoctors();
		Patient.addRandomPatients();
		Nurse.addRandomNurses();
		Treatment.addRandomTreatments();
		
		GUI gui = new GUI();		
		// refer to classes for login information


	}
}
