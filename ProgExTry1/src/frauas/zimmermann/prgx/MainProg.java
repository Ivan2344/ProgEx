package frauas.zimmermann.prgx;
import java.sql.*;


public class MainProg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		System.out.print("Lolz3");
		Create_Shema c1 = new Create_Shema("", "");
		c1.CreateDB();
		c1.setSch("jdbc:mysql://localhost:3306/SEIFENdemo1");
		c1.InsertDemoValues();
		GUI new2 = new GUI();
		OrderFrame new1 = new OrderFrame();
	}
}