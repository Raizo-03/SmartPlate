package main;
import framesPackage.*;
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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;

public class DashboardForm {

	private JFrame frame;
	static final User String = null;
    private User currentUser;
	private JLabel nameLabel;
	private JPanel homePanel;
    DatabaseConnection base = new DatabaseConnection();

	//EFFECTS
	private List<JButton> menuButtons;
	private static final int BUTTON_SHIFT = 222; // Distance to shift buttons
	private List<JLabel> slidingLabels;
	private static final int LABEL_SHIFT = 180; // Distance to shift labels

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
	        fetchinguserInformation(); // Move fetchinguserInformation() here
	    } else {
	        // Handle the case when the user is not authenticated
	        System.out.println("User not authenticated");
	    }
	    startSlidingLabels();
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
                        
                        //nameLabel.setVisible(false);

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
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\menu.png");
        panel.setLayout(null);
        panel.setBounds(0, 0, 940, 788);

        nameLabel = new JLabel("");
        nameLabel.setBounds(645, 11, 174, 25);
        panel.add(nameLabel);
        
        ImageIcon btnrightIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnRightIcon.png");
        JButton btnRight = new JButton(btnrightIcon);	
        btnRight.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnRight.setBounds(608, 475, 60, 53);
        frame.getContentPane().add(btnRight);
        // Make the button non-opaque
        btnRight.setOpaque(false);

        // Make the button's content area transparent
        btnRight.setContentAreaFilled(false);

        // Make the button's border transparent
        btnRight.setBorderPainted(false);
        
        ImageIcon btnleftIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnLeftIcon.png");
        ImageIcon lblPakbetIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\lblPakbetIcon.png");
        JLabel lblPakbet = new JLabel(lblPakbetIcon);
        lblPakbet.setBounds(175, 571, 165, 111);
        frame.getContentPane().add(lblPakbet);
        
        ImageIcon lblCBeefIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\lblCBeefIcon.png");
        JLabel lblCBeef = new JLabel(lblCBeefIcon);
        lblCBeef.setBounds(515, 571, 165, 111);
        frame.getContentPane().add(lblCBeef);
        
        ImageIcon lblAdobongSitawIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\lblAdobongSitawIcon.png");
        JLabel lblAdobongSitaw = new JLabel(lblAdobongSitawIcon);
        lblAdobongSitaw.setBounds(350, 571, 155, 111);
        frame.getContentPane().add(lblAdobongSitaw);
        
        ImageIcon lblTunaEggIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\lblTunaEggIcon.png");
        JLabel lblTunaEgg = new JLabel(lblTunaEggIcon);
        lblTunaEgg.setBounds(690, 571, 165, 111);
        frame.getContentPane().add(lblTunaEgg);
        
        ImageIcon lblSizzlingTofuIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\lblSizzTofuIcon.png");
        JLabel lblSizzlingTofu = new JLabel(lblSizzlingTofuIcon);
        lblSizzlingTofu.setBounds(0, 571, 165, 111);
        frame.getContentPane().add(lblSizzlingTofu);
        
        ImageIcon lblEggIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\lblEggIcon.png");
        JLabel lblEgg = new JLabel(lblEggIcon);
        lblEgg.setBorder(null);
        lblEgg.setBounds(865, 571, 156, 111);
        frame.getContentPane().add(lblEgg);
        
     // After creating and adding labels to the frame
        slidingLabels = new ArrayList<>();
        slidingLabels.add(lblSizzlingTofu); // Assuming this is the first (leftmost) label
        slidingLabels.add(lblPakbet);
        slidingLabels.add(lblAdobongSitaw);
        slidingLabels.add(lblCBeef);
        slidingLabels.add(lblTunaEgg);
        slidingLabels.add(lblEgg); // Assuming this is the last (rightmost) label
        
        JButton btnLeft = new JButton(btnleftIcon);
        btnLeft.setOpaque(false);
        btnLeft.setContentAreaFilled(false);
        btnLeft.setBorderPainted(false);
        btnLeft.setBounds(269, 475, 60, 53);
        frame.getContentPane().add(btnLeft);
        
        ImageIcon btnProfileIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnMeIcon.png");
        JButton btnProfile = new JButton(btnProfileIcon);
        btnProfile.setOpaque(false);
        btnProfile.setContentAreaFilled(false);
        btnProfile.setBorderPainted(false);
        btnProfile.setBounds(165, 240, 175, 191);
        panel.add(btnProfile);
        
        ImageIcon btnHistoryIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnHistoryIcon.png");
        JButton btnHistory = new JButton(btnHistoryIcon);
        btnHistory.setOpaque(false);
        btnHistory.setContentAreaFilled(false);
        btnHistory.setBorderPainted(false);
        btnHistory.setBounds(-32, 240, 175, 191);
        panel.add(btnHistory);
        
        ImageIcon btnAboutIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnAboutIcon.png");
        JButton btnAbout = new JButton(btnAboutIcon);
        btnAbout.setOpaque(false);
        btnAbout.setContentAreaFilled(false);
        btnAbout.setBorderPainted(false);
        btnAbout.setBounds(608, 240, 175, 191);
        btnAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				aboutFrame window = new aboutFrame(currentUser);
				window.Show();
				frame.dispose();
			}
        	
        });
        panel.add(btnAbout);

        ImageIcon btnBudgetIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnBudgetIcon.png");
        JButton btnBudget = new JButton(btnBudgetIcon);
        btnBudget.setOpaque(false);
        btnBudget.setContentAreaFilled(false);
        btnBudget.setBorderPainted(false);
        btnBudget.setBounds(806, 240, 175, 191);
        panel.add(btnBudget);

        ImageIcon btnCookIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnCookIcon.png");
        JButton btnCook = new JButton(btnCookIcon);
        btnCook.setOpaque(false);
        btnCook.setContentAreaFilled(false);
        btnCook.setBorderPainted(false);
        btnCook.setBounds(389, 240, 175, 191);
        panel.add(btnCook);
        
        btnCook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
                User authenticatedUser = UserAuthentication.authenticateUser1(currentUser.getUsername());
                SessionManager.createSession(authenticatedUser);
                cookFrame cook = new cookFrame(authenticatedUser);
                cook.Show();
                frame.dispose();			
			}
        });
        
        JButton btnLogout = new JButton("");
        btnLogout.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnLogoutIcon.png"));
        btnLogout.setOpaque(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setBorderPainted(false);
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?",
                        "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                	logoutSession();
                	LoginForm login = new LoginForm();
                	login.Show();
                	frame.dispose();
                }
        		
        	}
        });
        btnLogout.setBounds(798, 725, 106, 52);
        
        
        // Initialize the menu buttons list
        menuButtons = new ArrayList<>();
        menuButtons.add(btnHistory); // Assuming this is the first (leftmost) button
        menuButtons.add(btnProfile);
        menuButtons.add(btnCook);
        menuButtons.add(btnAbout);
        menuButtons.add(btnBudget); // Assuming this is the last (rightmost) button

        // Configure the right button
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollRight();
            }
        });

        // Configure the left button
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollLeft();
            }
        });


        frame.getContentPane().add(panel);
        
        
        
        
        
        
        panel.add(btnLeft);
        panel.add(btnRight);
        panel.add(btnLogout);

        return panel;
    }
	//FOR RIGHT AND LEFT BUTTON
	private void scrollRight() {
	    JButton firstButton = menuButtons.remove(0);
	    menuButtons.add(firstButton);
	    updateButtonPositions();
	}

	private void scrollLeft() {
	    JButton lastButton = menuButtons.remove(menuButtons.size() - 1);
	    menuButtons.add(0, lastButton);
	    updateButtonPositions();
	}
	
	private void logoutSession() {
		SessionManager.clearSession();
	}

	private void updateButtonPositions() {
	    int middleButtonIndex = 2; // The index of the middle button in the list
	    int middleX = 389; // X position of the middle button (btnCook's initial position)

	    // Calculate the starting x position based on the middle button's position
	    int x = middleX - (BUTTON_SHIFT * middleButtonIndex);

	    for (JButton button : menuButtons) {
	        button.setBounds(x, 240, 175, 191); // Update position
	        x += BUTTON_SHIFT; // Shift for the next button
	    }
	}
	//FOR SLIDESHOW
		private void startSlidingLabels() {
		    int delay = 1000; // Milliseconds between label shifts

		    ActionListener taskPerformer = new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		            scrollLabels();
		        }
		    };

		    new Timer(delay, taskPerformer).start();
		}

		private void scrollLabels() {
		    JLabel firstLabel = slidingLabels.remove(0);
		    slidingLabels.add(firstLabel);
		    updateLabelPositions();
		}

		private void updateLabelPositions() {
		    int x = 15; // Starting x position for the first label

		    for (JLabel label : slidingLabels) {
		        label.setBounds(x, 571, 165, 111); // Update position
		        x += LABEL_SHIFT; // Shift for the next label
		    }
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
