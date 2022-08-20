package Negozio;
import GUI.*;
import GUI.Program.Home;

//import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ErrorMessage extends Frame implements ActionListener{
	public ErrorMessage(){
		super("---ERROR---");
		setLayout(new BorderLayout(100,50));
		setLocation(300,300);
		setBackground(Est.chiaro);
		
		JLabel tx=new JLabel();
		tx.setText("I'm sorry, something went wrong ... ");
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Pulsante ok=new Pulsante("OK");
		ok.addActionListener(this);
		
		add("North",tx);
		add("Center",ok);
		setAlwaysOnTop(true);
		pack();
	}
	public ErrorMessage(String a){
		super("---ERROR---");
		setLayout(new BorderLayout(100,50));
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		
		JLabel tx=new JLabel();
		tx.setText("I'm sorry, something went wrong ... ");
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Pulsante ok=new Pulsante("OK");
		ok.addActionListener(this);
		
		JLabel ty=new JLabel();
		ty.setText(a);
		ty.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		ty.setFont(new Font("Lucida",Font.PLAIN,14));
		
		add("North",tx);
		add("Center",ok);
		add("South",ty);
		setAlwaysOnTop(true);
		pack();
	}
	
	public ErrorMessage(int a){
		super("---ATTENTION---");
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		
		JLabel tx=new JLabel();
		tx.setText("<html>Are you sure you want to delete this product? <br/> Number:"+a+" Product: "+DataM.elenco.get(a).getNome());
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Pulsante ok=new Pulsante("CANCEL");
		ok.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	dispose();
			}
		});
		
		Pulsante ty=new Pulsante("DELETE");
		ty.setBackground(Est.rosso);
		ty.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	DataM.elimina(a);
		    	setVisible(false);
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	dispose();
			}
		});
		
		add("North",tx);
		add("Center",ok);
		add("South",ty);
		setAlwaysOnTop(true);
		pack();
	}
	
	public ErrorMessage(Spesa x){
		super("---ATTENTION---");
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		boolean gst=x.b.getNome().equals("GUEST");
		System.out.println(""+gst);
		
		JLabel tx=new JLabel();
		tx.setText("<html>Are you sure you want to go out? <br/> The contents of the shopping cart will be lost.");
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Pulsante ok=new Pulsante("CANCEL");
		ok.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	x.setVisible(true);
		    	dispose();
			}
		});
		
		Pulsante ty=new Pulsante("DELETE");
		ty.setBackground(Est.rosso);
		ty.addActionListener(new ActionListener() {
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
		
		add("North",tx);
		add("Center",ok);
		add("South",ty);
		setAlwaysOnTop(true);
		pack();
	}
	
	public ErrorMessage(Merce m,int index){
		super("---ATTENTION---");
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		
		Fornitore f=DataB.fornitori.get(index);
		JLabel tx=new JLabel();
		tx.setText("<html>Are you sure you will delete the supplier <br/> "+f.getIntestazione()+"for the product: "+m.getNome()+"?");
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Pulsante ok=new Pulsante("CANCEL");
		ok.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	dispose();
			}
		});
		
		Pulsante ty=new Pulsante("DELETE");
		ty.setBackground(Est.rosso);
		ty.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	m.removeForn(f);
		    	f.removeMerc(m.getCod());
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	dispose();
			}
		});
		
		add("North",tx);
		add("Center",ok);
		add("South",ty);
		setAlwaysOnTop(true);
		pack();
	}
	
	public ErrorMessage(Fornitore f,int index){
		super("---ATTENtION---");
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		
		Merce m=DataM.get(index);
		JLabel tx=new JLabel();
		tx.setText("<html>Are you sure you will delete the supplier <br/> "+f.getIntestazione()+"for the product: "+m.getNome()+"?");
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Pulsante ok=new Pulsante("CANCEL");
		ok.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	dispose();
			}
		});
		
		Pulsante ty=new Pulsante("DELETE");
		ty.setBackground(Est.rosso);
		ty.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	m.removeForn(f);
		    	f.removeMerc(m.getCod());
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	dispose();
			}
		});
		
		add("North",tx);
		add("Center",ok);
		add("South",ty);
		setAlwaysOnTop(true);
		pack();
	}
	
	public ErrorMessage(Fornitore f,Merce m, double quantita){
		super("---ATTENTION---");
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		
		double saldo=m.getPrezzoA()*quantita;
		
		JLabel tx=new JLabel();
		tx.setText("<html>Do you want to place the order from "+f.getIntestazione()+"for the product: "+m.getNome()+"?");
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Panel sal=new Panel();
		sal.setLayout(new GridLayout(1,3));
		Etichetta sal1=new Etichetta("Order: "+quantita+m.getUnit());
		sal.add(sal1);
		Etichetta sal2=new Etichetta(" of "+m.getNome());
		sal.add(sal2);
		Etichetta sal3=new Etichetta("price: "+Est.deci.format(saldo)+" eu. ?");
		sal.add(sal3);
		
		
		Panel tasti=new Panel();
		tasti.setLayout(new GridLayout(1,2));
		
		Pulsante ok=new Pulsante("CANCEL");
		ok.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	Home home=new Home();
				home.setVisible(true);
		    	dispose();
			}
		});
		
		Pulsante ty=new Pulsante("PLACE ORDER");
		ty.setBackground(Est.rosso);
		ty.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	f.ordina(quantita, m.getCod());
		    	
		    	setVisible(false);
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
	public void actionPerformed(ActionEvent e){
		this.setVisible(false);
		this.dispose();
	}
	
}		
