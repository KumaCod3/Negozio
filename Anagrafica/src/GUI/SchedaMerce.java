package GUI;
import Negozio.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchedaMerce extends Finestra{
	public String nome="";
//	int codice;
	int index;
	Double quantita=0.0;
	Double rincaro=0.0;
	Double sconto=0.0;
	Double prezzoA=0.0;
	Double prezzoV=0.0;
	Double valore=0.0;
	String unita="";
	String note="";
	
	public SchedaMerce(int x){
		super("Product details");
		index=x;
		if (x!=-1) {
			try {
				String data=Main.db.leggiMercID(x);
				
				String[] spl=data.split(",");

				this.nome=spl[1];
				this.unita=spl[2];
				this.quantita=Double.parseDouble(spl[3]);
				this.prezzoA=Double.parseDouble(spl[4]);
				this.rincaro=Double.parseDouble(spl[5]);
				this.sconto=Double.parseDouble(spl[6]);
				this.note=spl[7];
				this.prezzoV=prezzoA+(prezzoA*rincaro)-(prezzoA*sconto);
				this.valore=prezzoA*quantita;
				
			} catch (SQLException ex) {	ex.printStackTrace(); }
		}
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(7,2));
		
/*comp1*/  Etichetta non=new Etichetta("Product: ");
		contenuto.add(non);	
		Etichetta nn=new Etichetta(""+nome);
		contenuto.add(nn);	
		
/*comp2*/  Etichetta uni=new Etichetta("In stock: ");
		contenuto.add(uni);
		Etichetta uu=new Etichetta(quantita+" "+unita);
		if (quantita<=5.0){
			uu.setForeground(Est.rosso);
			uu.setOpaque(true);
			uni.setOpaque(true);
		}
		contenuto.add(uu);
		
/*comp3*/  Etichetta ac=new Etichetta("Purchase price: ");
		contenuto.add(ac);
		Etichetta aa=new Etichetta(Est.deci.format(prezzoA)+"eu for "+unita);
		contenuto.add(aa);
		
/*comp4*/  Etichetta ri=new Etichetta("Selling price: ");
		contenuto.add(ri);
		Etichetta rr=new Etichetta(Est.deci.format(prezzoV)+"eu");
		contenuto.add(rr);
		
/*comp5*/		Etichetta car=new Etichetta(Est.sco.format(rincaro*100)+" % increase");
		contenuto.add(car);
/*comp6*/		Etichetta sco=new Etichetta(Est.sco.format(sconto*100)+" %  deal");
		contenuto.add(sco);
		
/*comp7*/  Etichetta va=new Etichetta("Total in stock value: ");
		contenuto.add(va);
		Etichetta vv=new Etichetta(Est.deci.format(valore)+"eu");
		contenuto.add(vv);
		
/*comp8*/  Etichetta forn=new Etichetta("Supplier: ");
		contenuto.add(forn);
		
		try {
			MyChoice ele1=new MyChoice(Main.db.getElenSuppF(index),5);
			ele1.jList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					try {
						String[] temp=ele1.getSel().split(", ");
	//					index=Integer.parseInt(temp[0]);
					}
					catch (Exception ex){
						// no selection
					}
					
				}
			});
			contenuto.add(ele1);
		} catch (SQLException ex) {ex.printStackTrace();}
		
		Panel sud=new Panel();
		sud.setLayout(new GridLayout(3,2));
		
/*comp9*/Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		sud.add(bex);
		Bottone bin=new Bottone("MODIFY");
		bin.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index!=-1){
			    	ModMerce modifM=new ModMerce(index);
			    	setVisible(false);
			    	modifM.setVisible(true);
		    	
			    	dispose();
		    	}
			}
		});
		sud.add(bin);
		
//--------new
		
		Bottone agg=new Bottone("Assign Product");
		agg.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try{
			    	AssegnaMerc ass=new AssegnaMerc(index, "ciao");
			    	ass.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
		    	catch (Exception pp){
		    		// ERRORE
		    	}
			}
		});
		sud.add(agg);
//---------fin
		
/*OUT*/		
		Bottone eli=new Bottone("DELETE");
		eli.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
			    	Errore del=new Errore(SchedaMerce.this);
			    	del.setVisible(true);
			    	setVisible(false);
		    	}
			}
		});
		
		sud.add(eli);
		Bottone ord=new Bottone("ORDER");
		ord.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
		    		
		    		try{
				    	Errore er=new Errore(index, SchedaMerce.this);
				    	er.setVisible(true);
				    	setVisible(false);
//				    	dispose();
			    	}
			    	catch (Exception y){
			    		y.printStackTrace();
			    	}
		    	}
		    	else {
		    	}
			}
		});
		
		sud.add(ord);
		c.add("Center", contenuto);
		c.add("South", sud);
		pack();
	}
}
