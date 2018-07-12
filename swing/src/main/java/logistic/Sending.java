package logistic;

public class Sending implements Comparable <Sending> {
	
	private double weight;
	private String location;
	private boolean express;
	
	public Sending(double weight, String location, boolean express) {
		this.weight = weight;
		this.location = location;
		this.express = express;
	}

	public double initPrice() {
		if (location.equalsIgnoreCase("A")) return 55.0/75.0;
		if (location.equalsIgnoreCase("B")) return 75.0/50.0;
		if (location.equalsIgnoreCase("C")) return 27.0/30.0;
		if (location.equalsIgnoreCase("D")) return 50.0/80.0;
		return 0;
	}
	
	public double getPay() {
		double p = weight * initPrice();
		if(express == true) p = p + (p * 0.15);
		return p;
	}
	
	public Object[] getRow() {
		String exp = express? "YES" : "NO";
		return new Object[] {weight, location, exp, getPay()};
	}

	public int compareTo(Sending o) {
		if(getPay() > o.getPay()) return 1;
		if(getPay() < o.getPay()) return -1;
		return 0;
	}

}
