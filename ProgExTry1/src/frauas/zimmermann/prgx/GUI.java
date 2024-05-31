package frauas.zimmermann.prgx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class GUI extends Mainframe {
	JFrame innerFrame;
	DefaultListModel <String> model = new DefaultListModel();
	JPanel[] panelList;
	String[] menuItems;
	
	GUI(){
		super();
		initializeGui();
	}
	public void initializeGui(){
		 String[] menuItems = {"Orders", "Products", "Employees", "Customers", "NEW Order", "NEW Order"};
	        createMenuList(menuItems);
		
		northCenterPanel.revalidate();
		northCenterPanel.repaint();
		
	}
//	public void createMenuButton(String buttonName, Color color) {
////		JPanel buttonPanel = new JPanel();
//		JButton menuButton = new JButton(buttonName);
//		
//		menuButton.setBackground(color);
//		northCenterPanel.add(menuButton);
//	}
	 public void createMenuList(String[] items) {
		 JList<String> menuList = new JList<>(items);
	        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        menuList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	        menuList.setVisibleRowCount(1); // Show all items
	        menuList.setFont(new Font("Arial", Font.PLAIN, 18));
	        menuList.setBackground(Color.DARK_GRAY);
	        menuList.setForeground(LIGHT_BLUE);

	        // Calculate the width for each cell dynamically
	        int cellWidth = northCenterPanel.getPreferredSize().width / items.length;
	        menuList.setFixedCellWidth(cellWidth);
	        menuList.setFixedCellHeight(50);

	        // Center the text horizontally
	        DefaultListCellRenderer renderer = (DefaultListCellRenderer) menuList.getCellRenderer();
	        renderer.setHorizontalAlignment(SwingConstants.CENTER);

	        northCenterPanel.add(menuList);

		    northCenterPanel.add(menuList);
	    }
	 public void createMainPanel() {
		 
	  JPanel container = new JPanel();
	  container.setLayout(null);
	  for (int i = 0; i < menuItems.length;i++) {
			panelList[i]= setMainPanel(i);
			panelList[i].setVisible(false);
			panelList[i].setBounds(0, 0, 1275, 1000);
			center.add(panelList[i]);
		}
		frame.add(center, BorderLayout.CENTER);
	}
	 
	 public JPanel setMainPanel(int i) {
		 JPanel tempPanel = new JPanel();
		 
		 return tempPanel;
	 }
//    mainPanel = new JPanel();
//    mainPanel.setBackground(Color.WHITE);
//    mainPanel.setLayout(new GridLayout(1, 2));
//    
//    JPanel leftPanel = createPanelWithBorder(LEFT_PANEL);
//    JPanel rightPanel = createPanelWithBorder(RIGHT_PANEL);
//    
//    JPanel firstLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
//    JPanel secondLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
//    
//    
//    leftPanel.add(firstLeftPanel);
//    leftPanel.add(secondLeftPanel);
//    
//    mainPanel.add(leftPanel);
//    mainPanel.add(rightPanel);
	 
	private JPanel createPanelWithBorder(int panelType) {
		JPanel panel = new JPanel();
		Color myColor = new Color(230,230,255);
	    panel.setBackground(myColor);
	    
	    int topBorderWidth, leftBorderWidth, bottomBorderWidth, rightBorderWidth;
	    
	    
	    switch(panelType){
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
	    	case LEFT_PANEL:
					topBorderWidth = 100; 
				 leftBorderWidth = 10;
			     bottomBorderWidth = 0;
			     rightBorderWidth = 10;
				 break;
				default:
				 topBorderWidth = 0; 
	                leftBorderWidth = 0;
	                bottomBorderWidth = 0;
	                rightBorderWidth = 0;
	                break;
			}
		
		panel.setBorder(BorderFactory.createMatteBorder(
		        topBorderWidth, leftBorderWidth, bottomBorderWidth, rightBorderWidth, BORDER_COLOR));
		    panel.setLayout(new GridLayout(1, 2));
	    return panel;
	}
}
