package GUI;
import java.awt.Frame;
import java.util.Map.Entry;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Negozio.ListaSpesa;

public class Tabella extends Frame{
	JTable tavola;
	
	DefaultTableModel model = new DefaultTableModel() {
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	public Tabella(){
		tavola=new JTable(model);
		tavola.setBackground(Est.sfondo);
		tavola.setColumnSelectionAllowed(false);
		tavola.setDragEnabled(false);
		tavola.setRowSelectionAllowed(true);
		
		model.addColumn("ID:");
		model.addColumn("PRODUCT:");
		model.addColumn("QUANTITY:");
		model.addColumn("ToT:");

		JScrollPane sp=new JScrollPane(tavola);
		sp.add(tavola);
		add(sp);
	}
	
	public JScrollPane ta(){
		JScrollPane sp=new JScrollPane(tavola);
		sp.setBorder(Est.borColTut);
		return sp;
	}
	
	public void aggiungi(int m, Double q){
		String id=m+"";
		String nome=Main.db.getMerName(m);
		String quantita=q+"";
		String tot=Est.deci.format(Main.db.getPrezzo(m)*q)+" $";
		String[] riga={id,nome,quantita,tot};
		model.addRow(riga);
	}
	
	public void togli(int x){
		model.removeRow(x);
	}
	
	public void clear(){
		model.setRowCount(0);
	}
	
	public String getNome(int row){
		return model.getValueAt(row,0).toString();
	}
	
	public int getInd(int row){
		return Integer.parseInt((String)model.getValueAt(row,0));
	}
	
	public void repaint(ListaSpesa l){
		clear();
		for (Entry<Integer,Double> m:l.elenco.entrySet()){
			aggiungi(m.getKey(), m.getValue());
		}
	}

}
