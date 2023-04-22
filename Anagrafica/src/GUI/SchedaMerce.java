package GUI;
import Negozio.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchedaMerce extends Finestra{
	String nome="";
	int codice;
	int index;
	Double quantita=0.0;
	Double rincaro=0.0;
	Double prezzoA=0.0;
	Double prezzoV=0.0;
	Double valore=0.0;
	String unita="";
	String note="";
	
	public SchedaMerce(int x){
		super("Product details");
		index=x;
		if (x!=-1) {
			try {
				String data=Main.db.leggiMercID(x);
				String[] spl=data.split(",");

				this.nome=spl[1];
				this.unita=spl[2];
				this.quantita=Double.parseDouble(spl[3]);
				this.prezzoA=Double.parseDouble(spl[4]);
				this.rincaro=Double.parseDouble(spl[5]);
				this.note=spl[6];
				this.prezzoV=prezzoA*rincaro;
				this.valore=prezzoA*quantita;
				
			} catch (SQLException ex) {	ex.printStackTrace(); }
		}
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(6,2));
		
/*comp1*/  Etichetta non=new Etichetta("Product: ");
		contenuto.add(non);	
		Etichetta nn=new Etichetta(""+nome);
		contenuto.add(nn);	
		
/*comp2*/  Etichetta uni=new Etichetta("In stock: ");
		contenuto.add(uni);
		Etichetta uu=new Etichetta(quantita+" "+unita);
		if (quantita<=5.0){
			uu.setForeground(Est.rosso);
			uu.setOpaque(true);
			uni.setOpaque(true);
		}
		contenuto.add(uu);
		
/*comp3*/  Etichetta ac=new Etichetta("Purchase price: ");
		contenuto.add(ac);
		Etichetta aa=new Etichetta(Est.deci.format(prezzoA)+"eu for "+unita);
		contenuto.add(aa);
		
/*comp4*/  Etichetta ri=new Etichetta("Selling price: ");
		contenuto.add(ri);
		Etichetta rr=new Etichetta(Est.deci.format(prezzoV)+"eu ("+rincaro+"% price increase)");
		contenuto.add(rr);
		
/*comp5*/  Etichetta va=new Etichetta("Total in stock value: ");
		contenuto.add(va);
		Etichetta vv=new Etichetta(Est.deci.format(valore)+"eu");
		contenuto.add(vv);
		
/*comp6*/  Etichetta forn=new Etichetta("Supplier: ");
		contenuto.add(forn);	
		Choice ele1=new Choice();
		ele1.add("Choose");
//		try{
//			for (Fornitore a:mer.getForn()){
//				ele1.add(a.getCognome()+", "+a.getNome());
//			}
//		}
//		catch (Exception e){
//			ele1.add("Empty");
//		}
		try{
			ResultSet xx=Main.db.getElenSuppF(index);
			while (xx.next()) {
				ele1.add(xx.getString(1)+", "+xx.getShort(2)+", "+xx.getShort(3));
			}
		}
		catch (SQLException e){	e.printStackTrace();	}
		
		ele1.setFont(Est.plainFont);
		ele1.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (ele1.getSelectedItem().equals("Choose")||ele1.getSelectedItem().equals("Empty")){
				}
				else {
					String[] temp=ele1.getSelectedItem().split(", ");
					codice=Integer.parseInt(temp[0]);
				}
			}
		});
		contenuto.add(ele1);	
		
		Panel sud=new Panel();
		sud.setLayout(new GridLayout(2,2));
		
/*comp7*/Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		sud.add(bex);
		Bottone bin=new Bottone("MODIFY");
		bin.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
			    	ModMerce modifM=new ModMerce(x);
			    	setVisible(false);
			    	modifM.setVisible(true);
		    	
			    	dispose();
		    	}
			}
		});
		sud.add(bin);
		
/*OUT*/		
		Bottone eli=new Bottone("DELETE");
		eli.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
			    	Errore del=new Errore(x);
			    	del.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
			}
		});
		
		sud.add(eli);
		Bottone ord=new Bottone("ORDER");
		ord.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
		    		
		    		try{
//	SEGNAPOSTOOOOO
//				    	Fornitore f=DataB.get(indexF,"ciao");
//				    	Spesa sp=new Spesa(mer,f);
//				    	sp.setVisible(true);
//				    	dispose();
			    	}
			    	catch (Exception y){
			    		// ERRORE
			    	}
		    	}
		    	else {
		    	}
			}
		});
		
		sud.add(ord);
		c.add("Center", contenuto);
		c.add("South", sud);
		pack();
	}
}
