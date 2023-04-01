package Negozio;

public class Guest extends Cliente {
	public Guest(String titolo,String nome,String cognome,String telefono,String email,String iva,String indirizzo,Double saldo){
		super("Mr." , "GUEST" , "--" , "00" , "--" , "00" , "--" , 00.00);
	}
	public Guest(){
		super("Mr." , "GUEST" , "--" , "00" , "--" , "00" , "--" , 00.00);
	}

	public void pagamentoEffettuato(Double pagamento) {
		
	}

	public void consegnaMerci(Double costo) {
			
	}
}
