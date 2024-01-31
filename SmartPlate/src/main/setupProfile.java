package main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class setupProfile {

	private JFrame frame;
	private JPanel chefsKnowledgePanel, JPanel, chefsAllergyPanel;
	static final User String = null;
    private User currentUser;
    DatabaseConnection base = new DatabaseConnection();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setupProfile window = new setupProfile(String);
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
	public setupProfile(User user) {
		initialize();
	    // Fetch authenticated user from SessionManager
	    currentUser = SessionManager.getCurrentUser();
	    // Check if the user is authenticated before displaying user info
	    if (currentUser != null) {
	        fetchinguserInformation(); // Move fetchinguserInformation() here
	    } else {
	        // Handle the case when the user is not authenticated
	        System.out.println("User not authenticated");
	    }
	}
    private class ImagePanel extends JPanel {
        private BufferedImage image;

        public ImagePanel(String imagePath) {
            try {
                image = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
    //For setting the cooking level
    private void updateCookingLevel(String level) {
        String username = currentUser.getUsername();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE UserAccounts SET cooking_level=? WHERE username=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, level);
                preparedStatement.setString(2, username);

                //FOR TROUBLE SHOOTING IF FETCHING IS CORRECT
                int updatedRows = preparedStatement.executeUpdate();
                if (updatedRows > 0) {
                } else {
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void confirmAndUpdateLevel(String level, String message) {
        int response = JOptionPane.showConfirmDialog(frame, message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            updateCookingLevel(level);
            JOptionPane.showMessageDialog(frame, "You are now a " + level + "!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void confirmAndUpdateAllergy(String allergy, String message) {
        int response = JOptionPane.showConfirmDialog(frame, message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            try {
				updateAllergyStatus(allergy);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            JOptionPane.showMessageDialog(frame, "Your " + allergy + " allergy has been updated.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
 // Method to reset all allergies
    private void resetAllergies() throws SQLException {
        String username = currentUser.getUsername();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE UserAccounts SET allergy_milk = false, allergy_eggs = false, allergy_fish = false, allergy_crabs = false, allergy_peanut = false, allergy_grain = false, allergy_nuts = false, allergy_shellfish = false WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            	preparedStatement.setString(1, username); // Replace with actual username

            	preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error resetting allergy information.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }
 // Method to update allergy status in the database
    private void updateAllergyStatus(String allergy) throws SQLException {
        String username = currentUser.getUsername();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String columnName;

            switch (allergy.toLowerCase()) {
                case "milk":
                    columnName = "allergy_milk";
                    break;
                case "fish":
                    columnName = "allergy_fish";
                    break;
                case "eggs":
                    columnName = "allergy_eggs";
                    break;
                case "crab":
                    columnName = "allergy_crabs";
                    break;
                case "peanut":
                    columnName = "allergy_peanut";
                    break;
                case "grain":
                    columnName = "allergy_grain";
                    break;
                case "shellfish":
                    columnName = "allergy_shellfish";
                    break;
                case "nuts":
                    columnName = "allergy_nuts";
                    break;
                case "coffee":
                    columnName = "allergy_coffee";
                    break;
                default:
                    throw new IllegalArgumentException("Unknown allergy: " + allergy);
            }

            String sql = "UPDATE UserAccounts SET " + columnName + " = ? WHERE username = ?";
        	try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            	preparedStatement.setBoolean(1, true);
            	preparedStatement.setString(2, username); // Replace with actual username

            	preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error updating allergy information.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        }
            
    }
    private void fetchinguserInformation() {
        String username = currentUser.getUsername();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM UserAccounts WHERE username=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve user information from the result set
                        String name = resultSet.getString("name");

                        // Now you have the user information
                        chefsAllergyPanel = createAllergiesPanel(username);

                        
                        // You can update the UI or perform other actions with this information
                    } else {
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 940, 788);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        ImageIcon AppIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
        frame.setIconImage(AppIcon.getImage());
        
        chefsKnowledgePanel = createchefsKnowledgePanel();
        //chefsAllergyPanel = createAllergiesPanel();
        frame.getContentPane().add(chefsKnowledgePanel);


	}
	
	private JPanel createAllergiesPanel(String username) {

        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\AllergiesImage.png");
        panel.setLayout(null);
        panel.setBounds(0, 0, 940, 788);
        
        
        JButton btnMilk = new JButton("");
        btnMilk.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnMilkIcon.png"));
        btnMilk.setBounds(102, 366, 87, 93);
        btnMilk.setOpaque(false);
        btnMilk.setContentAreaFilled(false);
        btnMilk.setBorderPainted(false);
        

        
        JButton btnEgg = new JButton("");
        btnEgg.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnEggIcon.png"));
        btnEgg.setOpaque(false);
        btnEgg.setContentAreaFilled(false);
        btnEgg.setBorderPainted(false);
        btnEgg.setBounds(242, 366, 87, 93);
        
        JButton btnFish = new JButton("");
        btnFish.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnFishIcon.png"));
        btnFish.setOpaque(false);
        btnFish.setContentAreaFilled(false);
        btnFish.setBorderPainted(false);
        btnFish.setBounds(406, 366, 87, 93);
        
        JButton btnCrab = new JButton("");
        btnCrab.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnCrabIcon.png"));
        btnCrab.setOpaque(false);
        btnCrab.setContentAreaFilled(false);
        btnCrab.setBorderPainted(false);
        btnCrab.setBounds(560, 366, 87, 93);
        
        JButton btnPeanut = new JButton("");
        btnPeanut.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnPeanutIcon.png"));
        btnPeanut.setOpaque(false);
        btnPeanut.setContentAreaFilled(false);
        btnPeanut.setBorderPainted(false);
        btnPeanut.setBounds(728, 366, 87, 93);
        
        JButton btnGrain = new JButton("");
        btnGrain.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnGrainIcon.png"));
        btnGrain.setOpaque(false);
        btnGrain.setContentAreaFilled(false);
        btnGrain.setBorderPainted(false);
        btnGrain.setBounds(195, 519, 87, 93);
        
        JButton btnCoffee = new JButton("");
        btnCoffee.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnCoffeeIcon.png"));
        btnCoffee.setOpaque(false);
        btnCoffee.setContentAreaFilled(false);
        btnCoffee.setBorderPainted(false);
        btnCoffee.setBounds(354, 519, 87, 93);
        
        JButton btnNuts = new JButton("");
        btnNuts.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnNutsIcon.png"));
        btnNuts.setOpaque(false);
        btnNuts.setContentAreaFilled(false);
        btnNuts.setBorderPainted(false);
        btnNuts.setBounds(479, 519, 87, 93);
        
        JButton btnShellfish = new JButton("");
        btnShellfish.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnShellfishIcon.png"));
        btnShellfish.setOpaque(false);
        btnShellfish.setContentAreaFilled(false);
        btnShellfish.setBorderPainted(false);
        btnShellfish.setBounds(627, 519, 87, 93);


        
        JButton btnReadyCook = new JButton("");
        btnReadyCook.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnReadyCookIcon.png"));
        btnReadyCook.setBounds(332, 665, 294, 83);
        btnReadyCook.setOpaque(false);
        btnReadyCook.setContentAreaFilled(false);
        btnReadyCook.setBorderPainted(false);
        btnReadyCook.addActionListener(e -> {
            User authenticatedUser = UserAuthentication.authenticateUser1(username);
            int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to continue?",
                                                         "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
            	DashboardForm dashboard = new DashboardForm(authenticatedUser);
            	dashboard.Show();
            	frame.dispose();
            }
        });
        
        JButton btnReset = new JButton("");
        btnReset.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnResetIcon.png"));
        btnReset.setOpaque(false);
        btnReset.setContentAreaFilled(false);
        btnReset.setBorderPainted(false);
        btnReset.setBounds(787, 256, 106, 52);
        btnReset.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to reset your allergies?",
                                                         "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
            	try {
					resetAllergies();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
                JOptionPane.showMessageDialog(frame, "Your allergies has been reset.", 
                                              "Reset Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnMilk.addActionListener(e -> confirmAndUpdateAllergy("milk", "Are you sure you have a milk allergy?"));
        btnEgg.addActionListener(e -> confirmAndUpdateAllergy("eggs", "Are you sure you have a egg allergy?"));
        btnFish.addActionListener(e -> confirmAndUpdateAllergy("fish", "Are you sure you have a fish allergy?"));
        btnCrab.addActionListener(e -> confirmAndUpdateAllergy("crab", "Are you sure you have a crab or shrimp allergy?"));
        btnPeanut.addActionListener(e -> confirmAndUpdateAllergy("peanut", "Are you sure you have a peanut allergy?"));
        btnGrain.addActionListener(e -> confirmAndUpdateAllergy("grain", "Are you sure you have grains allergy?"));
        btnCoffee.addActionListener(e -> confirmAndUpdateAllergy("coffee", "Are you sure you have a coffee allergy?"));
        btnNuts.addActionListener(e -> confirmAndUpdateAllergy("nuts", "Are you sure you have nuts allergy?"));
        btnShellfish.addActionListener(e -> confirmAndUpdateAllergy("shellfish", "Are you sure you have a shellfish allergy?"));
        panel.add(btnMilk);
        panel.add(btnEgg);
        panel.add(btnFish);
        panel.add(btnCrab);
        panel.add(btnPeanut);
        panel.add(btnGrain);
        panel.add(btnCoffee);
        panel.add(btnNuts);
        panel.add(btnShellfish);
        panel.add(btnReadyCook);
        panel.add(btnReset);
        
        

		return panel;
	}
	
	
	private JPanel createchefsKnowledgePanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\chefsKnowledgeImage.png");
        panel.setLayout(null);
        panel.setBounds(0, 0, 940, 788);
        
        JButton btnBeginner = new JButton("");
        btnBeginner.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnBeginnerIcon.png"));
        btnBeginner.setBounds(41, 623, 243, 66);
        btnBeginner.setOpaque(false);
        btnBeginner.setContentAreaFilled(false);
        btnBeginner.setBorderPainted(false);
        
        JButton btnIntermediate = new JButton("");
        btnIntermediate.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnIntermediateIcon.png"));
        btnIntermediate.setOpaque(false);
        btnIntermediate.setContentAreaFilled(false);
        btnIntermediate.setBorderPainted(false);
        btnIntermediate.setBounds(334, 623, 243, 66);
        
        JButton btnAdvanced = new JButton("");
        btnAdvanced.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnAdvancedIcon.png"));
        btnAdvanced.setOpaque(false);
        btnAdvanced.setContentAreaFilled(false);
        btnAdvanced.setBorderPainted(false);
        btnAdvanced.setBounds(640, 623, 243, 66);
        
        JButton btnNext = new JButton("");
        btnNext.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnNextIcon.png"));
        btnNext.setBounds(794, 710, 106, 52);
        btnNext.setOpaque(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setBorderPainted(false);
        btnNext.addActionListener( e -> {
        	  int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to continue?",
                      "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        	  
        	  if (response == JOptionPane.YES_OPTION) {
        		  showAllergiesPanel();
              }
        });
        
        JButton btnReset = new JButton("");
        btnReset.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnResetIcon.png"));
        btnReset.setOpaque(false);
        btnReset.setContentAreaFilled(false);
        btnReset.setBorderPainted(false);
        btnReset.setBounds(41, 188, 106, 52);
        btnReset.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to reset your cooking level?",
                                                         "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                updateCookingLevel("beginner");
                JOptionPane.showMessageDialog(frame, "Your cooking level has been reset to beginner.", 
                                              "Reset Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        btnBeginner.addActionListener(e -> confirmAndUpdateLevel("beginner", "Are you sure you want to choose beginner?"));
        btnIntermediate.addActionListener(e -> confirmAndUpdateLevel("intermediate", "Are you sure you want to choose intermediate?"));
        btnAdvanced.addActionListener(e -> confirmAndUpdateLevel("advanced", "Are you sure you want to choose advanced?"));
        
        panel.add(btnBeginner);
        panel.add(btnIntermediate);
        panel.add(btnAdvanced);
        panel.add(btnNext);
        panel.add(btnReset);
        
        return panel;
	}
    private void showAllergiesPanel() {
        frame.getContentPane().remove(chefsKnowledgePanel);
        frame.getContentPane().add(chefsAllergyPanel);
        frame.revalidate();
        frame.repaint();
    }
    
	public void Show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setupProfile window = new setupProfile(currentUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}

}
