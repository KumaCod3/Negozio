package GUI;
import Negozio.*;
//import java.util.*;
import java.awt.*;
import Negozio.*;
import javax.swing.*;

import GUI.Program.Home;

//import Negozio.Ogg;
import java.awt.event.*;

public class ConsultaMerci extends Finestra {
		boolean tipp=true;
		int index=-1;
		public ConsultaMerci(){
			super("CONSULT Products database",690, 450);

			JPanel contenuto=new JPanel();
			contenuto.setBorder(Est.bordo);
			contenuto.setOpaque(false);
			contenuto.setLayout(new GridLayout(2,3));
			

	/*comp1*/  Etichetta tx=new Etichetta("Products: ");
			contenuto.add(tx);
			
	/*comp2*/Choice ele=new Choice();
			ele.add("Choose");
			try{
				for (Merce a:DataM.elenco.values()){
					ele.add(a.getNome()+" "+a.getCod());
				}
			}
			catch (Exception e){
				ele.add("Empty");
			}
			ele.setFont(Est.font);
			ele.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e){
				}
				public void focusLost(FocusEvent e){
					if (ele.getSelectedItem().equals("Choose")||ele.getSelectedItem().equals("Empty")){
					}
					else {
						String temp=ele.getSelectedItem();
						String[] temAr=temp.split(" ");
						index=Integer.parseInt(temAr[temAr.length-1]);
					}
				}
			});
			contenuto.add(ele);

/*comp3*/Bottone bent=new Bottone("-ENTER-");
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

/*comp4*/Bottone bex=new Bottone("-EXIT-");
			bex.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	setVisible(false);
			    	Home home=new Home();
					home.setVisible(true);
			    	dispose();
				}
			});
			contenuto.add(bex);
			
/*comp5*/	Etichetta tv=new Etichetta("           ");
			contenuto.add(tv);
			
/*comp6*/Bottone b2=new Bottone("+ ADD new +");
			b2.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	AggiungiMerce aggg=new AggiungiMerce();
			    	aggg.setVisible(true);
			    	dispose();
				}
			});
	        contenuto.add(b2);
	        
	        
			c.add("Center",contenuto);
			pack();
		}
		public void sett(boolean a){
			this.tipp=a;
		}
	}