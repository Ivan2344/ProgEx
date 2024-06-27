package frauas.zimmermann.prgx;

import java.sql.*;

public class Create_Shema {
	private String url;
	private String user;
	private String password;

	Create_Shema(String usr, String pwd) {
		this.url = "jdbc:mysql://localhost:3306/";
		this.user = usr;
		this.password = pwd;
	}

	boolean CreateDB() {
		boolean success = false;
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();

			// Erstelle das Schema

			String createSchemaQuery = "CREATE SCHEMA IF NOT EXISTS SEIFENdemo2";
			stmt.executeUpdate(createSchemaQuery);

			// Verwende das Schema
			String useSchemaQuery = "USE SEIFENdemo2";
			stmt.executeUpdate(useSchemaQuery);

			// Erstelle die Tabellen
			String createSeifenQuery = "CREATE TABLE IF NOT EXISTS Soap (id INT AUTO_INCREMENT PRIMARY KEY, EAN INT, titel VARCHAR(255), category VARCHAR(50), price DOUBLE, created_at TIMESTAMP)";
			String createKundeQuery = "CREATE TABLE IF NOT EXISTS customers (id INT AUTO_INCREMENT PRIMARY KEY, address VARCHAR(255), email VARCHAR(100), password VARCHAR(50), name VARCHAR(50), city VARCHAR(50), birth_date DATE, created_at TIMESTAMP)";
			String createEmployer = "CREATE TABLE IF NOT EXISTS employer (employer_id INT AUTO_INCREMENT PRIMARY KEY, employer_name VARCHAR(100), address VARCHAR(255), email VARCHAR(100), phone_number VARCHAR(20), industry VARCHAR(50), established_date DATE)";
			String createOrderQuery = "CREATE TABLE IF NOT EXISTS Orders (id INT AUTO_INCREMENT PRIMARY KEY, user_id INT,employer_id INT, order_date DATE, status VARCHAR(50), total FLOAT, tax INT, "
					+ "discount INT ,FOREIGN KEY (user_id) REFERENCES customers(id) ON DELETE CASCADE,"
					+ "FOREIGN KEY(employer_id) REFERENCES employer(employer_id) ON DELETE CASCADE)";

			String createOrderProd = "CREATE TABLE IF NOT EXISTS RefOrderProd(id INT AUTO_INCREMENT Primary KEY, Oid INT, Sid INT," 
					+"FOREIGN KEY (Oid) REFERENCES Orders(id) "
					+ "ON DELETE CASCADE,"
					+ "FOREIGN KEY (Sid) REFERENCES Soap(id)"
					+ "ON DELETE CASCADE)";
			String createOrdEmp = "CREATE TABLE IF NOT EXISTS RefOrdEmp(id INT AUTO_INCREMENT Primary KEY, Oid INT, Eid INT,"+
					"FOREIGN KEY (Oid) REFERENCES Orders(id) "
					+ "ON DELETE CASCADE,"
					+ "FOREIGN KEY (Eid) REFERENCES employer(employer_id)"
					+ "ON DELETE CASCADE)";;
			
			
			stmt.executeUpdate(createSeifenQuery);
			stmt.executeUpdate(createKundeQuery);
			stmt.executeUpdate(createEmployer);

			stmt.executeUpdate(createOrderQuery);
			stmt.executeUpdate(createOrderProd);
			stmt.executeUpdate(createOrdEmp);
			System.out.println("Schema und Tabellen erfolgreich erstellt.");
			
			success = true;

			conn.close();
		} catch (Exception e) {
			System.err.println("Fehler beim Erstellen des Schemas und der Tabellen: " + e.getMessage());
		}
		
