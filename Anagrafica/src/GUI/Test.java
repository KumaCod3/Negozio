package GUI;

import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negozio.DataM;

import java.util.HashMap;

public class Test extends Frame{
	public Test(){
		super("HOME");
//		addWindowListener(Finestra.close);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLocation(70,70);
		setBackground(Est.sfondo);
		setPreferredSize(Est.standard);
		setUndecorated(true);
		
		Header inte=new Header();
		add(inte);
		
		JPanel bu1=new JPanel();
		bu1.setOpaque(false);
		bu1.setBorder(new EmptyBorder(10, 10, 10, 10));
		add("Center", bu1);
		
		MyChoice myChoice = new MyChoice(DataM.elenco);
		bu1.add(myChoice);
		
		 
		pack();
	}
}
