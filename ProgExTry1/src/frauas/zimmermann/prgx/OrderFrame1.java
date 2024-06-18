package frauas.zimmermann.prgx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//public interface OrderFrame1 {
//	static final Color BLUE = new Color (230,230,255);
//		public default JPanel setOrderMask() {
//	    	JPanel tempPanel = new JPanel(new BorderLayout()); // Set BorderLayout to tempPanel
//	        JPanel leftTempPanel = new JPanel(new GridLayout(8, 1)); // GridLayout for left panel
//	        JPanel rightTempPanel = new JPanel(new GridLayout(8, 1)); // GridLayout for right panel
////	        
////	    	leftTempPanel.setLayout(new BoxLayout(leftTempPanel, BoxLayout.Y_AXIS));
//	    	leftTempPanel.add(createCustomLabel("ID"));
//	    	leftTempPanel.add(createCustomLabel("User ID"));
//	    	leftTempPanel.add(createCustomLabel("Order Date"));
//	    	leftTempPanel.add(createCustomLabel("Status"));
//	    	leftTempPanel.add(createCustomLabel("Total"));
//	    	leftTempPanel.add(createCustomLabel("Subtotal"));
//	    	leftTempPanel.add(createCustomLabel("Tax"));
//	    	leftTempPanel.add(createCustomLabel("Discount"));
//	    
////	    	rightTempPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
////	    	for (int i = 0; i < 8; i++) {
////	            rightTempPanel.add(new JTextField());
////	        }
//	        
//	    
//	        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftTempPanel, rightTempPanel);
//	        splitPane.setDividerLocation(1.0 / 3.0); 
//	        splitPane.setResizeWeight(0.33);
//	        tempPanel.add(splitPane, BorderLayout.CENTER);
//	    	return tempPanel;
//	    }
//
//		public default JPanel setEmployeeMask() {
//			JPanel tempPanel = new JPanel(new BorderLayout()); // Set BorderLayout to tempPanel
//	        JPanel leftTempPanel = new JPanel(new GridLayout(8, 1)); // GridLayout for left panel
//	        JPanel rightTempPanel = new JPanel(new GridLayout(8, 1)); // GridLayout for right panel
//	        
////	    	leftTempPanel.setLayout(new BoxLayout(leftTempPanel, BoxLayout.Y_AXIS));
//	    	leftTempPanel.add(createCustomLabel("ID"));
//	    	leftTempPanel.add(createCustomLabel("Name"));
//	    	leftTempPanel.add(createCustomLabel("Address"));
//	    	leftTempPanel.add(createCustomLabel("Email"));
//	    	leftTempPanel.add(createCustomLabel("Phone Number"));
//	    	leftTempPanel.add(createCustomLabel("Industry"));
//	    	leftTempPanel.add(createCustomLabel("Established Date"));
//	        
////	    	rightTempPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
////	    	for (int i = 0; i < 8; i++) {
////	            rightTempPanel.add(new JTextField());
////	        }
//	        
//	    
//	        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftTempPanel, rightTempPanel);
//	        splitPane.setDividerLocation(1.0 / 3.0); 
//	        splitPane.setResizeWeight(0.33);
//	        tempPanel.add(splitPane, BorderLayout.CENTER);
//	    	return tempPanel;
//			
//		}
//		public default JPanel setSoapMask() {
//	    	JPanel tempPanel = new JPanel(new BorderLayout()); // Set BorderLayout to tempPanel
//	        JPanel leftTempPanel = new JPanel(new GridLayout(8, 1)); // GridLayout for left panel
//	        JPanel rightTempPanel = new JPanel(new GridLayout(8, 1)); // GridLayout for right panel
//	        
////	    	leftTempPanel.setLayout(new BoxLayout(leftTempPanel, BoxLayout.Y_AXIS));
//	    	leftTempPanel.add(createCustomLabel("ID"));
//	    	leftTempPanel.add(createCustomLabel("EAN"));
//	    	leftTempPanel.add(createCustomLabel("Title"));
//	    	leftTempPanel.add(createCustomLabel("Category"));
//	    	leftTempPanel.add(createCustomLabel("Price"));
//	    	leftTempPanel.add(createCustomLabel("Created At"));
//	        
////	    	rightTempPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
////	    	for (int i = 0; i < 8; i++) {
////	            rightTempPanel.add(new JTextField());
////	        }
//	        
//	    
//	        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftTempPanel, rightTempPanel);
//	        splitPane.setDividerLocation(1.0 / 3.0); 
//	        splitPane.setResizeWeight(0.33);
//	        tempPanel.add(splitPane, BorderLayout.CENTER);
//	    	return tempPanel;
//	    }
//		public default JPanel setCustomerMask() {
//	    	JPanel tempPanel = new JPanel(new BorderLayout()); // Set BorderLayout to tempPanel
//	        JPanel leftTempPanel = new JPanel(new GridLayout(8, 1)); // GridLayout for left panel
//	        JPanel rightTempPanel = new JPanel(new GridLayout(8, 1)); // GridLayout for right panel
//	        
////	    	leftTempPanel.setLayout(new BoxLayout(leftTempPanel, BoxLayout.Y_AXIS));
//	    	leftTempPanel.add(createCustomLabel("ID"));
//	    	leftTempPanel.add(createCustomLabel("Name"));
//	    	leftTempPanel.add(createCustomLabel("Address"));
//	    	leftTempPanel.add(createCustomLabel("Email"));
//	    	leftTempPanel.add(createCustomLabel("Password"));
//	    	leftTempPanel.add(createCustomLabel("City"));
//	    	leftTempPanel.add(createCustomLabel("Birth Date"));
//	    	leftTempPanel.add(createCustomLabel("Created At"));
//	        
////	    	rightTempPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
//	    	rightTempPanel.add(new JTextField());
////	    	for (int i = 0; i < 8; i++) {
////	            rightTempPanel.add(new JTextField());
////	        }
//	        
//	    
//	        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftTempPanel, rightTempPanel);
//	        splitPane.setDividerLocation(1.0 / 3.0); 
//	        splitPane.setResizeWeight(0.33);
//	        tempPanel.add(splitPane, BorderLayout.CENTER);
//	    	return tempPanel;
//	    }
//		private static JLabel createCustomLabel(String text) {
//	        JLabel label =new JLabel(text);
//	        label.setFont(new java.awt.Font("Book Antiqua", 0, 18));
//	        label.setBackground(BLUE);
//	        label.setForeground(Color.DARK_GRAY);
//	        label.setOpaque(true); // Needed for background color to be visible
//	        return label;
//	    }
//
//	}
//
//
//
//
import java.awt.*;
import javax.swing.*;

