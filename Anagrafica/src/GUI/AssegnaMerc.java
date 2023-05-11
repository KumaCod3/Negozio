package GUI;
import Negozio.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssegnaMerc extends Finestra{
	int index;
	int codice;
	String nomeForn="";
	String nomeMer="";
	Double costo=0.0;
	
	public AssegnaMerc(int x){
		super("Assign Product to Supplier"/*,870,570*/);
		codice=x;
		nomeForn=Main.db.getForName(x);

		JPanel contenuto=new JPanel();
		contenuto.setLayout(new GridLayout(4,1));
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		c.add("Center",contenuto);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		panel_1.setOpaque(false);
		contenuto.add(panel_1);
		Etichetta nom=new Etichetta("Supplier: ");
		panel_1.add(nom);
		Etichetta nome=new Etichetta(nomeForn);
		panel_1.add(nome);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(-20);
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
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
					index=Integer.parseInt(temAr[0]);
					
				}
			}
		});
		panel_2.add(ele);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		panel_3.setOpaque(false);
		contenuto.add(panel_3);
		Etichetta ttp=new Etichetta("Price:");
		panel_3.add(ttp);
		FormVuoto tf1 = new FormVuoto("prezzo");
		panel_3.add(tf1);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setAlignment(FlowLayout.TRAILING);
		panel_4.setOpaque(false);
		contenuto.add(panel_4);
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		panel_4.add(bex);
		Bottone bent=new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
		    		costo=Double.parseDouble(tf1.ret);
//			    	f.addMerc(index);
		    		try {
		    			Main.db.assMerc(codice,index,costo);
		    		} catch (SQLException ex) { ex.printStackTrace(); }

//			    	DataM.get(index).addForn(f);
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
		    	if (index>-1){
		    		// TODO
//			    	Errore del=new Errore(f,index);
//			    	del.setVisible(true);
//			    	setVisible(false);
//			    	dispose();
		    	}
			}
		});
		
		c.add("South",dis);
		pack();
	}
	
	public AssegnaMerc(int x, String a){
		super("Assign Product to Supplier"/*, 615, 550*/);
		index=x;
		nomeMer=Main.db.getMerName(x);
		Panel tit=new Panel();
		tit.setLayout(new GridLayout(1,2));
		Etichetta nom=new Etichetta("Product: ");
		tit.add(nom);
		Etichetta nim=new Etichetta(nomeMer);
		tit.add(nim);
		
		c.add("North",tit);	
		
		JPanel contenuto=new JPanel();
		contenuto.setLayout(new GridLayout(3,1));
		c.add("Center",contenuto);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		panel_1.setOpaque(false);
		Etichetta tt=new Etichetta("Choose Supplier:");
		panel_1.add(tt);
		Choice ele1=new Choice();
		ele1.add("Choose");
		try{
			ResultSet xx=Main.db.getElenForn();
			while (xx.next()) {
				ele1.add(xx.getString(1)+", "+xx.getString(2)+", "+xx.getString(3));
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
					String[] temp=ele1.getSelectedItem().split(", ");
					index=Integer.parseInt(temp[0]);
				}
			}
		});
		panel_1.add(ele1);
		contenuto.add(panel_1);
		
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(-20);
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		panel_2.setOpaque(false);
		contenuto.add(panel_2);
		Etichetta ttp=new Etichetta("Price:");
		panel_2.add(ttp);
		FormVuoto tf1 = new FormVuoto("prezzo");
		panel_2.add(tf1);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		panel_3.setOpaque(false);
		contenuto.add(panel_3);
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		panel_3.add(bex);
		Bottone bent=new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
//		    		DataB.fornitori.get(index).addMerc(m);
//			    	m.addForn(DataB.fornitori.get(index));
		    		costo=Double.parseDouble(tf1.ret);
		    		try {
		    			Main.db.assMerc(codice,index,costo);
		    		} catch (SQLException ex) { ex.printStackTrace(); }
		    		
			    	ConsultaMerci consultaM=new ConsultaMerci();
			    	consultaM.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
		    	else {
		    		Errore err=new Errore("Index not valid...");
		    		err.setVisible(true);
		    	}
			}
		});
		panel_3.add(bent);
		
		
		
		Bottone dis=new Bottone("REMOVE", 5);
		dis.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
		    		// TODO
//			    	Errore del=new Errore(m,index);
//			    	del.setVisible(true);
//			    	setVisible(false);
//			    	dispose();
		    		
		    	}
			}
		});
		
		c.add("South",dis);
		pack();
	}
}
