package Negozio;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;

import GUI.Errore;
import GUI.Main;
import GUI.Spesa;

public class ListaSpesa{
	int IDcli;
	Double saldo;
	LocalDateTime data;
	String fattura="";
	String note="";
	public HashMap<Integer,Double> elenco=new HashMap<Integer,Double>();
	
	public ListaSpesa(int c){
		IDcli=c;
		data=LocalDateTime.now();
		saldo=0.0;
	}
	
	public void compra(int merce,Double quantita, Spesa spes){
		for (int m:elenco.keySet()){
			if (m==merce){
				if (!check(m, elenco.get(m)+quantita)){
					fix(m,spes);
				}
				elenco.put(m, elenco.get(m)+quantita);
				calcolaSaldo();
				return;
			}
		}

		if (!check(merce, quantita)){
			fix(merce,spes);
		}
		elenco.put(merce, quantita);
		calcolaSaldo();
		return;
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
				Main.db.compra(index, quantita);
			} catch (SQLException ex) { ex.printStackTrace(); }
			
		}
		calcolaSaldo();
		try {
			Main.db.aggiornaSaldoCli(IDcli, saldo);
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public boolean check(int m, double quant){
		if (Main.db.getQuantMerc(m)<quant){
			return false;
		}
		return true;
	}
	// TODO
	public void fix(int m, Spesa spes){
//		Errore er=new Errore(m,ListaSpesa.this, spes);
//		er.setVisible(true);
	}
}

