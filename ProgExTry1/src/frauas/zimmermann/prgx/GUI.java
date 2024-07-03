package frauas.zimmermann.prgx;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComponent;
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
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import frauas.zimmermann.prgx.MiddlePanel.ProductTransferHandler;

public class GUI extends Mainframe implements MiddlePanel{
	JFrame newFrame;
    JPanel[] panelList;
    String[] menuItems = {"Orders", "Products", "Employees", "Customers"};
    JPanel leftPanel, rightPanel ,secondLeftPanel, firstLeftPanel;
    private DefaultTableModel employerTableModel;
    public Data_management dataManagement;
    static public int selectedIndex = -1;
    private int selectedRow = -1;
    public static final String ONLY_ADD = "add",EDIT_AND_ADD = "edit";
    private JButton addButton , deleteButton , editButton,refreshButton ;
   private JTable soapDatabase, employerDatabase, orderDatabase , customerDatabase;

   private final Map<String, JTextField[]> textFieldMap = new HashMap<>();
  
   /**
    * Constructor: GUI
    * @param usr the username for data management
    * @param psw the password for data management
    * Description: Initializes the GUI and sets up the main panel and menu list.
    */
   
    public GUI(String usr, String psw) { 
        super();
        dataManagement = new Data_management(usr, psw);
        createMainPanel();
        createMenuList(menuItems);
        northCenterPanel.revalidate();
        northCenterPanel.repaint();        
    }
    
    
    
    
    /**
     * Method: createMainPanel
     * Description: Creates and sets up the main panel and its components.
     */
    
    public void createMainPanel() {
        panelList = new JPanel[menuItems.length];
        innerCenterPanel.setLayout(new CardLayout());
        
        for (int i = 0; i < menuItems.length; i++) {
        	
            panelList[i] = setMainPanels(i);
            innerCenterPanel.add(panelList[i]);
        }
        
        frame.add(center, BorderLayout.CENTER);
    }
    
    

