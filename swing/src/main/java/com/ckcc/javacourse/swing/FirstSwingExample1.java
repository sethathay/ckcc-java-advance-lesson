package com.ckcc.javacourse.swing;

import javax.swing.*;

public class FirstSwingExample1 {
	JFrame f;
	
	public FirstSwingExample1() {
		// Creating instance of JFrame
		f = new JFrame();
		
		//Creating instance of JButton
		JButton b = new JButton("Click");
		b.setBounds(130, 100, 100, 40); // x axis, y axis, width, height
		
		f.add(b); //adding button in JFrame
		
		f.setSize(400,500);
		f.setLayout(null);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new FirstSwingExample1();
		
	}

}
