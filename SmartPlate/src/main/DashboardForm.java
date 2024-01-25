package main;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;

public class DashboardForm {

	private JFrame frame;
	static final User String = null;
    private User currentUser;
	private JLabel nameLabel;
	private JPanel currentPanel, homePanel, profilePanel, historyPanel, budgetPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardForm window = new DashboardForm(String);
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
	public DashboardForm(User user) {
	    initialize();
	    // Fetch authenticated user from SessionManager
	    currentUser = SessionManager.getCurrentUser();
	    // Check if the user is authenticated before displaying user info
	    if (currentUser != null) {
	        displayUserInfo();
	        fetchinguserInformation(); // Move fetchinguserInformation() here
	    } else {
	        // Handle the case when the user is not authenticated
	        System.out.println("User not authenticated");
	    }
	}
    private void displayUserInfo() {
        // Display user information in the UI
        nameLabel.setText(currentUser.getUsername());
        // AdditionalInfo can be displayed or used as needed
        // For example: currentUser.getAdditionalInfo();
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
                        nameLabel.setText(name);

                        // You can update the UI or perform other actions with this information
                    } else {
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        // Create and add homePanel to frame
        homePanel = createHomePanel();
        frame.getContentPane().add(homePanel);
    }

    private JPanel createHomePanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\Homepage.png");
        panel.setLayout(null);
        panel.setBounds(0, 0, 940, 788);

        nameLabel = new JLabel("");
        nameLabel.setBounds(645, 11, 174, 25);
        panel.add(nameLabel);
        
        ImageIcon btnrightIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnRightIcon.png");
        JButton btnRight = new JButton(btnrightIcon);	
        btnRight.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Clicked!");
        	}
        });
        btnRight.setBounds(579, 465, 60, 53);
        frame.getContentPane().add(btnRight);
        // Make the button non-opaque
        btnRight.setOpaque(false);

        // Make the button's content area transparent
        btnRight.setContentAreaFilled(false);

        // Make the button's border transparent
        btnRight.setBorderPainted(false);
        
        ImageIcon btnleftIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnLeftIcon.png");

        JButton btnLeft = new JButton(btnleftIcon);
        btnLeft.setOpaque(false);
        btnLeft.setContentAreaFilled(false);
        btnLeft.setBorderPainted(false);
        btnLeft.setBounds(237, 465, 60, 53);
        frame.getContentPane().add(btnLeft);
        panel.add(btnLeft);
        panel.add(btnRight);

        return panel;
    }
	
	public void Show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardForm window = new DashboardForm(currentUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}
}
