package GUI;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Etichetta extends JLabel{
	public Etichetta(String x){
		setText(x);
		setForeground(Color.WHITE);
		setFont(Est.plainFont);
		setOpaque(false);
		setBorder(Est.vuoto);
	}
}
