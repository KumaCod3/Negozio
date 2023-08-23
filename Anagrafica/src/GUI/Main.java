package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.SQLException;
import java.util.ArrayList;

import Negozio.DBhandler;
import Negozio.MyDB;
	
public class Main{
	
	
	
	public static void main(String[] args) {
		DBhandler dd=new DBhandler();
		do {
			
		} while (dd.ready);
		Home prova=new Home();
		prova.setVisible(true);

	}
	
	static public void restartApplication(){
		StringBuilder cmd = new StringBuilder();
        cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
        for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
            cmd.append(jvmArg + " ");
        }
        cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
        cmd.append(Main.class.getName()).append(" ");
//        for (String arg : args) {
//            cmd.append(arg).append(" ");
//        }
        try {
			Runtime.getRuntime().exec(cmd.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.exit(0);
	}
}