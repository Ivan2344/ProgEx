package frauas.zimmermann.prgx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.Border;
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
        splitPane.setDividerLocation(1.0 / 3.0);
        splitPane.setResizeWeight(0.33);
        tempPanel.add(splitPane, BorderLayout.CENTER);

        return tempPanel;
    }

    private static JLabel createCustomLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new java.awt.Font("Book Antiqua", 0, 18));
        label.setBackground(BLUE);
        label.setForeground(Color.DARK_GRAY);
        label.setOpaque(true); // Needed for background color to be visible
        int topBorderWidth, leftBorderWidth, bottomBorderWidth, rightBorderWidth;
        topBorderWidth = 0;
        leftBorderWidth = 10;
        bottomBorderWidth = 10;
        rightBorderWidth = 10;
        Border border = BorderFactory.createLineBorder(Color.gray);
//        Border border =BorderFactory.createMatteBorder(
//                topBorderWidth, leftBorderWidth, bottomBorderWidth, rightBorderWidth, BORDER);
        label.setBorder(border);
        return label;
    }

}
