package Negozio;
import java.util.ArrayList;

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
	public ArrayList<Fornitore> elenco=new ArrayList<Fornitore>();

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
		this.elenco=m.getForn();
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
	
	public Merce(String nome, Double quantita, int rincaro, Double prezzoA, String unita, int cod){
		this.nome=nome;
		this.quantita=quantita;
		this.rincaro=rincaro;
		this.prezzoA=prezzoA;
		this.unita=unita;
		this.prezzoV=(prezzoA/100*rincaro)+prezzoA;
		this.prezzoF=prezzoV*quantita;
		this.valore=prezzoA*quantita;
		this.codice=cod;
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
	
	public void aggiungi(double x){
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
	
	public void setQuantita(Double x){
		
		quantita=x;
		valore=quantita*prezzoA;
		prezzoF=quantita*prezzoV;
	}
	
	public void setUnita(String x){
		unita=x;
	}

	public void addForn(Fornitore f){
		if (!doppioForn(f)){
		elenco.add(f);
		}
	}
	
	public void addForn(String name){
		Fornitore f=DataB.fornitori.get(DataB.trovaNome(name));
		if (!doppioForn(f)){
			elenco.add(f);
		}
	}
	
	public ArrayList<Fornitore> getForn(){
		return elenco;
	}
	
	public void removeForn(String name){
		int index=getIndex(name);
		elenco.remove(index);
	}
	
	public void removeForn(Fornitore f){
		elenco.remove(f);
	}
	
	public int getIndex(String name){
		int index=-1;
		for (int i=0;i<elenco.size();i++){
			if (elenco.get(i).getNome().equals(name)){
				index=i;
			}
		}
		return index;
	}
	
	public boolean doppioForn(Fornitore f){
		for (Fornitore g:elenco){
			if (g.equals(f)){
				return true;
			}
		}
		return false;
	}
}
