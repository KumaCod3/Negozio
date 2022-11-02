package GUI;
import Negozio.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;

public class ConsultaMerci extends Finestra {
		boolean tipp=true;
		int index=-1;
		public ConsultaMerci(){
			super("CONSULT Products database"/*,690, 450*/);

			JPanel contenuto=new JPanel();
			contenuto.setBorder(Est.bordo);
			contenuto.setOpaque(false);
			contenuto.setLayout(new GridLayout(3,1));
			
			JPanel pan1=new JPanel();
			pan1.setLayout(new GridLayout(1,2));
			pan1.setBorder(Est.bordo);
			pan1.setOpaque(false);
			JPanel pan2=new JPanel();
			pan2.setLayout(new GridLayout(1,2));
			pan2.setBorder(Est.bordo);
			pan2.setOpaque(false);

	/*comp1*/  Etichetta tx=new Etichetta("Products: ");
  tx.setVerticalAlignment(SwingConstants.TOP);
			pan1.add(tx);
			
	/*comp2*/MyChoice ele=new MyChoice(DataM.elenco);
			ele.jList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					try {
						String temp=ele.getSel();
						String[] temAr=temp.split(" ");
						index=Integer.parseInt(temAr[0]);
					}
					catch (Exception ex){
						// no selection
					}
					
				}
			});
			
			
			pan1.add(ele);
			contenuto.add(pan1);
			
/*comp3*/Bottone bent=new Bottone("ENTER", 5);
			bent.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	if (index!=-1){
				    	SchedaMerce aggg=new SchedaMerce(index);
				    	aggg.setVisible(true);
				    	dispose();
			    	}
				}
			});
			contenuto.add(bent);

/*comp4*/Bottone bex=new Bottone("EXIT");
			bex.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	Home home=new Home();
					home.setVisible(true);
					setVisible(false);
			    	dispose();
				}
			});
			pan2.add(bex);
			
/*comp6*/Bottone b2=new Bottone("ADD new");
			b2.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	AggiungiMerce aggg=new AggiungiMerce(-1);
			    	aggg.setVisible(true);
			    	dispose();
				}
			});
	        pan2.add(b2);
	        contenuto.add(pan2);
	        
			c.add("Center",contenuto);
			pack();
		}
		public void sett(boolean a){
			this.tipp=a;
		}
	}