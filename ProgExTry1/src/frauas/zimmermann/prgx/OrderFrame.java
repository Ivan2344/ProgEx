package frauas.zimmermann.prgx;


	import java.awt.BorderLayout;
	import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
	import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
	import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.mysql.cj.log.Log;

	public class OrderFrame  {// implements ActionListener{	
		protected int count;
		protected static int limit = 9;
		protected static int offset = 0;
		JFrame frame; 
		JTextField customerEnt;
		JTextField employeeEnt,textField ;
		JPanel header, footer, right, left, center,text,jTreePanel, tabelPanel;
		DefaultTreeModel model ;
		JButton namePage, orderButton, employeeButton, productButton, customerButton;
		Color headerColor = new java.awt.Color(184, 235, 209);//new java.awt.Color(71, 20, 46)

		/**
	     * Constructs an object and initializes the main frame for the Drone Simulator GUI.
	     * It sets up the header, footer, right, left, and center panels along with navigation buttons.
	     */
		public OrderFrame() {
			initializeFrame();
			setupHeader();
			setupFooter();
			setupRightPanel();
			setupLeftPanel();
			setupCenterPanel();	
//			setUpTextField();
			JTree();
			frame.setVisible(true);
			
		}
		
		private void  initializeFrame() {
			frame = new JFrame("Main Page");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1500, 1000);
			frame.setLocationRelativeTo(null);
			frame.setLayout(new BorderLayout());
		}
		private void setupHeader() {
			header = new JPanel();
			header.setBackground(headerColor);
			header.setPreferredSize(new Dimension(1400,120));
			header.setLayout(new BorderLayout(0,5));
			JPanel namePanel = new JPanel();
			namePanel.setBackground(headerColor);
			//namePage = getButton("Soap Paradise", 25);
			JLabel nameShop = new JLabel();
			nameShop.setBackground(headerColor);
			nameShop.setFont(new java.awt.Font("Felix Titling", 0, 36));
			nameShop.setText("Your Shop");
			namePanel.add(nameShop);
			header.add(namePanel, BorderLayout.NORTH);	
			header.add(setupOption_Panel(),BorderLayout.CENTER);
			frame.add(header,BorderLayout.NORTH);
		}
		
		private JPanel setupOption_Panel() {
			JPanel optionPanel = new JPanel();
			optionPanel.setBackground(headerColor);
			optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
			customerButton = getButton("Customer",20);
			optionPanel.add(customerButton);
			productButton = getButton("Products",20);
			optionPanel.add(productButton);
			employeeButton = getButton("Employees",20);
			optionPanel.add(employeeButton);
			orderButton = getButton("Orders", 20);
			optionPanel.add(orderButton);
			return optionPanel;
		}

		private JButton getButton(String name, int nameSize) {
			JButton nameButton = new JButton(name);
			nameButton.setFont(new java.awt.Font("Amiri", 0, 24));
			nameButton.setPreferredSize(new Dimension(250,40));
			//nameButton.addActionListener(this);
			nameButton.setBackground(headerColor);
			//nameButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
			nameButton.setFocusable(false);
			return nameButton;	
		}
		
		private JPanel JTree() {
		JPanel jtree = new JPanel();
		DefaultMutableTreeNode products = new DefaultMutableTreeNode("Products");
		DefaultMutableTreeNode Soaps = new DefaultMutableTreeNode("Soaps");
		DefaultMutableTreeNode Handwash = new DefaultMutableTreeNode("Handwash");
		DefaultMutableTreeNode ShampooBars = new DefaultMutableTreeNode("Shampoo Bars");
		DefaultMutableTreeNode BathBombs = new DefaultMutableTreeNode("Bath Bombs");
		products.add(Soaps);
		products.add(Handwash);
		products.add(ShampooBars);
		products.add(BathBombs);
		Soaps.add(new DefaultMutableTreeNode("Charcoal"));
		Soaps.add(new DefaultMutableTreeNode("Aloe Vera"));
		Soaps.add(new DefaultMutableTreeNode("Botanical"));
		Soaps.add(new DefaultMutableTreeNode("Tumeric"));
		Handwash.add(new DefaultMutableTreeNode("Cucumber"));
		Handwash.add(new DefaultMutableTreeNode("Freshness"));
		Handwash.add(new DefaultMutableTreeNode("Roses"));
		Handwash.add(new DefaultMutableTreeNode("Tea tree"));
		ShampooBars.add(new DefaultMutableTreeNode("Cucumber"));
		ShampooBars.add(new DefaultMutableTreeNode("Freshness"));
		ShampooBars.add(new DefaultMutableTreeNode("Roses"));
		ShampooBars.add(new DefaultMutableTreeNode("Tea tree"));
		BathBombs.add(new DefaultMutableTreeNode("Cucumber"));
		BathBombs.add(new DefaultMutableTreeNode("Freshness"));
		BathBombs.add(new DefaultMutableTreeNode("Roses"));
		BathBombs.add(new DefaultMutableTreeNode("Tea tree"));
		
		model = new DefaultTreeModel(products);
		
		javax.swing.JTree tree = new javax.swing.JTree(model);
		  tree.setBorder(new javax.swing.border.MatteBorder(null));
	        tree.setFont(new java.awt.Font("Amiri", 0, 18)); // NOI18N
		tree.setEditable(true);
		// Add a TreeSelectionListener to handle node selection
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    String selectedNodeInfo = selectedNode.toString();
                    System.out.println("Selected Node: " + selectedNodeInfo);
                    // You can save the selectedNodeInfo to a file or database here
                }
            }

	
        });
		
		JScrollPane treeScrollPane = new JScrollPane(tree);
		treeScrollPane.setPreferredSize(new Dimension(300, 400));
		jtree.add(treeScrollPane,BorderLayout.CENTER);
		return jtree;
		
		}
	
		private void setupFooter() {
			footer = new JPanel();
			footer.setBackground(headerColor);
			footer.setPreferredSize(new Dimension(1400,50));
			frame.add(footer,BorderLayout.SOUTH);
		}
		
		private void setupRightPanel() {
			right = new JPanel();
			right.setBackground(Color.red);
			right.setPreferredSize(new Dimension(10,850));
			frame.add(right,BorderLayout.EAST);
		}
		
		private void setupLeftPanel() {
			left = new JPanel();
			left.setBackground(new java.awt.Color(219,193,207));
			left.setPreferredSize(new Dimension(10,850)); 
			frame.add(left,BorderLayout.WEST);
		}
		
