package GUI;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negozio.MyDB;

public class TabMerc extends Frame{
	JTable tavola;
	
	DefaultTableModel model = new DefaultTableModel() {
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	
	public TabMerc(){
		tavola=new JTable(model);
		tavola.setBackground(Est.sfondo);
		tavola.setColumnSelectionAllowed(false);
		tavola.setDragEnabled(false);
		tavola.setRowSelectionAllowed(true);
			
		model.addColumn("ID:");
		model.addColumn("PRODUCT:");
		model.addColumn("QUANTITY:");
		model.addColumn("Price:");

		JScrollPane sp=new JScrollPane(tavola);
		sp.add(tavola);
		repaint();
		add(sp);
	}
	
	public JScrollPane ta(){
		JScrollPane sp=new JScrollPane(tavola); 
		return sp;
	}
	
	
	public int getID(int row){
		return Integer.parseInt(model.getValueAt(row,0).toString());
	}
	
	public int getInd(String nome){
		for (int i=0;i<model.getRowCount();i++){
			if (model.getValueAt(i, 0).equals(nome)){
				return i;
			}
		}
		return -1;
	}
	
	public void clear(){
		model.setRowCount(0);
	}
	
	public void repaint(){
		clear();
		try {
			// TODO
//			ResultSet sett=Main.dd.db.getElenMerc();
			ResultSet sett=MyDB.getElenMerc();
			while (sett.next()) {
				double priz=sett.getDouble("price")+(sett.getDouble("price")*sett.getDouble("increase"))-(sett.getDouble("price")*sett.getDouble("deal"));
				
				String[] riga={sett.getString("ID_MERCE"),sett.getString("product"),sett.getString("Quantity"),Est.deci.format(priz)};
				model.addRow(riga);
			}
		}
		catch (SQLException e ) {e.printStackTrace(); }
	}
}
