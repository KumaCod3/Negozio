package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JPanel;

public class ModPersona extends Finestra{
	int codice;
	String cognome;
	String nome;
	String telefono;
	String email;
	String stato;
	String citta;
	String indirizzo;
	String iva="";
	Double saldo=0.00;
	String note;
	
	
	public ModPersona(int x,String tipo){
		super("Edit person");
		String data="";
		codice=x;
		if (tipo.equals("fornitore")){
			try {
				data=Main.db.leggiForID(x);
			}
			catch (SQLException e){	e.printStackTrace();	}
		}
		else {
			try {
				data=Main.db.leggiCliID(x);
			}
			catch (SQLException e){	e.printStackTrace();	}
		}
		String[] spl=data.split(",");
		this.nome=spl[1];
		this.cognome=spl[2];
		this.telefono=spl[3];
		this.email=spl[4];
		this.stato=spl[5];
		this.citta=spl[6];
		this.indirizzo=spl[7];
		this.iva=spl[8];
		this.saldo=Double.parseDouble(spl[9]);
		this.note=spl[10];
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(10,1));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(1,2));
		panel_1.setOpaque(false);
		contenuto.add(panel_1);
		
		Etichetta ty=new Etichetta("Type of preson: ");
		panel_1.add(ty);
		
		Etichetta ty1=new Etichetta(""+tipo);
	    panel_1.add(ty1);
	    
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new GridLayout(1,2));
		panel_2.setOpaque(false);
		contenuto.add(panel_2);
		
		Etichetta un=new Etichetta("Title: ");
		panel_2.add(un);
		
		Etichetta un1=new Etichetta("titolo");
		panel_2.add(un1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new GridLayout(1,2));
		panel_3.setOpaque(false);
		contenuto.add(panel_3);
		
		Etichetta non=new Etichetta("Name: ");
		panel_3.add(non);
		
		FormVuoto tf1 = new FormVuoto(nome);
		panel_3.add(tf1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new GridLayout(1,2));
		panel_4.setOpaque(false);
		contenuto.add(panel_4);
		
		Etichetta qtt=new Etichetta("Last Name: ");
		panel_4.add(qtt);
		
		FormVuoto tf2 = new FormVuoto(cognome);
		panel_4.add(tf2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new GridLayout(1,2));
		panel_5.setOpaque(false);
		contenuto.add(panel_5);
		
		Etichetta tel=new Etichetta("Phone: ");
		panel_5.add(tel);
		
		FormVuoto tf3 = new FormVuoto(telefono);
		panel_5.add(tf3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(new GridLayout(1,2));
		panel_6.setOpaque(false);
		contenuto.add(panel_6);
		
		Etichetta ty_1 = new Etichetta("eMail:   ");
		panel_6.add(ty_1);
		
		FormVuoto tf4 = new FormVuoto(email);
		panel_6.add(tf4);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(new GridLayout(1,2));
		panel_7.setOpaque(false);
		contenuto.add(panel_7);
		
		Etichetta ty_2 = new Etichetta("VAT number:  ");
		panel_7.add(ty_2);
		
		FormVuoto tf5 = new FormVuoto(iva);
		panel_7.add(tf5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(new GridLayout(1,2));
		panel_8.setOpaque(false);
		contenuto.add(panel_8);
		
		Etichetta ty_3 = new Etichetta("Address: ");
		panel_8.add(ty_3);
		
		FormVuoto tf6 = new FormVuoto(indirizzo);
		panel_8.add(tf6);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(new GridLayout(1,2));
		panel_9.setOpaque(false);
		contenuto.add(panel_9);
		
		Etichetta tchtOpeningBal = new Etichetta("Opening Balance: ");
		tchtOpeningBal.setText(""+saldo);
		panel_9.add(tchtOpeningBal);
		
		FormVuoto tf7 = new FormVuoto("balance");
		panel_9.add(tf7);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(new GridLayout(1,2));
		panel_10.setOpaque(false);
		c.add("South", panel_10);
		
		Bottone bex=new Bottone("Back");
		bex.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		panel_10.add(bex);
		
		Bottone bent=new Bottone("ENTER");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
				telefono=tf3.ret;
				email=tf4.ret;
				iva=tf5.ret;
				indirizzo=tf6.ret;
				if (tf7.ret.equals("balance")){
					saldo=0.0;
				}
				else{
					try {
						saldo=Double.parseDouble(tf7.ret);
					}
					catch (Exception ex){
						Errore err=new Errore("Enter number with . ");
						err.setVisible(true);
					}
				}
		    	
		    	if (tipo.contentEquals("cliente")){
		    		try {
		    			Main.db.modCliID(codice, nome, cognome, telefono, email, "SS", citta, indirizzo, iva, saldo, note);
		    		} catch (SQLException ex) {	ex.printStackTrace(); }
		    		
		    		
			    		ConsultaPersone consultaP=new ConsultaPersone();
				    	consultaP.setVisible(true);
				    	dispose();
		    	}
		    	else if (tipo.contentEquals("fornitore")){
		    		try {
		    			Main.db.modForID(codice, nome, cognome, telefono, email, stato, citta, indirizzo, iva, saldo, note);
		    		} catch (SQLException ex) {	ex.printStackTrace(); }
		    			

			    		ConsultaPersone consultaP=new ConsultaPersone();
				    	consultaP.setVisible(true);
				    	dispose();
		    	}
			}
		});
		panel_10.add(bent);
		
		c.add("Center", contenuto);
		pack();
    		
    }
}