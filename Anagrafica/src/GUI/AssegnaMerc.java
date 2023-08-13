package GUI;
import Negozio.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssegnaMerc extends Finestra{
	int codice;
	int indice;
	String nomeForn="";
	String nomeMer="";
	Double costo=0.0;
	
	// a fornitore x
	public AssegnaMerc(int x){
		super("Assign Product to Supplier");
		indice=x;
		try {
			nomeForn=Main.db.getForName(x);
		} catch (SQLException e) {
			nomeForn="not found";
		}

		JPanel contenuto=new JPanel();
		contenuto.setLayout(new GridLayout(4,1));
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		c.add("Center",contenuto);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(1,2));
		panel_1.setOpaque(false);
		contenuto.add(panel_1);
		Etichetta nom=new Etichetta("Supplier: ");
		panel_1.add(nom);
		Etichetta nome=new Etichetta(nomeForn);
		panel_1.add(nome);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new GridLayout(1,2));
		panel_2.setOpaque(false);
		contenuto.add(panel_2);
		Etichetta tt=new Etichetta("Choose Product:");
		panel_2.add(tt);
		Choice ele=new Choice();
		ele.add("Choose");
		try{
			ResultSet xx=Main.db.getElenMerc();
			while (xx.next()) {
				ele.add(xx.getString(1)+", "+xx.getString(2));
			}
		}
		catch (SQLException e){	e.printStackTrace();	}
		ele.setFont(Est.plainFont);
		ele.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (ele.getSelectedItem().equals("Choose")||ele.getSelectedItem().equals("Empty")){
				}
				else {
					String temp=ele.getSelectedItem();
					String[] temAr=temp.split(", ");
					codice=Integer.parseInt(temAr[0]);
					
				}
			}
		});
		panel_2.add(ele);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new GridLayout(1,2));
		panel_3.setOpaque(false);
		contenuto.add(panel_3);
		Etichetta ttp=new Etichetta("Price:");
		panel_3.add(ttp);
		
		FormVuoto tf1 = new FormVuoto("prezzo");
		JPanel ff=new JPanel();
		ff.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) ff.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setAlignOnBaseline(true);
		ff.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
		ff.add(tf1);
		panel_3.add(ff);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new GridLayout(1,3));
		panel_4.setOpaque(false);
		contenuto.add(panel_4);
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Home omeh=new Home();
		    	omeh.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		panel_4.add(bex);
		Bottone bent=new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (codice>-1){
		    		costo=Double.parseDouble(tf1.ret);
		    		try {
		    			Main.db.assMerc(indice,codice,costo);
		    		} catch (SQLException ex) { ex.printStackTrace(); }

			    	ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
		    	else {
		    		Errore err=new Errore("Index not valid... ");
		    		err.setVisible(true);
		    	}
			}
		});
		panel_4.add(bent);
		
		
		Bottone dis=new Bottone("REMOVE", 5);
		dis.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (codice>-1){
			    	Errore del=new Errore(codice, indice, AssegnaMerc.this);
			    	del.setVisible(true);
			    	setVisible(false);
		    	}
			}
		});
		
		panel_4.add(dis);
		pack();
	}
	
	// merce x
	public AssegnaMerc(int x, String a){
		super("Assign Product to Supplier");
		codice=x;
		nomeMer=Main.db.getMerName(x);
		
		JPanel contenuto=new JPanel();
		contenuto.setLayout(new GridLayout(4,1));
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		c.add("Center",contenuto);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(1,2));
		panel_1.setOpaque(false);
		contenuto.add(panel_1);
		Etichetta nom=new Etichetta("Product: ");
		panel_1.add(nom);
		Etichetta nome=new Etichetta(nomeMer);
		panel_1.add(nome);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new GridLayout(1,2));
		panel_2.setOpaque(false);
		contenuto.add(panel_2);
		

		Etichetta tt=new Etichetta("Choose Supplier:");
		panel_2.add(tt);
		Choice ele=new Choice();
		ele.add("Choose");
		try{
			ResultSet xx=Main.db.getElenForn();
			while (xx.next()) {
				ele.add(xx.getString(1)+", "+xx.getString(2)+", "+xx.getString(3));
			}
		}
		catch (SQLException e){	e.printStackTrace();	}
		ele.setFont(Est.plainFont);
		ele.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (ele.getSelectedItem().equals("Choose")||ele.getSelectedItem().equals("Empty")){
				}
				else {
					String temp=ele.getSelectedItem();
					String[] temAr=temp.split(", ");
					indice=Integer.parseInt(temAr[0]);
					
				}
			}
		});
		panel_2.add(ele);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new GridLayout(1,2));
		panel_3.setOpaque(false);
		contenuto.add(panel_3);
		Etichetta ttp=new Etichetta("Price:");
		panel_3.add(ttp);
		
		
		FormVuoto tf1 = new FormVuoto("prezzo");
		JPanel ff=new JPanel();
		ff.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) ff.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setAlignOnBaseline(true);
		ff.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
		ff.add(tf1);
		panel_3.add(ff);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new GridLayout(1,3));
		panel_4.setOpaque(false);
		contenuto.add(panel_4);
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaP=new ConsultaMerci();
		    	consultaP.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		panel_4.add(bex);
		Bottone bent=new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (codice>-1){
		    		costo=Double.parseDouble(tf1.ret);
		    		try {
		    			Main.db.assMerc(indice,codice,costo);
		    		} catch (SQLException ex) { ex.printStackTrace(); }

			    	ConsultaMerci consultaP=new ConsultaMerci();
			    	consultaP.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
		    	else {
		    		Errore err=new Errore("Index not valid... ");
		    		err.setVisible(true);
		    	}
			}
		});
		panel_4.add(bent);
		
		
		Bottone dis=new Bottone("REMOVE", 5);
		dis.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (codice>-1){
			    	Errore del=new Errore(codice, indice, AssegnaMerc.this);
			    	del.setVisible(true);
			    	setVisible(false);
		    	}
			}
		});
		
		panel_4.add(dis);
		pack();
	}
}
