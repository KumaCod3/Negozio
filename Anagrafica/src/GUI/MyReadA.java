package GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import Negozio.*;

public class MyReadA {
	
	public static LinkedHashMap<LocalDateTime,String[]> carica(){
		BufferedReader reader;
		LinkedHashMap<LocalDateTime,String[]> elenco=new LinkedHashMap<LocalDateTime,String[]>();
		ArrayList<String> ele=new ArrayList<String>();
		try{
			File file = new File("gross");
			FileReader fReader = new FileReader(file);
			reader = new BufferedReader(fReader);
			String line;
			while ((line = reader.readLine()) != null){
				ele.add(line);
			}
			for (String s:ele){
				String[] temp=s.split(" , ");
				String dat=temp[0];
				String[] rest={temp[1],temp[2],temp[3],temp[4]};
				LocalDateTime data=LocalDateTime.parse(dat,Est.dateForm);
				elenco.put(data, rest);
				
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return elenco;
	}
	public static void salva(Fornitore f, Double saldo, int merce){

		String riga=LocalDateTime.now().format(Est.dateForm)+" , "+f.getIntestazione()+" , "+Est.deci.format(saldo)+" , "+merce;
		
		try{
			FileWriter fWriter = new FileWriter("gross", true);
			BufferedWriter writer = new BufferedWriter(fWriter);
			
			writer.write(riga+System.lineSeparator());
			writer.close();

		}
		catch (Exception e){
			System.out.print("Errore");
			
		}
	}
}