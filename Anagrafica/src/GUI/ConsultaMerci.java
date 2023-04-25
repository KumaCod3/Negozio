package GUI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPanel;

public class ConsultaMerci extends Finestra{
	boolean tipp=true;
	int index=-1;
	int indice=-1;
	
	public ConsultaMerci(){
		super("Storage: ");

		JPanel centro=new JPanel();
		centro.setLayout(new GridLayout(1,1));
		centro.setOpaque(false);
		c.add("Center",centro);
		JPanel sinistro=new JPanel();
		sinistro.setOpaque(false);
		sinistro.setLayout(new BorderLayout());
		Etichetta non=new Etichetta("Products in storage: ");
		sinistro.add("North",non);
		
		TabMerc tab=new TabMerc();
		sinistro.add("Center",tab.ta());
		tab.tavola.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
				indice=tab.tavola.getSelectedRow();
				index=tab.getID(indice);
			}
			public void focusLost(FocusEvent e){

			}
		});
		
		JPanel sud=new JPanel();
		sud.setOpaque(false);
		sud.setLayout(new GridLayout(1,3));
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Home home=new Home();
				home.setVisible(true);
				setVisible(false);
		    	dispose();
			}
		});
		sud.add(bex);
		
		Bottone bent=new Bottone("See Product");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index!=-1){
		    		SchedaMerce aggg=new SchedaMerce(index);
				    	aggg.setVisible(true);
				    	dispose();
		    	}
			}
		});
		
		sud.add(bent);
		
/*comp6*/Bottone b2=new Bottone("ADD new");
		b2.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AggiungiMerce aggg=new AggiungiMerce();
		    	aggg.setVisible(true);
		    	dispose();
			}
		});
		sud.add(b2);
		c.add("South",sud);
		centro.add(sinistro);
		pack();
	}
}
