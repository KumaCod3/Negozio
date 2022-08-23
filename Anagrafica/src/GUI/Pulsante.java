package GUI;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class Pulsante extends JButton{
	public Pulsante(String x){
		super(x);

		setMinimumSize(Est.piccolo);
        setMaximumSize(Est.piccolo);
        setPreferredSize(Est.piccolo);
		
		setBorder(Est.bbb);
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