package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Est {
	public static Color chiaro=new Color(217,243,248);
	public static Color scuro=new Color(2,146,183);
	public static Color medio=new Color(26,200,219);
	public static Color rosso=new Color(230,20,21);
	public static Color oran=new Color(237, 155, 74);
	public static Color chiarissimo=new Color(117,223,235);
	
	public static Font font=new Font("Lucida",Font.PLAIN,24);
	public static Font font2=new Font(Font.DIALOG_INPUT, Font.BOLD,  20);
	
	public static Dimension piccolo=new Dimension(190,80);
	public static Dimension grosso=new Dimension(290,80);
	public static Dimension standard=new Dimension(690, 930);
	
	public static DecimalFormat deci = new DecimalFormat("0.00");
	public static DateTimeFormatter dateForm= DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm.ss");
	
	public static Border bordo= BorderFactory.createEmptyBorder(10,10,10,10);
	public static Border vuoto=new EmptyBorder(new Insets(3,3,3,3));;
	public static Border aaa= BorderFactory.createLineBorder(Est.chiaro,10);
	public static Border bbb= BorderFactory.createCompoundBorder(aaa,bordo);
	
}