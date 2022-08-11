package GUI;
import Negozio.*;
//import java.util.*;
import java.awt.*;
import Negozio.*;
import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class AssegnaMerc extends Finestra{
	int index;
	
	public AssegnaMerc(Fornitore f){
		super("Assegna Merce a fornitore");
		
		Panel contenuto=new Panel();
		contenuto.setLayout(new GridLayout(3,2));
		add("Center",contenuto);
		
		Etichetta nom=new Etichetta("Fornitore: ");
		contenuto.add(nom);
		Etichetta nome=new Etichetta(f.getIntestazione());
		contenuto.add(nome);
		
		Etichetta tt=new Etichetta("Scegliere merce:");
		contenuto.add(tt);
		Choice ele=new Choice();
		ele.add("Scegli");
		try{
			for (Merce a:DataM.elenco.values()){
				ele.add(a.getNome()+" "+a.getCod());
			}
		}
		catch (Exception e){
			ele.add("Lista vuota");
		}
		ele.setFont(Est.font);
		ele.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (ele.getSelectedItem().equals("Scegli")||ele.getSelectedItem().equals("Lista vuota")){
				}
				else {
					String temp=ele.getSelectedItem();
					String[] temAr=temp.split(" ");
					index=Integer.parseInt(temAr[temAr.length-1]);
				}
			}
		});
		contenuto.add(ele);
		
		Pulsante bex=new Pulsante("-ESCI-");
		bex.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	dispose();
			}
		});
		contenuto.add(bex);
		Pulsante bent=new Pulsante("-INVIO-");
		bent.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
			    	f.addMerc(index);
			    	DataM.get(index).addForn(f);
			    	ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
		    	else {
		    		ErrorMessage err=new ErrorMessage();
		    		err.setVisible(true);
		    	}
			}
		});
		contenuto.add(bent);
		Pulsante dis=new Pulsante("-RIMUOVI-");
		dis.setBackground(Est.rosso);
		dis.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
			    	setVisible(false);
			    	ErrorMessage del=new ErrorMessage(f,index);
			    	del.setVisible(true);
			    	dispose();
		    	}
			}
		});
		
		add("South",dis);
		pack();
	}
	public AssegnaMerc(Merce m){
		super("Assegna Merce a fornitore");
		
		Etichetta nom=new Etichetta("Merce: "+m.getNome());
		add("North",nom);	
		
		Panel contenuto=new Panel();
		contenuto.setLayout(new GridLayout(2,2));
		add("Center",contenuto);
		
		Etichetta tt=new Etichetta("Scegliere Fornitore:");
		contenuto.add(tt);
		Choice ele1=new Choice();
		ele1.add("Scegli");
		try{
			for (Fornitore a:DataB.fornitori){
				ele1.add(a.getCognome()+", "+a.getNome());
			}
		}
		catch (Exception e){
			ele1.add("Lista vuota");
		}
		ele1.setFont(Est.font);
		ele1.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (ele1.getSelectedItem().equals("Scegli")||ele1.getSelectedItem().equals("Lista vuota")){
				}
				else {
					String[] temp=ele1.getSelectedItem().split(", ");
					index=DataB.trovaPersona(temp[0], temp[1]);
				}
			}
		});
		contenuto.add(ele1);
		
		Pulsante bex=new Pulsante("-ESCI-");
		bex.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	dispose();
			}
		});
		contenuto.add(bex);
		Pulsante bent=new Pulsante("-INVIO-");
		bent.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
		    		DataB.fornitori.get(index).addMerc(m);
			    	m.addForn(DataB.fornitori.get(index));
			    	ConsultaMerci consultaM=new ConsultaMerci();
			    	consultaM.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
		    	else {
		    		ErrorMessage err=new ErrorMessage();
		    		err.setVisible(true);
		    	}
			}
		});
		contenuto.add(bent);
		
		Pulsante dis=new Pulsante("-RIMUOVI-");
		dis.setBackground(Est.rosso);
		dis.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index>-1){
			    	setVisible(false);
			    	ErrorMessage del=new ErrorMessage(m,index);
			    	del.setVisible(true);
			    	dispose();
		    	}
			}
		});
		
		add("South",dis);
		pack();
	}
}
