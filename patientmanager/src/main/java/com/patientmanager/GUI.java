package com.patientmanager;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JPanel panel = new JPanel();
	JButton blogin = new JButton("Login");
	JButton addTreatmentButton = new JButton("Add Treatment");
	JButton addTestResultButton = new JButton("Add Test Result");
	JButton viewTreatmentsButton = new JButton("View Treatments");
	JButton viewTreatmentsByPatientButton = new JButton("View Treatments");
	JButton addRoutineControlButton = new JButton("Add Routine Control");
	JButton viewTestResultsButton = new JButton("View Test Results");
	JButton viewTestResultsByPatientButton = new JButton("View Test Results");
	JButton viewBillsByPatientButton = new JButton("View Bills");
	JButton addTreatmentToDatabaseButton = new JButton("Add Treatment");
	JButton getTreatmentFromDatabaseButton = new JButton("View Treatments");
	JButton addTestResultToDatabaseButton = new JButton("Add Test Result");
	JButton addRoutineControlToDatabaseButton = new JButton("Add Routine Control");
	
	JTextField txMedicinesAddTreatment = new JTextField(150);
	JTextField txDoctorIDAddTreatment = new JTextField(20);
	JTextField txPatientIDAddTreatment = new JTextField(20);
	JTextField txPrescriptionAddTreatment = new JTextField(150);
	JTextField txIllnessAddTreatment = new JTextField(40);
	JTextField txCostAddTreatment = new JTextField(20);
	JTextField txPatientIDViewTreatment = new JTextField(20);
	JTextField txTestNameAddTestResult = new JTextField(50);
	JTextField txTestResultAddTestResult = new JTextField(100);
	JTextField txPatientIDAddTestResult = new JTextField(20);
	JTextField txNurseIDAddRoutineControl = new JTextField(20);
	JTextField txPatientIDAddRoutineControl = new JTextField(20);
	JTextField txResultsAddRoutineControl = new JTextField(20);
	
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);

	static int patientID;

	public GUI() {

		super("Login");
		displayLoginWindow();
		buttonActions();

	}

	public void displayLoginWindow() {

		setSize(500, 300);
		setLocation(500, 280);
		panel.setLayout(null);

		JLabel userNameLabel = new JLabel("ID: ");
		JLabel passwordLabel = new JLabel("Password: ");

		userNameLabel.setBounds(120, 40, 100, 100);
		passwordLabel.setBounds(120, 40, 150, 150);
		txuser.setBounds(210, 80, 150, 20);
		pass.setBounds(210, 105, 150, 20);
		blogin.setBounds(210, 160, 80, 20);

		panel.add(blogin);
		panel.add(txuser);
		panel.add(pass);
		panel.add(userNameLabel);
		panel.add(passwordLabel);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void openDoctorPanel() {

		JFrame doctorFrame = new JFrame("Doctor Panel");
		JPanel doctorPanel = new JPanel();
		doctorFrame.setSize(800, 600);
		doctorFrame.setLayout(null);
		doctorFrame.add(doctorPanel);

		addTreatmentButton.setBounds(250, 50, 300, 100);
		addTestResultButton.setBounds(250, 200, 300, 100);
		viewTreatmentsButton.setBounds(250, 350, 300, 100);

		doctorFrame.add(addTreatmentButton);
		doctorFrame.add(addTestResultButton);
		doctorFrame.add(viewTreatmentsButton);

		doctorFrame.setVisible(true);

	}

	public void openNursePanel() {

		JFrame nurseFrame = new JFrame("Nurse Panel");
		JPanel nursePanel = new JPanel();
		nurseFrame.setSize(800, 600);
		nurseFrame.setLayout(null);
		nurseFrame.add(nursePanel);

		addRoutineControlButton.setBounds(250, 200, 300, 100);

		nurseFrame.add(addRoutineControlButton);

		nurseFrame.setVisible(true);
	}

	public void openPatientPanel() {

		JFrame patientFrame = new JFrame("Patient Panel");
		JPanel patientPanel = new JPanel();
		patientFrame.setSize(800, 600);
		patientFrame.setLayout(null);
		patientFrame.add(patientPanel);

		viewTreatmentsByPatientButton.setBounds(250, 50, 300, 100);
		viewTestResultsByPatientButton.setBounds(250, 200, 300, 100);
		viewBillsByPatientButton.setBounds(250, 350, 300, 100);

		patientFrame.add(viewTreatmentsByPatientButton);
		patientFrame.add(viewTestResultsByPatientButton);
		patientFrame.add(viewBillsByPatientButton);

		patientFrame.setVisible(true);

	}

	public void buttonActions() {
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int userID = Integer.parseInt(txuser.getText());
				String ppaswd = pass.getText();

				try {
					String result = App.db.login(userID, ppaswd);
					if (result != null) {

						if (result == "doctor") {

							openDoctorPanel();

						}

						else if (result == "nurse") {

							openNursePanel();

						}

						else if (result == "patient") {

							patientID = userID;
							openPatientPanel();

						}

						JFrame regFace = new JFrame();
						regFace.setVisible(true);
						dispose();
					} else {

						JOptionPane.showMessageDialog(null, "Wrong Password / Username");
						txuser.setText("");
						pass.setText("");
						txuser.requestFocus();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		viewBillsByPatientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				JFrame billsFrame = new JFrame("Bills Panel");
				JPanel billsPanel = new JPanel();
				billsFrame.setSize(800, 600);
				billsFrame.setLayout(new BorderLayout());
				billsFrame.add(billsPanel);

				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("TreatmentID");
				model.addColumn("Cost");

				List<Treatment> treatmentList;
				try {
					treatmentList = App.db.getTreatments(patientID);
					
					for(Treatment t : treatmentList){
						model.addRow(new Object[] { t.ID, t.cost });						
					}					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				JTable billsTable = new JTable(model);
				billsTable.setFillsViewportHeight(true);
				JScrollPane tableContainer = new JScrollPane(billsTable);
				tableContainer.setBounds(100, 100, 300, 600);
				billsPanel.add(tableContainer);

				billsFrame.setVisible(true);
			}
		});
		
		viewTestResultsByPatientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				JFrame testResultsFrame = new JFrame("Test Results Panel");
				JPanel testResultsPanel = new JPanel();
				testResultsFrame.setSize(800, 600);
				testResultsFrame.setLayout(new BorderLayout());
				testResultsFrame.add(testResultsPanel);

				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("ID");
				model.addColumn("Name");
				model.addColumn("Result");

				List<TestResult> testResultList;
				try {
					testResultList = App.db.getTestResults(patientID);
					
					for(TestResult t : testResultList){
						model.addRow(new Object[] { t.ID, t.testName, t.testResult });						
					}					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				JTable testResultsTable = new JTable(model);
				testResultsTable.setFillsViewportHeight(true);
				JScrollPane tableContainer = new JScrollPane(testResultsTable);
				tableContainer.setBounds(100, 100, 300, 600);
				testResultsPanel.add(tableContainer);

				testResultsFrame.setVisible(true);
			}
		});
		
		viewTreatmentsByPatientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				JFrame treatmentsFrame = new JFrame("Treatments Panel");
				JPanel treatmentsPanel = new JPanel();
				treatmentsFrame.setSize(800, 600);
				treatmentsFrame.setLayout(new BorderLayout());
				treatmentsFrame.add(treatmentsPanel);

				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("ID");
				model.addColumn("Medicines");
				model.addColumn("Prescription");
				model.addColumn("Illness");

				List<Treatment> treatmentList;
				try {
					treatmentList = App.db.getTreatments(patientID);
					
					for(Treatment t : treatmentList){
						model.addRow(new Object[] { t.ID, t.medicines, t.prescription, t.illnessName });						
					}					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				JTable testResultsTable = new JTable(model);
				testResultsTable.setFillsViewportHeight(true);
				JScrollPane tableContainer = new JScrollPane(testResultsTable);
				tableContainer.setBounds(100, 100, 300, 600);
				treatmentsPanel.add(tableContainer);

				treatmentsFrame.setVisible(true);
			}
		});
		
		
		addTreatmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				JFrame addTreatmentFrame = new JFrame();
				JPanel addTreatmentPanel = new JPanel();
				
				addTreatmentFrame.setSize(700, 600);
				addTreatmentFrame.setLocation(100, 50);
				addTreatmentPanel.setLayout(null);
				
				JLabel medicinesLabelAddTreatment = new JLabel("Medicines: ");				
				medicinesLabelAddTreatment.setBounds(100, 50, 100, 50);
				addTreatmentPanel.add(medicinesLabelAddTreatment);
				
				JLabel doctorIDLabelAddTreatment = new JLabel("Doctor ID: ");
				doctorIDLabelAddTreatment.setBounds(100, 100, 300, 50);
				addTreatmentPanel.add(doctorIDLabelAddTreatment);
				
				JLabel patientIDLabelAddTreatment = new JLabel("Patient ID: ");
				patientIDLabelAddTreatment.setBounds(100, 150, 300, 50);
				addTreatmentPanel.add(patientIDLabelAddTreatment);
				
				JLabel prescriptionLabelAddTreatment = new JLabel("Prescription: ");
				prescriptionLabelAddTreatment.setBounds(100, 200, 300, 50);
				addTreatmentPanel.add(prescriptionLabelAddTreatment);
				
				JLabel illnessNameLabelAddTreatment = new JLabel("Illness: ");
				illnessNameLabelAddTreatment.setBounds(100, 250, 300, 50);
				addTreatmentPanel.add(illnessNameLabelAddTreatment);
				
				JLabel costLabelAddTreatment = new JLabel("Cost: ");
				costLabelAddTreatment.setBounds(100, 300, 300, 50);
				addTreatmentPanel.add(costLabelAddTreatment);		

				
				txMedicinesAddTreatment.setBounds(250, 50, 300, 50);
				addTreatmentPanel.add(txMedicinesAddTreatment);				
				
				txDoctorIDAddTreatment.setBounds(250, 100, 300, 50);
				addTreatmentPanel.add(txDoctorIDAddTreatment);				
				
				txPatientIDAddTreatment.setBounds(250, 150, 300, 50);
				addTreatmentPanel.add(txPatientIDAddTreatment);				
				
				txPrescriptionAddTreatment.setBounds(250, 200, 300, 50);
				addTreatmentPanel.add(txPrescriptionAddTreatment);				
				
				txIllnessAddTreatment.setBounds(250, 250, 300, 50);
				addTreatmentPanel.add(txIllnessAddTreatment);				
				
				txCostAddTreatment.setBounds(250, 300, 300, 50);			
				addTreatmentPanel.add(txCostAddTreatment);
				
				addTreatmentToDatabaseButton.setBounds(200, 400, 300, 100);
				addTreatmentPanel.add(addTreatmentToDatabaseButton);							

				addTreatmentFrame.add(addTreatmentPanel);
				addTreatmentFrame.setVisible(true);
				
			}
		});
		
		viewTreatmentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				JFrame viewTreatmentsFrame = new JFrame();
				JPanel viewTreatmentsPanel = new JPanel();
				
				viewTreatmentsFrame.setSize(500, 400);
				viewTreatmentsFrame.setLocation(400, 400);
				viewTreatmentsPanel.setLayout(null);
				
				JLabel medicinesLabelAddTreatment = new JLabel("Patient ID: ");				
				medicinesLabelAddTreatment.setBounds(100, 50, 100, 50);
				viewTreatmentsPanel.add(medicinesLabelAddTreatment);
				
				txPatientIDViewTreatment.setBounds(200, 50, 200, 50);
				viewTreatmentsPanel.add(txPatientIDViewTreatment);				
				
				getTreatmentFromDatabaseButton.setBounds(100, 150, 300, 100);
				viewTreatmentsPanel.add(getTreatmentFromDatabaseButton);							

				viewTreatmentsFrame.add(viewTreatmentsPanel);
				viewTreatmentsFrame.setVisible(true);
			}
		});
		
		getTreatmentFromDatabaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				JFrame treatmentsFrame = new JFrame("Treatments Panel");
				JPanel treatmentsPanel = new JPanel();
				treatmentsFrame.setSize(800, 600);
				treatmentsFrame.setLayout(new BorderLayout());
				treatmentsFrame.add(treatmentsPanel);

				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("ID");
				model.addColumn("Medicines");
				model.addColumn("Prescription");
				model.addColumn("Illness");

				List<Treatment> treatmentList;
				try {
					treatmentList = App.db.getTreatments(Integer.parseInt(txPatientIDViewTreatment.getText()));
					
					for(Treatment t : treatmentList){
						model.addRow(new Object[] { t.ID, t.medicines, t.prescription, t.illnessName });						
					}					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				JTable testResultsTable = new JTable(model);
				testResultsTable.setFillsViewportHeight(true);
				JScrollPane tableContainer = new JScrollPane(testResultsTable);
				tableContainer.setBounds(100, 100, 300, 600);
				treatmentsPanel.add(tableContainer);

				treatmentsFrame.setVisible(true);
			}
		});
		
		addRoutineControlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				JFrame addTreatmentFrame = new JFrame();
				JPanel addTreatmentPanel = new JPanel();
				
				addTreatmentFrame.setSize(700, 600);
				addTreatmentFrame.setLocation(100, 50);
				addTreatmentPanel.setLayout(null);
				
				JLabel nurseIDLabelAddTreatment = new JLabel("Nurse ID: ");				
				nurseIDLabelAddTreatment.setBounds(100, 50, 100, 50);
				addTreatmentPanel.add(nurseIDLabelAddTreatment);
				
				JLabel patientIDLabelAddTreatment = new JLabel("Patient ID: ");
				patientIDLabelAddTreatment.setBounds(100, 100, 300, 50);
				addTreatmentPanel.add(patientIDLabelAddTreatment);
				
				JLabel resultsLabelAddTreatment = new JLabel("Results: ");
				resultsLabelAddTreatment.setBounds(100, 150, 300, 50);
				addTreatmentPanel.add(resultsLabelAddTreatment);		
				
				txNurseIDAddRoutineControl.setBounds(250, 50, 300, 50);
				addTreatmentPanel.add(txNurseIDAddRoutineControl);				
				
				txPatientIDAddRoutineControl.setBounds(250, 100, 300, 50);
				addTreatmentPanel.add(txPatientIDAddRoutineControl);				
				
				txResultsAddRoutineControl.setBounds(250, 150, 300, 50);
				addTreatmentPanel.add(txResultsAddRoutineControl);				
				
				addRoutineControlToDatabaseButton.setBounds(200, 300, 300, 100);
				addTreatmentPanel.add(addRoutineControlToDatabaseButton);							

				addTreatmentFrame.add(addTreatmentPanel);
				addTreatmentFrame.setVisible(true);
				
			}
		});
		
		addRoutineControlToDatabaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				RoutineControl r = new RoutineControl(0, Integer.parseInt(txNurseIDAddRoutineControl.getText()), Integer.parseInt(txPatientIDAddRoutineControl.getText()), txResultsAddRoutineControl.getText());
				try {
					App.db.addNewRoutineControl(r);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		
		addTestResultButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				JFrame addTreatmentFrame = new JFrame();
				JPanel addTreatmentPanel = new JPanel();
				
				addTreatmentFrame.setSize(700, 600);
				addTreatmentFrame.setLocation(100, 50);
				addTreatmentPanel.setLayout(null);
				
				JLabel medicinesLabelAddTreatment = new JLabel("Test Name: ");				
				medicinesLabelAddTreatment.setBounds(100, 50, 100, 50);
				addTreatmentPanel.add(medicinesLabelAddTreatment);
				
				JLabel doctorIDLabelAddTreatment = new JLabel("Test Result: ");
				doctorIDLabelAddTreatment.setBounds(100, 100, 300, 50);
				addTreatmentPanel.add(doctorIDLabelAddTreatment);
				
				JLabel patientIDLabelAddTreatment = new JLabel("Patient ID: ");
				patientIDLabelAddTreatment.setBounds(100, 150, 300, 50);
				addTreatmentPanel.add(patientIDLabelAddTreatment);				
				
				txTestNameAddTestResult.setBounds(250, 50, 300, 50);
				addTreatmentPanel.add(txTestNameAddTestResult);				
				
				txTestResultAddTestResult.setBounds(250, 100, 300, 50);
				addTreatmentPanel.add(txTestResultAddTestResult);				
				
				txPatientIDAddTestResult.setBounds(250, 150, 300, 50);
				addTreatmentPanel.add(txPatientIDAddTestResult);				
				
				addTestResultToDatabaseButton.setBounds(200, 300, 300, 100);
				addTreatmentPanel.add(addTestResultToDatabaseButton);							

				addTreatmentFrame.add(addTreatmentPanel);
				addTreatmentFrame.setVisible(true);
				
			}
		});
		
		addTestResultToDatabaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				TestResult t = new TestResult(0, txTestNameAddTestResult.getText(), txTestResultAddTestResult.getText(), Integer.parseInt(txPatientIDAddTestResult.getText()));
				try {
					App.db.addNewTestResult(t);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		
		addTreatmentToDatabaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				Treatment t = new Treatment(0, txMedicinesAddTreatment.getText(), Integer.parseInt(txDoctorIDAddTreatment.getText()), Integer.parseInt(txPatientIDAddTreatment.getText()),txPrescriptionAddTreatment .getText(), txIllnessAddTreatment.getText(), Integer.parseInt(txCostAddTreatment.getText()));
				try {
					App.db.addNewTreatment(t);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		
		
		
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                try {
					App.db.killConnection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });


	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
