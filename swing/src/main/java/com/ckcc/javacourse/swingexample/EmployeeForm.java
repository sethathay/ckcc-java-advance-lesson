package com.ckcc.javacourse.swingexample;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class EmployeeForm extends JFrame implements ActionListener {
	
	JTextField txtID, txtName, txtSalary;
	JTable tb; 
	DefaultTableModel model;
	JButton btnInsert, btnExit;
	
	public EmployeeForm() {
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3,2,10,10));
		p1.add(new JLabel("ID", SwingConstants.CENTER));
		p1.add(txtID = new JTextField(12));
		p1.add(new JLabel("Name", SwingConstants.CENTER));
		p1.add(txtName = new JTextField(12));
		p1.add(new JLabel("Salary", SwingConstants.CENTER));
		p1.add(txtSalary = new JTextField(12));
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout(10,10));
		p2.add(new JLabel("Employee Form", SwingConstants.CENTER), BorderLayout.NORTH);
		p2.add(p1, BorderLayout.CENTER);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(btnInsert = new JButton("Insert"));
		p3.add(btnExit = new JButton("Exit"));
		
		JPanel p4 = new JPanel();
		p4.setLayout(new BorderLayout(10,10));
		p4.add(p3, BorderLayout.NORTH);
		model = new DefaultTableModel();
		model.addColumn("ID");
	    model.addColumn("Name");
	    model.addColumn("Salary");
		tb = new JTable(model);
		p4.add(new JScrollPane(tb),BorderLayout.CENTER);
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout(10,10));
		p.add(p2, BorderLayout.NORTH);
		p.add(p4, BorderLayout.CENTER);
		
		btnInsert.addActionListener(this);
		btnExit.addActionListener(this);
		
		add(p);
		setSize(350,400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnExit) {
			System.exit(0);
		}
		if(e.getSource() == btnInsert) {
			Object[] rowObj = new Object[] {
					txtID.getText(),
					txtName.getText(),
					txtSalary.getText()
			};
			model.addRow(rowObj);
			clearForm();
		}
	}
	
	private void clearForm() {
		txtID.setText("");
		txtName.setText("");
		txtSalary.setText("");
	}
	
	public static void main(String[] args) {
		new EmployeeForm();
	}

}
