package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Transazione extends Finestra{
	
	ArrayList<String> elenco=new ArrayList<String>();
	String nomeP;
	Double prezzoF;
	String dat;
	TranTab tab;
	
	public Transazione(int index, ConsultaTrans a) {	// vendite
		super("Transaction details:");
		
		try {
			ResultSet sor=Main.db.getVendite(index,"ciao");
			while (sor.next()) {
				nomeP=sor.getString("name");
				prezzoF=Double.parseDouble(sor.getString("price"));
				dat=sor.getString("Moment");
			}
		} catch (SQLException ex) { ex.printStackTrace(); }
		
		
    	try {
			ResultSet sor=Main.db.getVendita(index);
			while (sor.next()) {		
				String fin=sor.getString("ID_MERCE")+", "+sor.getString("Nome")+", "+sor.getString("Quantity")+", "+sor.getString("Price");
				elenco.add(fin);
			}
		} catch (SQLException ex) { ex.printStackTrace(); }
		
    	Etichetta intest=new Etichetta("Transaction of the: "+dat+" to: "+nomeP+" Tot.: "+prezzoF);
    	c.add("North", intest);
    	
    	tab=new TranTab(elenco);
		c.add("Center", tab.ta());
		
		Bottone bk=new Bottone("Back");
		bk.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	a.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		c.add("South", bk);
		
		pack();
	}
	
	
	public Transazione(int index, ConsultaTrans a, String ciao) { // acquisti
		super("Transaction details:");
		
		try {
			ResultSet sor=Main.db.getAcquisti(index,"ciao");
			while (sor.next()) {
				nomeP=sor.getString("name");
				prezzoF=Double.parseDouble(sor.getString("price"));
				dat=sor.getString("Moment");
			}
		} catch (SQLException ex) { ex.printStackTrace(); }
		
		try {
			ResultSet sor=Main.db.getAcquisto(index);
			while (sor.next()) {		
				String fin=sor.getString("ID_MERCE")+", "+sor.getString("Nome")+", "+sor.getString("Quantity")+", "+sor.getString("Price");
				elenco.add(fin);
			}
		} catch (SQLException ex) { ex.printStackTrace(); }
		
		
		Etichetta intest=new Etichetta("Transaction of the: "+dat+" to: "+nomeP+" Tot.: "+prezzoF);
    	c.add("North", intest);
    	
    	tab=new TranTab(elenco);
		c.add("Center", tab.ta());
		
		Bottone bk=new Bottone("Back");
		bk.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	a.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		c.add("South", bk);
		
		pack();
	}
}
