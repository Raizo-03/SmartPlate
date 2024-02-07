package framesPackage;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.DashboardForm;
import main.DatabaseConnection;
import main.SessionManager;
import main.User;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import com.mysql.cj.jdbc.Blob;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;

public class meFrame {

	private JFrame frame;
	static final User String = null;
    private User currentUser;
    private String nameUser;
	private JLabel nameLabel;
    private JTextField txtName;
    private String userNplaceholder; 
    private String emailU;
    private String level;
    private JTextField txtEmail;
    private JTextField txtSkill;
    private String selectedImagePath = ""; // Add this at the class level
    private JLabel lblImage;
    private JPanel panel;
    private ImageIcon format=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					meFrame window = new meFrame(String);
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
	public meFrame(User user) {
		initialize();
	    // Fetch authenticated user from SessionManager
	    currentUser = SessionManager.getCurrentUser();
	    // Check if the user is authenticated before displaying user info
	    if (currentUser != null) {
	        fetchinguserInformation();
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
	                    String name = resultSet.getString("name");
	                    String email = resultSet.getString("email");
	                    String levelU = resultSet.getString("cooking_level");
	                    Blob blob = (Blob) resultSet.getBlob("profile_picture");
                        ImageIcon imageIcon = null;
                        if (blob != null) {
                            byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                            imageIcon = new ImageIcon(imageBytes);
                            Image image = imageIcon.getImage().getScaledInstance(191, 205, Image.SCALE_SMOOTH);
                            imageIcon = new ImageIcon(image);
                            lblImage.setIcon(imageIcon);
                        }else {
                        	setDefaultImage();
                        }
	                    // Now you have the user information
	                    name = resultSet.getString("name");
	                    userNplaceholder = name;
	                    txtName.setText(userNplaceholder); // Update txtName with the user's name
	                    txtEmail.setText(email); // Update txtEmail with the user's email
	                    // Handle cooking level text
	                    
	                    switch (levelU) {
	                        case "beginner":
	                            txtSkill.setText("BEGINNER");
	                            break;
	                        case "intermediate":
	                            txtSkill.setText("INTERMEDIATE");
	                            break;
	                        case "advanced":
	                            txtSkill.setText("ADVANCED");
	                            break;
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private void setDefaultImage() {
	    // Optionally, log this event or handle it as per requirement
	    lblImage.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\defaultImage.png"));
	    lblImage.revalidate();
	    lblImage.repaint();
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
                
        panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\meImage.png");
        panel.setLayout(null);
        panel.setBounds(0, 0, 940, 788);
		
		JButton btnReturn = new JButton("");
		btnReturn.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnReturnIcon.png"));
		btnReturn.setOpaque(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DashboardForm window = new DashboardForm(currentUser);
				window.Show();
				frame.dispose();
			}
			
		});
		btnReturn.setBounds(626, 709, 112, 56);
		
		JButton btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnSaveIcon.png"));
		btnSave.setOpaque(false);
		btnSave.setContentAreaFilled(false);
		btnSave.setBorderPainted(false);
		btnSave.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Confirmation dialog
		        int confirm = JOptionPane.showConfirmDialog(
		            null, 
		            "Are you sure you want to update your profile?", 
		            "Confirm Profile Update", 
		            JOptionPane.YES_NO_OPTION
		        );

		        if (confirm == JOptionPane.YES_OPTION) {
		            // Proceed with the update if the user confirms
		            boolean imageUpdated = false;
					try {
						imageUpdated = saveImageToDatabase();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            boolean nameUpdated = saveName(); // Assuming saveName() is also adapted to return a boolean
		            boolean emailUpdated = false;
					try {
						emailUpdated = saveEmail();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // Assuming saveName() is also adapted to return a boolean

		            if (imageUpdated && nameUpdated && emailUpdated) {
		                JOptionPane.showMessageDialog(null, "Profile updated successfully.");
		            } else {
		                // Handle the case where either update fails
		                JOptionPane.showMessageDialog(null, "Failed to update profile.");
		            }
		        }
		        // If the user selects "NO", nothing happens, and the dialog simply closes.
		    }
		});
		btnSave.setBounds(775, 709, 112, 56);
		
		
		
		 lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\defaultImage.png"));
		lblImage.setBounds(119, 251, 191, 205);		
		JButton btnSetImage = new JButton();
		btnSetImage.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showOpenDialog(frame);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            selectedImagePath = selectedFile.getAbsolutePath(); // Store selected image path
		            try {
		                ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
		                Image originalImage = originalIcon.getImage();

		                // Calculate the new dimensions while preserving aspect ratio.
		                int originalWidth = originalImage.getWidth(null);
		                int originalHeight = originalImage.getHeight(null);
		                double aspectRatio = (double) originalWidth / originalHeight;

		                int labelWidth = lblImage.getWidth();
		                int labelHeight = lblImage.getHeight();
		                int newWidth, newHeight;

		                // Determine how to scale the image.
		                double labelAspectRatio = (double) labelWidth / labelHeight;
		                if (labelAspectRatio > aspectRatio) {
		                    // Label is wider than the image (relative to their heights).
		                    newHeight = labelHeight;
		                    newWidth = (int) (labelHeight * aspectRatio);
		                } else {
		                    // Label is narrower than the image (relative to their heights) or equal.
		                    newWidth = labelWidth;
		                    newHeight = (int) (labelWidth / aspectRatio);
		                }

		                Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		                ImageIcon resizedIcon = new ImageIcon(resizedImage);

		                // Set the icon to the label, which will be centered by default.
		                lblImage.setIcon(resizedIcon);
		                // Ensure the text position and horizontal alignment are set to center if not already.
		                lblImage.setHorizontalTextPosition(JLabel.CENTER);
		                lblImage.setHorizontalAlignment(JLabel.CENTER);
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                // Consider showing an error dialog or logging
		            }
		        }
		    }
		});
		
		txtSkill = new JTextField();
		txtSkill.setText((String) null);
		txtSkill.setForeground(new Color(241, 143, 46));
		txtSkill.setFont(new Font("Dialog", Font.PLAIN, 30));
		txtSkill.setColumns(10);
		txtSkill.setBorder(BorderFactory.createEmptyBorder());
		txtSkill.setBackground(new Color(252, 246, 219));
		txtSkill.setBounds(420, 422, 349, 45);
		txtSkill.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			    if (txtSkill.getText().equals(level)) {
			    	txtSkill.setText("");
                }				
			}
			@Override
			public void focusLost(FocusEvent e) {
				   if (txtSkill.getText().isEmpty()) {
					   txtSkill.setText(level);
	                	
	                }				
			}
        });
		frame.getContentPane().add(txtSkill);
		
		txtEmail = new JTextField();
		txtEmail.setText(emailU);
		txtEmail.setForeground(new Color(241, 143, 46));
		txtEmail.setFont(new Font("Fredoka One", Font.PLAIN, 30));
		txtEmail.setColumns(10);
		txtEmail.setBorder(BorderFactory.createEmptyBorder());
		txtEmail.setBackground(new Color(252, 246, 219));
		txtEmail.setBounds(420, 329, 349, 45);
		txtEmail.setText(emailU); // Set initial placeholder text
        // Add a focus listener to handle placeholder behavior
		txtEmail.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			    if (txtEmail.getText().equals(txtEmail)) {
			    	txtEmail.setText("");
                }				
			}
			@Override
			public void focusLost(FocusEvent e) {
				   if (txtEmail.getText().isEmpty()) {
					   txtEmail.setText(emailU);
	                	
	                }				
			}
        });
		frame.getContentPane().add(txtEmail);
		
		
		btnSetImage.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnSetImageIcon.png"));
		btnSetImage.setBounds(335, 398, 56, 58);
		frame.getContentPane().add(btnSetImage);
		btnSetImage.setOpaque(false);
		btnSetImage.setContentAreaFilled(false);
		btnSetImage.setBorderPainted(false);
		frame.getContentPane().add(lblImage);

	
		txtName = new JTextField();
		txtName.setBounds(420, 235, 349, 45);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		txtName.setBorder(BorderFactory.createEmptyBorder());
		txtName.setForeground(new Color(241, 143, 46));
		txtName.setBackground(new Color(252, 246, 219));
	        
	        
	        try {
				Font font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\fonts\\FredokaOne-Regular.otf")).deriveFont(30f);
	            // Register the font with the Graphics Environment
	            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	            ge.registerFont(font);
	            txtName.setFont(font);
	            txtEmail.setFont(font);
	            txtSkill.setFont(font);


			} catch (FontFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        txtName.setText(userNplaceholder); 
		
		panel.add(btnSave);
		panel.add(txtName);
		panel.add(txtEmail);
		panel.add(txtSkill);
		panel.add(btnReturn);
		
		frame.getContentPane().add(panel);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(panel);

	}
	
	public void Show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					meFrame window = new meFrame(currentUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}
	public boolean saveImageToDatabase() throws IOException {
	    if (currentUser != null && selectedImagePath != null && !selectedImagePath.isEmpty()) {
	        String sql = "UPDATE UserAccounts SET profile_picture = ? WHERE username = ?";

	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(sql);
	             FileInputStream fis = new FileInputStream(selectedImagePath)) { // Create a FileInputStream for the image

	            // Set the binary stream for the profile_picture column
	            File imageFile = new File(selectedImagePath);
	            pstmt.setBinaryStream(1, fis, (int) imageFile.length());
	            pstmt.setString(2, currentUser.getUsername()); // Use username to identify the user

	            int affectedRows = pstmt.executeUpdate();
	            if (affectedRows > 0) {
	                return true;
	            } else {
	                return false;
	            }
	        } catch (SQLException | FileNotFoundException ex) {
	            ((Throwable) ex).printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error updating profile picture.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "No image selected or user not set.");
	    }
	    return false;
	}

	public boolean saveName() {
	    if (currentUser != null && txtName.getText() != null) {
	        String sql = "UPDATE UserAccounts SET name= ? WHERE username = ?";

	        String name = txtName.getText();
	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {

	            pstmt.setString(1, name);
	            pstmt.setString(2, currentUser.getUsername()); // Use username to identify the user

	            int affectedRows = pstmt.executeUpdate();
	            if (affectedRows > 0) {
	            	return true;
	            } else {
	            	return false;
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error updating profile picture.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "No image selected or user not set.");
	    }
	    return false;
	}
	public boolean saveEmail() throws SQLException {
	    if (currentUser != null && txtEmail.getText() != null && !txtEmail.getText().trim().isEmpty()) {
	        String sql = "UPDATE UserAccounts SET email= ? WHERE username = ?";
	        String checkEmailSQL = "SELECT email FROM UserAccounts WHERE email = ? AND username != ?";
	        String email = txtEmail.getText().trim();
	        
	        try (Connection connection = DatabaseConnection.getConnection()) {
	            // Check for duplicate email
	            try (PreparedStatement checkStmt = connection.prepareStatement(checkEmailSQL)) {
	                checkStmt.setString(1, email);
	                checkStmt.setString(2, currentUser.getUsername()); // Exclude current user
	                
	                ResultSet resultSet = checkStmt.executeQuery();
	                if (resultSet.next()) {
	                    JOptionPane.showMessageDialog(null, "This email is already in use by another account.", "Duplicate Email Error", JOptionPane.ERROR_MESSAGE);
	                    return false;
	                }
	            }catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error updating profile picture.");
		        }
	            
	        try (Connection connection1 = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = connection1.prepareStatement(sql)) {

	            pstmt.setString(1, email);
	            pstmt.setString(2, currentUser.getUsername()); // Use username to identify the user

	            int affectedRows = pstmt.executeUpdate();
	            if (affectedRows > 0) {
	            	return true;
	            } else {
	            	return false;
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error updating profile picture.");
	        }
	        }
	    
	
	    }
		return false;
		}
}
