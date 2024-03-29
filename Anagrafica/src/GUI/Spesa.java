package GUI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Negozio.ListaSpesa;
import Negozio.MyDB;

public class Spesa extends Finestra{
	int indice=-1;
	int indixex=-1;
	int index=-1;
	public ListaSpesa list;
	public Tabella tab;

// cliente
	public Spesa (int indexC){
		super("Shopping cart ");
		removeWindowListener(getWindowListeners()[0]);
		addWindowListener (new WindowAdapter() {    
            public void windowClosing (WindowEvent e) {    
            	Errore err=new Errore("Are you sure you want to cancel this cart?", Spesa.this);
		    	err.setVisible(true);
		    	setVisible(false);
            }    
        });
		
		try {
			String[] dati=MyDB.leggiCliID(indexC).split(",");
			this.setTitle("Shopping cart of "+dati[1]+" "+dati[2]);
		
			list=new ListaSpesa(indexC, this);
			
			JPanel contenuto=new JPanel();
			contenuto.setBorder(Est.bordo);
			contenuto.setOpaque(false);
			contenuto.setLayout(new GridLayout(3,2));
			
	/*comp1*/  Etichetta ti=new Etichetta("Shopping cart of ");
			contenuto.add(ti);
	/*comp2*/  Etichetta no=new Etichetta(dati[1]+" "+dati[2]);
			contenuto.add(no);
			super.c.add("North",contenuto);
	
			
			JPanel pp1=new JPanel();
			pp1.setBorder(Est.bordo);
			pp1.setOpaque(false);
			pp1.setLayout(new GridLayout(1,2));
	/*comp1*/  Etichetta tx=new Etichetta("Choose product: ");
			tx.setBorder(BorderFactory.createEmptyBorder(1,1,80,1));
			pp1.add(tx);
			MyChoice ele=new MyChoice(MyDB.getElenMerc(), "ciao");
			JPanel ff=new JPanel();
			ff.setOpaque(false);
			ff.setLayout(new GridLayout(1,1));
			ff.add(ele);
			ele.jList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					try {
						String temp=ele.getSel();
						String[] temAr=temp.split(", ");
						index=Integer.parseInt(temAr[0]);

					}
					catch (Exception ex){
						// no selection
					}
					
				}
			});
			pp1.add(ff);
			contenuto.add(pp1);
			
			JPanel pp2=new JPanel();
			pp2.setBorder(Est.bordo);
			pp2.setOpaque(false);
			pp2.setLayout(new GridLayout(1,2));
	/*comp3*/  Etichetta qtt=new Etichetta("Quantity: ");
			qtt.setBorder(BorderFactory.createEmptyBorder(1,1,80,1));
			pp2.add(qtt);
	/*comp4*/JPanel pan2=new JPanel();
			FormVuoto tf2=new FormVuoto("Quantity");
			pan2.add(tf2);
			pan2.setOpaque(false);
			pp2.add(pan2);
			contenuto.add(pp2);

			
			JPanel sal=new JPanel();
			sal.setOpaque(false);
			sal.setLayout(new GridLayout(1,4));
			Etichetta sal1=new Etichetta("Total: .......");
			sal1.setForeground(Est.scuro);
			sal.add(sal1);
			Etichetta sal3=new Etichetta("....... "+Est.deci.format(list.getSaldo())+" eu.");
			sal3.setForeground(Est.scuro);
			sal.add(sal3);
			
			JPanel corpo=new JPanel();
			corpo.setOpaque(false);
			corpo.setLayout(new BorderLayout());
			tab=new Tabella();
			tab.tavola.addFocusListener(new FocusListener() {
					public void focusGained(FocusEvent e){
						indice=tab.tavola.getSelectedRow();
						indixex=tab.getInd(indice);
						sal3.setText(Est.deci.format(list.getSaldo())+" eu.");
					}
					public void focusLost(FocusEvent e){
	
					}
				});
			corpo.add("Center",tab.ta());
			
			super.c.add("Center", corpo);
	
	/*comp5*/Bottone bent=new Bottone("ADD");
			bent.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	if (index!=-1){
			    		try {
			    			Double quant=Double.parseDouble(tf2.ret);
			    			list.compra(index, quant);
			    			ele.clear();
			    			tf2.clear();
			    			sal3.setText(Est.deci.format(list.getSaldo())+" eu.");
			    			tab.repaint(list);
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
			    	if (indixex!=-1){
			    		try {
			    			list.elimina(indixex);
			    			indixex=-1;
			    			sal3.setText(Est.deci.format(list.getSaldo())+" eu.");
			    			tab.repaint(list);
	
				    	}
				    	catch (Exception ex){
				    	}
			    	}
				}
			});
			contenuto.add(eli);
						
			
	/*comp10*/Bottone bex=new Bottone("Back");
			bex.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	Errore err=new Errore("Are you sure you want to cancel this cart?", Spesa.this);
			    	err.setVisible(true);
			    	setVisible(false);
				}
			});
			sal.add(bex);
			
	/*comp11*/Bottone fin=new Bottone("BUY");
			fin.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	Errore er=new Errore(list, Spesa.this);
			    	er.setVisible(true);
			    	setVisible(false);
			    	
				}
			});
			sal.add(fin);
			super.c.add("South",sal);
			pack();

		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public void refre(){
		tab.repaint(list);
	}
	
// fornitore -----------------------------------------------------------------------------------------------
	public Spesa (int index, int codice) throws SQLException{
		super("Order "+MyDB.getMerName(codice)+" from "+MyDB.getForName(index));
		String unita="";
		double prezF=MyDB.getPriceF(index, codice);
		JPanel contenuto=new JPanel();
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(4,2));
		
		try {
/*comp1*/  Etichetta ti=new Etichetta("Order supply of "+MyDB.getMerName(codice));
		contenuto.add(ti);
		}	catch (Exception ee) {ee.printStackTrace();}
/*comp2*/  Etichetta no=new Etichetta("from "+MyDB.getForName(index));
		contenuto.add(no);
		
/*comp3*/  Etichetta to=new Etichetta("Purchase price: ");
		contenuto.add(to);
/*comp4*/  Etichetta po=new Etichetta(""+Est.deci.format(prezF)+" eu per "+unita);
		contenuto.add(po);

/*comp5*/  Etichetta go=new Etichetta("Sell price: ");
		contenuto.add(go);
/*comp6*/  Etichetta xo=new Etichetta(""+Est.deci.format(MyDB.getPrezzo(codice))+" eu per "+unita);
		contenuto.add(xo);
		
		
/*comp7*/  Etichetta qtt=new Etichetta("Quantity: ");
		contenuto.add(qtt);
/*comp8*/JPanel pan2=new JPanel();
		FormVuoto tf2=new FormVuoto("Quantity");
		pan2.setBorder(BorderFactory.createEmptyBorder(50,1,0,460));
		pan2.add(tf2);
		pan2.setOpaque(false);
		contenuto.add(pan2);
		
		c.add("Center",contenuto);
		
		JPanel sotto=new JPanel();
		sotto.setOpaque(false);
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
		    	
		    	double price=qt*prezF;
		    	if (qt>0){
			    	Errore er=new Errore(index, codice, price, Spesa.this, qt);
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
