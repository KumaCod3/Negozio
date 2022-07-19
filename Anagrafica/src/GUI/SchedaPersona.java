package GUI;
import Negozio.*;
//import java.util.*;
import java.awt.*;
import Negozio.*;
import javax.swing.*;
import java.awt.event.*;

public class SchedaPersona extends Finestra{

	String titolo;
	String cognome;
	String nome;
	String iva="";
	String telefono;
	String email;
	Double saldo=0.00;
	String indirizzo;
	Anagrafica mer;
	int index;
		public SchedaPersona(int x, String tipo){
			super("Scheda "+tipo);
			if (x>-1){
				if (tipo.equals("fornitore")){
					try {
						mer=DataB.fornitori.get(x);
					}
					catch (Exception e){
						
					}
				}
				else {
					try {
						mer=DataB.clienti.get(x);
					}
					catch (Exception e){
						
					}
				}

				this.titolo=mer.getTitolo();
				this.cognome=mer.getCognome();
				this.nome=mer.getNome();
				this.iva=mer.getIva();
				this.telefono=mer.getTelefono();
				this.email=mer.getEmail();
				this.saldo=mer.getSaldo();
				this.indirizzo=mer.getIndirizzo();
			}
			
			Panel contenuto=new Panel();
			contenuto.setLayout(new GridLayout(6,2));
			
	/*comp1*/  Etichetta non=new Etichetta("Nominativo: ");
			contenuto.add(non);	
			Etichetta nn=new Etichetta(titolo+" "+cognome+" "+nome);
			contenuto.add(nn);	
			
	/*comp2*/  Etichetta uni=new Etichetta("Contatti: ");
			contenuto.add(uni);
			Etichetta uu=new Etichetta("tel: "+telefono+" eMail: "+email);
			contenuto.add(uu);
			
	/*comp3*/  Etichetta ac=new Etichetta("Partita IVA: ");
			contenuto.add(ac);
			Etichetta aa=new Etichetta("n. "+iva);
			contenuto.add(aa);
			
	/*comp4*/  Etichetta ri=new Etichetta("Saldo: ");
			contenuto.add(ri);
			Etichetta rr=new Etichetta(Est.deci.format(saldo)+" eu. ");
			contenuto.add(rr);
			
	/*comp5*/  Etichetta va=new Etichetta("Indirizzo: ");
			contenuto.add(va);
			Etichetta vv=new Etichetta(indirizzo+" ");
			contenuto.add(vv);
			
	/*comp6*/Pulsante bex=new Pulsante("-ESCI-");
			bex.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	setVisible(false);
			    	ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	dispose();
				}
			});
			contenuto.add(bex);
			
			Pulsante bin=new Pulsante("-MODIFICA-");
			bin.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	if (x!=-1){
				    	setVisible(false);
				    	AggiungiPersona modifP=new AggiungiPersona(x,tipo);
				    	modifP.setVisible(true);
			    	
				    	dispose();
			    	}
				}
			});
			contenuto.add(bin);
			
/*comp 7*/
			if (tipo.equals("fornitore")){
				contenuto.setLayout(new GridLayout(7,2));
	
				Etichetta forn=new Etichetta("Merci: ");
				contenuto.add(forn);	
				
				Choice ele1=new Choice();
				ele1.add("Scegli");
				try{
					for (Merce a: mer.getMerc().values()){
						ele1.add(a.getNome()+" "+a.getCod());
					}
				}
				catch (Exception e){
					ele1.add("Lista vuota");
				}
				ele1.setFont(Est.font);
				ele1.addFocusListener(new FocusListener() {
					public void focusGained(FocusEvent e){
					}
					public void focusLost(FocusEvent e){
						if (ele1.getSelectedItem().equals("Scegli")||ele1.getSelectedItem().equals("Lista vuota")){
						}
						else {
							String temp=ele1.getSelectedItem();
							String[] temAr=temp.split(" ");
							index=Integer.parseInt(temAr[temAr.length-1]);
						}
					}
				});
				contenuto.add(ele1);
	
	/*OUT*/		Panel sud=new Panel();
				sud.setLayout(new GridLayout(1,2));
				
				Pulsante agg=new Pulsante("-Aggiungi Merce-");
				agg.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	setVisible(false);
				    	/* AGGIUNGI MERCE A FORNITORE mer*/
				    	dispose();
					}
				});
				sud.add(agg);
				
				Pulsante ord=new Pulsante("-Ordina Merce-");
				ord.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	if (x!=-1){
					    	setVisible(false);
					    	/*FAI ACQUISTO DI MERCE index*/
				    	
					    	dispose();
				    	}
					}
				});
				sud.add(ord);
				add("South",sud);
			}			
				
/*comp OUT cli*/
			if (tipo.equals("cliente")){
				Pulsante eli=new Pulsante("-APRI ACQUISTO-");
				eli.setPreferredSize(Est.piccolo);
				eli.setBackground(Est.medio);
				eli.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	Spesa aggg=new Spesa(DataB.clienti.get(x));
						aggg.setVisible(true);
						dispose();
					}
				});
				add("South", eli);
			}
			add(contenuto);
			
			pack();
		}
	}
