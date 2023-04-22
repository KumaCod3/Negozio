package GUI;

import Negozio.DataB;
import Negozio.DataM;
import Negozio.myDB;
	
public class Main{
	public static myDB db=new myDB();
	
	public static void main(String[] args) {
//		MyReadM.carica();
//		MyReadF.carica();
//		MyReadC.carica();
		Home prova=new Home();
		prova.setVisible(true);
	}
}