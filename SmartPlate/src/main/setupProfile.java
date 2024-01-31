package main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class setupProfile {

	private JFrame frame;
	private JPanel chefsKnowledgePanel, JPanel, chefsAllergyPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setupProfile window = new setupProfile();
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
	public setupProfile() {
		initialize();
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
        
        chefsKnowledgePanel = createchefsKnowledgePanel();
        frame.getContentPane().add(chefsKnowledgePanel);

	}
	
	private JPanel createchefsKnowledgePanel() {
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\chefsKnowledgeImage.png");
        panel.setLayout(null);
        panel.setBounds(0, 0, 940, 788);
        return panel;
	}

}
