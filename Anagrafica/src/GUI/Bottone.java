package GUI;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Bottone extends JPanel{
	public JButton but;
	public Bottone(String x){
		super();
		but=new JButton(x);
		setBorder(Est.bor);
		setLayout(new GridBagLayout());
		setBorder(Est.bordo);
		setBackground(Est.chiaro);

		but.setMinimumSize(Est.piccolo);
		but.setMaximumSize(Est.piccolo);
		but.setPreferredSize(Est.piccolo);
		but.setFont(Est.font2);
		
		if (x.equals("-ENTER-")){
			but.setBackground(Est.medio);
		}
		else if (x.equals("-EXIT-")){
			but.setBackground(Est.scuro);
		}
		else {
			but.setBackground(Est.chiarissimo);
		}
		add(but);
	}

}
