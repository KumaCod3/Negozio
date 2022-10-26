package GUI;
import Negozio.*;
//import java.util.*;
import java.awt.*;
import Negozio.*;
import javax.swing.*;

//import Negozio.Ogg;
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
			pan1.add(tx);
			
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
			pan1.add(ele);
			contenuto.add(pan1);

/*comp3*/Bottone bent=new Bottone("-ENTER-", 5);
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
			pan2.add(bex);
			
/*comp5	Etichetta tv=new Etichetta("           ");
			contenuto.add(tv);*/
			
/*comp6*/Bottone b2=new Bottone("+ ADD new +");
			b2.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	AggiungiMerce aggg=new AggiungiMerce();
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