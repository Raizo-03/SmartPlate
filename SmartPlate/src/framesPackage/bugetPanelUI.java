package framesPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;



public class bugetPanelUI {

	private JFrame frame;
	private JTextField txtFieldBugdet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bugetPanelUI window = new bugetPanelUI();
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
	public bugetPanelUI() {
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
        frame.setUndecorated(true);
        ImageIcon AppIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
        frame.setIconImage(AppIcon.getImage());
        frame.getContentPane().setLayout(null);
        
        
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
                String newValue = String.valueOf(baseVal);
                txtFieldBugdet.setText(newValue);
			}
        	
        });
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
        
        
        
        frame.getContentPane().add(btnDecrease);
        
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
                String newValue = String.valueOf(baseVal);

                // Set the updated value back to the text field
                txtFieldBugdet.setText(newValue);
            }
        });

        
        frame.getContentPane().add(btnIncrease);
        
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
        
        
        frame.getContentPane().add(panel);

        
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
