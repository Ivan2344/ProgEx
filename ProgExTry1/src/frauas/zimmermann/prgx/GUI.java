package frauas.zimmermann.prgx;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import frauas.zimmermann.prgx.MiddlePanel.ProductTransferHandler;

public class GUI extends Mainframe implements MiddlePanel{
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
//		 firstLeftPanel.add(addButtonPanel);
//		 firstLeftPanel.add(deleteButtonPanel);
//		 firstLeftPanel.add(editButtonPanel);
//		 firstLeftPanel.add(refreshButtonPanel);
		
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
//        Color myColor = new Color(230, 230, 255);
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
                    handleSelectedRow(selectedRow, employerDatabase, "Employer");
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
            	System.out.print(selectedRow);
                if (selectedRow != -1) {
                    handleSelectedRow(selectedRow, soapDatabase, "Soap");
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
        orderPanel.add(setUpper("Order Database"), BorderLayout.NORTH);

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
                    handleSelectedRow(selectedRow, orderDatabase, "Order");
                    System.out.print("selectedrow: " + selectedRow);
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
                	System.out.printf("KLICK!");
                	System.out.print(selectedRow);
                    handleSelectedRow(selectedRow, customerDatabase, "Customer");
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
    public JPanel setOrderTextPanel() {
    	String className = "Orders";
        String[] labels = {"ID", "User ID","Employee ID", "Order Date", "Status", "Total", "Tax", "Discount"};
        return createMaskPanel(labels, className);
    }

    public JPanel setEmployerTextPanel() {
    	String className = "Employees";
        String[] labels = {"ID", "Name", "Address", "Email", "Phone Number", "Industry", "Established Date"};
        return createMaskPanel(labels, className);
    }

    public JPanel setSoapTextPanel() {
    	String className = "Products";
        String[] labels = {"ID", "EAN", "Title", "Category", "Price"};
        return createMaskPanel(labels, className);
    }

    public JPanel setCustomerTextPanel() {
    	String className = "Customers";
    	String[] labels ={"ID", "Name", "Address", "Email", "Password", "City", "Birth Date"};
        return createMaskPanel(labels, className);
    }

    private Map<String, JTextField[]> textFieldMap = new HashMap<>();				//mapping panel name to JTextFields

    private JPanel createMaskPanel(String[] labels, String panelName) {
    	JPanel titlePanel = new JPanel(new BorderLayout());
//    	titlePanel = createPanelWithBorder(RIGHT_PANEL);
    	JLabel titleLabel = new JLabel();
    	titleLabel = createCustomLabel("Insert: ");
    	titleLabel.setBackground(Color.LIGHT_GRAY);
    	titleLabel.setFont(new java.awt.Font("Book Antiqua", 0, 20));
    	titlePanel.add(titleLabel);
        JPanel tempPanel = new JPanel(new BorderLayout());
        JPanel leftTempPanel = new JPanel(new GridLayout(labels.length, 1));
        JPanel rightTempPanel = new JPanel(new GridLayout(labels.length, 1));
  
        JLabel[] customLabels = new JLabel[labels.length];
        JTextField[] textFields = new JTextField[labels.length];
        int nextId = dataManagement.findMaxId(panelName)+1;

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
//       

        textFieldMap.put(panelName, textFields);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftTempPanel, rightTempPanel);
        splitPane.setDividerLocation(3.0 / 3.0);
        splitPane.setResizeWeight(0.33);
        tempPanel.add(splitPane, BorderLayout.CENTER);        
        tempPanel.add(titlePanel, BorderLayout.NORTH);
        
        if(panelName == "Orders") {
	        JButton openProductsButton = new JButton("Add Products");
	        openProductsButton.setFont(new java.awt.Font("Book Antiqua", 0, 18));
	        openProductsButton.addActionListener(e ->addProductsToOrderFrame(dataManagement));
	
	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        buttonPanel.setBackground(BLUE);
	        buttonPanel.add(openProductsButton);
	        tempPanel.add(buttonPanel, BorderLayout.SOUTH);	       
        }
        secondLeftPanel.add(tempPanel);

        
        return tempPanel;
    }
    private JLabel createCustomLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new java.awt.Font("Book Antiqua", 0, 18));
        label.setBackground(BLUE);
        label.setForeground(Color.DARK_GRAY);
        label.setOpaque(true); // Needed for background color to be visible
        Border margin = new EmptyBorder(0, 10, 0, 0);
        
        Border border = BorderFactory.createLineBorder(Color.gray);
        label.setBorder(BorderFactory.createCompoundBorder(border, margin));
        return label;
    }
    //handles select and drop function in Product frame
    static class ProductTransferHandler extends TransferHandler {
        /**
		 * 
		 */
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
    
    
    private static void addProductsToOrderFrame(Data_management dataManagement) {
    	 int orderId = dataManagement.findMaxId("Orders");
    	 
        JFrame newFrame = new JFrame("Product Drag and Drop");
        newFrame.setSize(600, 400);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setLayout(new BorderLayout());

        DefaultListModel<String> cartListModel = new DefaultListModel<>();
        
       
        JTree productTree = new JTree(productTreeModel(dataManagement));
        JList<String> cartList = new JList<>(cartListModel);
        
        ArrayList<Soap> existingSoaps = dataManagement.fetchProductsByOrderId(orderId);
        for (Soap soap : existingSoaps) {
            cartListModel.addElement(soap.getTitle());
        }
        

        productTree.setDragEnabled(true);
        productTree.setTransferHandler(new TreeTransferHandler());

        cartList.setDropMode(DropMode.INSERT);
        cartList.setTransferHandler(new ProductTransferHandler());

        // Add tree selection listener to add selected product to the cart
        productTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) productTree.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.isLeaf()) {
                String selectedProduct = selectedNode.toString();
                if (selectedProduct != null && !cartListModel.contains(selectedProduct)) {
                    cartListModel.addElement(selectedProduct);
                }
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(productTree), new JScrollPane(cartList));
        splitPane.setResizeWeight(0.5); // Initial divider location

        // Add component listener to adjust the split pane divider location on frame resize
        newFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                splitPane.setDividerLocation(0.5);
            }
        });

        newFrame.add(splitPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save");

//        deleteButton.addActionListener(e -> {
//            int selectedIndex = cartList.getSelectedIndex();
//            if (selectedIndex != -1) {
//                cartListModel.remove(selectedIndex);
//            }
//        });
        deleteButton.addActionListener(e -> {
            int selectedIndex = cartList.getSelectedIndex();
            System.out.println("index:"+selectedIndex);
            if (selectedIndex != -1) {
                // Assuming cartListModel contains objects of type Orders, and you can get orderId from the selected item
              String selectedProduct = cartListModel.getElementAt(selectedIndex);
              int soapId = dataManagement.getSoapIdByTitle(selectedProduct);
//           
                // Delegate deletion to DataManagement instance
                dataManagement.deleteOrderProductReference(orderId,soapId);
                cartListModel.remove(selectedIndex);  //incase product is not saved-> no reference
            }
        });

        saveButton.addActionListener(e -> {
        	// saveCartContentsToDatabase(cartListModel, dataManagement, 1); // Assuming order ID is 1
        	 saveCartContentsToDatabase(cartListModel, dataManagement, orderId, existingSoaps);
        });

        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        newFrame.add(buttonPanel, BorderLayout.SOUTH);

        newFrame.setVisible(true);
    }

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

    
    private static void saveCartContentsToDatabase(DefaultListModel<String> cartListModel, Data_management dataManagement,int orderId, ArrayList<Soap> existingSoaps) {
    	Set<String> existingProductTitles = new HashSet<>();
        for (Soap soap : existingSoaps) {
            existingProductTitles.add(soap.getTitle());
        }
        
//    	for (int i = 0; i < cartListModel.getSize(); i++) {
//            String productTitle = cartListModel.getElementAt(i);
//            if (existingProductTitles.contains(productTitle)) {
//	            int soapId = dataManagement.getSoapIdByTitle(productTitle);
//	            if (soapId != -1) {
//	                dataManagement.addOrderProductReference(1, soapId);
//	            } else {
//	                System.out.println("Error: Could not find soap ID for title: " + productTitle);
//	            }
//            }
//        }
    	for (int i = 0; i < cartListModel.getSize(); i++) {
            String productTitle = cartListModel.getElementAt(i);
            int soapId = dataManagement.getSoapIdByTitle(productTitle);
            if (soapId != -1) {
                dataManagement.addOrderProductReference(orderId, soapId);
                System.out.println("Product ID:"+ soapId+ ", Product Title: "+ productTitle);
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
	            	dataManagement.addOrder(order); }
	            
	            for (int i = 1; i < textFields.length; i++) {
	                textFields[i].setText("");
	                
//	                int nextId = dataManagement.findMaxId(panelName)+1;
//
//
//	                    JTextField textField = new JTextField();
//	                    if ("ID".equals(labels[i])) {
//	                        textField.setText(String.valueOf(nextId));
//	                        textField.setEditable(false);
//	                    }
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
	            }
	            
	            for (int i = 1; i < textFields.length; i++) {
	                textFields[i].setText("");
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
		          }
		          for (int i = 1; i < textFields.length; i++) {
		        	    textFields[i].setText("");
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
		          }
		          for (int i = 1; i < textFields.length; i++) {
		        	    textFields[i].setText("");
		        	}
		          	System.out.println("Customer verarbeitet: " + customer);
        
        	
        }
        }
    
