package frauas.zimmermann.prgx;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI extends Mainframe {
    JPanel[] panelList;
    String[] menuItems = {"Orders", "Products", "Employees", "Customers"};
    private JPanel mainPanel, employerPanel;
    
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
        JPanel rightPanel = createPanelWithBorder(RIGHT_PANEL);        
        JPanel firstLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
		JPanel secondLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
		
		
        JLabel firstLabel = new JLabel(String.valueOf(menuItem), SwingConstants.CENTER);
        firstLeftPanel.add(firstLabel);
        JLabel secondLabel = new JLabel("Second Left Panel", SwingConstants.CENTER);
        secondLeftPanel.add(secondLabel);
        JLabel rightLabel = new JLabel("Right Panel", SwingConstants.CENTER);        
        rightPanel.add(rightLabel);
        setEmployerPanel(rightPanel);
        
        
        
        leftPanel.add(firstLeftPanel);
        leftPanel.add(secondLeftPanel);
        tempPanel.add(leftPanel);
        tempPanel.add(rightPanel);
        
//        setContents(menuItem);
        
        return tempPanel;
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
    
    private void setEmployerPanel(JPanel rightPanel) {
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);
        
        JPanel employerPanel = new JPanel();
        employerPanel.setLayout(new BorderLayout());
        employerPanel.setPreferredSize(new Dimension(200, 200));

        setUpper(employerPanel); // Header hinzufügen

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 10)); // Eine Spalte für die Daten
        addHeaders(mainPanel); // Header hinzufügen
        addEmployerData(mainPanel); // Daten aus der Datenbank hinzufügen

        JScrollPane scrollPane = new JScrollPane(mainPanel); // Falls die Daten zu groß sind, wird ein Scrollpane benötigt
        employerPanel.add(scrollPane, BorderLayout.CENTER);

        rightPanel.add(employerPanel, BorderLayout.CENTER);
    }

    private void addEmployerData(JPanel mainPanel) {
        Data_management dataManagement = new Data_management("username", "password");
        ArrayList<Employer> employers = dataManagement.fetchEmployersFromDatabase();

        for (Employer employer : employers) {
        	
            JPanel dataPanel = new JPanel(new GridLayout(1, 0, 10, 10)); // Eine Zeile für jedes Datenelement
            dataPanel.add(createValueLabel(String.valueOf(employer.getEmployerId())));
            dataPanel.add(createValueLabel(employer.getEmployerName()));
            dataPanel.add(createValueLabel(employer.getAddress()));
            dataPanel.add(createValueLabel(employer.getEmail()));
            dataPanel.add(createValueLabel(employer.getPhoneNumber()));
            dataPanel.add(createValueLabel(employer.getIndustry()));
            dataPanel.add(createValueLabel(String.valueOf(employer.getEstablishedDate())));
            mainPanel.add(dataPanel);
        }
    }

    private JLabel createValueLabel(String value) {
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font(null, Font.PLAIN, 20));
        return valueLabel;
    }

    private void setUpper(JPanel employerPanel) {
        JPanel upper = new JPanel();
        upper.setBackground(Color.LIGHT_GRAY);
        upper.setPreferredSize(new Dimension(0, 50));
        upper.add(new JLabel("Employer Database"));
        employerPanel.add(upper, BorderLayout.NORTH);
    }

    private void addHeaders(JPanel mainPanel) {
        JPanel headerPanel = new JPanel(new GridLayout(1, 0, 10, 10)); // Eine Zeile für die Header
        headerPanel.add(new JLabel("ID"));
        headerPanel.add(new JLabel("Name"));
        headerPanel.add(new JLabel("Address"));
        headerPanel.add(new JLabel("Email"));
        headerPanel.add(new JLabel("Phone Number"));
        headerPanel.add(new JLabel("Industry"));
        headerPanel.add(new JLabel("Established Date"));
        mainPanel.add(headerPanel);
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
