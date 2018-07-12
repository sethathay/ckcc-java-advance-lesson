package com.ckcc.javacourse.Example1;

public class EmpBusinessLogic {
	
	//Calculate the yearly salary of employee
	public double calculateYearlySalary(EmployeeDetails employeeDetails) {
		/*double yearlySalary = 0;
		yearlySalary = employeeDetails.getMonthlySalary() * 12;
		return yearlySalary;
		*/
		return employeeDetails.getMonthlySalary() * 12;
	}
	
	//Calculate the appraisal amount of employee
	public double calculateAppraisal(EmployeeDetails emplyeeDetails) {
		/*double appraisal = 0;
		
		if(emplyeeDetails.getMonthlySalary() < 10000) {
			appraisal = 500;
		} else {
			appraisal = 1000;
		}
		return appraisal;
		*/
		return emplyeeDetails.getMonthlySalary() < 10000? 500 : 1000;
	}

}
