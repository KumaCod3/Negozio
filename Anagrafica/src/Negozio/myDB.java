package Negozio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class myDB {
	private static Connection connection;
	private static Statement statement;
	
	public myDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/negozioDB?characterEncoding=latin1&useConfigs=maxPerformance","root","Du1k3rKnows!");
			statement = connection.createStatement();
			System.out.println("DB connected!");
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
			System.out.println(id+ nome+ cognome+ telefono+ email+ iva+ indirizzo+ saldo);
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
			System.out.println(id+ nome+ cognome+ telefono+ email+ iva+ indirizzo+ saldo);
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
			Double prezzoA=result.getDouble("Price");
			Double rincaro=result.getDouble("deal");
			String note=result.getString("note");
			System.out.println(id+ nome+ unita+ quantita+ prezzoA+ rincaro+ note);
			inser=id+","+nome+","+unita+","+quantita+","+prezzoA+","+rincaro+","+note;
		}
		return inser;
	}
	
	public void aggCli(String x) throws SQLException{
		String sql="INSERT INTO Clienti (name, last_name, phone, Mail, state, city, street, vatn, tot_sold, note) values('"+x+"')";
		int result = statement.executeUpdate(sql);
		if (result!=0) {
			System.out.println("Suxcesfully added!");
		}
	}
	public void aggFor(String x) throws SQLException{
		String sql="INSERT INTO Fornitori (name, last_name, phone, Mail, state, city, street, vatn, tot_purchased, note) values('"+x+"')";
		int result = statement.executeUpdate(sql);
		if (result!=0) {
			System.out.println("Suxcesfully added!");
		}
	}
	public void aggMerc(String x) throws SQLException{
		String sql="INSERT INTO Merci (product,unity,quantity,price,deal,note) values('"+x+"')";
		int result = statement.executeUpdate(sql);
		if (result!=0) {
			System.out.println("Suxcesfully added!");
		}
	}
	
	public void modCliID(int x,String nome,String cognome,String telefono,String email,String stato,String citta,String indirizzo,String iva,Double saldo,String note) throws SQLException{
		String mods="name='"+nome+"' last_name='"+cognome+"' phone='"+telefono+"' mail='"+email+"' state='"+stato+"' city='"+citta+
				"' street='"+indirizzo+"' vatn='"+iva+"' tot_sold='"+saldo+"' note='"+note;
		
		String sql="UPDATE Clienti SET "+mods+" WHERE ID_CLIENTE="+x;
		int result = statement.executeUpdate(sql);
		
		if (result!=0) {
			System.out.println("Suxcesfully added!");
		}
	}
	public void modForID(int x,String nome,String cognome,String telefono,String email,String stato,String citta,String indirizzo,String iva,Double saldo,String note) throws SQLException{
		String mods="name='"+nome+"' last_name='"+cognome+"' phone='"+telefono+"' mail='"+email+"' state='"+stato+"' city='"+citta+
				"' street='"+indirizzo+"' vatn='"+iva+"' tot_purchased='"+saldo+"' note='"+note;
		
		String sql="UPDATE Fornitori SET "+mods+" WHERE ID_FORNITORE="+x;
		int result = statement.executeUpdate(sql);
		
		if (result!=0) {
			System.out.println("Suxcesfully added!");
		}
	}
	public void modMercID(int x,String nome,String unita,Double quantita,Double prezzoA,Double rincaro,String note) throws SQLException{
		String mods="product='"+nome+"' unity='"+unita+"' quantity='"+quantita+"' price='"+prezzoA+"' deal='"+rincaro+"' note='"+note;
		
		String sql="UPDATE Merci SET "+mods+" WHERE ID_MERCE="+x;
		int result = statement.executeUpdate(sql);
		
		if (result!=0) {
			System.out.println("Suxcesfully added!");
		}
	}
	
	public String getForName(int x) {
		try {
			String sql="SELECT name FROM Fornitori WHERE ID_FORNITORE="+x;
			ResultSet result = statement.executeQuery(sql);
			result.next();
			String inser=result.getString(1);
			
			return inser;
		} catch (SQLException e) { e.printStackTrace(); return ""; }
	}
	public String getMerName(int x) {
		try {
			String sql="SELECT product FROM Merci WHERE ID_MERCE="+x;
			ResultSet result = statement.executeQuery(sql);
			String inser="";
			try {
				result.next();
				inser=result.getString(0);
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
		String sql="SELECT ID_CLIENTE,name,last_name FROM clienti";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getElenMerc() throws SQLException {
		String sql="SELECT ID_MERCE,product,Quantity,price FROM merci";
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	
	public void assMerc(int codice, int index, Double cost) throws SQLException {
		String sql="INSERT INTO forniture VALUES("+codice+", "+index+", "+cost+")";
		int result = statement.executeUpdate(sql);
		if (result!=0) {
			System.out.println("Suxcesfully added!");
		}
	}

	public ResultSet getElenSuppF(int index) throws SQLException{
		String sql="SELECT forniture.ID_FORNITORE, fornitori.name, fornitori.last_name FROM forniture JOIN fornitori ON forniture.ID_FORNITORE WHERE ID_MERCE="+index;
		// cod, nome, cogn
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	public ResultSet getElenSuppM(int codice) throws SQLException{
		String sql="SELECT forniture.ID_MERCE, merci.product FROM forniture JOIN merci ON forniture.ID_MERCE=merci.ID_MERCE WHERE ID_FORNITORE="+codice;
		// num. prod
		ResultSet result = statement.executeQuery(sql);
		return result;
	}
	

	public void modMerc(int index, String nome, String unita, Double quantita, Double prezzoA, Double rincaro, String note) throws SQLException{
		String sql="UPDATE Merci SET product='"+nome+"', unity='"+unita+"', quantity="+quantita+",price="+prezzoA+",deal="+rincaro+",note='"+note+"' WHERE ID_MERCE="+index;
		int result = statement.executeUpdate(sql);
		if (result!=0) {
			System.out.println("Suxcesfully added!");
		}
	}

	public Double getPrezzo(int x) {
		try {
			String sql="SELECT price,deal FROM Merci WHERE ID_MERCE="+x;
			ResultSet result = statement.executeQuery(sql);
			Double inser=0.0;
				
			while (result.next()) {
				Double prezzoA=result.getDouble("Price");
				Double rincaro=result.getDouble("deal");
				inser=prezzoA*rincaro;
			}
			return inser;
		} catch (SQLException e) {e.printStackTrace(); return 0.0; }
	}

	public Double getPrezzoA(int x) {
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
	

	public void compra(int index, Double quantita)  throws SQLException {
		String sql="UPDATE Merci SET quantity="+quantita +" WHERE ID_MERCE="+index;
		int result = statement.executeUpdate(sql);
		if (result!=0) {
			System.out.println("Suxcesfully added!");
		}
	}
	
	public void aggiornaSaldoCli(int codice, Double prezzo)  throws SQLException {
		String sql="UPDATE Clienti SET tot_sold=tot_sold + "+prezzo +" WHERE ID_CLIENTE="+codice;
		int result = statement.executeUpdate(sql);
		if (result!=0) {
		}
	}
}
