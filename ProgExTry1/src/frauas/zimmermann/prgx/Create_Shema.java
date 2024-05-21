package frauas.zimmermann.prgx;
import java.sql.*;

public class Create_Shema 
{
	private String url;
	private String user;
	private String password;
	Create_Shema(String usr, String pwd)
	{
		this.url = "jdbc:mysql://localhost:3306/jdbc";
        this.user = usr;
        this.password = pwd;
	}
	void CreateDB()
	{
		 try {
	            Connection conn = DriverManager.getConnection(url, user, password);
	            Statement stmt = conn.createStatement();

	            // Erstelle das Schema
	            
	            String createSchemaQuery = "CREATE SCHEMA IF NOT EXISTS SEIFEN1";
	            stmt.executeUpdate(createSchemaQuery);

	            // Verwende das Schema
	            String useSchemaQuery = "USE SEIFEN1";
	            stmt.executeUpdate(useSchemaQuery);

	            // Erstelle die Tabellen
	            
	            String createTable1Query = "CREATE TABLE IF NOT EXISTS table1 (id INT PRIMARY KEY, name VARCHAR(255))";
	            String createTable2Query = "CREATE TABLE IF NOT EXISTS table2 (id INT PRIMARY KEY, age INT)";
	            String createTable3Query = "CREATE TABLE IF NOT EXISTS table3 (id INT PRIMARY KEY, address VARCHAR(255))";

	            stmt.executeUpdate(createTable1Query);
	            stmt.executeUpdate(createTable2Query);
	            stmt.executeUpdate(createTable3Query);

	            System.out.println("Schema und Tabellen erfolgreich erstellt.");

	            conn.close();
	        } catch (Exception e) {
	            System.err.println("Fehler beim Erstellen des Schemas und der Tabellen: " + e.getMessage());
	        }
	}
}
