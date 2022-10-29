package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Negozio.DataM;

public class Finestra extends Frame{
	JPanel c;
	static public WindowListener close=new WindowListener (){
	    public void windowClosing(WindowEvent we){
	        System.exit(0);
	    }
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowDeactivated(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowOpened(WindowEvent arg0) {}
	};
	public Finestra (String x){
		super(x);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.addWindowListener(close);
		setLocation(70,70);
		setPreferredSize(Est.standard);
		setBackground(Est.sfondo);
		Header testa=new Header();
		add(testa);
		c=new JPanel();
		c.setLayout(new BorderLayout(100,10));
		c.setBorder(Est.bordo);
		c.setBackground(Est.sfondo);
		add(c);
		
	}

}
