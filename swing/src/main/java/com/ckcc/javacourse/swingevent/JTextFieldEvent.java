package com.ckcc.javacourse.swingevent;

import javax.swing.*;
import java.awt.event.*;

public class JTextFieldEvent {
	
	public static void main(String[] args) {
		JFrame f = new JFrame("TextField Example");
		final JTextField t1;
		final JTextField t2;
		t1 = new JTextField("");
		t1.setBounds(50,100,200,30);
		
		t2 = new JTextField("");
		t2.setBounds(50,150,200,30);
		
		t1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t2.setText(t1.getText());
			}
		});
		f.add(t1);
		f.add(t2);
		f.setSize(400, 400);
		f.setLayout(null);
		f.setVisible(true);
	}


}
