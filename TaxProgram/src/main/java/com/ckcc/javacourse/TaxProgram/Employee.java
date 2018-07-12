package com.ckcc.javacourse.TaxProgram;

public class Employee {
	
	// Data Memember of Employee Info
	private String empID;
	private String firstName;
	private String lastName;
	private boolean gender;
	private String email;
	private String dob;
	// Data Member of Company Info
	private String department;
	private String position;
	private double salary;
	private double benefit;
	// Data Member of Family Info
	private boolean hasSpouse;
	private int minorChild;
	
	public Employee(String empID, String firstName, String lastName, boolean gender, String email, String dob,
			String department, String position, double salary, double benefit, boolean hasSpouse, int minorChild) {
		this.empID = empID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.dob = dob;
		this.department = department;
		this.position = position;
		this.salary = salary;
		this.benefit = benefit;
		this.hasSpouse = hasSpouse;
		this.minorChild = minorChild;
	}
	
	public String[] toStringData() {
		String genderStr = gender? "Male" : "Female";
		String hasSpouseStr = hasSpouse? "Yes" : "No";
		String data[] = 
			{empID, firstName, lastName, genderStr,
					email,dob,department,position,salary + "",
					benefit + "",hasSpouseStr,minorChild + ""};
		return data;
	}
	
	private double exchangeSalary(double exchangeRate) {
		return this.salary * exchangeRate;
	}
	
	private double moneyForFamily() {
		if(this.hasSpouse) {
			return 150000 * (this.minorChild + 1);
		}
		return 0;
	}
	
	private double getBiasMoney(double salaryForTax) {
		if(salaryForTax > 12500000) {
			return 1210000;
		}else if(salaryForTax > 8500000) {
			return 585000;
		}else if(salaryForTax > 2000000) {
			return 160000;
		}else if(salaryForTax > 1200000) {
			return 60000;
		}
		return 0;
	}
	
	private double getTaxRate(double salaryForTax) {
		if(salaryForTax > 12500000) {
			return 0.2;
		}else if(salaryForTax > 8500000) {
			return 0.15;
		}else if(salaryForTax > 2000000) {
			return 0.1;
		}else if(salaryForTax > 1200000) {
			return 0.05;
		}
		return 0;
	}
	
	private double taxBenefit(double exchangeRate) {
		return this.benefit * exchangeRate  * 0.2;
	}
	
	public double calculateTax(double exchangeRate) {
		// Exchange Salary to Riel
		double rielSalary = this.exchangeSalary(exchangeRate);
		// Tax Related to Family
		double moneyFamily = this.moneyForFamily();
		// Find Salary for Tax Calculate
		double salaryForTax = rielSalary - moneyFamily;
		// Get Percentage of Tax based on tax rule
		double taxRate = this.getTaxRate(salaryForTax);
		// Get Bias Money based on tax rule
		double biasMoney = this.getBiasMoney(salaryForTax);
		// Get Tax Benefit
		double taxBenefit = this.taxBenefit(exchangeRate);
		// Calculate Tax
		double finalTax = (salaryForTax * taxRate - biasMoney) + taxBenefit;
		// Return tax
		return finalTax;
	}

}
