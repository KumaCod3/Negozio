package GUI;
import Negozio.*;
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
			this.merchVal=Shop.getMerchVal();
			this.totSold=Shop.getTotSold();
			this.totBought=Shop.getTotBought();
			this.bal=Shop.getBal();
			
			JPanel contenuto=new JPanel();
			contenuto.setOpaque(false);
			contenuto.setLayout(new GridLayout(4,2));
			
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
			
	/*comp4*/  Etichetta ri=new Etichetta("FINAL BALANCE: ");
			contenuto.add(ri);
			Etichetta rr=new Etichetta(Est.deci.format(bal)+" eu. ");
			contenuto.add(rr);
			
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

