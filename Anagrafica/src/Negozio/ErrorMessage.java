package Negozio;
import GUI.*;
import GUI.Program.Home;

//import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ErrorMessage extends Frame implements ActionListener{
	public ErrorMessage(){
		super("---ERRORE---");
		setLayout(new BorderLayout(100,50));
		setLocation(300,300);
		setBackground(Est.chiaro);
		
		JLabel tx=new JLabel();
		tx.setText("Mi dispiace, qualcosa e andato storto... ");
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
		super("---ERRORE---");
		setLayout(new BorderLayout(100,50));
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		
		JLabel tx=new JLabel();
		tx.setText("Mi dispiace, qualcosa e andato storto... ");
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
		super("---ATTENZIONE---");
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		
		JLabel tx=new JLabel();
		tx.setText("<html>Se � sicuri divoler eliminare questo prodotto? <br/> Numero:"+a+" Merce: "+DataM.elenco.get(a).getNome());
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Pulsante ok=new Pulsante("ANNULLA");
		ok.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	dispose();
			}
		});
		
		Pulsante ty=new Pulsante("ELIMINA");
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
		super("---ATTENZIONE---");
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		boolean gst=x.b.getNome().equals("GUEST");
		System.out.println(""+gst);
		
		JLabel tx=new JLabel();
		tx.setText("<html>Se � sicuri divoler uscire? <br/> Il contenuto del carrello andra perso.");
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Pulsante ok=new Pulsante("ANNULLA");
		ok.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	x.setVisible(true);
		    	dispose();
			}
		});
		
		Pulsante ty=new Pulsante("ELIMINA");
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
		super("---ATTENZIONE---");
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		
		Fornitore f=DataB.fornitori.get(index);
		JLabel tx=new JLabel();
		tx.setText("<html>Se � sicuri divoler eliminare il fornitore? <br/> "+f.getIntestazione()+"per la Merce: "+m.getNome()+"?");
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Pulsante ok=new Pulsante("ANNULLA");
		ok.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	dispose();
			}
		});
		
		Pulsante ty=new Pulsante("ELIMINA");
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
		super("---ATTENZIONE---");
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		
		Merce m=DataM.get(index);
		JLabel tx=new JLabel();
		tx.setText("<html>Se � sicuri divoler eliminare il fornitore? <br/> "+f.getIntestazione()+"per la Merce: "+m.getNome()+"?");
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Pulsante ok=new Pulsante("ANNULLA");
		ok.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	AssegnaMerc ass=new AssegnaMerc(m);
		    	ass.setVisible(true);
		    	dispose();
			}
		});
		
		Pulsante ty=new Pulsante("ELIMINA");
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
		super("---ATTENZIONE---");
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		
		double saldo=m.getPrezzoA()*quantita;
		
		JLabel tx=new JLabel();
		tx.setText("<html>Se � sicuri divoler fare l'ordine? "+f.getIntestazione()+"per la Merce: "+m.getNome()+"?");
		tx.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		tx.setFont(Est.font);
		
		Panel sal=new Panel();
		sal.setLayout(new GridLayout(1,3));
		Etichetta sal1=new Etichetta("Ordinare: "+quantita+m.getUnit());
		sal.add(sal1);
		Etichetta sal2=new Etichetta(" di "+m.getNome());
		sal.add(sal2);
		Etichetta sal3=new Etichetta("costo: "+Est.deci.format(saldo)+" eu. ?");
		sal.add(sal3);
		
		
		Panel tasti=new Panel();
		tasti.setLayout(new GridLayout(1,2));
		
		Pulsante ok=new Pulsante("ANNULLA");
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
		
		Pulsante ty=new Pulsante("ORDINA");
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
