package GUI;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TranTab extends Finestra{
	ArrayList<String> elenco=new ArrayList<String>();
	JTable tavola;
	
	DefaultTableModel model = new DefaultTableModel() {
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	public TranTab(ArrayList<String> ele){
		super("Storico");
		elenco=ele;

		tavola=new JTable(model);
		tavola.setBackground(Est.sfondo);
		tavola.setColumnSelectionAllowed(false);
		tavola.setDragEnabled(false);
		tavola.setRowSelectionAllowed(true);
			
		model.addColumn("ID Merch:");
		model.addColumn("Name:");
		model.addColumn("Quantity:");
		model.addColumn("Price:");
		
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

	
	public void repaint(ArrayList<String> ele){
		elenco=ele;
		model.setRowCount(0);
		for (String entry:elenco){
			String[] riga=entry.split(", ");
			model.addRow(riga);
		}
	}
}
