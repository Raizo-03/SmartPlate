package framesPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
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
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class foodUI {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					foodUI window = new foodUI();
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
	public foodUI() {
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
		
		
		
        ImagePanel panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\tofuImage2.png");
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
        
        frame.getContentPane().add(btnCook);
        frame.getContentPane().add(panel);
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
