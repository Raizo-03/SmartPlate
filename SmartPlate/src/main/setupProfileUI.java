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

        frame.getContentPane().add(btnBeginner);
        frame.getContentPane().add(btnIntermediate);
        frame.getContentPane().add(btnAdvanced);
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