//		
//		  jComboBox1.setFont(new java.awt.Font("Amiri", 0, 18)); // NOI18N
//	        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Order 1", "Order 2", "Order 3", "Order 4", "Order 5", "Order 6", "Order 7", "Order 8", "Order 9", "Order 10" }));
//	        jComboBox1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
//	        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
//	            public void actionPerformed(java.awt.event.ActionEvent evt) {
//	                jComboBox1ActionPerformed(evt);
//	            }
//	        });
		
		private void setupCenterPanel() {
		    center = new JPanel();
		    text = new JPanel();
		    jTreePanel = new JPanel();
		    tabelPanel = new JPanel();
		    center.setBackground(new java.awt.Color(254, 238, 205)); 
		    center.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
		    text.setPreferredSize(new Dimension(375, 400));
		    text.setLayout(new FlowLayout(FlowLayout.LEADING,20,10)); // Set BoxLayout Y_AXIS for vertical layout
		    text.setBackground(new java.awt.Color(254, 238, 205));
		    tabelPanel.add(setTable());
		    jTreePanel.add(JTree());
		    customerEnt = setUpTextField("Enter Customer name");
		    employeeEnt = setUpTextField("Enter Employee name");
		    text.add(employeeEnt);
		    text.add(customerEnt);
		    center.add(text);
		    center.add(jTreePanel);
		    center.add(tabelPanel);
		    frame.add(center, BorderLayout.CENTER);
		    
		}

       // jComboBox1 = new javax.swing.JComboBox<>();
        private JTextField setUpTextField(String name) {
			textField = new JTextField(20);
			textField.setText(name);
			textField .setFont(new java.awt.Font("Amiri", 0, 18)); // NOI18N
			textField .setForeground(new java.awt.Color(102, 102, 102));
			textField.setMargin(new Insets(5,10,5,10));
			textField.setToolTipText(name);
			 textField.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
//		            	customerEntActionPerformed(evt);
		            }
		        });	

			return textField;
        }
        
        private JScrollPane setTable() {
        	

        	        String[] columnNames = {
        	            "", ""
        	        };

        	        // Create data for the table
        	        Object[][] data = {
        	            {"ID", "Data 1-2"},
        	            {"Customer ID", "Data 2-2"},
        	            {"Status", "Data 3-2"},
        	            {"Sub-Total", "Data 1-10"},
        	            {"Tax", "Data 1-8"},
        	            {"Total","Data 1-6"},
        	            { "Discount", "Data 1-4"},
        	            { "Order Date", "Data 1-4"},
        	            { "Product ID", "Data 1-4mwefm;oqiwekf;oqwekf;opqkf;oqpwk;fok;q;rogkqw"}
        	        };

        	        // Create the table with the data and column names
        	        JTable table = new JTable(data, columnNames);

        	        // Set preferred size for the table to enable horizontal scrolling
        	        table.setRowHeight(30);
        	       // table.set
        	        table.setFont(new java.awt.Font("Amiri", 0, 20));
        	       table.setPreferredSize(new Dimension(375, 400));
        	        table.setPreferredScrollableViewportSize(new Dimension(375, 400));
        	        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        	        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        	        table.setFillsViewportHeight(true);

        	        // Add the table to a scroll pane
        	        JScrollPane scrollPane = new JScrollPane(table);

        	        // Set the horizontal scroll bar policy
        	        scrollPane.setPreferredSize(new Dimension(375, 400));
        	     //  scrollPane.setHorizontalScrollBarPolicy(JScrollPane.);

        	     
        	return scrollPane;
        }
		
		/**
	     * Draws a border around a JPanel with a specified name and size.
	     *
	     * @param panel    The JPanel to which a border is added.
	     * @param name     The name of the border.
	     * @param nameSize The font size of the name.
	     */
		protected void drawBorder(JPanel panel, String name, int nameSize) {
			panel.setBorder(
					BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new java.awt.Color(184, 235, 209), 5),
							name,TitledBorder.CENTER,
							TitledBorder.CENTER,new Font(null,Font.BOLD,nameSize)));
		}   
		
		/**
	     * Handles the actionPerformed event, responding to button clicks and navigating between different pages.
	     *
	     * @param e The ActionEvent representing the action performed.
	     */
