package GUI;
import java.sql.SQLException;

import Negozio.MyDB;
	
public class Main{
//	public static MyDB db=new myDB();
	public static final MyDB db;

	static {
	    try {
	    	db=new MyDB();
	    }
	    catch (SQLException e) {
	    	System.out.println("MySQL DataBase missing");
	    	throw new RuntimeException("", e);
	    }
	}
	
	public static void main(String[] args) {
		Home prova=new Home();
		prova.setVisible(true);

	}
}