package GUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SchedaPersona extends Finestra{
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
		contenuto.setLayout(new GridLayout(5,2));
		
/*comp1*/  Etichetta non=new Etichetta("Nominative: ");
		contenuto.add(non);	
		Etichetta nn=new Etichetta(cognome+" "+nome);
		contenuto.add(nn);	
		
/*comp2*/  Etichetta uni=new Etichetta("Contacts: ");
		contenuto.add(uni);
		Etichetta uu=new Etichetta("Phone: "+telefono+"     eMail: "+email);
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

		JPanel fot=new JPanel();
		fot.setBorder(Est.bordo);
		fot.setOpaque(false);
		fot.setLayout(new GridLayout(1,3));
		
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		fot.add(bex);
		
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
		fot.add(bin);
		
/*comp 7 FOR FORNITORI*/
		if (tipo.equals("fornitore")){
			contenuto.setLayout(new GridLayout(6,2));

			Etichetta forn=new Etichetta("Products: ");
			contenuto.add(forn);	
			
			try {
				MyChoice ele1=new MyChoice(Main.db.getElenSuppM(codice), "ciao");
				JPanel ff=new JPanel();
				ff.setOpaque(false);
				FlowLayout flowLayout = (FlowLayout) ff.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				flowLayout.setAlignOnBaseline(true);
				ff.add(ele1);
				contenuto.add(ff);
			} catch (SQLException ex) {ex.printStackTrace();}

/*OUT*/	
			fot.setLayout(new GridLayout(1,4));
			Bottone agg=new Bottone("Assign Product");
			agg.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	try{
				    	AssegnaMerc ass=new AssegnaMerc(codice);
				    	ass.setVisible(true);
				    	setVisible(false);
				    	dispose();
			    	}
			    	catch (Exception pp){}
				}
			});
			fot.add(agg);
			
			Bottone ord=new Bottone("Order Product");
			ord.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	if (codice!=-1){
			    		try{
					    	Errore er=new Errore(codice, SchedaPersona.this);
					    	er.setVisible(true);
					    	setVisible(false);
//					    	dispose();
				    	}
				    	catch (Exception y){
				    		y.printStackTrace();
				    	}
			    	}
				}
			});
			fot.add(ord);
			c.add("South",fot);
		}			
			
/*comp OUT FOR CLIENTI*/
		else if (tipo.equals("cliente")){
			Bottone eli=new Bottone("OPEN CART");
			eli.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	Spesa aggg=new Spesa(x);
					aggg.setVisible(true);
					dispose();
				}
			});
			fot.add( eli);
			c.add("South",fot);
		}
		c.add("Center", contenuto);
		pack();
	}
}