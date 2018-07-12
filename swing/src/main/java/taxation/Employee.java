package taxation;

public class Employee {
	
	private String employeeId;
	private String firstName;
	private String lastName;
	private boolean gender;
	private String email;
	private String dob;
	private String department;
	private String position;
	private double salary;
	private double benefit;
	private boolean hasSpouse;
	private int minorChild;
	
	private final double TAX_RATE_L0 = 0;
	private final double TAX_RATE_L1 = 0.05;
	private final double TAX_RATE_L2 = 0.1;
	private final double TAX_RATE_L3 = 0.15;
	private final double TAX_RATE_L4 = 0.2;
	
	private final double MARGIN_RATE_L0 = 0;
	private final double MARGIN_RATE_L1 = 60000;
	private final double MARGIN_RATE_L2 = 160000;
	private final double MARGIN_RATE_L3 = 585000;
	private final double MARGIN_RATE_L4 = 1210000;
	
	private final double SALARY_RATE_1 = 1200000;
	private final double SALARY_RATE_2 = 2000000;
	private final double SALARY_RATE_3 = 8500000;
	private final double SALARY_RATE_4 = 12500000;
	
	private final double FAMILY_SUPPORT = 150000;
	
	private final double TAX_RATE_BENEFIT = 0.2;
	
	public Employee(String employeeId, String firstName, String lastName, boolean gender, String email, String dob,
			String department, String position, double salary, double benefit, boolean hasSpouse, int minorChild) {
		this.employeeId = employeeId;
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
	
	public Object[] getEmployeeObject() {
		String genderText = gender? "Male" : "Female";
		String spouseText = hasSpouse? "Yes" : "No";
		return new Object[] {employeeId, firstName, lastName,
				genderText, email, dob, department, position, 
				salary, benefit, spouseText, minorChild		
		};
	}
	
	public double getTotalSalary() {
		return salary + benefit;
	}
	
	private double getSalaryInRiels(double exchangeRate) {
		return salary * exchangeRate;
	}
	
	private double getTaxRate(double salaryRiels) {
		if(salaryRiels <= this.SALARY_RATE_1) {
			return this.TAX_RATE_L0;
		}else if(salaryRiels <= this.SALARY_RATE_2) {
			return this.TAX_RATE_L1;
		}else if(salaryRiels <= this.SALARY_RATE_3) {
			return this.TAX_RATE_L2;
		}else if(salaryRiels <= this.SALARY_RATE_4) {
			return this.TAX_RATE_L3;
		}else {
			return this.TAX_RATE_L4;
		}
	}
	
	private double getMarginRate(double salaryRiels) {
		if(salaryRiels <= this.SALARY_RATE_1) {
			return this.MARGIN_RATE_L0;
		}else if(salaryRiels <= this.SALARY_RATE_2) {
			return this.MARGIN_RATE_L1;
		}else if(salaryRiels <= this.SALARY_RATE_3) {
			return this.MARGIN_RATE_L2;
		}else if(salaryRiels <= this.SALARY_RATE_4) {
			return this.MARGIN_RATE_L3;
		}else {
			return this.MARGIN_RATE_L4;
		}
	}
	
	private double getFamilySupportRiels() {
		if(hasSpouse) {
			//Plus 1 : Because of Spouse + Number of Child
			return (minorChild + 1) * this.FAMILY_SUPPORT;
		}
		return 0.0;
	}
	
	private double getBenefitTax(double exchangeRate) {
		return benefit * exchangeRate * this.TAX_RATE_BENEFIT;
	}
	
	public double calculateTax(double exchangeRate) {
		double salaryRiels = this.getSalaryInRiels(exchangeRate);
		double familySupportRiels = this.getFamilySupportRiels();
		double salaryForTax = salaryRiels - familySupportRiels;
		double totalTax = ((salaryForTax * this.getTaxRate(salaryForTax)) - this.getMarginRate(salaryForTax)) + this.getBenefitTax(exchangeRate);
		return totalTax;
	}
}
