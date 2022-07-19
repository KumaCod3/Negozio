package Negozio;
import GUI.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Merce {
	private final String nome;
	private int codice;
	private Double quantita;
	private int rincaro;
	private Double prezzoA;
	private Double prezzoV;
	private Double prezzoF;
	private Double valore;
	private String unita;
//	private Scanner sc = new Scanner(System.in);
	
	
/*	public Merce(){
		System.out.println("Inserire nome prodotto: ");
		nome=sc.nextLine();
		System.out.println("Inserire l�nita di misura del prodotto: ");
		unita=sc.nextLine();
		System.out.println("Inserire prezzo acquisto all unita: ");
		prezzoA=sc.nextDouble();
		System.out.println("Inserire percentuale rincaro alla vendita: ");
		rincaro=sc.nextInt();
		prezzoV=(prezzoA/100*rincaro)+prezzoA;
		prezzoF=prezzoV*quantita;
		valore=quantita*prezzoA;
		codice=DataM.nextIndice();
	}*/
	public Merce(Merce m){
		this.nome=m.getNome();
		this.quantita=m.getQuantita();
		this.rincaro=m.getRincaro();
		this.prezzoA=m.getPrezzoA();
		this.unita=m.getUnit();
		this.prezzoV=(prezzoA/100*rincaro)+prezzoA;
		this.prezzoF=prezzoV*quantita;
		this.valore=prezzoA*quantita;
		this.codice=m.getCod();
	}
	public Merce(String nome, Double quantita, int rincaro, Double prezzoA, String unita){
		this.nome=nome;
		this.quantita=quantita;
		this.rincaro=rincaro;
		this.prezzoA=prezzoA;
		this.unita=unita;
		this.prezzoV=(prezzoA/100*rincaro)+prezzoA;
		this.prezzoF=prezzoV*quantita;
		this.valore=prezzoA*quantita;
		this.codice=DataM.nextIndice();
	}
	public int getCod(){
		return codice;
	}
	public String getUnit(){
		return unita;
	}
	public String getNome(){
		return nome;
	}
	public Double getQuantita(){
		return quantita;
	}
	public int getRincaro(){
		return rincaro;
	}
	public Double getPrezzoA(){
		return prezzoA;
	}
	public Double getPrezzoV(){
		return prezzoV;
	}
	public Double getValore(){
		return valore;
	}
	public void togli(Double x){
		quantita=quantita-x;
		valore=quantita*prezzoA;
		prezzoF=quantita*prezzoV;
	}
	public void aggiungi(int x){
		quantita=quantita+x;
		valore=quantita*prezzoA;
		prezzoF=quantita*prezzoV;
	}
	public void setRincaro(int x){
		rincaro=x;
		prezzoV=(prezzoA/100*rincaro)+prezzoA;
	}
	public void setPrezzoA(Double x){
		prezzoA=x;
		prezzoV=(prezzoA/100*rincaro)+prezzoA;
		valore=quantita*prezzoA;
		prezzoF=quantita*prezzoV;
	}
	public Double compra(int x){
		aggiungi(x);
		return (x*prezzoA);
	}
	public Double vendi(Double x){
		togli(x);
		return (x*prezzoV);
	}
	public void setQuantita(Double x){
		
		quantita=x;
		valore=quantita*prezzoA;
		prezzoF=quantita*prezzoV;
	}
	public void setUnita(String x){
		unita=x;
	}
	public Double getPrezzoF(){
		return prezzoF;
	}
	public String toString(){
		String stampa=nome+" qt. "+quantita+" prezzo: "+prezzoF;
		return stampa;
	}
	public String toStri(){
		String stampa=nome+" qt. "+quantita+" TOT "+valore;
		return stampa;
	}
}
