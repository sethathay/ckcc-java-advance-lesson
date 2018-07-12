package com.ckcc.javacourse.FindPageNumber;

public class FindPage {
	
	private int numberOfPaper;
	private int pickupPage;
	
	public FindPage(int numberOfPaper, int pickupPage) {
		this.numberOfPaper = numberOfPaper;
		this.pickupPage = pickupPage;
	}
	
	public int findNearPageNumber() {
		//Study Odd/Even of pickupPage
		//Case: pickupPage is even
		if(this.pickupPage % 2 == 0 ) {
			return pickupPage -1;
		}
		//Case: pickupPage is odd
		return pickupPage + 1;
	}
	
	public int findBackPageNumber() {
		//Case: pickupPage is even
		if(this.pickupPage %2 == 0) {
			return (numberOfPaper * 4) - findNearPageNumber();
		}
		//Case: pickupPage is odd
		return (numberOfPaper * 4) - this.pickupPage;
	}
	
	public int findNearBackPageNumber() {
		return findBackPageNumber() + 1;
	}

}
