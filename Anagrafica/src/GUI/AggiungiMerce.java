package GUI;
import Negozio.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		JPanel foot=new JPanel();
		foot.setBorder(Est.bordo);
		foot.setOpaque(false);
//		foot.setBorder(BorderFactory.createEmptyBorder(100,10,100,10));
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
		
		
		
/*		
		
		JPanel fin=new JPanel();
		fin.setBorder(Est.bordo);
		fin.setOpaque(false);
		fin.setLayout(new GridLayout(1,2));
		Etichetta ri=new Etichetta("Selling price: ");
		fin.add(ri);
		rr=new Etichetta(Est.deci.format(prezzoV)+"eu");
		fin.add(rr);
		contenuto.add(fin);
		
Etichetta etUni = new Etichetta("Unit of measure:              ");
		contenuto.add(etUni);
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
		contenuto.add(un);
		Etichetta vv=new Etichetta(" ");
		contenuto.add(vv);
		
Etichetta car=new Etichetta(" % increase");
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
			          rincaro=Double.parseDouble(itt)/100;
			          ricalSell();
			       }
			}
		});
		contenuto.add(rim);
	
		Bottone bent = new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

			    	nome=tf1.ret;
			    	try {
			    		quantita=Double.parseDouble(tf2.ret);
			    		prezzoA=Double.parseDouble(tf3.ret);
			    		
			    		try {
				    		String dati=nome+"', '"+unita+"', "+quantita+", "+prezzoA+", "+sconto+", "+rincaro+", '"+"note";
				    		Main.db.aggMerc(dati);
			    		} catch (SQLException ex) {	ex.printStackTrace(); }
			    		
						ConsultaMerci consultaM=new ConsultaMerci();
				    	consultaM.setVisible(true);
//				    	MyReadM.scarica();
				    	setVisible(false);
				    	dispose();
			    	}
			    	catch (Exception ex){
			    		Errore err=new Errore("Enter number with . ");
			    		err.setVisible(true);
			    	}
			}
		});
		contenuto.add(bent);
		// bottone salva
		
Etichetta sco=new Etichetta(" %  deal");
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
			          sconto=Double.parseDouble(itt)/100;
			          ricalSell();
			       }
			}
		});
		contenuto.add(scc);

		Bottone bex = new Bottone("cancel");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		contenuto.add(bex);
		
///////////		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		flowLayout.setAlignOnBaseline(true);
		panel_1.setOpaque(false);
		contenuto.add(panel_1);
		
		Etichetta etNome = new Etichetta("Product:             ");
		panel_1.add(etNome);
		
		FormVuoto tf1 = new FormVuoto(nome);
		panel_1.add(tf1);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		panel_2.setOpaque(false);
		contenuto.add(panel_2);
		
		Etichetta etUni = new Etichetta("Unit of measure:              ");
		panel_2.add(etUni);
		
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
		panel_2.add(un);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		panel_3.setOpaque(false);
		contenuto.add(panel_3);
		
		Etichetta etQuant = new Etichetta("Quantity:            ");
		panel_3.add(etQuant);
		
		FormVuoto tf2 = new FormVuoto(""+quantita);
		panel_3.add(tf2);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setAlignment(FlowLayout.TRAILING);
		panel_4.setOpaque(false);
		contenuto.add(panel_4);
		
		Etichetta etPri  = new Etichetta("Purchase price:            ");
		panel_4.add(etPri);
		
		FormVuoto tf3 = new FormVuoto(prezzoA+"");
		panel_4.add(tf3);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_5.getLayout();
		flowLayout_5.setAlignment(FlowLayout.TRAILING);
		panel_5.setOpaque(false);
		contenuto.add(panel_5);
		
		Etichetta etRinc = new Etichetta("Choose %price increase:  ");
		panel_5.add(etRinc);
		
		Choice rim = new Choice();
		rim.setPreferredSize(Est.choi);
		rim.add(""+(rincaro*100));
		rim.add("0");
		rim.add("10");
		rim.add("20");
		rim.add("30");
		rim.add("50");
		rim.setFont(Est.plainFont);
		rim.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (rim.getSelectedIndex()>0){
					rincaro=Double.parseDouble(rim.getSelectedItem())/100;
				}
			}
		});
		panel_5.add(rim);
		
//-----------new
		
		JPanel panel_5b = new JPanel();
		FlowLayout flowLayout_5b = (FlowLayout) panel_5b.getLayout();
		flowLayout_5b.setAlignment(FlowLayout.TRAILING);
		panel_5b.setOpaque(false);
		contenuto.add(panel_5b);
		
		
		Etichetta etSco = new Etichetta("Choose % deal:      ");
		panel_5b.add(etSco);
		
		Choice sco = new Choice();
		sco.setPreferredSize(Est.choi);
		sco.add(""+(rincaro*100));
		sco.add("0");
		sco.add("10");
		sco.add("20");
		sco.add("30");
		sco.add("50");
		sco.setFont(Est.plainFont);
		sco.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (sco.getSelectedIndex()>0){
					sconto=Double.parseDouble(sco.getSelectedItem())/100;
				}
			}
		});
		panel_5b.add(sco);
		
/// ---------------fin
		
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		contenuto.add(panel_6);
		
		Bottone bex = new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		
		panel_6.add(bex);
		
		Bottone bent = new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

			    	nome=tf1.ret;
			    	try {
			    		quantita=Double.parseDouble(tf2.ret);
			    		prezzoA=Double.parseDouble(tf3.ret);
			    		
			    		try {
				    		String dati=nome+"', '"+unita+"', "+quantita+", "+prezzoA+", "+sconto+", "+rincaro+", '"+"note";
				    		Main.db.aggMerc(dati);
			    		} catch (SQLException ex) {	ex.printStackTrace(); }
			    		
						ConsultaMerci consultaM=new ConsultaMerci();
				    	consultaM.setVisible(true);
//				    	MyReadM.scarica();
				    	setVisible(false);
				    	dispose();
			    	}
			    	catch (Exception ex){
			    		Errore err=new Errore("Enter number with . ");
			    		err.setVisible(true);
			    	}
			}
		});
		panel_6.add(bent);
		*/
		
		c.add("Center", contenuto);
		pack();
	}
	
}
