package Negozio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;

import GUI.Errore;
import GUI.Main;
import GUI.Spesa;

public class ListaSpesa{
	int IDtrans;
	int IDcli;
	Double saldo;
	LocalDateTime data;
	String fattura="";
	String note="";
	Spesa ss;
	public HashMap<Integer,Double> elenco=new HashMap<Integer,Double>();
	
	public ListaSpesa(int c, Spesa s){
		ss=s;
		IDcli=c;
		data=LocalDateTime.now();
		saldo=0.0;
		try {
			IDtrans=Main.db.createTransactionIn(c,data);
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public void compra(int merce,Double quantita){	//aggiunge un nuovo prod
		for (int m:elenco.keySet()){
			if (m==merce){
				quantita=elenco.get(m)+quantita;
			}
		}
		elenco.put(merce, quantita);
		
		if (!check(merce))
		regolaQ(merce);

		calcolaSaldo();
		ss.refre();
		return;
	}
	
	private void regolaQ(int merce) {
		Errore er=new Errore(ListaSpesa.this, merce);
		er.setVisible(true);
		er.ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	double qtt=0;
		    	try {
		    		qtt=Double.parseDouble(er.tf1.ret);
		    		elenco.put(merce, qtt);
		    		if (check(merce)){
		    			er.setVisible(false);
				    	er.dispose();
				    	ss.refre();
		    		}
		    		else {
		    			er.setVisible(false);
				    	er.dispose();
				    	regolaQ(merce);
		    		}
		    	}
		    	catch (Exception ex) { ex.printStackTrace(); }
		    	
			}
		});
		
	}

	public Double getSaldo(){
		calcolaSaldo();
		return saldo;
	}

	public void elimina(int x){
		elenco.remove(x);
		calcolaSaldo();
	}

	public void calcolaSaldo(){
		saldo=0.0;
		for (Entry<Integer,Double> m:elenco.entrySet()){
			Double prezzo=Main.db.getPrezzo((int)m.getKey());
			Double quant=(Double)m.getValue();
			saldo=saldo+(quant*prezzo);
		}
	}
	
	public void concludi(){
		for (Entry<Integer,Double> m:elenco.entrySet()){
			int index=m.getKey();
			Double quantita=m.getValue();
			try {
				Main.db.vendi(index, quantita, IDtrans);
			} catch (SQLException ex) { ex.printStackTrace(); }
			
		}
		calcolaSaldo();
		try {
			Main.db.aggiornaSaldoCli(IDcli, saldo);
		} catch (SQLException e) { e.printStackTrace(); }
		try {
			Main.db.aggiornaVendite(IDtrans, saldo,"note");
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public boolean check(int m){
		if (Main.db.getQuantMerc(m)<elenco.get(m)){
			return false;
		}
		return true;
	}

}

