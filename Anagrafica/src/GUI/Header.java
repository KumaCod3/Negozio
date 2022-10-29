package GUI;
import java.awt.FlowLayout;
import java.awt.Label;
import javax.swing.JPanel;

public class Header extends JPanel {
	public Header(){
		super();
		setBackground(Est.sfondo);
		setLayout(new FlowLayout());
		
		MyPanel pan=new MyPanel();
		pan.setOpaque(false);
		add(pan);
		Label tz=new Label();
		tz.setText("Powered by Pucchosa.sa :) ");
		add(tz);
		
		

	}
	
}