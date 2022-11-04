package GUI;
import Negozio.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AggiungiMerce extends Finestra{
	String nome="";
	Double quantita=0.0;
	Double prezzoA=0.0;
	int rincaro=0;
	String unita="";
	
	public AggiungiMerce(int x){
		super("Product");
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(6,1));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		flowLayout.setAlignOnBaseline(true);
		panel_1.setOpaque(false);
		contenuto.add(panel_1);
		
		Etichetta etNome = new Etichetta("Product:             ");
		panel_1.add(etNome);
		
		FormVuoto tf1 = new FormVuoto("name");
		if (x!=-1) {
			tf1.setEditable(false);
		}
		panel_1.add(tf1);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		panel_2.setOpaque(false);
		contenuto.add(panel_2);
		
		Etichetta etUni = new Etichetta("Unit of measure:              ");
		panel_2.add(etUni);
		
		Choice uni = new Choice();
		uni.setPreferredSize(Est.choi);
		uni.add("choose");
		uni.add("Pieces");
		uni.add("Kg");
		uni.add("Grams");
		uni.add("Quintals");
		uni.add("Liters");
		uni.setFont(Est.plainFont);
		uni.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (uni.getSelectedIndex()>0){
					unita=uni.getSelectedItem();
				}
			}
		});
		panel_2.add(uni);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		panel_3.setOpaque(false);
		contenuto.add(panel_3);
		
		Etichetta etQuant = new Etichetta("Quantity:            ");
		panel_3.add(etQuant);
		
		FormVuoto tf2 = new FormVuoto("quantity");
		panel_3.add(tf2);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setAlignment(FlowLayout.TRAILING);
		panel_4.setOpaque(false);
		contenuto.add(panel_4);
		
		Etichetta etPri  = new Etichetta("Purchase price:            ");
		panel_4.add(etPri);
		
		FormVuoto tf3 = new FormVuoto("price");
		panel_4.add(tf3);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_5.getLayout();
		flowLayout_5.setAlignment(FlowLayout.TRAILING);
		panel_5.setOpaque(false);
		contenuto.add(panel_5);
		
		Etichetta etRinc = new Etichetta("Choose %price increase:              ");
		panel_5.add(etRinc);
		
		Choice rim = new Choice();
		rim.setPreferredSize(Est.choi);
		rim.add("choose");
		rim.add("10");
		rim.add("20");
		rim.add("30");
		rim.add("50");
		rim.setFont(Est.plainFont);
		rim.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (rim.getSelectedIndex()>0){
					rincaro=Integer.parseInt(rim.getSelectedItem());
				}
			}
		});
		panel_5.add(rim);
		
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		contenuto.add(panel_6);
		
		Bottone bex = new Bottone("EXIT");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		
		panel_6.add(bex);
		
		Bottone bent = new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x==-1) {
			    	nome=tf1.ret;
			    	try {
			    		quantita=Double.parseDouble(tf2.ret);
			    		prezzoA=Double.parseDouble(tf3.ret);
			    		Merce inserisci =new Merce(nome, quantita, rincaro, prezzoA, unita);
						DataM.agg(inserisci);
						ConsultaMerci consultaM=new ConsultaMerci(/*c*/);
				    	consultaM.setVisible(true);
				    	MyReadM.scarica();
				    	setVisible(false);
				    	dispose();
			    	}
			    	catch (Exception ex){
			    		Errore err=new Errore("Enter number with . ");
			    		err.setVisible(true);
			    	}
			    	
					
		    	}
		    	else {
		    		try {
			    		quantita=Double.parseDouble(tf2.ret);
			    		prezzoA=Double.parseDouble(tf3.ret);
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
			    	catch (Exception ex){
			    		Errore err=new Errore("Enter number with . ");
			    		err.setVisible(true);
			    	}
		    	}
			}
		});
		panel_6.add(bent);
		
		
		if (x!=-1) {
			JPanel panel_7 = new JPanel();
			panel_7.setOpaque(false);
			Bottone agg=new Bottone("Assign to Supplier", 5);
			agg.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	try{
				    	AssegnaMerc ass=new AssegnaMerc(DataM.get(x));
				    	ass.setVisible(true);
				    	setVisible(false);
				    	dispose();
			    	}
			    	catch (Exception pp){
			    		// ERRORE
			    	}
				}
			});
			panel_7.add(agg);
			c.add("South", panel_7);
		}
		
		c.add("Center", contenuto);
		pack();
		
	}
}
