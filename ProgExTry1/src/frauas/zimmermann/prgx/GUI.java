package frauas.zimmermann.prgx;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.table.DefaultTableModel;

public class GUI extends Mainframe implements MiddlePanel{
    JPanel[] panelList;
    String[] menuItems = {"Orders", "Products", "Employees", "Customers"};
    JPanel leftPanel, rightPanel ,secondLeftPanel, firstLeftPanel;
    private DefaultTableModel employerTableModel;
    public Data_management dataManagement;
    static public int selectedIndex = -1;
    private int selectedRow = -1;
    private JButton addButton , deleteButton , editButton,refreshButton ;
    private JTextField soapIdTfd , soapEANTfd ,soapTitleTfd ,soapCategoryTfd , soapPriceTfd , soapCreatedTfd ;
    private JTextField employerIdTfd ,employerNameTfd , employerAddressTfd ,employerEmailTfd ,phoneNumberTfd ,industryTfd , establishedTfd;
    private JTextField customerIdTfd, customerNameTfd , customerAddressTfd, customerEmailTfd , customerPasswordTfd ,customerCityTfd , customerBirthdayTfd , customerCreatedAtTfd;
    private JTextField ordersIdTfd ,ordersUserTfd , ordersDateTfd, ordersStatusTfd , ordersTotalTfd , ordersSubtotalTfd , ordersTaxTfd ,ordersDiscountTfd ;
    private JTable soapDatabase, employerDatabase, orderDatabase , customerDatabase;

    public GUI(String usr, String psw) {
        super();
        dataManagement = new Data_management(usr, psw);
        createMainPanel();
        createMenuList(menuItems);
        northCenterPanel.revalidate();
        northCenterPanel.repaint();
        
    }

    //create the menulist
    
