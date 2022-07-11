package GUI;
import java.awt.Frame;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Negozio.Merce;

public class StorTab extends Frame{
	public HashMap<LocalDate,String[]> elenco=new HashMap<LocalDate,String[]>();
	JTable tavola;
	DefaultTableModel model = new DefaultTableModel() {
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	public StorTab(){
		elenco=MyReadL.carica();
		
		
		
		tavola=new JTable(model);
		tavola.setBackground(Est.chiaro);
		tavola.setColumnSelectionAllowed(false);
		tavola.setDragEnabled(false);
		tavola.setRowSelectionAllowed(true);
				
		model.addColumn("DATA:");
		model.addColumn("COGNOME:");
		model.addColumn("NOME:");
		model.addColumn("TOT:");
		
		for (Entry<LocalDate,String[]> entry:elenco.entrySet()){
			
			String[] riga={entry.getKey().format(Est.dateForm),entry.getValue()[0],entry.getValue()[1],entry.getValue()[2]};
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
	
	/*
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
	}*/
}
