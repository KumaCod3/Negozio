package Negozio;

public class Guest extends Cliente {
	public Guest(int id,String nome,String cognome,String telefono,String email,String stato,String citta,String indirizzo,String iva,Double saldo){
		super( id, nome, cognome, telefono, email, stato, citta, indirizzo, iva, saldo);
	}
	public Guest(){
		super(-1 ,"guest","--","--","--","--","--","--","--", 00.00);
	}

	public void pagamentoEffettuato(Double pagamento) {
		
	}

	public void consegnaMerci(Double costo) {
			
	}
}
