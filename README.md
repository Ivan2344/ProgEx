# 🧼 Soap Shop Manager

> University project | Frankfurt University of Applied Sciences | 2024

A desktop inventory and order management application for a soap shop, built with **Java Swing** and a **MySQL** backend. The application allows shop staff to manage products, customers, employees and orders through a graphical user interface.

---

## 📋 Features

- **Login screen** – authenticate with MySQL credentials before accessing the app
- **Product management** – add, view and manage soap articles (EAN, title, category, price)
- **Customer management** – store and manage customer data (name, address, email, birth date)
- **Employee management** – manage employee/supplier records
- **Order management** – create and track orders with status, tax, discount and totals
- **Order-Product linking** – reference table connecting orders to individual soap products
- **Drag & drop** – drag items from a tree view into order lists via `JTree` and `JList`
- **Auto schema creation** – automatically sets up the MySQL database and all tables on first run
- **Logging** – user navigation and app events are written to `LogFile.log`

---

## 🛠️ Tech Stack

| Layer       | Technology                        |
|-------------|-----------------------------------|
| GUI         | Java Swing (`JFrame`, `JTable`, `JTree`, `JList`, `CardLayout`) |
| Backend     | Java 17+                          |
| Database    | MySQL 8                           |
| DB Driver   | MySQL Connector/J 8.4.0 (JDBC)    |
| IDE         | Eclipse                           |
| Logging     | Java `java.util.logging`          |

---

## 🗄️ Database Schema

The app automatically creates the schema `SEIFENdemo2` with the following tables:

```
Soap          – products (id, EAN, title, category, price, created_at)
customers     – customer accounts (id, name, address, email, password, city, birth_date)
employer      – employees/suppliers (id, name, address, email, phone, industry, established_date)
Orders        – orders (id, user_id, employer_id, date, status, total, tax, discount)
RefOrderProd  – order-product reference table (order_id → soap_id, quantity)
```

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- MySQL 8 running locally on port `3306`
- Eclipse IDE (or any Java IDE)

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/[your-username]/ProgEx.git
   ```

2. **Open in Eclipse**
   - `File → Import → Existing Projects into Workspace`
   - Select the `ProgExTry1` folder

3. **Add the MySQL connector**
   - The JAR `mysql-connector-j-8.4.0.jar` is already included in `src/`
   - Make sure it is on the build path (`Right-click project → Build Path → Configure Build Path`)

4. **Run the application**
   - Run `MainProg.java` as a Java Application
   - A login window appears – enter your **MySQL username and password**
   - The app will automatically create the database schema on first login

---

## 📁 Project Structure

```
ProgExTry1/src/frauas/zimmermann/prgx/
│
├── MainProg.java           # Entry point – launches the login window
├── Mainframe.java          # Base JFrame with header/footer/layout setup
├── LoginMask.java          # Login screen (username + MySQL password)
├── GUI.java                # Main application window with all panels (CardLayout navigation)
├── MiddlePanel.java        # Interface defining reusable input mask panels
├── OrderFrame.java         # Order management view with JTree navigation
│
├── Create_Shema.java       # Creates the MySQL schema and all tables on first run
├── Data_management.java    # All CRUD operations via JDBC
│
├── Soap.java               # Model class – soap/product entity
├── Customers.java          # Model class – customer entity
├── Employer.java           # Model class – employer/supplier entity
├── Orders.java             # Model class – order entity
│
├── TreeTransferHandler.java # Drag & drop handler (JTree → JList)
├── Log.java                # Logging utility (writes to LogFile.log)
└── Login.java              # (Placeholder class)
```

## 📝 Notes

- Passwords are stored in plain text in the database – this is a university demo project, not intended for production use
- The database URL is hardcoded to `localhost:3306` – adjust in `Data_management.java` and `Create_Shema.java` if needed
- Log output is written to `LogFile.log` in the project root directory

