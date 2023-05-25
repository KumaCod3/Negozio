package Negozio;
import GUI.Main;

public class Shop {
	private static double merchVal;
	private static double totSold;
	private static double totBought;
	private static double bal;
	
	public static double getMerchVal(){
		merchVal=Main.db.getMercVal();
		return merchVal;
	}
	public static double getTotSold(){
		totSold=Main.db.getTotSold();
		return totSold;
	}
	public static double getTotBought(){
		totBought=Main.db.getTotBought();
		return totBought;
	}
	
	public static double getBal(){
		bal=totSold-totBought;
		return bal;
	}
}
