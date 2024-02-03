package framesPackage;

import main.*;
import main.SessionManager;
import main.DatabaseConnection;
import main.User;
import main.UserAuthentication;

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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;


public class cookFrameUI {

	private JFrame frame;
	static final User String = null;
    private User currentUser;
    private JLabel lblName;
    DatabaseConnection base = new DatabaseConnection();
    private String name;
    private JPanel dietaryPanel, budgetPanel, suggestionsPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cookFrameUI window = new cookFrameUI();
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
	public cookFrameUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
	        
	        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\dietaryPanelImage.png");
	        panel.setBounds(0, 0, 940, 788);
	        panel.setLayout(null);
	        
	        
	        
	        
	        
	        frame.getContentPane().add(panel);
	        frame.getContentPane().add(panel);
	        
	        JRadioButton rdHighProtein = new JRadioButton("  High Protein");
	        rdHighProtein.setBackground(new Color(164, 229, 255)); // #A4E5FF
	        rdHighProtein.setForeground(new Color(229, 175, 55));
	        rdHighProtein.setBounds(46, 163, 263, 45);
	        rdHighProtein.setBorder(null);
	        rdHighProtein.setFocusPainted(false);

	        Icon proteinDefault = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\proteinDefaultIcon.png"); // Custom radio button icon
	        Icon proteinSelected = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\proteinSelectedIcon.png"); // Custom selected radio button icon
	        
            JButton btnNext = new JButton("");
            btnNext.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnNextIcon.png"));
            btnNext.setOpaque(false);
            btnNext.setContentAreaFilled(false);
            btnNext.setBorderPainted(false);
            btnNext.setBounds(786, 704, 106, 52);
            
            JButton btnSave = new JButton("");
            btnSave.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnSaveIcon.png"));
            btnSave.setOpaque(false);
            btnSave.setContentAreaFilled(false);
            btnSave.setBorderPainted(false);
            btnSave.setBounds(656, 704, 106, 52);
            frame.getContentPane().add(btnSave);
            
            frame.getContentPane().add(btnNext);
	        rdHighProtein.setIcon(proteinDefault); // Default state
	        rdHighProtein.setSelectedIcon(proteinSelected); // Selected state       
	        frame.getContentPane().add(rdHighProtein);
	        
	        JRadioButton rdHeart = new JRadioButton("  Heart-Healthy");
	        rdHeart.setForeground(new Color(255, 102, 196));
	        rdHeart.setFocusPainted(false);
	        rdHeart.setBorder(null);
	        rdHeart.setBackground(new Color(164, 229, 255));
	        rdHeart.setBounds(24, 263, 285, 45);
	        Icon heartDefault = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\heartDefaultIcon.png"); // Custom radio button icon
	        Icon heartSelected = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\heartSelectedIcon.png"); // Custom selected radio button icon
	        rdHeart.setIcon(heartDefault); // Default state
	        rdHeart.setSelectedIcon(heartSelected); // Selected stat
	        frame.getContentPane().add(rdHeart);
	        
	        JRadioButton rdVegetarian = new JRadioButton("  Vegetarian");
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
	        frame.getContentPane().add(rdVegetarian);
	        
	        JRadioButton rdCalorie = new JRadioButton("  Low-Calorie");
	        rdCalorie.setForeground(new Color(218, 52, 34));
	        rdCalorie.setFocusPainted(false);
	        rdCalorie.setBorder(null);
	        rdCalorie.setBackground(new Color(164, 229, 255));
	        rdCalorie.setBounds(57, 464, 263, 45);
	        Icon calorieDefault = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\calorieDefaultIcon.png"); // Custom radio button icon
	        Icon calorieSelected = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\calorieSelectedIcon.png"); // Custom selected radio button icon
	        rdCalorie.setIcon(calorieDefault); // Default state
	        rdCalorie.setSelectedIcon(calorieSelected); // Selected stat
	        frame.getContentPane().add(rdCalorie);
	        
