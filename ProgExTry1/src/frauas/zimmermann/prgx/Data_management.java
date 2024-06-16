package frauas.zimmermann.prgx;

import java.sql.*;
import java.util.ArrayList;

public class Data_management {

	private String url;
	private String username;
	private String password;
	
    public Data_management(String usr, String pwd) { 
    	this.url = "jdbc:mysql://localhost:3306/SEIFENdemo2";
        this.username = usr;
        this.password = pwd;
    }

 // Fetch methods
    public ArrayList<Employer> fetchEmployersFromDatabase() {
        ArrayList<Employer> employers = new ArrayList<>();
        try {       
            Connection connection = DriverManager.getConnection(url, username, password);  
            Statement statement = connection.createStatement();  
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employer");  
            
            while(resultSet.next()) { 
                Employer employer = new Employer();
                employer.setEmployerId(resultSet.getInt("employer_id"));
                employer.setEmployerName(resultSet.getString("employer_name"));
                employer.setAddress(resultSet.getString("address"));
                employer.setEmail(resultSet.getString("email"));
                employer.setPhoneNumber(resultSet.getString("phone_number"));
                employer.setIndustry(resultSet.getString("industry"));
                employer.setEstablishedDate(resultSet.getDate("established_date"));
                employers.add(employer);
                
                System.out.println(employer.getEmployerId() + " " + employer.getEmployerName() + " " + employer.getAddress() + " " + employer.getEmail() + " " + employer.getPhoneNumber() + " " + employer.getIndustry() + " " + employer.getEstablishedDate());
            }
            
            connection.close();  
        } catch (Exception e) {
            System.out.println("Error fetching data from the database: " + e.getMessage());  
        }
        return employers;
    }

