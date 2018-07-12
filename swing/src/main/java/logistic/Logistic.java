package logistic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Logistic extends JFrame implements ActionListener {
	
	JTextField txtWeight;
	JComboBox location;
	JRadioButton rYes, rNo;
	JButton btnInsert, btnSort;
	DefaultTableModel model;
	JTable tbList;
	
	ArrayList<Sending> list;
	
	public Logistic() {
		list = new ArrayList<Sending>();
		
		JPanel pExpress = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pExpress.add(rYes = new JRadioButton("YES"));
		pExpress.add(rNo = new JRadioButton("NO", true));
		ButtonGroup bg = new ButtonGroup();
		bg.add(rYes);
		bg.add(rNo);
		
		JPanel pGrid = new JPanel(new GridLayout(3,2,10,10));
		pGrid.add(new JLabel("Weight", SwingConstants.CENTER));
		pGrid.add(txtWeight = new JTextField());
		pGrid.add(new JLabel("Location", SwingConstants.CENTER));
		String[] locationText = {"A", "B", "C", "D"};
		pGrid.add(location = new JComboBox(locationText));
		pGrid.add(new JLabel("Express", SwingConstants.CENTER));
		pGrid.add(pExpress);
		
		JPanel pNorth = new JPanel(new BorderLayout(20,20));
		pNorth.add(new JLabel("LOGISTIC SERVICE", SwingConstants.CENTER), BorderLayout.NORTH);
		pNorth.add(pGrid, BorderLayout.CENTER);
		
		JPanel pButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pButton.add(btnInsert = new JButton("Insert"));
		pButton.add(btnSort = new JButton("Sort"));
		btnInsert.addActionListener(this);
		btnSort.addActionListener(this);
		
		JPanel pBottom = new JPanel(new BorderLayout(10,10));
		model = new DefaultTableModel();
		model.addColumn("Weight");
	    model.addColumn("Location");
	    model.addColumn("Express");
	    model.addColumn("Total Price");
	    tbList = new JTable(model);
		pBottom.add(new JScrollPane(tbList),BorderLayout.CENTER);
		
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.add(pNorth, BorderLayout.NORTH);
		panel.add(pButton, BorderLayout.CENTER);
		
		JPanel panelMain = new JPanel(new BorderLayout(10,10));
		panelMain.add(panel, BorderLayout.NORTH);
		panelMain.add(pBottom, BorderLayout.CENTER);
		//Set main panel to fixed position
		panelMain.setSize(500,500);
		
		add(panelMain);
		//Maximize window when start
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//Set layout of frame to null (default: border layout)
		setLayout(null);
		setTitle("Logistic Service");
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert) insertFunc();
		if(e.getSource() == btnSort) sortFunc();
	}
	
	private void insertFunc() {
		double weight = Double.parseDouble(txtWeight.getText());
		String loc = location.getSelectedItem().toString();
		boolean isExpress = rYes.isSelected()? true : false;
		
		Sending obj = new Sending(weight, loc, isExpress);
		list.add(obj);
		model.addRow(obj.getRow());
	}
	
	private void sortFunc() {
		model.setRowCount(0);
		Object[] ob = list.toArray();
		Arrays.sort(ob);
		for(Object iterator: ob) {
			Sending o = (Sending)iterator;
			model.addRow(o.getRow());
		}
	}
	
	public static void main(String[] args) {
		new Logistic();
	}
	
}
