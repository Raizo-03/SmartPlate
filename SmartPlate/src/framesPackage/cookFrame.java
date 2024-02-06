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

public class cookFrame extends loadingDialog{

	private JFrame frame;
	static final User String = null;
    private User currentUser;
    private JLabel lblName;
    DatabaseConnection base = new DatabaseConnection();
    private String name;
    private JPanel panel, dietaryPanel, budgetPanel, tofuPanel, tofuPanel2, chopsueyPanel, chopsueyPanel2, creamyPanel, creamyPanel2, sinigangPanel, sinigangPanel2, calamansiPanel, calamansiPanel2,
    tokwaPanel, tokwaPanel2, ginilingPanel, ginilingPanel2, pastaPanel, pastaPanel2, sandwichPanel, sandwichPanel2, broccoliPanel, broccoliPanel2, upoPanel, upoPanel2,
    eggplantPanel, eggplantPanel2, sweetporkPanel, sweetporkPanel2, eggPanel, eggPanel2, beefPanel, beefPanel2, tunaPanel, tunaPanel2, ricePanel, ricePanel2, pakbetPanel, pakbetPanel2;
	private JTextField txtFieldBugdet;
	private boolean low_calorie = false, vegetarian = false, low_sodium = false,
	high_protein, heart_healthy = false, balanced_nutrition = false, low_carb = false,gluten_free = false, vitamins_minerals_focused = false;
	private JRadioButton rdHighProtein, rdHeart, rdVegetarian, rdCalorie, rdBalanced, rdSodium, rdCarbs, rdGluten, rdVita;
	JProgressBar progressBar;
	loadingDialog load = new loadingDialog();
	recipePanels recipes = new recipePanels(currentUser);

    
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
		        if (high_protein == true && low_carb == true && vegetarian == true && price >= 200 && price <= 220) {
		        	load.showSuggestingDialog();
		                    tofuPanel = createTofuPanel();
		                    frame.getContentPane().add(tofuPanel);
		                    frame.getContentPane().removeAll(); // Remove all components
		                    frame.getContentPane().add(tofuPanel); // Add new panel
		                    frame.revalidate();
		                    frame.repaint();
		        }else if(vitamins_minerals_focused == true && balanced_nutrition == true && heart_healthy == true && low_carb == false && price >= 100 && price <= 120) {
		        	load.showSuggestingDialog();
		        	chopsueyPanel = createChopsueyPanel();
		        	
							frame.getContentPane().add(chopsueyPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();					        
				}else if(high_protein == true && vitamins_minerals_focused == true && balanced_nutrition == true && low_carb == false && price >= 250 && price <= 270) {
		        	load.showSuggestingDialog();
		                    creamyPanel = createCreamyPanel();
							frame.getContentPane().add(creamyPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(high_protein == true && balanced_nutrition == true && vitamins_minerals_focused == true && low_carb == true && price >= 250 && price <= 290) {
		        	load.showSuggestingDialog();
		                    sinigangPanel = createSinigangPanel();
							frame.getContentPane().add(sinigangPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(high_protein == true && low_calorie == true && heart_healthy == true && balanced_nutrition == true && low_sodium == true && gluten_free == true 
						&& vitamins_minerals_focused == true && low_carb == true && vegetarian == false && price >= 160 && price <= 180) {
		        	load.showSuggestingDialog();
		                    calamansiPanel = createCalamansiPanel();
							frame.getContentPane().add(calamansiPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(high_protein == true && low_calorie == true && heart_healthy == true && balanced_nutrition == true && low_carb == true && vegetarian == true && 
						vitamins_minerals_focused == true && gluten_free == false && price >= 100 && price <= 120) {
		        	load.showSuggestingDialog();
                    tokwaPanel = createTokwaPanel();
							frame.getContentPane().add(tokwaPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
					
				}else if(low_calorie == true && low_sodium == true && low_carb == true && price >= 100 && price <= 120) {
			       	load.showSuggestingDialog();
                    pastaPanel = createPastaPanel();
							frame.getContentPane().add(pastaPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(high_protein == true && low_sodium == true && vitamins_minerals_focused == true && price >= 100 && price <= 120) {
			       	load.showSuggestingDialog();
                    sandwichPanel = createSandwichPanel();
							frame.getContentPane().add(sandwichPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(high_protein == true && heart_healthy == true && low_calorie == true && low_carb == true && vitamins_minerals_focused == true && vegetarian == true &&
						balanced_nutrition == true && price >= 180 && price <= 200) {
			       	load.showSuggestingDialog();
                    broccoliPanel = createBroccoliPanel();
							frame.getContentPane().add(broccoliPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(high_protein == true && heart_healthy == true && low_calorie == true && low_carb == true && vitamins_minerals_focused == true && balanced_nutrition == true && gluten_free == true 
						&& price >= 100 && price <= 120) {
			       	load.showSuggestingDialog();
                    upoPanel = createUpoPanel();
							frame.getContentPane().add(upoPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(vegetarian == true && heart_healthy == true && low_calorie == true && low_carb == true && vitamins_minerals_focused == true && balanced_nutrition == true && price >= 90 && price <= 100) {
			       	load.showSuggestingDialog();
                    eggplantPanel = createEggplantPanel();
							frame.getContentPane().add(eggplantPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(vitamins_minerals_focused == true && heart_healthy == true && low_carb == true && balanced_nutrition == true && high_protein == true && price >= 400 &&
						price <= 420) {
			       	load.showSuggestingDialog();
                    sweetporkPanel = createSweetPork();
							frame.getContentPane().add(sweetporkPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(low_calorie == true && balanced_nutrition == true && low_carb == false && price >= 15 && price <= 35) {
			       	load.showSuggestingDialog();
                    eggPanel = createEggPanel();
							frame.getContentPane().add(eggPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(balanced_nutrition == true && high_protein == false && price >= 60 && price <= 80) {
			       	load.showSuggestingDialog();
                    beefPanel = createBeefPanel();
							frame.getContentPane().add(beefPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(high_protein == true && balanced_nutrition == true && low_sodium == false && price >= 65 && price <= 85) {
			       	load.showSuggestingDialog();
                    tunaPanel = createTunaPanel();
							frame.getContentPane().add(tunaPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(balanced_nutrition == true && low_calorie == true && low_carb == true && vitamins_minerals_focused == true && price >= 50 && price <= 70) {
			       	load.showSuggestingDialog();
                    ricePanel = createRicePanel();
							frame.getContentPane().add(ricePanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(vitamins_minerals_focused == true && balanced_nutrition == true && vegetarian == true && price >= 150 && price <= 170) {
			       	load.showSuggestingDialog();
                    pakbetPanel = createPakbetPanel();
							frame.getContentPane().add(pakbetPanel);
							frame.getContentPane().remove(budgetPanel);
							frame.getContentPane().remove(panel);
						    frame.revalidate();
					        frame.repaint();
				}else if(high_protein == true && heart_healthy == true && vitamins_minerals_focused == true && balanced_nutrition == true && price >= 160 && price <= 180) {
			       	load.showSuggestingDialog();
					 ginilingPanel = createGinilingPanel();
						frame.getContentPane().add(ginilingPanel);
						frame.getContentPane().remove(budgetPanel);
						frame.getContentPane().remove(panel);
					    frame.revalidate();
				        frame.repaint();
					 
				}else {
			      	load.showSuggestingDialog();
								 ginilingPanel = createGinilingPanel();
									frame.getContentPane().add(ginilingPanel);
									frame.getContentPane().remove(budgetPanel);
									frame.getContentPane().remove(panel);
								    frame.revalidate();
							        frame.repaint();
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
				load.showCookingDialog();
				recipes.activatePanels("Tofu");
				recipes.Show();
				frame.dispose();
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
        btnCook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				load.showCookingDialog();
				recipes.activatePanels("Chopsuey");
				recipes.Show();
				frame.dispose();
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
	private JPanel createCreamyPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\creamyCImage.png");
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
				creamyPanel2 = createCreamyPanel2();
				frame.getContentPane().add(creamyPanel2);
				frame.getContentPane().remove(creamyPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	private JPanel createCreamyPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\creamyCImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Creamy");
				recipes.Show();
				frame.dispose();
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
	private JPanel createSinigangPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\sinigangImage.png");
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
				sinigangPanel2 = createSinigangPanel2();
				frame.getContentPane().add(sinigangPanel2);
				frame.getContentPane().remove(sinigangPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createSinigangPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\sinigangImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Sinigang");
				recipes.Show();
				frame.dispose();
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
	private JPanel createCalamansiPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\calamansiImage.png");
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
				calamansiPanel2 = createCalamansiPanel2();
				frame.getContentPane().add(calamansiPanel2);
				frame.getContentPane().remove(calamansiPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createCalamansiPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\calamansiImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Calamansi");
				recipes.Show();
				frame.dispose();
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
	private JPanel createTokwaPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\tokwaImage.png");
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
				tokwaPanel2 = createTokwaPanel2();
				frame.getContentPane().add(tokwaPanel2);
				frame.getContentPane().remove(tokwaPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createTokwaPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\tokwaImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Tokwa");
				recipes.Show();
				frame.dispose();
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
	private JPanel createGinilingPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\ginilingImage.png");
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
				ginilingPanel2 = createGinilingPanel2();
				frame.getContentPane().add(ginilingPanel2);
				frame.getContentPane().remove(ginilingPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createGinilingPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\ginilingImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Giniling");
				recipes.Show();
				frame.dispose();
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
	private JPanel createPastaPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\pastaImage.png");
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
				pastaPanel2 = createPastaPanel2();
				frame.getContentPane().add(pastaPanel2);
				frame.getContentPane().remove(pastaPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createPastaPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\pastaImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Pasta");
				recipes.Show();
				frame.dispose();
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
	private JPanel createSandwichPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\sandwichImage.png");
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
				sandwichPanel2 = createSandwichPanel2();
				frame.getContentPane().add(sandwichPanel2);
				frame.getContentPane().remove(sandwichPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createSandwichPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\sandwichImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Sandwich");
				recipes.Show();
				frame.dispose();
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
	private JPanel createBroccoliPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\broccoliImage.png");
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
				broccoliPanel2 = createBroccoliPanel2();
				frame.getContentPane().add(broccoliPanel2);
				frame.getContentPane().remove(broccoliPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createBroccoliPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\broccoliImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Brocolli");
				recipes.Show();
				frame.dispose();
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
	
	private JPanel createUpoPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\upoImage.png");
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
				upoPanel2 = createUpoPanel2();
				frame.getContentPane().add(upoPanel2);
				frame.getContentPane().remove(upoPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createUpoPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\upoImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Upo");
				recipes.Show();
				frame.dispose();
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
	private JPanel createEggplantPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\eggplantImage.png");
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
				eggplantPanel2 = createEggplantPanel2();
				frame.getContentPane().add(eggplantPanel2);
				frame.getContentPane().remove(eggplantPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createEggplantPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\eggplantImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Eggplant");
				recipes.Show();
				frame.dispose();
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
	private JPanel createSweetPork() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\sweetImage.png");
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
				sweetporkPanel2 = createSweetPork2();
				frame.getContentPane().add(sweetporkPanel2);
				frame.getContentPane().remove(sweetporkPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createSweetPork2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\sweetImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Sweet");
				recipes.Show();
				frame.dispose();
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
	private JPanel createEggPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\eggImage.png");
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
				eggPanel2 = createEggPanel2();
				frame.getContentPane().add(eggPanel2);
				frame.getContentPane().remove(eggPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createEggPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\eggImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Egg");
				recipes.Show();
				frame.dispose();
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
	private JPanel createBeefPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\beefImage.png");
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
				beefPanel2 = createBeefPanel2();
				frame.getContentPane().add(beefPanel2);
				frame.getContentPane().remove(beefPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createBeefPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\beefImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Beef");
				recipes.Show();
				frame.dispose();
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
	private JPanel createTunaPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\tunaImage.png");
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
				tunaPanel2 = createTunaPanel2();
				frame.getContentPane().add(tunaPanel2);
				frame.getContentPane().remove(tunaPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createTunaPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\tunaImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Tuna");
				recipes.Show();
				frame.dispose();
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
	private JPanel createRicePanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\riceImage.png");
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
				ricePanel2 = createRicePanel2();
				frame.getContentPane().add(ricePanel2);
				frame.getContentPane().remove(ricePanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createRicePanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\riceImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Rice");
				recipes.Show();
				frame.dispose();		    
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
	private JPanel createPakbetPanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\pakbetImage.png");
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
				pakbetPanel2 = createPakbetPanel2();
				frame.getContentPane().add(pakbetPanel2);
				frame.getContentPane().remove(pakbetPanel);
				frame.getContentPane().remove(budgetPanel);
				frame.getContentPane().remove(panel);
			    frame.revalidate();
		        frame.repaint();
			}
        	
        });
        
        panel.add(btnSelect);

        
        
        return panel;
	}
	
	private JPanel createPakbetPanel2() {
	    ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\pakbetImage2.png");
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
				load.showCookingDialog();
				recipes.activatePanels("Pakbet");
				recipes.Show();
				frame.dispose();		   
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
