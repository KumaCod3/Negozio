package GUI;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import Negozio.Anagrafica;
import Negozio.Merce;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.BoxLayout;

public class MyChoice <T extends Anagrafica>extends JPanel{
	JList<String> jList;
	ArrayList<String> elenco=new ArrayList<String>();
	JTextField field;
	
	public MyChoice(HashMap<Integer,Merce> lis){
		super();
		populate(lis);
		setBorder(Est.bordo);
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
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
	
	public MyChoice(ArrayList<T> lis){
		super();
		populate(lis);
		setBorder(Est.bordo);
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
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
		return fin;
	}
	
	private void populate(ArrayList<T> sor) {
		for (T a:sor) {
			String fin=a.getMinIntestazione();
			elenco.add(fin);
		}
		
	}
	
	private void populate(HashMap<Integer,Merce> sor) {
		for (Merce a:sor.values()) {
			String fin=a.getCod()+" "+a.getNome();
			elenco.add(fin);
		}
		
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
