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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Negozio.Anagrafica;
import Negozio.Cliente;
import Negozio.DataM;
import Negozio.Fornitore;
import Negozio.ListaSpesa;
import Negozio.Merce;

public class Spesa extends Finestra{
	int indice=-1;
	int indixex=-1;
	int index=-1;
	public Anagrafica b;
	public ListaSpesa list;
	public Tabella tab;
	/**
	 * @wbp.parser.constructor
	 */
	public Spesa (Cliente c){
		super("Shopping cart of "+c.getTitolo()+" "+c.getCognome()+" "+c.getNome());
		list=new ListaSpesa(c);
		b=c;
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(4,2));
		
/*comp1*/  Etichetta ti=new Etichetta("Shopping cart of ");
		contenuto.add(ti);
/*comp2*/  Etichetta no=new Etichetta(c.getTitolo()+" "+c.getCognome()+" "+c.getNome());
		contenuto.add(no);
		
		super.c.add("North",contenuto);

/*comp1*/  Etichetta tx=new Etichetta("Choose product: ");
		contenuto.add(tx);
		
		MyChoice ele=new MyChoice(DataM.elenco);
		ele.jList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					String temp=ele.getSel();
					String[] temAr=temp.split(" ");
					index=Integer.parseInt(temAr[0]);
				}
				catch (Exception ex){
					// no selection
				}
				
			}
		});
		contenuto.add(ele);
		
/*comp3*/  Etichetta qtt=new Etichetta("Quantity: ");
		contenuto.add(qtt);
/*comp4*/JPanel pan2=new JPanel();
		FormVuoto tf2=new FormVuoto("Quantity");
		pan2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan2.add(tf2);
		pan2.setOpaque(false);
		contenuto.add(pan2);
		
		Panel sal=new Panel();
		sal.setLayout(new GridLayout(1,3));
		Etichetta sal1=new Etichetta("Total: .......");
		sal1.setForeground(Est.scuro);
		sal.add(sal1);
		Etichetta sal2=new Etichetta("..............");
		sal.add(sal2);
		sal2.setForeground(Est.scuro);
		Etichetta sal3=new Etichetta("....... "+Est.deci.format(list.getSaldo())+" eu.");
		sal3.setForeground(Est.scuro);
		sal.add(sal3);
		
		Panel corpo=new Panel();
		corpo.setLayout(new BorderLayout());
		tab=new Tabella();
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
		
		super.c.add("Center", corpo);

/*comp5*/Bottone bent=new Bottone("ADD");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index!=-1){
		    		try {
		    			boolean x=list.compra(index, Double.parseDouble(tf2.ret), Spesa.this);
		    			
		    			if (x){
		    				tab.aggiungi(list.get(index),list.get(index).getQuantita());
		    			}
		    			else {
			    			tab.togli(tab.getInd(DataM.get(index).getNome()));
		    				tab.aggiungi(list.get(index),list.get(index).getQuantita());
		    			}
		    			ele.clear();
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
		
/*comp6*/Bottone eli=new Bottone("DELETE");
		eli.but.addActionListener(new ActionListener() {
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
		
		
/*comp10*/Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Errore err=new Errore(Spesa.this);
		    	err.setVisible(true);
		    	setVisible(false);
			}
		});
		sotto.add(bex);
		
/*comp11*/Bottone fin=new Bottone("BUY");
		fin.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Errore er=new Errore(Spesa.this, c);
		    	er.setVisible(true);
		    	setVisible(false);
		    	
			}
		});
		sotto.add(fin);
		
		
		super.c.add("South",sotto);



		pack();
	}
	public void refre(){
		tab.repaint(list);
	}
	
	public Spesa (Merce m,Fornitore f){
		super("Order "+m.getNome()/*,690,350*/);
//		setLocation(150,50);
		b=f;
		Panel contenuto=new Panel();
		contenuto.setLayout(new GridLayout(4,2));
		
/*comp1*/  Etichetta ti=new Etichetta("Order supply from ");
		contenuto.add(ti);
/*comp2*/  Etichetta no=new Etichetta(""+f.getCognome()+" "+f.getNome());
		contenuto.add(no);
		
/*comp3*/  Etichetta to=new Etichetta("Purchase price: ");
		contenuto.add(to);
/*comp4*/  Etichetta po=new Etichetta(""+Est.deci.format(m.getPrezzoA())+" eu per "+m.getUnit());
		contenuto.add(po);

/*comp5*/  Etichetta go=new Etichetta("Sale price: ");
		contenuto.add(go);
/*comp6*/  Etichetta xo=new Etichetta(""+Est.deci.format(m.getPrezzoV())+" eu per "+m.getUnit());
		contenuto.add(xo);
		
		
/*comp7*/  Etichetta qtt=new Etichetta("Quantity: ");
		contenuto.add(qtt);
/*comp8*/JPanel pan2=new JPanel();
		FormVuoto tf2=new FormVuoto("Quantity");
		// DA SISTEMARE DOPO ETICHETTA
		pan2.setBorder(BorderFactory.createEmptyBorder(60, 10, 10, 10));
		pan2.add(tf2);
		pan2.setOpaque(false);
		contenuto.add(pan2);
		
		c.add("Center",contenuto);
		
		Panel sotto=new Panel();
		sotto.setLayout(new GridLayout(1,2));
		
/*comp9*/Bottone bex=new Bottone("CANCEL");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Home home=new Home();
				home.setVisible(true);
				setVisible(false);
		    	dispose();
			}
		});
		sotto.add(bex);
		
/*comp10*/Bottone fin=new Bottone("BUY");
		fin.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	double qt=Double.parseDouble(tf2.ret);
		    	if (qt>0){
			    	Errore er=new Errore(f,m,qt);
			    	er.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
			}
		});
		sotto.add(fin);
			
		c.add("South",sotto);
		pack();
	}
}
