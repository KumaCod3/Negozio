package GUI;

import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Etichetta extends JLabel{
	public Etichetta(String x){
		setText(x);
		setFont(Est.font);
		setBackground(Est.oran);
		setOpaque(true);
		setBorder(Est.vuoto);
	}
}
