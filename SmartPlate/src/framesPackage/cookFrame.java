package framesPackage;

import main.*;
import main.SessionManager;
import main.DatabaseConnection;
import main.User;
import main.UserAuthentication;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import java.awt.Font;

public class cookFrame {

	private JFrame frame;
	static final User String = null;
    private User currentUser;
    private JLabel lblName;
    DatabaseConnection base = new DatabaseConnection();
    private String name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cookFrame window = new cookFrame(String);
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
	public cookFrame(User user) {
	    currentUser = SessionManager.getCurrentUser();
		initialize();

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
                        lblName.setText(name);
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
        frame.getContentPane().setLayout(null);
        
        
        JPanel panel= new JPanel();
        panel.setLayout(null);

        lblName = new JLabel();
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblName.setBounds(235, 206, 233, 85);
        panel.add(lblName);
        
        frame.setUndecorated(true);
        ImageIcon AppIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
        frame.setIconImage(AppIcon.getImage());
        
        frame.getContentPane().add(panel);
        
	}

	public void Show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cookFrame window = new cookFrame(currentUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});			
	}
}
