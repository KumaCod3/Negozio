package GUI;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import Negozio.Guest;
import java.awt.Component;
import javax.swing.border.EmptyBorder;

public class Home extends Finestra{
	public Home(){
		super("HOME");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(5,1));
		
		
		JPanel bu1=new JPanel();
		bu1.setOpaque(false);
		bu1.setBorder(new EmptyBorder(10, 10, 10, 10));
		Bottone b1=new Bottone("See Persons DataBase");
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.but.setMinimumSize(Est.grosso);
		b1.but.setMaximumSize(Est.grosso);
		b1.but.setPreferredSize(Est.grosso);
		b1.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	setVisible(false);
			}
		});
		bu1.add(b1);
        contenuto.add(bu1);
        
        JPanel bu2=new JPanel();
		bu2.setOpaque(false);
		bu2.setBorder(new EmptyBorder(10, 10, 10, 10));
		Bottone b2=new Bottone("See Products DataBase");
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.but.setMinimumSize(Est.grosso);
        b2.but.setMaximumSize(Est.grosso);
        b2.but.setPreferredSize(Est.grosso);
		b2.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		bu2.add(b2);
        contenuto.add(bu2);
		
        JPanel bu3=new JPanel();
		bu3.setOpaque(false);
		bu3.setBorder(new EmptyBorder(10, 10, 10, 10));
        Bottone b3=new Bottone("QUICK PURCHASE");
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.but.setMinimumSize(Est.grosso);
		b3.but.setMaximumSize(Est.grosso);
		b3.but.setPreferredSize(Est.grosso);
		b3.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Spesa aggg=new Spesa(new Guest());
				aggg.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		bu3.add(b3);
        contenuto.add(bu3);
		
		/*add(contenuto);*/
		
		
        JPanel bott=new JPanel();
		bott.setOpaque(false);
		bott.setBorder(new EmptyBorder(10, 10, 10, 10));
					
		Bottone bes=new Bottone("History");
		bes.but.setMinimumSize(Est.piccolo);
		bes.but.setMaximumSize(Est.piccolo);
		bes.but.setPreferredSize(Est.piccolo);
		bes.but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Storico stor=new Storico();
            	stor.setVisible(true);
		    	setVisible(false);
		    	dispose();
        	}
		});
		bott.add(bes);
		
		Bottone bal=new Bottone("Balance");
		bal.but.setMinimumSize(Est.piccolo);
		bal.but.setMaximumSize(Est.piccolo);
		bal.but.setPreferredSize(Est.piccolo);
		bal.but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Balance bl=new Balance();
            	bl.setVisible(true);
		    	setVisible(false);
		    	dispose();
        	}
		});
		bott.add(bal);
		
		contenuto.add(bott);
		
		JPanel buex=new JPanel();
		buex.setOpaque(false);
		buex.setBorder(new EmptyBorder(10, 10, 10, 10));
		Bottone bex=new Bottone("EXIT");
        bex.but.setMinimumSize(Est.grosso);
		bex.but.setMaximumSize(Est.grosso);
		bex.but.setPreferredSize(Est.grosso);
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
		buex.add(bex);
        contenuto.add(buex);
        c.add("Center", contenuto);
		pack();
	}
}
