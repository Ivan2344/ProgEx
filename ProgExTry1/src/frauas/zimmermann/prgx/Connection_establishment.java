package frauas.zimmermann.prgx;


import java.sql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Connection_establishment{
	
	private String url = "jdbc:mysql://localhost:3306/jdbc";  
	private String username = "root";
	private String password = "12345678";
	
	public  Connection_establishment(){ 
		fetchFromDatabase();
	}
		
	
	public void fetchFromDatabase() {
	
		try {       
			Class.forName("com.mysql.cj.jdbc.Driver");        
			Connection connection = DriverManager.getConnection(url, username, password);  
			Statement statement = connection.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from person");  
		
			while(resultSet.next()) { 
				Person person = new Person();
			
				person.setId(resultSet.getInt(1));
				person.setGender(resultSet.getInt(2)); 
				person.setFnam(resultSet.getString(3)); 
				person.setNnam(resultSet.getString(4)); 
            
				System.out.println(person.getId() + " " + person.getGender() + " " + person.getFnam() + " " + person.getNnam());
			}
		
		
			connection.close();  
	
		}
		catch (Exception e) {
			System.out.println(e);  
		}
	}
	
	
	 public void addPerson(Person person) {
	        try {

	            Connection connection = DriverManager.getConnection(url, username, password);
	            Statement statement = connection.createStatement();
	            

	            String sql = "INSERT INTO person (id, gender, fnam, nnam) "
	                       + "VALUES (" + person.getId() + ", " + person.getGender() + ", '" + person.getFnam() + "', '" + person.getNnam() + "')";

	       
	            int rows = statement.executeUpdate(sql);

	     
	            if (rows > 0) {
	                System.out.println("Person erfolgreich hinzugefügt.");
	            } else { 
	                System.out.println("Fehler beim Hinzufügen der Person."); 
	            }

	            connection.close();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
	 
	 public void deletePerson(Person person) {
		 try (
				 Connection connection = DriverManager.getConnection(url, username, password);
	             Statement statement = connection.createStatement()) {

	   
	            String sql = "DELETE FROM person WHERE id = " + person.getId();

	           
	            int rowsAffected = statement.executeUpdate(sql);

	            if (rowsAffected > 0) {
	                System.out.println("Eintrag erfolgreich gelöscht.");
	            } else {
	                System.out.println("Fehler: Kein Eintrag gelöscht.");
	            }
	        } catch (Exception e) {
	            System.out.println("Fehler beim Löschen des Eintrags: " + e.getMessage());
	        }
	    }
	 }