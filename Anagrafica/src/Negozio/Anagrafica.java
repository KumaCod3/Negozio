package Negozio;
import GUI.*;

import java.util.HashMap;
import java.util.Scanner;

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
	Scanner sc = new Scanner(System.in);

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
	/*public void setTitolo(String tit){
		titolo=tit;
	}
	public Anagrafica(String unCognome, String unNome){
		cognome=unCognome;
		nome=unNome;
	}*/

	public String getNome(){
		return nome;
	}
	public String getCognome(){
		return cognome;
	}
	/*public void setNome(String nome){
		this.nome=nome;
	}
	public void setCognome(String cognome){
		this.cognome=cognome;
	}*/
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
	public abstract void pagamentoEffettuato(Double pagamento);
	public String getTipo(){
		return tipo;
	}
	public Double getSaldo(){
		return saldo;
	}
	public void setSaldo(Double costo){
		saldo=costo;
	}
	public abstract void consegnaMerci(Double costo);
	public String toString(){
		String stamp="";
		if (getTipo().equals("fornitore")){
			stamp=getTitolo()+getNome()+" Piva: "+getIva()+" Debito totale: "+getSaldo();
		}
		else {
			stamp=getTitolo()+" "+getNome()+" "+getCognome()+" Debito totale: "+getSaldo();
		}
		return stamp;
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