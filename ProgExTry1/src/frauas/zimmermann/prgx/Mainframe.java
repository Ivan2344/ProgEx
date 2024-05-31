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
	protected JPanel header, footer, right, left, center, namePanel, 
	innerCenterPanel, northCenterPanel, leftInnerCenterPanel, 
	middleInnerCenterPanel, rightInnerCenterPanel, wedummy, nsdummy, cLeftInnerCenterPanel;
	JLabel label;
	protected static final int LEFT_PANEL = 0, RIGHT_PANEL = 1,INNER_LEFT_PANEL = 2;
    protected static final Color BORDER_COLOR = Color.LIGHT_GRAY, LIGHT_BLUE = new Color (230,230,255);
    
    
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
		label.setForeground(LIGHT_BLUE);
		
		namePanel.add(label);
		header.add(namePanel);	
		frame.add(header,BorderLayout.NORTH);
	}
	
	public void setFooter() {
		footer = new JPanel();
		footer.setBackground(Color.BLACK);
		footer.setPreferredSize(new Dimension(1400,100));
		frame.add(footer,BorderLayout.SOUTH);
	}
	

	
	public void setCenter() {
	    center = new JPanel();
	    center.setLayout(new BorderLayout(0,0));
	    
	    northCenterPanel = new JPanel();
	    northCenterPanel.setBackground(Color.DARK_GRAY);
	    northCenterPanel.setLayout(new FlowLayout((FlowLayout.CENTER)));
	    northCenterPanel.setPreferredSize(new Dimension(1300, 50));
	    
//	    mainPanel = new JPanel();
//	    mainPanel.setBackground(Color.WHITE);
//	    mainPanel.setLayout(new GridLayout(1, 2));
//	    
//	    JPanel leftPanel = createPanelWithBorder(LEFT_PANEL);
//	    JPanel rightPanel = createPanelWithBorder(RIGHT_PANEL);
//	    
//	    JPanel firstLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
//	    JPanel secondLeftPanel = createPanelWithBorder(INNER_LEFT_PANEL);
//	    
//	    
//	    leftPanel.add(firstLeftPanel);
//	    leftPanel.add(secondLeftPanel);
//	    
//	    mainPanel.add(leftPanel);
//	    mainPanel.add(rightPanel);
	    
	    innerCenterPanel = new JPanel();
	    innerCenterPanel.setLayout(new BorderLayout(0,0));
	    innerCenterPanel.setBackground(LIGHT_BLUE);
	    //mainPanel.add(innerCenterPanel);
	    
	    center.add(northCenterPanel, BorderLayout.NORTH);
	    center.add(innerCenterPanel, BorderLayout.CENTER);
	   
	    frame.add(center, BorderLayout.CENTER);
	}


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