		return success;
	}

	void InsertDemoValues() {

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "INSERT INTO Orders (id, user_id, employer_id, order_date, status, total, tax, discount) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, 1);
			statement.setInt(3, 1);
			statement.setString(4, "2022-01-01");
			statement.setString(5, "Deliverd");
			statement.setDouble(6, 100.00);
			statement.setDouble(7, 90.00);
			statement.setDouble(8, 5.00);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Ein neuer Datensatz wurde erfolgreich hinzugefügt (Employer)!");
			}
		} catch (SQLException e) {
			System.out.println("Fehler beim Hinzufügen des Datensatzes: " + e.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "INSERT INTO Soap (id, EAN, titel, category, price, created_at) VALUES (?, ?, ?, ?, ?, NOW())";
			PreparedStatement statement = conn.prepareStatement(sql);

			// Datensatz 1
			statement.setInt(1, 1);
			statement.setString(2, "1234");
			statement.setString(3, "SeifeSauber");
			statement.setString(4, "Seife");
			statement.setDouble(5, 5.99);
			statement.executeUpdate();

			// Datensatz 2
			statement.setInt(1, 2);
			statement.setString(2, "2345");
			statement.setString(3, "Shampoo");
			statement.setString(4, "Dusche");
			statement.setDouble(5, 6.99);
			statement.executeUpdate();

			// Datensatz 3
			statement.setInt(1, 3);
			statement.setString(2, "3456");
			statement.setString(3, "Spueli");
			statement.setString(4, "Other");
			statement.setDouble(5, 7.99);
			statement.executeUpdate();

			System.out.println("Drei neue Datensätze wurden erfolgreich hinzugefügt!");
		} catch (SQLException e) {
			System.out.println("Fehler beim Hinzufügen der Datensätze: " + e.getMessage());
		}

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "INSERT INTO customers (id, address, email, password, name, city, birth_date, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
			PreparedStatement statement = conn.prepareStatement(sql);

			// Datensatz 1
			statement.setInt(1, 1);
			statement.setString(2, "Musterstraße 1");
			statement.setString(3, "kunde1@example.com");
			statement.setString(4, "passwort1");
			statement.setString(5, "Kunde 1");
			statement.setString(6, "Stadt 1");
			statement.setString(7, "1990-01-01");
			statement.executeUpdate();

			// Datensatz 2
			statement.setInt(1, 2);
			statement.setString(2, "Beispielweg 2");
			statement.setString(3, "kunde2@example.com");
			statement.setString(4, "passwort2");
			statement.setString(5, "Kunde 2");
			statement.setString(6, "Stadt 2");
			statement.setString(7, "1995-02-02");
			statement.executeUpdate();

			// Datensatz 3
			statement.setInt(1, 3);
			statement.setString(2, "Testgasse 3");
			statement.setString(3, "kunde3@example.com");
			statement.setString(4, "passwort3");
			statement.setString(5, "Kunde 3");
			statement.setString(6, "Stadt 3");
			statement.setString(7, "2000-03-03");
			statement.executeUpdate();

			System.out.println("Drei neue Datensätze wurden erfolgreich hinzugefügt!");
		} catch (SQLException e) {
			System.out.println("Fehler beim Hinzufügen der Datensätze: " + e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "INSERT INTO employer (employer_id, employer_name, address, email, phone_number, industry, established_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			// Datensatz
			statement.setInt(1, 1);
			statement.setString(2, "Musterfirma");
			statement.setString(3, "Musterstraße 1");
			statement.setString(4, "firma@example.com");
			statement.setString(5, "1234567890");
			statement.setString(6, "Industrie XYZ");
			statement.setString(7, "2009-01-01");
			statement.executeUpdate();

			System.out.println("Ein neuer Datensatz wurde erfolgreich hinzugefügt!");
		} catch (SQLException e) {
			System.out.println("Fehler beim Hinzufügen des Datensatzes: " + e.getMessage());
		}

		// referenytabellen
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Verbindung zur Datenbank hergestellt!");

			String insertQuery = "INSERT INTO RefOrderProd (id, Oid, Sid) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, 1); // id
			preparedStatement.setInt(2, 1); // Wert für Oid
			preparedStatement.setInt(3, 1); // Wert für Sid

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Datensatz erfolgreich eingefügt!");
			} else {
				System.out.println("Fehler beim Einfügen des Datensatzes.");
			}

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Fehler beim Verbindungsaufbau zur Datenbank oder beim Einfügen des Datensatzes: "
					+ e.getMessage());
		}

		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Verbindung zur Datenbank hergestellt!");

			String insertQuery = "INSERT INTO RefOrdEmp (id, Oid, Eid) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, 1); // Wert für id
			preparedStatement.setInt(2, 1); // Wert für Oid
			preparedStatement.setInt(3, 1); // Wert für Eid

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Datensatz erfolgreich eingefügt!");
			} else {
				System.out.println("Fehler beim Einfügen des Datensatzes.");
			}

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Fehler beim Verbindungsaufbau zur Datenbank oder beim Einfügen des Datensatzes: "
					+ e.getMessage());
		}
	}

	void setSch(String sh) {
		this.url = sh;
	}

	void createForeignKeys() {
		try (Connection connection = DriverManager.getConnection(url, user, password);
				Statement statement = connection.createStatement()) {

			// Referenz für die Order zu produkt
			String sql1 = "ALTER TABLE RefOrderProd " + "ADD CONSTRAINT fk_Sid_soap " + "FOREIGN KEY (Sid) "
					+ "REFERENCES soap(id)";

			String sql2 = "ALTER TABLE RefOrderProd " + "ADD CONSTRAINT fk_Oid_Orders " + "FOREIGN KEY (Oid) "
					+ "REFERENCES Orders(id)";
			// Referenz user id zu customers
			String sql3 = "ALTER TABLE Orders" + "ADD CONSTRAINT fk_user_id" + "FOREIGN KEY (user_id)"
					+ "REFERENCES customers(id)";
			// Referenz für Order Mitarbeiter
			String sql4 = "ALTER TABLE RefOrdEmp" + "ADD CONSTRAINT fk_Oid" + "FOREIGN KEY (Oid)"
					+ "REFERENCES Orders(id)";

			String sql5 = "ALTER TABLE Orders" + "ADD CONSTRAINT fk_Eid" + "FOREIGN KEY (Eid)"
					+ "REFERENCES employer(employer_id)";

			statement.executeUpdate(sql1);
			statement.executeUpdate(sql2);
			statement.executeUpdate(sql3);
			statement.executeUpdate(sql4);
			statement.executeUpdate(sql5);
			System.out.println("Fremdschlüssel erfolgreich erstellt.");

		} catch (SQLException e) {
			System.err.println("Fehler beim Erstellen der Fremdschlüssel: " + e.getMessage());
		}
	}
}