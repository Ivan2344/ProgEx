package frauas.zimmermann.prgx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;


import java.awt.*;
import javax.swing.*;

import java.awt.*;
import javax.swing.*;

public interface MiddlePanel {
	 Data_management dataManagement = new Data_management("","");
    static final Color BLUE = new Color(230, 230, 255);
    static final Color BORDER = Color.LIGHT_GRAY;

    public default JPanel setOrderMask() {
    	String className = "Orders";
        String[] labels = {"ID", "User ID", "Order Date", "Status", "Total", "Subtotal", "Tax", "Discount"};
        return createMaskPanel(labels, className);
    }

    public default JPanel setEmployeeMask() {
    	String className = "Employees";
        String[] labels = {"ID", "Name", "Address", "Email", "Phone Number", "Industry", "Established Date"};
        return createMaskPanel(labels, className);
    }

    public default JPanel setSoapMask() {
    	String className = "Products";
        String[] labels = {"ID", "EAN", "Title", "Category", "Price", "Created At"};
        return createMaskPanel(labels, className);
    }

    public default JPanel setCustomerMask() {
    	String className = "Customers";
    	String[] labels ={"ID", "Name", "Address", "Email", "Password", "City", "Birth Date", "Created At"};
        return createMaskPanel(labels, className);
    }

    private static JPanel createMaskPanel(String[] labels, String panelName) {
        JPanel tempPanel = new JPanel(new BorderLayout());
        JPanel leftTempPanel = new JPanel(new GridLayout(labels.length, 1));
        JPanel rightTempPanel = new JPanel(new GridLayout(labels.length, 1));
        
       
//      addButton.addActionListener(e -> {
//      JOptionPane.showMessageDialog(null, "Button geklickt: Add");
////      switch (viewName) {
////      case "Employees": 
//          
//          break;
//      case "Customers":
//          
//          break;
//      case "Orders": 
//	           int userId = Integer.parseInt(userIdField.getText());
//	           String orderDate = orderDateField.getText();
//	           String status = statusField.getText();
//	           double total = Double.parseDouble(totalField.getText());
//	           double subtotal = Double.parseDouble(subtotalField.getText());
//	           double tax = Double.parseDouble(taxField.getText());
//	           double discount = Double.parseDouble(discountField.getText());		          
//          break;
//      case "Products":
//          
//          break;
//      default:
//          // Handle unknown view
//          break;       
//  }
//  });



        JLabel[] customLabels = new JLabel[labels.length];
        JTextField[] textFields = new JTextField[labels.length];
        
        for (int i = 0; i < labels.length; i++) {
            customLabels[i] = createCustomLabel(labels[i]);
            leftTempPanel.add(customLabels[i]);

            JTextField textField = new JTextField();
            textFields[i] = textField;
            rightTempPanel.add(textField);

            final JTextField textFieldRef = textField; // Final machen, um Zugriff im ActionListener zu ermöglichen
            final String label = labels[i]; // Final machen, um im ActionListener darauf zuzugreifen

            
            
            textField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = textFieldRef.getText();
                    System.out.println("Eingabe in " + label + ": " + text);
                    // bring das object 
                    
                }
            });
        }
        	
       

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftTempPanel, rightTempPanel);
        splitPane.setDividerLocation(3.0 / 3.0);
        splitPane.setResizeWeight(0.33);
        tempPanel.add(splitPane, BorderLayout.CENTER);
        
        JButton openProductsButton = new JButton("Add Products");
        openProductsButton.setFont(new java.awt.Font("Book Antiqua", 0, 18));
        openProductsButton.addActionListener(e ->addProductsToOrderFrame(dataManagement));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(BLUE);
        buttonPanel.add(openProductsButton);
        tempPanel.add(buttonPanel, BorderLayout.SOUTH);

        
        return tempPanel;
    }
    
    
    
   
//    
//    static JButton createStyledButton(String text, Color bgColor) {
//        JButton button = new JButton(text);
//        button.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
//        button.setBackground(bgColor);
//        button.setForeground(Color.DARK_GRAY); 
//
//        return button;
//    }
//    

    private static JLabel createCustomLabel(String text) {
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
    //	dataManagement.findMaxId();
    //	int 1 = dataManagement.1; // Get the dynamically fetched 1
    	//int 1 = 1;
    	 
        JFrame newFrame = new JFrame("Product Drag and Drop");
        newFrame.setSize(600, 400);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setLayout(new BorderLayout());

        DefaultListModel<String> cartListModel = new DefaultListModel<>();
        
       
        JTree productTree = new JTree(productTreeModel(dataManagement));
        JList<String> cartList = new JList<>(cartListModel);
        
        ArrayList<Soap> existingSoaps = dataManagement.fetchProductsByOrderId(1);
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

        deleteButton.addActionListener(e -> {
            int selectedIndex = cartList.getSelectedIndex();
            if (selectedIndex != -1) {
                cartListModel.remove(selectedIndex);
            }
        });

        saveButton.addActionListener(e -> {
        	// saveCartContentsToDatabase(cartListModel, dataManagement, 1); // Assuming order ID is 1
        	 saveCartContentsToDatabase(cartListModel, dataManagement, 1, existingSoaps);
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

    
    private static void saveCartContentsToDatabase(DefaultListModel<String> cartListModel, Data_management dataManagement,int a, ArrayList<Soap> existingSoaps) {
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
                dataManagement.addOrderProductReference(1, soapId);
                System.out.println("title id "+ soapId+ "Product "+ productTitle);
            } else {
                System.out.println("Error: Could not find soap ID for title: " + productTitle);
            }
        }
        System.out.println("Cart contents saved to database.");
    }
    }



