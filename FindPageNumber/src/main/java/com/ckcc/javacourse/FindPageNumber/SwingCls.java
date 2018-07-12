package com.ckcc.javacourse.FindPageNumber;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class SwingCls extends JFrame implements ActionListener {

	private JTextField txtPaper, txtPageNum;
	private JButton btnFind;
	private JTextArea areaResult;
	
	public SwingCls() {
		//Layout - Grid for input data
		JPanel panelGrid = new JPanel(new GridLayout(2,2,15,15));
		panelGrid.add(new JLabel("Paper"));
		panelGrid.add(txtPaper = new JTextField(15));
		panelGrid.add(new JLabel("Page Number"));
		panelGrid.add(txtPageNum = new JTextField(15));
		
		//Layout - Border for input data with header
		JPanel titlePanel = new JPanel(new BorderLayout(15,15));
		titlePanel.add(new JLabel("FIND PAGE NUMBER", SwingConstants.CENTER), BorderLayout.NORTH);
		titlePanel.add(panelGrid, BorderLayout.CENTER);
		
		//Layout - Flow for button
		JPanel buttonFlow = new JPanel(new FlowLayout());
		buttonFlow.add(btnFind = new JButton("Find"));
		btnFind.addActionListener(this);
		
		//Layout - Border final panel
		JPanel panel = new JPanel(new BorderLayout(15,15));
		panel.add(titlePanel, BorderLayout.NORTH);
		panel.add(buttonFlow, BorderLayout.CENTER);
		panel.add(areaResult = new JTextArea(18,4), BorderLayout.SOUTH);
		
		add(panel);
		setSize(500,500);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		int inputPage = Integer.parseInt(txtPageNum.getText());
		if(e.getSource() == btnFind) {
			FindPage obj = new FindPage(
					Integer.parseInt(txtPaper.getText()),
					inputPage
					);
			
			areaResult.setText(inputPage + " / " + obj.findBackPageNumber() + " / " +
					obj.findNearPageNumber() + " / " + obj.findNearBackPageNumber() + " / "
					);
		}
	}
	
	public static void main(String[] args) {
		new SwingCls();
	}
	
}
