package GUI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StorTab extends Finestra{
	ArrayList<String> elenco=new ArrayList<String>();
	JTable tavola;
	
	DefaultTableModel model = new DefaultTableModel() {
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	public StorTab(){
		super("Storico");
		try {
			ResultSet sor=Main.db.getVendite();
			while (sor.next()) {		
				String fin=sor.getString("ID_VENDITA")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
				elenco.add(fin);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		
		

		tavola=new JTable(model);
		tavola.setBackground(Est.sfondo);
		tavola.setColumnSelectionAllowed(false);
		tavola.setDragEnabled(false);
		tavola.setRowSelectionAllowed(true);
			
		model.addColumn("ID Transaction:");
		model.addColumn("DATE:");
		model.addColumn("LAST NAME:");
		model.addColumn("TOT:");
		
		for (String entry:elenco){
			
			String[] riga=entry.split(", ");
			model.addRow(riga);
		}

		JScrollPane sp=new JScrollPane(tavola);
		sp.add(tavola);
		add(sp);
	}
	
	public StorTab(String x){
		super("Storico");
		try {
			ResultSet sor=Main.db.getAcquisti();
			while (sor.next()) {		
				String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
				elenco.add(fin);
			}
		} catch (SQLException e) { e.printStackTrace(); }

		tavola=new JTable(model);
		tavola.setBackground(Est.sfondo);
		tavola.setColumnSelectionAllowed(false);
		tavola.setDragEnabled(false);
		tavola.setRowSelectionAllowed(true);
			
		model.addColumn("ID Transaction:");
		model.addColumn("DATE:");
		model.addColumn("LAST NAME:");
		model.addColumn("TOT:");
		
for (String entry:elenco){
			
			String[] riga=entry.split(", ");
			model.addRow(riga);
		}

		JScrollPane sp=new JScrollPane(tavola);
		sp.add(tavola);
		add(sp);
	}
	
	public JScrollPane ta(){
		JScrollPane sp=new JScrollPane(tavola); 
		return sp;
	}
}
