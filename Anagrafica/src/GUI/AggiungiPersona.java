package GUI;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AggiungiPersona extends Finestra{
	String tipo;
	String cognome;
	String nome;
	String telefono;
	String email;
	String stato="ST";
	String citta="city";
	String indirizzo;
	String iva="";
	Double saldo=0.00;
	String note=" ";
	
	public AggiungiPersona() {
		super("Add new Person");
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(9,1));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(1,3));
		
		panel_1.setOpaque(false);
		contenuto.add(panel_1);
		
		Etichetta ty=new Etichetta("Choose DB:                       ");
		panel_1.add(ty);
		
		JRadioButton client = new JRadioButton("Customer");
		client.setOpaque(false);
	    client.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            tipo="cliente";
	        }
	    });
	    panel_1.add(client);
		JRadioButton fornit = new JRadioButton("Supplier");
 		fornit.setOpaque(false);
	    fornit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	tipo="fornitore";
	        }
	    });
	    panel_1.add(fornit);
	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(client);
	    group.add(fornit);
	    
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new GridLayout(1,2));
		panel_2.setOpaque(false);
		contenuto.add(panel_2);
		
		Etichetta un=new Etichetta("Title: ");
		panel_2.add(un);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new GridLayout(1,2));
		panel_3.setOpaque(false);
		contenuto.add(panel_3);
		
		Etichetta non=new Etichetta("Name: ");
		panel_3.add(non);
		
		FormVuoto tf1 = new FormVuoto("name");
		panel_3.add(tf1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new GridLayout(1,2));
		panel_4.setOpaque(false);
		contenuto.add(panel_4);
		
		Etichetta qtt=new Etichetta("Last Name: ");
		panel_4.add(qtt);
		
		FormVuoto tf2 = new FormVuoto("last n.");
		panel_4.add(tf2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new GridLayout(1,2));
		panel_5.setOpaque(false);
		contenuto.add(panel_5);
		
		Etichetta tel=new Etichetta("Phone: ");
		panel_5.add(tel);
		
		FormVuoto tf3 = new FormVuoto("phone");
		panel_5.add(tf3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(new GridLayout(1,2));
		panel_6.setOpaque(false);
		contenuto.add(panel_6);
		
		Etichetta ty_1 = new Etichetta("eMail:   ");
		panel_6.add(ty_1);
		
		FormVuoto tf4 = new FormVuoto("mail");
		panel_6.add(tf4);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(new GridLayout(1,2));
		panel_7.setOpaque(false);
		contenuto.add(panel_7);
		
		Etichetta ty_2 = new Etichetta("VAT number:  ");
		panel_7.add(ty_2);
		
		FormVuoto tf5 = new FormVuoto("VAT");
		panel_7.add(tf5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(new GridLayout(1,2));
		panel_8.setOpaque(false);
		contenuto.add(panel_8);
		
		Etichetta ty_3 = new Etichetta("Address: ");
		panel_8.add(ty_3);
		
		FormVuoto tf6 = new FormVuoto("address");
		panel_8.add(tf6);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(new GridLayout(1,2));
		panel_9.setOpaque(false);
		contenuto.add(panel_9);
		
		Etichetta tchtOpeningBal = new Etichetta("Opening Balance: ");
		tchtOpeningBal.setText("Opening Bal.:");
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
				nome=tf1.ret;
				cognome=tf2.ret;
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
		    		String dati=nome+"','"+cognome+"','"+telefono+"','"+email+"','"+stato+"','"+citta+"','"+indirizzo+"','"+iva+"',"+saldo+",'"+note;
		    		try {
		    			Main.db.aggCli(dati);
		    		} catch (SQLException ex) {	ex.printStackTrace(); }
		    		
		    		ConsultaPersone consultaP=new ConsultaPersone();
			    	consultaP.setVisible(true);
			    	dispose();
		    	}
		    	else if (tipo.contentEquals("fornitore")){
		    		try {
		    			String dati=nome+"','"+cognome+"','"+telefono+"','"+email+"','"+stato+"','"+citta+"','"+indirizzo+"','"+iva+"',"+saldo+",'"+note;
			    		Main.db.aggFor(dati);
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
