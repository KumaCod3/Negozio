package GUI;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
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
// 	int indx=-1;
	public Tabella(){
		tavola=new JTable(model);
		tavola.setBackground(Est.chiaro);
		tavola.setColumnSelectionAllowed(false);
		tavola.setDragEnabled(false);
		tavola.setRowSelectionAllowed(true);
				
		model.addColumn("PRODOTTO:");
		model.addColumn("QUANTIT�:");
		model.addColumn("ToT:");
		/*tavola.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			int indx=-1;
			public void valueChanged(ListSelectionEvent arg0) {
				indx = tavola.getSelectedRow();
			}
			public int getInd(){
				return indx;
			}
		});*/
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
		System.out.println("tolgo "+x);
		model.removeRow(x);
		System.out.println("tolta");
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
}
