package frauas.zimmermann.prgx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mainframe {
	JFrame frame; 
	JPanel header, footer, right, left, center, namePanel, 
	innerCenterPanel, northCenterPanel, leftInnerCenterPanel, 
	middleInnerCenterPanel, rightInnerCenterPanel, wedummy, nsdummy, cLeftInnerCenterPanel;
	JLabel label;
	private static final int LEFT_PANEL = 0;
    private static final int RIGHT_PANEL = 1;
    private static final int INNER_LEFT_PANEL = 2;
    private static final Color BORDER_COLOR = Color.LIGHT_GRAY;
    
	public Mainframe(){
		initializeFrame();
		setHeader();
		setFooter();
		setCenter();
		frame.setVisible(true);
	};
		
	public void initializeFrame() {
		frame = new JFrame("Your shop");
		frame.setSize(1500,1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
	}
	
	public void setHeader() {
		header = new JPanel();
		Color myColor = new Color(0,0,0);//rgb
		header.setBackground(myColor);
		header.setPreferredSize(new Dimension(1400,100));
		header.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		namePanel.setBackground(Color.BLACK);
		
		label = new JLabel("Your Shop");
		label.setFont(new Font("Arial",Font.BOLD,36));
		label.setForeground(Color.WHITE);
		
		namePanel.add(label);
		header.add(namePanel);	
		frame.add(header,BorderLayout.NORTH);
	}
	
	public void setFooter() {
		footer = new JPanel();
		footer.setBackground(Color.BLACK);
		footer.setPreferredSize(new Dimension(1400,50));
		frame.add(footer,BorderLayout.SOUTH);
	}
	
	
	public void setCenter() {
	    center = new JPanel();
	    center.setLayout(new BorderLayout(0,0));
	    
	    northCenterPanel = new JPanel();
	    northCenterPanel.setBackground(Color.GRAY);
	    northCenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    northCenterPanel.setPreferredSize(new Dimension(1300, 50));
	    
	    innerCenterPanel = new JPanel();
	    innerCenterPanel.setBackground(Color.WHITE);
	    innerCenterPanel.setLayout(new GridLayout(1, 2));
	    
	    JPanel leftPanel = createPanelWithBorder(LEFT_PANEL);
	    JPanel rightPanel = createPanelWithBorder(RIGHT_PANEL);
	    
	    JPanel firstLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
	    JPanel secondLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
	    
	    
	    leftPanel.add(firstLeftPanel);
	    leftPanel.add(secondLeftPanel);
	    
	    innerCenterPanel.add(leftPanel);
	    innerCenterPanel.add(rightPanel);
	    

	    center.add(northCenterPanel, BorderLayout.NORTH);
	    center.add(innerCenterPanel, BorderLayout.CENTER);
	    frame.add(center, BorderLayout.CENTER);
	}


	private JPanel createPanelWithBorder(int panelType) {
		JPanel panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    
	    int topBorderWidth, leftBorderWidth, bottomBorderWidth, rightBorderWidth;
	    Color borderColor;
	    
	    
	    switch(panelType){
	    	case RIGHT_PANEL: 
	    		topBorderWidth = 100;
	    		leftBorderWidth = 20;
	    		bottomBorderWidth = 50;
	    		rightBorderWidth = 20;
	    		break;
	    	case INNER_LEFT_PANEL:
				 topBorderWidth = 0; 
				 leftBorderWidth = 10;
			     bottomBorderWidth = 50;
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
	                borderColor = Color.BLACK;
	                break;
			}
		
		panel.setBorder(BorderFactory.createMatteBorder(
		        topBorderWidth, leftBorderWidth, bottomBorderWidth, rightBorderWidth, BORDER_COLOR));
		    panel.setLayout(new GridLayout(1, 2));
	    return panel;
	}
}
