package frauas.zimmermann.prgx;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginMask extends Mainframe {
	
	LoginMask(){
		super();
        loggui();
    }

	/**
     * Method: loggui
     * Description: Sets up the graphical user interface for the login
     */
    public void loggui() {
    	JPanel MainPanel = new JPanel(new BorderLayout());
    	
    	setNorth(MainPanel);
    	setSouth(MainPanel);
    	setLeft(MainPanel);
    	setRight(MainPanel);
    	
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        loginPanel.setPreferredSize(new Dimension(200, 50));

        JLabel userNameLabel = new JLabel("Username:");
        userNameLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 25));
        userNameLabel.setHorizontalAlignment(JLabel.CENTER);
        userNameLabel.setVerticalAlignment(JLabel.CENTER);
        
        JTextField userNameField = new JTextField(10);
        userNameField.setFont(new Font("Book Antiqua", Font.PLAIN, 25));
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 25));
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setVerticalAlignment(JLabel.CENTER);
        
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setFont(new Font("Book Antiqua", Font.PLAIN, 25));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(LIGHT_BLUE);
        loginButton.setFont(new Font("Book Antiqua", Font.PLAIN, 20)); 
        
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String username = userNameField.getText();
                String password = new String(passwordField.getPassword());
                login(username, password);
            }
        });


        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	String username = userNameField.getText();
                    String password = new String(passwordField.getPassword());
                    login(username, password);
                }
            }
        });

       
        loginPanel.add(userNameLabel);
        loginPanel.add(userNameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        MainPanel.add(loginPanel, BorderLayout.CENTER);
        innerCenterPanel.add(MainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
    
    private void setNorth(JPanel Panel) {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.LIGHT_GRAY);
        northPanel.setPreferredSize(new Dimension(0, 130)); // Breite einstellen
        Panel.add(northPanel, BorderLayout.NORTH);
    }
    
    private void setSouth(JPanel Panel) {
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.LIGHT_GRAY);
        southPanel.setPreferredSize(new Dimension(0, 130)); // Breite einstellen
        Panel.add(southPanel, BorderLayout.SOUTH);
    }
    
    private void setLeft(JPanel Panel) {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setPreferredSize(new Dimension(400, 0)); // Breite einstellen
        Panel.add(leftPanel, BorderLayout.WEST);
    }

    private void setRight(JPanel Panel) {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setPreferredSize(new Dimension(400, 0)); // Breite einstellen
        Panel.add(rightPanel, BorderLayout.EAST);
    }
    
    /**
     * Method: login
     * @param username the username entered by the user
     * @param password the password entered by the user
     * Description: Authenticates the user and proceeds to the main GUI if successful.
     */
    public void login(String username, String password) {

        Create_Shema schema = new Create_Shema(username, password);
        boolean success = schema.CreateDB();
        schema.setSch("jdbc:mysql://localhost:3306/SEIFENdemo2");
        schema.InsertDemoValues();

        if (success) {
            GUI new2 = new GUI(username, password);
            frame.setVisible(false); 
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password for database.");
        }
    }
}