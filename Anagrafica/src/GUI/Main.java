package GUI;

import Negozio.DataB;
import Negozio.DataM;

public class Main{
	public static void main(String[] args) {
		MyReadM.carica();
		MyReadF.carica();
		MyReadC.carica();
		Home prova=new Home();
		prova.setVisible(true);
	}
}