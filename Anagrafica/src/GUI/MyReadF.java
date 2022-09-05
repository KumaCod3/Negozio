package GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Negozio.*;

public class MyReadF{
	public static void carica(){
		BufferedReader reader;
		ArrayList<String> elenco=new ArrayList<String>();
		
		try{
			File file = new File("fornitori");
			FileReader fReader = new FileReader(file);
			reader = new BufferedReader(fReader);
			String line;
			while ((line = reader.readLine()) != null){
				elenco.add(line);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		for (String lin:elenco){
			ArrayList<Integer> mer=new ArrayList<Integer>();
			String[] og=lin.split(",");
			String titolo=og[0];
			String nome=og[1];
			String cognome=og[2];
			String telefono=og[3];
			String email=og[4];
			String iva=og[5];
			String indirizzo=og[6];
			Double saldo=Double.parseDouble(og[7]);
			String[] num=calcolaMerc(og[9]);
			try {
				for (String a:num){
					mer.add(Integer.parseInt(a));
				}
			}
			catch (Exception e){
			}
			
			Fornitore inser=new Fornitore(titolo, nome, cognome, telefono, email, iva, indirizzo, saldo);
			
			for (int a:mer){
				inser.addMerc(a);
				DataM.get(a).addForn(inser);
			}
			DataB.agg(inser);
			
		}
	}
	
	public static void scarica(){
		ArrayList<String> elenco=new ArrayList<String>();
		for (Fornitore f:DataB.fornitori){
			String merce="";
			for (Merce m:f.elenco.values()){
				merce=merce+m.getCod()+"-";
			}
			String temp=f.getTitolo()+","+f.getNome()+","+f.getCognome()+","+f.getTelefono()+","+f.getEmail()+","+f.getIva()+","+f.getIndirizzo()+","+Est.deci.format(f.getSaldo())+",("+merce+")";
			elenco.add(temp);
		}
		try{
			FileWriter fWriter = new FileWriter("fornitori", false);
			BufferedWriter writer = new BufferedWriter(fWriter);
			
			for (String stringa:elenco){
				writer.write(stringa+System.lineSeparator());
			}
			writer.close();

		}
		catch (Exception e){
			System.out.print("Errore");
			
		}
	}
	public static String[] calcolaMerc(String riga){
		String aa=riga.replaceAll("[\\p{Ps}\\p{Pe}]", "");
		String[] numeri=aa.split("-");
		return numeri;
	}
}
