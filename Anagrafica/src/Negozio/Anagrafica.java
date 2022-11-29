package Negozio;
import java.util.HashMap;

public abstract class Anagrafica {
	private final String titolo;
	private final String cognome;
	private final String nome;
	private String iva="";
	private String telefono;
	private String email;
	private Double saldo=0.00;
	private String indirizzo;
	private String tipo;

	public Anagrafica(String titolo,String nome,String cognome,String telefono,String email,String iva,String indirizzo,Double saldo){
		this.cognome=cognome;
		this.nome=nome;
		this.titolo=titolo;
		this.telefono=telefono;
		this.email=email;
		this.iva=iva;
		this.indirizzo=indirizzo;
		this.saldo=saldo;
	}
	public void setTipo(String a){
		tipo=a;
	}
	public String getTitolo(){
		return titolo;
	}

	public String getNome(){
		return nome;
	}
	public String getCognome(){
		return cognome;
	}

	public void setIva(String iva){
		this.iva=iva;
	}
	public void setTelefono(String telefono){
		this.telefono=telefono;
	}
	public void setEmail(String mail){
		email=mail;
	}
	public void setIndirizzo(String indir){
		indirizzo=indir;
	}
	public String getIva(){
		return iva;
	}
	public String getTelefono(){
		return telefono;
	}
	public String getEmail(){
		return email;
	}
	public String getIndirizzo(){
		return indirizzo;
	}
	public Double getSaldo(){
		return saldo;
	}
	public void setSaldo(Double costo){
		saldo=costo;
	}
	public String getIntestazione(){
		String in=getTitolo()+" "+cognome+" , "+nome;
		return in;
	}
	public String getMinIntestazione(){
		String in=cognome+" , "+nome;
		return in;
	}
	public abstract HashMap<Integer,Merce> getMerc();
}