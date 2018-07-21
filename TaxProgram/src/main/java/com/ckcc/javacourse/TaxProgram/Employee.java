package com.ckcc.javacourse.TaxProgram;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_employee")
public class Employee {
	
	// Data Memember of Employee Info
	@Id
	@Column(name = "id")
	private String empID;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "gender")
	private boolean gender;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "dob")
	private String dob;
	// Data Member of Company Info
	@Column(name = "department")
	private String department;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "salary")
	private double salary;
	
	@Column(name = "benefit")
	private double benefit;
	// Data Member of Family Info
	
	@Column(name = "has_spouse")
	private boolean hasSpouse;
	
	@Column(name = "minor_children")
	private int minorChild;
	
	public Employee() {
		
	}
	
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
	
	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getBenefit() {
		return benefit;
	}

	public void setBenefit(double benefit) {
		this.benefit = benefit;
	}

	public boolean isHasSpouse() {
		return hasSpouse;
	}

	public void setHasSpouse(boolean hasSpouse) {
		this.hasSpouse = hasSpouse;
	}

	public int getMinorChild() {
		return minorChild;
	}

	public void setMinorChild(int minorChild) {
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
