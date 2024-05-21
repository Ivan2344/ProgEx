package frauas.zimmermann.prgx;
import java.sql.*;


public class MainProg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		System.out.print("Lolz3");
		
		Create_Shema s1 = new Create_Shema("salat", "amd2");
		s1.CreateDB();
	}

}
