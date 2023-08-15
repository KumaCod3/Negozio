package Negozio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import GUI.Est;

public class myDB {
	private static Connection connection;
	private static Statement statement;
	private String PASSWORD="Du1k3rKnows!";
	private String DataBaseNAME="negozioDB";
	
	
	public myDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DataBaseNAME+"?characterEncoding=latin1&useConfigs=maxPerformance","root",PASSWORD);
			statement = connection.createStatement();
//			System.out.println("DB connected!");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String leggiCliID(int x) throws SQLException{
		String sql="SELECT * FROM Clienti WHERE ID_CLIENTE="+x;
		ResultSet result = statement.executeQuery(sql);
		String inser="";
			
		while (result.next()) {
			int id = result.getInt("ID_Cliente");
			String nome=result.getString("name");
			String cognome=result.getString("last_name");
			String telefono=result.getString("phone");
			String email=result.getString("Mail");
			String iva=result.getString("vatn");
			String stato=result.getString("state");
			String citta=result.getString("city");
			String indirizzo=result.getString("street");
			Double saldo=result.getDouble("tot_sold");
			String note=result.getString("note");
//			System.out.println(id+ nome+ cognome+ telefono+ email+ iva+ indirizzo+ saldo);
			inser=id+","+nome+","+cognome+","+telefono+","+email+","+stato+","+citta+","+indirizzo+","+iva+","+saldo+","+note;
		}
		return inser;
	}
	public String leggiForID(int x) throws SQLException{
		String sql="SELECT * FROM Fornitori WHERE ID_FORNITORE="+x;
		ResultSet result = statement.executeQuery(sql);
		String inser="";
			
		while (result.next()) {
			int id = result.getInt("ID_FORNITORE");
			String nome=result.getString("name");
			String cognome=result.getString("last_name");
			String telefono=result.getString("phone");
			String email=result.getString("Mail");
			String iva=result.getString("vatn");
			String stato=result.getString("state");
			String citta=result.getString("city");
			String indirizzo=result.getString("street");
			Double saldo=result.getDouble("tot_purchased");
			String note=result.getString("note");
//			System.out.println(id+ nome+ cognome+ telefono+ email+ iva+ indirizzo+ saldo);
			inser=id+","+nome+","+cognome+","+telefono+","+email+","+stato+","+citta+","+indirizzo+","+iva+","+saldo+","+note;
		}
		return inser;
	}
	public String leggiMercID(int x) throws SQLException{
		String sql="SELECT * FROM Merci WHERE ID_MERCE="+x;
		ResultSet result = statement.executeQuery(sql);
		String inser="";
			
		while (result.next()) {
			int id = result.getInt("ID_Merce");
			String nome=result.getString("Product");
			String unita=result.getString("Unity");
			Double quantita=result.getDouble("Quantity");
			Double prezzoA=result.getDouble("price");
			Double rincaro=result.getDouble("increase");
			Double sconto=result.getDouble("Deal");
			String note=result.getString("note");
//			System.out.println(id+ nome+ unita+ quantita+ prezzoA+ rincaro+ note);
			inser=id+","+nome+","+unita+","+quantita+","+prezzoA+","+rincaro+","+sconto+","+note;
		}
		return inser;
	}
	
	public void aggCli(String x) throws SQLException{
		String sql="INSERT INTO Clienti (name, last_name, phone, Mail, state, city, street, vatn, tot_sold, note) values('"+x+"')";
		int result = statement.executeUpdate(sql);
		if (result!=0) {
//			System.out.println("Suxcesfully added!");
		}
	}
	public void aggFor(String x) throws SQLException{
		String sql="INSERT INTO Fornitori (name, last_name, phone, Mail, state, city, street, vatn, tot_purchased, note) values('"+x+"')";
		int result = statement.executeUpdate(sql);
		if (result!=0) {
//			System.out.println("Suxcesfully added!");
		}
	}
	public void aggMerc(String x) throws SQLException{
		String sql="INSERT INTO Merci (product,unity,quantity,price,deal,increase,note) values('"+x+"')";
		int result = statement.executeUpdate(sql);
		if (result!=0) {
//			System.out.println("Suxcesfully added!");
		}
	}
	public void elimMerc(int x) throws SQLException{
		String sql="DELETE FROM Merci WHERE ID_MERCE="+x;
		int result = statement.executeUpdate(sql);
		if (result!=0) {
//			System.out.println("Suxcesfully removed!");
		}
	}
	
	public void modCliID(int x,String nome,String cognome,String telefono,String email,String stato,String citta,String indirizzo,String iva,Double saldo,String note) throws SQLException{
		String mods="name='"+nome+"', last_name='"+cognome+"', phone='"+telefono+"', mail='"+email+"', state='"+stato+"', city='"+citta+
				"', street='"+indirizzo+"', vatn='"+iva+"', tot_sold="+saldo+", note='"+note;
		
		String sql="UPDATE Clienti SET "+mods+"' WHERE ID_CLIENTE="+x;
		int result = statement.executeUpdate(sql);
		
		if (result!=0) {
//			System.out.println("Suxcesfully added!");
		}
	}
	public void modForID(int x,String nome,String cognome,String telefono,String email,String stato,String citta,String indirizzo,String iva,Double saldo,String note) throws SQLException{
		String mods="name='"+nome+"', last_name='"+cognome+"', phone='"+telefono+"', mail='"+email+"', state='"+stato+"', city='"+citta+
				"', street='"+indirizzo+"', vatn='"+iva+"', tot_purchased="+saldo+", note='"+note;
		
		String sql="UPDATE Fornitori SET "+mods+"' WHERE ID_FORNITORE="+x;
		int result = statement.executeUpdate(sql);
		
		if (result!=0) {
//			System.out.println("Suxcesfully added!");
		}
	}
	
	public String getForName(int x) throws SQLException {
//		try {
			String sql="SELECT name FROM Fornitori WHERE ID_FORNITORE="+x;
			ResultSet result = statement.executeQuery(sql);
			result.next();
			String inser=result.getString(1);
			return inser;
//		} catch (SQLException e) { e.printStackTrace(); return ""; }
	}
	public String getMerName(int x) {
		try {
			String sql="SELECT product FROM Merci WHERE ID_MERCE="+x;
			ResultSet result = statement.executeQuery(sql);
			String inser="";
			try {
				result.next();
				inser=result.getString(1);
			} catch (SQLException ex) { return null; }
			
			return inser;
		} catch (SQLException e) { e.printStackTrace(); return ""; }
	}

	public ResultSet getElenForn() throws SQLException {
		String sql="SELECT ID_FORNITORE,name,last_name FROM fornitori";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getElenCli() throws SQLException {
		String sql="SELECT ID_CLIENTE,name,last_name FROM clienti WHERE ID_CLIENTE!=1";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getElenMerc() throws SQLException {
		String sql="SELECT ID_MERCE,product,Quantity,price,deal,increase FROM merci";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	
	public void assMerc(int codice, int index, Double cost) throws SQLException {
		try {
			String sql="INSERT INTO forniture VALUES("+codice+", "+index+", "+cost+")";
			int result = statement.executeUpdate(sql);
			if (result!=0) {
	//			System.out.println("Suxcesfully added!");
			}
			aggiornaPrezzo(index);
			
		} catch (MySQLIntegrityConstraintViolationException x) {
			String sql="UPDATE forniture SET PRICE="+cost+" WHERE ID_MERCE="+index+" AND ID_FORNITORE="+codice+";";
			int result = statement.executeUpdate(sql);
			if (result!=0) {
	//			System.out.println("Suxcesfully added!");
			}
			aggiornaPrezzo(index);
		}
	}

	private void aggiornaPrezzo(int index) {
		double prezFin=100000;
		ArrayList<Double> prezzi=new ArrayList<>();
		try {
			String sql="SELECT price FROM Forniture WHERE ID_MERCE="+index;
			ResultSet result = statement.executeQuery(sql);	
			while (result.next()) {
				Double prezzo=result.getDouble("Price");
				prezzi.add(prezzo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		for (Double d:prezzi) {
			if (d<prezFin) {
				prezFin=d;
			}
		}
		try {
			String sql2="UPDATE Merci SET price="+prezFin+" WHERE ID_MERCE="+index;
			int result2 = statement.executeUpdate(sql2);
			if (result2!=0) {
	//			System.out.println("Suxcesfully added!");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getElenSuppF(int index) throws SQLException{
		String sql="SELECT fornitori.ID_FORNITORE, fornitori.name, fornitori.last_name FROM fornitori WHERE ID_FORNITORE IN (SELECT ID_FORNITORE FROM forniture WHERE ID_MERCE="+index+");";
		// cod, nome, cogn
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getElenSuppM(int codice) throws SQLException{
		String sql="SELECT merci.ID_MERCE, merci.product FROM merci WHERE ID_MERCE IN (SELECT ID_MERCE FROM forniture WHERE ID_FORNITORE="+codice+");";
		// num. prod
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	
	public void modMerc(int index, String nome, String unita, Double quantita, Double prezzoA, Double rincaro, Double sconto, String note) throws SQLException{
		String sql="UPDATE Merci SET product='"+nome+"', unity='"+unita+"', quantity="+quantita+",price="+prezzoA+",deal="+sconto+",increase="+rincaro+",note='"+note+"' WHERE ID_MERCE="+index;
		int result = statement.executeUpdate(sql);
		if (result!=0) {
//			System.out.println("Suxcesfully added!");
		}
	}

	public double getPrezzo(int x) {
		try {
			String sql="SELECT price,deal,increase FROM Merci WHERE ID_MERCE="+x;
			ResultSet result = statement.executeQuery(sql);
			Double inser=0.0;
				
			while (result.next()) {
				Double prezzoA=result.getDouble("Price");
				Double rincaro=result.getDouble("increase");
				Double sconto=result.getDouble("deal");
				inser=prezzoA+(prezzoA*rincaro)-(prezzoA*sconto);
			}
			return inser;
		} catch (SQLException e) {e.printStackTrace(); return 0.0; }
	}
	public double getPrezzoA(int x) {
		try {
			String sql="SELECT price FROM Merci WHERE ID_MERCE="+x;
			ResultSet result = statement.executeQuery(sql);
			Double inser=0.0;
				
			while (result.next()) {
				inser=result.getDouble("Price");
			}
			return inser;
		} catch (SQLException e) {e.printStackTrace(); return 0.0; }
	}	
	public double getQuantMerc(int x) {
		try {
			String sql="SELECT quantity FROM Merci WHERE ID_MERCE="+x;
			ResultSet result = statement.executeQuery(sql);
			Double inser=0.0;
				
			while (result.next()) {
				inser=result.getDouble("quantity");
			}
			return inser;
		} catch (SQLException e) {e.printStackTrace(); return 0.0; }
	}

	public double getMercVal() {
		try {
			String sql="SELECT id_merce, (quantity*price) AS Total FROM negoziodb.merci GROUP BY id_merce;";
			ResultSet result = statement.executeQuery(sql);
			Double inser=0.0;
				
			while (result.next()) {
				inser=inser+result.getDouble("Total");
			}
			return inser;
		} catch (SQLException e) {e.printStackTrace(); return 0.0; }
	}
	public double getTotBought() {
		try {
			String sql="SELECT SUM(Price) AS Total FROM negoziodb.acquisti;";
			ResultSet result = statement.executeQuery(sql);
			Double inser=0.0;
				
			while (result.next()) {
				inser=result.getDouble("Total");
			}
			return inser;
		} catch (SQLException e) {e.printStackTrace(); return 0.0; }
	}
	public double getTotSold() {
		try {
			String sql="SELECT SUM(Price) AS Total FROM negoziodb.vendite;";
			ResultSet result = statement.executeQuery(sql);
			Double inser=0.0;
				
			while (result.next()) {
				inser=result.getDouble("Total");
			}
			return inser;
		} catch (SQLException e) {e.printStackTrace(); return 0.0; }
	}
	
	public void vendi(int index, Double quantita, int idTrans)  throws SQLException {
		String sql="UPDATE Merci SET quantity=quantity-"+quantita +" WHERE ID_MERCE="+index;
		int result = statement.executeUpdate(sql);
		if (result!=0) {
//			System.out.println("Suxcesfully added!");
		}
		double cost=getPrezzo(index);
		String sql2="INSERT INTO merci_vendite (ID_VENDITA, ID_MERCE, quantity, price) values("+idTrans+", "+index+", "+quantita+", "+cost+")";
		int result2 = statement.executeUpdate(sql2);
		if (result2!=0) {
//			System.out.println("Suxcesfully added!");
		}
	}
	public void compra(int index, Double quantita, double cost, int idTrans)  throws SQLException {
		String sql="UPDATE Merci SET quantity=quantity+"+quantita +" WHERE ID_MERCE="+index;
		int result = statement.executeUpdate(sql);
		if (result!=0) {
//			System.out.println("Suxcesfully added!");
		}
		String sql2="INSERT INTO merci_acquisti (ID_ACQUISTO, ID_MERCE, quantity, price) values("+idTrans+", "+index+", "+quantita+", "+cost+")";
		int result2 = statement.executeUpdate(sql2);
		if (result2!=0) {
//			System.out.println("Suxcesfully added!");
		}
	}
	
	public void aggiornaSaldoCli(int codice, Double prezzo)  throws SQLException {
		String sql="UPDATE Clienti SET tot_sold=tot_sold + "+prezzo +" WHERE ID_CLIENTE="+codice;
		int result = statement.executeUpdate(sql);
		if (result!=0) {
		}
	}
	public int createTransactionIn(int c, LocalDateTime data) throws SQLException{
		String sql="INSERT INTO Vendite (ID_CLIENTE, moment) values("+c+", '"+Est.dateFormSQL.format(data)+"')";
		int result = statement.executeUpdate(sql);
		if (result!=0) {
//			System.out.println("Suxcesfully added!");
		}
		int idd=0;
		String sql2="SELECT LAST_INSERT_ID() AS id;";
		ResultSet result2 = statement.executeQuery(sql2);
		while (result2.next()) {
			idd=result2.getInt("id");
		}
		return idd;
	}
	public void elimTransactionIn(int iDtrans) {
		try {
			String sql="DELETE FROM Vendite WHERE ID_VENDITA="+iDtrans+";";
			int result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void aggiornaVendite(int iDtrans, Double saldo, String not) throws SQLException{
		String sql="UPDATE Vendite SET price="+saldo +", note='"+not +"' WHERE ID_VENDITA="+iDtrans;
		int result = statement.executeUpdate(sql);
		if (result!=0) {
		}
	}


	public void aggiornaSaldoFor(int index, Double prezzo)  throws SQLException {
		String sql="UPDATE Fornitori SET tot_purchased=tot_purchased + "+prezzo +" WHERE ID_FORNITORE="+index;
		int result = statement.executeUpdate(sql);
		if (result!=0) {
		}
	}	
	public int createTransactionOu(int index, double price) throws SQLException{
		LocalDateTime data=LocalDateTime.now();
		String sql="INSERT INTO Acquisti (ID_FORNITORE, moment, Price) values("+index+", '"+Est.dateFormSQL.format(data)+"', "+price+")";
		int result = statement.executeUpdate(sql);
		if (result!=0) {
//			System.out.println("Suxcesfully added!");
		}
		int idd=0;
		String sql2="SELECT LAST_INSERT_ID() AS id;";
		ResultSet result2 = statement.executeQuery(sql2);
		while (result2.next()) {
			idd=result2.getInt("id");
		}
		return idd;
	}

	public double getPriceF(int index, int codice) {
		try {
			String sql="SELECT price FROM negoziodb.forniture WHERE ID_FORNITORE="+index+" AND ID_MERCE="+codice+";";
			ResultSet result = statement.executeQuery(sql);
			Double inser=0.0;
				
			while (result.next()) {
				inser=result.getDouble("price");
			}
			return inser;
		} catch (SQLException e) {e.printStackTrace(); return 0.0; }
	}
	
	public ResultSet getVendite(LocalDateTime dataIN, LocalDateTime dataFI) throws SQLException{
		String sql="SELECT vendite.ID_VENDITA, vendite.Moment, clienti.name, clienti.Last_name, vendite.price FROM vendite JOIN clienti ON vendite.ID_CLIENTE=clienti.ID_CLIENTE WHERE vendite.Moment BETWEEN '"+dataIN+"' AND '"+dataFI+"';";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getVendite(double priceI, double priceF) throws SQLException{
		String sql="SELECT vendite.ID_VENDITA, vendite.Moment, clienti.name, clienti.Last_name, vendite.price FROM vendite JOIN clienti ON vendite.ID_CLIENTE=clienti.ID_CLIENTE WHERE vendite.price BETWEEN '"+priceI+"' AND '"+priceF+"';";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getVendite(int IDpers) throws SQLException{
		String sql="SELECT vendite.ID_VENDITA, vendite.Moment, clienti.name, clienti.Last_name, vendite.price FROM vendite JOIN clienti ON vendite.ID_CLIENTE=clienti.ID_CLIENTE WHERE vendite.ID_CLIENTE="+IDpers+";";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getVendite(int IDtrans, String s) throws SQLException{
		String sql="SELECT vendite.ID_VENDITA, vendite.Moment, clienti.name, clienti.Last_name, vendite.price FROM vendite JOIN clienti ON vendite.ID_CLIENTE=clienti.ID_CLIENTE WHERE vendite.ID_VENDITA="+IDtrans+";";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getVendita(int index) throws SQLException{
		String sql="SELECT merci_vendite.ID_MERCE, merci.Product as Nome, merci_vendite.Quantity, merci_vendite.Price FROM merci_vendite JOIN merci ON merci_vendite.ID_MERCE=merci.ID_MERCE  WHERE ID_VENDITA="+index+";";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}

	
	public ResultSet getAcquisti(LocalDateTime dataIN, LocalDateTime dataFI) throws SQLException{
		String sql="SELECT acquisti.ID_ACQUISTO, acquisti.Moment, fornitori.name, fornitori.Last_name, acquisti.price FROM acquisti JOIN fornitori ON acquisti.ID_FORNITORE=fornitori.ID_FORNITORE WHERE acquisti.Moment BETWEEN '"+dataIN+"' AND '"+dataFI+"';";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getAcquisti(double priceI, double priceF) throws SQLException{
		String sql="SELECT acquisti.ID_ACQUISTO, acquisti.Moment, fornitori.name, fornitori.Last_name, acquisti.price FROM acquisti JOIN fornitori ON acquisti.ID_FORNITORE=fornitori.ID_FORNITORE WHERE acquisti.price BETWEEN '"+priceI+"' AND '"+priceF+"';";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getAcquisti(int IDpers) throws SQLException{
		String sql="SELECT acquisti.ID_ACQUISTO, acquisti.Moment, fornitori.name, fornitori.Last_name, acquisti.price FROM acquisti JOIN fornitori ON acquisti.ID_FORNITORE=fornitori.ID_FORNITORE WHERE acquisti.ID_FORNITORE="+IDpers+";";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getAcquisti(int IDtrans, String s) throws SQLException{
		String sql="SELECT acquisti.ID_ACQUISTO, acquisti.Moment, fornitori.name, fornitori.Last_name, acquisti.price FROM acquisti JOIN fornitori ON acquisti.ID_FORNITORE=fornitori.ID_FORNITORE WHERE acquisti.ID_ACQUISTO="+IDtrans+";";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getAcquisto(int index) throws SQLException{
		String sql="SELECT merci_acquisti.ID_MERCE, merci.Product as Nome, merci_acquisti.Quantity, merci_acquisti.Price FROM merci_acquisti JOIN merci ON merci_acquisti.ID_MERCE=merci.ID_MERCE  WHERE ID_ACQUISTO="+index+";";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	
	public void removeForn(int codice, int indice) {
		try {
			String sql="DELETE FROM forniture WHERE ID_MERCE="+codice+" AND ID_FORNITORE="+indice+";";
			int result = statement.executeUpdate(sql);
			if (result!=0) {
	//			System.out.println("Suxcesfully added!");
			}
		} catch (SQLException e) { e.printStackTrace();}
	}

	public int nuovaMerc(String nome, String unita) {
		String sql="INSERT INTO Merci (product,unity) values('"+nome+"','"+unita+"')";
		int idd=-1;
		try {
			int result = statement.executeUpdate(sql);
			String sql2="SELECT LAST_INSERT_ID() AS id;";
			ResultSet result2 = statement.executeQuery(sql2);
			while (result2.next()) {
				idd=result2.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idd;
	}



	
}
