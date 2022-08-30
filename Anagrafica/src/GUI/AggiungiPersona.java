package GUI;
import Negozio.*;
//import java.util.*;
import java.awt.*;
import Negozio.*;
import javax.swing.*;
import java.awt.event.*;

public class AggiungiPersona extends Finestra{
	String cognome="";
	String nome="";
	String telefono="";
	String email="";
	String indirizzo="";
	String tipo="";
	Double saldo=0.0;
	String titolo="";
	String iva="";
	boolean sett;
	Anagrafica mer=null;
	public AggiungiPersona(){
		super("Add new Person");
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(10,2));
		
/*comp1*/  Etichetta ty=new Etichetta("Choose DB: ");
		contenuto.add(ty);
		
/*comp2*/Panel ss=new Panel();
		ss.setLayout(new GridLayout(1,2));
		JRadioButton client = new JRadioButton("Customer");
		client.setBackground(Est.chiaro);
	    client.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            tipo="cliente";
	        }
	    });
	    ss.add(client);
		JRadioButton fornit = new JRadioButton("Supplier");
 		fornit.setBackground(Est.chiaro);
	    fornit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	tipo="fornitore";
	        }
	    });
	    ss.add(fornit);
	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(client);
	    group.add(fornit);
	    contenuto.add(ss);
		
/*comp3*/  Etichetta un=new Etichetta("Title: ");
		contenuto.add(un);
		
/*comp4*/Choice uni=new Choice();
		uni.add("Choose");
		uni.add("Firm");
		uni.add("Mr.");
		uni.add("Mrs.");
		uni.setFont(Est.font);
		uni.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (uni.getSelectedIndex()>0){
					titolo=uni.getSelectedItem();
				}
			}
		});
		contenuto.add(uni);	
		
/*comp5*/  Etichetta non=new Etichetta("Name: ");
		contenuto.add(non);
/*comp6*/ JPanel pan1=new JPanel();
		FormVuoto tf1 = new FormVuoto("Name");
		pan1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan1.add(tf1);
		pan1.setBackground(Est.chiaro);
		contenuto.add(pan1);

/*comp7*/  Etichetta qtt=new Etichetta("Last Name: ");
		contenuto.add(qtt);
/*comp8*/JPanel pan2=new JPanel();
		FormVuoto tf2 = new FormVuoto("Last Name");
		pan2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan2.add(tf2);
		pan2.setBackground(Est.chiaro);
		contenuto.add(pan2);
		
/*comp9*/  Etichetta tel=new Etichetta("Phone: ");
		contenuto.add(tel);
/*comp10*/JPanel pan3=new JPanel();
		FormVuoto tf3 = new FormVuoto("Phone");
		tf3.setFont(Est.font);
		pan3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan3.add(tf3);
		pan3.setBackground(Est.chiaro);
		contenuto.add(pan3);
		
/*comp11*/  Etichetta mai=new Etichetta("eMail: ");
		contenuto.add(mai);
/*comp12*/JPanel pan4=new JPanel();
		FormVuoto tf4 = new FormVuoto("eMail");
		pan4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan4.add(tf4);
		pan4.setBackground(Est.chiaro);
		contenuto.add(pan4);
		
/*comp13*/  Etichetta ivv=new Etichetta("VAT number: ");
		contenuto.add(ivv);
/*comp14*/JPanel pan5=new JPanel();
		FormVuoto tf5 = new FormVuoto("VAT n.");
		pan5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan5.add(tf5);
		pan5.setBackground(Est.chiaro);
		contenuto.add(pan5);
		
/*comp15*/  Etichetta ind=new Etichetta("Address: ");
		contenuto.add(ind);
/*comp16*/JPanel pan6=new JPanel();
		FormVuoto tf6 = new FormVuoto("address");
		pan6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan6.add(tf6);
		pan6.setBackground(Est.chiaro);
		contenuto.add(pan6);
		
/*comp17*/  Etichetta prr=new Etichetta("Opening balance: ");
		contenuto.add(prr);
