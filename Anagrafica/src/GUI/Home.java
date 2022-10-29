package GUI;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import Negozio.Guest;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Font;

public class Home extends Frame{
	public Home(){
		super("HOME");
		addWindowListener(Finestra.close);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLocation(70,70);
		setBackground(SystemColor.menu);
		setPreferredSize(Est.standard);
		setUndecorated(true);
		
		Header inte=new Header();
		inte.setBackground(SystemColor.menu);
		add(inte);
		
		JPanel bu1=new JPanel();
		bu1.setOpaque(false);
		bu1.setBorder(new EmptyBorder(10, 10, 10, 10));
		MyButVoi b1=new MyButVoi("See Persons DataBase", Color.GRAY, Color.BLACK,3);
		b1.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		b1.setForeground(SystemColor.controlText);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setMinimumSize(Est.grosso);
		b1.setMaximumSize(Est.grosso);
		b1.setPreferredSize(Est.grosso);
		b1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaPersone consultaP=new ConsultaPersone();
		    	consultaP.setVisible(true);
		    	setVisible(false);
			}
		});
		bu1.add(b1);
        /*contenuto.*/add(bu1);
		
        
        JPanel bu2=new JPanel();
		bu2.setOpaque(false);
		bu2.setBorder(new EmptyBorder(10, 10, 10, 10));
        MyButVoi b2=new MyButVoi("See Products DataBase", Color.GRAY, Color.BLACK,3);
        b2.setForeground(SystemColor.desktop);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setMinimumSize(Est.grosso);
        b2.setMaximumSize(Est.grosso);
        b2.setPreferredSize(Est.grosso);
		b2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ConsultaMerci consultaM=new ConsultaMerci();
		    	consultaM.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		bu2.add(b2);
        /*contenuto.*/add(bu2);
		
        JPanel bu3=new JPanel();
		bu3.setOpaque(false);
		bu3.setBorder(new EmptyBorder(10, 10, 10, 10));
        MyBut b3=new MyBut("--QUICK PURCHASE--", Color.GRAY, Color.BLACK);
        b3.setForeground(Color.YELLOW);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setMinimumSize(Est.grosso);
		b3.setMaximumSize(Est.grosso);
		b3.setPreferredSize(Est.grosso);
		b3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Spesa aggg=new Spesa(new Guest());
				aggg.setVisible(true);
		    	setVisible(false);
		    	dispose();
			}
		});
		bu3.add(b3);
        /*contenuto.*/add(bu3);
		
		/*add(contenuto);*/
		
		
        JPanel bott=new JPanel();
		bott.setOpaque(false);
		bott.setBorder(new EmptyBorder(10, 10, 10, 10));
					
		MyButVoi bes=new MyButVoi("-History-", Color.GRAY, Color.BLACK,2);
		bes.setForeground(SystemColor.desktop);
		bes.setMinimumSize(Est.piccolo);
		bes.setMaximumSize(Est.piccolo);
		bes.setPreferredSize(Est.piccolo);
		bes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Storico stor=new Storico();
            	stor.setVisible(true);
		    	setVisible(false);
		    	dispose();
        	}
		});
		bott.add(bes);
		
		MyButVoi bal=new MyButVoi("-Balance-", Color.GRAY, Color.BLACK,2);
		bal.setForeground(SystemColor.controlText);
		bal.setMinimumSize(Est.piccolo);
		bal.setMaximumSize(Est.piccolo);
		bal.setPreferredSize(Est.piccolo);
		bal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Balance bl=new Balance();
            	bl.setVisible(true);
		    	setVisible(false);
		    	dispose();
        	}
		});
		bott.add(bal);
		
		add(bott);
		
		JPanel buex=new JPanel();
		buex.setOpaque(false);
		buex.setBorder(new EmptyBorder(10, 10, 10, 10));
		MyButVoi bex=new MyButVoi("-EXIT-", Color.GRAY, Color.BLACK,7);
		bex.setForeground(Color.RED);
        bex.setMinimumSize(Est.grosso);
		bex.setMaximumSize(Est.grosso);
		bex.setPreferredSize(Est.grosso);
		bex.addActionListener(new ActionListener() {
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
        /*contenuto.*/add(buex);
        
		pack();
	}
}
