package GUI;
import Negozio.*;
//import java.util.*;
import java.awt.*;
import Negozio.*;
import javax.swing.*;

import java.awt.event.*;

public class AggiungiMerce extends Finestra{
	String nome="";
	Double quantita=0.0;
	Double prezzoA=0.0;
	int rincaro=0;
	String unita="";
	public AggiungiMerce(){
		super("Add new Product");
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(6,2));

/*comp1*/  Etichetta non=new Etichetta("Product: ");
		contenuto.add(non);
		
		
		JPanel pan1=new JPanel();
 /*comp2*/FormVuoto tf1=new FormVuoto("Name");
		pan1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan1.add(tf1);
		pan1.setBackground(Est.chiaro);
		contenuto.add(pan1);
		
/*comp3*/  Etichetta un=new Etichetta("Unit of measure: ");
		contenuto.add(un);
/*comp4*/Choice uni=new Choice();
		uni.add("Choose");
		uni.add("Pieces");
		uni.add("Kg");
		uni.add("Grams");
		uni.add("Quintals");
		uni.add("Liters");
		uni.setFont(Est.font);
		uni.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (uni.getSelectedIndex()>0){
					unita=uni.getSelectedItem();
				}
			}
		});
		contenuto.add(uni);	
		
/*comp5*/  Etichetta qtt=new Etichetta("Quantity: ");
		contenuto.add(qtt);
/*comp6*/JPanel pan2=new JPanel();
		FormVuoto tf2=new FormVuoto("Quantity");
		pan2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan2.add(tf2);
		pan2.setBackground(Est.chiaro);
		contenuto.add(pan2);
		
/*comp7*/  Etichetta prr=new Etichetta("Purchase price: ");
		contenuto.add(prr);
/*comp8*/ JPanel pan3=new JPanel();
		FormVuoto tf3=new FormVuoto("Price");
		pan3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan3.add(tf3);
		pan3.setBackground(Est.chiaro);
		contenuto.add(pan3);
		
/*comp9*/  Etichetta rin=new Etichetta("Choose % price increase: ");
		contenuto.add(rin);
/*comp10*/Choice rim=new Choice();
		rim.add("Choose");
		rim.add("10");
		rim.add("20");
		rim.add("30");
		rim.add("50");
		rim.setFont(Est.font);
		rim.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (rim.getSelectedIndex()>0){
					rincaro=Integer.parseInt(rim.getSelectedItem());
				}
			}
		});
		contenuto.add(rim);	
			
/*comp11*/Bottone bex=new Bottone("-EXIT-");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	dispose();
			}
		});
		contenuto.add(bex);
		
/*comp12*/Bottone bent=new Bottone("-ENTER-");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	nome=tf1.ret;
		    	try {
		    		quantita=Double.parseDouble(tf2.ret);
		    	}
		    	catch (Exception ex){
		    		Errore err=new Errore("Enter number with . ");
		    		err.setVisible(true);
		    	}
		    	try {
					prezzoA=Double.parseDouble(tf3.ret);
				}
				catch (Exception ex){
					Errore err=new Errore("Enter number with . ");
					err.setVisible(true);
				}
				Merce inserisci =new Merce(nome, quantita, rincaro, prezzoA, unita);
				DataM.agg(inserisci);
				ConsultaMerci consultaM=new ConsultaMerci(/*c*/);
		    	consultaM.setVisible(true);
		    	MyReadM.scarica();
		    	setVisible(false);
		    	dispose();
			}
		});
		contenuto.add(bent);
		
		c.add("Center", contenuto);
		pack();
	}
	
	public AggiungiMerce(int x){
		super("Product modification");
		Merce prod=DataM.get(x);
		
		nome=prod.getNome();
		quantita=prod.getQuantita();
		prezzoA=prod.getPrezzoA();
		rincaro=prod.getRincaro();
		unita=prod.getUnit();
		
		c.setLayout(new BorderLayout(100,10));
		setBackground(Est.chiaro);
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(6,2));

/*comp1*/  Etichetta non=new Etichetta("Product: ");
		contenuto.add(non);
/*comp2*/ Etichetta nom=new Etichetta(nome);
		contenuto.add(nom);
		
/*comp3*/  Etichetta un=new Etichetta("Unit of measure: ");
		contenuto.add(un);
/*comp4*/Choice uni=new Choice();
		uni.add(unita);
		uni.add("Pezzi");
		uni.add("Kg");
		uni.add("grammi");
		uni.add("quintali");
		uni.add("tonnellate");
		uni.setFont(Est.font);
		uni.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (uni.getSelectedIndex()>0){
					unita=uni.getSelectedItem();
				}
			}
		});
		contenuto.add(uni);	
		
/*comp5*/  Etichetta qtt=new Etichetta("Quantity: ");
		contenuto.add(qtt);
/*comp6*/JPanel pan2=new JPanel();
		FormVuoto tf2=new FormVuoto(quantita+"");
		pan2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan2.add(tf2);
		pan2.setBackground(Est.chiaro);
		contenuto.add(pan2);
		
/*comp7*/  Etichetta prr=new Etichetta("Purchase price: ");
		contenuto.add(prr);
/*comp8*/ JPanel pan3=new JPanel();
		FormVuoto tf3=new FormVuoto(prezzoA+"");
		pan3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan3.add(tf3);
		pan3.setBackground(Est.chiaro);
		contenuto.add(pan3);
		
/*comp9*/  Etichetta rin=new Etichetta("Choose % price increase: ");
		contenuto.add(rin);
/*comp10*/Choice rim=new Choice();
		rim.add(""+(rincaro));
		rim.add("10");
		rim.add("20");
		rim.add("30");
		rim.add("50");
		rim.setFont(Est.font);
		rim.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (rim.getSelectedIndex()>0){
					rincaro=Integer.parseInt(rim.getSelectedItem());
				}
			}
		});
		contenuto.add(rim);	
			
/*comp11*/Bottone bex=new Bottone("-EXIT-");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	dispose();
			}
		});
		contenuto.add(bex);
		
/*comp12*/Bottone bent=new Bottone("-ENTER-");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		quantita=Double.parseDouble(tf2.ret);
		    	}
		    	catch (Exception ex){
		    	}
		    	try {
					prezzoA=Double.parseDouble(tf3.ret);
				}
				catch (Exception ex){
				}
		    	DataM.get(x).setPrezzoA(prezzoA);
		    	DataM.get(x).setQuantita(quantita);
		    	DataM.get(x).setRincaro(rincaro);
		    	DataM.get(x).setUnita(unita);
		    	
				ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	MyReadM.scarica();
		    	setVisible(false);
		    	dispose();
			}
		});
		contenuto.add(bent);
		
/*comp14*/Bottone agg=new Bottone("-Assign to Supplier-", 5);
		agg.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	try{
			    	AssegnaMerc ass=new AssegnaMerc(DataM.get(x));
			    	ass.setVisible(true);
			    	dispose();
		    	}
		    	catch (Exception pp){
		    		// ERRORE
		    	}
			}
		});
		c.add("South", agg);
				
		c.add(contenuto);
		pack();
	}
}
