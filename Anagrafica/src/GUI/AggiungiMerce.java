package GUI;
import Negozio.*;
//import java.util.*;
import java.awt.*;
import Negozio.*;
import javax.swing.*;

import java.awt.event.*;

public class AggiungiMerce extends Frame{
	String nome="";
	Double quantita=0.0;
	Double prezzoA=0.0;
	int rincaro=0;
	String unita="";
	public AggiungiMerce(DataM c){
		super("Aggiungi prodotto");
		setLayout(new BorderLayout(100,10));
		/*layout.numColumns = 3;
    layout.makeColumnsEqualWidth = true;	*/
		setLocation(300,300);
		setBackground(new Color(217,243,248));
		Font ft=new Font("Lucida",Font.PLAIN,24);
		
		Panel contenuto=new Panel();
		contenuto.setLayout(new GridLayout(6,2));

/*comp1*/  JLabel non=new JLabel();
		non.setText("Merce: ");
		non.setFont(ft);
		non.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		contenuto.add(non);
/*comp2*/ JPanel pan1=new JPanel();
		JTextField tf1 = new JTextField("Nome", 15);
		tf1.setForeground(new Color(117,223,235));
		tf1.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
				if (tf1.getText().equals("Nome")){
					tf1.setText("");
					tf1.setForeground(Color.BLACK);
				}
			}
			public void focusLost(FocusEvent e){
				if (tf1.getText().isEmpty()){
					tf1.setText("Nome");
					tf1.setForeground(new Color(117,223,235));
				}
				else nome=tf1.getText();
			}
		});
		tf1.setFont(ft);
		pan1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan1.add(tf1);
		pan1.setBackground(new Color(217,243,248));
		contenuto.add(pan1);
		
/*comp3*/  JLabel un=new JLabel();
		un.setText("Scelta Unita: ");
		un.setFont(ft);
		un.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		contenuto.add(un);
/*comp4*/Choice uni=new Choice();
		uni.add("Scegli");
		uni.add("Pezzi");
		uni.add("Kg");
		uni.add("grammi");
		uni.add("quintali");
		uni.add("tonnellate");
		uni.setFont(ft);
		uni.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (uni.getSelectedIndex()>0){
					unita=uni.getSelectedItem();
				}
			}
		});
		contenuto.add(uni);	
		
/*comp5*/  JLabel qtt=new JLabel();
		qtt.setText("Quantita: ");
		qtt.setFont(ft);
		qtt.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		contenuto.add(qtt);
/*comp6*/JPanel pan2=new JPanel();
		JTextField tf2 = new JTextField("Quantita", 15);
		tf2.setForeground(new Color(117,223,235));
		tf2.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
				if (tf2.getText().equals("Quantita")){
					tf2.setText("");
					tf2.setForeground(Color.BLACK);
				}
			}
			public void focusLost(FocusEvent e){
				if (tf2.getText().isEmpty()){
					tf2.setText("Quantita");
					tf2.setForeground(new Color(117,223,235));
				}
				else {
					try {
						quantita=Double.parseDouble(tf2.getText());
					}
					catch (Exception ex){
						ErrorMessage err=new ErrorMessage("inserire numero con . ");
						err.setVisible(true);
					}
				}
			}
		});
		tf2.setFont(ft);
		pan2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan2.add(tf2);
		pan2.setBackground(new Color(217,243,248));
		contenuto.add(pan2);
		
/*comp7*/  JLabel prr=new JLabel();
		prr.setText("Prezzo d'acquisto: ");
		prr.setFont(ft);
		prr.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		contenuto.add(prr);
/*comp8*/ JPanel pan3=new JPanel();
		JTextField tf3 = new JTextField("Prezzo", 15);
		tf3.setForeground(new Color(117,223,235));
		tf3.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
				if (tf3.getText().equals("Prezzo")){
					tf3.setText("");
					tf3.setForeground(Color.BLACK);
				}
			}
			public void focusLost(FocusEvent e){
				if (tf3.getText().isEmpty()){
					tf3.setText("Prezzo");
					tf3.setForeground(new Color(117,223,235));
				}
				else {
					try {
						prezzoA=Double.parseDouble(tf3.getText());
					}
					catch (Exception ex){
						ErrorMessage err=new ErrorMessage("inserire numero con . ");
						err.setVisible(true);
					}
				}
			}
		});
		tf3.setFont(ft);
		pan3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pan3.add(tf3);
		pan3.setBackground(new Color(217,243,248));
		contenuto.add(pan3);
		
/*comp9*/  JLabel rin=new JLabel();
		rin.setText("Imposta % rincaro: ");
		rin.setFont(ft);
		rin.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 70));
		contenuto.add(rin);
/*comp10*/Choice rim=new Choice();
		rim.add("Scegli");
		rim.add("10");
		rim.add("20");
		rim.add("30");
		rim.add("50");
		rim.setFont(ft);
		rim.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
			}
			public void focusLost(FocusEvent e){
				if (rim.getSelectedIndex()>0){
					rincaro=Integer.parseInt(rim.getSelectedItem())+100;
				}
			}
		});
		contenuto.add(rim);	
			
/*comp11*/Button bex=new Button("-ESCI-");
		bex.setBackground(new Color(2,146,183));
		bex.setPreferredSize(new Dimension(80,50));
		bex.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
			}
		});
		contenuto.add(bex);
		
/*comp12*/Button bent=new Button("-INVIO-");
		bent.setBackground(new Color(26,200,219));
		bent.setPreferredSize(new Dimension(80,50));
		bent.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				Merce inserisci =new Merce(nome, quantita, rincaro, prezzoA, unita,c);
				c.agg(inserisci);
		    	setVisible(false);
			}
		});
		

		contenuto.add(bent);
		
		add(contenuto);
		pack();
	}
}