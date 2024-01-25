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
import java.io.File;
import java.io.IOException;
import java.awt.event.FocusAdapter;
import java.awt.geom.RoundRectangle2D;



public class LoginForm {

	private JFrame frame;
	private JPanel firstPanel;
	private JPanel firstPanel_1;
	private JButton btnSignUp;
	private JButton btnExit;
	private JButton btnConnect;
    private JPanel loginPanel;
    private JPanel signUpPanel;
    private JPanel signupSucess;

    private JTextField txtUsername;
    private JTextField txtPassword;
    
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
        ImageIcon AppIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
        frame.setIconImage(AppIcon.getImage());
  
        
        
        firstPanel = new JPanel(null);
        firstPanel.setLayout(null);
        firstPanel_1 = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\LogInFrame.png");
        frame.getContentPane().add(firstPanel_1);
        firstPanel_1.setLayout(null);
        
        JButton btnLogIn = new JButton("");
        btnLogIn.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnLoginIcon.png"));
        btnLogIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {   		
            	showLoginPanel();

        	}
        });
        btnLogIn.setOpaque(false);
        btnLogIn.setContentAreaFilled(false);
        btnLogIn.setBorderPainted(false);
        btnLogIn.setBounds(299, 543, 106, 52);
        firstPanel_1.add(btnLogIn);
        
        btnSignUp = new JButton("");
        btnSignUp.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnSignupIcon.png"));
        btnSignUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showSignUpPanel();
        	}
        });
        btnSignUp.setBounds(493, 543, 106, 52);
        firstPanel_1.add(btnSignUp);
        btnSignUp.setOpaque(false);
        btnSignUp.setContentAreaFilled(false);
        btnSignUp.setBorderPainted(false);
        
        btnExit = new JButton("");
        btnExit.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnExitIcon.png"));
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                System.exit(0);
        		
                
        	}
        });
        btnExit.setBounds(809, 705, 106, 52);
        firstPanel_1.add(btnExit);
        btnExit.setOpaque(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        
        btnConnect = new JButton("");
        btnConnect.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnConnectIcon.png"));
        btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnConnect.setBounds(783, 128, 135, 66);
        firstPanel_1.add(btnConnect);
        btnConnect.setOpaque(false);
        btnConnect.setContentAreaFilled(false);
        btnConnect.setBorderPainted(false);
        
        
        //ADDITIONAL PANELS
        loginPanel = createLoginPanel();
        signUpPanel =  createSignUpPanel();
        signupSucess = createWelcomePanel();
        
	}
	
	private JPanel createLoginPanel() {
        JPanel panel = new JPanel(null); // Use null layout
        panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\Signin.png");
        panel.setLayout(null);
        
        JButton btnSignin = new JButton("SIGN-IN");
        btnSignin.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnSigninIcon.png"));
        btnSignin.setBounds(355, 510, 206, 60);
        btnSignin.setOpaque(false);
        btnSignin.setContentAreaFilled(false);
        btnSignin.setBorderPainted(false);
        btnSignin.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(((JPasswordField) txtPassword).getPassword());
                User authenticatedUser = UserAuthentication.authenticateUser(username, password);

                if (!username.isEmpty() && !password.isEmpty()) {
                    if (validateLogin(username, password)) {
                        // Successful login
                        // Perform any additional actions or navigate to the main application
                    	JOptionPane.showMessageDialog(frame, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                        SessionManager.createSession(authenticatedUser);
                        DashboardForm dashboard = new DashboardForm(authenticatedUser);
                    	dashboard.Show();
                    	frame.dispose();

                    } else {
                        // Invalid credentials
                        JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Missing username or password
                    JOptionPane.showMessageDialog(frame, "Please enter both username and password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        txtUsername = createRoundedTextField(10);
        txtUsername.setText("");
        txtUsername.setColumns(10);
        txtUsername.setBounds(340, 300, 237, 40);
        txtUsername.setForeground(new Color(255, 202, 110));
        try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\fonts\\FredokaOne-Regular.otf")).deriveFont(21f);
            // Register the font with the Graphics Environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            txtUsername.setFont(font);
            

		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        final String userNplaceholder = "  username";
        txtUsername.setText(userNplaceholder); // Set initial placeholder text
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
        
        JButton btnSignup = new JButton();
        btnSignup.setBounds(355, 680, 206, 60);
        btnSignup.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\signupIcon.png"));
        btnSignup.setOpaque(false);
        btnSignup.setContentAreaFilled(false);
        btnSignup.setBorderPainted(false);
        btnSignup.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		showSignUpPanel();				
			}
        	
        });

        
        txtPassword = createRoundedTextField1(10);
        ((JPasswordField) txtPassword).setEchoChar((char) 0); // Initially display normal text
        txtPassword.setColumns(10);
        txtPassword.setBounds(340, 380, 237, 40);
        txtPassword.setForeground(new Color(255, 202, 110));
        try {
   			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\fonts\\FredokaOne-Regular.otf")).deriveFont(21f);
               // Register the font with the Graphics Environment
               GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
               ge.registerFont(font);
               txtPassword.setFont(font);
               

   		} catch (FontFormatException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		} catch (IOException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		}
        final String userPassPlaceholder = "  password";
        txtPassword.setText(userPassPlaceholder); // Set initial placeholder text
        // Add a focus listener to handle placeholder behavior
        txtPassword.addFocusListener(new FocusAdapter() {
         @Override
         public void focusGained(FocusEvent e) {
             if (new String(((JPasswordField) txtPassword).getPassword()).equals(userPassPlaceholder)) {
                 txtPassword.setText("");
                 ((JPasswordField) txtPassword).setEchoChar('•'); // Use dots for password characters
             }
         }

         @Override
         public void focusLost(FocusEvent e) {
             if (new String(((JPasswordField) txtPassword).getPassword()).isEmpty()) {
                 ((JPasswordField) txtPassword).setEchoChar((char) 0); // Show normal text
                 txtPassword.setText(userPassPlaceholder);
             }
         }
     });


        btnExit = new JButton("BACK");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        		showMainPanel();
        	}
        });
        btnExit.setBounds(809, 705, 93, 40);
        
        panel.add(btnSignin);
        panel.add(btnSignup);
        panel.add(txtUsername);
        panel.add(txtPassword);
		return panel;
    }
	//FOR MAKING THE TEXTFIELD ROUND 
	  public static JTextField createRoundedTextField(int columns) {
	        JTextField textField = new JTextField(columns) {
	            private Shape shape;
	            @Override
	            protected void paintComponent(Graphics g) {
	                g.setColor(getBackground());
	                g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	                super.paintComponent(g);
	            }
	            @Override
	            protected void paintBorder(Graphics g) {
	                g.setColor(getForeground());
	                g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	            }
	            @Override
	            public boolean contains(int x, int y) {
	                if (shape == null || !shape.getBounds().equals(getBounds())) {
	                    shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	                }
	                return shape.contains(x, y);
	            }
	        };
	        textField.setOpaque(false);
	        textField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	        return textField;
	    }
	  public static JPasswordField createRoundedTextField1(int columns) {
		  JPasswordField textField = new JPasswordField(columns) {
	            private Shape shape;
	            @Override
	            protected void paintComponent(Graphics g) {
	                g.setColor(getBackground());
	                g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	                super.paintComponent(g);
	            }
	            @Override
	            protected void paintBorder(Graphics g) {
	                g.setColor(getForeground());
	                g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	            }
	            @Override
	            public boolean contains(int x, int y) {
	                if (shape == null || !shape.getBounds().equals(getBounds())) {
	                    shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	                }
	                return shape.contains(x, y);
	            }
	        };
	        textField.setOpaque(false);
	        textField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	        return textField;
	    }
	
	
	  
    private JPanel createSignUpPanel() {
        JPanel panel = new JPanel(null); // Use a layout manager
        panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\Signup.png");
        panel.setLayout(null);
        JButton BtnSignUp = new JButton("SIGN-UP");
        BtnSignUp.setBounds(365, 540, 206, 60);
        BtnSignUp.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\signupIcon.png"));
        BtnSignUp.setOpaque(false);
        BtnSignUp.setContentAreaFilled(false);
        BtnSignUp.setBorderPainted(false);
        BtnSignUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	    String email = txtSignUpEmail.getText();
        	    String name = txtSignUpName.getText();
                String username = txtSignUpUsername.getText();
                String password = txtSignPassword.getText();

                
                if (!txtSignUpName.getText().equals("") && txtSignUpName.getText().matches("^[a-zA-Z\\s]+$") && !txtSignUpEmail.getText().equals("") ) {
            		if (!txtSignUpUsername.getText().equals("")) {
            	    	if (!txtSignPassword.getText().equals("")) {
            	    		
            	    		if (insertUser(email, name, username, password)) showWelcomePanel();
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
            Connection connection = DatabaseConnection.getConnection();

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
	
    private boolean validateLogin(String username, String password) {
        try {
            // Use the DatabaseConnection class to obtain a connection
            Connection connection = DatabaseConnection.getConnection();

            // Prepare the SQL statement for login validation
            String sql = "SELECT * FROM UserAccounts WHERE username=? AND password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                // Execute the SQL statement
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If a row is returned, the login is valid
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private JPanel createWelcomePanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\signupSuccess.png");
        panel.setLayout(null);
        panel.setBounds(0, 0, 940, 788);
    	
    	return panel;
    }
    private void showWelcomePanel() {
        frame.getContentPane().remove(firstPanel);
        frame.getContentPane().remove(firstPanel_1);
        frame.getContentPane().remove(loginPanel);
        frame.getContentPane().remove(signUpPanel);
        frame.getContentPane().add(signupSucess);
        frame.revalidate();
        frame.repaint();
              
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
    
    

	   
    public static class ImagePanel extends JPanel {
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