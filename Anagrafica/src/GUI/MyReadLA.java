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

import Negozio.*;

public class MyReadLA {
	
	public static HashMap<LocalDateTime,String[]> carica(){
		BufferedReader reader;
		HashMap<LocalDateTime,String[]> elenco=new HashMap<LocalDateTime,String[]>();
		ArrayList<String> ele=new ArrayList<String>();
		try{
			File file = new File("trans");
			FileReader fReader = new FileReader(file);
			reader = new BufferedReader(fReader);
			String line;
			while ((line = reader.readLine()) != null){
				ele.add(line);
			}
			for (String s:ele){
				String[] temp=s.split(" , ");
				String dat=temp[0];
				String[] rest={temp[1],temp[2],temp[3]};
				LocalDateTime data=LocalDateTime.parse(dat,Est.dateForm);
				elenco.put(data, rest);
				
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return elenco;
	}
	public static void scarica(ListaSpesa l){
		if (l.qtTot()>0){
			String riga=l.getData().format(Est.dateForm)+" , "+l.getCliente().getIntestazione()+" , "+Est.deci.format(l.getSaldo());
			
			try{
				FileWriter fWriter = new FileWriter("trans", true);
				BufferedWriter writer = new BufferedWriter(fWriter);
				
				writer.write(riga+System.lineSeparator());
				writer.close();
	
			}
			catch (Exception e){
				System.out.print("Errore");
				
			}
		}
	}
}