package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class Balance extends Finestra{
	private double merchVal;
	private double totSold;
	private double totBought;
	private double bal;
	
		public Balance(){
			super("TOTAL BALANCE:");
			this.merchVal=Main.db.getMercVal(); // somma prezzi acquisto per quantita di tutto
			this.totSold=Main.db.getTotSold();	// somma tutte vendite
			this.totBought=Main.db.getTotBought();	// somma tutti acquisti
			this.bal=totSold-totBought;				// tutte vendite - tutti acquisti
			double balMag=bal+merchVal;			// vendite - acquisti - valore magazzino
			
			JPanel contenuto=new JPanel();
			contenuto.setOpaque(false);
			contenuto.setLayout(new GridLayout(5,2));
			
	/*comp1*/  Etichetta non=new Etichetta("Goods value in stock: ");
			contenuto.add(non);	
			Etichetta nn=new Etichetta(Est.deci.format(merchVal)+" eu. ");
			contenuto.add(nn);	
			
	/*comp2*/  Etichetta uni=new Etichetta("Total sold: ");
			contenuto.add(uni);
			Etichetta uu=new Etichetta(Est.deci.format(totSold)+" eu. ");
			contenuto.add(uu);
			
	/*comp3*/  Etichetta ac=new Etichetta("Total Bought: ");
			contenuto.add(ac);
			Etichetta aa=new Etichetta(Est.deci.format(totBought)+" eu. ");
			contenuto.add(aa);
			
	/*comp5*/  Etichetta ri=new Etichetta("Cash BALANCE: ");
			contenuto.add(ri);
			Etichetta rr=new Etichetta(Est.deci.format(bal)+" eu. ");
			contenuto.add(rr);		
			
	/*comp5*/  Etichetta rin=new Etichetta("FINAL BALANCE: ");
			contenuto.add(rin);
			Etichetta rrn=new Etichetta(Est.deci.format(balMag)+" eu. ");
			contenuto.add(rrn);
			
			c.add("Center", contenuto);
			
	/*exit*/Bottone bex=new Bottone("Back");
			bex.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	Home home=new Home();
					home.setVisible(true);
					setVisible(false);
			    	dispose();
				}
			});
			c.add("South", bex);
			pack();
		}
	}