//		@Override
//		public void actionPerformed(ActionEvent e) {
//
//			if(e.getSource()== namePage) {
//				Log.getLog("User goes to Dashboard");
//				
//				frame.dispose();
//			//	new Dashboard();
//	 		}
//			if(e.getSource()== orderButton) {
//				Log.getLog("User goes to Drone Page");
//				frame.dispose();
//			
////				new DronePage();
//			}
//			if(e.getSource()== productButton) {
//				Log.getLog("User goes to Drone Type Page");
//				frame.dispose();
//			
////				new DroneTypePage();
//			}
//			if(e.getSource()== employeeButton) {
//				Log.getLog("User goes to Flight Dynamic Page");
//				frame.dispose();
////				new FlightDynamics();
//			}
//			if(e.getSource()== textField) {
//				customerEntActionPerformed(e);
////				new FlightDynamics();
//			}
//			
//			if(e.getSource()== employeeEnt) {
//				employeeEntActionPerformed(e);
////				new FlightDynamics();
//			}
//			
//		}
//		
//		 private void customerEntActionPerformed(java.awt.event.ActionEvent e) {                                            
//		        // TODO add your handling code here:
//		    }                                           
//
//		    private void employeeEntActionPerformed(java.awt.event.ActionEvent e) {                                            
//		        // TODO add your handling code here:
//		    }     
//		
//	
	}

