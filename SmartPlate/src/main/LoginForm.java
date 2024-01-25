package main;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm {

	private JFrame frame;
	private JPanel firstPanel;
	private JPanel firstPanel_1;
	private JButton btnSignUp;
	private JButton btnExit;
	private JButton btnConnect;
    private JPanel loginPanel;
    private JPanel signUpPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    
    private JTextField txtSignUpName;
    private JTextField txtSignUpEmail;
    private JTextField txtSignUpUsername;
    private JPasswordField txtSignPassword;
    //FOR DATABASE
    DatabaseConnection base = new DatabaseConnection();

    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 940, 788);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        ImageIcon AppIcon = new ImageIcon("C:\\Users\\MSI\\eclipse-workspace\\SmartPlate\\src\\assets\\SmartPlateLogo1.png");
        frame.setIconImage(AppIcon.getImage());
  
        
        firstPanel = new JPanel(null);
        firstPanel.setLayout(null);
        firstPanel_1 = new ImagePanel("C:\\Users\\MSI\\eclipse-workspace\\SmartPlate\\src\\assets\\LogInFrame.png");
        frame.getContentPane().add(firstPanel_1);
        firstPanel_1.setLayout(null);
        
        JButton btnLogIn = new JButton("Log In");
        btnLogIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {   		
            	showLoginPanel();

        	}
        });
        btnLogIn.setBounds(304, 555, 93, 40);
        firstPanel_1.add(btnLogIn);
        
        btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showSignUpPanel();
        	}
        });
        btnSignUp.setBounds(504, 555, 93, 40);
        firstPanel_1.add(btnSignUp);
        
        btnExit = new JButton("EXIT");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                System.exit(0);
        		
                
        	}
        });
        btnExit.setBounds(809, 705, 93, 40);
        firstPanel_1.add(btnExit);
        
        btnConnect = new JButton("CONNECT WITH US");
        btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnConnect.setBounds(783, 128, 135, 58);
        firstPanel_1.add(btnConnect);
        
        
        //ADDITIONAL PANELS
        loginPanel = createLoginPanel();
        signUpPanel =  createSignUpPanel();
        
        
	}
	
	private JPanel createLoginPanel() {
        JPanel panel = new JPanel(null); // Use null layout
        panel = new ImagePanel("C:\\Users\\MSI\\eclipse-workspace\\SmartPlate\\src\\assets\\Signin.png");
        panel.setLayout(null);
        
        JButton signupButton = new JButton("SIGN-IN");
        signupButton.setBounds(386, 391, 144, 40);
        
        txtUsername = new JTextField();
        txtUsername.setText("");
        txtUsername.setColumns(10);
        txtUsername.setBounds(340, 287, 237, 40);
        
        final String userNplaceholder = "  username";
        // Add a focus listener to handle placeholder behavior
        txtUsername.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			    if (txtUsername.getText().equals(userNplaceholder)) {
                	txtUsername.setText("");
                }				
			}
			@Override
			public void focusLost(FocusEvent e) {
				   if (txtUsername.getText().isEmpty()) {
	                	txtUsername.setText(userNplaceholder);
	                	
	                }				
			}
        });
        
        txtPassword = new JPasswordField();
        txtPassword.setText("password");
        txtPassword.setColumns(10);
        txtPassword.setBounds(340, 340, 237, 40);
        
        final String userPassplaceholder = "  password";
        // Add a focus listener to handle placeholder behavior
        txtPassword.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			    if (txtPassword.getText().equals(userPassplaceholder)) {
			    	txtPassword.setText("");
                }				
			}
			@Override
			public void focusLost(FocusEvent e) {
				   if (txtPassword.getText().isEmpty()) {
					   txtPassword.setText(userPassplaceholder);
	                }				
			}
        });

        JButton logInButton = new JButton("Log In");
        logInButton.setBounds(127, 404, 93, 30);
        logInButton.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
            	
                
            }
        });

        btnExit = new JButton("BACK");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        		showMainPanel();
        	}
        });
        btnExit.setBounds(809, 705, 93, 40);
        
        panel.add(signupButton);
        panel.add(txtUsername);
        panel.add(txtPassword);
        panel.add(btnExit);
		return panel;
    }
	
    private JPanel createSignUpPanel() {
        JPanel panel = new JPanel(null); // Use a layout manager
        panel = new ImagePanel("C:\\Users\\MSI\\eclipse-workspace\\SmartPlate\\src\\assets\\SignUp.png");
        panel.setLayout(null);
        JButton BtnSignUp = new JButton("SIGN-UP");
        BtnSignUp.setBounds(394, 507, 144, 40);
        BtnSignUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	    String email = txtSignUpEmail.getText();
        	    String name = txtSignUpName.getText();
                String username = txtSignUpUsername.getText();
                String password = txtSignPassword.getText();

                
                if (!txtSignUpName.getText().equals("") && txtSignUpName.getText().matches("^[a-zA-Z\\s]+$") && !txtSignUpEmail.getText().equals("") ) {
            		if (!txtSignUpUsername.getText().equals("")) {
            	    	if (!txtSignPassword.getText().equals("")) {
            	    		
            	    		if (insertUser(email, name, username, password)) showLoginPanel();
            	    		else return;

            	    	  }else {
            	    		  JOptionPane.showMessageDialog(frame, "Please enter your password", "Error", JOptionPane.ERROR_MESSAGE);
                  	        return;
            	    	  }
            	    } else {
            	        JOptionPane.showMessageDialog(frame, "Please enter your username", "Error", JOptionPane.ERROR_MESSAGE);
            	        return;
            	    }
            	} else {
            	    JOptionPane.showMessageDialog(frame, "Please enter a valid name with letters only", "Error", JOptionPane.ERROR_MESSAGE);
            	    return;
            	}
        		
        		
                
        	}
        });
        
        
        txtSignUpName = new JTextField();
        txtSignUpName.setText("name");
        txtSignUpName.setBounds(348, 290, 237, 40);
        txtSignUpName.setColumns(10);
        
        txtSignUpEmail = new JTextField();
        txtSignUpEmail.setText("email");
        txtSignUpEmail.setBounds(348, 341, 237, 40);
        txtSignUpEmail.setColumns(10);
        
        txtSignUpUsername = new JTextField();
        txtSignUpUsername.setText("username");
        txtSignUpUsername.setColumns(10);
        txtSignUpUsername.setBounds(348, 392, 237, 40);
        
        txtSignPassword = new JPasswordField();
        txtSignPassword.setText("password");
        txtSignPassword.setColumns(10);
        txtSignPassword.setBounds(348, 445, 237, 40);
    
        JButton btnExitSignUp = new JButton("EXIT");
        btnExitSignUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                System.exit(0);
        		
                
        	}
        });
        btnExitSignUp.setBounds(809, 705, 93, 40);        
        
        
        panel.add(BtnSignUp);
        panel.add(txtSignUpEmail);
        panel.add(txtSignUpName);
        panel.add(txtSignUpUsername);
        panel.add(txtSignPassword);
        panel.add(btnExitSignUp);

        return panel;
    }
    
    private boolean insertUser(String email, String name, String username, String password) {
        try {
            // Use the DatabaseConnection class to obtain a connection
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            // Check if the username already exists
            if (isUsernameTaken(connection, username)) {
                JOptionPane.showMessageDialog(frame, "Username already exists, please choose another one.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            // Check if the email already exists
            if (isEmailTaken(connection, email)) {
                JOptionPane.showMessageDialog(frame, "Email already exists, please choose another one.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Prepare the SQL statement
            String sql = "INSERT INTO UserAccounts (email, name, username, password) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, password);

                // Execute the SQL statement
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "User registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    txtSignUpEmail.setText("");
                    txtSignUpName.setText("");
                    txtSignUpUsername.setText("");
                    txtSignPassword.setText("");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to Register", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; // Return false if an exception occurs or the insertion fails
    }

    //Method to check similar username
    private boolean isUsernameTaken(Connection connection, String username) throws SQLException {
        // Check if the username already exists in the database
        String query = "SELECT COUNT(*) FROM UserAccounts WHERE username=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
    
    private boolean isEmailTaken(Connection connection, String email) throws SQLException {
        // Check if the username already exists in the database
        String query = "SELECT COUNT(*) FROM UserAccounts WHERE email=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
	
    
    
    private void showLoginPanel() {
        frame.getContentPane().remove(firstPanel);
        frame.getContentPane().remove(firstPanel_1);
        frame.getContentPane().add(loginPanel);
        frame.getContentPane().remove(signUpPanel);
        frame.revalidate();
        frame.repaint();
        
                    
    }
    private void showSignUpPanel() {
        frame.getContentPane().remove(firstPanel);
        frame.getContentPane().remove(firstPanel_1);
        frame.getContentPane().remove(loginPanel);
        frame.getContentPane().add(signUpPanel);
        frame.revalidate();
        frame.repaint();
    }
    private void showMainPanel() {
        frame.getContentPane().remove(loginPanel);
        frame.getContentPane().add(firstPanel);
        frame.getContentPane().add(firstPanel_1);
        frame.revalidate();
        frame.repaint();
    }
    
    

	   
    private static class ImagePanel extends JPanel {
        private final ImageIcon imageIcon;

        public ImagePanel(String imagePath) {
            // Load the image
            this.imageIcon = new ImageIcon(imagePath);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the image as the background
            g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}