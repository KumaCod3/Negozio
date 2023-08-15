package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.sql.SQLException;

public class SchedaMerce extends Finestra{
	public String nome="";
	int index;
	Double quantita=0.0;
	Double rincaro=0.0;
	Double sconto=0.0;
	Double prezzoA=0.0;
	Double prezzoV=0.0;
	Double valore=0.0;
	String unita="";
	String note="";
	Double rincaroT=0.0;
	Double scontoT=0.0;
	Double prezzoT=0.0;
	Etichetta rr;
	
	public SchedaMerce(int x){
		super("Product details");
		removeWindowListener(getWindowListeners()[0]);
		addWindowListener (new WindowAdapter() {    
            public void windowClosing (WindowEvent e) {    
                dispose(); 
                ConsultaMerci aa=new ConsultaMerci();
                aa.setVisible(true);
            }    
        });
		
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
				this.sconto=Double.parseDouble(spl[6]);
				this.note=spl[7];
				this.prezzoV=prezzoA+(prezzoA*rincaro)-(prezzoA*sconto);
				this.valore=prezzoA*quantita;
				this.rincaroT=this.rincaro;
				this.scontoT=this.sconto;
				this.prezzoT=this.prezzoV;
				
			} catch (SQLException ex) {	ex.printStackTrace(); }
		}
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(4,4));
		
/*comp1*/  Etichetta non=new Etichetta("Product: ");
		contenuto.add(non);	
		Etichetta nn=new Etichetta(""+nome);
		contenuto.add(nn);
		
/*comp2*/  Etichetta ri=new Etichetta("Selling price: ");
		contenuto.add(ri);
		rr=new Etichetta(Est.deci.format(prezzoV)+"eu");
		contenuto.add(rr);
		
/*comp3*/  Etichetta uni=new Etichetta("In stock: ");
		contenuto.add(uni);
		Etichetta uu=new Etichetta(quantita+" "+unita);
		if (quantita<=3.0){
			uu.setForeground(Est.rosso);
			uu.setOpaque(true);
			uni.setOpaque(true);
		}
		contenuto.add(uu);
		
/*comp4*/  Etichetta va=new Etichetta("Total in stock value: ");
		contenuto.add(va);
		Etichetta vv=new Etichetta(Est.deci.format(valore)+"eu");
		contenuto.add(vv);
		
/*comp5*/  Etichetta ac=new Etichetta("Purchase price: ");
		contenuto.add(ac);
		Etichetta aa=new Etichetta(Est.deci.format(prezzoA)+"eu for "+unita);
		contenuto.add(aa);
		
/*comp6*/  Etichetta forn=new Etichetta("Supplier: ");
		contenuto.add(forn);	
		try {
			MyChoice ele1=new MyChoice(Main.db.getElenSuppF(index),5);
			contenuto.add(ele1);
		} catch (SQLException ex) {ex.printStackTrace();}
		
		
/*comp7*/	Etichetta car=new Etichetta(" % increase");
		contenuto.add(car);
		Choice rim = new Choice();
		rim.setPreferredSize(Est.choi);
		rim.add(""+(rincaro*100));
		rim.add("0");
		rim.add("10");
		rim.add("20");
		rim.add("30");
		rim.add("50");
		rim.setFont(Est.plainFont);
		rim.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
			          Object item = event.getItem();
			          String itt=item.toString();
			          rincaroT=Double.parseDouble(itt)/100;
			          ricalSell();
			       }
			}
		});
		contenuto.add(rim);
		
/*comp8*/	Etichetta sco=new Etichetta(" %  deal");
		contenuto.add(sco);
		Choice scc = new Choice();
		scc.setPreferredSize(Est.choi);
		scc.add(""+(sconto*100));
		scc.add("0");
		scc.add("10");
		scc.add("20");
		scc.add("30");
		scc.add("50");
		scc.setFont(Est.plainFont);
		scc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
			          Object item = event.getItem();
			          String itt=item.toString();
			          scontoT=Double.parseDouble(itt)/100;
			          ricalSell();
			       }
			}
		});
		contenuto.add(scc);
		c.add("Center", contenuto);

		JPanel est=new JPanel();
		est.setBorder(Est.bordo);
		est.setOpaque(false);
		est.setLayout(new GridLayout(4,1));
/*side1*/Bottone eli=new Bottone("DELETE");
		eli.but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (x!=-1){
					Errore del=new Errore(SchedaMerce.this);
					del.setVisible(true);
					setVisible(false);
				}
			}
		});
		est.add(eli);
/*side2*/Bottone ord=new Bottone("ORDER");
		ord.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (x!=-1){
		    		
		    		try{
				    	Errore er=new Errore(index, SchedaMerce.this);
				    	er.setVisible(true);
				    	setVisible(false);
			    	}
			    	catch (Exception y){
			    		y.printStackTrace();
			    	}
		    	}
		    	else {
		    	}
			}
		});
		est.add(ord);
		
		
/*side3*/Bottone agg=new Bottone("Assign Product");
		agg.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try{
			    	AssegnaMerc ass=new AssegnaMerc(index, "ciao");
			    	ass.setVisible(true);
			    	setVisible(false);
			    	dispose();
		    	}
		    	catch (Exception pp){
		    	}
			}
		});
		est.add(agg);
/*side4*/Bottone bin=new Bottone("save");
		bin.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (index!=-1){
		    		try {
			    		Main.db.modMerc(index, nome, unita, quantita, prezzoA, rincaroT, scontoT, note);
		    		} catch (SQLException ex) {	ex.printStackTrace(); }
		    	}
			}
		});
		est.add(bin);
		c.add("East",est);
		pack();
	}
	
	private void ricalSell() {
		prezzoT=prezzoA+(prezzoA*rincaroT)-(prezzoA*scontoT);
		rr.setText(Est.deci.format(prezzoT)+"eu");
	}
}
