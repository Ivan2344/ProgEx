package frauas.zimmermann.prgx;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class GUI extends Mainframe {
    JPanel[] panelList;
    String[] menuItems = {"Orders", "Products", "Employees", "Customers"};
    JPanel rightPanel;
    private DefaultTableModel employerTableModel;
    
    GUI() {
        super();
        createMainPanel();
        createMenuList(menuItems);
        northCenterPanel.revalidate();
        northCenterPanel.repaint();
        
    }

    public void createMenuList(String[] items) {
        JList<String> menuList = new JList<>(items);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        menuList.setVisibleRowCount(1); // Show all items
        menuList.setFont(new java.awt.Font("Felix Titling", 0, 18));
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
                    int selectedIndex = menuList.getSelectedIndex();
                    for (int i = 0; i < panelList.length; i++) {
                        if (i == selectedIndex) {
                            panelList[i].setVisible(true);
                            System.out.print("item click is recognized\n");
                            System.out.print(i);
                            System.out.print("\n");
//                            String selectedItem = menuItems[selectedIndex];
//                            setViewPanel(selectedItem);
      
                        } else {
                            panelList[i].setVisible(false);
                            System.out.print("item click is recognized empty\n");
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

    public JPanel setMainPanels(int menuItem) {
        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(Color.WHITE);
        tempPanel.setLayout(new GridLayout(1, 2));

        
        JPanel leftPanel = createPanelWithBorder(LEFT_PANEL);
        rightPanel = createPanelWithBorder(RIGHT_PANEL);        
        JPanel firstLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
		JPanel secondLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
		
		
		
		
        JLabel firstLabel = new JLabel(String.valueOf(menuItem), SwingConstants.CENTER);
        firstLeftPanel.add(firstLabel);
        JLabel secondLabel = new JLabel("Second Left Panel", SwingConstants.CENTER);
        secondLeftPanel.add(secondLabel);
        JLabel rightLabel = new JLabel("Right Panel", SwingConstants.CENTER);        
        rightPanel.add(rightLabel);
        
        String selectedItem = menuItems[menuItem];
        setViewPanel(selectedItem);
        
        leftPanel.add(firstLeftPanel);
        leftPanel.add(secondLeftPanel);
        tempPanel.add(leftPanel);
        tempPanel.add(rightPanel);
        
//        setContents(menuItem);
        
        return tempPanel;
    }

    public void setViewPanel(String viewName) {
        switch (viewName) {
            case "Employees":
                setEmployerPanel();
                break;
            case "Customers":
                setCustomerPanel();
                break;
            case "Orders":
                setOrderPanel();
                break;
            case "Products":
                setSoapPanel();
                break;
            default:
                // Handle unknown view
                break;
        }
    }
    private JPanel createPanelWithBorder(int panelType) {
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
        JPanel upper = new JPanel();
        upper.setBackground(Color.LIGHT_GRAY);
        upper.setPreferredSize(new Dimension(0, 30));
        upper.add(createLabel("Employer Database"));
        employerPanel.add(upper, BorderLayout.NORTH);

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

        JTable employerDatabase = new JTable(employerTableModel);
        employerDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = employerDatabase.getSelectedRow();
                if (row != -1) {
                    handleSelectedRow(row, employerDatabase, "Employer");
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
        Data_management dataManagement = new Data_management("username", "password");
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
    
    private void setSoapPanel() {
        rightPanel.removeAll();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);

        JPanel soapPanel = new JPanel();
        soapPanel.setLayout(new BorderLayout());
        soapPanel.setPreferredSize(new Dimension(300, 300));

        // Set upper panel
        JPanel upper = new JPanel();
        upper.setBackground(Color.LIGHT_GRAY);
        upper.setPreferredSize(new Dimension(0, 30));
        upper.add(createLabel("Soap Database"));
        soapPanel.add(upper, BorderLayout.NORTH);

        setLeft(soapPanel);
        setRight(soapPanel);

        DefaultTableModel soapTableModel = new DefaultTableModel();
        soapTableModel.addColumn("ID");
        soapTableModel.addColumn("EAN");
        soapTableModel.addColumn("Title");
        soapTableModel.addColumn("Category");
        soapTableModel.addColumn("Price");
        soapTableModel.addColumn("Created At");

        JTable soapDatabase = new JTable(soapTableModel);
        soapDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = soapDatabase.getSelectedRow();
                if (row != -1) {
                    handleSelectedRow(row, soapDatabase, "Soap");
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
        Data_management dataManagement = new Data_management("username", "password");
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
    
    
    private void setOrderPanel() {
        rightPanel.removeAll();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setPreferredSize(new Dimension(300, 300));

        // Set upper panel
        JPanel upper = new JPanel();
        upper.setBackground(Color.LIGHT_GRAY);
        upper.setPreferredSize(new Dimension(0, 30));
        upper.add(createLabel("Order Database"));
        orderPanel.add(upper, BorderLayout.NORTH);

        setLeft(orderPanel);
        setRight(orderPanel);

        DefaultTableModel orderTableModel = new DefaultTableModel();
        orderTableModel.addColumn("ID");
        orderTableModel.addColumn("User ID");
        orderTableModel.addColumn("Order Date");
        orderTableModel.addColumn("Status");
        orderTableModel.addColumn("Total");
        orderTableModel.addColumn("Subtotal");
        orderTableModel.addColumn("Tax");
        orderTableModel.addColumn("Discount");

        JTable orderDatabase = new JTable(orderTableModel);
        orderDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = orderDatabase.getSelectedRow();
                if (row != -1) {
                    handleSelectedRow(row, orderDatabase, "Order");
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
        Data_management dataManagement = new Data_management("username", "password");
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
    
    private void setCustomerPanel() {
        rightPanel.removeAll();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);

        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new BorderLayout());
        customerPanel.setPreferredSize(new Dimension(300, 300));

        // Set upper panel
        JPanel upper = new JPanel();
        upper.setBackground(Color.LIGHT_GRAY);
        upper.setPreferredSize(new Dimension(0, 30));
        upper.add(createLabel("Customer Database"));
        customerPanel.add(upper, BorderLayout.NORTH);

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

        JTable customerDatabase = new JTable(customerTableModel);
        customerDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = customerDatabase.getSelectedRow();
                if (row != -1) {
                    handleSelectedRow(row, customerDatabase, "Customer");
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
        Data_management dataManagement = new Data_management("username", "password");
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
        switch (tableName) {
            case "Employer":
                selectedData = "Selected " + tableName + ": " + table.getValueAt(row, 1);
                break;
            case "Customer":
                selectedData = "Selected " + tableName + ": " + table.getValueAt(row, 1);
                break;
            case "Soap":
                selectedData = "Selected " + tableName + ": " + table.getValueAt(row, 1);
                break;
            case "Order":
                selectedData = "Selected " + tableName + ": " + table.getValueAt(row, 1);
                break;
            default:
                break;
        }
        JOptionPane.showMessageDialog(null, selectedData);
    }
    
//    public JPanel setContents(int i) {
//    	JPanel contentPanel = new JPanel();
//    	
//    	switch(i){
//    	case 0: 
//    		
////    		JButton addButton = new JButton("Add");
////    		JButton deleteButton = new JButton("Delete");
////    		JButton editButton = new JButton("Edit");
////    		JButton refreshButton = new JButton("Refresh");
//    		
//    		
//    		break;
//    	case 1: 
//    		 
//    	    break;
//    	    
//    	case 2: 
//    		
//    	case 3: 
//   		
// 	    break;
//    	        
//    	        
//    	       
//    		
//    		
//    	}
//    	
//    	  	        
//    	return contentPanel;
//    }
    
    
    
}
