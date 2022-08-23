package GUI;
import Negozio.*;
//import java.util.*;
import java.awt.*;
import Negozio.*;
import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class SchedaMerce extends Finestra{
	String nome;
	int codice;
	Double quantita;
	int rincaro;
	Double prezzoA;
	Double prezzoV;
	Double valore;
	String unita;
	Merce mer;
	int indexF=-1;
	public SchedaMerce(int x){
		super("Product details");
		
		if (x!=-1&&DataM.elenco.containsKey(x)){
			mer=DataM.elenco.get(x);
			this.nome=mer.getNome();
			this.quantita=mer.getQuantita();
			this.rincaro=mer.getRincaro();
			this.prezzoA=mer.getPrezzoA();
			this.unita=mer.getUnit();
			this.prezzoV=mer.getPrezzoV();
			this.valore=mer.getValore();
			this.codice=x;
		}
		
		Panel contenuto=new Panel();
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
			uu.setBackground(Est.chiarissimo);
			uni.setOpaque(true);
			uni.setBackground(Est.chiarissimo);
		}
		contenuto.add(uu);
		
/*comp3*/  Etichetta ac=new Etichetta("Purchase price: ");
		contenuto.add(ac);
		Etichetta aa=new Etichetta(Est.deci.format(prezzoA)+"eu for "+unita);
		contenuto.add(aa);
		
/*comp4*/  Etichetta ri=new Etichetta("Selling price: ");
		contenuto.add(ri);
		Etichetta rr=new Etichetta(Est.deci.format(prezzoV)+"eu ("+rincaro+"% price increase)");
		contenuto.add(rr);
		
/*comp5*/  Etichetta va=new Etichetta("Total in stock value: ");
		contenuto.add(va);
		Etichetta vv=new Etichetta(Est.deci.format(valore)+"eu");
		contenuto.add(vv);
		
/*comp6*/  Etichetta forn=new Etichetta("Supplier: ");
		contenuto.add(forn);	
		Choice ele1=new Choice();
		ele1.add("Choose");
		try{
			for (Fornitore a:mer.getForn()){
				ele1.add(a.getCognome()+", "+a.getNome());
			}
		}
		catch (Exception e){
			ele1.add("Empty");
		}
		ele1.setFont(Est.font);
		ele1.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (ele1.getSelectedItem().equals("Choose")||ele1.getSelectedItem().equals("Empty")){
				}
				else {
					String[] temp=ele1.getSelectedItem().split(", ");
					indexF=DataB.trovaPersona(temp[0], temp[1]);
				}
			}
		});
		contenuto.add(ele1);	
		
/*comp7*/Pulsante bex=new Pulsante("-EXIT-");
		bex.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	dispose();
			}
		});
		contenuto.add(bex);
		Pulsante bin=new Pulsante("-MODIFY-");
		bin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
			    	setVisible(false);
			    	AggiungiMerce modifM=new AggiungiMerce(x);
			    	modifM.setVisible(true);
		    	
			    	dispose();
		    	}
			}
		});
		contenuto.add(bin);
		
/*OUT*/		Panel sud=new Panel();
		sud.setLayout(new GridLayout(1,2));
		Pulsante eli=new Pulsante("-DELETE-");
		eli.setPreferredSize(Est.piccolo);
		eli.setBackground(Est.rosso);
		eli.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
			    	setVisible(false);
			    	ErrorMessage del=new ErrorMessage(x);
			    	del.setVisible(true);
			    	dispose();
		    	}
			}
		});
		sud.add(eli);
		Pulsante ord=new Pulsante("-ORDER-");
		ord.setPreferredSize(Est.piccolo);
		ord.setBackground(Est.oran);
		ord.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
		    		
		    		try{
				    	Fornitore f=DataB.get(indexF,"ciao");
				    	Spesa sp=new Spesa(mer,f);
				    	sp.setVisible(true);;
				    	dispose();
			    	}
			    	catch (Exception y){
			    		// ERRORE
			    	}
		    	}
			}
		});
		sud.add(ord);
		
		c.add("Center", contenuto);
		c.add("South", sud);
		pack();
	}
	
}
