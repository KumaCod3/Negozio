package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ConsultaTrans extends Finestra{
	int idTrans;
	int idPers;
	String data;
	LocalDateTime dataFI=LocalDateTime.now();
	LocalDateTime dataIN=dataFI.minusMonths(1);
	double price;
	String file;
	String note;
	ArrayList<String> elenco=new ArrayList<String>();
	
	public ConsultaTrans(String s) {
		super("Find "+s);
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(2,1));
		c.add("Center", contenuto);
		JPanel centro=new JPanel();
		centro.setBorder(Est.bordo);
		centro.setOpaque(false);
		centro.setLayout(new GridLayout(5,3));
		contenuto.add(centro);
		// 5 righe con etic, campo e bottone
		// prima
		Etichetta datI=new Etichetta("between: ");
		centro.add(datI);
		FormVuoto datIform = new FormVuoto(""+Est.dateForm.format(dataIN));
		centro.add(datIform);
		Etichetta vuo=new Etichetta("     ");
		centro.add(vuo);
		// seconda
		Etichetta datF=new Etichetta("and: ");
		centro.add(datF);
		FormVuoto datFform = new FormVuoto(""+Est.dateForm.format(dataFI));
		centro.add(datFform);
		Bottone bDat=new Bottone("Find dates");
		bDat.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	// TODO
			}
		});
		centro.add(bDat);
		
		
		
		
		
//		try {
//			ResultSet sor=Main.db.getAcquisti(dataIN, dataFI);
//			while (sor.next()) {		
//				String fin=sor.getString("ID_ACQUISTO")+", "+sor.getString("Moment")+", "+sor.getString("name")+" "+sor.getString("Last_name")+", "+sor.getString("price");
//				elenco.add(fin);
//			}
//		} catch (SQLException e) { e.printStackTrace(); }
		StorTab tab=new StorTab(elenco);
		contenuto.add(tab);
		
		
		
		JPanel foot=new JPanel();
		foot.setBorder(Est.bordo);
		foot.setOpaque(false);
		foot.setLayout(new GridLayout(1,2));
		c.add("South", foot);
		
		// due bottoni
	}

}
