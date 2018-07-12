package split;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class JSplitPaneCls {
	JFrame f;
	public JSplitPaneCls() {
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
	   
		JPanel panel = new JPanel();
		panel.add(new JLabel("Right Component of JSplitPane"));
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jTree,panel);
		
		f.add(jsp);
		f.setSize(400,400);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new JSplitPaneCls();
	}
}
