package GUI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Storico extends Finestra{
	
		public Storico(){
			super("History: ");

			Panel centro=new Panel();
			centro.setLayout(new GridLayout(1,2));
			c.add("Center",centro);
			Panel sinistro=new Panel();
			sinistro.setLayout(new BorderLayout());
			Etichetta non=new Etichetta("Sales History: ");
			sinistro.add("North",non);
			
			StorTab tab=new StorTab();
			sinistro.add("Center",tab.ta());
			
			Bottone bex=new Bottone("EXIT");
			bex.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	Home home=new Home();
					home.setVisible(true);
					setVisible(false);
			    	dispose();
				}
			});
			c.add("South",bex);
			centro.add(sinistro);
			
			Panel destro=new Panel();
			destro.setLayout(new BorderLayout());
			Etichetta nomm=new Etichetta("Purchase History: ");
			destro.add("North",nomm);
			
			StorTab tabb=new StorTab("prova");
			destro.add("Center",tabb.ta());
			centro.add(destro);

			
			pack();
		}
	}
