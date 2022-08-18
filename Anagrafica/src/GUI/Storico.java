package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.HashMap;

import GUI.Program.Home;
import Negozio.Anagrafica;
import Negozio.DataB;

public class Storico extends Finestra{
	
		public Storico(){
			super("Storico: ");

			Panel centro=new Panel();
			centro.setLayout(new GridLayout(1,2));
			add("Center",centro);
			Panel sinistro=new Panel();
			sinistro.setLayout(new BorderLayout());
			Etichetta non=new Etichetta("Storico Vendite: ");
			sinistro.add("North",non);
			
			StorTab tab=new StorTab();
			sinistro.add("Center",tab.ta());
			
			Pulsante bex=new Pulsante("-ESCI-");
			bex.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	setVisible(false);
			    	Home home=new Home();
					home.setVisible(true);
			    	dispose();
				}
			});
			add("South",bex);
			centro.add(sinistro);
			
			Panel destro=new Panel();
			destro.setLayout(new BorderLayout());
			Etichetta nomm=new Etichetta("Storico Acquisti: ");
			destro.add("North",nomm);
			
			StorTab tabb=new StorTab("prova");
			destro.add("Center",tabb.ta());
			centro.add(destro);

			
			pack();
		}
	}
