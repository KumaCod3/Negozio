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
//			setPreferredSize(new Dimension(400, 500));
			
			Header inte=new Header();
			add(inte);

			JPanel contenuto=new JPanel();
			contenuto.setLayout(new GridLayout(3,1));
			contenuto.setMaximumSize(new Dimension(560, 560));
			contenuto.setAlignmentX(Panel.CENTER_ALIGNMENT);
			
			Bottone b1=new Bottone("See Persons DataBase");
			b1.but.setMinimumSize(Est.grosso);
			b1.but.setMaximumSize(Est.grosso);
			b1.but.setPreferredSize(Est.grosso);
			b1.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	setVisible(false);
				}
			});
	        contenuto.add(b1);
			
	        Bottone b2=new Bottone("See Products DataBase");
	        b2.but.setMinimumSize(Est.grosso);
	        b2.but.setMaximumSize(Est.grosso);
	        b2.but.setPreferredSize(Est.grosso);
			b2.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	ConsultaMerci consultaM=new ConsultaMerci();
			    	consultaM.setVisible(true);
			    	setVisible(false);
			    	dispose();
				}
			});
	        contenuto.add(b2);
			
	        Bottone b3=new Bottone("--QUICK PURCHASE--");
	        b3.but.setMinimumSize(Est.grosso);
	        b3.but.setMaximumSize(Est.grosso);
	        b3.but.setPreferredSize(Est.grosso);
	        b3.but.setBackground(Est.oran);
			b3.but.addActionListener(new ActionListener() {
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
			bott.setMaximumSize(new Dimension(480, 180));
						
			Bottone bes=new Bottone("-HISTORY-");
			bes.but.setBackground(Est.medio);
			bes.but.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Storico stor=new Storico();
	            	stor.setVisible(true);
			    	setVisible(false);
			    	dispose();
	        	}
			});
			bott.add(bes);
			
			Bottone bal=new Bottone("-BALANCE-");
			bal.but.setBackground(Est.medio);
			bal.but.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Balance bl=new Balance();
	            	bl.setVisible(true);
			    	setVisible(false);
			    	dispose();
	        	}
			});
			bott.add(bal);
			
			add(bott);
			
			Bottone bex=new Bottone("-EXIT-");
			bex.but.setBackground(Est.rosso);
			bex.but.setAlignmentX(Panel.CENTER_ALIGNMENT);
			bex.but.setMaximumSize(new Dimension(360, 360));
			bex.but.addActionListener(new ActionListener() {
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