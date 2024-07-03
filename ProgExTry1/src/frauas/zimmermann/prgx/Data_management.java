
    package frauas.zimmermann.prgx;

    import java.sql.*;
    import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

    public class Data_management {

    	private String url;
    	private String username;
    	private String password;
    	public String errorMessage = "";
    	

    	/**
    	    * Constructor: Data_Management
    	    * @param usr the username for access to mysql workbench
    	    * @param psw the password for access to the mysql workbench
    	    * Description: sets username, password and the JDBC-url to locally connect with the MySql-Database through port 3360
    	    */
        public Data_management(String usr, String pwd) { 
        	this.url = "jdbc:mysql://localhost:3306/SEIFENdemo2";
            this.username = usr;
            this.password = pwd;
        }
        
        

        public int findMaxId(String panelName) {
        	int nextId =-1;

        	 try {
        	        Connection connection = DriverManager.getConnection(url, username, password);
        	        Statement statement = connection.createStatement();
        	        String sql = "";
        	        if ("Orders".equals(panelName)) {
        	            sql = "SELECT MAX(ID)  AS nextId FROM Orders";
        	        } else if ("Employees".equals(panelName)) {
        	            sql = "SELECT MAX(employer_id)  AS nextId FROM employer";
        	        } else if ("Products".equals(panelName)) {
        	            sql = "SELECT MAX(ID) AS nextId FROM Soap";
        	        } else if ("Customers".equals(panelName)) {
        	            sql = "SELECT MAX(ID)  AS nextId FROM customers";
        	        }
        	        
        	        ResultSet resultSet = statement.executeQuery(sql);
        	        if (resultSet.next()) {
        	            nextId = resultSet.getInt("nextId");
        	        }
        	        connection.close();
        	    } catch (Exception e) {
        	        System.out.println("Error fetching next available ID: " + e.getMessage());
        	    }
        	    return nextId;
        }
        

        /**
         * Fetches employers and adds them to the ArrayList
         *
         * @return a list of employers
         */
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

        /**
         * Fetches customers and adds them to the ArrayList
         *
         * @return a list of customers
         */
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

        /**
         * Fetches orders and adds them to the ArrayList
         *
         * @return a list of orders
         */
        public ArrayList<Orders> fetchOrdersFromDatabase() {
            ArrayList<Orders> orders = new ArrayList<>();
            try {       
                Connection connection = DriverManager.getConnection(url, username, password);  
                Statement statement = connection.createStatement();  
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders");  
                
                while(resultSet.next()) { 
                    Orders order = new Orders();
                    order.setId(resultSet.getInt("id"));
                    order.setUser_id(resultSet.getInt("user_id"));
                    order.setEmployee_id(resultSet.getInt("employer_id"));
                    order.setOrder_date(resultSet.getDate("order_date"));
                    order.setStatus(resultSet.getString("status"));
                    order.setTotal(resultSet.getInt("total"));
                    order.setTax(resultSet.getFloat("tax"));
                    order.setDiscount(resultSet.getInt("discount"));
                    
                    orders.add(order);
                    
                    System.out.println(order.getId() + " " + order.getUser_id() + " " + order.getEmployee_id() + " "+ order.getOrder_date() + " " + order.getStatus() + " " + order.getTotal() + " "  + order.getTax() + " " + order.getDiscount());
                }
                
                connection.close();  
            } catch (Exception e) {
                System.out.println("Error fetching data from the database: " + e.getMessage());  
            }
            return orders;
        }

        /**
         * Fetches soaps and adds them to the ArrayList
         *
         * @return a list of soaps
         */
        public ArrayList<Soap> fetchSoapsFromDatabase() {
            ArrayList<Soap> soaps = new ArrayList<>();
            try {       
                Connection connection = DriverManager.getConnection(url, username, password);  
                Statement statement = connection.createStatement();  
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Soap");  
                
                while(resultSet.next()) { 
                    Soap soap = new Soap();
                    soap.setId(resultSet.getInt("id"));
                    soap.setEAN(resultSet.getInt("EAN"));
                    soap.setTitle(resultSet.getString("titel"));
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

        /**
         * Adds an employer dataset to the database table
         *
         * @param Employer employer
         */
        public void addEmployer(Employer employer) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                
                String sql = "INSERT INTO employer (employer_name, address, email, phone_number, industry, established_date) VALUES ('" 
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
            	errorMessage = "Error adding employer: " + e.getMessage();
            }
        }

        /**
         * Adds an customer dataset to the database table
         *
         * @param Customer customer
         */
        public void addCustomer(Customers customer) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                
                String sql = "INSERT INTO customers (address, email, password, name, city, birth_date, created_at) VALUES ('" 
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
            	errorMessage = "Error adding customer: " + e.getMessage();
            }
        }

        /**
         * Adds an order dataset to the database table
         *
         * @param Orders order
         */
        public void addOrder(Orders order) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                
                String sql = "INSERT INTO Orders (user_id, employer_id, order_date, status, total, tax, discount) VALUES (" 
                        + order.getUser_id() + ", " 
                        + order.getEmployee_id() + ", '" 
                        + order.getOrder_date() + "', '" 
                        + order.getStatus() + "', " 
                        + order.getTotal() + ", " 
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
            	errorMessage = "Error adding order: " + e.getMessage();
            }
        }

        /**
         * Adds an product dataset to the database table
         *
         * @param Soap soap
         */
        public void addSoap(Soap soap) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                
                String sql = "INSERT INTO Soap (EAN, titel, category, price, created_at) VALUES (" 
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
            	errorMessage = "Error adding soap: " + e.getMessage();
            }
        }
        
        /**
         * Deletes an employer dataset from the database table
         *
         * @param Employer employer
         */
        public void deleteEmployer(Employer employer) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                String sql = "DELETE FROM employer WHERE employer_id = " + employer.getEmployerId();
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Employer successfully deleted.");
                //    findMaxId();
                } else {
                    System.out.println("Error: No employer deleted.");
                }
            } catch (Exception e) {
                System.out.println("Error deleting employer: " + e.getMessage());
            }
        }
        
        /**
         * Deletes an employer dataset from the database table
         *
         * @param Customer customer
         */
        public void deleteCustomer(Customers customer) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                String sql = "DELETE FROM customers WHERE id = " + customer.getId();
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Customer successfully deleted.");
                   // findMaxId();
                } else {
                    System.out.println("Error: No customer deleted.");
                }
            } catch (Exception e) {
                System.out.println("Error deleting customer: " + e.getMessage());
            }
        }

        /**
         * Deletes an order dataset from the database table
         *
         * @param Order order
         */
        public void deleteOrder(Orders order) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                String sql = "DELETE FROM Orders WHERE id = " + order.getId();
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Order successfully deleted.");
                  //  findMaxId();
                } else {
                    System.out.println("Error: No order deleted.");
                }
            } catch (Exception e) {
                System.out.println("Error deleting order: " + e.getMessage());
            }
        }

        /**
         * Deletes an soap dataset from the database table
         *
         * @param Soap soap
         */
        public void deleteSoap(Soap soap) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                String sql = "DELETE FROM Soap WHERE id = " + soap.getId();
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Soap successfully deleted.");
                 //   findMaxId();
                } else {
                    System.out.println("Error: No soap deleted.");
                }
            } catch (Exception e) {
                System.out.println("Error deleting soap: " + e.getMessage());
            }
        }
        

        
        /**
         * Updates an dataset of the database table
         *
         * @param Employer employer
         */
        public void updateEmployer(Employer employer) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                // Create a list of SQL update statements for each column
                ArrayList<String> updates = new ArrayList<>();
                updates.add("employer_name = '" + employer.getEmployerName() + "'");
                updates.add("address = '" + employer.getAddress() + "'");
                updates.add("email = '" + employer.getEmail() + "'");
                updates.add("phone_number = '" + employer.getPhoneNumber() + "'");
                updates.add("industry = '" + employer.getIndustry() + "'");
                updates.add("established_date = '" + employer.getEstablishedDate() + "'");

                // Execute each update statement individually
                for (String update : updates) {
                    String sql = "UPDATE employer SET " + update + " WHERE employer_id = " + employer.getEmployerId();
                    try {
                        int affectedRows = statement.executeUpdate(sql);
                        if (affectedRows > 0) {
                            System.out.println("Column successfully updated: " + update);
                        } else {
                            System.out.println("Error updating column: " + update);
                        }
                    } catch (Exception e) {
                        System.out.println("Error updating column: " + update + ". Error message: " + e.getMessage());
                        errorMessage =  "Error updating Employer. Error updating column: " + update + ". Error message: " + e.getMessage();
                    }
                }

                connection.close();
            } catch (Exception e) {
                System.out.println("Error updating employer: " + e.getMessage());
            }
        }
            
         
  
        /**
         * Updates an dataset of the database table
         *
         * @param Orders order
         */
        public void updateOrder(Orders order) {

            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                ArrayList<String> updates = new ArrayList<>();
                updates.add("user_id = " + order.getUser_id());
                updates.add("employer_id = " + order.getEmployee_id());
                updates.add("order_date = '" + order.getOrder_date() + "'");
                updates.add("status = '" + order.getStatus() + "'");
                updates.add("total = " + order.getTotal());
                updates.add("tax = " + order.getTax());
                updates.add("discount = " + order.getDiscount());

                for (String update : updates) {
                    String sql = "UPDATE Orders SET " + update + " WHERE id = " + order.getId();
                    try {
                        int affectedRows = statement.executeUpdate(sql);
                        if (affectedRows > 0) {
                            System.out.println("Column successfully updated: " + update);
                        } else {
                            System.out.println("Error updating column: " + update);
                        }
                    } catch (Exception e) {
                        System.out.println("Error updating order. Error updating column: " + update + ". Error message: " + e.getMessage());
                        errorMessage =  "Error updating order. Error updating column: " + update + ". Error message: " + e.getMessage();
                        
                    }
                }
                
                connection.close();
            } catch (Exception e) {
                System.out.println("Error updating order: " + e.getMessage());
            }
            
        }
       
        /**
         * Updates an dataset of the database table
         *
         * @param Soap soap
         */
        public void updateSoap(Soap soap) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                ArrayList<String> updates = new ArrayList<>();
                updates.add("EAN = " + soap.getEAN());
                updates.add("titel = '" + soap.getTitle() + "'");
                updates.add("category = '" + soap.getCategory() + "'");
                updates.add("price = " + soap.getPrice());
                updates.add("created_at = '" + soap.getCreatedAt() + "'");

                for (String update : updates) {
                    String sql = "UPDATE Soap SET " + update + " WHERE id = " + soap.getId();
                    try {
                        int affectedRows = statement.executeUpdate(sql);
                        if (affectedRows > 0) {
                            System.out.println("Column successfully updated: " + update);
                        } else {
                            System.out.println("Error updating column: " + update);
                        }
                    } catch (Exception e) {
                        System.out.println("Error updating column: " + update + ". Error message: " + e.getMessage());
                        errorMessage =  "Error updating Soap. Error updating column: " + update + ". Error message: " + e.getMessage();
                    }
                }

                connection.close();
            } catch (Exception e) {
                System.out.println("Error updating soap: " + e.getMessage());
            }
        }

        /**
         * Updates an dataset of the database table
         *
         * @param Customers customer
         */
        public void updateCustomer(Customers customer) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                ArrayList<String> updates = new ArrayList<>();
                updates.add("address = '" + customer.getAddress() + "'");
                updates.add("email = '" + customer.getEmail() + "'");
                updates.add("password = '" + customer.getPassword() + "'");
                updates.add("name = '" + customer.getName() + "'");
                updates.add("city = '" + customer.getCity() + "'");
                updates.add("birth_date = '" + customer.getBirthDate() + "'");
                updates.add("created_at = '" + customer.getCreatedAt() + "'");

                for (String update : updates) {
                    String sql = "UPDATE customers SET " + update + " WHERE id = " + customer.getId();
                    try {
                        int affectedRows = statement.executeUpdate(sql);
                        if (affectedRows > 0) {
                            System.out.println("Column successfully updated: " + update);
                        } else {
                            System.out.println("Error updating column: " + update);          
                        }
                    } catch (Exception e) {
                        System.out.println("Error updating column: " + update + ". Error message: " + e.getMessage());
                        errorMessage =  "Error updating Customer. Error updating column: " + update + ". Error message: " + e.getMessage();
                    }
                }

                connection.close();
            } catch (Exception e) {
                System.out.println("Error updating customer: " + e.getMessage());
            }
        }
        /**
         * Fetches products associated with a specific order ID.
         *
         * @param orderId the ID of the order
         * @return a list of Soap products
         */
        public ArrayList<Soap> fetchProductsByOrderId(int orderId) {
            ArrayList<Soap> soaps = new ArrayList<>();
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                
                String sql = "SELECT s.id, s.EAN, s.titel, s.category, s.price, s.created_at " +
                             "FROM Soap AS s " +
                             "INNER JOIN RefOrderProd AS ref ON s.id = ref.Sid " +
                             "INNER JOIN Orders AS o ON o.id = ref.Oid " +
                             "WHERE o.id = " + orderId;
                
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Soap soap = new Soap();
                    soap.setId(resultSet.getInt("id"));
                    soap.setEAN(resultSet.getInt("EAN"));
                    soap.setTitle(resultSet.getString("titel"));
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
        
        /**
         * Adds a reference between an order and a soap product.
         *
         * @param orderId the ID of the order
         * @param soapId  the ID of the soap product
         */
        public void addOrderProductReference(int orderId, int soapId) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false); // Start transaction

                // Check if orderId exists in orders table
                Statement checkOrderStatement = connection.createStatement();
                ResultSet orderResult = checkOrderStatement.executeQuery("SELECT id FROM Orders WHERE id = " + orderId); //added checks to order and soap table
                if (!orderResult.next()) {
                    System.out.println("Order ID " + orderId + " does not exist in orders table.");
                    return;
                }

                // Check if soapId exists in Soap table
                Statement checkSoapStatement = connection.createStatement();
                ResultSet soapResult = checkSoapStatement.executeQuery("SELECT id FROM Soap WHERE id = " + soapId);
                if (!soapResult.next()) {
                    System.out.println("Soap ID " + soapId + " does not exist in Soap table.");
                    return;
                }

                // If both exist, insert into RefOrderProd table
                String sql = "INSERT INTO RefOrderProd (Oid, Sid) VALUES (" + orderId + ", " + soapId + ")";
                Statement insertStatement = connection.createStatement();
                int affectedRows = insertStatement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Order-Product reference successfully added.");
                } else {
                    System.out.println("Product ID: " + soapId + " Order ID: " + orderId);
                    System.out.println("Error adding Order-Product reference.");
                }

                connection.commit(); // Commit transaction
                connection.close();
            } catch (Exception e) {
                System.out.println("Product ID: " + soapId + " Order ID: " + orderId);
                System.out.println("Error adding Order-Product reference: " + e.getMessage());
            }
        }


        /**
         * Deletes the reference between an order and a soap product.
         *
         * @param orderId the ID of the order
         * @param soapId  the ID of the soap product
         */
        public void deleteOrderProductReference(int orderId, int soapId) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                // Delete from the reference table (RefOrderProd)
                String deleteRefOrderProdSQL = "DELETE FROM RefOrderProd WHERE Oid = " + orderId + " AND Sid = " + soapId;
                int rowsDeletedRefOrderProd = statement.executeUpdate(deleteRefOrderProdSQL);

                if (rowsDeletedRefOrderProd > 0) {
                    System.out.println("Deleted from RefOrderProd for Order ID: " + orderId + " and Soap ID: " + soapId);
                } else {
                    System.out.println("No records deleted from RefOrderProd for Order ID: " + orderId + " and Soap ID: " + soapId);
                }

                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error deleting order references: " + ex.getMessage());
            }
        }
    
       


        /**
         * Fetches all unique categories from the Soap table.
         *
         * @return a list of categories
         */
        public ArrayList<String> fetchCategoriesFromDatabase() {
            ArrayList<String> categories = new ArrayList<>();
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT DISTINCT category FROM Soap");

                while (resultSet.next()) {
                    categories.add(resultSet.getString("category"));
                }

                connection.close();
            } catch (Exception e) {
                System.out.println("Error fetching categories from the database: " + e.getMessage());
            }
            return categories;
        }

        /**
         * Fetches soaps by category.
         *
         * @param category the category of the soaps
         * @return a list of Soap products
         */
        public ArrayList<Soap> fetchSoapsByCategory(String category) {
            ArrayList<Soap> soaps = new ArrayList<>();
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                
                // Escape single quotes in the category to prevent SQL injection
                String escapedCategory = category.replace("'", "''");
                
                String query = "SELECT * FROM Soap WHERE category = '" + escapedCategory + "'";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Soap soap = new Soap();
                    soap.setId(resultSet.getInt("id"));
                    soap.setEAN(resultSet.getInt("EAN"));
                    soap.setTitle(resultSet.getString("titel"));
                    soap.setCategory(resultSet.getString("category"));
                    soap.setPrice(resultSet.getDouble("price"));
                    soap.setCreatedAt(resultSet.getTimestamp("created_at"));

                    soaps.add(soap);
                }

                connection.close();
            } catch (Exception e) {
                System.out.println("Error fetching soaps by category: " + e.getMessage());
            }
            return soaps;
        }
        
        /**
         * Retrieves the soap ID based on its title.
         *
         * @param title the title of the soap
         * @return the ID of the soap, or -1 if not found
         */
        public int getSoapIdByTitle(String title) {
            int soapId = -1;
            String sql = "SELECT id FROM Soap WHERE titel = ?";
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, title);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    soapId = resultSet.getInt("id");
                }
            } catch (Exception e) {
                System.out.println("Error fetching soap ID by title: " + e.getMessage());
            }
            return soapId;
        }
        
        /**
         * Retrieves the total count of a specific soap in a specific order.
         *
         * @param orderId the ID of the order
         * @param soapId  the ID of the soap
         * @return the total count of the soap in the order
         */
        public int getTotalCount(int orderId, int soapId) {
            int totalCount = 0;

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "SELECT COUNT(*) AS total_count FROM RefOrderProd WHERE Oid = ? AND Sid = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, orderId);
                statement.setInt(2, soapId);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    totalCount = resultSet.getInt("total_count");
                }
            } catch (Exception e) {
                System.out.println("Error retrieving total count: " + e.getMessage());
            }

            return totalCount;
        }


	
        


    }
      
  
      

  
      
    