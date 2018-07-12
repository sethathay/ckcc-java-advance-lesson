package tab;

import javax.swing.*;

public class JTabbedPaneCls extends JFrame{
	
	public JTabbedPaneCls() {
		JTextArea jArea = new JTextArea(200,200);
		JPanel p1 = new JPanel();
		p1.add(jArea);
		
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		JTabbedPane jTab = new JTabbedPane();
		jTab.setBounds(50,50,200,200); 
		jTab.add("Main", p1);
		jTab.add("Visit", p2);
		jTab.addTab("Help", p3);
		
		add(jTab);
		setSize(400, 400);
		setLayout(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new JTabbedPaneCls();
	}
}
