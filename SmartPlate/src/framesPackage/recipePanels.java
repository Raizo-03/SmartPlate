package framesPackage;

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
import javax.swing.JPanel;

import main.DashboardForm;
import main.DatabaseConnection;
import main.SessionManager;
import main.User;

public class recipePanels {

	private JFrame frame;
    private JPanel tofuPanel, chopsueyPanel, creamyPanel, sinigangPanel, calamansiPanel,
    tokwaPanel, ginilingPanel, pastaPanel, sandwichPanel, brocolliPanel, upoPanel,
    eggplantPanel, sweetporkPanel, eggPanel, beefPanel, tunaPanel, ricePanel, pakbetPanel;
	static final User String = null;
    private User currentUser;
    DatabaseConnection base = new DatabaseConnection();
    private String name;
    


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					recipePanels window = new recipePanels(String);
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
	public recipePanels(User user) {
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
        
	}
	public void activatePanels(String food) {
		if(food.equals("Tofu")) {
		    createTofuPanel();
	        frame.add(tofuPanel); // Add the panel to the frame directly here
	        frame.revalidate(); // This tells the layout manager to reset based on the new component
	        frame.repaint(); // This refreshes the frame to display the new component
		}else if(food.equals("Chopsuey")) {
			createChopSueyPanel();
		     frame.add(chopsueyPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Creamy")) {
			createCreamyPanel();
			   frame.add(creamyPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Sinigang")) {
			createSinigangPanel();
			  frame.add(sinigangPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Calamansi")) {
			createCalamansiPanel();
			  frame.add(calamansiPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Tokwa")) {
			createTokwaPanel();
			 frame.add(tokwaPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Giniling")) {
			createGinilingPanel();
			 frame.add(ginilingPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Pasta")) {
			createPastaPanel();
			 frame.add(pastaPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Sandwich")) {
			createSandwichPanel();
			 frame.add(sandwichPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Brocolli")) {
			createBrocolliPanel();
			 frame.add(brocolliPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Upo")) {
			createUpoPanel();
			 frame.add(upoPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Eggplant")) {
			createEggplantPanel();
			 frame.add(eggplantPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Sweet")) {
			createSweetPanel();
			 frame.add(sweetporkPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Egg")) {
			createEggPanel();
			 frame.add(eggPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Beef")) {
			createBeefPanel();
			 frame.add(beefPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Tuna")) {
			createTunaPanel();
			 frame.add(tunaPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Rice")) {
			createRicePanel();
			 frame.add(ricePanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}else if(food.equals("Pakbet")) {
			createPakbetPanel();
			 frame.add(pakbetPanel); // Add the panel to the frame directly here
		        frame.revalidate(); // This tells the layout manager to reset based on the new component
		        frame.repaint();
		}
	}
	private void createPakbetPanel() {
		pakbetPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookPakbetImage.png");
		pakbetPanel.setBounds(0, 0, 940, 788);
		pakbetPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    pakbetPanel.add(btnDone); 
	}
	private void createRicePanel() {
		ricePanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookRiceImage.png");
		ricePanel.setBounds(0, 0, 940, 788);
		ricePanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    ricePanel.add(btnDone); 
	}
	private void createTunaPanel() {
		tunaPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookTunaImage.png");
		tunaPanel.setBounds(0, 0, 940, 788);
		tunaPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    tunaPanel.add(btnDone); 
	}
	private void createBeefPanel() {
		beefPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookBeefImage.png");
		beefPanel.setBounds(0, 0, 940, 788);
		beefPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    beefPanel.add(btnDone); 
	}
	private void createEggPanel() {
		eggPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookEggImage.png");
		eggPanel.setBounds(0, 0, 940, 788);
		eggPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    eggPanel.add(btnDone); 
	}
	private void createSweetPanel() {
		sweetporkPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookSweetSourImage.png");
		sweetporkPanel.setBounds(0, 0, 940, 788);
		sweetporkPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    sweetporkPanel.add(btnDone); 
	}
	private void createEggplantPanel() {
        eggplantPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookEggplantImage.png");
        eggplantPanel.setBounds(0, 0, 940, 788);
        eggplantPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    eggplantPanel.add(btnDone); 
	}
	private void createUpoPanel() {
        upoPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookUpoImage.png");
        upoPanel.setBounds(0, 0, 940, 788);
        upoPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    upoPanel.add(btnDone); 
	}
	
	private void createBrocolliPanel() {
        brocolliPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookBrocolliImage.png");
        brocolliPanel.setBounds(0, 0, 940, 788);
        brocolliPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    brocolliPanel.add(btnDone); 
	}
	
	private void createSandwichPanel() {
        sandwichPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookSandwichImage.png");
        sandwichPanel.setBounds(0, 0, 940, 788);
        sandwichPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
        sandwichPanel.add(btnDone); 
	}
	private void createPastaPanel() {
        pastaPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookPastaImage.png");
        pastaPanel.setBounds(0, 0, 940, 788);
        pastaPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    pastaPanel.add(btnDone); 
	}
	
	private void createGinilingPanel() {
        ginilingPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookGinilingImage.png");
        ginilingPanel.setBounds(0, 0, 940, 788);
        ginilingPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    ginilingPanel.add(btnDone); 
	}
	private void createTokwaPanel() {
        tokwaPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookTokwaImage.png");
        tokwaPanel.setBounds(0, 0, 940, 788);
        tokwaPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    tokwaPanel.add(btnDone); 
	}
	private void createCalamansiPanel() {
        calamansiPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookCalamansiImage.png");
        calamansiPanel.setBounds(0, 0, 940, 788);
        calamansiPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    calamansiPanel.add(btnDone); 
	}
	private void createSinigangPanel() {
        sinigangPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookSinigangImage.png");
        sinigangPanel.setBounds(0, 0, 940, 788);
        sinigangPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    sinigangPanel.add(btnDone); 
	}
	
	private void createCreamyPanel() {
        creamyPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookCreamyImage.png");
        creamyPanel.setBounds(0, 0, 940, 788);
        creamyPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    creamyPanel.add(btnDone); 
	}
	
	
	private void createChopSueyPanel() {
        chopsueyPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookChopsueyImage.png");
        chopsueyPanel.setBounds(0, 0, 940, 788);
        chopsueyPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
	    chopsueyPanel.add(btnDone);      
	}
	
	
	private void createTofuPanel() {
        tofuPanel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cookTofuImage.png");
        tofuPanel.setBounds(0, 0, 940, 788);
        tofuPanel.setLayout(null);
	    JButton btnDone = new JButton("");
	    
	    btnDone.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
	        }
	    });
	    btnDone.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnDoneImage.png"));
	    btnDone.setOpaque(false);
	    btnDone.setContentAreaFilled(false);
	    btnDone.setBorderPainted(false);
	    btnDone.setBounds(780, 715, 150, 76);
        
        tofuPanel.add(btnDone);      
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
                    if (frame == null) { // Check if frame is already initialized
                        initialize(); // This should setup the frame if not already done
                    }
                    frame.setVisible(true); // Make the frame visible
                    activatePanels(""); // If you need to show a specific panel on showing the frame
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });     
    }

}
