package taxation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class TaxProgram implements ActionListener {
	
	JFrame f;
	JMenuBar menuBar;
	JMenu file,empMgt,taxRule,report,help;
	JMenuItem newFile, saveFile, exitFile;
	JMenuItem newEmp, listEmp;
	JMenuItem taxRule2018, taxCalculator;
	JMenuItem taxReport;
	JMenuItem welcome, helpContent, checkUpdate, about;
	
	JSplitPane jsp;
	JTree jTree;
	JPanel panelRight;
	JTabbedPane jTab;
	
	JTextField employeeID, firstName, lastName, email, dob;
	JTextField department, position, salary, benefit;
	JTextField childNum;
	JLabel minorLabel;
	JComboBox gender;
	JButton btnSave, btnClear;
	JRadioButton rYes, rNo;
	
	DefaultTableModel model, modelTax;
	JTable tbEmpList, tbEmpListTax;
	JTextField searchText;
	JLabel empNum, empNumTax;
	JComboBox searchField;
	
	EmployeeMgt empMgtObj;
	
	JTextField taxSalary, taxBenefit, taxChildren, taxExchange;
	JRadioButton taxYes, taxNo;
	JLabel childLabel;
	JButton btnTaxCalculate, btnTaxClear;
	JLabel taxRiel, taxUSD;
	JLabel netRiel, netUSD;
	
	JTextField exchangeRate;
	
	private void createMenuBar() {
		//Menu: File, Employee Mgt, Tax Rule, Report, Help
		menuBar = new JMenuBar();
		file = new JMenu("File");
		empMgt = new JMenu("Emplyee Mgt");
		taxRule = new JMenu("Tax Rule");
		report = new JMenu("Report");
		help = new JMenu("Help");
		//Menu Item: New, Save, Exit
		newFile = new JMenuItem("New");
		saveFile = new JMenuItem("Save");
		exitFile = new JMenuItem("Exit");
		exitFile.addActionListener(this);
		file.add(newFile); file.add(saveFile);
		file.add(new JSeparator());
		file.add(exitFile);
		//Menu Item: New, List
		newEmp = new JMenuItem("New Employee");
		listEmp = new JMenuItem("List Employee");
		newEmp.addActionListener(this);
		listEmp.addActionListener(this);
		empMgt.add(newEmp); empMgt.add(listEmp);
		//Menu Item: Tax Rule2018
		taxRule2018 = new JMenuItem("Tax Rule 2018");
		taxRule2018.addActionListener(this);
		taxCalculator = new JMenuItem("Tax Calculator");
		taxCalculator.addActionListener(this);
		taxRule.add(taxRule2018);
		taxRule.add(taxCalculator);
		//Menu Item: Tax Report
		taxReport = new JMenuItem("Employee Tax Report");
		taxReport.addActionListener(this);
		report.add(taxReport);
		//Menu Item: Welcome, Help Contents, Check for Updates, About Employee Mgt
		welcome = new JMenuItem("Welcome");
		helpContent = new JMenuItem("Help Contents");
		checkUpdate = new JMenuItem("Check for Updates");
		about = new JMenuItem("About Employee Mgt");
		help.add(welcome); help.add(helpContent);
		help.add(new JSeparator());
		help.add(checkUpdate); help.add(about);
		
		//Add Menu to Menu Bar
		menuBar.add(file);
		menuBar.add(empMgt);
		menuBar.add(report);
		menuBar.add(taxRule);
		menuBar.add(help);
	}
	
	private void createJSplitPane() {
		//Tree of Root
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		//Tree of Employee Mgt
		DefaultMutableTreeNode empMgt = new DefaultMutableTreeNode("Employee Mgt");
		DefaultMutableTreeNode newEmp = new DefaultMutableTreeNode("New Employee");
		DefaultMutableTreeNode listEmp = new DefaultMutableTreeNode("List Employee");
		empMgt.add(newEmp);
		empMgt.add(listEmp);
		//Tree of Report
		DefaultMutableTreeNode report = new DefaultMutableTreeNode("Report");
		DefaultMutableTreeNode empTaxReport = new DefaultMutableTreeNode("Employee Tax Report");
		report.add(empTaxReport);
		//Tree of Tax Rule
		DefaultMutableTreeNode taxRule = new DefaultMutableTreeNode("Tax Rule");
		DefaultMutableTreeNode taxRule2018 = new DefaultMutableTreeNode("Tax Rule 2018");
		DefaultMutableTreeNode taxCal = new DefaultMutableTreeNode("Tax Calculator");
		taxRule.add(taxRule2018);
		taxRule.add(taxCal);
		
		root.add(empMgt);
		root.add(report);
		root.add(taxRule);
	    jTree = new JTree(root);
	    jTree.setRowHeight(25);
	    jTree.setRootVisible(false);
	    //Add event mouse double click on JTree
	    jTree.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
	            int selRow = jTree.getRowForLocation(e.getX(), e.getY());
	            TreePath selPath = jTree.getPathForLocation(e.getX(), e.getY());
	            if(selRow != -1) {
	                if(e.getClickCount() == 2) {
	                	String path = selPath.getLastPathComponent().toString();
	                	if(path.equals("List Employee")) {
                			funcEmployeeList();
	                	}
	                	if(path.equals("Employee Tax Report")) {
	                		funcTaxReport();
	                	}
	                	if(path.equals("Tax Rule 2018")) {
	                		funcTaxRule2018();
	                	}
	                	if(path.equals("New Employee")) {
	                		funcNewEmployee();
	                	}
	                	if(path.equals("Tax Calculator")) {
	                		funcTaxCalculator();
	                	}
	                	if(path.equals("Employee Tax Report")) {
	                		funcTaxReport();
	                	}
	                }
	            }
	        }
		});
	    //Expand All Node of JTree
	    for (int i = 0; i < jTree.getRowCount(); i++) jTree.expandRow(i);
	    
	    jTab = new JTabbedPane();
	    
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jTree, jTab);
		jsp.setDividerLocation(170);
	}
	
	public TaxProgram() {
		f = new JFrame("Taxation Program v1.0");
		empMgtObj = new EmployeeMgt();
		//Call function to create menu bar
		createMenuBar();
		createJSplitPane();
		//Add menu bar to frame
		f.add(jsp);
		f.setJMenuBar(menuBar);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitFile) {
			int confirm = JOptionPane.showConfirmDialog(f, "Are you sure, you want to exit program?", "Message", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		if(e.getSource() == listEmp) {
			funcEmployeeList();
		}
		if(e.getSource() == taxReport) {
			funcTaxReport();
		}
		if(e.getSource() == taxRule2018) {
			funcTaxRule2018();
		}
		if(e.getSource() == newEmp) {
			funcNewEmployee();
		}
		if(e.getSource() == taxCalculator) {
			funcTaxCalculator();
		}
		if(e.getSource() == rYes) {
			if(rYes.isSelected()) {
				childNum.setVisible(true);
				minorLabel.setVisible(true);
			}
		}
		if(e.getSource() == rNo) {
			if(rNo.isSelected()) {
				childNum.setText("0");
				minorLabel.setVisible(false);
				childNum.setVisible(false);
			}
		}
		if(e.getSource() == btnSave) {
			funcSaveEmployee();
		}
		if(e.getSource() == btnClear) {
			funcClearEmployee();
		}
		if(e.getSource() == taxYes) {
			if(taxYes.isSelected()) {
				taxChildren.setVisible(true);
				childLabel.setVisible(true);
			}
		}
		if(e.getSource() == taxNo) {
			if(taxNo.isSelected()) {
				taxChildren.setText("0");
				childLabel.setVisible(false);
				taxChildren.setVisible(false);
			}
		}
		if(e.getSource() == btnTaxCalculate) {
			funcCalculateTax();
		}
		if(e.getSource() == btnTaxClear) {
			funcClearTax();
		}
		if(e.getSource() == exchangeRate) {
			funcDisplayReport();
		}
	}
	
	private void funcDisplayReport() {
		//Display count employee
		empNumTax.setText(countRecord());
		//Display employee tax report
		for (Employee eObj : empMgtObj.get()) {
			Object[] row = eObj.getEmployeeObject();
			ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(row));
			double exchangeR = Double.parseDouble(exchangeRate.getText());
			double taxOnSalaryR = eObj.calculateTax(exchangeR);
			double taxOnSalaryD = taxOnSalaryR / exchangeR;
			//Tax on salary in riels
			temp.add(String.format("%.2f", taxOnSalaryR));
			//Tax on salary in dollar
			temp.add(String.format("%.2f", taxOnSalaryD));
			//net salary in riels
			temp.add(String.format("%.2f", (eObj.getTotalSalary() * exchangeR) - taxOnSalaryR));
			//net salary in dollar
			temp.add(String.format("%.2f", eObj.getTotalSalary() - taxOnSalaryD));
			Object[] rowObj = temp.toArray();
			modelTax.addRow(rowObj);
		}
	}
	
	private void funcClearTax() {
		taxSalary.setText("");
		taxBenefit.setText("");
		taxExchange.setText("");
		taxNo.setSelected(true);
		taxChildren.setText("0");
		taxChildren.setVisible(false);
		childLabel.setVisible(false);
		taxRiel.setText("0.0 Riel");
		taxUSD.setText("0.0 USD");
		netRiel.setText("0.0 Riel");
		netUSD.setText("0.0 USD");
	}
	
	private void funcCalculateTax() {
		boolean hasSpouse = taxYes.isSelected()? true : false;
		double taxSal = Double.parseDouble(taxSalary.getText());
		double taxBe = Double.parseDouble(taxBenefit.getText());
		Employee newEmp = new Employee(
				"Unknown", "Unknow", "Unknow", true,
				"Unknow", "Unknow", "Unknow", "Unknow",
				taxSal, taxBe,
				hasSpouse, Integer.parseInt(taxChildren.getText())
				);
		double exchangeRate = Double.parseDouble(taxExchange.getText());
		double resultTaxRiel = newEmp.calculateTax(exchangeRate);
		taxRiel.setText(String.format("%.2f", resultTaxRiel) + " Riel");
		double taxDollar = resultTaxRiel/exchangeRate;
		taxUSD.setText(String.format("%.2f", taxDollar) + " USD");
		netRiel.setText(String.format("%.2f",(((taxSal + taxBe) * exchangeRate) - resultTaxRiel)) + " Riel");
		netUSD.setText(String.format("%.2f", ((taxSal + taxBe) - taxDollar)) + " USD");
	}
	
	private void funcClearEmployee() {
		employeeID.setText("");
		firstName.setText("");
		lastName.setText("");
		email.setText("");
		dob.setText("");
		department.setText("");
		position.setText("");
		salary.setText("");
		benefit.setText("");
		rNo.setSelected(true);
		childNum.setText("0");
		childNum.setVisible(false);
		minorLabel.setVisible(false);
	}
	
	private void funcSaveEmployee() {
		boolean isMale = gender.getSelectedItem().equals("Male") ? true : false;
		boolean hasSpouse = rYes.isSelected()? true : false;
		Employee newEmployee = new Employee(
				employeeID.getText(), firstName.getText(),lastName.getText(),
				isMale, email.getText(), dob.getText(), department.getText(),
				position.getText(), Double.parseDouble(salary.getText()),
				Double.parseDouble(benefit.getText()), hasSpouse, Integer.parseInt(childNum.getText())
				);
		empMgtObj.add(newEmployee);
		model.addRow(newEmployee.getEmployeeObject());
	}
	
	private void funcTaxCalculator() {
		if(jTab.indexOfTab("Tax Calculator") != -1) return;
		JPanel topPanel = new JPanel();
		//Top panel
		TitledBorder titleBorderLeft = BorderFactory.createTitledBorder("EMPLOYEE TAX CALCULATOR");
		titleBorderLeft.setTitleJustification(TitledBorder.CENTER);
		topPanel.setBorder(titleBorderLeft);
		//Block employee info
		JPanel empInfo = new JPanel(new GridLayout(5,2,15,15));
		empInfo.add(new JLabel("Salary($) : "));
		empInfo.add(taxSalary = new JTextField(15));
		empInfo.add(new JLabel("Benefit($) : "));
		empInfo.add(taxBenefit = new JTextField(15));
		empInfo.add(new JLabel("Exchange Rate (1USD) : "));
		empInfo.add(taxExchange = new JTextField(15));
		JPanel pHasFamily = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pHasFamily.add(taxYes = new JRadioButton("YES"));
		pHasFamily.add(taxNo = new JRadioButton("NO", true));
		ButtonGroup bg = new ButtonGroup();
		bg.add(taxYes);
		bg.add(taxNo);
		taxYes.addActionListener(this);
		taxNo.addActionListener(this);
		empInfo.add(new JLabel("Has Spouse? : "));
		empInfo.add(pHasFamily);
		empInfo.add(childLabel = new JLabel("Minor Children : "));
		empInfo.add(taxChildren = new JTextField(15));
		taxChildren.setText("0");
		taxChildren.setVisible(false);
		childLabel.setVisible(false);
		//Button Panel
		JPanel buttonInfo = new JPanel(new FlowLayout());
		buttonInfo.add(btnTaxCalculate = new JButton("Calculate"));
		btnTaxCalculate.addActionListener(this);
		buttonInfo.add(btnTaxClear = new JButton("Clear"));
		btnTaxClear.addActionListener(this);
		//Employee All Info
		JPanel empInfoAll = new JPanel(new BorderLayout(15,15));
		empInfoAll.add(empInfo, BorderLayout.NORTH);
		empInfoAll.add(new JSeparator(), BorderLayout.CENTER);
		empInfoAll.add(buttonInfo, BorderLayout.SOUTH);
		//Calculator Panel
		JPanel calPanel = new JPanel(new BorderLayout(15,15));
		JLabel taxInfo = new JLabel("Salary Tax Calculator");
		taxInfo.setForeground(Color.BLUE);
		calPanel.add(taxInfo, BorderLayout.NORTH);
		calPanel.add(new JSeparator(), BorderLayout.CENTER);
		calPanel.add(empInfoAll, BorderLayout.SOUTH);
		//Result Panel
		JPanel resultPanel = new JPanel(new GridLayout(4,2,15,15));
		resultPanel.add(new JLabel("Tax on Salary : "));
		resultPanel.add(taxRiel = new JLabel("0.0 Riel"));
		taxRiel.setForeground(Color.RED);
		resultPanel.add(new JLabel(""));
		resultPanel.add(taxUSD = new JLabel("0.0 USD"));
		taxUSD.setForeground(Color.RED);
		resultPanel.add(new JLabel("Net Salary : "));
		resultPanel.add(netRiel = new JLabel("0.0 Riel"));
		netRiel.setForeground(Color.RED);
		resultPanel.add(new JLabel(""));
		resultPanel.add(netUSD = new JLabel("0.0 USD"));
		netUSD.setForeground(Color.RED);
		//Final Panel
		JPanel finalPanel = new JPanel(new BorderLayout(15,15));
		finalPanel.add(calPanel, BorderLayout.NORTH);
		finalPanel.add(resultPanel, BorderLayout.CENTER);
		//Add to top panel
		topPanel.add(finalPanel);
		
		JPanel mainPanel = new JPanel(new BorderLayout(10,10));
		mainPanel.add(topPanel);
		jTab.addTab("Tax Calculator", mainPanel);
		jTab.setSelectedComponent(mainPanel);
	}
	
	private void funcTaxRule2018() {
		if(jTab.indexOfTab("Tax Rule 2018") != -1) return;
		ImageIcon icon1 = new ImageIcon("images/tax1.jpg");
		JLabel lb1 = new JLabel();
		lb1.setIcon(icon1);
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icon2 = new ImageIcon("images/tax2.jpg");
		JLabel lb2 = new JLabel();
		lb2.setIcon(icon2);
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icon3 = new ImageIcon("images/tax3.jpg");
		JLabel lb3= new JLabel();
		lb3.setIcon(icon3);
		lb3.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel taxRule2018Panel = new JPanel( new BorderLayout(10,10));
		taxRule2018Panel.add(lb1, BorderLayout.NORTH);
		taxRule2018Panel.add(lb2, BorderLayout.CENTER);
		taxRule2018Panel.add(lb3, BorderLayout.SOUTH);
		JScrollPane jsp = new JScrollPane(taxRule2018Panel);
		jTab.addTab("Tax Rule 2018", jsp);
		jTab.setSelectedComponent(jsp);
	}
	
	private void funcTaxReport() {
		if(jTab.indexOfTab("Employee Tax Report") != -1) return;
		JPanel empTaxReport = new JPanel(new BorderLayout(15,15));
		//Employee taxation panel
		TitledBorder titleBorderLeft = BorderFactory.createTitledBorder("Employee Tax Report");
		titleBorderLeft.setTitleJustification(TitledBorder.CENTER);
		empTaxReport.setBorder(titleBorderLeft);
		//Title Block
		JPanel conditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		conditionPanel.add(new JLabel("Exchange Rate (1USD) : ", SwingConstants.CENTER));
		conditionPanel.add(exchangeRate = new JTextField(20));
		exchangeRate.addActionListener(this);
		//All Condition Block
		JPanel allConditionPanel = new JPanel(new GridLayout(1,2,15,15));
		allConditionPanel.add(conditionPanel);
		allConditionPanel.add(empNumTax = new JLabel("# Employee : 0", SwingConstants.RIGHT));
		//All Condition Block - with title
		JPanel condPanel = new JPanel(new BorderLayout(15,15));
		JLabel condInfo = new JLabel("Employee Tax Report");
		condInfo.setForeground(Color.BLUE);
		condPanel.add(condInfo, BorderLayout.NORTH);
		condPanel.add(new JSeparator(), BorderLayout.CENTER);
		condPanel.add(allConditionPanel, BorderLayout.SOUTH);
		//All Condition Block - with title - with Table
		JPanel empListPanel = new JPanel(new BorderLayout(15,15));
		empListPanel.add(condPanel, BorderLayout.NORTH);
		modelTax = new DefaultTableModel();
		modelTax.addColumn("ID");
		modelTax.addColumn("First Name");
		modelTax.addColumn("Last Name");
		modelTax.addColumn("Gender");
		modelTax.addColumn("Email");
		modelTax.addColumn("Date of Birth");
		modelTax.addColumn("Department");
		modelTax.addColumn("Position");
		modelTax.addColumn("Salary($)");
		modelTax.addColumn("Benefit($)");
		modelTax.addColumn("Spouse?");
		modelTax.addColumn("Minor Children");
		modelTax.addColumn("Tax on Salary (Riel)");
		modelTax.addColumn("Tax on Salary ($)");
		modelTax.addColumn("Net Salary (Riel)");
		modelTax.addColumn("Net Salary ($)");
		
	    tbEmpListTax = new JTable(modelTax);
	    empListPanel.add(new JScrollPane(tbEmpListTax), BorderLayout.CENTER);
	    empTaxReport.add(empListPanel);
	    
		jTab.addTab("Employee Tax Report", empTaxReport);
		jTab.setSelectedComponent(empTaxReport);
	}
	
	private JPanel newEmployee() {
		JPanel empRight = new JPanel();
		//Right panel
		TitledBorder titleBorderRight = BorderFactory.createTitledBorder("CREATE A NEW EMPLOYEE");
		titleBorderRight.setTitleJustification(TitledBorder.CENTER);
		empRight.setBorder(titleBorderRight);
		//Block employee info
		JPanel empInfo = new JPanel(new GridLayout(6,2,15,15));
		empInfo.add(new JLabel("Employee ID : "));
		empInfo.add(employeeID = new JTextField(12));
		empInfo.add(new JLabel("First Name : "));
		empInfo.add(firstName = new JTextField(12));
		empInfo.add(new JLabel("Last Name : "));
		empInfo.add(lastName = new JTextField(12));
		empInfo.add(new JLabel("Gender : "));
		String[] genderText = {"Male", "Female"};
		empInfo.add(gender = new JComboBox(genderText));
		empInfo.add(new JLabel("Email : "));
		empInfo.add(email = new JTextField(12));
		empInfo.add(new JLabel("Date of Birth (dd/mm/yyyy) : "));
		empInfo.add(dob = new JTextField(12));
		//Block employee info - with title
		JPanel empInfoBlock = new JPanel(new BorderLayout(15,15));
		JLabel eInfo = new JLabel("Employee Information");
		eInfo.setForeground(Color.BLUE);
		empInfoBlock.add(eInfo, BorderLayout.NORTH);
		empInfoBlock.add(new JSeparator(), BorderLayout.CENTER);
		empInfoBlock.add(empInfo, BorderLayout.SOUTH);
		//Block company info
		JPanel comInfo = new JPanel(new GridLayout(4,2,15,15));
		comInfo.add(new JLabel("Department : "));
		comInfo.add(department = new JTextField(12));
		comInfo.add(new JLabel("Position : "));
		comInfo.add(position = new JTextField(12));
		comInfo.add(new JLabel("Salary($) : "));
		comInfo.add(salary = new JTextField(12));
		comInfo.add(new JLabel("Benefit($) : "));
		comInfo.add(benefit = new JTextField(12));
		//Block company info - with title
		JPanel comInfoBlock = new JPanel(new BorderLayout(15,15));
		JLabel cInfo = new JLabel("Company Information");
		cInfo.setForeground(Color.BLUE);
		comInfoBlock.add(cInfo, BorderLayout.NORTH);
		comInfoBlock.add(new JSeparator(), BorderLayout.CENTER);
		comInfoBlock.add(comInfo, BorderLayout.SOUTH);
		//Block family info
		JPanel pHasFamily = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pHasFamily.add(rYes = new JRadioButton("YES"));
		pHasFamily.add(rNo = new JRadioButton("NO", true));
		ButtonGroup bg = new ButtonGroup();
		bg.add(rYes);
		bg.add(rNo);
		rYes.addActionListener(this);
		rNo.addActionListener(this);
		JPanel familyInfo = new JPanel(new GridLayout(2,2,15,15));
		familyInfo.add(new JLabel("Has Spouse? : "));
		familyInfo.add(pHasFamily);
		familyInfo.add(minorLabel = new JLabel("Minor Children : "));
		familyInfo.add(childNum = new JTextField(12));
		childNum.setText("0");
		minorLabel.setVisible(false);
		childNum.setVisible(false);
		//Block family info - with title
		JPanel familyInfoBlock = new JPanel(new BorderLayout(15,15));
		JLabel fInfo = new JLabel("Family Information");
		fInfo.setForeground(Color.BLUE);
		familyInfoBlock.add(fInfo, BorderLayout.NORTH);
		familyInfoBlock.add(new JSeparator(), BorderLayout.CENTER);
		familyInfoBlock.add(familyInfo, BorderLayout.SOUTH);
		//Block Employee All Info
		JPanel allInfoBlock = new JPanel(new BorderLayout(15,15));
		allInfoBlock.add(empInfoBlock, BorderLayout.NORTH);
		allInfoBlock.add(comInfoBlock, BorderLayout.CENTER);
		allInfoBlock.add(familyInfoBlock, BorderLayout.SOUTH);
		//Block Operation Button
		JPanel buttonInfo = new JPanel(new FlowLayout());
		buttonInfo.add(btnSave = new JButton("Save"));
		btnSave.addActionListener(this);
		buttonInfo.add(btnClear = new JButton("Clear"));
		btnClear.addActionListener(this);
		//Block Operation Button - with separator
		JPanel buttonInfoBlock = new JPanel(new BorderLayout(15,15));
		buttonInfoBlock.add(new JSeparator(), BorderLayout.NORTH);
		buttonInfoBlock.add(buttonInfo, BorderLayout.CENTER);
		// Final Block
		JPanel finalBlock = new JPanel(new BorderLayout(15,15));
		finalBlock.add(allInfoBlock, BorderLayout.NORTH);
		finalBlock.add(buttonInfoBlock, BorderLayout.CENTER);
		//Add into right panel
		empRight.add(finalBlock);
		return empRight;
	}
	
	private JPanel listEmployee() {
		JPanel empLeft = new JPanel(new BorderLayout(10,10));
		//Left panel
		TitledBorder titleBorderLeft = BorderFactory.createTitledBorder("LIST OF EMPLOYEE");
		titleBorderLeft.setTitleJustification(TitledBorder.CENTER);
		empLeft.setBorder(titleBorderLeft);
		//Left Condition Block
		JPanel conditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		conditionPanel.add(new JLabel("Fields : ", SwingConstants.CENTER));
		String[] searchFieldText = {"ID", "First Name", "Last Name", "Email", "Department", "Position"};
		conditionPanel.add(searchField = new JComboBox(searchFieldText));
		conditionPanel.add(new JLabel("Search : ", SwingConstants.CENTER));
		conditionPanel.add(searchText = new JTextField(20));
		//All Condition Block
		JPanel allConditionPanel = new JPanel(new GridLayout(1,2,15,15));
		allConditionPanel.add(conditionPanel);
		allConditionPanel.add(empNum = new JLabel("# Employee : 0", SwingConstants.RIGHT));
		//All Condition Block - with title
		JPanel condPanel = new JPanel(new BorderLayout(15,15));
		JLabel condInfo = new JLabel("List of Your Employee");
		condInfo.setForeground(Color.BLUE);
		condPanel.add(condInfo, BorderLayout.NORTH);
		condPanel.add(new JSeparator(), BorderLayout.CENTER);
		condPanel.add(allConditionPanel, BorderLayout.SOUTH);
		//All Condition Block - with title - with Table
		JPanel empListPanel = new JPanel(new BorderLayout(15,15));
		empListPanel.add(condPanel, BorderLayout.NORTH);
		model = new DefaultTableModel();
		model.addColumn("ID");
	    model.addColumn("First Name");
	    model.addColumn("Last Name");
	    model.addColumn("Gender");
	    model.addColumn("Email");
	    model.addColumn("Date of Birth");
	    model.addColumn("Department");
	    model.addColumn("Position");
	    model.addColumn("Salary($)");
	    model.addColumn("Benefit($)");
	    model.addColumn("Spouse?");
	    model.addColumn("Minor Children");
	    tbEmpList = new JTable(model);
	    empListPanel.add(new JScrollPane(tbEmpList), BorderLayout.CENTER);
	    //Add into left panel
	    empLeft.add(empListPanel);
	    initializeEmployee();
	    empNum.setText(countRecord());
	    return empLeft;
	}
	
	private String countRecord() {
		return "# Employee : " + empMgtObj.count();
	}
	
	private void initializeEmployee() {
		//Initialize 5 Employees
		Employee emp1 = new Employee(
				"101", "Kimtey", "Chav", true,
				"kimtey.chav@gmail.com", "22/07/1988",
				"Development Dept", "Software Engineer",
				2500, 500, true, 1
				);
		Employee emp2 = new Employee(
				"102", "Setha", "Thay", true,
				"setha.thay@gmail.com", "03/01/1989",
				"Development Dept", "Mobile Developer",
				6500, 50, false, 0
				);
		Employee emp3 = new Employee(
				"103", "Vanndy", "Sun", true,
				"vanndy.sun@gmail.com", "28/09/1988",
				"Development Dept", "Quality Assurance",
				5500, 60, true, 1
				);
		Employee emp4 = new Employee(
				"104", "Tittheany", "An", false,
				"tittheany.an@gmail.com", "15/07/1989",
				"Development Dept", "Software Engineer",
				3500, 29, false, 0
				);
		Employee emp5 = new Employee(
				"105", "Inhae", "Park", false,
				"inhae.park@gmail.com", "10/02/1992",
				"Development Dept", "Software Engineer",
				2900, 80, false, 0
				);
		empMgtObj.add(emp1);
		empMgtObj.add(emp2);
		empMgtObj.add(emp3);
		empMgtObj.add(emp4);
		empMgtObj.add(emp5);
		model.addRow(emp1.getEmployeeObject());
		model.addRow(emp2.getEmployeeObject());
		model.addRow(emp3.getEmployeeObject());
		model.addRow(emp4.getEmployeeObject());
		model.addRow(emp5.getEmployeeObject());
	}
	
	private void funcEmployeeList() {
		if(jTab.indexOfTab("Employee List") != -1) return;
		JPanel empPanel = new JPanel(new BorderLayout(10,10));
	    JPanel empRight = newEmployee();
	    JPanel empLeft = listEmployee();
		empPanel.add(new JScrollPane(empLeft), BorderLayout.CENTER);
		empPanel.add(new JScrollPane(empRight), BorderLayout.EAST);
		
		jTab.addTab("Employee List", empPanel);
		jTab.setSelectedComponent(empPanel);
	}
	private void funcNewEmployee() {
		funcEmployeeList();
	}
	
	public static void main(String[] args) {
		new TaxProgram();
	}

}
