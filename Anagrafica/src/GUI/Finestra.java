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
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Negozio.DataM;

public class Finestra extends Frame{
	Panel c;
	public Finestra (String x){
		super(x);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setLocation(70,70);
		setPreferredSize(new Dimension(650, 900));
		setBackground(Est.chiaro);
		Header testa=new Header();
		add(testa);
		c=new Panel();
		c.setLayout(new BorderLayout(100,10));
		add(c);
		
	}
}
