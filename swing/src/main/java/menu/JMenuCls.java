package menu;

import javax.swing.*;

public class JMenuCls {
	
	JMenu menu, subMenu;
	JMenuItem item1, item2, item3, item4, item5;
	
	public JMenuCls() {
		JFrame f = new JFrame("Menu and MenuItem Example");
		
		JMenuBar menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		subMenu = new JMenu("Sub Menu");
		item1 = new JMenuItem("Item 1");
		item2 = new JMenuItem("Item 2");
		item3 = new JMenuItem("Item 3");
		item4 = new JMenuItem("Item 4");
		item5 = new JMenuItem("Item 5");
		
		menu.add(item1); menu.add(item2); menu.add(item3);
		subMenu.add(item4); subMenu.add(item5);
		menu.add(subMenu);
		menuBar.add(menu);
		
		f.setJMenuBar(menuBar);
		f.setSize(400,400);
		f.setLayout(null);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new JMenuCls();
	}
}
