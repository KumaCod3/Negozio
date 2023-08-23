package GUI;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Est {
	public static Color sfondo=new Color(91,138,223);
	public static Color scuro=new Color(44,100,198);	// #2C64C6
	public static Color rosso=new Color(230,20,21);
	public static Color chiarissimo=new Color(117,223,235);
	
	public static Font boldFont=new Font("Arial Rounded MT Bold", Font.BOLD, 20);
	public static Font plainFont=new Font("Arial Rounded MT Bold", Font.PLAIN, 20);
	
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static double wi=screenSize.getSize().getWidth()*0.8;
	private static double he=screenSize.getSize().getSize().getHeight()*0.8;
	private static double he80=he/11.5;
	private static double he30=he/31.0;
	private static double wi270=wi/2.5;
	private static double wi190=wi/3.6;
	private static double wi310=wi/2.2;
	private static double wis=wi/1.5;
	public static Dimension standard=new Dimension((int)wi, (int)he);
	public static Dimension piccolo=new Dimension((int)wi190,(int)he80);
	public static Dimension piccoloMin=new Dimension((int)(wi190/3*2),(int)(he80/3*2));
	public static Dimension grosso=new Dimension((int)wi310,(int)he80);
	public static Dimension grossoMin=new Dimension((int)(wi310/3),(int)(he80/3*2));
	public static Dimension testa=new Dimension((int)wi, (int)he80);
	public static Dimension choi=new Dimension((int)wi270,(int)he80);
	public static Dimension fil=new Dimension((int)wi270,(int)he30);
	public static Dimension istr=new Dimension((int)wis,(int)he);
	public static int marginX=(int)((screenSize.getSize().getWidth()/2)-(standard.getSize().getWidth()/2));
	public static int marginY=(int)((screenSize.getSize().getSize().getHeight()/2)-(standard.getSize().getHeight()/2));
	public static int centX=(int)((screenSize.getSize().getWidth()/2));
	public static int centY=(int)((screenSize.getSize().getSize().getHeight()/2));
	
	public static DecimalFormat deci = new DecimalFormat("0.00");
	public static DecimalFormat sco = new DecimalFormat("00");
	public static DateTimeFormatter dateForm= DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm.ss");
	public static DateTimeFormatter dateFormSQL= DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
	public static DateTimeFormatter simpDate= DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static DateTimeFormatter simpDateHH= DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm.ss");
	
	public static Border bordo= BorderFactory.createEmptyBorder(10,10,10,10);
	public static Border bordoCh= BorderFactory.createEmptyBorder(100,10,10,10);
	public static Border borCol= BorderFactory.createMatteBorder(0, 4, 4, 4, scuro);
	public static Border borColHe= BorderFactory.createMatteBorder(4, 4, 0, 4, scuro);
	public static Border borColTut= BorderFactory.createMatteBorder(4, 4, 4, 4, scuro);
	
}