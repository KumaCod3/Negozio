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
//					System.out.println("fix a: "+m.getQuantita()+" in magaz: "+DataM.elenco.get(m.getCod()).getQuantita());
				}
				calcolaSaldo();
				return false;
			}
		}
		x.setQuantita(quantita);
		if (!check(x)){
			fix(x,spes);
//			System.out.println("fix a: "+x.getQuantita()+" in magaz: "+DataM.elenco.get(x.getCod()).getQuantita());
		}
		elenco.put(merce, x);
		calcolaSaldo();
		return true;
	}
	
	public Double getSaldo(){
		calcolaSaldo();
		return saldo;
	}
	
	public String toString(){
		String stampa="";
		for (Merce a:elenco.values()){
			stampa=stampa+a+"/n";
		}
		stampa=stampa+"TOT. "+saldo+"eu.";
		return stampa;
	}
	public String salva(){
		String sav=data+" "+cliente.getNome()+" "+cliente.getCognome()+" tot to pay: "+saldo+"eu./n";
		for (int k:elenco.keySet()){
			Double x=elenco.get(k).getPrezzoV();
			DataM.get(k).togli(x);
		}
		return sav;
	}
	
	public Merce get(int x){
		return elenco.get(x);
	}
	public int nextIndice(){
		for (int i=1;i>0;i++){
			if(elenco.containsKey(i)==false){
				return i;
			}
		}
		return -1;
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

	public boolean eDuplicato(Merce m){
		for (Merce a:elenco.values()){
			if (a.equals(m)){
				return true;
			}
		}
		return false;
	}
	
	public boolean eDuplicato(String nome){
		for (Merce a:elenco.values()){
			if (a.getNome().equals(nome)){
				return true;
			}
		}
		return false;
	}
	public int trovaNumero(String nome){
		for (int i=0;i<elenco.size();i++){
			if (elenco.get(i).getNome().equals(nome)){
				return i;
			}
		}
		return -1;
	}
	public boolean controllaKey(int k){
		for (int a: elenco.keySet()){
			if (a==k){
				return true;
			}
		}
		return false;
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
//		System.out.println("rich: "+m.getQuantita()+" in magaz: "+DataM.elenco.get(m.getCod()).getQuantita());
		Errore er=new Errore(m,ListaSpesa.this, spes);
		er.setVisible(true);
	}
}

