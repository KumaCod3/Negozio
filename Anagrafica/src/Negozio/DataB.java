package Negozio;
import GUI.*;
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

	static public Cliente get(int x, int y) throws Exception{
		if (x>=clienti.size())throw new Exception("Indice troppo grande");
		else return clienti.get(x);
	}
	static public Fornitore get(int x ,String y) throws Exception{
		if (x>=fornitori.size())throw new Exception("Indice troppo grande");
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
	
	static public boolean eDuplicato(String nominativo, int cli){
		String[] nom=nominativo.split(" ");
		if (nom.length==2){
			if (trovaCognome(nom[0], 5)!=-1||trovaCognome(nom[1], 5)!=-1){
				if (trovaNome(nom[1], 5)!=-1||trovaNome(nom[0], 5)!=-1){
					return true;
				}
			}
		}
		return false;
	}
	static public boolean eDuplicato(String nominativo){
		String[] nom=nominativo.split(" ");
		if (nom.length==2){
			if (trovaCognome(nom[0])!=-1||trovaCognome(nom[1])!=-1){
				if (trovaNome(nom[1])!=-1||trovaNome(nom[0])!=-1){
					return true;
				}
			}
		}
		return false;
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
	
	static public boolean eDuplicato(String co, String no,int cli){
		if (trovaCognome(co, cli)!=-1||trovaCognome(no, cli)!=-1){
			if (trovaNome(co, cli)!=-1||trovaNome(no, cli)!=-1){
				return true;
			}
		}
		return false;
	}
	static public boolean eDuplicato(String co, String no){
		if (trovaCognome(co)!=-1||trovaCognome(no)!=-1){
			if (trovaNome(co)!=-1||trovaNome(no)!=-1){
				return true;
			}
		}
		return false;
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
	
	static public int trovaIva(String iva, int y){
		if (fornitori.size()>0){
			for (int i=0;i<fornitori.size();i++){
				if (fornitori.get(i).getIva().equals(iva)){
					return i;
				}
			}
		}
		return -1;
	}
	static public int trovaIva(String iva ,String y){
		if (clienti.size()>0){
			for (int i=0;i<clienti.size();i++){
				if (clienti.get(i).getIva().equals(iva)){
					return i;
				}
			}
		}
		return -1;
	}
	
	static public String toString(int y){
		String ss="";
		if (clienti.size()>0){
			for (Cliente a:clienti){
				ss=ss+a.getCognome()+" "+a.getNome()+"\n";
			}
		}
		return ss;
	}
	static public String toString(String y){
		String ss="";
		if (fornitori.size()>0){
			for (Fornitore a:fornitori){
				ss=ss+a.getCognome()+" "+a.getNome()+"\n";
			}
		}
		return ss;
	}
	
	static public String elenCogn(int a, int y){
		return clienti.get(a).getCognome();
	}
	static public String elenCogn(int a ,String y){
		return fornitori.get(a).getCognome();
	}
	static public Fornitore trovaForn(Anagrafica x)throws Exception{
		int index=trovaPersona(x.getCognome(), x.getNome());
		return get(index ,"Fornitore");
	}
	static public Cliente trovaClie(Anagrafica x)throws Exception{
		int index=trovaPersona(x.getCognome(), x.getNome());
		return get(index,0);
	}
	static public void remove(int x){
		for (Fornitore f:fornitori){
			f.removeMerc(x);
		}
	}
}