    public void createMenuList(String[] items) {
        JList<String> menuList = new JList<>(items);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        menuList.setVisibleRowCount(1); // Show all items
        menuList.setFont(new java.awt.Font("Book Antiqua", 0, 18));
        menuList.setBackground(Color.DARK_GRAY);
        menuList.setForeground(LIGHT_BLUE);

        int cellWidth = northCenterPanel.getPreferredSize().width / items.length;
        menuList.setFixedCellWidth(cellWidth);
        menuList.setFixedCellHeight(50);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) menuList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        menuList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedIndex = menuList.getSelectedIndex();
                    for (int i = 0; i < panelList.length; i++) {
                        if (i == selectedIndex) {
                            panelList[i].setVisible(true);
//                            System.out.print("item click is recognized\n");
//                            System.out.print(i);
//                            System.out.print("\n");
//                            String selectedItem = menuItems[selectedIndex];
//                            setViewPanel(selectedItem);
      
                        } else {
                            panelList[i].setVisible(false);
//                            System.out.print("item click is recognized empty\n");
                        }
                        
                    }
                    innerCenterPanel.revalidate();
                    innerCenterPanel.repaint();
                }
            }
        });


        northCenterPanel.add(menuList);
    }


    public void createMainPanel() {
        panelList = new JPanel[menuItems.length];
        innerCenterPanel.setLayout(new CardLayout());
        
        for (int i = 0; i < menuItems.length; i++) {
        	
            panelList[i] = setMainPanels(i);
//            panelList[i] = setContents(i); 
            panelList[i].setVisible(true);
            innerCenterPanel.add(panelList[i]);
        }
        
        frame.add(center, BorderLayout.CENTER);
    }

    public JPanel createPanel() {
    	JPanel tempPanel = new JPanel();
    	tempPanel.setLayout(new BorderLayout());
    	tempPanel = createPanelWithBorder(BUTTON_PANEL);
    	return tempPanel;
    }
    
    public JPanel setMainPanels(int menuItem) {
        JPanel tempPanel = new JPanel();             
        JPanel addButtonPanel = new JPanel();
        JPanel editButtonPanel = new JPanel();  
        JPanel deleteButtonPanel = new JPanel();     
        JPanel refreshButtonPanel = new JPanel();
        
                
        tempPanel.setBackground(Color.WHITE);
        tempPanel.setLayout(new GridLayout(1, 2));
		
        
        leftPanel = createPanelWithBorder(LEFT_PANEL);
        rightPanel = createPanelWithBorder(RIGHT_PANEL);        
        firstLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
		secondLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
		firstLeftPanel.setLayout(new GridLayout(4, 1));		
		
		
		addButtonPanel= createPanel();
		editButtonPanel= createPanel();
		deleteButtonPanel= createPanel();
		refreshButtonPanel= createPanel();
		
		
		 addButton = new JButton("Add");
		 addButton = setButtons(addButton);
		 deleteButton = new JButton("Delete");
		 deleteButton = setButtons(deleteButton);
		 editButton = new JButton("Edit");
		 editButton = setButtons(editButton);
		 refreshButton = new JButton("Refresh");
		 refreshButton = setButtons(refreshButton);
		 
		 setActionAddButton(menuItem);
		 setActionDeleteButton(menuItem);
		 setActionEditButton(menuItem);
		 setActionRefreshButton(menuItem);
		 
		 
		 addButtonPanel.add(addButton);
		 deleteButtonPanel.add(deleteButton);
		 editButtonPanel.add(editButton);
		 refreshButtonPanel.add(refreshButton);
    	
		 
		firstLeftPanel.add(addButtonPanel);
		firstLeftPanel.add(deleteButtonPanel);
		firstLeftPanel.add(editButtonPanel);
		firstLeftPanel.add(refreshButtonPanel);
		
       // JLabel secondLabel = new JLabel("Second Left Panel", SwingConstants.CENTER);
		secondLeftPanel.setLayout(new BorderLayout());
		
        JLabel rightLabel = new JLabel("Right Panel", SwingConstants.CENTER);        
        rightPanel.add(rightLabel);
        
        //code eingefügt bzw gui fix
        String selectedItem = menuItems[menuItem];
        setViewPanel(selectedItem);
        
        leftPanel.add(firstLeftPanel);
        leftPanel.add(secondLeftPanel);
        tempPanel.add(leftPanel);
        tempPanel.add(rightPanel);
        
//        setContents(menuItem);
        
        return tempPanel;
    }
    
    public JButton setButtons(JButton button) {
    	button.setFont(new java.awt.Font("Book Antiqua", 0, 18));
		button.setBackground(LIGHT_BLUE);
		button.setForeground(Color.DARK_GRAY);
		return button;   	
    }
    
   
    public void setViewPanel(String viewName) {
        switch (viewName) {
            case "Employees":
                setEmployerPanel();
                setEmployerTextPanel();
//                secondLeftPanel.add(setEmployeeMask());
                break;
            case "Customers":
                setCustomerPanel();
                setCustomerTextPanel();
//               secondLeftPanel.add(setCustomerMask());
                break;
            case "Orders":
                setOrderPanel();
                setOrderTextPanel();
//                secondLeftPanel.add(setOrderMask());
                break;
            case "Products":
                setSoapPanel();
                setSoapTextPanel();
//                secondLeftPanel.add(setSoapMask());
                break;
            default:
                // Handle unknown view
                break;       
        }
        //firstLeftPanel.add(setEditButtons);
    }
    protected JPanel createPanelWithBorder(int panelType) {
        JPanel panel = new JPanel();
        Color myColor = new Color(230, 230, 255);
        panel.setBackground(myColor);

        int topBorderWidth, leftBorderWidth, bottomBorderWidth, rightBorderWidth;

        switch (panelType) {
            case RIGHT_PANEL:
                topBorderWidth = 100;
                leftBorderWidth = 20;
                bottomBorderWidth = 100;
                rightBorderWidth = 20;
                break;
            case INNER_LEFT_PANEL:
                topBorderWidth = 0;
                leftBorderWidth = 10;
                bottomBorderWidth = 100;
                rightBorderWidth = 10;
                break;
            case BUTTON_PANEL:
            	topBorderWidth = 0;
                leftBorderWidth = 10;
                bottomBorderWidth = 10;
                rightBorderWidth = 10;
                break;
            default: // LEFT_PANEL
                topBorderWidth = 100;
                leftBorderWidth = 10;
                bottomBorderWidth = 0;
                rightBorderWidth = 10;
                break;
        }

        panel.setBorder(BorderFactory.createMatteBorder(
                topBorderWidth, leftBorderWidth, bottomBorderWidth, rightBorderWidth, BORDER_COLOR));
        panel.setLayout(new GridLayout(1, 2));
        return panel;
    }
    
    
    private void setEmployerPanel() {
        rightPanel.removeAll();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);
        
        JPanel employerPanel = new JPanel();
        employerPanel.setLayout(new BorderLayout());
        employerPanel.setPreferredSize(new Dimension(200, 200));

        // Set upper panel
        employerPanel.add(setUpper("Employer Database"), BorderLayout.NORTH);

        setLeft(employerPanel);
        setRight(employerPanel);

        employerTableModel = new DefaultTableModel();
        employerTableModel.addColumn("ID");
        employerTableModel.addColumn("Name");
        employerTableModel.addColumn("Address");
        employerTableModel.addColumn("Email");
        employerTableModel.addColumn("Phone Number");
        employerTableModel.addColumn("Industry");
        employerTableModel.addColumn("Established Date");

        employerDatabase = new JTable(employerTableModel); /*{
        	 @Override
             public boolean isCellEditable(int row, int column) {
                 return false; // Disable cell editing
             }
         };*/
        
        employerDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedRow = employerDatabase.getSelectedRow();
                if (selectedRow != -1) {
                    handleSelectedRow(selectedRow, employerDatabase, "Employer");
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(employerDatabase);
        employerPanel.add(scrollPane, BorderLayout.CENTER);

        addEmployerData(); // Daten aus der Datenbank hinzufügen

        rightPanel.add(employerPanel, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }
    
    private void addEmployerData() {
        ArrayList<Employer> employers = dataManagement.fetchEmployersFromDatabase();

        for (Employer employer : employers) {
            Object[] rowData = {
                employer.getEmployerId(),
                employer.getEmployerName(),
                employer.getAddress(),
                employer.getEmail(),
                employer.getPhoneNumber(),
                employer.getIndustry(),
                employer.getEstablishedDate()
            };
            employerTableModel.addRow(rowData);
        }
    }
    
    public void setEmployerTextPanel(){
    	JPanel tempPanel = new JPanel();
    	JLabel tempPanel1 = new JLabel("ID");
    	JLabel tempPanel2 = new JLabel("Name");
    	JLabel tempPanel3 = new JLabel("Adresse");
    	JLabel tempPanel4 = new JLabel("Email");
    	JLabel tempPanel5 = new JLabel("PhoneNumber");
    	JLabel tempPanel6 = new JLabel("Industry");
    	JLabel tempPanel7 = new JLabel("EstablishedDate");
    	
    	tempPanel.setLayout(new GridLayout(7,2));
    	 employerIdTfd = new JTextField();
    	 employerNameTfd = new JTextField();
    	 employerAddressTfd = new JTextField();
    	 employerEmailTfd = new JTextField();
    	 phoneNumberTfd = new JTextField();
    	 industryTfd = new JTextField();
    	 establishedTfd = new JTextField();

    	
    	tempPanel.add(tempPanel1);
    	tempPanel.add(employerIdTfd);
    	tempPanel.add(tempPanel2);
    	tempPanel.add(employerNameTfd);
    	tempPanel.add(tempPanel3);
    	tempPanel.add(employerAddressTfd);
    	tempPanel.add(tempPanel4);
    	tempPanel.add(employerEmailTfd);
    	tempPanel.add(tempPanel5);
    	tempPanel.add(phoneNumberTfd);
    	tempPanel.add(tempPanel6);
    	tempPanel.add(industryTfd);
    	tempPanel.add(tempPanel7);
    	tempPanel.add(establishedTfd);
    	
    	secondLeftPanel.add(tempPanel);
    	
    	
    }
    
    private void setSoapPanel() {
        rightPanel.removeAll();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);

        JPanel soapPanel = new JPanel();
        soapPanel.setLayout(new BorderLayout());
        soapPanel.setPreferredSize(new Dimension(300, 300));

        // Set upper panel
        soapPanel.add(setUpper("Product Database"), BorderLayout.NORTH);

        setLeft(soapPanel);
        setRight(soapPanel);

        DefaultTableModel soapTableModel = new DefaultTableModel();
        soapTableModel.addColumn("ID");
        soapTableModel.addColumn("EAN");
        soapTableModel.addColumn("Title");
        soapTableModel.addColumn("Category");
        soapTableModel.addColumn("Price");
        soapTableModel.addColumn("Created At");

        soapDatabase = new JTable(soapTableModel); /*{
        	 @Override
             public boolean isCellEditable(int row, int column) {
                 return false; // Disable cell editing
             }
         };*/
        
        soapDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedRow = soapDatabase.getSelectedRow();
            	System.out.print(selectedRow);
                if (selectedRow != -1) {
                    handleSelectedRow(selectedRow, soapDatabase, "Soap");
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(soapDatabase);
        soapPanel.add(scrollPane, BorderLayout.CENTER);

        addSoapData(soapTableModel); // Add data from the database

        rightPanel.add(soapPanel, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void addSoapData(DefaultTableModel soapTableModel) {
        ArrayList<Soap> soaps = dataManagement.fetchSoapsFromDatabase();

        for (Soap soap : soaps) {
            Object[] rowData = {
                soap.getId(),
                soap.getEAN(),
                soap.getTitle(),
                soap.getCategory(),
                soap.getPrice(),
                soap.getCreatedAt()
            };
            soapTableModel.addRow(rowData);
        }
    }
    
    public void setSoapTextPanel(){
    	
    	JPanel tempPanel = new JPanel();
    	JLabel tempPanel1 = new JLabel("ID");
    	JLabel tempPanel2 = new JLabel("EAN");
    	JLabel tempPanel3 = new JLabel("Title");
    	JLabel tempPanel4 = new JLabel("Category");
    	JLabel tempPanel5 = new JLabel("Price");
//    	JLabel tempPanel6 = new JLabel("CreatedAt");
    	
    	tempPanel.setLayout(new GridLayout(5,2));
    	 soapIdTfd = new JTextField();
    	 soapEANTfd = new JTextField();
    	 soapTitleTfd = new JTextField();
    	 soapCategoryTfd = new JTextField();
    	 soapPriceTfd = new JTextField();
//    	 soapCreatedTfd = new JTextField();

    	
    	tempPanel.add(tempPanel1);
    	tempPanel.add(soapIdTfd);
    	tempPanel.add(tempPanel2);
    	tempPanel.add(soapEANTfd);
    	tempPanel.add(tempPanel3);
    	tempPanel.add(soapTitleTfd);
    	tempPanel.add(tempPanel4);
    	tempPanel.add(soapCategoryTfd);
    	tempPanel.add(tempPanel5);
    	tempPanel.add(soapPriceTfd);
//    	tempPanel.add(tempPanel6);
//    	tempPanel.add(soapCreatedTfd);

    	
    	secondLeftPanel.add(tempPanel);
    	
    	
    	
    	
    }
    
    
    private void setOrderPanel() {
        rightPanel.removeAll();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setPreferredSize(new Dimension(300, 300));

        // Set upper panel
        orderPanel.add(setUpper("Order Database"), BorderLayout.NORTH);

        setLeft(orderPanel);
        setRight(orderPanel);

        DefaultTableModel orderTableModel = new DefaultTableModel();
        orderTableModel.addColumn("ID");;
        orderTableModel.addColumn("User ID");
        orderTableModel.addColumn("Order Date");
        orderTableModel.addColumn("Status");
        orderTableModel.addColumn("Total");
        orderTableModel.addColumn("Subtotal");
        orderTableModel.addColumn("Tax");
        orderTableModel.addColumn("Discount");

        orderDatabase = new JTable(orderTableModel); /*{
        	 @Override
             public boolean isCellEditable(int row, int column) {
                 return false; // Disable cell editing
             }
         };*/
        
        orderDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedRow = orderDatabase.getSelectedRow();
                if (selectedRow != -1) {
                    handleSelectedRow(selectedRow, orderDatabase, "Order");
                    System.out.print("selectedrow: " + selectedRow);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(orderDatabase);
        orderPanel.add(scrollPane, BorderLayout.CENTER);

        addOrderData(orderTableModel); // Add data from the database

        rightPanel.add(orderPanel, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }
    

    private void addOrderData(DefaultTableModel orderTableModel) {
        ArrayList<Orders> orders = dataManagement.fetchOrdersFromDatabase();

        	
        for (Orders order : orders) {
            Object[] rowData = {
                order.getId(),
                order.getUser_id(),
                order.getOrder_date(),
                order.getStatus(),
                order.getTotal(),
                order.getSubtotal(),
                order.getTax(),
                order.getDiscount()
            };
            orderTableModel.addRow(rowData);
        }

    }
    
    public void setOrderTextPanel(){
    	
    	JPanel tempPanel = new JPanel();
    	JLabel tempPanel1 = new JLabel("ID");
    	JLabel tempPanel2 = new JLabel("User ID");
    	JLabel tempPanel3 = new JLabel("Order date");
    	JLabel tempPanel4 = new JLabel("Status");
    	JLabel tempPanel5 = new JLabel("Total");
    	JLabel tempPanel6 = new JLabel("Subtotal");
    	JLabel tempPanel7 = new JLabel("Tax");
    	JLabel tempPanel8 = new JLabel("Discount");
    	
    	tempPanel.setLayout(new GridLayout(8,2));
    	 ordersIdTfd = new JTextField();
    	 ordersUserTfd = new JTextField(12); //ÄNDERn!!!!!!!!!!!!
    	 ordersDateTfd = new JTextField();
    	 ordersStatusTfd = new JTextField();
    	 ordersTotalTfd = new JTextField();
    	 ordersSubtotalTfd = new JTextField();
    	 ordersTaxTfd = new JTextField();
    	 ordersDiscountTfd = new JTextField();
    	
    	tempPanel.add(tempPanel1);
    	tempPanel.add(ordersIdTfd);
    	tempPanel.add(tempPanel2);
    	tempPanel.add(ordersUserTfd);
    	tempPanel.add(tempPanel3);
    	tempPanel.add(ordersDateTfd);
    	tempPanel.add(tempPanel4);
    	tempPanel.add(ordersStatusTfd);
    	tempPanel.add(tempPanel5);
    	tempPanel.add(ordersTotalTfd);
    	tempPanel.add(tempPanel6);
    	tempPanel.add(ordersSubtotalTfd);
    	tempPanel.add(tempPanel7);
    	tempPanel.add(ordersTaxTfd);
    	tempPanel.add(tempPanel8);
    	tempPanel.add(ordersDiscountTfd);
    	
    	secondLeftPanel.add(tempPanel);
    	
    	
    }
    
    private void setCustomerPanel() {
        rightPanel.removeAll();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);

        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new BorderLayout());
        customerPanel.setPreferredSize(new Dimension(300, 300));

        // Set upper panel
        customerPanel.add(setUpper("Customer Database"), BorderLayout.NORTH);

        setLeft(customerPanel);
        setRight(customerPanel);

        DefaultTableModel customerTableModel = new DefaultTableModel();
        customerTableModel.addColumn("ID");
        customerTableModel.addColumn("Name");
        customerTableModel.addColumn("Address");
        customerTableModel.addColumn("Email");
        customerTableModel.addColumn("Password");
        customerTableModel.addColumn("City");
        customerTableModel.addColumn("Birth Date");
        customerTableModel.addColumn("Created At");

        //customerDatabase = new JTable(customerTableModel);
        customerDatabase = new JTable(customerTableModel); /*{
            // Override isCellEditable method to make all cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };*/
        
        customerDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedRow = customerDatabase.getSelectedRow();
                if (selectedRow != -1) {
                	System.out.printf("KLICK!");
                	System.out.print(selectedRow);
                    handleSelectedRow(selectedRow, customerDatabase, "Customer");
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(customerDatabase);
        customerPanel.add(scrollPane, BorderLayout.CENTER);

        addCustomerData(customerTableModel); // Add data from the database

        rightPanel.add(customerPanel, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void addCustomerData(DefaultTableModel customerTableModel) {
        ArrayList<Customers> customers = dataManagement.fetchCustomersFromDatabase();

        for (Customers customer : customers) {
            Object[] rowData = {
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getCity(),
                customer.getBirthDate(),
                customer.getCreatedAt()
            };
            customerTableModel.addRow(rowData);
        }
    }
    public void setCustomerTextPanel(){
    	JPanel tempPanel = new JPanel();
    	JLabel tempPanel1 = new JLabel("ID");
    	tempPanel1.setHorizontalAlignment(SwingConstants.CENTER);
    	JLabel tempPanel2 = new JLabel("Name");
    	JLabel tempPanel3 = new JLabel("Adresse");
    	JLabel tempPanel4 = new JLabel("Email");
    	JLabel tempPanel5 = new JLabel("Passwort");
    	JLabel tempPanel6 = new JLabel("City");
    	JLabel tempPanel7 = new JLabel("Birthday");
    	
    	tempPanel.setLayout(new GridLayout(7,2));
    	 customerIdTfd = new JTextField();
    	 customerNameTfd = new JTextField();
    	 customerAddressTfd = new JTextField();
    	 customerEmailTfd = new JTextField();
    	 customerPasswordTfd = new JTextField();
    	 customerCityTfd = new JTextField();
    	 customerBirthdayTfd = new JTextField();
    	
    	tempPanel.add(tempPanel1);
    	tempPanel.add(customerIdTfd);
    	tempPanel.add(tempPanel2);
    	tempPanel.add(customerNameTfd);
    	tempPanel.add(tempPanel3);
    	tempPanel.add(customerAddressTfd);
    	tempPanel.add(tempPanel4);
    	tempPanel.add(customerEmailTfd);
    	tempPanel.add(tempPanel5);
    	tempPanel.add(customerPasswordTfd);
    	tempPanel.add(tempPanel6);
    	tempPanel.add(customerCityTfd);
    	tempPanel.add(tempPanel7);
    	tempPanel.add(customerBirthdayTfd);
    	
    	
    	secondLeftPanel.add(tempPanel);
    	
    	
    }

    private JPanel setUpper(String header) {
        JPanel upper = new JPanel();
        upper.setBackground(Color.LIGHT_GRAY);
        upper.setPreferredSize(new Dimension(0, 30));
        upper.add(createLabel(header));
        return upper;
    }

    private void setLeft(JPanel Panel) {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setPreferredSize(new Dimension(40, 0)); // Breite einstellen
        Panel.add(leftPanel, BorderLayout.WEST);
    }

    private void setRight(JPanel Panel) {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setPreferredSize(new Dimension(40, 0)); // Breite einstellen
        Panel.add(rightPanel, BorderLayout.EAST);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Georgia", Font.PLAIN, 20));
        return label;
    }

    private void handleSelectedRow(int row, JTable table, String tableName) {
        String selectedData = "";
//        switch (tableName) {
//            case "Employer":
//                selectedData = "Selected " + tableName + ": " + table.getValueAt(row, 1);
//                break;
//            case "Customer":
//                selectedData = "Selected " + tableName + ": " + table.getValueAt(row, 1);
//                break;
//            case "Soap":
//                selectedData = "Selected " + tableName + ": " + table.getValueAt(row, 1);
//                break;
//            case "Order":
//                selectedData = "Selected " + tableName + ": " + table.getValueAt(row, 1);
//                break;
//            default:
//                break;
//        }
//        JOptionPane.showMessageDialog(null, selectedData);
    }
    public void readTextfield(int menuItem) {
    	
    	
        switch (menuItem) {
            case 0:
            	Orders order = new Orders();
//            	order.setId(Integer.parseInt(ordersIdTfd.getText()));
//            	String ordersIdText = ordersIdTfd.getText();            	
            	order.setUser_id(Integer.parseInt(ordersUserTfd.getText()));            	
            	order.setOrder_date(convertToDate(ordersDateTfd.getText()));           	            
                order.setStatus(ordersStatusTfd.getText());                
                order.setTotal(Integer.parseInt(ordersTotalTfd.getText()));               
                order.setSubtotal(Integer.parseInt(ordersSubtotalTfd.getText()));                
                order.setTax(Integer.parseInt(ordersTaxTfd.getText()));               
                order.setDiscount(Integer.parseInt(ordersDiscountTfd.getText()));
                
                System.out.println("Orders: " + order.getId() + ", " + order.getUser_id() + ", " + order.getOrder_date() + ", " + order.getStatus() + ", " + order.getTotal() + ", " + order.getSubtotal() + ", " + order.getTax() + ", " + order.getDiscount());
                
                dataManagement.addOrder(order);
                
                
                
                break;
                
            case 1:
            	Soap soap = new Soap();

            	soap.setEAN(Integer.parseInt(soapEANTfd.getText()));           
            	soap.setTitle(soapTitleTfd.getText());        	
            	soap.setCategory(soapCategoryTfd.getText());
            	soap.setPrice(Integer.parseInt(soapPriceTfd.getText()));
//            	soap.setCreatedAt(convertToTimestamp(soapCreatedTfd.getText()));            	                            

                
                System.out.println("Soap: " + soap.getId() + ", " + soap.getEAN() + ", " + soap.getTitle() + ", " + soap.getCategory() + ", " + soap.getPrice() + ", " + soap.getCreatedAt());
                dataManagement.addSoap(soap);
                
                break;
            case 2:
            	Employer employer = new Employer();
            	

            	employer.setEmployerName(employerNameTfd.getText());
            	employer.setAddress(employerAddressTfd.getText());
            	employer.setEmail(employerEmailTfd.getText());
            	employer.setPhoneNumber(phoneNumberTfd.getText());
            	employer.setIndustry(industryTfd.getText());       	
            	employer.setEstablishedDate(convertToDate(establishedTfd.getText()));

            
                System.out.println("Employer: " + employer.getEmployerId() + ", " + employer.getEmployerName() + ", " + employer.getAddress() + ", " + employer.getEmail() + ", " + employer.getPhoneNumber() + ", " + employer.getIndustry() + ", " + employer.getEstablishedDate());
                
                dataManagement.addEmployer(employer);
                break;
            case 3:
            	Customers customer = new Customers();
            	

            	customer.setName(customerNameTfd.getText());     	
            	customer.setAddress(customerAddressTfd.getText());
            	customer.setEmail(customerEmailTfd.getText());
            	customer.setPassword(customerPasswordTfd.getText());
            	customer.setCity(customerCityTfd.getText()); 
            	customer.setBirthDate(convertToDate(customerBirthdayTfd.getText()));
//            	customer.setCreatedAt(convertToTimestamp(customerCreatedAtTfd.getText())); 
            	
            	
                System.out.println("Customer: " + customer.getId() + ", " + customer.getName() + ", " + customer.getAddress() + ", " + customer.getEmail() + ", " + customer.getPassword() + ", " + customer.getCity() + ", " + customer.getBirthDate() + ", " + customer.getCreatedAt());
                dataManagement.addCustomer(customer);
                break;
            default:
                break;
        }
    }
    	
    	
    
    


    public void editRowInDatabase() {
    	
    }
    
    public void refreshDatabase() {
    	
    }
    public void setActionAddButton(int menuItem) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Button geklickt: " + e.getActionCommand());
                readTextfield(menuItem); // Ruft die Methode zum Lesen und Verarbeiten der Textfelder auf
            }
        });
    }
    public void deleteRowInDatabase(int menuItem) {
    	switch (menuItem) {
        case 0:
        	     ArrayList<Orders> orders = dataManagement.fetchOrdersFromDatabase();
        	     Orders selectedOrder = orders.get(selectedRow);  
        	     dataManagement.deleteOrder(selectedOrder);
        	     System.out.println("Selected Order ID: " + selectedOrder.getId());
        	break;
            
        case 1:
        		ArrayList<Soap> soap = dataManagement.fetchSoapsFromDatabase();
        		Soap selectedSoap = soap.get(selectedRow);  
        		dataManagement.deleteSoap(selectedSoap);
        		System.out.println("Selected Order ID: " + selectedSoap.getId());
            break;
        case 2:
        		ArrayList<Employer> employer = dataManagement.fetchEmployersFromDatabase();
        		Employer selectedEmployer = employer.get(selectedRow);  
        		dataManagement.deleteEmployer(selectedEmployer);
        		System.out.println("Selected Order ID: " + selectedEmployer.getEmployerId());
            break;
        case 3:
        		ArrayList<Customers> customer = dataManagement.fetchCustomersFromDatabase();
        		Customers selectedCustomers = customer.get(selectedRow);  
   	     		dataManagement.deleteCustomer(selectedCustomers);
   	     		System.out.println("Selected Order ID: " + selectedCustomers.getId());
            break;
        default:
            break;
    }
    	
//        if (selectedIndex != -1 && selectedIndex < orders.size()) {
        
        
        
//    }
    }
    public void setActionDeleteButton(int menuItem) {   	
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Button geklickt: " + e.getActionCommand());
               deleteRowInDatabase(menuItem);

            }
        });
    }
    public void setActionEditButton(int menuItem) {
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Button geklickt: " + e.getActionCommand());
                // soll die ausgewählte reihe scannen und dann die Möglichkeit bieten zu bearbeiten
            }
        });
    }
    public void setActionRefreshButton(int menuItem) {
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Button geklickt: " + e.getActionCommand());
                // soll die datenbank refreshen
            }
        });
    }
    public static Timestamp convertToTimestamp(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsedDate = (Date) dateFormat.parse(dateString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception properly in your application
            return null; // Return null or handle error case as appropriate
        }
    }

    public static Date convertToDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateString);
            return new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception properly in your application
            return null; // Return null or handle error case as appropriate
        }
    }
}
