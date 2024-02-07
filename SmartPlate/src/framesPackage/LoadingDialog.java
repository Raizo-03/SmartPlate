package framesPackage;

import main.*;
import main.SessionManager;
import main.DatabaseConnection;
import main.User;
import main.UserAuthentication;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class loadingDialog{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loadingDialog window = new loadingDialog();
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
	public loadingDialog() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
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
	 
	 
	public void showSuggestingDialog() {
	    final JDialog loadingDialog = new JDialog();
	    loadingDialog.setTitle("Suggesting...");
	    loadingDialog.setModal(true);
	    loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    loadingDialog.setLocation(790, 500);
	    ImageIcon icon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
	    loadingDialog.setIconImage(icon.getImage());

	    JProgressBar progressBar = new JProgressBar();
	    progressBar.setPreferredSize(new Dimension(350, 20));
	    progressBar.setIndeterminate(true);
	    progressBar.setForeground(new Color(0, 168, 89));
	    loadingDialog.getContentPane().add(progressBar);
	    loadingDialog.pack();

	    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() throws Exception {
	            Thread.sleep(2000); // Simulate time-consuming task
	            return null;
	        }

	        @Override
	        protected void done() {
	            loadingDialog.dispose();
	        }
	    };

	    worker.execute();
	    loadingDialog.setVisible(true); // This will block until the SwingWorker is done
	}
	
	
	public void showCookingDialog() {
        final JDialog loadingDialog = new JDialog(frame, "Cooking...", true);
        loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        loadingDialog.setLocationRelativeTo(frame); // Center relative to the frame
        loadingDialog.setLocation(790, 500); // This positions the dialog at coordinates (100, 100) on the screen.
        loadingDialog.getContentPane().setBackground(Color.BLACK); // Set the background color of the content pane to black.

        ImageIcon icon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\SmartPlateLogo1.png");
        loadingDialog.setIconImage(icon.getImage());

        // Set the dialog layout
        loadingDialog.setLayout(new BorderLayout());

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(350, 30));
        progressBar.setForeground(new Color(0, 168, 89));
        loadingDialog.add(progressBar, BorderLayout.SOUTH);

        // Path to the GIF
        ImageIcon gifIcon = new ImageIcon("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\Assets\\cookingGif.gif");
        JLabel gifLabel = new JLabel(gifIcon);
        loadingDialog.add(gifLabel, BorderLayout.NORTH);

        loadingDialog.pack();

        // Use a SwingWorker to perform background task
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Perform time-consuming task here
                Thread.sleep(2000); // Simulate task duration
                return null;
            }

            @Override
            protected void done() {
                // Close the loading dialog
                loadingDialog.dispose();

                // Proceed with your next operations...
            }
        };

        worker.execute(); // Start the task execution

        // Display the loading dialog; it blocks here until disposed
        loadingDialog.setVisible(true);
	}

}
