package com.ckcc.javacourse.swingexample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Example1 extends JFrame{
	
	JTextField tfn, tfr;
	JButton bti, bte;
	
	public Example1() {
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,2,10,10));
		
		p.add(new JLabel("Name"));
		tfn = new JTextField(12);
		p.add(tfn);
		p.add(bti = new JButton("Insert"));
		p.add(bte = new JButton("Exit"));
		p.add(new JLabel("Result"));
		p.add(tfr = new JTextField(12));
		
		add(p);
		
		bti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfr.setText(tfn.getText());
			}
		});
		
		bte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		setSize(300,300);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Example1();
	}

}
