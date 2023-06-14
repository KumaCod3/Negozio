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
	
	
	
	public ConsultaTrans(String s) {
		super("Find "+s);
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(2,1));
		c.add("Center", contenuto);
		JPanel centro=new JPanel();
		centro.setBorder(Est.bordo);
		centro.setOpaque(false);
		centro.setLayout(new GridLayout(6,3));
		contenuto.add(centro);
		// 5 righe con etic, campo e bottone
// prima
		Etichetta datI=new Etichetta("Dates between: ");
		centro.add(datI);
		datIform = new FormVuoto(""+Est.simpDate.format(dataIN));
		centro.add(datIform);
		Etichetta vuo=new Etichetta("     ");
		centro.add(vuo);
// seconda
		Etichetta datF=new Etichetta("and: ");
		centro.add(datF);
		datFform = new FormVuoto(""+Est.simpDate.format(dataFI));
		centro.add(datFform);
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
					ResultSet sor=Main.db.getVendite(dataIN, dataFI);
					while (sor.next()) {		
						String fin=sor.getString("ID_VENDITA")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		centro.add(bDat);
// terza
		Etichetta priI=new Etichetta("Price between: ");
		centro.add(priI);
		priIform = new FormVuoto(""+Est.deci.format(priceI));
		centro.add(priIform);
		Etichetta vuot=new Etichetta("     ");
		centro.add(vuot);
// quarta
		Etichetta priF=new Etichetta("and: ");
		centro.add(priF);
		priFform = new FormVuoto(""+Est.deci.format(priceF));
		centro.add(priFform);
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
					ResultSet sor=Main.db.getVendite(priceI, priceF);
					while (sor.next()) {		
						String fin=sor.getString("ID_VENDITA")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		centro.add(bPri);
// quinta
		Etichetta idp=new Etichetta("Personal ID ");
		centro.add(idp);
		idPform = new FormVuoto(""+Est.sco.format(pID));
		centro.add(idPform);
		bIdp=new Bottone("Find ID");
		bIdp.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		pID=Integer.parseInt(idPform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=Main.db.getVendite(pID);
					while (sor.next()) {		
						String fin=sor.getString("ID_VENDITA")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		centro.add(bIdp);
// sesta
		Etichetta idt=new Etichetta("Transaction ID");
		centro.add(idt);
		idTform = new FormVuoto(""+Est.sco.format(tID));
		centro.add(idTform);
		bIdt=new Bottone("Find ID");
		bIdt.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		tID=Integer.parseInt(idTform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=Main.db.getVendite(tID, "ciao");
					while (sor.next()) {		
						String fin=sor.getString("ID_VENDITA")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		centro.add(bIdt);
		
		tab=new StorTab(elenco);
		contenuto.add(tab.ta());
		
		JPanel foot=new JPanel();
		foot.setBorder(Est.bordo);
		foot.setOpaque(false);
		foot.setLayout(new GridLayout(1,2));
		c.add("South", foot);
		Bottone opn=new Bottone("Open Transaction");
		opn.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// TODO
			}
		});
		foot.add(opn);
		Bottone bk=new Bottone("Back");
		bk.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// TODO
			}
		});
		foot.add(bk);
		
		pack();
	}

	public ConsultaTrans(String s, int x) {
		this(s);

		bDat.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		dataIN=LocalDateTime.parse(datIform.ret+" 00:00.00", Est.simpDateHH);
		    		dataFI=LocalDateTime.parse(datFform.ret+" 23:55.00", Est.simpDateHH);
		    	} catch (DateTimeParseException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=Main.db.getAcquisti(dataIN, dataFI);
					while (sor.next()) {		
						String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		bPri.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		priceI=Double.parseDouble(priIform.ret);
		    		priceF=Double.parseDouble(priFform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=Main.db.getAcquisti(priceI, priceF);
					while (sor.next()) {		
						String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		bIdp.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		pID=Integer.parseInt(idPform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=Main.db.getAcquisti(pID);
					while (sor.next()) {		
						String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
		bIdt.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		tID=Integer.parseInt(idTform.ret);
		    	} catch (NumberFormatException ex) { Errore er=new Errore("Wrong format"); er.setVisible(true);}
		    	elenco.clear();
		    	tab.repaint(elenco);
		    	try {
					ResultSet sor=Main.db.getAcquisti(tID, "ciao");
					while (sor.next()) {		
						String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
						elenco.add(fin);
					}
				} catch (SQLException ex) { ex.printStackTrace(); }
		    	tab.repaint(elenco);
			}
		});
	}
	
}