	        JRadioButton rdBalanced = new JRadioButton(" Balanced Nutrition");
	        rdBalanced.setForeground(new Color(0, 133, 200));
	        rdBalanced.setFocusPainted(false);
	        rdBalanced.setBorder(null);
	        rdBalanced.setBackground(new Color(164, 229, 255));
	        rdBalanced.setBounds(282, 693, 334, 45);
	        Icon balancedDefaultIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\balancedDefaultIcon.png"); // Custom radio button icon
	        Icon balancedSelectedIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\balancedSelectedIcon.png"); // Custom selected radio button icon
	        rdBalanced.setIcon(balancedDefaultIcon); // Default state
	        rdBalanced.setSelectedIcon(balancedSelectedIcon); // Selected stat
	        frame.getContentPane().add(rdBalanced);
	        
	        JRadioButton rdSodium = new JRadioButton("  Low-Sodium");
	        rdSodium.setForeground(new Color(57, 36, 19));
	        rdSodium.setFocusPainted(false);
	        rdSodium.setBorder(null);
	        rdSodium.setBackground(new Color(164, 229, 255));
	        rdSodium.setBounds(629, 163, 263, 45);
	        Icon sodiumDefaultIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\sodiumDefaultIcon.png"); // Custom radio button icon
	        Icon sodiumSelectedIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\sodiumSelectedIcon.png"); // Custom selected radio button icon
	        rdSodium.setIcon(sodiumDefaultIcon); // Default state
	        rdSodium.setSelectedIcon(sodiumSelectedIcon); // Selected stat
	        frame.getContentPane().add(rdSodium);
	        
	        JRadioButton rdCarbs = new JRadioButton("  Low-Carbs");
	        rdCarbs.setForeground(new Color(255, 145, 77));
	        rdCarbs.setFocusPainted(false);
	        rdCarbs.setBorder(null);
	        rdCarbs.setBackground(new Color(164, 229, 255));
	        rdCarbs.setBounds(629, 263, 247, 45);
	        Icon carbsDefaultIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\carbDefaultIcon.png"); // Custom radio button icon
	        Icon carbsSelectedIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\carbSelectedIcon.png"); // Custom selected radio button icon
	        rdCarbs.setIcon(carbsDefaultIcon); // Default state
	        rdCarbs.setSelectedIcon(carbsSelectedIcon); // Selected stat
	        frame.getContentPane().add(rdCarbs);
	        
	        JRadioButton rdGluten = new JRadioButton("  Gluten-Free");
	        rdGluten.setForeground(new Color(94, 23, 235));
	        rdGluten.setFocusPainted(false);
	        rdGluten.setBorder(null);
	        rdGluten.setBackground(new Color(164, 229, 255));
	        rdGluten.setBounds(615, 360, 247, 45);
	        Icon glutenDefaultIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\glutenDefaultIcon.png"); // Custom radio button icon
	        Icon glutenSelectedIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\glutenSelectedIcon.png"); // Custom selected radio button icon
	        rdGluten.setIcon(glutenDefaultIcon); // Default state
	        rdGluten.setSelectedIcon(glutenSelectedIcon); // Selected stat
	        frame.getContentPane().add(rdGluten);
	        
	        JRadioButton rdVita = new JRadioButton("<html> Vitamins and<br/>Minerals Focused </html>");
	        rdVita.setForeground(new Color(78, 127, 28));
	        rdVita.setFocusPainted(false);
	        rdVita.setBorder(null);
	        rdVita.setBackground(new Color(164, 229, 255));
	        rdVita.setBounds(595, 456, 279, 114);
	        Icon vitaDefaultIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\vitaDefaultIcon.png"); // Custom radio button icon
	        Icon vitaSelectedIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\vitaSelectedIcon.png"); // Custom selected radio button icon
	        rdVita.setIcon(vitaDefaultIcon); // Default state
	        rdVita.setSelectedIcon(vitaSelectedIcon); // Selected stat
	        frame.getContentPane().add(rdVita);
            
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
}
