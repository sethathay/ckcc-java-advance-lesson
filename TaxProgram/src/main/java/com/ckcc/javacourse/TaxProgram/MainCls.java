package com.ckcc.javacourse.TaxProgram;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCls extends JFrame implements ActionListener {

	JMenuBar menuBar;
	JMenu mFile, mEmployee, mReport, mTax, mHelp;
	// For Menu File
	JMenuItem mItemNew, mItemSave, mItemExit;
	// For Menu Employee Mgt
	JMenuItem mItemNewEmp, mItemListEmp;
	// For Menu Report
	JMenuItem mItemTaxReport;
	// For Menu Tax;
	JMenuItem mItemTaxRule, mItemTaxCalculator;
	// For Menu Help;
	JMenuItem mItemWelcome, mItemHelpContents, mItemCheckUpdate, mItemAbout;
	
	// JTree Employee Mgt
	JTree jTreeEmp;
	JTabbedPane jTab;
	
	// Employee Information
	JTextField txtEmpID, txtFirstName, txtLastName, txtEmail, txtDOB;
	JComboBox cboGender;
	
	// Company Information
	JTextField txtDepartment, txtPosition, txtSalary, txtBenefit;
	
	// Family Information
	JRadioButton rbYes, rbNo;
	JTextField txtmMinorChild;
	
	// New Employee - Button
	JButton btnSave, btnClear;
	
	// Search Area
	JComboBox cboFields;
	JTextField txtSearch;
	
	// List
	JTable tbEmp;
	DefaultTableModel tbModel;
	
	//Tax Calculator
	JTextField txtCalSalary, txtCalBenefit, txtCalExchange, txtCalChildNumber;
	JRadioButton rbCalYes, rbCalNo;
	JButton btnCalculate, btnCalClear;
	JLabel resultTaxRiel, resultTaxUSD;
	JLabel resultNetRiel, resultNetUSD;
	
	//Report Tax
	JTextField reportExchangeRate;
	JTable tbEmpTaxReport;
	DefaultTableModel tbModelTaxReport;
	
	// Declare Employee Array List
	//ArrayList<Employee> listEmployee;
	
	public MainCls() {
		//listEmployee = new ArrayList<Employee>();
		//Create Object MenuItem of File
		mItemNew = new JMenuItem("New");
		mItemSave = new JMenuItem("Save");
		mItemExit = new JMenuItem("Exit");
		//Create object Menu File and add its items
		mFile = new JMenu("File");
		mFile.add(mItemNew);
		mFile.add(mItemSave);
		mFile.add(new JSeparator());
		mFile.add(mItemExit);
		//==========================END MENU FILE =====================/
		//Create Object Menu Item of Employee Mgt
		mItemNewEmp = new JMenuItem("New Employee");
		mItemNewEmp.addActionListener(this);
		mItemListEmp = new JMenuItem("List Employee");
		mItemListEmp.addActionListener(this);
		//Create Object Menu Employee Mgt and add its items
		mEmployee = new JMenu("Employee Mgt");
		mEmployee.add(mItemNewEmp);
		mEmployee.add(mItemListEmp);
		//==========================END MENU EMPLOYEE ==================/
		//Create Object Menu Item of Report
		mItemTaxReport = new JMenuItem("Employee Tax Report");
		mItemTaxReport.addActionListener(this);
		//Create Object Menu Report add add its items
		mReport = new JMenu("Reports");
		mReport.add(mItemTaxReport);
		//=========================END MENU REPORT ======================/
		//Create Object Menu item of Tax Rule
		mItemTaxRule = new JMenuItem("Tax Rule 2018");
		mItemTaxRule.addActionListener(this);
		mItemTaxCalculator = new JMenuItem("Tax Calculator");
		mItemTaxCalculator.addActionListener(this);
		//Create Object menu tax rule and add its items
		mTax = new JMenu("Tax Rule");
		mTax.add(mItemTaxRule);
		mTax.add(mItemTaxCalculator);
		//==========================END MENU TAX RULE===================/
		//Create Object Menu Item of Help
		mItemWelcome = new JMenuItem("Welcome");
		mItemHelpContents = new JMenuItem("Help Contents");		
		mItemCheckUpdate = new JMenuItem("Check Update");
		mItemAbout = new JMenuItem("About Employee Mgt");
		//Create Object Menu Help and add its items
		mHelp = new JMenu("Help");
		mHelp.add(mItemWelcome);
		mHelp.add(mItemHelpContents);
		mHelp.add(new JSeparator());
		mHelp.add(mItemCheckUpdate);
		mHelp.add(mItemAbout);
		//==========================END MENU HELP======================/
		//Create object menu bar and add its menus
		menuBar = new JMenuBar();
		menuBar.add(mFile); //Add File
		menuBar.add(mEmployee); //Add Employee Mgt
		menuBar.add(mReport); //Add Report
		menuBar.add(mTax); //Add Tax Rule
		menuBar.add(mHelp); // Add Help
		//=========================END MENU BAR======================/
		
		//JPanel leftPanel = new JPanel();
		// Add JTree into leftPanel
		JTree leftJTree = createJTree();
		//JPanel rightPanel = new JPanel();
		//rightPanel.add(new JLabel("RIGHT"));
		jTab = new JTabbedPane();
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, leftJTree, jTab);
		jsp.setDividerLocation(180);
		//Add Menu bar to Frame
		add(menuBar);
		add(jsp);
		setTitle("Tax Program v1.0");
		setJMenuBar(menuBar);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	private void performOpenTaxRule2018() {
		JLabel lbl1 = new JLabel("", SwingConstants.CENTER);
		ImageIcon icon1 = new ImageIcon("images/tax1.jpg");
		lbl1.setIcon(icon1);
		//==============End Set Icon 1=================
		JLabel lbl2 = new JLabel("", SwingConstants.CENTER);
		ImageIcon icon2 = new ImageIcon("images/tax2.jpg");
		lbl2.setIcon(icon2);
		//==============End Set Icon 2=================
		JLabel lbl3 = new JLabel("", SwingConstants.CENTER);
		ImageIcon icon3 = new ImageIcon("images/tax3.jpg");
		lbl3.setIcon(icon3);
		//==============End Set Icon 3=================
		JPanel jPanelIcon = new JPanel(new BorderLayout(10,10));
		jPanelIcon.add(lbl1, BorderLayout.NORTH);
		jPanelIcon.add(lbl2, BorderLayout.CENTER);
		jPanelIcon.add(lbl3, BorderLayout.SOUTH);
		// Add JScrollPane with Icon into JTab
		JScrollPane jspPictures = new JScrollPane(jPanelIcon);
		jTab.addTab("Tax Rule 2018", jspPictures);
		jTab.setSelectedComponent(jspPictures);
	}
	
	private JPanel performOpenEmployee_SectionEmpInfo() {
		// Create Block Employee Info
		JPanel blockEmpInfo = new JPanel(new GridLayout(6,2,10,10));
		blockEmpInfo.add(new JLabel("Emplyee ID :"));
		blockEmpInfo.add(txtEmpID = new JTextField());
		blockEmpInfo.add(new JLabel("First Name :"));
		blockEmpInfo.add(txtFirstName = new JTextField());
		blockEmpInfo.add(new JLabel("Last Name :"));
		blockEmpInfo.add(txtLastName = new JTextField());
		blockEmpInfo.add(new JLabel("Gender :"));
		String[] genderText = {"Male", "Female"};
		blockEmpInfo.add(cboGender = new JComboBox(genderText));
		blockEmpInfo.add(new JLabel("Email :"));
		blockEmpInfo.add(txtEmail = new JTextField());
		blockEmpInfo.add(new JLabel("Date of Birth (dd/mm/yyyy) :"));
		blockEmpInfo.add(txtDOB = new JTextField());
		// Create Block Employee Info - FINAL
		JPanel blockEmpInfo_FINAL = new JPanel(new BorderLayout(10,10));
		blockEmpInfo_FINAL.add(new JLabel("Employee Information"), BorderLayout.NORTH);
		blockEmpInfo_FINAL.add(new JSeparator(), BorderLayout.CENTER);
		blockEmpInfo_FINAL.add(blockEmpInfo, BorderLayout.SOUTH);
		return blockEmpInfo_FINAL;
	}
	
	private JPanel performOpenEmployee_SectionComInfo() {
		// Create Block Company Info
		JPanel blockComInfo = new JPanel(new GridLayout(4,2,10,10));
		blockComInfo.add(new JLabel("Department :"));
		blockComInfo.add(txtDepartment = new JTextField());
		blockComInfo.add(new JLabel("Position :"));
		blockComInfo.add(txtPosition = new JTextField());
		blockComInfo.add(new JLabel("Salary($) :"));
		blockComInfo.add(txtSalary = new JTextField());
		blockComInfo.add(new JLabel("Benefit :"));
		blockComInfo.add(txtBenefit = new JTextField());
		//Create Block Company Info - FINAL
		JPanel blockComInfo_FINAL = new JPanel(new BorderLayout(10,10));
		blockComInfo_FINAL.add(new JLabel("Company Information"), BorderLayout.NORTH);
		blockComInfo_FINAL.add(new JSeparator(), BorderLayout.CENTER);
		blockComInfo_FINAL.add(blockComInfo, BorderLayout.SOUTH);
		return blockComInfo_FINAL;
	}
	
	private JPanel performOpenEmployee_SectionFamInfo() {
		// Create Block Family Info
		JPanel blockFamInfo = new JPanel(new GridLayout(2,2,10,10));
		blockFamInfo.add(new JLabel("Has Spouse? :"));
		// Radio Button FlowLayout
		//=============================================
		JPanel rbPanel = new JPanel(new FlowLayout());
		rbPanel.add(rbYes = new JRadioButton("YES"));
		rbPanel.add(rbNo = new JRadioButton("NO"));
		ButtonGroup bgGroup = new ButtonGroup();
		bgGroup.add(rbYes);
		bgGroup.add(rbNo);
		//=============================================
		blockFamInfo.add(rbPanel);
		blockFamInfo.add(new JLabel("Minor Children :"));
		blockFamInfo.add(txtmMinorChild = new JTextField());
		//Create Block Family Info - FINAL
		JPanel blockFamInfo_FINAL = new JPanel(new BorderLayout(10,10));
		blockFamInfo_FINAL.add(new JLabel("Family Information"), BorderLayout.NORTH);
		blockFamInfo_FINAL.add(new JSeparator(), BorderLayout.CENTER);
		blockFamInfo_FINAL.add(blockFamInfo, BorderLayout.SOUTH);
		return blockFamInfo_FINAL;
	}
	
	private JPanel performOpenEmployee_NewEmployee(JPanel empNew) {
		// Create Group Box - New Employee
		TitledBorder tBorderNewEmp = BorderFactory.createTitledBorder("CREATE A NEW EMPLOYEE");
		tBorderNewEmp.setTitleJustification(TitledBorder.CENTER);
		empNew.setBorder(tBorderNewEmp);
		
		//Create Block All Info
		JPanel blockAllInfo = new JPanel(new BorderLayout(10,10));
		blockAllInfo.add(performOpenEmployee_SectionEmpInfo(), BorderLayout.NORTH);
		blockAllInfo.add(performOpenEmployee_SectionComInfo(), BorderLayout.CENTER);
		blockAllInfo.add(performOpenEmployee_SectionFamInfo(), BorderLayout.SOUTH);
		
		//Create Block All Info - FINAL
		JPanel blockAllInfo_FINAL = new JPanel(new BorderLayout(10,10));
		blockAllInfo_FINAL.add(blockAllInfo, BorderLayout.NORTH);
		blockAllInfo_FINAL.add(new JSeparator(), BorderLayout.CENTER);
		// Button FlowLayout
		//=============================================
		JPanel actionBtnPanel = new JPanel(new FlowLayout());
		actionBtnPanel.add(btnSave = new JButton("SAVE"));
		btnSave.addActionListener(this);
		actionBtnPanel.add(btnClear = new JButton("CLEAR"));
		//=============================================
		blockAllInfo_FINAL.add(actionBtnPanel, BorderLayout.SOUTH);
		return blockAllInfo_FINAL;
	}
	
	private JPanel performOpenEmployee_ListEmployee(JPanel empList) {
		// Create Group Box - New Employee
		TitledBorder tBorderListEmp = BorderFactory.createTitledBorder("LIST OF EMPLOYEES");
		tBorderListEmp.setTitleJustification(TitledBorder.CENTER);
		empList.setBorder(tBorderListEmp);
		
		// Create Search Area and Count Employee
		JPanel searchAndCountPanel = new JPanel(new GridLayout(1,2,10,10));
		// Create Search Area Panel
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.add(new JLabel("Fields:"));
		String[] searchFieldText = {"ID", "First Name", 
				"Last Name", "Email", "Department", "Position"};
		searchPanel.add(cboFields = new JComboBox(searchFieldText));
		searchPanel.add(new JLabel("Search"));
		searchPanel.add(txtSearch = new JTextField(12));
		// Add Search Area
		searchAndCountPanel.add(searchPanel);
		// Add Count Employee
		searchAndCountPanel.add(new JLabel("# Employee: 0", SwingConstants.RIGHT));
		
		// Table Employee
		tbModel = new DefaultTableModel();
		tbModel.addColumn("ID");
		tbModel.addColumn("First Name");
		tbModel.addColumn("Last Name");
		tbModel.addColumn("Gender");
		tbModel.addColumn("Email");
		tbModel.addColumn("Date of Birth");
		tbModel.addColumn("Department");
		tbModel.addColumn("Position");
		tbModel.addColumn("Salary($)");
		tbModel.addColumn("Benefit($)");
		tbModel.addColumn("Spouse?");
		tbModel.addColumn("Minor Children");
		tbEmp = new JTable(tbModel);
		/*String data[] = 
				{"105", "Inhae", "Park", "Male",
					"inhae.park@gmail.com", "10/02/1992",
					"Development Dept", "Software Engineer",
					"2900", "80", "N", "0"};*/
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			// start a transaction
			session.beginTransaction();
			String data[];
			// query students
			List<Employee> employeeList = session.createQuery("from Employee").getResultList();
			for(Employee emp: employeeList) {
				data = emp.toStringData();
				tbModel.addRow(data);
			}
			// commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
		
		//Create Block Employee List - FINAL
		JPanel blockEmpList_FINAL = new JPanel(new BorderLayout(10,10));
		blockEmpList_FINAL.add(new JLabel("List of Your Employees"), BorderLayout.NORTH);
		blockEmpList_FINAL.add(new JSeparator(), BorderLayout.CENTER);
		blockEmpList_FINAL.add(searchAndCountPanel, BorderLayout.SOUTH);
		
		//Create table list and search area
		JPanel searchAndListPanel = new JPanel(new BorderLayout(10,10));
		searchAndListPanel.add(blockEmpList_FINAL, BorderLayout.NORTH);
		searchAndListPanel.add(new JScrollPane(tbEmp), BorderLayout.CENTER);
		
		return searchAndListPanel;
	}
	
	private void performOpenTaxReport() {
		JPanel empTaxReport = new JPanel(new BorderLayout(10,10));
		//Label of employee taxation panel
		TitledBorder titleBorderLeft = BorderFactory.createTitledBorder("Employee Tax Report");
		titleBorderLeft.setTitleJustification(TitledBorder.CENTER);
		empTaxReport.setBorder(titleBorderLeft);
		
		//All Condition Block
		JPanel allConditionPanel = new JPanel(new GridLayout(1,2,10,10));
		//======================================================
		//Title Block
		JPanel conditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		conditionPanel.add(new JLabel("Exchange Rate (1USD) : ", SwingConstants.CENTER));
		conditionPanel.add(reportExchangeRate = new JTextField(20));
		reportExchangeRate.addActionListener(this);
		//======================================================
		allConditionPanel.add(conditionPanel);
		allConditionPanel.add(new JLabel("# Employee : 0", SwingConstants.RIGHT));
		
		//All Condition Block - with title
		JPanel condPanel = new JPanel(new BorderLayout(10,10));
		JLabel condInfo = new JLabel("Employee Tax Report");
		condInfo.setForeground(Color.BLUE);
		condPanel.add(condInfo, BorderLayout.NORTH);
		condPanel.add(new JSeparator(), BorderLayout.CENTER);
		condPanel.add(allConditionPanel, BorderLayout.SOUTH);
		
		//All Condition Block - with title - with Table
		JPanel empListPanel = new JPanel(new BorderLayout(15,15));
		empListPanel.add(condPanel, BorderLayout.NORTH);
		tbModelTaxReport = new DefaultTableModel();
		tbModelTaxReport.addColumn("ID");
		tbModelTaxReport.addColumn("First Name");
		tbModelTaxReport.addColumn("Last Name");
		tbModelTaxReport.addColumn("Gender");
		tbModelTaxReport.addColumn("Email");
		tbModelTaxReport.addColumn("Date of Birth");
		tbModelTaxReport.addColumn("Department");
		tbModelTaxReport.addColumn("Position");
		tbModelTaxReport.addColumn("Salary($)");
		tbModelTaxReport.addColumn("Benefit($)");
		tbModelTaxReport.addColumn("Spouse?");
		tbModelTaxReport.addColumn("Minor Children");
		tbModelTaxReport.addColumn("Tax on Salary (Riel)");
		tbModelTaxReport.addColumn("Tax on Salary ($)");
		tbModelTaxReport.addColumn("Net Salary (Riel)");
		tbModelTaxReport.addColumn("Net Salary ($)");
		
		tbEmpTaxReport = new JTable(tbModelTaxReport);
	    empListPanel.add(new JScrollPane(tbEmpTaxReport), BorderLayout.CENTER);
	    empTaxReport.add(empListPanel);
	    
		jTab.addTab("Employee Tax Report", empTaxReport);
		jTab.setSelectedComponent(empTaxReport);
	}
	
	private void performOpenTaxCalculator() {
		JPanel taxPanel = new JPanel();
		// Create Group Box - Tax Calculator
		TitledBorder tBorderTaxCal = BorderFactory.createTitledBorder("EMPLOYEE TAX CALCULATOR");
		tBorderTaxCal.setTitleJustification(TitledBorder.CENTER);
		taxPanel.setBorder(tBorderTaxCal);
		
		//Block Calculator - Employee Info
		JPanel empInfo = new JPanel(new GridLayout(5,2,10,10));
		empInfo.add(new JLabel("Salary($) : "));
		empInfo.add(txtCalSalary = new JTextField(15));
		empInfo.add(new JLabel("Benefit($) : "));
		empInfo.add(txtCalBenefit = new JTextField(15));
		empInfo.add(new JLabel("Exchange Rate (1USD) : "));
		empInfo.add(txtCalExchange = new JTextField(15));
		//=================================================
		JPanel hasFamilyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		hasFamilyPanel.add(rbCalYes = new JRadioButton("YES"));
		hasFamilyPanel.add(rbCalNo = new JRadioButton("NO", true));
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbCalYes);
		bg.add(rbCalNo);
		//=================================================
		empInfo.add(new JLabel("Has Spouse? : "));
		empInfo.add(hasFamilyPanel);
		empInfo.add(new JLabel("Minor Children : "));
		empInfo.add(txtCalChildNumber = new JTextField(15));
		
		//Create Block Calculator - Employee Info - FINAL
		JPanel empInfo_FINAL = new JPanel(new BorderLayout(10,10));
		empInfo_FINAL.add(new JLabel("Salary Tax Calculator"), BorderLayout.NORTH);
		empInfo_FINAL.add(new JSeparator(), BorderLayout.CENTER);
		empInfo_FINAL.add(empInfo, BorderLayout.SOUTH);
		
		//Create Panel Calculator Employee Info with Action Button
		JPanel empInfoButtonPanel = new JPanel(new BorderLayout(10,10));
		empInfoButtonPanel.add(empInfo_FINAL, BorderLayout.NORTH);
		empInfoButtonPanel.add(new JSeparator(), BorderLayout.CENTER);
		//=======================================================
		JPanel actionButtonPanel = new JPanel(new FlowLayout());
		actionButtonPanel.add(btnCalculate = new JButton("Calculate"));
		btnCalculate.addActionListener(this);
		actionButtonPanel.add(btnCalClear = new JButton("Clear"));
		//=======================================================
		empInfoButtonPanel.add(actionButtonPanel, BorderLayout.SOUTH);
		
		//Create Tax Calculator Result
		JPanel taxCalResultPanel = new JPanel(new GridLayout(4,2,10,10));
		taxCalResultPanel.add(new JLabel("Tax on Salary : ", SwingConstants.CENTER));
		taxCalResultPanel.add(resultTaxRiel = new JLabel("0.0 Riel", SwingConstants.CENTER));
		resultTaxRiel.setForeground(Color.RED);
		taxCalResultPanel.add(new JLabel(""));
		taxCalResultPanel.add(resultTaxUSD = new JLabel("0.0 USD", SwingConstants.CENTER));
		resultTaxUSD.setForeground(Color.RED);
		taxCalResultPanel.add(new JLabel("Net Salary : ", SwingConstants.CENTER));
		taxCalResultPanel.add(resultNetRiel = new JLabel("0.0 Riel", SwingConstants.CENTER));
		resultNetRiel.setForeground(Color.MAGENTA);
		taxCalResultPanel.add(new JLabel(""));
		taxCalResultPanel.add(resultNetUSD = new JLabel("0.0 USD", SwingConstants.CENTER));
		resultNetUSD.setForeground(Color.MAGENTA);
		
		//Panel Calculator Final
		JPanel calculatorFINAL = new JPanel(new BorderLayout(10,10));
		calculatorFINAL.add(empInfoButtonPanel, BorderLayout.NORTH);
		calculatorFINAL.add(taxCalResultPanel, BorderLayout.CENTER);
		
		taxPanel.add(calculatorFINAL);
		
		jTab.addTab("Tax Calculator", taxPanel);
		jTab.setSelectedComponent(taxPanel);
	}
	
	private void performOpenEmployee() {
		JPanel empPanel = new JPanel(new BorderLayout(10,10));
		JPanel empList = new JPanel(new BorderLayout(10,10));
		JPanel empNew = new JPanel(new BorderLayout(10,10));
		
		empNew.add(performOpenEmployee_NewEmployee(empNew), BorderLayout.NORTH);
		empList.add(performOpenEmployee_ListEmployee(empList));
		
		empPanel.add(new JScrollPane(empList), BorderLayout.CENTER);
		empPanel.add(new JScrollPane(empNew), BorderLayout.EAST);
		
		jTab.addTab("Employee List", empPanel);
		jTab.setSelectedComponent(empPanel);
	}
	
	private JTree createJTree() {
		//JPanel leftPanel = new JPanel();
		// Create Tree Root Node
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root Node");
		// Create Tree Node Employee Mgt
		DefaultMutableTreeNode nodeEmpMgt = new DefaultMutableTreeNode("Employee Mgt");
		// Create Tree Node Add Employee
		DefaultMutableTreeNode nodeAddEmp = new DefaultMutableTreeNode("Add Employee");
		// Create Tree Node List Employee
		DefaultMutableTreeNode nodeListEmp = new DefaultMutableTreeNode("List Employee");
		nodeEmpMgt.add(nodeAddEmp);
		nodeEmpMgt.add(nodeListEmp);
		//================================END TREE NODE of EMPLOYEE LIST=========//
		
		// Create Tree Node Report
		DefaultMutableTreeNode nodeReport = new DefaultMutableTreeNode("Report");
		// Create Tree Node Employee Tax Report
		DefaultMutableTreeNode nodeEmpTaxReport = new DefaultMutableTreeNode("Employee Tax Report");		
		nodeReport.add(nodeEmpTaxReport);
		//================================ END TREE NODE of REPORT =========//
		
		// Create Tree Node Tax Rule
		DefaultMutableTreeNode nodeTaxRule = new DefaultMutableTreeNode("Tax Rule");
		// Create Tree Node Tax Rule 2018
		DefaultMutableTreeNode nodeTaxRule2018 = new DefaultMutableTreeNode("Tax Rule 2018");
		// Create Tree Node Tax Calculator
		DefaultMutableTreeNode nodeTaxCalculator = new DefaultMutableTreeNode("Tax Calculator");
		nodeTaxRule.add(nodeTaxRule2018);
		nodeTaxRule.add(nodeTaxCalculator);
		//================================ END TREE NODE of TAX RULE =========//
		
		rootNode.add(nodeEmpMgt);
		rootNode.add(nodeReport);
		rootNode.add(nodeTaxRule);
		
		// Create object of JTree Employee Mgt
		jTreeEmp = new JTree(rootNode);
		jTreeEmp.setRowHeight(25);
		jTreeEmp.setRootVisible(false);
		jTreeEmp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		// Add mouse listener to tree
		jTreeEmp.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// Find selected node of tree
				int selectedNode = jTreeEmp.getRowForLocation(e.getX(), e.getY());
				// Condition when mouse pressed on a specific node
				if(selectedNode != -1) {
					// When mouse pressed is double clicked
					if(e.getClickCount() == 2) {
						//Get for whole tree path
						TreePath treePath = jTreeEmp.getPathForLocation(e.getX(), e.getY());
						//Get last selected tree path
						String lastSelectedPath = treePath.getLastPathComponent().toString();
						if(lastSelectedPath.equals("Add Employee")) {
							performOpenEmployee();
						}else if (lastSelectedPath.equals("List Employee")) {
							performOpenEmployee();
						}else if (lastSelectedPath.equals("Employee Tax Report")) {
							performOpenTaxReport();
						}else if (lastSelectedPath.equals("Tax Rule 2018")) {
							performOpenTaxRule2018();
						}else if (lastSelectedPath.equals("Tax Calculator")) {
							performOpenTaxCalculator();
						}
						
					}
				}
			}
		});
		// Expand all tree nodes
		for(int i =0; i<=jTreeEmp.getRowCount(); i++)
			jTreeEmp.expandRow(i);
		// Add tree to panel
		//leftPanel.add(jTreeEmp);
		return jTreeEmp;
	}
	
	public static void main(String[] args) {
		new MainCls();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mItemNewEmp) {
			performOpenEmployee();
		}else if (e.getSource() == mItemListEmp) {
			performOpenEmployee();
		}else if (e.getSource() == mItemTaxReport) {
			performOpenTaxReport();
		}else if(e.getSource() == mItemTaxRule) {
			performOpenTaxRule2018();
		}else if(e.getSource() == mItemTaxCalculator) {
			performOpenTaxCalculator();
		}
		
		// Action Button Save
		if(e.getSource() == btnSave) {
			// Step 1: create object of employee
			String empID = txtEmpID.getText();
			String firstName = txtFirstName.getText();
			String lastName = txtLastName.getText();
			boolean gender = cboGender.getSelectedItem().toString() == "Male"? 
							true : false;
			String email = txtEmail.getText();
			String dob = txtDOB.getText();
			String department = txtDepartment.getText();
			String position = txtPosition.getText();
			Double salary = Double.parseDouble(txtSalary.getText());
			Double benefit = Double.parseDouble(txtBenefit.getText());
			boolean hasSpouse = rbYes.isSelected();
			int minorChild = hasSpouse? Integer.parseInt(txtmMinorChild.getText()) : 0;
			
			Employee empObj = new Employee(
					empID, firstName, lastName, gender,
					email,dob,department,position,salary,
					benefit,hasSpouse,minorChild
					);
			// Step 2: add data into jTable for (employee list)
			tbModel.addRow(empObj.toStringData());
			// Add to database using hibernate
			// Create Factory of Session
			SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Employee.class)
									.buildSessionFactory();
			// Get session object from factory
			Session sessionObj = factory.getCurrentSession();
			try {
				// start transaction
				sessionObj.beginTransaction();
				// save the student
				sessionObj.save(empObj);
				// commit the transaction
				sessionObj.getTransaction().commit();
			}finally {
				factory.close();
			}
			// Step 3: add object into ArrayList for reporting
			//listEmployee.add(empObj);
		}
		if(e.getSource() == reportExchangeRate) {
			// create session factory
	 		SessionFactory factory = new Configuration()
	 				.configure("hibernate.cfg.xml")
	 				.addAnnotatedClass(Employee.class)
	 				.addAnnotatedClass(Student.class)
	 				.buildSessionFactory();
	 		// create session
	 		Session session = factory.getCurrentSession();
	 		try {
	 			// start a transaction
	 			session.beginTransaction();
	 			String data[];
	 			// query students
	 			List<Employee> employeeList = session.createQuery("from Employee").getResultList();
	 			for(Employee emp: employeeList) {
	 				data = emp.toStringData();
	 				ArrayList<String> temp = new ArrayList<String>(Arrays.asList(data));
	 				double exchangeRate = Double.parseDouble(reportExchangeRate.getText());
	 				double taxRiel = emp.calculateTax(exchangeRate);
	 				double taxUSD = taxRiel / exchangeRate;
	 				
	 				double netSalaryRiel = ((emp.getSalary() + emp.getBenefit()) * exchangeRate) - taxRiel;
	 				double netSalaryUSD = netSalaryRiel / exchangeRate;
	 				
	 				temp.add("Riel " + String.format("%.2f", taxRiel));
	 				temp.add("USD " + String.format("%.2f", taxUSD));
	 				temp.add("Riel " + String.format("%.2f", netSalaryRiel));
	 				temp.add("USD " + String.format("%.2f", netSalaryUSD));
	 				
	 				Object[] row = temp.toArray();
	 				tbModelTaxReport.addRow(row);
	 			}
	 			// commit transaction
	 			session.getTransaction().commit();
	 		}finally {
	 			factory.close();
	 		}
		}
		if(e.getSource() == btnCalculate) {
			String empID = "001";
			String firstName = "Unknown";
			String lastName = "Unknown";
			boolean gender = false;
			String email = "yahoo@yahoo.com";
			String dob = "";
			String department = "Unknown";
			String position = "CEO";
			Double salary = Double.parseDouble(txtCalSalary.getText());
			Double benefit = Double.parseDouble(txtCalBenefit.getText());
			boolean hasSpouse = rbCalYes.isSelected();
			int minorChild = hasSpouse? Integer.parseInt(txtCalChildNumber.getText()) : 0;
			//Exchange Rate
			double exchangeRate = Double.parseDouble(txtCalExchange.getText());
			
			Employee empObj = new Employee(
					empID, firstName, lastName, gender,
					email,dob,department,position,salary,
					benefit,hasSpouse,minorChild
					);
			
			double taxRiel = empObj.calculateTax(exchangeRate);
			double taxUSD = taxRiel / exchangeRate;
			
			double netSalaryRiel = ((salary + benefit) * exchangeRate) - taxRiel;
			double netSalaryUSD = netSalaryRiel / exchangeRate;
			
			resultTaxRiel.setText("Riel " + String.format("%.2f", taxRiel));
			resultTaxUSD.setText("USD " + String.format("%.2f", taxUSD));
			resultNetRiel.setText("Riel " + String.format("%.2f", netSalaryRiel));
			resultNetUSD.setText("USD " + String.format("%.2f", netSalaryUSD));
		}
	}
	
}
