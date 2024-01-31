package main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

public class setupProfileUI {

	private JFrame frame;
	private JPanel chefsKnowledgePanel, JPanel, chefsAllergyPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setupProfileUI window = new setupProfileUI();
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
	public setupProfileUI() {
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
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        ImageIcon AppIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
        frame.setIconImage(AppIcon.getImage());
        
 
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\AllergiesImage.png");
        panel.setLayout(null);
        panel.setBounds(0, 0, 940, 788);
                
    
     
        frame.getContentPane().add(panel);
        
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
        frame.getContentPane().add(btnEgg);
        
        JButton btnFish = new JButton("");
        btnFish.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnFishIcon.png"));
        btnFish.setOpaque(false);
        btnFish.setContentAreaFilled(false);
        btnFish.setBorderPainted(false);
        btnFish.setBounds(406, 366, 87, 93);
        frame.getContentPane().add(btnFish);
        
        JButton btnCrab = new JButton("");
        btnCrab.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnCrabIcon.png"));
        btnCrab.setOpaque(false);
        btnCrab.setContentAreaFilled(false);
        btnCrab.setBorderPainted(false);
        btnCrab.setBounds(560, 366, 87, 93);
        frame.getContentPane().add(btnCrab);
        
        JButton btnPeanut = new JButton("");
        btnPeanut.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnPeanutIcon.png"));
        btnPeanut.setOpaque(false);
        btnPeanut.setContentAreaFilled(false);
        btnPeanut.setBorderPainted(false);
        btnPeanut.setBounds(728, 366, 87, 93);
        frame.getContentPane().add(btnPeanut);
        
        JButton btnGrain = new JButton("");
        btnGrain.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnGrainIcon.png"));
        btnGrain.setOpaque(false);
        btnGrain.setContentAreaFilled(false);
        btnGrain.setBorderPainted(false);
        btnGrain.setBounds(195, 519, 87, 93);
        frame.getContentPane().add(btnGrain);
        
        JButton btnCoffee = new JButton("");
        btnCoffee.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnCoffeeIcon.png"));
        btnCoffee.setOpaque(false);
        btnCoffee.setContentAreaFilled(false);
        btnCoffee.setBorderPainted(false);
        btnCoffee.setBounds(354, 519, 87, 93);
        frame.getContentPane().add(btnCoffee);
        
        JButton btnNuts = new JButton("");
        btnNuts.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnNutsIcon.png"));
        btnNuts.setOpaque(false);
        btnNuts.setContentAreaFilled(false);
        btnNuts.setBorderPainted(false);
        btnNuts.setBounds(479, 519, 87, 93);
        frame.getContentPane().add(btnNuts);
        
        JButton btnShellfish = new JButton("");
        btnShellfish.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnShellfishIcon.png"));
        btnShellfish.setOpaque(false);
        btnShellfish.setContentAreaFilled(false);
        btnShellfish.setBorderPainted(false);
        btnShellfish.setBounds(627, 519, 87, 93);
        frame.getContentPane().add(btnShellfish);


        
        JButton btnReadyCook = new JButton("");
        btnReadyCook.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnReadyCookIcon.png"));
        btnReadyCook.setBounds(332, 665, 294, 83);
        btnReadyCook.setOpaque(false);
        btnReadyCook.setContentAreaFilled(false);
        btnReadyCook.setBorderPainted(false);
        
        JButton btnReset = new JButton("");
        btnReset.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnResetIcon.png"));
        btnReset.setOpaque(false);
        btnReset.setContentAreaFilled(false);
        btnReset.setBorderPainted(false);
        btnReset.setBounds(787, 256, 106, 52);
        btnReset.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to reset your cooking level?",
                                                         "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "Your cooking level has been reset to beginner.", 
                                              "Reset Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        frame.getContentPane().add(btnReset);

        frame.getContentPane().add(btnReadyCook);

        frame.getContentPane().add(btnMilk);
        
        frame.getContentPane().add(panel);
	}
	private JPanel createchefsKnowledgePanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\chefsKnowledgeImage.png");
        panel.setLayout(null);
        panel.setBounds(0, 0, 940, 788);
        
        return panel;
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