    /**
     * Method: createMenuList
     * @param items an array of menu item names
     * Description: Creates and sets up the menu list for the GUI.
     */    
    
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
      
                        } else {
                            panelList[i].setVisible(false);
                        }
                        
                    }
                    innerCenterPanel.revalidate();
                    innerCenterPanel.repaint();
                }
            }
        });


        northCenterPanel.add(menuList);
    }


    
    
    /**
     * Method: setMainPanels
     * @param menuItem the index of the menu item
     * @return a JPanel configured based on the menu item
     * Description: Configures and returns a JPanel based on the provided menu item index.
     */   
    
    public JPanel setMainPanels(int menuItem) {
    	JPanel titlePanel = new JPanel();
        JPanel tempPanel = new JPanel(); 
        JPanel fLTPanel = new JPanel();
        fLTPanel.setLayout(new GridLayout(4,1));
        JPanel addButtonPanel = new JPanel();
        JPanel editButtonPanel = new JPanel();  
        JPanel deleteButtonPanel = new JPanel();     
        JPanel refreshButtonPanel = new JPanel();
        JLabel titleLabel = new JLabel();
        titleLabel = createCustomLabel("Commands:");
        titleLabel.setBackground(Color.LIGHT_GRAY);
        titlePanel = createPanelWithBorder(BUTTON_PANEL);
        titlePanel.setFont(new java.awt.Font("Book Antiqua", 0, 17));
        titlePanel.add(titleLabel);
                
        tempPanel.setBackground(Color.WHITE);
        tempPanel.setLayout(new GridLayout(1, 2));
		
        
        leftPanel = createPanelWithBorder(LEFT_PANEL);
        rightPanel = createPanelWithBorder(RIGHT_PANEL);        
        firstLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
		secondLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
		firstLeftPanel.setLayout(new BorderLayout());		
		
		if(menuItem==0) {
			rightPanel.setBorder(BorderFactory.createMatteBorder(
	                100, 20, 60, 40, BORDER_COLOR));
	        rightPanel.setLayout(new GridLayout(1, 2));
			
		}
		
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
    	
		 
		 fLTPanel.add(addButtonPanel);
		 fLTPanel.add(deleteButtonPanel);
		 fLTPanel.add(editButtonPanel);
		 fLTPanel.add(refreshButtonPanel);
		 
		 firstLeftPanel.add(titlePanel, BorderLayout.NORTH);
		 firstLeftPanel.add(fLTPanel, BorderLayout.CENTER);
		

		secondLeftPanel.setLayout(new BorderLayout());
		
        JLabel rightLabel = new JLabel("Right Panel", SwingConstants.CENTER);        
        rightPanel.add(rightLabel);
        

        String selectedItem = menuItems[menuItem];
        setViewPanel(selectedItem);
        
        leftPanel.add(firstLeftPanel);
        leftPanel.add(secondLeftPanel);
        tempPanel.add(leftPanel);
        tempPanel.add(rightPanel);
        
        return tempPanel;
    }
    
    
    
    /**
     * Method: createPanel
     * @return a new JPanel with a BorderLayout and border settings
     * Description: Creates and returns a JPanel with BorderLayout and border settings.
     */   
    
    public JPanel createPanel() {
    	JPanel tempPanel = new JPanel();
    	tempPanel.setLayout(new BorderLayout());
    	tempPanel = createPanelWithBorder(BUTTON_PANEL);
    	return tempPanel;
    }
    
    
    
    /**
     * Method: setButtons
     * @param button the JButton to be styled
     * @return the styled JButton
     * Description: Styles the provided JButton with custom settings.
     */   
    public JButton setButtons(JButton button) {
    	button.setFont(new java.awt.Font("Book Antiqua", 0, 18));
		button.setBackground(LIGHT_BLUE);
		button.setForeground(Color.DARK_GRAY);
		return button;   	
    }
    
    
       
    /**
     * Method: setViewPanel
     * @param viewName the name of the view to be displayed
     * Description: Sets up the view panel based on the provided view name.
     */   
    
    public void setViewPanel(String viewName) {
        switch (viewName) {
            case "Employees":
                setEmployerPanel();
                setEmployerTextPanel();
                break;
                
            case "Customers":
                setCustomerPanel();
                setCustomerTextPanel();
                break;
                
            case "Orders":
                setOrderPanel();
                setOrderTextPanel();
                break;
                
            case "Products":
                setSoapPanel();
                setSoapTextPanel();
                break;
                
            default:
                
                break;       
        }
        //firstLeftPanel.add(setEditButtons);
    }
    
    
    /**
     * Method: createPanelWithBorder
     * @param panelType the type of panel to create
     * @return a JPanel with specified border settings
     * Description: Creates and returns a JPanel with specific border settings based on panel type.
     */
    
    protected JPanel createPanelWithBorder(int panelType) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

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
        employerPanel.add(setUpper("Employer Table"), BorderLayout.NORTH);

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

        employerDatabase = new JTable(employerTableModel) {
       	 @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };
       
        employerDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedRow = employerDatabase.getSelectedRow();
                if (selectedRow != -1) {
                    ArrayList<Employer> employer = dataManagement.fetchEmployersFromDatabase();
                    Employer selectedOrder = employer.get(selectedRow);  
                    
	           	    JTextField[] textFields;
	           	    textFields = textFieldMap.get("Employees"); //FEHLERMEDLUNG
	
	           	    textFields[0].setText(Integer.toString(selectedOrder.getEmployerId()));
	           	    textFields[1].setText(selectedOrder.getEmployerName());
	           	    textFields[2].setText(selectedOrder.getAddress());
	           	    textFields[3].setText(selectedOrder.getEmail());
	           	    textFields[4].setText(selectedOrder.getPhoneNumber());
	           	    textFields[5].setText(selectedOrder.getIndustry());
	           	    textFields[6].setText(convertDateToString(selectedOrder.getEstablishedDate()));
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

    private void setSoapPanel() {
        rightPanel.removeAll();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);

        JPanel soapPanel = new JPanel();
        soapPanel.setLayout(new BorderLayout());
        soapPanel.setPreferredSize(new Dimension(300, 300));

        // Set upper panel
        soapPanel.add(setUpper("Product Table"), BorderLayout.NORTH);

        setLeft(soapPanel);
        setRight(soapPanel);

        DefaultTableModel soapTableModel = new DefaultTableModel();
        soapTableModel.addColumn("ID");
        soapTableModel.addColumn("EAN");
        soapTableModel.addColumn("Title");
        soapTableModel.addColumn("Category");
        soapTableModel.addColumn("Price");
        soapTableModel.addColumn("Created At");

        soapDatabase = new JTable(soapTableModel){
        	 @Override
             public boolean isCellEditable(int row, int column) {
                 return false; // Disable cell editing
             }
         };
        
        soapDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedRow = soapDatabase.getSelectedRow();
            	
                if (selectedRow != -1) {
                    ArrayList<Soap> soaps = dataManagement.fetchSoapsFromDatabase();
                    Soap selectedOrder = soaps.get(selectedRow); 
                    
	           	    JTextField[] textFields;
	           	    textFields = textFieldMap.get("Products");
	
	           	    textFields[0].setText(Integer.toString(selectedOrder.getId()));
	           	    textFields[1].setText(Integer.toString(selectedOrder.getEAN()));
	           	    textFields[2].setText(selectedOrder.getTitle());
	           	    textFields[3].setText(selectedOrder.getCategory());
	           	    textFields[4].setText(Double.toString(selectedOrder.getPrice()));

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

    private void setOrderPanel() {
        rightPanel.removeAll();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setPreferredSize(new Dimension(300, 300));

        // Set upper panel
        orderPanel.add(setUpper("Order Table"), BorderLayout.NORTH);

        setLeft(orderPanel);
        setRight(orderPanel);

        DefaultTableModel orderTableModel = new DefaultTableModel();
        orderTableModel.addColumn("ID");;
        orderTableModel.addColumn("User ID");
        orderTableModel.addColumn("Employee ID");
        orderTableModel.addColumn("Order Date");
        orderTableModel.addColumn("Status");
        orderTableModel.addColumn("Total");
        orderTableModel.addColumn("Tax");
        orderTableModel.addColumn("Discount");

        orderDatabase = new JTable(orderTableModel){
        	 @Override
             public boolean isCellEditable(int row, int column) {
                 return false; // Disable cell editing
             }
         };
        
        orderDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedRow = orderDatabase.getSelectedRow();
                if (selectedRow != -1) {
                    ArrayList<Orders> orders = dataManagement.fetchOrdersFromDatabase();
           	     	Orders selectedOrder = orders.get(selectedRow);  
           	     	
	           	    JTextField[] textFields;
	           	    textFields = textFieldMap.get("Orders");
	
	           	    textFields[0].setText(Integer.toString(selectedOrder.getId()));
	           	    textFields[1].setText(Integer.toString(selectedOrder.getUser_id()));
	           	    textFields[2].setText(Integer.toString(selectedOrder.getEmployee_id()));
	           	    textFields[3].setText(convertDateToString(selectedOrder.getOrder_date()));
	           	    textFields[4].setText(selectedOrder.getStatus());
	           	    textFields[5].setText(Integer.toString(selectedOrder.getTotal()));
	           	    textFields[6].setText(Float.toString(selectedOrder.getTax()));
	           	    textFields[7].setText(Integer.toString(selectedOrder.getDiscount())); 
                }
            }
        });
        
        JButton openProductsButton = new JButton("Add Products");
        openProductsButton.setFont(new java.awt.Font("Book Antiqua", 0, 18));
        openProductsButton.setBackground(LIGHT_BLUE);
        openProductsButton.addActionListener(e ->addProductsToOrderFrame(dataManagement));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.add(openProductsButton);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);	  
        
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
                order.getEmployee_id(),
                order.getOrder_date(),
                order.getStatus(),
                order.getTotal(),
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
        customerPanel.add(setUpper("Customer Table"), BorderLayout.NORTH);

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
        customerDatabase = new JTable(customerTableModel){
            // Override isCellEditable method to make all cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };
        
        customerDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	selectedRow = customerDatabase.getSelectedRow();
                if (selectedRow != -1) {
                    ArrayList<Customers> customers = dataManagement.fetchCustomersFromDatabase();
                    Customers selectedOrder = customers.get(selectedRow);  
                    
	           	    JTextField[] textFields;
	           	    textFields = textFieldMap.get("Customers");
	
	           	    textFields[0].setText(Integer.toString(selectedOrder.getId()));
	           	    textFields[1].setText(selectedOrder.getName());
	           	    textFields[2].setText(selectedOrder.getAddress());
	           	    textFields[3].setText(selectedOrder.getEmail());
	           	    textFields[4].setText(selectedOrder.getPassword());
	           	    textFields[5].setText(selectedOrder.getCity());
	           	    textFields[6].setText(convertDateToString(selectedOrder.getBirthDate()));
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


    /**
     * Sets up the Order text panel.
     *
     * @return JPanel for Orders
     */
    public JPanel setOrderTextPanel() {
        String className = "Orders";
        String[] labels = {"ID", "User ID", "Employee ID", "Order Date", "Status", "Total", "Tax", "Discount"};
        return createMaskPanel(labels, className);
    }

    
    /**
     * Sets up the Employee text panel.
     *
     * @return JPanel for Employees
     */
    public JPanel setEmployerTextPanel() {
        String className = "Employees";
        String[] labels = {"ID", "Name", "Address", "Email", "Phone Number", "Industry", "Established Date"};
        return createMaskPanel(labels, className);
    }

    
    /**
     * Sets up the Product text panel.
     *
     * @return JPanel for Products
     */
    public JPanel setSoapTextPanel() {
        String className = "Products";
        String[] labels = {"ID", "EAN", "Title", "Category", "Price"};
        return createMaskPanel(labels, className);
    }

    
    /**
     * Sets up the Customer text panel.
     *
     * @return JPanel for Customers
     */
    public JPanel setCustomerTextPanel() {
        String className = "Customers";
        String[] labels = {"ID", "Name", "Address", "Email", "Password", "City", "Birth Date"};
        return createMaskPanel(labels, className);
    }

    
    /**
     * Creates a mask panel with specified labels and panel name.
     *
     * @param labels    the labels for the fields
     * @param panelName the name of the panel
     * @return JPanel with specified labels
     */
    private JPanel createMaskPanel(String[] labels, String panelName) {
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = createCustomLabel("Insert: ");
        titleLabel.setBackground(Color.LIGHT_GRAY);
        titleLabel.setFont(new java.awt.Font("Book Antiqua", 0, 20));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel tempPanel = new JPanel(new BorderLayout());
        JPanel leftTempPanel = new JPanel(new GridLayout(labels.length, 1));
        JPanel rightTempPanel = new JPanel(new GridLayout(labels.length, 1));

        JLabel[] customLabels = new JLabel[labels.length];
        JTextField[] textFields = new JTextField[labels.length];

        int nextId = dataManagement.findMaxId(panelName) + 1;

        for (int i = 0; i < labels.length; i++) {
            customLabels[i] = createCustomLabel(labels[i]);
            leftTempPanel.add(customLabels[i]);

            JTextField textField = new JTextField();
            if ("ID".equals(labels[i])) {
                textField.setText(String.valueOf(nextId));
                textField.setEditable(false);
            }
            textFields[i] = textField;
            rightTempPanel.add(textField);
        }

        textFieldMap.put(panelName, textFields);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftTempPanel, rightTempPanel);
        splitPane.setDividerLocation(3.0 / 3.0);
        splitPane.setResizeWeight(0.33);
        tempPanel.add(splitPane, BorderLayout.CENTER);
        tempPanel.add(titlePanel, BorderLayout.NORTH);

        secondLeftPanel.add(tempPanel);

        return tempPanel;
    }

    /**
     * Creates a custom JLabel.
     *
     * @param text the text for the label
     * @return JLabel with custom settings
     */
    private JLabel createCustomLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new java.awt.Font("Book Antiqua", 0, 18));
        label.setBackground(LIGHT_BLUE);
        label.setForeground(Color.DARK_GRAY);
        label.setOpaque(true); // Needed for background color to be visible
        Border margin = new EmptyBorder(0, 10, 0, 0);
        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        label.setBorder(BorderFactory.createCompoundBorder(border, margin));
        return label;
    }

    /**
     * Transfer handler for product drag and drop functionality.
     */
    static class ProductTransferHandler extends TransferHandler {
        private static final long serialVersionUID = 1L;

        @Override
        public int getSourceActions(JComponent c) {
            return COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new StringSelection(((JList<?>) c).getSelectedValue().toString());
        }

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }

        @Override
        public boolean importData(TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }

            try {
                String data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
                int index = dl.getIndex();
                boolean insert = dl.isInsert();

                DefaultListModel<String> listModel = (DefaultListModel<String>) ((JList<?>) support.getComponent()).getModel();
                if (insert) {
                    listModel.add(index, data);
                } else {
                    listModel.set(index, data);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * Adds products to the order frame with drag and drop functionality.
     *
     * @param dataManagement the data management object
     */
    private void addProductsToOrderFrame(Data_management dataManagement) {
        int orderId;
        if ("Orders".equals("Orders") && selectedRow != -1) { // Gives OrderId of selected row to add products
            ArrayList<Orders> orders = dataManagement.fetchOrdersFromDatabase();
            Orders selectedOrder = orders.get(selectedRow);
            orderId = selectedOrder.getId();
        } else {
            orderId = dataManagement.findMaxId("Orders") + 1;
        }

        newFrame = new JFrame("Product Drag and Drop");
        newFrame.setSize(800, 600); // Adjusted size to accommodate all panels
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setLayout(new BorderLayout());
        newFrame.setResizable(false);

        JPanel existingPanel = new JPanel(new BorderLayout());
        JLabel existingLabel = createCustomLabel("Existing Products");
        existingPanel.add(existingLabel, BorderLayout.NORTH);

        DefaultListModel<String> existingListModel = new DefaultListModel<>();
        DefaultListModel<String> cartListModel = new DefaultListModel<>();

        JTree productTree = new JTree(productTreeModel(dataManagement));
        JList<String> existingList = new JList<>(existingListModel);
        JList<String> cartList = new JList<>(cartListModel);

        ArrayList<Soap> existingSoaps = dataManagement.fetchProductsByOrderId(orderId);
        for (Soap soap : existingSoaps) {
            existingListModel.addElement(soap.getTitle());
        }
        existingPanel.add(new JScrollPane(existingList), BorderLayout.CENTER);
        existingList.setFont(new java.awt.Font("Book Antiqua", 0, 14));
        existingList.setCellRenderer(createListCellRenderer());
        newFrame.add(existingPanel, BorderLayout.WEST);

        JPanel productsPanel = new JPanel(new BorderLayout());
        JLabel productsLabel = createCustomLabel("Products");
        productsPanel.add(productsLabel, BorderLayout.NORTH);

        productTree.setDragEnabled(true);
        productTree.setTransferHandler(new TreeTransferHandler());
        productsPanel.add(new JScrollPane(productTree), BorderLayout.CENTER);
        productTree.setCellRenderer(createTreeCellRenderer());
        newFrame.add(productsPanel, BorderLayout.CENTER);

        JPanel cartPanel = new JPanel(new BorderLayout());
        JLabel cartLabel = createCustomLabel("Drop New Products Here");
        cartPanel.add(cartLabel, BorderLayout.NORTH);
        cartList.setDropMode(DropMode.INSERT);
        cartList.setTransferHandler(new ProductTransferHandler());
        cartList.setFont(new java.awt.Font("Book Antiqua", 0, 14));
        cartList.setCellRenderer(createListCellRenderer());
        cartPanel.add(new JScrollPane(cartList), BorderLayout.CENTER);
        newFrame.add(cartPanel, BorderLayout.EAST);

        // Add tree selection listener to add selected product to the cart
        productTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) productTree.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.isLeaf()) {
                String selectedProduct = selectedNode.toString();
                if (!cartListModel.contains(selectedProduct)) {
                    cartListModel.addElement(selectedProduct);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save");

        deleteButton.addActionListener(e -> {
            int selectedCartIndex = cartList.getSelectedIndex();
            int selectedExistingIndex = existingList.getSelectedIndex();

            if (selectedCartIndex != -1) {
                String selectedProduct = cartListModel.getElementAt(selectedCartIndex);
                int soapId = dataManagement.getSoapIdByTitle(selectedProduct);
                int quantity = dataManagement.getTotalCount(orderId, soapId);
                if (quantity > 1) {
                    dataManagement.deleteOrderProductReference(orderId, soapId);
                    for (int i = 0; i < quantity - 1; i++) {
                        dataManagement.addOrderProductReference(orderId, soapId);
                    }
                } else {
                    dataManagement.deleteOrderProductReference(orderId, soapId);
                }
                cartListModel.remove(selectedCartIndex); // In case product is not saved -> no reference
            } else if (selectedExistingIndex != -1) {
                String selectedProduct = existingListModel.getElementAt(selectedExistingIndex);
                int soapId = dataManagement.getSoapIdByTitle(selectedProduct);
                int quantity = dataManagement.getTotalCount(orderId, soapId);
                if (quantity > 1) {
                    dataManagement.deleteOrderProductReference(orderId, soapId);
                    for (int i = 0; i < quantity - 1; i++) {
                        dataManagement.addOrderProductReference(orderId, soapId);
                    }
                } else {
                    dataManagement.deleteOrderProductReference(orderId, soapId);
                }
                existingListModel.remove(selectedExistingIndex); // Remove from the existing list
            }
        });

        saveButton.addActionListener(e -> {
            saveCartContentsToDatabase(cartListModel, dataManagement, orderId, existingSoaps);
            newFrame.dispose(); // Close the JFrame after saving
        });
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        newFrame.add(buttonPanel, BorderLayout.SOUTH);

        newFrame.setVisible(true);
    }

    
    /**
     * Creates a list cell renderer with custom settings.
     *
     * @return ListCellRenderer with custom settings
     */
    private ListCellRenderer<? super String> createListCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(new EmptyBorder(2, 5, 2, 5)); // Add padding to the label
                label.setFont(new Font("Book Antiqua", 0, 14)); // Set font for list items
                return label;
            }
        };
    }

    
    /**
     * Creates a tree cell renderer with custom settings.
     *
     * @return TreeCellRenderer with custom settings
     */
    private TreeCellRenderer createTreeCellRenderer() {
        return new DefaultTreeCellRenderer() {
            private final Font font = new Font("Book Antiqua", 0, 14); // Custom font for tree nodes

            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                          boolean expanded, boolean leaf, int row, boolean hasFocus) {
                JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                label.setFont(font); // Set the custom font
                return label;
            }
        };
    }

    
    /**
     * Creates a tree model for products.
     *
     * @param dataManagement the data management object
     * @return TreeModel for products
     */
    private static TreeModel productTreeModel(Data_management dataManagement) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Products");
        ArrayList<String> categories = dataManagement.fetchCategoriesFromDatabase();
        for (String category : categories) {
            DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(category);
            ArrayList<Soap> soaps = dataManagement.fetchSoapsByCategory(category);
            for (Soap soap : soaps) {
                categoryNode.add(new DefaultMutableTreeNode(soap.getTitle()));
            }
            root.add(categoryNode);
        }
        return new DefaultTreeModel(root);
    }

    /**
     * Saves the contents of the cart to the database.
     *
     * @param cartListModel     the list model for the cart
     * @param dataManagement    the data management object
     * @param orderId           the ID of the order
     * @param existingSoaps     the existing soaps
     */
    private void saveCartContentsToDatabase(DefaultListModel<String> cartListModel, Data_management dataManagement, int orderId, ArrayList<Soap> existingSoaps) {
        Set<String> existingProductTitles = new HashSet<>();
        for (Soap soap : existingSoaps) {
            existingProductTitles.add(soap.getTitle());
        }

        for (int i = 0; i < cartListModel.getSize(); i++) {
            String productTitle = cartListModel.getElementAt(i);
            int soapId = dataManagement.getSoapIdByTitle(productTitle);
            if (soapId != -1) {
                dataManagement.addOrderProductReference(orderId, soapId);
                System.out.println("Product ID:" + soapId + ", Product Title: " + productTitle);
            } else {
                System.out.println("Error: Could not find soap ID for title: " + productTitle);
            }
        }

        System.out.println("Cart contents saved to database.");
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
        rightPanel.setPreferredSize(new Dimension(15, 0)); // Breite einstellen
        Panel.add(rightPanel, BorderLayout.EAST);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Georgia", Font.PLAIN, 20));
        return label;
    }
    
    
    /**
     * Method: readTextfield
     * @param menuItem the index of the menu item
     * @param operation the operation to perform ("edit" or "add")
     * @param selectedObject the selected object to be processed
     * Description: Reads data from text fields, updates the corresponding object, and performs the specified operation.
     */
    
    
    public void readTextfield(int menuItem, String operation, Object selectedObject) {
    	 JTextField[] textFields;
         System.out.println("Lesen der Textfelder für menuItem: " + menuItem + ", Modus: " + operation);

        if (selectedObject instanceof Orders) {
	        	textFields = textFieldMap.get("Orders");
	            Orders order = (Orders) selectedObject;
	            
	            order.setUser_id(Integer.parseInt(textFields[1].getText()));  // User ID
	            order.setEmployee_id(Integer.parseInt(textFields[2].getText()));
	            order.setOrder_date(convertToDate(textFields[3].getText()));  // Order Date
	            order.setStatus(textFields[4].getText());                     // Status
	            order.setTotal(Integer.parseInt(textFields[5].getText()));    // Total
	            order.setTax(Float.parseFloat(textFields[6].getText()));      // Tax
	            order.setDiscount(Integer.parseInt(textFields[7].getText())); // Discount
	 
	            System.out.println("Order verarbeitet: " + order);
	            if ("edit".equals(operation)) {
	            	dataManagement.updateOrder(order);	            	
	            } else if("add".equals(operation)) {
	            	System.out.println("Orders: " + order.getId() + ", " + order.getUser_id() + ", " + order.getEmployee_id() + ", " + order.getOrder_date() + ", " + order.getStatus() + ", " + order.getTotal() + ", "+ order.getTax() + ", " + order.getDiscount());
	            	dataManagement.addOrder(order);
	            	
	            	if (!dataManagement.errorMessage.isEmpty()) {
        	    	    JOptionPane.showMessageDialog(null, dataManagement.errorMessage, "Edit Error", JOptionPane.ERROR_MESSAGE);
        	    	    dataManagement.errorMessage = "";
        	    	}
	            }
	            for (JTextField textField : textFields) {
	                textField.setText("");
	            }
	            
            
        } else if (selectedObject instanceof Soap) {
	            textFields = textFieldMap.get("Products");
	            Soap soap = (Soap) selectedObject;
	            
	            soap.setEAN(Integer.parseInt(textFields[1].getText()));       // EAN
	            soap.setTitle(textFields[2].getText());                       // Title
	            soap.setCategory(textFields[3].getText());                    // Category
	            soap.setPrice(Double.parseDouble(textFields[4].getText()));     // Price
	
	            if ("edit".equals(operation)) {
	            	dataManagement.updateSoap(soap);
	            } else if("add".equals(operation)) {
	            	System.out.println("Orders: " + soap.getId() + ", " + soap.getId() + ", " + soap.getEAN() + ", " + soap.getTitle() + ", " + soap.getCategory() + ", " + soap.getPrice());
	            	dataManagement.addSoap(soap);
	            	
	            	if (!dataManagement.errorMessage.isEmpty()) {
        	    	    JOptionPane.showMessageDialog(null, dataManagement.errorMessage, "Edit Error", JOptionPane.ERROR_MESSAGE);
        	    	    dataManagement.errorMessage = "";
        	    	}
	            }
	            for (JTextField textField : textFields) {
	                textField.setText("");
	            }
	            System.out.println("Soap verarbeitet: " + soap);
	            
	            
        } else if (selectedObject instanceof Employer) {
        		System.out.println("Erkannt dass es sich um Employer handeln muss");
        		textFields = textFieldMap.get("Employees");
            	Employer employer = (Employer) selectedObject;
            	
	          
            	employer.setEmployerName(textFields[1].getText());            // Name
            	employer.setAddress(textFields[2].getText());                 // Address
            	employer.setEmail(textFields[3].getText());                   // Email
	          	employer.setPhoneNumber(textFields[4].getText());             // Phone Number
	          	employer.setIndustry(textFields[5].getText());                // Industry
	          	employer.setEstablishedDate(convertToDate(textFields[6].getText())); // Established Date
	
		          if ("edit".equals(operation)) {
		          	dataManagement.updateEmployer(employer);
		          } else if("add".equals(operation)) {
		          	System.out.println("Orders: " + employer.getEmployerId() + ", " + employer.getEmployerName() + ", " + employer.getAddress() + ", " + employer.getEmail() + ", " + employer.getPhoneNumber()  + ", "+ employer.getIndustry() + ", " + employer.getEstablishedDate());
		          	dataManagement.addEmployer(employer);
		          	
		          	if (!dataManagement.errorMessage.isEmpty()) {
        	    	    JOptionPane.showMessageDialog(null, dataManagement.errorMessage, "Edit Error", JOptionPane.ERROR_MESSAGE);
        	    	    dataManagement.errorMessage = "";
        	    	}
		          }
		          for (JTextField textField : textFields) {
		                textField.setText("");
		            }
		            System.out.println("Employer verarbeitet: " + employer);
        } else if (selectedObject instanceof Customers) {
            
            	textFields = textFieldMap.get("Customers");
            	Customers customer = (Customers) selectedObject;
		         
		          customer.setName(textFields[1].getText());                    // Name
		          customer.setAddress(textFields[2].getText());                 // Address
		          customer.setEmail(textFields[3].getText());                   // Email
		          customer.setPassword(textFields[4].getText());                // Password
		          customer.setCity(textFields[5].getText());                    // City
		          customer.setBirthDate(convertToDate(textFields[6].getText())); // Birth Date
		
		          if ("edit".equals(operation)) {
		          	dataManagement.updateCustomer(customer);
		          } else if("add".equals(operation)) {
		          	System.out.println("Orders: " + customer.getId() + ", " + customer.getName() + ", " + customer.getAddress() + ", " + customer.getEmail() + ", " + customer.getPassword() + ", " + customer.getCity() + ", "+ customer.getBirthDate() );
		          	dataManagement.addCustomer(customer);
		          	
		          	if (!dataManagement.errorMessage.isEmpty()) {
        	    	    JOptionPane.showMessageDialog(null, dataManagement.errorMessage, "Edit Error", JOptionPane.ERROR_MESSAGE);
        	    	    dataManagement.errorMessage = "";
        	    	}
		          }
		          for (JTextField textField : textFields) {
		                textField.setText("");
		            }
		          	System.out.println("Customer verarbeitet: " + customer);
        
        	
        	}
        }
    	
    

    /**
     * Method to edit a row in the database based on the selected menu item.
     * @param menuItem the index of the menu item
     */
    
    public void editRowInDatabase(int menuItem) {

    	switch (menuItem) {
    	
        case 0:
        	     ArrayList<Orders> orders = dataManagement.fetchOrdersFromDatabase();
        	     Orders selectedOrder = orders.get(selectedRow);      
        	     readTextfield(menuItem, EDIT_AND_ADD, selectedOrder);
        	     
        	     System.out.println("Selected Order ID: " + selectedOrder.getId());
        	     if (!dataManagement.errorMessage.isEmpty()) {
        	    	    JOptionPane.showMessageDialog(null, dataManagement.errorMessage, "Edit Error", JOptionPane.ERROR_MESSAGE);
        	    	    dataManagement.errorMessage = "";
        	    	}
        	break;
            
        case 1:
        		ArrayList<Soap> soap = dataManagement.fetchSoapsFromDatabase();
        		Soap selectedSoap = soap.get(selectedRow);  
        		readTextfield(menuItem, EDIT_AND_ADD, selectedSoap);
        		
        		System.out.println("Selected Order ID: " + selectedSoap.getId());
        		if (!dataManagement.errorMessage.isEmpty()) {
    	    	    JOptionPane.showMessageDialog(null, dataManagement.errorMessage, "Edit Error", JOptionPane.ERROR_MESSAGE);
    	    	    dataManagement.errorMessage = "";
    	    	}
            break;
            
        case 2:
        		ArrayList<Employer> employer = dataManagement.fetchEmployersFromDatabase();
        		Employer selectedEmployer = employer.get(selectedRow);  
        		readTextfield(menuItem, EDIT_AND_ADD, selectedEmployer);
        		
        		System.out.println("Selected Order ID: " + selectedEmployer.getEmployerId());
        		if (!dataManagement.errorMessage.isEmpty()) {
    	    	    JOptionPane.showMessageDialog(null, dataManagement.errorMessage, "Edit Error", JOptionPane.ERROR_MESSAGE);
    	    	    dataManagement.errorMessage = "";
    	    	}
            break;
            
        case 3:
        		ArrayList<Customers> customer = dataManagement.fetchCustomersFromDatabase();
        		Customers selectedCustomers = customer.get(selectedRow);  
        		readTextfield(menuItem, EDIT_AND_ADD, selectedCustomers);
        		
   	     		System.out.println("Selected Order ID: " + selectedCustomers.getId());
   	     	if (!dataManagement.errorMessage.isEmpty()) {
	    	    JOptionPane.showMessageDialog(null, dataManagement.errorMessage, "Edit Error", JOptionPane.ERROR_MESSAGE);
	    	    dataManagement.errorMessage = "";
	    	}
            break;
            
        default:
            break;
    	}    	
    }
    
    

    /**
     * Method to delete a row in the database based on the selected menu item.
     * @param menuItem the index of the menu item
     */
    
    public void deleteRowInDatabase(int menuItem) {
    	switch (menuItem) {
        case 0:
        	     ArrayList<Orders> orders = dataManagement.fetchOrdersFromDatabase();
        	     Orders selectedOrder = orders.get(selectedRow);  
        	     dataManagement.deleteOrder(selectedOrder);
//        	     System.out.println("Selected Order ID: " + selectedOrder.getId());
        	break;
            
        case 1:
        		ArrayList<Soap> soap = dataManagement.fetchSoapsFromDatabase();
        		Soap selectedSoap = soap.get(selectedRow);  
        		dataManagement.deleteSoap(selectedSoap);
//        		System.out.println("Selected Order ID: " + selectedSoap.getId());
            break;
            
        case 2:
        		ArrayList<Employer> employer = dataManagement.fetchEmployersFromDatabase();
        		Employer selectedEmployer = employer.get(selectedRow);  
        		dataManagement.deleteEmployer(selectedEmployer);
//        		System.out.println("Selected Order ID: " + selectedEmployer.getEmployerId());
            break;
            
        case 3:
        		ArrayList<Customers> customer = dataManagement.fetchCustomersFromDatabase();
        		Customers selectedCustomers = customer.get(selectedRow);  
   	     		dataManagement.deleteCustomer(selectedCustomers);
//   	     		System.out.println("Selected Order ID: " + selectedCustomers.getId());
            break;
            
        default:
            break;
    }
    }	
    
    /**
     * Method to refresh all panels in the GUI.
     */
    
    public void refreshALL() {
    		
    	for (int i = 0; i < panelList.length; i++) {
    		panelList[i].removeAll();
        	innerCenterPanel.add(panelList[i]);
        }   	
        	createMainPanel(); 
        	createMenuList(menuItems);
        	
        for (int i = 0; i <= selectedIndex; i++) {
        	
            if(i== selectedIndex ) {
            	panelList[i].setVisible(true);
            }
            else {panelList[i].setVisible(false);
            }
        }
    }
    
    
    /**
     * Method to set action for the add button.
     * @param menuItem the index of the menu item
     */
    
    public void setActionAddButton(int menuItem) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedObject = null;

                switch (menuItem) {
                    case 0:
                        selectedObject = new Orders();
                        break;
                    case 1:
                        selectedObject = new Soap();
                        break;
                    case 2:
                        selectedObject = new Employer();
                        break;
                    case 3:
                        selectedObject = new Customers();
                        break;
                    default:
                        System.out.println("Ungültige Auswahl");
                        return;
                }

                readTextfield(menuItem, ONLY_ADD, selectedObject);
                refreshALL();
            }
        });
    }
    
   
    /**
     * Method to set action for the edit button.
     * @param menuItem the index of the menu item
     */
    
    public void setActionEditButton(int menuItem) {
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	editRowInDatabase(menuItem);
            	refreshALL();
            }
        });
    }
    
    
    /**
     * Method to set action for the delete button.
     * @param menuItem the index of the menu item
     */
    
    public void setActionDeleteButton(int menuItem) {   	
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               deleteRowInDatabase(menuItem);
               refreshALL();

            }
        });
    }
    
    
    /**
     * Method to set action for the refresh button.
     * @param menuItem the index of the menu item
     */
    
    public void setActionRefreshButton(int menuItem) {
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	refreshALL();
            }
        });
    }
    
    
    /**
     * Method to convert a date string to a Timestamp object.
     * @param dateString the date string to convert
     * @return the converted Timestamp object
     */
    
    
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

    
    /**
     * Method to convert a Date object to a string.
     * @param date the Date object to convert
     * @return the converted date string
     */
    
    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
    
    
    /**
     * Method to convert a date string to a Date object.
     * @param dateString the date string to convert
     * @return the converted Date object
     */
    
    public static Date convertToDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsedDate = dateFormat.parse(dateString);
            return new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception properly in your application
            return null; // Return null or handle error case as appropriate
        }
    }
}