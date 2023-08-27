package Negozio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.Errore;

public class DBhandler {
	public MyDB db;
	public boolean ready=true;
	String ppw;
	
	public DBhandler(){
		chiediPW();
		do {
			try {
			Thread.sleep(1);
			}catch (Exception ee) {ee.printStackTrace();}
		} while (ppw==null);
//		System.out.println("Provo");
	    db=new MyDB(ppw, this);
//	    ready=false;
	}
	
	private void chiediPW() {
		String[] pw=new String[1];
		float xxx=2f;
		Errore er=new Errore(xxx);
		er.setVisible(true);
		er.ok.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String qtt="";
		    	try {
		    		qtt=er.tf1.ret;
		    		pw[0]=qtt;
		    		ppw=qtt;
//		    		System.out.println("passNEW "+qtt);
		    		er.dispose();
		    	}
		    	catch (Exception ex) { ex.printStackTrace(); }
			}
		});
	}
}