//    public void readTextfield(int menuItem, String operation, Object object) {
//        JTextField[] textFields;
//        
//        switch (menuItem) {
//            case 0:  // Orders
//                textFields = textFieldMap.get("Orders");
////                object = new Orders();
//                object.setUser_id(Integer.parseInt(textFields[1].getText()));  // User ID
//                object.setEmployee_id(Integer.parseInt(textFields[2].getText()));
//                object.setOrder_date(convertToDate(textFields[3].getText()));  // Order Date
//                object.setStatus(textFields[4].getText());                     // Status
//                object.setTotal(Integer.parseInt(textFields[5].getText()));    // Total
//                object.setTax(Float.parseFloat(textFields[6].getText()));      // Tax
//                object.setDiscount(Integer.parseInt(textFields[7].getText())); // Discount
//
//                if (operation == "edit") {
//                	dataManagement.updateOrder(order);
//                } else if(operation == "add") {
//                	System.out.println("Orders: " + order.getId() + ", " + order.getUser_id() + ", " + order.getEmployee_id() + ", " + order.getOrder_date() + ", " + order.getStatus() + ", " + order.getTotal() + ", "+ order.getTax() + ", " + order.getDiscount());
//                	dataManagement.addOrder(order); 
//                	
//                	for (JTextField textField : textFields) {
//                    textField.setText("");
//                }              	
//                }
//                
//                
//                
//                break;
//            case 1:  // Soap
//                textFields = textFieldMap.get("Products");
//                Soap soap = new Soap();
//                soap.setEAN(Integer.parseInt(textFields[1].getText()));       // EAN
//                soap.setTitle(textFields[2].getText());                       // Title
//                soap.setCategory(textFields[3].getText());                    // Category
//                soap.setPrice(Double.parseDouble(textFields[4].getText()));     // Price
//
//                if (operation == "edit") {
//                	dataManagement.updateSoap(soap);
//                } else if(operation == "add") {
//                	System.out.println("Orders: " + soap.getId() + ", " + soap.getId() + ", " + soap.getEAN() + ", " + soap.getTitle() + ", " + soap.getCategory() + ", " + soap.getPrice());
//                	dataManagement.addSoap(soap);
//                }
//                break;
//            case 2:  // Employer
//                textFields = textFieldMap.get("Employees");
//                Employer employer = new Employer();
//                employer.setEmployerName(textFields[1].getText());            // Name
//                employer.setAddress(textFields[2].getText());                 // Address
//                employer.setEmail(textFields[3].getText());                   // Email
//                employer.setPhoneNumber(textFields[4].getText());             // Phone Number
//                employer.setIndustry(textFields[5].getText());                // Industry
//                employer.setEstablishedDate(convertToDate(textFields[6].getText())); // Established Date
//
//                if (operation == "edit") {
//                	dataManagement.updateEmployer(employer);
//                } else if(operation == "add") {
//                	System.out.println("Orders: " + employer.getEmployerId() + ", " + employer.getEmployerName() + ", " + employer.getAddress() + ", " + employer.getEmail() + ", " + employer.getPhoneNumber()  + ", "+ employer.getIndustry() + ", " + employer.getEstablishedDate());
//                	dataManagement.addEmployer(employer);
//                }
//                break;
//            case 3:  // Customers
//                textFields = textFieldMap.get("Customers");
//                Customers customer = new Customers();
//                customer.setName(textFields[1].getText());                    // Name
//                customer.setAddress(textFields[2].getText());                 // Address
//                customer.setEmail(textFields[3].getText());                   // Email
//                customer.setPassword(textFields[4].getText());                // Password
//                customer.setCity(textFields[5].getText());                    // City
//                customer.setBirthDate(convertToDate(textFields[6].getText())); // Birth Date
//
//                if (operation == "edit") {
//                	dataManagement.updateCustomer(customer);
//                } else if(operation == "add") {
//                	System.out.println("Orders: " + customer.getId() + ", " + customer.getName() + ", " + customer.getAddress() + ", " + customer.getEmail() + ", " + customer.getPassword() + ", " + customer.getCity() + ", "+ customer.getBirthDate() );
//                	dataManagement.addCustomer(customer);
//                }
//                break;
//            default:
//                break;
//        }
//    }

    	
    	
    
    


    public void editRowInDatabase(int menuItem) {

    	switch (menuItem) {
        case 0:
        	     ArrayList<Orders> orders = dataManagement.fetchOrdersFromDatabase();
        	     Orders selectedOrder = orders.get(selectedRow);      
        	     readTextfield(menuItem, EDIT_AND_ADD, selectedOrder);
        	     System.out.println("Selected Order ID: " + selectedOrder.getId());
//                 System.out.println("Orders: " + order.getId() + ", " + order.getUser_id() + ", " + order.getEmployee_id() + ", " + order.getOrder_date() + ", " + order.getStatus() + ", " + order.getTotal() + ", "+ order.getTax() + ", " + order.getDiscount());
//                 dataManagement.addOrder(order);       	     
        	break;
            
        case 1:
        		ArrayList<Soap> soap = dataManagement.fetchSoapsFromDatabase();
        		Soap selectedSoap = soap.get(selectedRow);  
        		readTextfield(menuItem, EDIT_AND_ADD, selectedSoap);
        		System.out.println("Selected Order ID: " + selectedSoap.getId());
            break;
        case 2:
        		ArrayList<Employer> employer = dataManagement.fetchEmployersFromDatabase();
        		Employer selectedEmployer = employer.get(selectedRow);  
        		readTextfield(menuItem, EDIT_AND_ADD, selectedEmployer);
        		System.out.println("Selected Order ID: " + selectedEmployer.getEmployerId());
            break;
        case 3:
        		ArrayList<Customers> customer = dataManagement.fetchCustomersFromDatabase();
        		Customers selectedCustomers = customer.get(selectedRow);  
        		readTextfield(menuItem, EDIT_AND_ADD, selectedCustomers);
   	     		System.out.println("Selected Order ID: " + selectedCustomers.getId());
            break;
        default:
            break;
    }
    	
    }
    
    public void refreshDatabase() {
    	
    }
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
               deleteRowInDatabase(menuItem);

            }
        });
    }
    public void setActionEditButton(int menuItem) {
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	editRowInDatabase(menuItem);
            }
        });
    }
    public void setActionRefreshButton(int menuItem) {
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    
    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
    
    
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