/*comp18*/ JPanel pan7=new JPanel();
		FormVuoto tf7 = new FormVuoto("balance");
		pan7.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan7.add(tf7);
		pan7.setBackground(Est.chiaro);
		contenuto.add(pan7);
			
/*comp19*/Bottone bex=new Bottone("-EXIT-");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	dispose();
			}
		});
		contenuto.add(bex);
		
/*comp20*/Bottone bent=new Bottone("-ENTER-");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				nome=tf1.ret;
				cognome=tf2.ret;
				telefono=tf3.ret;
				email=tf4.ret;
				iva=tf5.ret;
				indirizzo=tf6.ret;
				try {
					saldo=Double.parseDouble(tf7.ret);
				}
				catch (Exception ex){
					Errore err=new Errore("Enter number with . ");
					err.setVisible(true);
				}
		    	
		    	if (tipo.contentEquals("cliente")){
		    		Cliente inser=new Cliente(titolo, nome, cognome, telefono, email, iva, indirizzo, saldo);
		    		DataB.agg(inser);
		    		ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	dispose();
		    	}
		    	else if (tipo.contentEquals("fornitore")){
		    		Fornitore inser=new Fornitore(titolo, nome, cognome, telefono, email, iva, indirizzo, saldo);
		    		DataB.agg(inser);
		    		ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	dispose();
		    	}
			}
		});
		contenuto.add(bent);
		
		c.add("Center", contenuto);
		pack();
	}
	
	public AggiungiPersona(int x,String tipo){
		super("Edit person");
		
		if (tipo.equals("fornitore")){
			try {
				mer=DataB.fornitori.get(x);
			}
			catch (Exception e){
				Errore err=new Errore("Wrong Index...");
				err.setVisible(true);
				setVisible(false);
			    ConsultaPersone consultaP=new ConsultaPersone();
			    consultaP.setVisible(true);
			   	dispose();
			}
		}
		else {
			try {
				mer=DataB.clienti.get(x);
			}
			catch (Exception e){
				Errore err=new Errore("Wrong Index...");
				err.setVisible(true);
				setVisible(false);
			   	ConsultaPersone consultaP=new ConsultaPersone();
			   	consultaP.setVisible(true);
			   	dispose();		
			}
		}
		
			c.setLayout(new BorderLayout(100,10));

			setBackground(Est.chiaro);
			
			Panel contenuto=new Panel();
			contenuto.setLayout(new GridLayout(10,2));
			
			titolo=mer.getTitolo();
    		iva=mer.getIva();
    		telefono=mer.getTelefono();
    		email=mer.getEmail();
    		saldo=mer.getSaldo();
    		indirizzo=mer.getIndirizzo();
			
	/*comp1*/  Etichetta ty=new Etichetta("Type of person: ");
			contenuto.add(ty);
			
	/*comp2*/Etichetta ty1=new Etichetta(""+tipo);
			contenuto.add(ty1);
			
	/*comp3*/  Etichetta un=new Etichetta("Title: ");
			contenuto.add(un);
	/*comp4*/JLabel uni=new JLabel();
			uni.setText(mer.getTitolo());
			uni.setFont(Est.font);
			uni.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
			contenuto.add(uni);
			
	/*comp5*/  Etichetta non=new Etichetta("Name: ");
			contenuto.add(non);
	/*comp6*/ Etichetta nomm=new Etichetta(mer.getNome());
			contenuto.add(nomm);
	
	/*comp7*/  Etichetta qtt=new Etichetta("Last Name: ");
			contenuto.add(qtt);
	/*comp8*/Etichetta qtr=new Etichetta(mer.getCognome());
			contenuto.add(qtr);
			
	/*comp9*/  Etichetta tel=new Etichetta("Phone: ");
			contenuto.add(tel);
	/*comp10*/JPanel pan3=new JPanel();
			FormVuoto tf3 = new FormVuoto(mer.getTelefono());
			pan3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			pan3.add(tf3);
			pan3.setBackground(Est.chiaro);
			contenuto.add(pan3);
			
	/*comp11*/  Etichetta mai=new Etichetta("eMail: ");
			contenuto.add(mai);
	/*comp12*/JPanel pan4=new JPanel();
			FormVuoto tf4 = new FormVuoto(mer.getEmail());
			pan4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			pan4.add(tf4);
			pan4.setBackground(Est.chiaro);
			contenuto.add(pan4);
			
	/*comp13*/  Etichetta ivv=new Etichetta("VAT Number: ");
			contenuto.add(ivv);
	/*comp14*/JPanel pan5=new JPanel();
			FormVuoto tf5 = new FormVuoto(mer.getIva());
			pan5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			pan5.add(tf5);
			pan5.setBackground(Est.chiaro);
			contenuto.add(pan5);
			
	/*comp15*/  Etichetta ind=new Etichetta("Address: ");
			contenuto.add(ind);
	/*comp16*/JPanel pan6=new JPanel();
			FormVuoto tf6 = new FormVuoto(mer.getIndirizzo());
			pan6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			pan6.add(tf6);
			pan6.setBackground(Est.chiaro);
			contenuto.add(pan6);
			
	/*comp17*/  Etichetta prr=new Etichetta("Opening balance: ");
			contenuto.add(prr);
	/*comp18*/ JPanel pan7=new JPanel();
			FormVuoto tf7 = new FormVuoto(""+mer.getSaldo());
			pan7.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			pan7.add(tf7);
			pan7.setBackground(Est.chiaro);
			contenuto.add(pan7);
				
	/*comp19*/Bottone bex=new Bottone("-EXIT-");
			bex.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	setVisible(false);
			    	ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	dispose();
				}
			});
			contenuto.add(bex);
			
	/*comp20*/Bottone bent=new Bottone("-ENTER-");
			bent.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	
					telefono=tf3.ret;
					email=tf4.ret;
					iva=tf5.ret;
					indirizzo=tf6.ret;
					try {
						saldo=Double.parseDouble(tf7.ret);
					}
					catch (Exception ex){
						Errore err=new Errore("Enter number with . ");
						err.setVisible(true);
					}
			    	
			    	if (tipo.contentEquals("cliente")){
			    		try {
			    		
		//		    		DataB.clienti.get(x).setTitolo(titolo);
				    		DataB.clienti.get(x).setIva(iva);
				    		DataB.clienti.get(x).setTelefono(telefono);
				    		DataB.clienti.get(x).setEmail(email);
				    		DataB.clienti.get(x).setSaldo(saldo);
				    		DataB.clienti.get(x).setIndirizzo(indirizzo);
				    		ConsultaPersone consultaP=new ConsultaPersone();
					    	consultaP.setVisible(true);
					    	dispose();
			    		}
			    		catch (Exception r){
			    			Errore err=new Errore("Wrong Index...");
			    			err.setVisible(true);
							setVisible(false);
						    ConsultaPersone consultaP=new ConsultaPersone();
						    consultaP.setVisible(true);
						    dispose();			    			
			    		}
			    	}
			    	else if (tipo.contentEquals("fornitore")){
			    		try {
	//		    			DataB.fornitori.get(x).setTitolo(titolo);
			    			DataB.fornitori.get(x).setIva(iva);
			    			DataB.fornitori.get(x).setTelefono(telefono);
			    			DataB.fornitori.get(x).setEmail(email);
			    			DataB.fornitori.get(x).setSaldo(saldo);
			    			DataB.fornitori.get(x).setIndirizzo(indirizzo);
				    		ConsultaPersone consultaP=new ConsultaPersone();
					    	consultaP.setVisible(true);
					    	dispose();
			    		}
			    		catch (Exception u){
			    			Errore err=new Errore("Wrong Index...");
			    			err.setVisible(true);
							setVisible(false);
						    ConsultaPersone consultaP=new ConsultaPersone();
						    consultaP.setVisible(true);
						    dispose();	
			    		}
			    	}
				}
			});
			contenuto.add(bent);
			
			c.add("Center", contenuto);
			pack();

	}
}