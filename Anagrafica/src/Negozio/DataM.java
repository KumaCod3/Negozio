package Negozio;
import java.util.HashMap;
import java.util.Map.Entry;

public class DataM {
	static public HashMap<Integer,Merce> elenco=new HashMap<Integer,Merce>();
	
	static public void agg(Merce t){
		if (eDuplicato(t)==false){
			elenco.put(t.getCod(),t);
		}
		else {
		}
	}
	
	static public void agg(Merce t,int ind){
		elenco.put(ind,t);
	}
	
	static public Merce get(int x){
		return elenco.get(x);
	}
	
	static public int nextIndice(){
		for (int i=1;i>0;i++){
			if(elenco.containsKey(i)==false){
				return i;
			}
		}
		return -1;
	}
	
	static public int trovaNome(String nome){
		for (Entry<Integer,Merce> entry:elenco.entrySet()){
			if(nome.equals(entry.getValue().getNome())){
				return entry.getKey();
			}
		}
		return -1;
	}
	
	public static void acquista(Merce m) throws AoOexception{
		int index=trovaNome(m.getNome());
		Double qt=(elenco.get(index).getQuantita())-(m.getQuantita());
		if (qt<0){
			throw new AoOexception("Out Of Order",index);
			
		}
		else {
			elenco.get(index).setQuantita(qt);
			return;
		}
	}

	static public boolean eDuplicato(Merce m){
		for (Merce a:elenco.values()){
			if (a.equals(m)){
				return true;
			}
		}
		return false;
	}
	
	static public void elimina(int x){
		elenco.remove(x);
		DataB.remove(x);
	}
}
