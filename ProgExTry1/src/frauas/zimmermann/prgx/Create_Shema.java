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
	            
	            String createSchemaQuery = "CREATE SCHEMA IF NOT EXISTS SEIFENdemo1";
	            stmt.executeUpdate(createSchemaQuery);

	            // Verwende das Schema
	            String useSchemaQuery = "USE SEIFENdemo1";
	            stmt.executeUpdate(useSchemaQuery);

	            // Erstelle die Tabellen
	            
	            String createSeifenQuery = "CREATE TABLE IF NOT EXISTS Soap (id INT PRIMARY KEY, EAN INT, titel VARCHAR(255), category VARCHAR(50), price DOUBLE, created_at TIMESTAMP)";
	            String createKundeQuery = "CREATE TABLE IF NOT EXISTS customers (id INT PRIMARY KEY, address VARCHAR(255), email VARCHAR(100), password VARCHAR(50), name VARCHAR(50), city VARCHAR(50), birth_date DATE, created_at TIMESTAMP)";
	            String createOrderQuery = "CREATE TABLE IF NOT EXISTS Orders (id INT PRIMARY KEY, user_id INT, order_date TIMESTAMP, status VARCHAR(50), total FLOAT, subtotal INT, tax INT, discount INT)";
	            
	            String createOrderProd = "CREATE TABLE IF NOT EXISTS RefOrderProd(id INT Primary KEY, Oid INT, Sid INT)"; //erstellen der referenztabelle
	            String createEmployer = "CREATE TABLE IF NOT EXISTS employer (employer_id INT PRIMARY KEY, employer_name VARCHAR(100), address VARCHAR(255), email VARCHAR(100), phone_number VARCHAR(20), industry VARCHAR(50), established_date DATE)";
	            String createOrdEmp = "CREATE TABLE IF NOT EXISTS RefOrdEmp(id INT Primary KEY, Oid INT, Eid INT)";
	            
	            stmt.executeUpdate(createSeifenQuery);
	            stmt.executeUpdate(createKundeQuery);
	            stmt.executeUpdate(createEmployer);
	            
	            stmt.executeUpdate(createOrderQuery);
	            stmt.executeUpdate(createOrderProd);
	            stmt.executeUpdate(createOrdEmp);
	            
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
