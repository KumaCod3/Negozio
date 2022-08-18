package Negozio;
import java.util.HashMap;

import GUI.*;

public class Cliente extends Anagrafica {
	
	public Cliente(String titolo,String nome,String cognome,String telefono,String email,String iva,String indirizzo,Double saldo){
		super(titolo, nome, cognome, telefono, email, iva, indirizzo, saldo);
		setTipo("cliente");
	}

	public void pagamentoEffettuato(Double pagamento){
		setSaldo(getSaldo()-pagamento);
	}
	public void consegnaMerci(Double costo){
		setSaldo(getSaldo()+costo);
		if (!getIva().equals("")){
		}
	}
	public HashMap<Integer,Merce> getMerc(){
		HashMap<Integer,Merce> vod=new HashMap<Integer,Merce>();
		return vod;
	}
}