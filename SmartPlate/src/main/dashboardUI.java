package main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;

public class dashboardUI {

	private JFrame frame;
	private JLabel nameLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboardUI window = new dashboardUI();
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
	public dashboardUI() {
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
        
        JPanel panel = new JPanel();
        panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\Homepage.png");
        panel.setLayout(null);
        panel.setBounds(0, 0, 940, 788);

        nameLabel = new JLabel("");
        nameLabel.setBounds(645, 11, 174, 25);
        panel.add(nameLabel);
        
        frame.getContentPane().add(panel);
        
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\USER\\Downloads\\double-arrows (2) (1).png");

        JButton btnRight = new JButton(imageIcon);	
        btnRight.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Clicked!");
        	}
        });
        btnRight.setBounds(579, 465, 60, 53);
        frame.getContentPane().add(btnRight);
        panel.add(btnRight);
        // Make the button non-opaque
        btnRight.setOpaque(false);

        // Make the button's content area transparent
        btnRight.setContentAreaFilled(false);

        // Make the button's border transparent
        btnRight.setBorderPainted(false);
        
        ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\USER\\Downloads\\arrow-left (2) (1).png");

        JButton btnLeft = new JButton(imageIcon1);
        btnLeft.setOpaque(false);
        btnLeft.setContentAreaFilled(false);
        btnLeft.setBorderPainted(false);
        btnLeft.setBounds(237, 465, 60, 53);
        frame.getContentPane().add(btnLeft);
        panel.add(btnLeft);



	
	}
}
