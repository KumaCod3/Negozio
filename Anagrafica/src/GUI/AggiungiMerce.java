package GUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AggiungiMerce extends Finestra{
	String nome="nome";
	String unita="unita";
	
	public AggiungiMerce(){
		super("New Product");
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setBorder(BorderFactory.createEmptyBorder(100,10,100,10));
		contenuto.setLayout(new GridLayout(1,2));
		
/*comp1*/
		JPanel uno=new JPanel();
		uno.setBorder(Est.bordo);
		uno.setOpaque(false);
		uno.setLayout(new GridLayout(1,2));
		Etichetta non=new Etichetta("Product: ");
		uno.add(non);
		JPanel form=new JPanel();
		form.setBorder(BorderFactory.createEmptyBorder(180,1,180,1));
		form.setOpaque(false);
		form.setLayout(new GridLayout(1,1));
		FormVuoto tf1 = new FormVuoto(nome);
		form.add(tf1);
		uno.add(form);
		contenuto.add(uno);
		
/*comp2*/
		JPanel due=new JPanel();
		due.setBorder(Est.bordo);
		due.setOpaque(false);
		due.setLayout(new GridLayout(1,2));		
		Etichetta etUni = new Etichetta("Unit of measure:");
		due.add(etUni);
		Choice un = new Choice();
		un.setPreferredSize(Est.choi);
		un.add("choose");
		un.add("Kg");
		un.add("g");
		un.add("lt");
		un.add("unit");
		un.setFont(Est.plainFont);
		un.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (un.getSelectedIndex()>0){
					unita=un.getSelectedItem();
				}
			}
		});
		due.add(un);
		contenuto.add(due);
		c.add("Center", contenuto);
		
		JPanel foot=new JPanel();
		foot.setBorder(Est.bordo);
		foot.setOpaque(false);
		foot.setLayout(new GridLayout(1,2));
/*comp1*/Bottone bex = new Bottone("cancel");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		foot.add(bex);
		
/*comp2*/Bottone bent = new Bottone("SAVE");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	nome=tf1.ret;
		    	if (!nome.equals("name")) {
		    		int index=Main.db.nuovaMerc(nome,unita);
		    		if (index!=-1) {
			    		SchedaMerce aggg=new SchedaMerce(index);
					    aggg.setVisible(true);
					    dispose();
		    		}
		    	}
			}
		});
		foot.add(bent);
		c.add("South",foot);
		pack();
	}
	
}
