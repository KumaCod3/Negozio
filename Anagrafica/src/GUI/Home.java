package GUI;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import Negozio.Guest;
import javax.swing.SwingConstants;
import java.awt.Component;

public class Home extends Frame{
	public Home(){
		super("HOME");
		addWindowListener(Finestra.close);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLocation(70,70);
		setBackground(Est.chiaro);
		setPreferredSize(Est.standard);
		
		Header inte=new Header();
		add(inte);
		
		Bottone b1=new Bottone("See Persons DataBase", 5);
		b1.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	setVisible(false);
			}
		});
        /*contenuto.*/add(b1);
		
        Bottone b2=new Bottone("See Products DataBase", 5);
		b2.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
        /*contenuto.*/add(b2);
		
        MyBut b3=new MyBut("--QUICK PURCHASE--");
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setMinimumSize(Est.grosso);
		b3.setMaximumSize(Est.grosso);
		b3.setPreferredSize(Est.grosso);
		b3.setFont(Est.font2);
		b3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Spesa aggg=new Spesa(new Guest());
				aggg.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
        /*contenuto.*/add(b3);
		
		/*add(contenuto);*/
		
		
		JPanel bott=new JPanel();
		bott.setLayout(new GridLayout(1,2));
		bott.setMaximumSize(new Dimension(480, 180));
					
		Bottone bes=new Bottone("-HISTORY-");
		bes.but.setBackground(Est.medio);
		bes.but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Storico stor=new Storico();
            	stor.setVisible(true);
		    	setVisible(false);
		    	dispose();
        	}
		});
		bott.add(bes);
		
		Bottone bal=new Bottone("-BALANCE-");
		bal.but.setBackground(Est.medio);
		bal.but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Balance bl=new Balance();
            	bl.setVisible(true);
		    	setVisible(false);
		    	dispose();
        	}
		});
		bott.add(bal);
		
		add(bott);
		
		Bottone bex=new Bottone("-EXIT-", 5);
		bex.but.setBackground(Est.rosso);
		bex.but.setAlignmentX(Panel.CENTER_ALIGNMENT);
		bex.but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MyReadM.scarica();
    			MyReadF.scarica();
    			MyReadC.scarica();
            	setVisible(false);
            	dispose();
            	System.exit(0);
        	}
		});
        add(bex);
        
		pack();
	}
}
