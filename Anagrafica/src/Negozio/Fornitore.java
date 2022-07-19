package Negozio;
import java.util.ArrayList;
import java.util.HashMap;

import GUI.*;

public class Fornitore extends Anagrafica {
	public HashMap<Integer,Merce> elenco=new HashMap<Integer,Merce>();
	
	public Fornitore(String titolo,String nome,String cognome,String telefono,String email,String iva,String indirizzo,Double saldo){
		super(titolo, nome, cognome, telefono, email, iva, indirizzo, saldo);
		setTipo("fornitore");
	}

	public void pagamentoEffettuato(Double pagamento){
		setSaldo(getSaldo()+pagamento);
	}
	public void consegnaMerci(Double costo){
		setSaldo(getSaldo()-costo);
		System.out.println("merce ricevuta da "+getNome()+" fattuira registrata per P.IVA: "+getIva());
	}
	public HashMap<Integer,Merce> getMerc(){
		return elenco;
	}
	public void addMerc(Merce m){
		int x=m.getCod();
		elenco.put(x,m);
	}
	public void addMerc(int x){
		elenco.put(x,DataM.get(x));
	}
	public void removeMerc(int x){
		elenco.remove(x);
	}
	public void removeMerc(String name){
		int x=DataM.trovaNome(name);
		elenco.remove(x);
	}
}