package GUI;
import Negozio.ListaSpesa;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.sql.SQLException;

public class Errore extends Frame {
	JLabel tx;
	public Bottone ok;
	Bottone ty;
	JPanel c;
	public double qtt;
	public double qttMax;
	public int index=-1;
	public FormVuoto tf1;
	
	public Errore(){
		super("---ERROR---");
		setBackground(Est.sfondo);
		setUndecorated(true);
//		setLocation(Est.centX-((int)(getSize().getWidth())),Est.centY-((int)(getSize().getHeight())));
		setLocation(500,500);
		c = new JPanel();
		c.setBorder(Est.borColTut);
		c.setOpaque(false);
		add("Center",c);
		
		tx=new JLabel();
		tx.setAlignmentX(Component.CENTER_ALIGNMENT);
		tx.setText("I'm sorry, something went wrong ... ");
		tx.setBorder(Est.bordo);
		tx.setFont(Est.plainFont);
		
		ok=new Bottone("OK", 5);
		
		ty=new Bottone("CANCEL", 5);
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		c.add(tx);
		c.add(ok);
		c.add(ty);
		setAlwaysOnTop(true);
//		pack();
	}
	
	public Errore(String a){
		this();
		// da AggiungiMerce e AggiungiPersona
		tx.setText(a);

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	dispose();
			}
		});
		
		remove(ty);
		ty.but.setText(a);
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	dispose();
			}
		});
		ty.setVisible(false);

		pack();
	}
	
	public Errore(SchedaMerce a){	// elimina merce
		this();
		String s="<html>Are you sure you want to delete this product? <br/> Product: "+a.nome;
		tx.setText(s);

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	a.dispose();
		    	setVisible(false);
		    	try {
		    		Main.db.elimMerc(a.index);
		    	} catch (SQLException ee) { ee.printStackTrace();}
		    	
		    	
		    	
		    	ConsultaMerci mc=new ConsultaMerci();
		    	mc.setVisible(true);
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	a.setVisible(true);
		    	dispose();
			}
		});
		pack();
	}

	public Errore(String s, Spesa a) {	// annulla spesa
		this();
		tx.setText(s);

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	a.list.annulla();
		    	a.dispose();
		    	setVisible(false);
		    	Home cp=new Home();
		    	cp.setVisible(true);
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	a.setVisible(true);
		    	dispose();
			}
		});
		pack();
	}

	public Errore(ListaSpesa list, Spesa a) {	// concludi spesa
		this();
		double price=list.getSaldo();
		tx.setText("<html>Do you want to procede to <br/> check out?: "+Est.deci.format(price)+" eu.");

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	a.dispose();
		    	setVisible(false);
		    	list.concludi();
		    	Home hh=new Home();
		    	hh.setVisible(true);
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	a.setVisible(true);
		    	dispose();
			}
		});
		pack();
	}

	public Errore(ListaSpesa l, int merce) {	// non abbastanza merc
		this();
		
		qtt=0.0;
		qttMax=Main.db.getQuantMerc(merce);
		tx.setText("<html>Not enough in store, available: "+qttMax+"<br/> put in cart: ");

		c.remove(ty);
		tf1 = new FormVuoto(""+qttMax);
		c.add(tf1);
		
		pack();
		
	}
	
	public Errore(int ind, SchedaMerce t) {		// scelta forn x ordine merch
		this();
		tx.setText("From which supplier you want to purchase the product?");

		try {
			MyChoice ele=new MyChoice(Main.db.getElenSuppF(ind),5);
					ele.jList.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent e) {
							try {
								String[] temp=ele.getSel().split(", ");
								index=Integer.parseInt(temp[0]);
							}
							catch (Exception ex){
								// no selection
							}
							
						}
					});
					c.add(ele);
		} catch (SQLException e) { e.printStackTrace();}

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	t.dispose();
		    	Spesa sp=new Spesa(index,ind);
		    	sp.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	t.setVisible(true);
		    	dispose();
			}
		});
		pack();
	}
	public Errore(int ind, ConsultaMerci t) {		// scelta forn x ordine merch
		this();
		tx.setText("From which supplier you want to purchase the product?");

		try {
			MyChoice ele=new MyChoice(Main.db.getElenSuppF(ind),5);
					ele.jList.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent e) {
							try {
								String[] temp=ele.getSel().split(", ");
								index=Integer.parseInt(temp[0]);
							}
							catch (Exception ex){
								// no selection
							}
							
						}
					});
					c.add(ele);
		} catch (SQLException e) { e.printStackTrace();}

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	t.dispose();
		    	Spesa sp=new Spesa(index,ind);
		    	sp.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	t.setVisible(true);
		    	dispose();
			}
		});
		pack();
	}

	public Errore(int index2, int codice, double price, Spesa a, double qt) {	// concludi spesa fornit
		this();
		tx.setText("<html>Do you want to procede to <br/> check out?: "+Est.deci.format(price)+" eu.");

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	a.dispose();
		    	setVisible(false);
		    	//list.concludi();
		    	try {
		    		int IDtrans=Main.db.createTransactionOu(index2, price);
		    		Main.db.compra(codice, qt,price, IDtrans);
		    		Main.db.aggiornaSaldoFor(index2, price);
		    	} catch (SQLException ex) { ex.printStackTrace();}
		    	
		    	Home hh=new Home();
		    	hh.setVisible(true);
		    	a.dispose();
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	a.setVisible(true);
		    	dispose();
			}
		});
		pack();
	}
	

	public Errore(int codice, SchedaPersona t) {	// scelta merce x ordine forn
		this();
		tx.setText("Which product you want to purchase form this supplyer?");

		try {
				MyChoice ele=new MyChoice(Main.db.getElenSuppM(codice), "ciao");
				ele.jList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						try {
							String[] temp=ele.getSel().split(", ");
							index=Integer.parseInt(temp[0]);
						}
						catch (Exception ex){
							ex.printStackTrace();
						}
						
					}
				});
				c.add(ele);
		} catch (SQLException e) { e.printStackTrace();}

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	t.dispose();
		    	Spesa sp=new Spesa(codice, index);
		    	sp.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	t.setVisible(true);
		    	dispose();
			}
		});
		pack();
	}

	public Errore(int codice, int indice, AssegnaMerc a) {		// elimina rapporto fornitore/merce
		this();
		String mer=Main.db.getMerName(codice);
		String sup=Main.db.getForName(indice);
		
		tx.setText("Remove "+mer+" from supplier "+sup+"?");

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	a.dispose();
		    	setVisible(false);
	//	    	removeee();
		    	Main.db.removeForn(codice,indice);
		    	ConsultaPersone cp=new ConsultaPersone();
		    	cp.setVisible(true);
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	a.setVisible(true);
		    	dispose();
			}
		});
		pack();
	}


}		
