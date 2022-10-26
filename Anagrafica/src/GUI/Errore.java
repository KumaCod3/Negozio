package GUI;
import Negozio.Cliente;
import Negozio.DataB;
import Negozio.DataM;
import Negozio.Fornitore;
import Negozio.ListaSpesa;
import Negozio.Merce;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Errore extends Frame{
	JLabel tx;
	Bottone ok;
	Bottone ty;
	
	public Errore(){
		super("---ERROR---");
		setLayout(new GridLayout(3,1));
		setLocation(300,300);
		setBackground(Est.chiaro);
		addWindowListener(Finestra.close);
		
		tx=new JLabel();
		tx.setText("I'm sorry, something went wrong ... ");
		tx.setBorder(Est.eti);
		tx.setFont(Est.font);
		
		ok=new Bottone("OK", 5);
		
		ty=new Bottone("CANCEL", 5);
		ty.but.setBackground(Est.rosso);
		
		add(tx);
		add(ok);
		add(ty);
		setAlwaysOnTop(true);
	}
	public Errore(String a){
		this();
		addWindowListener(Finestra.close);
		// da AggiungiMerce e AggiungiPersona
		tx.setText("I'm sorry, something went wrong ... ");

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	dispose();
			}
		});
		
		ty.but.setText(a);
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	dispose();
			}
		});

		pack();
	}
	
	public Errore(int a){
		this();
		//da SchedaMerce 
		tx.setText("<html>Are you sure you want to delete this product? <br/> Number:"+a+" Product: "+DataM.elenco.get(a).getNome());

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	DataM.elimina(a);
		    	setVisible(false);
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	dispose();
			}
		});

		pack();
	}
	
	public Errore(Spesa x){
		this();
		// da Spesa
		boolean gst=x.b.getNome().equals("GUEST");

		tx.setText("<html>Are you sure you want to go out? <br/> The contents of the shopping cart will be lost.");

		ok.but.setText("NO, go back!");
		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	x.setVisible(true);
		    	dispose();
			}
		});

		ty.but.setText("DELETE ORDER");
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	x.dispose();
		    	setVisible(false);
		    	if (!gst){
		    		ConsultaPersone consultaP=new ConsultaPersone();
		    		consultaP.setVisible(true);
		    	}
		    	else if (gst){
		    		Home home=new Home();
					home.setVisible(true);
		    	}
		    	dispose();
			}
		});

		pack();
	}
	
	public Errore(Spesa x,Cliente c){
		this();
		// da Spesa
		tx.setText("<html>Are you sure you want to place the order? <br/>"+c.getIntestazione()+" tot: "+Est.deci.format(x.list.getSaldo()));

		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	x.setVisible(true);
		    	dispose();
			}
		});
		
		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	setVisible(false);
		    	x.list.concludi();
		    	MyReadL.scarica(x.list);
		    	MyReadM.scarica();
    			MyReadF.scarica();
    			MyReadC.scarica();
		    	x.tab.clear();
		    	
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	x.dispose();
		    	dispose();
			}
		});

		pack();
	}
	
	public Errore(Merce m,int index){
		this();
		// da AssegnaMerce
		Fornitore f=DataB.fornitori.get(index);

		tx.setText("<html>Are you sure you will delete the supplier <br/> "+f.getIntestazione()+"for the product: "+m.getNome()+"?");

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	dispose();
			}
		});

		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	m.removeForn(f);
		    	f.removeMerc(m.getCod());
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	dispose();
			}
		});

		pack();
	}
	
	public Errore(Merce m,ListaSpesa x, Spesa spes){
		this();
		// da ListaSpesa
		double max=DataM.get(m.getCod()).getQuantita();

		tx.setText("<html>Product is Out of Order! <br/> "+"You have only: "+max+m.getUnit()+" of "+m.getNome()+" left.");

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	m.setQuantita(DataM.elenco.get(m.getCod()).getQuantita());
		    	spes.refre();
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	m.setQuantita(0.0);
		    	dispose();
			}
		});

		pack();
	}
	
	public Errore(Fornitore f,int index){
		this();
		// da AssegnaMerce 
		Merce m=DataM.get(index);

		tx.setText("<html>Are you sure you will delete the supplier <br/> "+f.getIntestazione()+"for the product: "+m.getNome()+"?");

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	dispose();
			}
		});

		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	m.removeForn(f);
		    	f.removeMerc(m.getCod());
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	dispose();
			}
		});

		pack();
	}
	
	public Errore(Fornitore f,Merce m, double quantita){
		this();
		// da Spesa
		double saldo=m.getPrezzoA()*quantita;

		tx.setText("<html>Do you want to place the order from "+f.getIntestazione()+"?");
		
		Panel sal=new Panel();
		sal.setLayout(new GridLayout(1,3));
		Etichetta sal1=new Etichetta(" Order: "+quantita+" "+m.getUnit());
		sal.add(sal1);
		Etichetta sal2=new Etichetta("of "+m.getNome());
		sal.add(sal2);
		Etichetta sal3=new Etichetta("for: "+Est.deci.format(saldo)+" eu. ?");
		sal.add(sal3);
		
		
		Panel tasti=new Panel();
		tasti.setLayout(new GridLayout(1,2));
		
		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	f.ordina(quantita, m.getCod());
		    	setVisible(false);
		    	MyReadM.scarica();
    			MyReadF.scarica();
    			MyReadC.scarica();
		    	Home home=new Home();
				home.setVisible(true);
		    	dispose();
		    	
			}
		});

		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	SchedaMerce ass=new SchedaMerce(m.getCod());
		    	ass.setVisible(true);
		    	Home home=new Home();
				home.setVisible(true);
		    	dispose();
			}
		});
		tasti.add(ok);
		tasti.add(ty);
		add("North",tx);
		add("Center",sal);
		add("South",tasti);
		setAlwaysOnTop(true);
		pack();
	}

}		
