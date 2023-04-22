package Negozio;

import java.util.HashMap;

public class Cliente  extends Anagrafica {
	
	public Cliente(String titolo,String nome,String cognome,String telefono,String email,String iva,String indirizzo,Double saldo){
		super(titolo, nome, cognome, telefono, email, iva, indirizzo, saldo);
	}
	
	public HashMap<Integer,Merce> getMerc(){
		HashMap<Integer,Merce> vod=new HashMap<Integer,Merce>();
		return vod;
	}
}