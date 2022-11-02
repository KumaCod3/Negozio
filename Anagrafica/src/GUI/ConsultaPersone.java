package GUI;
import Negozio.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;

public class ConsultaPersone  extends Finestra {
	int indexF=-1;
	int indexC=-1;
	boolean tipp=true;
	public ConsultaPersone(){
		super("Consult Persons database"/*,770,400*/);

		JPanel contenuto=new JPanel();
		contenuto.setLayout(new GridLayout(5,1));
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		
		JPanel pan1=new JPanel();
		pan1.setLayout(new GridLayout(1,2));
		pan1.setBorder(Est.bordo);
		pan1.setOpaque(false);
		JPanel pan2=new JPanel();
		pan2.setLayout(new GridLayout(1,2));
		pan2.setBorder(Est.bordo);
		pan2.setOpaque(false);
		JPanel pan3=new JPanel();
		pan3.setLayout(new GridLayout(1,2));
		pan3.setBorder(Est.bordo);
		pan3.setOpaque(false);
		
/*comp1*/  Etichetta tx1=new Etichetta("Supplier:");
		pan1.add(tx1);
		
/*comp2*/MyChoice<Fornitore> ele=new MyChoice<Fornitore>(DataB.fornitori);
		ele.jList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					String[] temp=ele.getSel().split(" , ");
					indexF=DataB.trovaPersona(temp[0], temp[1]);
				}
				catch (Exception ex){
					// no selection
				}
				
			}
		});
		
		pan1.add(ele);
		contenuto.add(pan1);
		
/*comp3*/Bottone bent1=new Bottone("ENTER", 5);
		bent1.but.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (indexF!=-1){
					SchedaPersona aggg=new SchedaPersona(indexF,"fornitore");
				    aggg.setVisible(true);
				    dispose();
				 }
			}
		});
		contenuto.add(bent1);


/*comp4*/  Etichetta tx=new Etichetta("Customers:");
		pan2.add(tx);
		
/*comp5*/MyChoice<Cliente> ele2=new MyChoice<Cliente>(DataB.clienti);
		ele2.jList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					String[] temp=ele2.getSel().split(" , ");
					indexC=DataB.trovaPersona(temp[0], temp[1], 5);
				}
				catch (Exception ex){
					// no selection
				}
			}
		});
		pan2.add(ele2);
		contenuto.add(pan2);

/*comp6*/Bottone bent=new Bottone("ENTER", 5);
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (indexC!=-1){
		    		SchedaPersona aggg=new SchedaPersona(indexC,"cliente");
			    	aggg.setVisible(true);
			    	dispose();
			 }
		}
	});
		contenuto.add(bent);
		
/*comp7*/Bottone bex=new Bottone("EXIT");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Home home=new Home();
				home.setVisible(true);
				setVisible(false);
		    	dispose();
			}
		});
		pan3.add(bex);
		
/*comp8Etichetta tv=new Etichetta("           ");
		contenuto.add(tv);*/
		
/*comp9*/Bottone b2=new Bottone("ADD new");
		b2.but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AggiungiPersona aggg=new AggiungiPersona();
				aggg.setVisible(true);
				dispose();
			}
		});
		pan3.add(b2);
		contenuto.add(pan3);
		
		c.add(contenuto);
		pack();
	}
	public void sett(boolean a){
		this.tipp=a;
	}
}