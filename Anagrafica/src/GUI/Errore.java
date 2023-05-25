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
		setLocation(Est.marginX,Est.marginY);
		setBackground(Est.sfondo);
		setUndecorated(true);
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
	
	public Errore(String s,SchedaMerce a){	// elimina merce
		this();
		tx.setText(s);

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// TODO fare azione ok (ELIMINA LA MERCE)
		    	a.dispose();
		    	setVisible(false);
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
		    	a.dispose();
		    	setVisible(false);
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

	public Errore(ListaSpesa list, Spesa a) {	// concludi spesa
		this();
		double price=0.0;
		tx.setText("<html>Do you want to procede to <br/> check out?: "+Est.deci.format(price)+" eu.");

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// TODO fare azione ok (acquista)
		    	a.dispose();
		    	setVisible(false);
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
	
	
	public Errore(int ind, SchedaMerce t){		// scelta forn x ordine merch
		this();
		tx.setText("From which supplier you want to purchase the product?");

		try {
			/*comp2*/MyChoice ele=new MyChoice(Main.db.getElenSuppF(ind),5);
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
	

}		
