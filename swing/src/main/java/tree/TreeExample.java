package tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeExample {

	JFrame f;
	
	public TreeExample() {
		f = new JFrame();
		DefaultMutableTreeNode style = new DefaultMutableTreeNode("Style");
		DefaultMutableTreeNode color = new DefaultMutableTreeNode("Color");
		DefaultMutableTreeNode font = new DefaultMutableTreeNode("Font");
		style.add(color);
		style.add(font);
		
		DefaultMutableTreeNode red=new DefaultMutableTreeNode("red");  
	    DefaultMutableTreeNode blue=new DefaultMutableTreeNode("blue");  
	    DefaultMutableTreeNode black=new DefaultMutableTreeNode("black");  
	    DefaultMutableTreeNode green=new DefaultMutableTreeNode("green");
	    color.add(red); color.add(blue); color.add(black); color.add(green);
	    
	    JTree jTree = new JTree(style);
	    f.add(jTree);
	    f.setSize(200, 200);
	    f.setVisible(true);
	}
	public static void main(String[] args) {
		new TreeExample();
	}
}
