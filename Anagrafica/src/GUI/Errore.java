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
	JPanel c;
	
	public Errore(){
		super("---ERROR---");
//		setLayout(new GridLayout(3,1));
		setLocation(300,300);
		setBackground(Est.sfondo);
		setUndecorated(true);
		c = new JPanel();
		c.setBorder(Est.borColTut);
		c.setOpaque(false);
		add("Center",c);
		
		tx=new JLabel();
		tx.setAlignmentX(Component.CENTER_ALIGNMENT);
		tx.setText("I'm sorry, something went wrong ... ");
		tx.setBorder(Est.eti);
		tx.setFont(Est.plainFont);
		
		ok=new Bottone("OK", 5);
		
		ty=new Bottone("CANCEL", 5);
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		c.add(tx);
		c.add(ok);
		c.add(ty);
		setAlwaysOnTop(true);
		pack();
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
	
	public Errore(int a){
		this();
		//da SchedaMerce 
		tx.setText("<html>Are you sure you want to delete this product? <br/> Number:"+a+" Product: "+DataM.elenco.get(a).getNome());

		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	DataM.elimina(a);
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
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
		    	x.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});

		ty.but.setText("DELETE ORDER");
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (!gst){
		    		ConsultaPersone consultaP=new ConsultaPersone();
		    		consultaP.setVisible(true);
		    	}
		    	else if (gst){
		    		Home home=new Home();
					home.setVisible(true);
		    	}
		    	setVisible(false);
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
		    	x.setVisible(true);
		    	setVisible(false);
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
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});

		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	m.removeForn(f);
		    	f.removeMerc(m.getCod());
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	setVisible(false);
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

		ok.but.setText("Order all you have");
		ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	m.setQuantita(DataM.elenco.get(m.getCod()).getQuantita());
		    	spes.refre();
		    	setVisible(false);
		    	dispose();
			}
		});
		
		ty.setVisible(false);
		remove(ty);
		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
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
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});

		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	m.removeForn(f);
		    	f.removeMerc(m.getCod());
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	setVisible(false);
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
		    	MyReadM.scarica();
    			MyReadF.scarica();
    			MyReadC.scarica();
		    	Home home=new Home();
				home.setVisible(true);
				setVisible(false);
		    	dispose();
		    	
			}
		});

		ty.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	SchedaMerce ass=new SchedaMerce(m.getCod());
		    	ass.setVisible(true);
		    	Home home=new Home();
				home.setVisible(true);
				setVisible(false);
		    	dispose();
			}
		});
		tasti.add(ok);
		tasti.add(ty);
		c.add("North",tx);
		c.add("Center",sal);
		c.add("South",tasti);
		setAlwaysOnTop(true);
		pack();
	}

}		
