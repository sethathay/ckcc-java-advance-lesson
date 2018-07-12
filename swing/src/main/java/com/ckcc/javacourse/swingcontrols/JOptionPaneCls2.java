package com.ckcc.javacourse.swingcontrols;

import javax.swing.*;

public class JOptionPaneCls2 {
	
	JFrame f;
	
	public JOptionPaneCls2() {
		
		f = new JFrame();
		String name = JOptionPane.showInputDialog(f, "Enter Name");
		
	}
	
	public static void main(String[] args) {
		new JOptionPaneCls2();
	}

}
