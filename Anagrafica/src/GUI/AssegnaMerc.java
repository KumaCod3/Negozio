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
//	Double prezzo;
	
	public AssegnaMerc(int x){
		super("Assign Product to Supplier"/*,870,570*/);
		codice=x;
		try {
			nomeForn=Main.db.getForName(x);
		} catch (SQLException ex) { ex.printStackTrace(); }

		JPanel contenuto=new JPanel();
		contenuto.setLayout(new GridLayout(3,2));
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		c.add("Center",contenuto);
		
		Etichetta nom=new Etichetta("Supplier: ");
		contenuto.add(nom);
		Etichetta nome=new Etichetta(nomeForn);
		contenuto.add(nome);
		
		Etichetta tt=new Etichetta("Choose Product:");
		contenuto.add(tt);
		Choice ele=new Choice();
		ele.add("Choose");
//		try{
//			for (Merce a:DataM.elenco.values()){
//				ele.add(a.getNome()+" "+a.getCod());
//			}
//		}
//		catch (Exception e){
//			ele.add("Empty");
//		}
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
		contenuto.add(ele);
		
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		contenuto.add(bex);
		Bottone bent=new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
		    		System.out.println("indice= "+index);
					System.out.println("forn= "+codice);
//			    	f.addMerc(index);
		    		try {
// SEGNAPOSTOOOOO
		    			Main.db.assMerc(codice,index,0.0);
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
		contenuto.add(bent);
		Bottone dis=new Bottone("REMOVE", 5);
		dis.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
// SEGNAPOSTOOOOOO
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
		try {
			nomeMer=Main.db.getMerName(x);
		} catch (SQLException ex) { ex.printStackTrace(); }
		Panel tit=new Panel();
		tit.setLayout(new GridLayout(1,2));
		Etichetta nom=new Etichetta("Product: ");
		tit.add(nom);
		Etichetta nim=new Etichetta(nomeMer);
		tit.add(nim);
		
		c.add("North",tit);	
		
		Panel contenuto=new Panel();
		contenuto.setLayout(new GridLayout(2,2));
		c.add("Center",contenuto);
		
		Etichetta tt=new Etichetta("Choose Supplier:");
		contenuto.add(tt);
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
		contenuto.add(ele1);
		
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		contenuto.add(bex);
		Bottone bent=new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
//		    		DataB.fornitori.get(index).addMerc(m);
//			    	m.addForn(DataB.fornitori.get(index));
			    	
		    		try {
// SEGNAPOSTOOOOOO		    			
		    			
		    			Main.db.assMerc(codice,index,0.0);
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
		contenuto.add(bent);
		
		Bottone dis=new Bottone("REMOVE", 5);
		dis.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
// SEGNAPOSTOOOO
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
