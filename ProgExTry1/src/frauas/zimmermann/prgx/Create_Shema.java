package frauas.zimmermann.prgx;
import java.sql.*;

public class Create_Shema 
{
	private String url;
	private String user;
	private String password;
	Create_Shema(String usr, String pwd)
	{
		this.url = "jdbc:mysql://localhost:3306/";
        this.user = usr;
        this.password = pwd;
	}
	void CreateDB()
	{
		 try {
	            Connection conn = DriverManager.getConnection(url, user, password);
	            Statement stmt = conn.createStatement();

	            // Erstelle das Schema
	            
	            String createSchemaQuery = "CREATE SCHEMA IF NOT EXISTS SEIFENdemo";
	            stmt.executeUpdate(createSchemaQuery);

	            // Verwende das Schema
	            String useSchemaQuery = "USE SEIFENdemo";
	            stmt.executeUpdate(useSchemaQuery);

	            // Erstelle die Tabellen
	            
	            String createTable1Query = "CREATE TABLE IF NOT EXISTS Seifen (id INT PRIMARY KEY, EAN INT, titel VARCHAR(255), category VARCHAR(50), price DOUBLE, created_at TIMESTAMP)";
	            String createTable2Query = "CREATE TABLE IF NOT EXISTS Kunde (id INT PRIMARY KEY, address VARCHAR(255), email VARCHAR(100), password VARCHAR(50), name VARCHAR(50), city VARCHAR(50), birth_date DATE, created_at TIMESTAMP)";
	            String createTable3Query = "CREATE TABLE IF NOT EXISTS Order (id INT PRIMARY KEY, user_id INT, order_date DATE, status VARCHAR(50), total INT, subtotal INT, tax FLOAT, discount INT)";

	            stmt.executeUpdate(createTable1Query);
	            stmt.executeUpdate(createTable2Query);
	            stmt.executeUpdate(createTable3Query);

	            System.out.println("Schema und Tabellen erfolgreich erstellt.");

	            conn.close();
	        } catch (Exception e) {
	            System.err.println("Fehler beim Erstellen des Schemas und der Tabellen: " + e.getMessage());
	        }
	}
	
	void InsertDemoValues() 
	{
		
	}
}
