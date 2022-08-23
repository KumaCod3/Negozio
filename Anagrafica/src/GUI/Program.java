package GUI;
import Negozio.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Program{
	public static void main(String[] args) {
		MyReadM.carica();
		MyReadF.carica();
		MyReadC.carica();
		Home prova=new Home();
		prova.setVisible(true);
	}
	
	public static class Home extends Frame{
		public Home(){
			super("HOME");
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			setLocation(70,70);
			setBackground(Est.chiaro);
			setPreferredSize(new Dimension(400, 500));
			
			Header inte=new Header();
			add(inte);

			JPanel contenuto=new JPanel();
			contenuto.setLayout(new GridLayout(3,1));
			contenuto.setMaximumSize(new Dimension(360, 360));
			contenuto.setAlignmentX(Panel.CENTER_ALIGNMENT);
			
			Pulsante b1=new Pulsante("See Persons DataBase");
			b1.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	setVisible(false);
				}
			});
	        contenuto.add(b1);
			
	        Pulsante b2=new Pulsante("See Products DataBase");
			b2.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	ConsultaMerci consultaM=new ConsultaMerci();
			    	consultaM.setVisible(true);
			    	setVisible(false);
			    	dispose();
				}
			});
	        contenuto.add(b2);
			
	        Pulsante b3=new Pulsante("--QUICK PURCHASE--");
	        b3.setBackground(Est.oran);
			b3.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	Spesa aggg=new Spesa(new Guest());
					aggg.setVisible(true);
			    	setVisible(false);
			    	dispose();
				}
			});
	        contenuto.add(b3);
			
			add(contenuto);
			
			
			JPanel bott=new JPanel();
			bott.setLayout(new GridLayout(1,2));
			bott.setMaximumSize(new Dimension(360, 180));
						
			Pulsante bes=new Pulsante("-HISTORY-");
			bes.setBackground(Est.medio);
			bes.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Storico stor=new Storico();
	            	stor.setVisible(true);
			    	setVisible(false);
			    	dispose();
	        	}
			});
			bott.add(bes);
			
			Pulsante bal=new Pulsante("-BALANCE-");
			bal.setBackground(Est.medio);
			bal.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Balance bl=new Balance();
	            	bl.setVisible(true);
			    	setVisible(false);
			    	dispose();
	        	}
			});
			bott.add(bal);
			
			add(bott);
			
			Pulsante bex=new Pulsante("-EXIT-");
			bex.setBackground(Est.rosso);
			bex.setAlignmentX(Panel.CENTER_ALIGNMENT);
			bex.setMaximumSize(new Dimension(360, 360));
			bex.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	MyReadM.scarica();
	    			MyReadF.scarica();
	    			MyReadC.scarica();
	            	setVisible(false);
	            	dispose();
	            	System.exit(0);
	        	}
			});
	        add(bex);
	        
			pack();
		}
	}
}