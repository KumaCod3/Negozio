package GUI;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DownloadSQLinfo extends Finestra{		
	public DownloadSQLinfo() {
		super("Please Install MySQL: ");
		setMinimumSize(Est.istrMin);
		if (Est.istrMin.getWidth()>Est.istr.getWidth()||Est.istrMin.getHeight()>Est.istr.getHeight()) {
			setPreferredSize(Est.istr);
		}
//		System.out.println("larg "+Est.istrMin.getWidth()+" alte "+Est.istrMin.getHeight());
		JPanel foot=new JPanel();
		foot.setBorder(Est.bordo);
		foot.setOpaque(false);
		foot.setLayout(new GridLayout(1,2));
		c.add("South", foot);
		
		Etichetta non=new Etichetta("<html><big style=\"font-size:30px; font-weight:bold; color:black; \">Please download and install MySQL: </big></html>");
		non.setHorizontalAlignment(JLabel.CENTER);
		non.setVerticalAlignment(JLabel.CENTER);
		c.add("North", non);	

		String ist="<html><big style=\"font-size:20px; color:#2C64C6; \">To work, this app needs a local MySQL Database, "
				+ " It looks like you didn't install or connect it correctly."
				+ "<br> "
				+ "<br>To fix it, follow the next step: </big>"
				+ "<br> "
				+ "<br>- Click \"DOWNLOAD\" to open the MySql download page (the community edition is free for personal use) and download the installer; "
				+ "<br>- Run the installation wizard and keep all the default options (Go \"Next\" a few times, then \"Install\", and some more \"Next\"); "
				+ "<br>- When you are asked to enter a PASSWORD, remember it because you will need it for every run; "
				+ "<br>- After clicking on \"Finish\" you will be asked for the PASSWORD to create the connection, enter it and \"Check\", if the connection is successful you can proceed with the installation;"
				+ "<br>- At the end it will open a command prompt and the workbench program, you can close everything. "
				+ "<br> "
				+ "<br><big style=\"font-size:30px; font-weight:bold; color:#2C64C6; \">"
				+ "Everything's ready! Click \"ESC\" on this page and run this program again!</big></html>";
		
		Etichetta nn=new Etichetta("");
		nn.setText(ist);
		nn.setHorizontalAlignment(JLabel.CENTER);
		c.add("Center", nn);
		
		Bottone esc=new Bottone("ESC");
		esc.but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		foot.add(esc);
		
		Bottone dwn=new Bottone("DOWNLOAD");
		dwn.but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scaricaMySQL();
			}
		});
		foot.add(dwn);
		
		pack();
	}
	
	private static void scaricaMySQL() {
//		System.out.println("MySQL not installed, install it please.");
		try {
	        Desktop.getDesktop().browse(new URL("https://dev.mysql.com/downloads/mysql/").toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
