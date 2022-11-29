package Negozio;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map.Entry;

import GUI.Errore;
import GUI.Spesa;

public class ListaSpesa{
	Cliente cliente;
	Double saldo;
	LocalDateTime data;
	public HashMap<Integer,Merce> elenco=new HashMap<Integer,Merce>();
	
	public ListaSpesa(Cliente c){
		cliente=c;
		data=LocalDateTime.now();
		saldo=0.0;
	}
	
	public Cliente getCliente(){
		return cliente;
	}
	
	public LocalDateTime getData(){
		return data;
	}
	
	public boolean compra(int merce,Double quantita, Spesa spes){
		Merce x=new Merce(DataM.get(merce));
		for (Merce m:elenco.values()){
			if (m.getNome().equals(x.getNome())){
				m.setQuantita(quantita+(m.getQuantita()));
				if (!check(m)){
					fix(m,spes);

				}
				calcolaSaldo();
				return false;
			}
		}
		x.setQuantita(quantita);
		if (!check(x)){
			fix(x,spes);
		}
		elenco.put(merce, x);
		calcolaSaldo();
		return true;
	}
	
	public Double getSaldo(){
		calcolaSaldo();
		return saldo;
	}

	public Merce get(int x){
		return elenco.get(x);
	}

	public int trovaNome(String nome){
		for (Entry<Integer,Merce> entry:elenco.entrySet()){
			String part=entry.getValue().toString();
			String[] pez=part.split(" ");
			if(nome.equals(pez[0])){
				return entry.getKey();
			}
		}
		return -1;
	}

	public void elimina(int x){
		elenco.remove(x);
		calcolaSaldo();
	}

	public void calcolaSaldo(){
		saldo=0.0;
		for (Merce m:elenco.values()){
			saldo=saldo+(m.getQuantita()*m.getPrezzoV());
		}
	}
	
	public Double qtTot(){
		Double tot=0.0;
		for (Merce m:elenco.values()){
			tot=tot+(m.getQuantita());
		}
		return tot;
	}
	
	public void concludi(){
		
		for (Merce m:elenco.values()){
			try{
				DataM.acquista(m);
			}
			catch (AoOexception e){
				continue;
			}
		}
		calcolaSaldo();
		cliente.setSaldo(cliente.getSaldo()-saldo);
	}
	
	public boolean check(Merce m){
		if (DataM.elenco.get(m.getCod()).getQuantita()<m.getQuantita()){
			return false;
		}
		return true;
	}
	
	public void fix(Merce m, Spesa spes){
		Errore er=new Errore(m,ListaSpesa.this, spes);
		er.setVisible(true);
	}
}

