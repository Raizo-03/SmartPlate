package framesPackage;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mysql.cj.jdbc.Blob;
import main.DashboardForm;
import main.DatabaseConnection;
import main.SessionManager;
import main.User;
public class trackerFrame {

	private JFrame frame;
	private JLabel totalSpent;
	static final User String = null;
    private User currentUser;
    private JLabel lblName;
    DatabaseConnection base = new DatabaseConnection();
    private String name, userName;
    private JPanel panel;
    private JTable historyTable;
    private int userId; // Add a variable to store user_id
    DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trackerFrame window = new trackerFrame(String);
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
	public trackerFrame(User user) {
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
	                        name = resultSet.getString("name");
	                        userName = username;
	                        userId = resultSet.getInt("user_id"); // Fetch and store user_id
	            	        populateHistoryTable(tableModel, userId);

	                    } else {
	                        // Handle case where user is not found
	                        System.out.println("User not found");
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
        frame.getContentPane().setLayout(null);
        
        
        panel = new ImagePanel("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\trackerImage.png");
        panel.setBounds(0, 0, 940, 788);
        panel.setLayout(null);
        // Initialize the table model without the "Price" column
       tableModel = new DefaultTableModel(new Object[]{"Image", "Dish Name", "Price"}, 0) {
    	   @Override
    	   public boolean isCellEditable(int row, int column) {
        return false; // This makes the table non-editable
    	   }
       };

       historyTable = new JTable(tableModel);

       // Prevent column resizing
       historyTable.getTableHeader().setResizingAllowed(false);

       // Prevent column reordering
       historyTable.getTableHeader().setReorderingAllowed(false);

       historyTable = new JTable();
       historyTable.setBounds(213, 535, 1, 1);
       historyTable.setRowHeight(200);
       frame.getContentPane().add(historyTable);
       historyTable.setModel(tableModel);
       Color blue = new Color(190, 237, 255); // This is an example color; adjust as needed

       historyTable.setBackground(blue);
       // Customizing the table header appearance


       JTableHeader tableHeader = historyTable.getTableHeader();
       tableHeader.setBackground(blue); // Set the background of the header
       tableHeader.setForeground(Color.BLACK); // Set the text color of the header, if needed
       tableHeader.setOpaque(true); // Make the header opaque to display the background color
       


       DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
       centerRenderer.setHorizontalAlignment(JLabel.CENTER);
       centerRenderer.setBackground(blue); // Set the background color for cells
    
       // Apply the renderer to the second column (dish name)
       historyTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
       historyTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

    
       JScrollPane scrollPane = new JScrollPane(historyTable);
       scrollPane.setBounds(371, 165, 537, 520);
       frame.getContentPane().add(scrollPane);
       historyTable.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
       try {
    	   // Load and create the font
    	   Font font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\fonts\\FredokaOne-Regular.otf")).deriveFont(17f);      
    	   // Apply the font to the JTable
    	   historyTable.setFont(font);
    
    	   // Apply the font to the table header
    	   historyTable.getTableHeader().setFont(font);
    
    
       } catch (FontFormatException | IOException e) {
    	   e.printStackTrace();
       }


       JButton btnMenu = new JButton("");
       btnMenu.setIcon(new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\btnMenu.png"));
       btnMenu.setBounds(782, 709, 89, 38);
       btnMenu.setOpaque(false);
       btnMenu.setContentAreaFilled(false);
       btnMenu.setBorderPainted(false);
       btnMenu.addActionListener(new ActionListener() {

    	   @Override
		public void actionPerformed(ActionEvent e) {
		DashboardForm dash = new DashboardForm(currentUser);
		dash.Show();
		frame.dispose();
		
    	   	}
	
       });
       frame.getContentPane().add(btnMenu);

       frame.getContentPane().add(panel);
       frame.getContentPane().add(panel);
        
		
		
	}
	private void populateHistoryTable(DefaultTableModel tableModel, int user_id) {
	    if (currentUser != null) {
	        try (Connection connection = DatabaseConnection.getConnection()) {
	            String sql = "SELECT DISH.dish_name, DISH.image, DISH.price FROM UserDishSelections " + // Removed DISH.price from the SELECT
	                    "JOIN DISH ON UserDishSelections.dish_id = DISH.id " +
	                    "WHERE UserDishSelections.user_id = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setInt(1, userId);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        String dishName = resultSet.getString("dish_name");
	                        Blob blob = (Blob) resultSet.getBlob("image");
	                        BigDecimal price = resultSet.getBigDecimal("price");
	                        ImageIcon imageIcon = null;
	                        if (blob != null) {
	                            byte[] imageBytes = blob.getBytes(1, (int) blob.length());
	                            imageIcon = new ImageIcon(imageBytes);
	                            Image image = imageIcon.getImage().getScaledInstance(150, 350, Image.SCALE_SMOOTH);
	                            imageIcon = new ImageIcon(image);
	                        }
	                        // Removed fetching and printing of price
	                        tableModel.addRow(new Object[]{imageIcon, dishName, price}); // Removed price from the added row
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("SQLException: " + e.getMessage());
	        }
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
    
    
	public void Show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trackerFrame window = new trackerFrame(currentUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}

}
