package GUI;
import java.awt.Frame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Negozio.ListaSpesa;
import Negozio.Merce;

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
				
		model.addColumn("PRODUCT:");
		model.addColumn("QUANTITY:");
		model.addColumn("ToT:");

		JScrollPane sp=new JScrollPane(tavola);
		sp.add(tavola);
		add(sp);
	}
	
	public JScrollPane ta(){
		JScrollPane sp=new JScrollPane(tavola); 
		return sp;
	}
	
	public void aggiungi(Merce m, Double q){
		String nome=m.getNome();
		String quantita=q+"";
		String tot=Est.deci.format(m.getPrezzoV()*q)+" $";
		String[] riga={nome,quantita,tot};
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
	
	public int getInd(String nome){
		for (int i=0;i<model.getRowCount();i++){
			if (model.getValueAt(i, 0).equals(nome)){
				return i;
			}
		}
		return -1;
	}
	
	public void repaint(ListaSpesa l){
		clear();
		for (Merce m:l.elenco.values()){
			aggiungi(m,m.getQuantita());
		}
	}
}
