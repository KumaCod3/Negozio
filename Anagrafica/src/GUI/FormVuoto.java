package GUI;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class FormVuoto extends JTextField{
	String ret;
	String tt="";
	
	public FormVuoto(String tit){
		super(tit, 15);
		ret=tit;
		tt=tit;
		setForeground(Est.chiarissimo);
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
				if (getText().equals(tit)){
					setText("");
					setForeground(Color.BLACK);
				}
			}
			public void focusLost(FocusEvent e){
				if (getText().isEmpty()){
					setText(tit);
					setForeground(Est.chiarissimo);
				}
				else ret=getText();
			}
		});
		setFont(Est.plainFont);
		
	}
	
	public void clear(){
		setText(tt);
		setForeground(Est.chiarissimo);
	}
	
	public void setUnchain() {
		for (FocusListener f:getFocusListeners()) {
			removeFocusListener(f);
		}
	}
}
