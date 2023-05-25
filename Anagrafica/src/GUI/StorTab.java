package GUI;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StorTab extends Finestra{
	public LinkedHashMap<LocalDateTime,String[]> elenco=new LinkedHashMap<LocalDateTime,String[]>();
	JTable tavola;
	
	DefaultTableModel model = new DefaultTableModel() {
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	public StorTab(){
		super("Storico");
		elenco=MyReadL.carica();

		tavola=new JTable(model);
		tavola.setBackground(Est.sfondo);
		tavola.setColumnSelectionAllowed(false);
		tavola.setDragEnabled(false);
		tavola.setRowSelectionAllowed(true);
						
		model.addColumn("DATE:");
		model.addColumn("LAST NAME:");
		model.addColumn("NAME:");
		model.addColumn("TOT:");
		
		for (Entry<LocalDateTime,String[]> entry:elenco.entrySet()){
			
			String[] riga={entry.getKey().format(Est.dateForm),entry.getValue()[0],entry.getValue()[1],entry.getValue()[2]};
			model.addRow(riga);
		}

		JScrollPane sp=new JScrollPane(tavola);
		sp.add(tavola);
		add(sp);
	}
	
	public StorTab(String x){
		super("Storico");
		elenco=MyReadA.carica();

		tavola=new JTable(model);
		tavola.setBackground(Est.sfondo);
		tavola.setColumnSelectionAllowed(false);
		tavola.setDragEnabled(false);
		tavola.setRowSelectionAllowed(true);
				
		model.addColumn("DATE:");
		model.addColumn("LAST NAME:");
		model.addColumn("PRODUCT:");
		model.addColumn("TOT:");
		
		for (Entry<LocalDateTime,String[]> entry:elenco.entrySet()){
			
			String[] riga={entry.getKey().format(Est.dateForm),entry.getValue()[0],DataM.get(Integer.parseInt(entry.getValue()[3])).getNome(),entry.getValue()[2]};
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
