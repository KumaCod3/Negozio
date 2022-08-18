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
			super("Storico Vendite: ");

			Etichetta non=new Etichetta("Storico Vendite: ");
			add("North",non);
			
			StorTab tab=new StorTab();
			add("Center",tab.ta());
			
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
			
			pack();
		}
	}
