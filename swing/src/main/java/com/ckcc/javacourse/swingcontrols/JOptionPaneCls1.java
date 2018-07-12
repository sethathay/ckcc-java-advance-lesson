package com.ckcc.javacourse.swingcontrols;

import javax.swing.*;

public class JOptionPaneCls1 {

	private JFrame f;
	
	public JOptionPaneCls1() {
		f = new JFrame();
		//JOptionPane.showMessageDialog(f, "Hello, Welcome to Java Course");
		JOptionPane.showMessageDialog(f, "Becareful to delete this message!!","Alert", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void main(String[] args) {
		new JOptionPaneCls1();
	}
	
}