    public ArrayList<Customers> fetchCustomersFromDatabase() {
        ArrayList<Customers> customers = new ArrayList<>();
        try {       
            Connection connection = DriverManager.getConnection(url, username, password);  
            Statement statement = connection.createStatement();  
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");  
            
            while(resultSet.next()) { 
                Customers customer = new Customers();
                customer.setId(resultSet.getInt("id"));
                customer.setAddress(resultSet.getString("address"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setName(resultSet.getString("name"));
                customer.setCity(resultSet.getString("city"));
                customer.setBirthDate(resultSet.getDate("birth_date"));
                customer.setCreatedAt(resultSet.getTimestamp("created_at"));
                
                customers.add(customer);
                
                System.out.println(customer.getId() + " " + customer.getAddress() + " " + customer.getEmail() + " " + customer.getPassword() + " " + customer.getName() + " " + customer.getCity() + " " + customer.getBirthDate() + " " + customer.getCreatedAt());
            }
            
            connection.close();  
        } catch (Exception e) {
            System.out.println("Error fetching data from the database: " + e.getMessage());  
        }
        return customers;
    }

    public ArrayList<Orders> fetchOrdersFromDatabase() {
        ArrayList<Orders> orders = new ArrayList<>();
        try {       
            Connection connection = DriverManager.getConnection(url, username, password);  
            Statement statement = connection.createStatement();  
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");  
            
            while(resultSet.next()) { 
                Orders order = new Orders();
                order.setId(resultSet.getInt("id"));
                order.setUser_id(resultSet.getInt("user_id"));
                order.setOrder_date(resultSet.getDate("order_date"));
                order.setStatus(resultSet.getString("status"));
                order.setTotal(resultSet.getInt("total"));
                order.setSubtotal(resultSet.getInt("subtotal"));
                order.setTax(resultSet.getFloat("tax"));
                order.setDiscount(resultSet.getInt("discount"));
                
                orders.add(order);
                
                System.out.println(order.getId() + " " + order.getUser_id() + " " + order.getOrder_date() + " " + order.getStatus() + " " + order.getTotal() + " " + order.getSubtotal() + " " + order.getTax() + " " + order.getDiscount());
            }
            
            connection.close();  
        } catch (Exception e) {
            System.out.println("Error fetching data from the database: " + e.getMessage());  
        }
        return orders;
    }

    public ArrayList<Soap> fetchSoapsFromDatabase() {
        ArrayList<Soap> soaps = new ArrayList<>();
        try {       
            Connection connection = DriverManager.getConnection(url, username, password);  
            Statement statement = connection.createStatement();  
            ResultSet resultSet = statement.executeQuery("SELECT * FROM soap");  
            
            while(resultSet.next()) { 
                Soap soap = new Soap();
                soap.setId(resultSet.getInt("id"));
                soap.setEAN(resultSet.getInt("EAN"));
                soap.setTitle(resultSet.getString("title"));
                soap.setCategory(resultSet.getString("category"));
                soap.setPrice(resultSet.getDouble("price"));
                soap.setCreatedAt(resultSet.getTimestamp("created_at"));
                
                soaps.add(soap);
                
                System.out.println(soap.getId() + " " + soap.getEAN() + " " + soap.getTitle() + " " + soap.getCategory() + " " + soap.getPrice() + " " + soap.getCreatedAt());
            }
            
            connection.close();  
        } catch (Exception e) {
            System.out.println("Error fetching data from the database: " + e.getMessage());  
        }
        return soaps;
    }

  // Add methods
    public void addEmployer(Employer employer) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            
            String sql = "INSERT INTO employer (employer_id, employer_name, address, email, phone_number, industry, established_date) VALUES (" 
                         + employer.getEmployerId() + ", '" 
                         + employer.getEmployerName() + "', '" 
                         + employer.getAddress() + "', '" 
                         + employer.getEmail() + "', '" 
                         + employer.getPhoneNumber() + "', '" 
                         + employer.getIndustry() + "', '" 
                         + employer.getEstablishedDate() + "')";
            
            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows > 0) {
                System.out.println("Employer successfully added.");
            } else { 
                System.out.println("Error adding employer."); 
            }

            connection.close();
        } catch (Exception e) {
        	System.out.println("Error adding employer: " + e.getMessage());
        }
    }

    public void addCustomer(Customers customer) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            
            String sql = "INSERT INTO customers (id, address, email, password, name, city, birth_date, created_at) VALUES (" 
                         + customer.getId() + ", '" 
                         + customer.getAddress() + "', '" 
                         + customer.getEmail() + "', '" 
                         + customer.getPassword() + "', '" 
                         + customer.getName() + "', '" 
                         + customer.getCity() + "', '" 
                         + customer.getBirthDate() + "', '" 
                         + customer.getCreatedAt() + "')";
            
            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows > 0) {
                System.out.println("Customer successfully added.");
            } else { 
                System.out.println("Error adding customer."); 
            }

            connection.close();
        } catch (Exception e) {
        	System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    public void addOrder(Orders order) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            
            String sql = "INSERT INTO orders (id, user_id, order_date, status, total, subtotal, tax, discount) VALUES (" 
                         + order.getId() + ", " 
                         + order.getUser_id() + ", '" 
                         + order.getOrder_date() + "', '" 
                         + order.getStatus() + "', " 
                         + order.getTotal() + ", " 
                         + order.getSubtotal() + ", " 
                         + order.getTax() + ", " 
                         + order.getDiscount() + ")";
            
            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows > 0) {
                System.out.println("Order successfully added.");
            } else { 
                System.out.println("Error adding order."); 
            }

            connection.close();
        } catch (Exception e) {
        	System.out.println("Error adding order: " + e.getMessage());
        }
    }

    public void addSoap(Soap soap) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            
            String sql = "INSERT INTO soap (id, EAN, title, category, price, created_at) VALUES (" 
                         + soap.getId() + ", " 
                         + soap.getEAN() + ", '" 
                         + soap.getTitle() + "', '" 
                         + soap.getCategory() + "', " 
                         + soap.getPrice() + ", '" 
                         + soap.getCreatedAt() + "')";
            
            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows > 0) {
                System.out.println("Soap successfully added.");
            } else { 
                System.out.println("Error adding soap."); 
            }

            connection.close();
        } catch (Exception e) {
        	System.out.println("Error adding soap: " + e.getMessage());
        }
    }
    
 //delete methods
    public void deleteEmployer(Employer employer) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "DELETE FROM employer WHERE employer_id = " + employer.getEmployerId();
            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows > 0) {
                System.out.println("Employer successfully deleted.");
            } else {
                System.out.println("Error: No employer deleted.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting employer: " + e.getMessage());
        }
    }

    public void deleteCustomer(Customers customer) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "DELETE FROM customers WHERE id = " + customer.getId();
            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows > 0) {
                System.out.println("Customer successfully deleted.");
            } else {
                System.out.println("Error: No customer deleted.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    public void deleteOrder(Orders order) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "DELETE FROM orders WHERE id = " + order.getId();
            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows > 0) {
                System.out.println("Order successfully deleted.");
            } else {
                System.out.println("Error: No order deleted.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting order: " + e.getMessage());
        }
    }

    public void deleteSoap(Soap soap) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "DELETE FROM soap WHERE id = " + soap.getId();
            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows > 0) {
                System.out.println("Soap successfully deleted.");
            } else {
                System.out.println("Error: No soap deleted.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting soap: " + e.getMessage());
        }
    }
    
    
    
    public ArrayList<Soap> fetchProductsByOrderId(int orderId) {
        ArrayList<Soap> soaps = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            
            String sql = "SELECT s.id, s.EAN, s.title, s.category, s.price, s.created_at " +
                         "FROM soap AS s " +
                         "INNER JOIN RefOrderProd AS ref ON s.id = ref.Sid " +
                         "INNER JOIN orders AS o ON o.id = ref.Oid " +
                         "WHERE o.id = " + orderId;
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Soap soap = new Soap();
                soap.setId(resultSet.getInt("id"));
                soap.setEAN(resultSet.getInt("EAN"));
                soap.setTitle(resultSet.getString("title"));
                soap.setCategory(resultSet.getString("category"));
                soap.setPrice(resultSet.getDouble("price"));
                soap.setCreatedAt(resultSet.getTimestamp("created_at"));

                soaps.add(soap);
                System.out.println(soap.getId() + " " + soap.getEAN() + " " + soap.getTitle() + " " + soap.getCategory() + " " + soap.getPrice() + " " + soap.getCreatedAt());
            }
            
            connection.close();
        } catch (Exception e) {
            System.out.println("Error fetching products by order id: " + e.getMessage());
        }
        return soaps;
    }
    
    public void addOrderProductReference(int orderId, int soapId) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            
            String sql = "INSERT INTO RefOrderProd (Oid, Sid) VALUES (" 
                         + orderId + ", "
                         + soapId + ")";
            
            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows > 0) {
                System.out.println("Order-Product reference successfully added.");
            } else { 
                System.out.println("Error adding Order-Product reference."); 
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error adding Order-Product reference: " + e.getMessage());
        }
    }
    
}
    
