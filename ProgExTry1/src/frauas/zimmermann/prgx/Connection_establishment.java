package frauas.zimmermann.prgx;


import java.sql.*;
import java.sql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Connection_establishment{
	
	private String url = "jdbc:mysql://localhost:3306/";  
	private String username = "root";
	private String password = "12345678";
	
	public  Connection_establishment(){ 

	}
		
	
	public void fetchProductsFromDatabase() {
	
		try {       
			//Class.forName("com.mysql.cj.jdbc.Driver");        
			Connection connection = DriverManager.getConnection(url, username, password);  
			Statement statement = connection.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from products");  
		
			while(resultSet.next()) { 
				Products products = new Products();
			
				products.setId(resultSet.getInt(1));
				products.setEAN(resultSet.getInt(2)); 
				
            
				System.out.println(products.getId() + " " + products.getEAN());
			}
		
		
			connection.close();  
	
		}
		catch (Exception e) {
			System.out.println("Error fetching data from the database: " + e.getMessage());  
		}
	}
	
	
	 public void addProduct(Products product) {
	        try {

	            Connection connection = DriverManager.getConnection(url, username, password);
	            Statement statement = connection.createStatement();
	            

	            String sql = "INSERT INTO products (id, gender, fnam, nnam) "
	                       + "VALUES (" + product.getId() + ", " + product.getEAN() + ", '" + product.getTitle() + "', '" + product.getPrice() + "')";

	       
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
	 
	 public void addCustomer(Customers customer) {
	        try {

	            Connection connection = DriverManager.getConnection(url, username, password);
	            Statement statement = connection.createStatement();
	            

	            String sql =  "INSERT INTO customers (id, gender, fnam, nnam) "
	                       + "VALUES (" + customer.getId() + ")";
	       
	            int rows = statement.executeUpdate(sql);

	     
	            if (rows > 0) {
	                System.out.println("Customer erfolgreich hinzugefügt.");
	            } else { 
	                System.out.println("Fehler beim Hinzufügen der Customer."); 
	            }

	            connection.close();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
	 
	 public void deleteProduct(Products product) {
		 try (
				 Connection connection = DriverManager.getConnection(url, username, password);
	             Statement statement = connection.createStatement()) {

	   
	            String sql = "DELETE FROM products WHERE id = " + product.getId();

	           
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
	 
	 public void deleteCustomer(Customers customer) {
		 try (
				 Connection connection = DriverManager.getConnection(url, username, password);
	             Statement statement = connection.createStatement()) {

	   
	            String sql = "DELETE FROM customers WHERE id = " + customer.getId();

	           
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
	 
	 public void deleteOrder(Orders order) {
		 try (
				 Connection connection = DriverManager.getConnection(url, username, password);
	             Statement statement = connection.createStatement()) {

	   
	            String sql = "DELETE FROM orders WHERE id = " + order.getId();

	           
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