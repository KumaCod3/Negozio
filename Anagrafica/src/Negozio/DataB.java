package Negozio;
import java.util.*;

public class DataB <T extends Anagrafica> {
	static public ArrayList<Cliente> clienti=new ArrayList<Cliente>();
	static public ArrayList<Fornitore> fornitori=new ArrayList<Fornitore>();
	
	static public void agg(Cliente t){
		if (eDuplicato(t)==false){
			clienti.add(t);
		}
		else {
		}
	}
	
	static public void agg(Fornitore t){
		if (eDuplicato(t)==false){
			fornitori.add(t);
		}
		else {
		}
	}

	static public Fornitore get(int x ,String y) throws Exception{
		if (x>=fornitori.size())throw new Exception("Index too big");
		else return fornitori.get(x);
	}
	
	static public int trovaNome(String nome, int y){
		if (clienti.size()>0){
			for (Cliente t:clienti){
				if (t.getNome().equals(nome)){
					return clienti.indexOf(t);
				}
			}
		}
		return -1;
	}
	
	static public int trovaNome(String nome){
		if (fornitori.size()>0){
			for (Fornitore t:fornitori){
				if (t.getNome().equals(nome)){
					return fornitori.indexOf(t);
				}
			}
		}
		return -1;
	}
	
	static public int trovaCognome(String cognome, int y){
		if (clienti.size()>0){
			for (Cliente t:clienti){
				if (t.getCognome().equals(cognome)){
					return clienti.indexOf(t);
				}
			}
		}
		return -1;
	}
	
	static public int trovaCognome(String cognome){
		if (fornitori.size()>0){
			for (Fornitore t:fornitori){
				if (t.getCognome().equals(cognome)){
					return fornitori.indexOf(t);
				}
			}
		}
		return -1;
	}
	
	static public int trovaPersona(String cogn, String nom, int y){
		ArrayList<Integer> indici=new ArrayList<Integer>();
		for (int i=0;i<clienti.size();i++){
			if (clienti.get(i).getCognome().equals(cogn)){
				indici.add(i);
			}
		}
		for (int a:indici){
			if (clienti.get(a).getNome().equals(nom)){
				return a;
			}
		}
		return -1;
	}
	
	static public int trovaPersona(String cogn, String nom){
		ArrayList<Integer> indici=new ArrayList<Integer>();
		for (int i=0;i<fornitori.size();i++){
			if (fornitori.get(i).getCognome().equals(cogn)){
				indici.add(i);
			}
		}
		for (int a:indici){
			if (fornitori.get(a).getNome().equals(nom)){
				return a;
			}
		}
		return -1;
	}

	static public boolean eDuplicato(Cliente ogg){
		String[] nom={ogg.getNome(), ogg.getCognome()};
		if (trovaCognome(nom[0], 5)!=-1){
			if (trovaNome(nom[1], 5)!=-1){
				return true;
			}
		}
		return false;
	}
	
	static public boolean eDuplicato(Fornitore ogg){
		String[] nom={ogg.getNome(), ogg.getCognome()};
		if (trovaCognome(nom[0])!=-1){
			if (trovaNome(nom[1])!=-1){
				return true;
			}
		}
		return false;
	}

	static public Fornitore trovaForn(Anagrafica x)throws Exception{
		int index=trovaPersona(x.getCognome(), x.getNome());
		return get(index ,"Fornitore");
	}

	static public void remove(int x){
		for (Fornitore f:fornitori){
			f.removeMerc(x);
		}
	}
}
