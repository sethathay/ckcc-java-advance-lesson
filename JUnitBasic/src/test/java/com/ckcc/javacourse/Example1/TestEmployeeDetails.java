package com.ckcc.javacourse.Example1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestEmployeeDetails {
	static EmpBusinessLogic empBizLogic;
	static EmployeeDetails employee;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empBizLogic = new EmpBusinessLogic();
		employee = new EmployeeDetails();
		employee.setName("Rajeev");
		employee.setAge(25);
	}

	@Test
	public void testCalculateYearlySalary() {
		employee.setMonthlySalary(8000);
		
		double salary = empBizLogic.calculateYearlySalary(employee);
		assertEquals(96000, salary, 0.0);
	}

	@Test
	public void testCalculateAppraisal_SalarySmallerThan10k() {
		employee.setMonthlySalary(8000);
		
		double appraisal = empBizLogic.calculateAppraisal(employee);
		assertEquals(500, appraisal, 0.0);
	}
	
	@Ignore
	public void testCalculateAppraisal_SalaryBiggerOrEqual10k() {
		employee.setMonthlySalary(12000);
		double appraisal = empBizLogic.calculateAppraisal(employee);
		assertEquals(1000, appraisal, 0.0);
	}

}
