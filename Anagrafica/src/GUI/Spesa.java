package GUI;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import GUI.Program.Home;
import Negozio.Cliente;
import Negozio.DataM;
import Negozio.ErrorMessage;
import Negozio.Fornitore;
import Negozio.ListaSpesa;
import Negozio.Merce;

public class Spesa extends Finestra{
	int indice=-1;
	int indixex=-1;
	int index=-1;
	public Spesa (Cliente c){
		super("Lista spesa per "+c.getTitolo()+" "+c.getCognome()+" "+c.getNome());
		setLocation(150,50);
		ListaSpesa list=new ListaSpesa(c);
		
		Panel contenuto=new Panel();
		contenuto.setLayout(new GridLayout(4,2));

	/*	Panel tit=new Panel();
		tit.setLayout(new GridLayout(2,1));*/
		
/*comp1*/  Etichetta ti=new Etichetta("Lista spesa per ");
		contenuto.add(ti);
/*comp2*/  Etichetta no=new Etichetta(c.getTitolo()+" "+c.getCognome()+" "+c.getNome());
		contenuto.add(no);
		
		add("North",contenuto);

/*comp1*/  Etichetta tx=new Etichetta("Scegli merce: ");
		contenuto.add(tx);
		
/*comp2*/Choice ele=new Choice();
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
		
/*comp3*/  Etichetta qtt=new Etichetta("Quantita: ");
		contenuto.add(qtt);
/*comp4*/JPanel pan2=new JPanel();
		FormVuoto tf2=new FormVuoto("Quantita");
		pan2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan2.add(tf2);
		pan2.setBackground(Est.chiaro);
		contenuto.add(pan2);
		
		Panel sal=new Panel();
		sal.setLayout(new GridLayout(1,3));
		Etichetta sal1=new Etichetta("Totale: .......");
		sal.add(sal1);
		Etichetta sal2=new Etichetta("..............");
		sal.add(sal2);
		Etichetta sal3=new Etichetta(Est.deci.format(list.getSaldo())+" eu.");
		sal.add(sal3);
		
		Panel corpo=new Panel();
		corpo.setLayout(new BorderLayout());
		Tabella tab=new Tabella();
		tab.tavola.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e){
					indice=tab.tavola.getSelectedRow();
					indixex=list.trovaNome(tab.getNome(indice));
					sal3.setText(Est.deci.format(list.getSaldo())+" eu.");
				}
				public void focusLost(FocusEvent e){

				}
			});
		corpo.add("Center",tab.ta());

		corpo.add("South",sal);
		
		add("Center", corpo);

/*comp5*/Pulsante bent=new Pulsante("-AGGIUNGI-");
		bent.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index!=-1){
		    		try {
		    			boolean x=list.compra(index, Double.parseDouble(tf2.ret));
		    			
		    			if (x){
		    				tab.aggiungi(DataM.get(index),Double.parseDouble(tf2.ret));
		    			}
		    			else {
			    			tab.togli(tab.getInd(DataM.get(index).getNome()));
		    				tab.aggiungi(list.get(index),list.get(index).getQuantita());
		    			}
		    			ele.select(0);
		    			tf2.clear();
		    			sal3.setText(Est.deci.format(list.getSaldo())+" eu.");
		    			index=-1;

			    	}
			    	catch (Exception ex){
			    	}
		    	}
			}
		});
		contenuto.add(bent);
		
/*comp6*/Pulsante eli=new Pulsante("-ELIMINA-");
		eli.setBackground(Est.rosso);
		eli.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (indice!=-1&&indixex!=-1){
		    		try {
		    			list.elimina(indixex);
		    			tab.togli(indice);
		    			indice=-1;
		    			indixex=-1;
		    			sal3.setText(Est.deci.format(list.getSaldo())+" eu.");

			    	}
			    	catch (Exception ex){
			    	}
		    	}
			}
		});
		contenuto.add(eli);
		
		
		Panel sotto=new Panel();
		sotto.setLayout(new GridLayout(1,2));
		
		
/*comp10*/Pulsante bex=new Pulsante("-ESCI-");
		bex.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	ErrorMessage err=new ErrorMessage(Spesa.this);
		    	err.setVisible(true);
			}
		});
		sotto.add(bex);
		
/*comp11*/Pulsante fin=new Pulsante("-ACQUISTA-");
		fin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	list.concludi();
		    	tab.clear();
		    	MyReadL.scarica(list);
		    	setVisible(false);
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	dispose();
			}
		});
		sotto.add(fin);
		
		
		add("South",sotto);



		pack();
	}
	
	public Spesa (Merce m,Fornitore f){
		super("Ordina "+m.getNome());
		setLocation(150,50);
		
		Panel contenuto=new Panel();
		contenuto.setLayout(new GridLayout(2,2));
		
/*comp1*/  Etichetta ti=new Etichetta("Ordina rifornimenti di "+m.getNome());
		contenuto.add(ti);
/*comp2*/  Etichetta no=new Etichetta(" da "+f.getCognome()+" "+f.getNome());
		contenuto.add(no);
		
		add("North",contenuto);

/*comp3*/  Etichetta qtt=new Etichetta("Quantita: ");
		contenuto.add(qtt);
/*comp4*/JPanel pan2=new JPanel();
		FormVuoto tf2=new FormVuoto("Quantita");
		pan2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan2.add(tf2);
		pan2.setBackground(Est.chiaro);
		contenuto.add(pan2);
		
		
		Panel sotto=new Panel();
		sotto.setLayout(new GridLayout(1,2));
		
/*comp10*/Pulsante bex=new Pulsante("-ANNULLA-");
		bex.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    	Home home=new Home();
				home.setVisible(true);
		    	dispose();
			}
		});
		sotto.add(bex);
		
/*comp11*/Pulsante fin=new Pulsante("-ACQUISTA-");
		fin.setBackground(Est.oran);
		fin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	double qt=Double.parseDouble(tf2.ret);
		    	if (qt>0){
			    	ErrorMessage er=new ErrorMessage(f,m,qt);
			    	er.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
			}
		});
		sotto.add(fin);
			
		add("South",sotto);
		pack();
	}
}