import java.awt.*;
import javax.swing.*;

public interface OrderFrame1 {
	 Data_management dataManagement = new Data_management("root", "Mercury123");
    static final Color BLUE = new Color(230, 230, 255);
    static final Color BORDER = Color.LIGHT_GRAY;

    public default JPanel setOrderMask() {
        String[] labels = {"ID", "User ID", "Order Date", "Status", "Total", "Subtotal", "Tax", "Discount"};
        return createMaskPanel(labels);
    }

    public default JPanel setEmployeeMask() {
        String[] labels = {"ID", "Name", "Address", "Email", "Phone Number", "Industry", "Established Date"};
        return createMaskPanel(labels);
    }

    public default JPanel setSoapMask() {
        String[] labels = {"ID", "EAN", "Title", "Category", "Price", "Created At"};
        return createMaskPanel(labels);
    }

    public default JPanel setCustomerMask() {
    	String[] labels ={"ID", "Name", "Address", "Email", "Password", "City", "Birth Date", "Created At"};
        return createMaskPanel(labels);
    }

    private static JPanel createMaskPanel(String[] labels) {
        JPanel tempPanel = new JPanel(new BorderLayout());
        JPanel leftTempPanel = new JPanel(new GridLayout(labels.length, 1));
        JPanel rightTempPanel = new JPanel(new GridLayout(labels.length, 1));

        for (String label : labels) {
            leftTempPanel.add(createCustomLabel(label));
            rightTempPanel.add(new JTextField());
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftTempPanel, rightTempPanel);
        splitPane.setDividerLocation(3.0 / 3.0);
        splitPane.setResizeWeight(0.33);
        tempPanel.add(splitPane, BorderLayout.CENTER);
        
        JButton openFrameButton = new JButton("Add Products");
        openFrameButton.setFont(new java.awt.Font("Book Antiqua", 0, 18));
        openFrameButton.addActionListener(e ->createAndShowDragDropFrame(dataManagement));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(BLUE);
        buttonPanel.add(openFrameButton);
        tempPanel.add(buttonPanel, BorderLayout.SOUTH);

        return tempPanel;
    }

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

    private static void createAndShowDragDropFrame(Data_management dataManagement) {
        JFrame productFrame = new JFrame("My Products");
        productFrame.setSize(600, 600);
        productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productFrame.setLayout(new BorderLayout());

        DefaultListModel<String> productListModel = new DefaultListModel<>();
        DefaultListModel<String> cartListModel = new DefaultListModel<>();

        productListModel.addElement("Product 1");
        productListModel.addElement("Product 2");
        productListModel.addElement("Product 3");

        JList<String> productList = new JList<>(productListModel);
        JList<String> cartList = new JList<>(cartListModel);

        productList.setDragEnabled(true);
        productList.setTransferHandler(new ProductTransferHandler());

        cartList.setDropMode(DropMode.INSERT);
        cartList.setTransferHandler(new ProductTransferHandler());
        
//      Adding ListSelectionListener to the productList to add selected product to the cart
//		Add on click
        productList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedProduct = productList.getSelectedValue();
                if (selectedProduct != null && !cartListModel.contains(selectedProduct)) {
                    cartListModel.addElement(selectedProduct);
                }
            }
        });


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(productList), new JScrollPane(cartList));
        splitPane.setDividerLocation(3.0 / 3.0);
        
        // Add component listener to adjust the split pane divider location on frame resize always in the middle
        productFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                splitPane.setDividerLocation(0.5);
            }
        });

        productFrame.add(splitPane, BorderLayout.CENTER);

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
            System.out.println("Products saved: " + Collections.list(cartListModel.elements()));
        });

        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        productFrame.add(buttonPanel, BorderLayout.SOUTH);
        
        populateProductList(dataManagement, productListModel);

        productFrame.setVisible(true);
    }
    
    private static void populateProductList(Data_management dataManagement, DefaultListModel<String> productListModel) {
        ArrayList<Soap> soaps = dataManagement.fetchSoapsFromDatabase();
        for (Soap soap : soaps) {
            productListModel.addElement(soap.getTitle());
        }
    }

    static class ProductTransferHandler extends TransferHandler {
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
    
}
