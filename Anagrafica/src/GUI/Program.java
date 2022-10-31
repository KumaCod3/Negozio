package GUI;

import Negozio.DataB;
import Negozio.DataM;

public class Program{
	public static void main(String[] args) {
		MyReadM.carica();
		MyReadF.carica();
		MyReadC.carica();
//		Home prova=new Home();
		MyChoice prova=new MyChoice(DataM.elenco);
		prova.setVisible(true);
	}
}