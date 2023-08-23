package GUI;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JPanel;

import Negozio.MyDB;

public class ConsultaTrans extends Finestra{
	int pID;
	int tID;
	LocalDateTime dataFI=LocalDateTime.now();
	LocalDateTime dataIN=dataFI.minusMonths(1);
	double priceI;
	double priceF;
	ArrayList<String> elenco=new ArrayList<String>();
	StorTab tab;
	
	FormVuoto datIform;
	FormVuoto datFform;
	Bottone bDat;
	FormVuoto priIform;
	FormVuoto priFform;
	Bottone bPri;
	FormVuoto idTform;
	Bottone bIdp;
	FormVuoto idPform;
	Bottone bIdt;
	Bottone opn;
	
	
	public ConsultaTrans(String s) {	// vendite
		super("Find "+s);
		
		JPanel centro=new JPanel();
		centro.setBorder(Est.bordo);
		centro.setOpaque(false);
		centro.setLayout(new GridLayout(7,1));
		c.add("Center", centro);
// prima
		
		JPanel primo=new JPanel();
		primo.setBorder(Est.bordo);
		primo.setOpaque(false);
		primo.setLayout(new GridLayout(1,3));
		centro.add(primo);
		Etichetta datI=new Etichetta("Dates between: ");
		primo.add(datI);
		datIform = new FormVuoto(""+Est.simpDate.format(dataIN));
		primo.add(datIform);
		Etichetta vuo=new Etichetta("     ");
		primo.add(vuo);
// seconda
		JPanel secondo=new JPanel();
		secondo.setBorder(Est.bordo);
		secondo.setOpaque(false);
		secondo.setLayout(new GridLayout(1,3));
		centro.add(secondo);
		Etichetta datF=new Etichetta("and: ");
		secondo.add(datF);
		datFform = new FormVuoto(""+Est.simpDate.format(dataFI));
		secondo.add(datFform);
		bDat=new Bottone("Find dates");
		bDat.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		dataIN=LocalDateTime.parse(datIform.ret+" 00:00.00", Est.simpDateHH);
		    		dataFI=LocalDateTime.parse(datFform.ret+" 23:55.00", Est.simpDateHH);
		    	} catch (DateTimeParseException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=MyDB.getVendite(dataIN, dataFI);
					while (sor.next()) {		
						String fin=sor.getString("ID_VENDITA")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		secondo.add(bDat);
// terza
		JPanel terzo=new JPanel();
		terzo.setBorder(Est.bordo);
		terzo.setOpaque(false);
		terzo.setLayout(new GridLayout(1,3));
		centro.add(terzo);
		Etichetta priI=new Etichetta("Price between: ");
		terzo.add(priI);
		priIform = new FormVuoto(""+Est.deci.format(priceI));
		terzo.add(priIform);
		Etichetta vuot=new Etichetta("     ");
		terzo.add(vuot);
// quarta
		JPanel quarto=new JPanel();
		quarto.setBorder(Est.bordo);
		quarto.setOpaque(false);
		quarto.setLayout(new GridLayout(1,3));
		centro.add(quarto);
		Etichetta priF=new Etichetta("and: ");
		quarto.add(priF);
		priFform = new FormVuoto(""+Est.deci.format(priceF));
		quarto.add(priFform);
		bPri=new Bottone("Find price");
		bPri.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		priceI=Double.parseDouble(priIform.ret);
		    		priceF=Double.parseDouble(priFform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=MyDB.getVendite(priceI, priceF);
					while (sor.next()) {		
						String fin=sor.getString("ID_VENDITA")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		quarto.add(bPri);
// quinta
		JPanel quinto=new JPanel();
		quinto.setBorder(Est.bordo);
		quinto.setOpaque(false);
		quinto.setLayout(new GridLayout(1,3));
		centro.add(quinto);
		Etichetta idp=new Etichetta("Personal ID ");
		quinto.add(idp);
		idPform = new FormVuoto(""+Est.sco.format(pID));
		quinto.add(idPform);
		bIdp=new Bottone("Find ID");
		bIdp.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		pID=Integer.parseInt(idPform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=MyDB.getVendite(pID);
					while (sor.next()) {		
						String fin=sor.getString("ID_VENDITA")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		quinto.add(bIdp);
// sesta
		JPanel sesto=new JPanel();
		sesto.setBorder(Est.bordo);
		sesto.setOpaque(false);
		sesto.setLayout(new GridLayout(1,3));
		centro.add(sesto);
		Etichetta idt=new Etichetta("Transaction ID");
		sesto.add(idt);
		idTform = new FormVuoto(""+Est.sco.format(tID));
		sesto.add(idTform);
		bIdt=new Bottone("Find ID");
		bIdt.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		tID=Integer.parseInt(idTform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=MyDB.getVendite(tID, "ciao");
					while (sor.next()) {		
						String fin=sor.getString("ID_VENDITA")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		sesto.add(bIdt);
//	settima
		JPanel settimo=new JPanel();
		settimo.setBorder(Est.bordo);
		settimo.setOpaque(false);
		settimo.setLayout(new GridLayout(1,2));
		centro.add(settimo);
		opn=new Bottone("Open Transaction");
		opn.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (tab.getIndex()>-1) {
		    		Transazione tra=new Transazione(tab.getNumber(), ConsultaTrans.this);
		    		tra.setVisible(true);
		    		setVisible(false);
		    	}
			}
		});
		settimo.add(opn);
		Bottone bk=new Bottone("Back");
		bk.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Storico st=new Storico();
		    	st.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		settimo.add(bk);

// footer
		tab=new StorTab(elenco);
		c.add("South", tab.ta());
		
		
		pack();
	}

	public ConsultaTrans(String s, int x) {	// acquisti
		this(s);
		bDat.but.removeActionListener(bDat.but.getActionListeners()[0]);
		bDat.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		dataIN=LocalDateTime.parse(datIform.ret+" 00:00.00", Est.simpDateHH);
		    		dataFI=LocalDateTime.parse(datFform.ret+" 23:55.00", Est.simpDateHH);
		    	} catch (DateTimeParseException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=MyDB.getAcquisti(dataIN, dataFI);
					while (sor.next()) {		
						String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		
		bPri.but.removeActionListener(bPri.but.getActionListeners()[0]);
		bPri.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		priceI=Double.parseDouble(priIform.ret);
		    		priceF=Double.parseDouble(priFform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=MyDB.getAcquisti(priceI, priceF);
					while (sor.next()) {		
						String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		
		bIdp.but.removeActionListener(bIdp.but.getActionListeners()[0]);
		bIdp.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		pID=Integer.parseInt(idPform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=MyDB.getAcquisti(pID);
					while (sor.next()) {		
						String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		
		bIdt.but.removeActionListener(bIdt.but.getActionListeners()[0]);
		bIdt.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		tID=Integer.parseInt(idTform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=MyDB.getAcquisti(tID, "ciao");
					while (sor.next()) {		
						String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		
		opn.but.removeActionListener(opn.but.getActionListeners()[0]);
		opn.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (tab.getIndex()>-1) {
		    		Transazione tra=new Transazione(tab.getNumber(), ConsultaTrans.this, "acquisti");
		    		tra.setVisible(true);
		    		setVisible(false);
		    	}
			}
		});
		pack();
	}
}
