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
		super("Product details"/*, 650, 920*/);
		
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
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(6,2));
		
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
		ele1.setFont(Est.plainFont);
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
		
		Panel sud=new Panel();
		sud.setLayout(new GridLayout(2,2));
		
/*comp7*/Bottone bex=new Bottone("-EXIT-");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	dispose();
			}
		});
		sud.add(bex);
		Bottone bin=new Bottone("-MODIFY-");
		bin.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
			    	setVisible(false);
			    	AggiungiMerce modifM=new AggiungiMerce(x);
			    	modifM.setVisible(true);
		    	
			    	dispose();
		    	}
			}
		});
		sud.add(bin);
		
/*OUT*/		
		Bottone eli=new Bottone("-DELETE-");
//		eli.but.setBackground(Est.rosso);
		eli.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
			    	setVisible(false);
			    	Errore del=new Errore(x);
			    	del.setVisible(true);
			    	dispose();
		    	}
			}
		});
		sud.add(eli);
		Bottone ord=new Bottone("-ORDER-");
		ord.but.addActionListener(new ActionListener() {
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
