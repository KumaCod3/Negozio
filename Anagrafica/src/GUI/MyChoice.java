package GUI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.BoxLayout;

public class MyChoice extends JPanel{
	JList<String> jList;
	ArrayList<String> elenco=new ArrayList<String>();
	JTextField field;
	
	public MyChoice() {
		super();
		setBorder(Est.bordo);
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	public MyChoice(ResultSet lis,String x){
		this();
		populateMer(lis);
		
		jList = new JList<String>(createDefaultListModel());
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setVisibleRowCount(1);
		
		field=createTextField();
		add(field);
		JScrollPane pap=new JScrollPane(jList);
		pap.setPreferredSize(Est.choi);
		pap.setMaximumSize(Est.choi);
		add(pap);
	}
	
	public MyChoice(ResultSet lis,int x){
		this();
		populateFor(lis);
		
		jList = new JList<String>(createDefaultListModel());
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setVisibleRowCount(6);
		
		field=createTextField();
		add(field);
		JScrollPane pap=new JScrollPane(jList);
		pap.setPreferredSize(Est.choi);
		pap.setMaximumSize(Est.choi);
		add(pap);
	}
	
	public MyChoice(ResultSet lis){
		this();
		populateCli(lis);
		
		jList = new JList<String>(createDefaultListModel());
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setVisibleRowCount(6);
		
		field=createTextField();
		add(field);
		JScrollPane pap=new JScrollPane(jList);
		pap.setPreferredSize(Est.choi);
		pap.setMaximumSize(Est.choi);
		add(pap);
	}
	

	public String getSel() {
		String fin=jList.getSelectedValue();
//		System.out.println(fin);
		return fin;
	}
	
	private void populateMer(ResultSet sor) {
		try {
			while (sor.next()) {		
				String fin=sor.getString("ID_MERCE")+", "+sor.getString("product");
				elenco.add(fin);
			}
		} catch (SQLException e) { e.printStackTrace(); }
	}
	private void populateCli(ResultSet sor) {
		try {
			while (sor.next()) {		
				String fin=sor.getString("ID_CLIENTE")+", "+sor.getString("name")+", "+sor.getString("last_name");
				elenco.add(fin);
			}
		} catch (SQLException e) { e.printStackTrace(); }
	}
	private void populateFor(ResultSet sor) {
		try {
			while (sor.next()) {		
				String fin=sor.getString("ID_FORNITORE")+", "+sor.getString("name")+", "+sor.getString("last_name");
				elenco.add(fin);
			}
		} catch (SQLException e) { e.printStackTrace(); }
	}

	private JTextField createTextField() {
        final JTextField field = new JTextField(15);
        field.setPreferredSize(Est.fil);
        field.setMaximumSize(Est.fil);
        field.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) {}
            private void filter() {
                String filter = field.getText();
                filterModel((DefaultListModel<String>)jList.getModel(), filter);
            }
        });
        return field;
    }
	
	private ListModel<String> createDefaultListModel() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String s : elenco) {
            model.addElement(s);
        }
        return model;
    }
	
	public void filterModel(DefaultListModel<String> model, String filter) {
        for (String s : elenco) {
        	if (s.toLowerCase().contains(filter)) {
        		if (!model.contains(s)) {
                    model.addElement(s);
                }
            } else {
            	if (model.contains(s)) {
                    model.removeElement(s);
                }
                
            }
        }
    }
	
	public void clear() {
		field.setText("");
	}
	
}
