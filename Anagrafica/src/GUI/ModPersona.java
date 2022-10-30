package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import Negozio.Anagrafica;
import Negozio.DataB;

public class ModPersona extends Finestra{
	String cognome="";
	String nome="";
	String telefono="";
	String email="";
	String indirizzo="";
	String tipo="";
	Double saldo=0.0;
	String titolo="";
	String iva="";
	boolean sett;
	Anagrafica mer=null;
	
	public ModPersona(int x,String tipo){
		super("Edit person");
		
		if (tipo.equals("fornitore")){
			try {
				mer=DataB.fornitori.get(x);
			}
			catch (Exception e){
				Errore err=new Errore("Wrong Index...");
				err.setVisible(true);
			    ConsultaPersone consultaP=new ConsultaPersone();
			    consultaP.setVisible(true);
			    setVisible(false);
			   	dispose();
			}
		}
		else {
			try {
				mer=DataB.clienti.get(x);
			}
			catch (Exception e){
				Errore err=new Errore("Wrong Index...");
				err.setVisible(true);
			   	ConsultaPersone consultaP=new ConsultaPersone();
			   	consultaP.setVisible(true);
			   	setVisible(false);
			   	dispose();		
			}
		}
		
		c.setLayout(new BorderLayout(100,10));
		
		Panel contenuto=new Panel();
		contenuto.setLayout(new GridLayout(10,2));
		
		titolo=mer.getTitolo();
		iva=mer.getIva();
		telefono=mer.getTelefono();
		email=mer.getEmail();
		saldo=mer.getSaldo();
		indirizzo=mer.getIndirizzo();
		
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		panel_1.setOpaque(false);
		contenuto.add(panel_1);
		
		Etichetta ty=new Etichetta("Type of preson:                                      ");
		panel_1.add(ty);
		
		Etichetta ty1=new Etichetta(""+tipo);
	    panel_1.add(ty1);
	    
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		panel_2.setOpaque(false);
		contenuto.add(panel_2);
		
		Etichetta un=new Etichetta("Title:                                              ");
		panel_2.add(un);
		
		Etichetta un1=new Etichetta(mer.getTitolo());
		panel_2.add(un1);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		panel_3.setOpaque(false);
		contenuto.add(panel_3);
		
		Etichetta non=new Etichetta("Name: ");
		panel_3.add(non);
		
		FormVuoto tf1 = new FormVuoto(mer.getNome());
		panel_3.add(tf1);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setAlignment(FlowLayout.TRAILING);
		panel_4.setOpaque(false);
		contenuto.add(panel_4);
		
		Etichetta qtt=new Etichetta("Last Name: ");
		panel_4.add(qtt);
		
		FormVuoto tf2 = new FormVuoto(mer.getCognome());
		panel_4.add(tf2);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_5.getLayout();
		flowLayout_5.setAlignment(FlowLayout.TRAILING);
		panel_5.setOpaque(false);
		contenuto.add(panel_5);
		
		Etichetta tel=new Etichetta("Phone: ");
		panel_5.add(tel);
		
		FormVuoto tf3 = new FormVuoto(mer.getTelefono());
		panel_5.add(tf3);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_6.getLayout();
		flowLayout_6.setAlignment(FlowLayout.TRAILING);
		flowLayout_6.setAlignOnBaseline(true);
		panel_6.setOpaque(false);
		contenuto.add(panel_6);
		
		Etichetta ty_1 = new Etichetta("eMail:   ");
		panel_6.add(ty_1);
		
		FormVuoto tf4 = new FormVuoto(mer.getEmail());
		panel_6.add(tf4);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_7.getLayout();
		flowLayout_7.setAlignment(FlowLayout.TRAILING);
		flowLayout_7.setAlignOnBaseline(true);
		panel_7.setOpaque(false);
		contenuto.add(panel_7);
		
		Etichetta ty_2 = new Etichetta("VAT number:  ");
		panel_7.add(ty_2);
		
		FormVuoto tf5 = new FormVuoto(mer.getIva());
		panel_7.add(tf5);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_8.getLayout();
		flowLayout_4.setAlignment(FlowLayout.TRAILING);
		FlowLayout flowLayout_8 = (FlowLayout) panel_7.getLayout();
		flowLayout_8.setAlignment(FlowLayout.TRAILING);
		flowLayout_8.setAlignOnBaseline(true);
		panel_8.setOpaque(false);
		contenuto.add(panel_8);
		
		Etichetta ty_3 = new Etichetta("Address: ");
		panel_8.add(ty_3);
		
		FormVuoto tf6 = new FormVuoto(mer.getIndirizzo());
		panel_8.add(tf6);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_9.getLayout();
		flowLayout_11.setAlignment(FlowLayout.TRAILING);
		FlowLayout flowLayout_9 = (FlowLayout) panel_7.getLayout();
		flowLayout_9.setAlignment(FlowLayout.TRAILING);
		flowLayout_9.setAlignOnBaseline(true);
		panel_9.setOpaque(false);
		contenuto.add(panel_9);
		
		Etichetta tchtOpeningBal = new Etichetta("Opening Balance: ");
		tchtOpeningBal.setText(""+mer.getSaldo());
		panel_9.add(tchtOpeningBal);
		
		FormVuoto tf7 = new FormVuoto("balance");
		panel_9.add(tf7);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_7.getLayout();
		flowLayout_10.setAlignment(FlowLayout.TRAILING);
		flowLayout_10.setAlignOnBaseline(true);
		panel_10.setOpaque(false);
		c.add("South", panel_10);
		
		Bottone bex=new Bottone("EXIT");
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
		    		
			    		DataB.clienti.get(x).setIva(iva);
			    		DataB.clienti.get(x).setTelefono(telefono);
			    		DataB.clienti.get(x).setEmail(email);
			    		DataB.clienti.get(x).setSaldo(saldo);
			    		DataB.clienti.get(x).setIndirizzo(indirizzo);
			    		ConsultaPersone consultaP=new ConsultaPersone();
				    	consultaP.setVisible(true);
				    	MyReadC.scarica();
				    	dispose();
		    		}
		    		catch (Exception r){
		    			Errore err=new Errore("Wrong Index...");
		    			err.setVisible(true);
					    ConsultaPersone consultaP=new ConsultaPersone();
					    consultaP.setVisible(true);
					    setVisible(false);
					    dispose();			    			
		    		}
		    	}
		    	else if (tipo.contentEquals("fornitore")){
		    		try {
		    			DataB.fornitori.get(x).setIva(iva);
		    			DataB.fornitori.get(x).setTelefono(telefono);
		    			DataB.fornitori.get(x).setEmail(email);
		    			DataB.fornitori.get(x).setSaldo(saldo);
		    			DataB.fornitori.get(x).setIndirizzo(indirizzo);
			    		ConsultaPersone consultaP=new ConsultaPersone();
				    	consultaP.setVisible(true);
				    	MyReadF.scarica();
				    	dispose();
		    		}
		    		catch (Exception u){
		    			Errore err=new Errore("Wrong Index...");
		    			err.setVisible(true);
					    ConsultaPersone consultaP=new ConsultaPersone();
					    consultaP.setVisible(true);
					    setVisible(false);
					    dispose();	
		    		}
		    	}
			}
		});
		panel_10.add(bent);
		
		
		c.add("Center", contenuto);
		pack();
    		
    }
}