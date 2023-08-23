package GUI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JPanel;

import Negozio.MyDB;

public class Storico extends Finestra{
	public Storico(){
		super("History: ");

		JPanel centro=new JPanel();
		centro.setOpaque(false);
		centro.setLayout(new GridLayout(1,2));
		c.add("Center",centro);
		
// sinistro
		JPanel sinistro=new JPanel();
		sinistro.setOpaque(false);
		sinistro.setLayout(new BorderLayout());
		Etichetta non=new Etichetta("Sales History: ");
		sinistro.add("North",non);
		
		ArrayList<String> elenco=new ArrayList<String>();
		LocalDateTime dataFI=LocalDateTime.now();
		LocalDateTime dataIN=dataFI.minusMonths(1);
		try {
			ResultSet sor=MyDB.getVendite(dataIN, dataFI);
			while (sor.next()) {		
				String fin=sor.getString("ID_VENDITA")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
				elenco.add(fin);
			}
		} catch (SQLException e) { e.printStackTrace(); }

		StorTab tab=new StorTab(elenco);
		sinistro.add("Center",tab.ta());
		Bottone bexSX=new Bottone("Find Sale");
		bexSX.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaTrans xx=new ConsultaTrans("Sale");
		    	xx.setVisible(true);
				setVisible(false);
		    	dispose();
			}
		});
		sinistro.add("South",bexSX);
				
// destro
		JPanel destro=new JPanel();
		destro.setOpaque(false);
		destro.setLayout(new BorderLayout());
		Etichetta nomm=new Etichetta("Purchase History: ");
		destro.add("North",nomm);

		ArrayList<String> elenco2=new ArrayList<String>();
		try {
			ResultSet sor=MyDB.getAcquisti(dataIN, dataFI);
			while (sor.next()) {		
				String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
				elenco2.add(fin);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		StorTab tabb=new StorTab(elenco2);
		destro.add("Center",tabb.ta());
		Bottone bexDX=new Bottone("Find Purchase");
		bexDX.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaTrans xx=new ConsultaTrans("Purchase",3);
		    	xx.setVisible(true);
				setVisible(false);
		    	dispose();
			}
		});
		destro.add("South",bexDX);
		
		centro.add(sinistro);
		centro.add(destro);
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Home home=new Home();
				home.setVisible(true);
				setVisible(false);
		    	dispose();
			}
		});
		c.add("South",bex);
		pack();
	}
}