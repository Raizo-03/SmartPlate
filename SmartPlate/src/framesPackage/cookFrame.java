package framesPackage;

import main.*;
import main.SessionManager;
import main.DatabaseConnection;
import main.User;
import main.UserAuthentication;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class cookFrame {

	private JFrame frame;
	static final User String = null;
    private User currentUser;
    private JLabel lblName;
    DatabaseConnection base = new DatabaseConnection();
    private String name;
    private JPanel panel, dietaryPanel, budgetPanel, tofuPanel, tofuPanel2, chopsueyPanel, chopsueyPanel2;
	private JTextField txtFieldBugdet;
	private int price = 0;
	private boolean low_calorie = false, vegetarian = false, low_sodium = false,
	high_protein, heart_healthy = false, balanced_nutrition = false, low_carb = false,gluten_free = false, vitamins_minerals_focused = false;
	private JRadioButton rdHighProtein, rdHeart, rdVegetarian, rdCalorie, rdBalanced, rdSodium, rdCarbs, rdGluten, rdVita;
	JProgressBar progressBar;

    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cookFrame window = new cookFrame(String);
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
	public cookFrame(User user) {
		initialize();
	    currentUser = SessionManager.getCurrentUser();
	    // Check if the user is authenticated before displaying user info
	    if (currentUser != null) {
	        fetchinguserInformation(); // Move fetchinguserInformation() here
	    } else {
	        // Handle the case when the user is not authenticated
	        System.out.println("User not authenticated");
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
                        name = resultSet.getString("name");

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
        frame.setUndecorated(true);
        ImageIcon AppIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
        frame.setIconImage(AppIcon.getImage());
        frame.getContentPane().setLayout(null);
        
        
        panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\dietaryPanelImage.png");
        panel.setBounds(0, 0, 940, 788);
        panel.setLayout(null);
		
        
         rdHighProtein = new JRadioButton("  High Protein");
        rdHighProtein.setBackground(new Color(164, 229, 255)); // #A4E5FF
        rdHighProtein.setForeground(new Color(229, 175, 55));
        rdHighProtein.setBounds(46, 163, 263, 45);
        rdHighProtein.setBorder(null);
        rdHighProtein.setFocusPainted(false);
        Icon proteinDefault = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\proteinDefaultIcon.png"); // Custom radio button icon
        Icon proteinSelected = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\proteinSelectedIcon.png");     
        rdHighProtein.setIcon(proteinDefault); // Default state
        rdHighProtein.setSelectedIcon(proteinSelected); // Selected state
        
        
         rdHeart = new JRadioButton("  Heart-Healthy");
        rdHeart.setForeground(new Color(255, 102, 196));
        rdHeart.setFocusPainted(false);
        rdHeart.setBorder(null);
        rdHeart.setBackground(new Color(164, 229, 255));
        rdHeart.setBounds(24, 263, 285, 45);
        Icon heartDefault = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\heartDefaultIcon.png"); // Custom radio button icon
        Icon heartSelected = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\heartSelectedIcon.png"); // Custom selected radio button icon
        rdHeart.setIcon(heartDefault); // Default state
        rdHeart.setSelectedIcon(heartSelected); // Selected stat
        
         rdVegetarian = new JRadioButton("  Vegetarian");
        rdVegetarian.setForeground(new Color(126, 217, 87));
        rdVegetarian.setFont(new Font("Dialog", Font.PLAIN, 21));
        rdVegetarian.setFocusPainted(false);
        rdVegetarian.setBorder(null);
        rdVegetarian.setBackground(new Color(164, 229, 255));
        rdVegetarian.setBounds(53, 360, 234, 45);
        Icon vegeDefault = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\vegeDefaultIcon.png"); // Custom radio button icon
        Icon vegeSelected = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\vegeSelectedIcon.png"); // Custom selected radio button icon
        rdVegetarian.setIcon(vegeDefault); // Default state
        rdVegetarian.setSelectedIcon(vegeSelected); // Selected stat
        
         rdCalorie = new JRadioButton("  Low-Calorie");
        rdCalorie.setForeground(new Color(218, 52, 34));
        rdCalorie.setFocusPainted(false);
        rdCalorie.setBorder(null);
        rdCalorie.setBackground(new Color(164, 229, 255));
        rdCalorie.setBounds(57, 464, 263, 45);
        Icon calorieDefault = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\calorieDefaultIcon.png"); // Custom radio button icon
        Icon calorieSelected = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\calorieSelectedIcon.png"); // Custom selected radio button icon
        rdCalorie.setIcon(calorieDefault); // Default state
        rdCalorie.setSelectedIcon(calorieSelected); // Selected stat
        
         rdBalanced = new JRadioButton(" Balanced Nutrition");
        rdBalanced.setForeground(new Color(0, 133, 200));
        rdBalanced.setFocusPainted(false);
        rdBalanced.setBorder(null);
        rdBalanced.setBackground(new Color(164, 229, 255));
        rdBalanced.setBounds(282, 693, 334, 45);
        Icon balancedDefaultIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\balancedDefaultIcon.png"); // Custom radio button icon
        Icon balancedSelectedIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\balancedSelectedIcon.png"); // Custom selected radio button icon
        rdBalanced.setIcon(balancedDefaultIcon); // Default state
        rdBalanced.setSelectedIcon(balancedSelectedIcon); // Selected stat
        
         rdSodium = new JRadioButton("  Low-Sodium");
        rdSodium.setForeground(new Color(57, 36, 19));
        rdSodium.setFocusPainted(false);
        rdSodium.setBorder(null);
        rdSodium.setBackground(new Color(164, 229, 255));
        rdSodium.setBounds(629, 163, 263, 45);
        Icon sodiumDefaultIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\sodiumDefaultIcon.png"); // Custom radio button icon
        Icon sodiumSelectedIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\sodiumSelectedIcon.png"); // Custom selected radio button icon
        rdSodium.setIcon(sodiumDefaultIcon); // Default state
        rdSodium.setSelectedIcon(sodiumSelectedIcon); // Selected stat
        
         rdCarbs = new JRadioButton("  Low-Carbs");
        rdCarbs.setForeground(new Color(255, 145, 77));
        rdCarbs.setFocusPainted(false);
        rdCarbs.setBorder(null);
        rdCarbs.setBackground(new Color(164, 229, 255));
        rdCarbs.setBounds(629, 263, 247, 45);
        Icon carbsDefaultIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\carbDefaultIcon.png"); // Custom radio button icon
        Icon carbsSelectedIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\carbSelectedIcon.png"); // Custom selected radio button icon
        rdCarbs.setIcon(carbsDefaultIcon); // Default state
        rdCarbs.setSelectedIcon(carbsSelectedIcon); // Selected stat
        
         rdGluten = new JRadioButton("  Gluten-Free");
        rdGluten.setForeground(new Color(94, 23, 235));
        rdGluten.setFocusPainted(false);
        rdGluten.setBorder(null);
        rdGluten.setBackground(new Color(164, 229, 255));
        rdGluten.setBounds(615, 360, 247, 45);
        Icon glutenDefaultIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\glutenDefaultIcon.png"); // Custom radio button icon
        Icon glutenSelectedIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\glutenSelectedIcon.png"); // Custom selected radio button icon
        rdGluten.setIcon(glutenDefaultIcon); // Default state
        rdGluten.setSelectedIcon(glutenSelectedIcon); // Selected stat
        
         rdVita = new JRadioButton("<html> Vitamins and<br/>Minerals Focused </html>");
        rdVita.setForeground(new Color(78, 127, 28));
        rdVita.setFocusPainted(false);
        rdVita.setBorder(null);
        rdVita.setBackground(new Color(164, 229, 255));
        rdVita.setBounds(595, 456, 279, 114);
        Icon vitaDefaultIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\vitaDefaultIcon.png"); // Custom radio button icon
        Icon vitaSelectedIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\vitaSelectedIcon.png"); // Custom selected radio button icon
        rdVita.setIcon(vitaDefaultIcon); // Default state
        rdVita.setSelectedIcon(vitaSelectedIcon); // Selected stat
        
        //FONT
        try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\fonts\\Shrikhand-Regular.otf")).deriveFont(28f);
            // Register the font with the Graphics Environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            rdHighProtein.setFont(font);
            rdHeart.setFont(font);
            rdVegetarian.setFont(font);
            rdCalorie.setFont(font);
            rdSodium.setFont(font);
            rdCarbs.setFont(font);
            rdGluten.setFont(font);
            rdBalanced.setFont(font);
            rdVita.setFont(font);

		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        JButton btnNext = new JButton("");
        btnNext.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnNextIcon.png"));
        btnNext.setOpaque(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setBorderPainted(false);
        btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    high_protein = rdHighProtein.isSelected();
			    heart_healthy = rdHeart.isSelected();
			    vegetarian = rdVegetarian.isSelected();
			    low_calorie = rdCalorie.isSelected();
			    balanced_nutrition = rdBalanced.isSelected();
			    low_sodium = rdSodium.isSelected();
			    low_carb = rdCarbs.isSelected();
			    gluten_free = rdGluten.isSelected();
			    vitamins_minerals_focused = rdVita.isSelected();			
		        
		        
		        budgetPanel = createBudgetPanel(low_calorie, vegetarian, low_sodium,
		    			 high_protein, heart_healthy, balanced_nutrition,  low_carb, gluten_free, vitamins_minerals_focused);
		        
		        frame.getContentPane().remove(panel);
		        frame.getContentPane().add(budgetPanel);
			    frame.revalidate();
		        frame.repaint();

			}
        	
        });
        btnNext.setBounds(786, 704, 106, 52);
 
        
        JButton btnSave = new JButton("");
        btnSave.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnSaveIcon.png"));
        btnSave.setOpaque(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setBorderPainted(false);
        btnSave.setBounds(656, 704, 106, 52);  
        btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdHighProtein.isSelected()) {
					high_protein = true;
				}else if(rdHeart.isSelected()) {
					heart_healthy = true;
				}else if(rdVegetarian.isSelected()) {
					vegetarian = true;
				}else if(rdCalorie.isSelected()) {
					low_calorie = true;
				}else if(rdBalanced.isSelected()) {
					balanced_nutrition = true;
				}else if(rdSodium.isSelected()) {
					low_sodium = true;
				}else if(rdCarbs.isSelected()) {
					low_carb = true;
				}else if(rdGluten.isSelected()) {
					gluten_free = true;
				}else if(rdVita.isSelected()) {
					vitamins_minerals_focused = true;
				}				
			}
        	
        });
        
        panel.add(rdVita); 
        panel.add(rdGluten);
        panel.add(rdCarbs);
        panel.add(rdSodium);
        panel.add(rdBalanced);
        panel.add(rdCalorie);
        panel.add(rdVegetarian);
        panel.add(rdHeart);
        panel.add(btnSave);
        panel.add(rdHighProtein);
        panel.add(btnNext);
        
        frame.getContentPane().add(panel);
        
   
	}
	private JPanel createBudgetPanel(boolean low_calorie,boolean vegetarian,boolean low_sodium,
			boolean high_protein, boolean heart_healthy,boolean balanced_nutrition, boolean low_carb,boolean gluten_free,boolean vitamins_minerals_focused) {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\budgetPanelImage.png");
        panel.setBounds(0, 0, 940, 788);
        panel.setLayout(null);
        
        
        JButton btnDecrease = new JButton("");
        btnDecrease.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDecreaseIcon.png"));
        btnDecrease.setBounds(191, 250, 56, 45);
        btnDecrease.setOpaque(false);
        btnDecrease.setContentAreaFilled(false);
        btnDecrease.setBorderPainted(false);
        btnDecrease.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                String currentValue = txtFieldBugdet.getText();
                int baseVal;
                
                try {
                    // Convert the current value to an integer
                    baseVal = Integer.parseInt(currentValue);
                } catch (NumberFormatException ex) {
                    // Handle the case where the text field does not contain a valid integer
                    baseVal = 0; // or any default value you deem appropriate
                }
                
                baseVal--;
                if(baseVal < 0) {
                	JOptionPane.showMessageDialog(frame, "Cannot decrease input anymore. Limit is 0.", "Error", JOptionPane.ERROR_MESSAGE);
                	btnDecrease.disable();
                	baseVal = 0;
                }
                String newValue = (java.lang.String.valueOf(baseVal));
                txtFieldBugdet.setText(newValue);
			}
        	
        });
        JButton btnNext = new JButton("");
        btnNext.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnNextIcon.png"));
        btnNext.setOpaque(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setBorderPainted(false);
        btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				java.lang.String priceT = txtFieldBugdet.getText();
				int price = Integer.parseInt(priceT);
		        if (high_protein && low_carb && vegetarian && price >= 200) {
		            // Create the modal JDialog for the loading screen
		            final JDialog loadingDialog = new JDialog();
		            loadingDialog.setTitle("Suggesting...");
		            loadingDialog.setModal(true); // Set modal to block user input to other windows
		            loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Prevent closing
		            loadingDialog.setLocation(790, 500); // This positions the dialog at coordinates (100, 100) on the screen.
		            ImageIcon icon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
		            // Set the icon for the dialog
		            loadingDialog.setIconImage(icon.getImage());
		            // Create a progress bar with indeterminate mode
		            JProgressBar progressBar = new JProgressBar();
		            progressBar.setPreferredSize(new Dimension(350, 20)); // Set preferred size
		            progressBar.setIndeterminate(true);
		            progressBar.setForeground(new Color(0, 168, 89)); // Green color
		            loadingDialog.getContentPane().add(progressBar); // Add progress bar to dialog
		            loadingDialog.pack(); // Adjust dialog size to fit its contents

		            // Use a SwingWorker to perform background task
		            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		                @Override
		                protected Void doInBackground() throws Exception {
		                    // Perform time-consuming task here
		                    Thread.sleep(2000); // Simulate task duration
		                    return null;
		                }

		                @Override
		                protected void done() {
		                    // Close the loading screen once the task is done
		                    loadingDialog.dispose();

		                    // Proceed with your next operations, such as showing another panel
		                    tofuPanel = createTofuPanel();
		                    // Assuming 'frame' is the main JFrame of your application
		                    frame.getContentPane().add(tofuPanel);
		                    frame.getContentPane().removeAll(); // Remove all components
		                    frame.getContentPane().add(tofuPanel); // Add new panel
		                    frame.revalidate();
		                    frame.repaint();
		                }
		            };

		            worker.execute(); // Start the task execution
		            loadingDialog.setVisible(true); // Display the loading dialog; blocks here until task is done
		        }else if(vitamins_minerals_focused == true && balanced_nutrition == true && heart_healthy == true && price >= 100) {
		            // Create the modal JDialog for the loading screen
		            final JDialog loadingDialog = new JDialog();
		            loadingDialog.setTitle("Suggesting...");
		            loadingDialog.setModal(true); // Set modal to block user input to other windows
		            loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Prevent closing
		            loadingDialog.setLocation(790, 500); // This positions the dialog at coordinates (100, 100) on the screen.
		            ImageIcon icon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
		            // Set the icon for the dialog
		            loadingDialog.setIconImage(icon.getImage());
		            // Create a progress bar with indeterminate mode
		            JProgressBar progressBar = new JProgressBar();
		            progressBar.setPreferredSize(new Dimension(350, 25)); // Set preferred size
		            progressBar.setIndeterminate(true);
		            progressBar.setForeground(new Color(0, 168, 89)); // Green color
		            loadingDialog.getContentPane().add(progressBar); // Add progress bar to dialog
		            loadingDialog.pack(); // Adjust dialog size to fit its contents

		            // Use a SwingWorker to perform background task
		            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		                @Override
		                protected Void doInBackground() throws Exception {
		                    // Perform time-consuming task here
		                    Thread.sleep(2000); // Simulate task duration
		                    return null;
		                }

		                @Override
		                protected void done() {
		                    // Close the loading screen once the task is done
		                    loadingDialog.dispose();
		                    chopsueyPanel = createChopsueyPanel();
							frame.getContentPane().add(chopsueyPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
		                }
		            };

		            worker.execute(); // Start the task execution
		            loadingDialog.setVisible(true); // Display the loading dialog; blocks here until task is done
				}else if(heart_healthy == true) {
					
				}
				}
        	
        });
        btnNext.setBounds(786, 704, 106, 52);
        
        
        JButton btnSave = new JButton("");
        btnSave.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnSaveIcon.png"));
        btnSave.setOpaque(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setBorderPainted(false);
        btnSave.setBounds(656, 704, 106, 52);
        
        
        
        
        JButton btnIncrease = new JButton("");
        btnIncrease.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnIncreaseIcon.png"));
        btnIncrease.setBounds(692, 250, 56, 45);
        btnIncrease.setOpaque(false);
        btnIncrease.setContentAreaFilled(false);
        btnIncrease.setBorderPainted(false);
        btnIncrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Read the current value from the text field
                String currentValue = txtFieldBugdet.getText();
                int baseVal;
                
                try {
                    // Convert the current value to an integer
                    baseVal = Integer.parseInt(currentValue);
                } catch (NumberFormatException ex) {
                    // Handle the case where the text field does not contain a valid integer
                    baseVal = 0; // or any default value you deem appropriate
                }
                
                // Increment the value
                baseVal++;

                // Convert the incremented value back to a string
                String newValue = (java.lang.String.valueOf(baseVal));

                // Set the updated value back to the text field
                txtFieldBugdet.setText(newValue);
            }
        });

        
        
        txtFieldBugdet = new JTextField();
        txtFieldBugdet.setBounds(351, 250, 211, 53);
        frame.getContentPane().add(txtFieldBugdet);
        txtFieldBugdet.setColumns(10);
        txtFieldBugdet.setBorder(BorderFactory.createEmptyBorder());
        txtFieldBugdet.setForeground(new Color(241, 143, 46));
        txtFieldBugdet.setBackground(new Color(252, 246, 219));
        txtFieldBugdet.setHorizontalAlignment(JTextField.CENTER);
        
        
        try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\fonts\\FredokaOne-Regular.otf")).deriveFont(30f);
            // Register the font with the Graphics Environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            txtFieldBugdet.setFont(font);
            

		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        final String userNplaceholder = "Enter Budget";
        txtFieldBugdet.setText(userNplaceholder); // Set initial placeholder text
        // Add a focus listener to handle placeholder behavior
        txtFieldBugdet.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			    if (txtFieldBugdet.getText().equals(userNplaceholder)) {
			    	txtFieldBugdet.setText("");
                }				
			}
			@Override
			public void focusLost(FocusEvent e) {
				   if (txtFieldBugdet.getText().isEmpty()) {
					   txtFieldBugdet.setText(userNplaceholder);
	                	
	                }				
			}
        });
        
		panel.add(btnIncrease);
		panel.add(btnDecrease);
		panel.add(btnSave);
		panel.add(btnNext);
		panel.add(txtFieldBugdet);

		
		return panel;
	}
	
	private JPanel createTofuPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\tofuImage.png");
        panel.setBounds(0, 0, 940, 788);
        panel.setLayout(null);
        
        JButton btnSelect = new JButton("");
        btnSelect.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnSelectIcon.png"));
        btnSelect.setBounds(581, 702, 194, 58);
        btnSelect.setOpaque(false);
        btnSelect.setContentAreaFilled(false);
        btnSelect.setBorderPainted(false);
        btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tofuPanel2 = createTofuPanel2();
				frame.getContentPane().add(tofuPanel2);
				frame.getContentPane().remove(tofuPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	private JPanel createTofuPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\tofuImage2.png");
        panel.setBounds(0, 0, 940, 788);
        panel.setLayout(null);
        
        
        
        JTextField textField = new JTextField();
        textField.setBounds(312, 368, 98, 53);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        textField.setColumns(10);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setForeground(new Color(241, 143, 46));
        textField.setBackground(new Color(252, 246, 219));
        textField.setHorizontalAlignment(JTextField.CENTER);
        
        
        try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\fonts\\FredokaOne-Regular.otf")).deriveFont(35f);
            // Register the font with the Graphics Environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            textField.setFont(font);
            

		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        final String userNplaceholder = "1";
        textField.setText(userNplaceholder); // Set initial placeholder text
        
        JButton btnIncrease = new JButton("");
        btnIncrease.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnIncrease.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnIncreaseIcon2.png"));
        btnIncrease.setBounds(418, 372, 40, 40);
        btnIncrease.setOpaque(false);
        btnIncrease.setContentAreaFilled(false);
        btnIncrease.setBorderPainted(false);
        
        panel.add(btnIncrease);
        
        JButton btnDecrease = new JButton("");
        btnDecrease.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDecreaseIcon2.png"));
        btnDecrease.setBounds(265, 372, 40, 40);
        btnDecrease.setOpaque(false);
        btnDecrease.setContentAreaFilled(false);
        btnDecrease.setBorderPainted(false);


        panel.add(btnDecrease);
        
        JButton btnCook = new JButton("");
        btnCook.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnCokIcon.png"));
        btnCook.setBounds(492, 377, 151, 44);
        btnCook.setOpaque(false);
        btnCook.setContentAreaFilled(false);
        btnCook.setBorderPainted(false);
        btnCook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		        final JDialog loadingDialog = new JDialog(frame, "Cooking...", true);
		        loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		        loadingDialog.setLocationRelativeTo(frame); // Center relative to the frame
	            loadingDialog.setLocation(790, 500); // This positions the dialog at coordinates (100, 100) on the screen.
	            loadingDialog.getContentPane().setBackground(Color.BLACK); // Set the background color of the content pane to black.

		        ImageIcon icon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
		        loadingDialog.setIconImage(icon.getImage());

		        // Set the dialog layout
		        loadingDialog.setLayout(new BorderLayout());

		        JProgressBar progressBar = new JProgressBar();
		        progressBar.setIndeterminate(true);
		        progressBar.setPreferredSize(new Dimension(350, 30));
		        progressBar.setForeground(new Color(0, 168, 89));
		        loadingDialog.add(progressBar, BorderLayout.SOUTH);

		        // Path to the GIF
		        ImageIcon gifIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\cookingGif.gif");
		        JLabel gifLabel = new JLabel(gifIcon);
		        loadingDialog.add(gifLabel, BorderLayout.NORTH);

		        loadingDialog.pack();

		        // Use a SwingWorker to perform background task
		        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		            @Override
		            protected Void doInBackground() throws Exception {
		                // Perform time-consuming task here
		                Thread.sleep(2000); // Simulate task duration
		                return null;
		            }

		            @Override
		            protected void done() {
		                // Close the loading dialog
		                loadingDialog.dispose();

		                // Proceed with your next operations...
		            }
		        };

		        worker.execute(); // Start the task execution

		        // Display the loading dialog; it blocks here until disposed
		        loadingDialog.setVisible(true);
		    }
        	
        });
        panel.add(btnCook);
        
        // Add a focus listener to handle placeholder behavior
        textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			    if (textField.getText().equals(userNplaceholder)) {
			    	textField.setText("");
                }				
			}
			@Override
			public void focusLost(FocusEvent e) {
				   if (textField.getText().isEmpty()) {
					   textField.setText(userNplaceholder);
	                	
	                }				
			}
        });
        
        btnDecrease.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                String currentValue = textField.getText();
                int baseVal;
                
                try {
                    // Convert the current value to an integer
                    baseVal = Integer.parseInt(currentValue);
                } catch (NumberFormatException ex) {
                    // Handle the case where the text field does not contain a valid integer
                    baseVal = 0; // or any default value you deem appropriate
                }
                
                baseVal--;
                if(baseVal < 0) {
                	JOptionPane.showMessageDialog(frame, "Cannot decrease input anymore. Limit is 0.", "Error", JOptionPane.ERROR_MESSAGE);
                	btnDecrease.disable();
                	baseVal = 0;
                }
                String newValue = (java.lang.String.valueOf(baseVal));
                textField.setText(newValue);
			}
        	
        });
        btnIncrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Read the current value from the text field
                String currentValue = textField.getText();
                int baseVal;
                
                try {
                    // Convert the current value to an integer
                    baseVal = Integer.parseInt(currentValue);
                } catch (NumberFormatException ex) {
                    // Handle the case where the text field does not contain a valid integer
                    baseVal = 0; // or any default value you deem appropriate
                }
                
                // Increment the value
                baseVal++;

                // Convert the incremented value back to a string
                String newValue = (java.lang.String.valueOf(baseVal));

                // Set the updated value back to the text field
                textField.setText(newValue);
            }
        });
        

        
        panel.add(textField);
        panel.add(btnIncrease);
        panel.add(btnDecrease);
        panel.add(btnCook);

        
        
        return panel;
	}
	
	private JPanel createChopsueyPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\chopsueyImage.png");
        panel.setBounds(0, 0, 940, 788);
        panel.setLayout(null);
        
        JButton btnSelect = new JButton("");
        btnSelect.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnSelectIcon.png"));
        btnSelect.setBounds(581, 702, 194, 58);
        btnSelect.setOpaque(false);
        btnSelect.setContentAreaFilled(false);
        btnSelect.setBorderPainted(false);
        btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chopsueyPanel2 = createChopsueyPanel2();
				frame.getContentPane().add(chopsueyPanel2);
				frame.getContentPane().remove(chopsueyPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	private JPanel createChopsueyPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\chopsueyImage2.png");
        panel.setBounds(0, 0, 940, 788);
        panel.setLayout(null);
        
        
        
        JTextField textField = new JTextField();
        textField.setBounds(312, 368, 98, 53);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        textField.setColumns(10);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setForeground(new Color(241, 143, 46));
        textField.setBackground(new Color(252, 246, 219));
        textField.setHorizontalAlignment(JTextField.CENTER);
        
        
        try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\fonts\\FredokaOne-Regular.otf")).deriveFont(35f);
            // Register the font with the Graphics Environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            textField.setFont(font);
            

		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        final String userNplaceholder = "1";
        textField.setText(userNplaceholder); // Set initial placeholder text
        
        JButton btnIncrease = new JButton("");
        btnIncrease.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnIncrease.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnIncreaseIcon2.png"));
        btnIncrease.setBounds(418, 372, 40, 40);
        btnIncrease.setOpaque(false);
        btnIncrease.setContentAreaFilled(false);
        btnIncrease.setBorderPainted(false);
        
        panel.add(btnIncrease);
        
        JButton btnDecrease = new JButton("");
        btnDecrease.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDecreaseIcon2.png"));
        btnDecrease.setBounds(265, 372, 40, 40);
        btnDecrease.setOpaque(false);
        btnDecrease.setContentAreaFilled(false);
        btnDecrease.setBorderPainted(false);


        panel.add(btnDecrease);
        
        JButton btnCook = new JButton("");
        btnCook.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnCokIcon.png"));
        btnCook.setBounds(492, 377, 151, 44);
        btnCook.setOpaque(false);
        btnCook.setContentAreaFilled(false);
        btnCook.setBorderPainted(false);
        panel.add(btnCook);
        
        // Add a focus listener to handle placeholder behavior
        textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			    if (textField.getText().equals(userNplaceholder)) {
			    	textField.setText("");
                }				
			}
			@Override
			public void focusLost(FocusEvent e) {
				   if (textField.getText().isEmpty()) {
					   textField.setText(userNplaceholder);
	                	
	                }				
			}
        });
        
        btnDecrease.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                String currentValue = textField.getText();
                int baseVal;
                
                try {
                    // Convert the current value to an integer
                    baseVal = Integer.parseInt(currentValue);
                } catch (NumberFormatException ex) {
                    // Handle the case where the text field does not contain a valid integer
                    baseVal = 0; // or any default value you deem appropriate
                }
                
                baseVal--;
                if(baseVal < 0) {
                	JOptionPane.showMessageDialog(frame, "Cannot decrease input anymore. Limit is 0.", "Error", JOptionPane.ERROR_MESSAGE);
                	btnDecrease.disable();
                	baseVal = 0;
                }
                String newValue = (java.lang.String.valueOf(baseVal));
                textField.setText(newValue);
			}
        	
        });
        btnIncrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Read the current value from the text field
                String currentValue = textField.getText();
                int baseVal;
                
                try {
                    // Convert the current value to an integer
                    baseVal = Integer.parseInt(currentValue);
                } catch (NumberFormatException ex) {
                    // Handle the case where the text field does not contain a valid integer
                    baseVal = 0; // or any default value you deem appropriate
                }
                
                // Increment the value
                baseVal++;

                // Convert the incremented value back to a string
                String newValue = (java.lang.String.valueOf(baseVal));

                // Set the updated value back to the text field
                textField.setText(newValue);
            }
        });
        

        
        panel.add(textField);
        panel.add(btnIncrease);
        panel.add(btnDecrease);
        panel.add(btnCook);

        
        
        return panel;
	}
	
	private void showTofuPanel() {
		tofuPanel = createTofuPanel();
		frame.getContentPane().remove(panel);
		frame.getContentPane().remove(dietaryPanel);
		frame.getContentPane().add(tofuPanel);
	    frame.revalidate();
        frame.repaint();

	}
	
	
	
	
	
	public void showBudgetPanel(boolean low_calorie,boolean vegetarian,boolean low_sodium,
			boolean high_protein, boolean heart_healthy,boolean balanced_nutrition, boolean low_carb,boolean gluten_free,boolean vitamins_minerals_focused) 
	{
		frame.getContentPane().remove(dietaryPanel);
        budgetPanel = createBudgetPanel(low_calorie, vegetarian, low_sodium,
    			 high_protein, heart_healthy, balanced_nutrition,  low_carb, gluten_free, vitamins_minerals_focused);
        
		frame.getContentPane().add(budgetPanel);
        frame.revalidate();
        frame.repaint();
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
    

	public void Show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cookFrame window = new cookFrame(currentUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});			
	}
}
