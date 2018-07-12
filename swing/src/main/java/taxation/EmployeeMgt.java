package taxation;

import java.util.ArrayList;

public class EmployeeMgt {
	
	private ArrayList<Employee> list;
	
	public EmployeeMgt() {
		this.list = new ArrayList<Employee>();
	}

	public EmployeeMgt(ArrayList<Employee> list) {
		this.list = list;
	}
	
	public void add(Employee emp) {
		this.list.add(emp);
	}
	
	public ArrayList<Employee> get() {
		return this.list;
	}
	
	public int count() {
		return this.list.size();
	}

}
