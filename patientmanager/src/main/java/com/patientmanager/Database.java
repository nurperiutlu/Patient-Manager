package com.patientmanager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Database {

	static Connection conn;

	public void dropAllTables() throws SQLException {

		Statement stmt = conn.createStatement();

		DatabaseMetaData meta = conn.getMetaData();

		ResultSet res = meta.getTables(null, null, "PATIENT", new String[] { "TABLE" });
		if (res.next()) { // if table exists
			stmt.executeUpdate("Drop Table PATIENT");
			System.out.println("Dropped PATIENT");
		}

		res = meta.getTables(null, null, "DOCTOR", new String[] { "TABLE" });
		if (res.next()) { // if table exists
			stmt.executeUpdate("Drop Table DOCTOR");
			System.out.println("Dropped DOCTOR");
		}

		res = meta.getTables(null, null, "NURSE", new String[] { "TABLE" });
		if (res.next()) { // if table exists
			stmt.executeUpdate("Drop Table NURSE");
			System.out.println("Dropped NURSE");
		}

		res = meta.getTables(null, null, "TREATMENT", new String[] { "TABLE" });
		if (res.next()) { // if table exists
			stmt.executeUpdate("Drop Table TREATMENT");
			System.out.println("Dropped TREATMENT");
		}

		res = meta.getTables(null, null, "APPOINTMENT", new String[] { "TABLE" });
		if (res.next()) { // if table exists
			stmt.executeUpdate("Drop Table APPOINTMENT");
			System.out.println("Dropped APPOINTMENT");
		}

		res = meta.getTables(null, null, "TESTRESULT", new String[] { "TABLE" });
		if (res.next()) { // if table exists
			stmt.executeUpdate("Drop Table TESTRESULT");
			System.out.println("Dropped TESTRESULT");
		}

		res = meta.getTables(null, null, "ROUTINECONTROL", new String[] { "TABLE" });
		if (res.next()) { // if table exists
			stmt.executeUpdate("Drop Table ROUTINECONTROL");
			System.out.println("Dropped ROUTINECONTROL");
		}

	}

	public void addNewPatient(Patient p) throws SQLException {

		Statement stmt = conn.createStatement();

		String s = String.format("insert into PATIENT values (%d, '%s', '%s', '%s', '%s')", p.ID, p.firstName,
				p.lastName, p.sex, p.password);
		stmt.executeUpdate(s);

	}

	public void addNewDoctor(Doctor d) throws SQLException {

		Statement stmt = conn.createStatement();

		String s = String.format("insert into DOCTOR values (%d, '%s', '%s', '%s', '%s')", d.ID, d.firstName,
				d.lastName, d.sex, d.password);
		stmt.executeUpdate(s);

	}

	public void addNewNurse(Nurse n) throws SQLException {

		Statement stmt = conn.createStatement();

		String s = String.format("insert into NURSE values (%d, '%s', '%s', '%s', '%s')", n.ID, n.firstName,
				n.lastName, n.sex, n.password);
		stmt.executeUpdate(s);

	}

	public void addNewTreatment(Treatment t) throws SQLException {

		int randomNum = ThreadLocalRandom.current().nextInt(1, 1000000 + 1);
		
		Statement stmt = conn.createStatement();

		String s = String.format("insert into TREATMENT values (%d, '%s', %d, %d, '%s', '%s', %d)", randomNum,
				t.medicines, t.doctorID, t.patientID, t.prescription, t.illnessName, t.cost);
		stmt.executeUpdate(s);

	}
	
	public void addNewTestResult(TestResult t) throws SQLException {

		int randomNum = ThreadLocalRandom.current().nextInt(1, 1000000 + 1);
		
		Statement stmt = conn.createStatement();

		String s = String.format("insert into TESTRESULT values (%d, '%s', '%s', %d)", randomNum,
				t.testName, t.testResult, t.patientID);
		stmt.executeUpdate(s);

	}
	
	public void addNewRoutineControl(RoutineControl r) throws SQLException {

		int randomNum = ThreadLocalRandom.current().nextInt(1, 1000000 + 1);
		
		Statement stmt = conn.createStatement();

		String s = String.format("insert into ROUTINECONTROL values (%d, %d, %d, '%s')", randomNum,
				r.nurseID, r.patientID, r.results);
		stmt.executeUpdate(s);

	}

	public void createTables() throws SQLException {

		Statement stmt = conn.createStatement();
		DatabaseMetaData meta = conn.getMetaData();

		ResultSet res = meta.getTables(null, null, "patient", new String[] { "TABLE" });
		if (!res.next()) { // if table exists
			stmt.executeUpdate(
					"Create table patient (id int primary key, firstname varchar(30), lastname varchar(30), sex varchar(30), password varchar(30))");
		}

		res = meta.getTables(null, null, "doctor", new String[] { "TABLE" });
		if (!res.next()) { // if table exists
			stmt.executeUpdate(
					"Create table doctor (id int primary key, firstname varchar(30), lastname varchar(30), sex varchar(30), password varchar(30))");
		}

		res = meta.getTables(null, null, "nurse", new String[] { "TABLE" });
		if (!res.next()) { // if table exists
			stmt.executeUpdate(
					"Create table nurse (id int primary key, firstname varchar(30), lastname varchar(30), sex varchar(30), password varchar(30))");
		}

		res = meta.getTables(null, null, "treatment", new String[] { "TABLE" });
		if (!res.next()) { // if table exists
			stmt.executeUpdate("Create table treatment (id int primary key, medicines varchar(100), doctorid int, patientid int, prescription varchar(100), illnessname varchar(100), cost real)");
		}

		res = meta.getTables(null, null, "testresult", new String[] { "TABLE" });
		if (!res.next()) { // if table exists
			stmt.executeUpdate("Create table testresult (id int primary key, testname varchar(100), testresult varchar(100), patientid int)");
		}

		res = meta.getTables(null, null, "routinecontrol", new String[] { "TABLE" });
		if (!res.next()) { // if table exists
			stmt.executeUpdate("Create table routinecontrol (id int primary key, nurseid int, patientid int, results varchar(100))");
		}

	}

	public void connectionToDerby() throws SQLException {

		String dbUrl = "jdbc:derby:c:\\patientmanager\\hazal\\MyDB\\demo; create=true";//
		conn = DriverManager.getConnection(dbUrl);
		if (conn != null) {
			System.out.println("Connected to database");
		}
	}

	public void killConnection() throws SQLException {

		String dbUrl = "jdbc:derby:c:\\patientmanager\\hazal\\MyDB\\demo; shutdown=true";//
		conn = DriverManager.getConnection(dbUrl);
		if (conn != null) {
			System.out.println("Killed connection");
		}

	}
	
	public List<TestResult> getTestResults(int ID) throws SQLException{
		
		List<TestResult> testResultList = new LinkedList<TestResult>();

		Statement stmt = conn.createStatement();
		String query = String.format("SELECT * FROM TESTRESULT WHERE patientid=%d", ID);
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {

			TestResult t = new TestResult(rs.getInt("id"), rs.getString("testName"), rs.getString("testResult"),
					rs.getInt("patientID"));

			testResultList.add(t);
		}

		return testResultList;	
		
	}

	public List<Treatment> getTreatments(int ID) throws SQLException {

		List<Treatment> treatmentList = new LinkedList<Treatment>();

		Statement stmt = conn.createStatement();
		String query = String.format("SELECT * FROM TREATMENT WHERE patientid=%d", ID);
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {

			Treatment t = new Treatment(rs.getInt("id"), rs.getString("medicines"), rs.getInt("doctorID"),
					rs.getInt("patientID"), rs.getString("prescription"), rs.getString("illnessName"), rs.getInt("cost"));

			treatmentList.add(t);
		}

		return treatmentList;
	}

	public String login(int ID, String password) throws SQLException {

		Statement stmt = conn.createStatement();
		String query = String.format("SELECT COUNT(*) AS total FROM DOCTOR WHERE id= %d AND password='%s'",
				ID, password);

		ResultSet rs = stmt.executeQuery(query);

		if (rs.next()) {
			int count = rs.getInt("total");
			if (count > 0)
				return "doctor";
		}

		query = String.format("SELECT COUNT(*) AS total FROM NURSE WHERE id= %d AND password='%s'", ID,
				password);

		rs = stmt.executeQuery(query);

		if (rs.next()) {
			int count = rs.getInt("total");
			if (count > 0)
				return "nurse";
		}

		query = String.format("SELECT COUNT(*) AS total FROM PATIENT WHERE id= %d AND password='%s'", ID,
				password);

		rs = stmt.executeQuery(query);

		if (rs.next()) {
			int count = rs.getInt("total");
			if (count > 0)
				return "patient";
		}

		return null;
	}

}
