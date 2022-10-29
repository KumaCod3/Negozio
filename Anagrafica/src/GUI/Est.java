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
	public static Color sfondo=new Color(91,138,223);
	public static Color scuro=new Color(44,100,198);
	public static Color rosso=new Color(230,20,21);
	public static Color chiarissimo=new Color(117,223,235);
	
	public static Font boldFont=new Font("Arial Rounded MT Bold", Font.BOLD, 20);
	public static Font plainFont=new Font("Arial Rounded MT Bold", Font.PLAIN, 20);
	
	public static Dimension piccolo=new Dimension(190,80);
	public static Dimension grosso=new Dimension(310,80);
	public static Dimension standard=new Dimension(690, 930);
	public static Dimension testa=new Dimension(690, 80);
	
	public static DecimalFormat deci = new DecimalFormat("0.00");
	public static DateTimeFormatter dateForm= DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm.ss");
	
	public static Border bordo= BorderFactory.createEmptyBorder(10,10,10,10);
	public static Border borGro= BorderFactory.createEmptyBorder(20,20,20,20);
	public static Border vuoto=new EmptyBorder(new Insets(3,3,3,3));;
	public static Border aaa= BorderFactory.createLineBorder(Est.sfondo,10);
	public static Border bbb= BorderFactory.createCompoundBorder(aaa,bordo);
	public static Border eti= BorderFactory.createEmptyBorder(30, 20, 20, 70);
	
}