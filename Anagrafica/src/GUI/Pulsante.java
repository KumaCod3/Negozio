package GUI;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Pulsante extends JButton{
	public Pulsante(String x){
		super(x);

		setMinimumSize(Est.piccolo);
        setMaximumSize(Est.piccolo);
        setPreferredSize(Est.piccolo);
		
        
	//	setBorder(Est.bbb);
		setFont(Est.font2);
		if (x.equals("-ENTER-")){
			setBackground(Est.medio);
		}
		else if (x.equals("-EXIT-")){
			setBackground(Est.scuro);
		}
		else {
			setBackground(Est.chiarissimo);
		}
	}

}
