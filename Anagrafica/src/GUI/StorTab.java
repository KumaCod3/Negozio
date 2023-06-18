package GUI;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negozio.ListaSpesa;

public class StorTab extends Finestra{
	ArrayList<String> elenco=new ArrayList<String>();
	JTable tavola;
	
	DefaultTableModel model = new DefaultTableModel() {
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	public StorTab(ArrayList<String> ele){
		super("Storico");
		elenco=ele;

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
		JScrollPane scrollPane = new JScrollPane(tavola, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollBar bar = scrollPane.getVerticalScrollBar();
				bar.setPreferredSize(new Dimension(20, 0));
		scrollPane.setPreferredSize(new Dimension(200,200));
		
		return scrollPane;
	}
	
	public int getIndex() {
		return tavola.getSelectedRow();
	}
	
	public void repaint(ArrayList<String> ele){
		elenco=ele;
		model.setRowCount(0);
		for (String entry:elenco){
			String[] riga=entry.split(", ");
			model.addRow(riga);
		}
	}
}
