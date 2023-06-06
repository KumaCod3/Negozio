package GUI;
import Negozio.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ModMerce extends Finestra{
		String nome="nome";
		Double quantita=0.0;
		Double prezzoA=0.0;
		Double rincaro=0.0;
		Double sconto=0.0;
		String unita="unita";
		String note="";
		int index;
		
		public ModMerce(int x){
			super("Product");
			
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
					
				} catch (SQLException ex) {	ex.printStackTrace(); }
			}
			
			
			JPanel contenuto=new JPanel();
			contenuto.setBorder(Est.bordo);
			contenuto.setOpaque(false);
			contenuto.setLayout(new GridLayout(7,1));
			
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
			un.add(unita);
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
			
			Etichetta etRinc = new Etichetta("Choose %price increase:              ");
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
			
// ---------new
			JPanel panel_5bis = new JPanel();
			FlowLayout flowLayout_5bis = (FlowLayout) panel_5.getLayout();
			flowLayout_5bis.setAlignment(FlowLayout.TRAILING);
			panel_5bis.setOpaque(false);
			contenuto.add(panel_5bis);
			
			Etichetta etSco = new Etichetta("Choose %price increase:              ");
			panel_5.add(etSco);
			
			Choice sco = new Choice();
			sco.setPreferredSize(Est.choi);
			sco.add(""+(sconto*100));
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
			panel_5bis.add(sco);
			
	// -------------fin
			
			
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

					    		Main.db.modMerc(index, nome, unita, quantita, prezzoA, rincaro, sconto, note);
				    		} catch (SQLException ex) {	ex.printStackTrace(); }
				    		
							ConsultaMerci consultaM=new ConsultaMerci(/*c*/);
					    	consultaM.setVisible(true);
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
			
			c.add("Center", contenuto);
			pack();
		}
	}
