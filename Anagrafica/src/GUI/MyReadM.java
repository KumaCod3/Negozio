package GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Negozio.*;

public class MyReadM {
	
	public static void carica(){
		BufferedReader reader;
		ArrayList<String> elenco=new ArrayList<String>();
		try{
			File file = new File("merce");
			
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
			String[] og=lin.split(",");
			int cod=Integer.parseInt(og[0]);
			String nome=og[1];
			Double quantita=Double.parseDouble(og[2]);
			int rincaro=Integer.parseInt(og[3]);
			Double prezzoA=Double.parseDouble(og[4]);
			String unita=og[5];
			Merce ogetto=new Merce(nome, quantita, rincaro, prezzoA, unita, cod);
			DataM.agg(ogetto,cod);
			
		}
	}
	
	public static void scarica(){
		ArrayList<String> elenco=new ArrayList<String>();
		for (Merce f:DataM.elenco.values()){
			String temp=f.getCod()+","+f.getNome()+","+f.getQuantita()+","+f.getRincaro()+","+f.getPrezzoA()+","+f.getUnit();
			elenco.add(temp);
		}
		try{
			FileWriter fWriter = new FileWriter("merce", false);
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
}