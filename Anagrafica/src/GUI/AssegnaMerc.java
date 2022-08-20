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
		super("Assign Product to Supplier");
		
		Panel contenuto=new Panel();
		contenuto.setLayout(new GridLayout(3,2));
		add("Center",contenuto);
		
		Etichetta nom=new Etichetta("Supplier: ");
		contenuto.add(nom);
		Etichetta nome=new Etichetta(f.getIntestazione());
		contenuto.add(nome);
		
		Etichetta tt=new Etichetta("Choose Product:");
		contenuto.add(tt);
		Choice ele=new Choice();
		ele.add("Choose");
		try{
			for (Merce a:DataM.elenco.values()){
				ele.add(a.getNome()+" "+a.getCod());
			}
		}
		catch (Exception e){
			ele.add("Empty");
		}
		ele.setFont(Est.font);
		ele.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (ele.getSelectedItem().equals("Choose")||ele.getSelectedItem().equals("Empty")){
				}
				else {
					String temp=ele.getSelectedItem();
					String[] temAr=temp.split(" ");
					index=Integer.parseInt(temAr[temAr.length-1]);
				}
			}
		});
		contenuto.add(ele);
		
		Pulsante bex=new Pulsante("-EXIT-");
		bex.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	dispose();
			}
		});
		contenuto.add(bex);
		Pulsante bent=new Pulsante("-ENTER-");
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
		Pulsante dis=new Pulsante("-REMOVE-");
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
		super("Assign Product to Supplier");
		
		Etichetta nom=new Etichetta("Product: "+m.getNome());
		add("North",nom);	
		
		Panel contenuto=new Panel();
		contenuto.setLayout(new GridLayout(2,2));
		add("Center",contenuto);
		
		Etichetta tt=new Etichetta("Choose Supplier:");
		contenuto.add(tt);
		Choice ele1=new Choice();
		ele1.add("Choose");
		try{
			for (Fornitore a:DataB.fornitori){
				ele1.add(a.getCognome()+", "+a.getNome());
			}
		}
		catch (Exception e){
			ele1.add("Empty");
		}
		ele1.setFont(Est.font);
		ele1.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (ele1.getSelectedItem().equals("Choose")||ele1.getSelectedItem().equals("Empty")){
				}
				else {
					String[] temp=ele1.getSelectedItem().split(", ");
					index=DataB.trovaPersona(temp[0], temp[1]);
				}
			}
		});
		contenuto.add(ele1);
		
		Pulsante bex=new Pulsante("-EXIT-");
		bex.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	dispose();
			}
		});
		contenuto.add(bex);
		Pulsante bent=new Pulsante("-ENTER-");
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
		
		Pulsante dis=new Pulsante("-REMOVE-");
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
