package GUI;
import Negozio.myDB;
	
public class Main{
	public static myDB db=new myDB();
	
	public static void main(String[] args) {
		Home prova=new Home();
		prova.setVisible(true);
	}
}