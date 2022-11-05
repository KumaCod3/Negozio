package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import Negozio.Anagrafica;
import Negozio.Cliente;
import Negozio.DataB;
import Negozio.Fornitore;

import javax.swing.JRadioButton;
import java.awt.Choice;

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
	
	public AggiungiPersona() {
		super("Add new Person");
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(9,1));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		panel_1.setOpaque(false);
		contenuto.add(panel_1);
		
		Etichetta ty=new Etichetta("Choose DB:                       ");
		panel_1.add(ty);
		
		JRadioButton client = new JRadioButton("Customer");
		client.setOpaque(false);
	    client.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            tipo="cliente";
	        }
	    });
	    panel_1.add(client);
		JRadioButton fornit = new JRadioButton("Supplier");
 		fornit.setOpaque(false);
	    fornit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	tipo="fornitore";
	        }
	    });
	    panel_1.add(fornit);
	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(client);
	    group.add(fornit);
	    
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(-20);
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		panel_2.setOpaque(false);
		contenuto.add(panel_2);
		
		Etichetta un=new Etichetta("Title: ");
		panel_2.add(un);
		
		Choice uni = new Choice();
		uni.setPreferredSize(Est.choi);
		uni.add("Choose");
		uni.add("Firm");
		uni.add("Mr.");
		uni.add("Mrs.");
		uni.setFont(Est.plainFont);
		uni.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (uni.getSelectedIndex()>0){
					titolo=uni.getSelectedItem();
				}
			}
		});
		panel_2.add(uni);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		panel_3.setOpaque(false);
		contenuto.add(panel_3);
		
		Etichetta non=new Etichetta("Name: ");
		panel_3.add(non);
		
		FormVuoto tf1 = new FormVuoto("name");
		panel_3.add(tf1);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setAlignment(FlowLayout.TRAILING);
		panel_4.setOpaque(false);
		contenuto.add(panel_4);
		
		Etichetta qtt=new Etichetta("Last Name: ");
		panel_4.add(qtt);
		
		FormVuoto tf2 = new FormVuoto("last n.");
		panel_4.add(tf2);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_5.getLayout();
		flowLayout_5.setAlignment(FlowLayout.TRAILING);
		panel_5.setOpaque(false);
		contenuto.add(panel_5);
		
		Etichetta tel=new Etichetta("Phone: ");
		panel_5.add(tel);
		
		FormVuoto tf3 = new FormVuoto("phone");
		panel_5.add(tf3);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_6.getLayout();
		flowLayout_6.setAlignment(FlowLayout.TRAILING);
		flowLayout_6.setAlignOnBaseline(true);
		panel_6.setOpaque(false);
		contenuto.add(panel_6);
		
		Etichetta ty_1 = new Etichetta("eMail:   ");
		panel_6.add(ty_1);
		
		FormVuoto tf4 = new FormVuoto("mail");
		panel_6.add(tf4);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_7.getLayout();
		flowLayout_7.setAlignment(FlowLayout.TRAILING);
		flowLayout_7.setAlignOnBaseline(true);
		panel_7.setOpaque(false);
		contenuto.add(panel_7);
		
		Etichetta ty_2 = new Etichetta("VAT number:  ");
		panel_7.add(ty_2);
		
		FormVuoto tf5 = new FormVuoto("VAT");
		panel_7.add(tf5);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_8.getLayout();
		flowLayout_4.setAlignment(FlowLayout.TRAILING);
		FlowLayout flowLayout_8 = (FlowLayout) panel_7.getLayout();
		flowLayout_8.setAlignment(FlowLayout.TRAILING);
		flowLayout_8.setAlignOnBaseline(true);
		panel_8.setOpaque(false);
		contenuto.add(panel_8);
		
		Etichetta ty_3 = new Etichetta("Address: ");
		panel_8.add(ty_3);
		
		FormVuoto tf6 = new FormVuoto("address");
		panel_8.add(tf6);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_9.getLayout();
		flowLayout_11.setAlignment(FlowLayout.TRAILING);
		FlowLayout flowLayout_9 = (FlowLayout) panel_7.getLayout();
		flowLayout_9.setAlignment(FlowLayout.TRAILING);
		flowLayout_9.setAlignOnBaseline(true);
		panel_9.setOpaque(false);
		contenuto.add(panel_9);
		
		Etichetta tchtOpeningBal = new Etichetta("Opening Balance: ");
		tchtOpeningBal.setText("Opening Bal.:");
		panel_9.add(tchtOpeningBal);
		
		FormVuoto tf7 = new FormVuoto("balance");
		panel_9.add(tf7);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_7.getLayout();
		flowLayout_10.setAlignment(FlowLayout.TRAILING);
		flowLayout_10.setAlignOnBaseline(true);
		panel_10.setOpaque(false);
		c.add("South", panel_10);
		
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		panel_10.add(bex);
		
		Bottone bent=new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				nome=tf1.ret;
				cognome=tf2.ret;
				telefono=tf3.ret;
				email=tf4.ret;
				iva=tf5.ret;
				indirizzo=tf6.ret;
				if (tf7.ret.equals("balance")){
					saldo=0.0;
				}
				else{
					try {
						saldo=Double.parseDouble(tf7.ret);
					}
					catch (Exception ex){
						Errore err=new Errore("Enter number with . ");
						err.setVisible(true);
					}
				}
		    	
		    	if (tipo.contentEquals("cliente")){
		    		Cliente inser=new Cliente(titolo, nome, cognome, telefono, email, iva, indirizzo, saldo);
		    		DataB.agg(inser);
		    		ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	MyReadC.scarica();
			    	dispose();
		    	}
		    	else if (tipo.contentEquals("fornitore")){
		    		Fornitore inser=new Fornitore(titolo, nome, cognome, telefono, email, iva, indirizzo, saldo);
		    		DataB.agg(inser);
		    		ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	MyReadF.scarica();
			    	dispose();
		    	}
			}
		});
		panel_10.add(bent);
		
		c.add("Center", contenuto);
		pack();
		
	}
}
