package GUI;
import Negozio.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchedaPersona extends Finestra{
//	String titolo;
	int codice;
	String cognome;
	String nome;
	String telefono;
	String email;
	String stato;
	String citta;
	String indirizzo;
	String iva="";
	Double saldo=0.00;
	String note;
	int index;
	
//	Anagrafica mer;
//	int index=-1;
	
	public SchedaPersona(int x, String tipo){
		super("Scheda "+tipo);
		String data="";
		codice=x;
		if (tipo.equals("fornitore")){
			try {
				data=Main.db.leggiForID(x);
			}
			catch (SQLException e){	e.printStackTrace();	}
		}
		else {
			try {
				data=Main.db.leggiCliID(x);
			}
			catch (SQLException e){	e.printStackTrace();	}
		}
		String[] spl=data.split(",");
		this.nome=spl[1];
		this.cognome=spl[2];
		this.telefono=spl[3];
		this.email=spl[4];
		this.stato=spl[5];
		this.citta=spl[6];
		this.indirizzo=spl[7];
		this.iva=spl[8];
		this.saldo=Double.parseDouble(spl[9]);
		this.note=spl[10];
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(6,2));
		
/*comp1*/  Etichetta non=new Etichetta("Nominative: ");
		contenuto.add(non);	
		Etichetta nn=new Etichetta(cognome+" "+nome);
		contenuto.add(nn);	
		
/*comp2*/  Etichetta uni=new Etichetta("Contacts: ");
		contenuto.add(uni);
		Etichetta uu=new Etichetta("Phone: "+telefono+" eMail: "+email);
		contenuto.add(uu);
		
/*comp3*/  Etichetta ac=new Etichetta("VAT number: ");
		contenuto.add(ac);
		Etichetta aa=new Etichetta("n. "+iva);
		contenuto.add(aa);
		
/*comp4*/  Etichetta ri=new Etichetta("Balance: ");
		contenuto.add(ri);
		Etichetta rr=new Etichetta(Est.deci.format(saldo)+" eu. ");
		contenuto.add(rr);
		
/*comp5*/  Etichetta va=new Etichetta("Address: ");
		contenuto.add(va);
		Etichetta vv=new Etichetta(indirizzo+" ");
		contenuto.add(vv);
		
/*comp6*/Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		contenuto.add(bex);
		
		Bottone bin=new Bottone("MODIFY");
		bin.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
		    		ModPersona modifP=new ModPersona(x,tipo);
			    	modifP.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
			}
		});
		contenuto.add(bin);
		
/*comp 7 FOR FORNITORI*/
		if (tipo.equals("fornitore")){
			contenuto.setLayout(new GridLayout(7,2));

			Etichetta forn=new Etichetta("Products: ");
			contenuto.add(forn);	
			
			Choice ele1=new Choice();
			ele1.add("Choose");
			
			try{
				ResultSet xx=Main.db.getElenSuppM(codice);
				while (xx.next()) {
					ele1.add(xx.getString(1)+", "+xx.getString(2));
				}
			}
			catch (SQLException e){	e.printStackTrace();	}
			
			ele1.setFont(Est.plainFont);
			ele1.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e){
				}
				public void focusLost(FocusEvent e){
					if (ele1.getSelectedItem().equals("Choose")||ele1.getSelectedItem().equals("Empty")){
					}
					else {
						String temp=ele1.getSelectedItem();
						String[] temAr=temp.split(", ");
						index=Integer.parseInt(temAr[0]);
					}
				}
			});
			contenuto.add(ele1);

/*OUT*/		JPanel sud=new JPanel();
			sud.setOpaque(false);
			sud.setLayout(new GridLayout(1,2));
			
			Bottone agg=new Bottone("Assign Product");
			agg.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	try{
//				    	Fornitore f=DataB.trovaForn(mer);
				    	AssegnaMerc ass=new AssegnaMerc(codice);
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
			
			Bottone ord=new Bottone("Order Product");
			ord.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	if (index!=-1){
				    	try{
				    		// TODO
//					    	Fornitore f=DataB.trovaForn(mer);
//					    	Spesa sp=new Spesa(DataM.get(index),f);
//					    	sp.setVisible(true);
//					    	setVisible(false);
//					    	dispose();
				    	}
				    	catch (Exception y){
				    		// ERRORE
				    	}
			    	}
				}
			});
			sud.add(ord);
			c.add("South",sud);
		}			
			
/*comp OUT FOR CLIENTI*/
		if (tipo.equals("cliente")){
			Bottone eli=new Bottone("OPEN CART");
			eli.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	Spesa aggg=new Spesa(x);
					aggg.setVisible(true);
					dispose();
				}
			});
			c.add("South", eli);
		}
		c.add("Center", contenuto);
		pack();
	}
}