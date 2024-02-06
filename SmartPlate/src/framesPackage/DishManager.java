package framesPackage;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.DatabaseConnection;

public class DishManager {

    public static void updateDishImage(String dishName, File imageFile) {
        String sql = "UPDATE DISH SET image = ? WHERE dish_name = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            FileInputStream fis = new FileInputStream(imageFile);
            pstmt.setBlob(1, fis);
            pstmt.setString(2, dishName);
            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                System.out.println("Image updated for dish: " + dishName);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Call this method to update images for all dishes
    public static void updateAllDishImages() {
        updateDishImage("SIZZLING TOFU", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\tofuImage.jpg"));
        updateDishImage("CHOP SEUY", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\chopsueyImage.jpg"));
        updateDishImage("CREAMY CHICKEN", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\creamyImage.jpg"));
        updateDishImage("SINIGANG NA BABOY", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\sinigangImage.jpg"));
        updateDishImage("CALAMANSI FISH FILLET", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\calamansiImage.png"));
        updateDishImage("TOKWAT KANGKONG", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\tokwaImage.jpg"));
        updateDishImage("BASIC GINILING", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\ginilingImage.jpg"));
        updateDishImage("QUICK PASTA", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\pastaImage.jpg"));
        updateDishImage("SANDWICH", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\monteImage.jpg"));
        updateDishImage("TOFU AND BROCOLLI STIR FRY", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\broccoliImage.jpg"));
        updateDishImage("GINISANG UPO", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\upoImage.jpg")); // Assuming this is for GINISANG UPO, adjust if the name is incorrect
        updateDishImage("SPICY EGGPLANT", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\eggplantImage.jpg"));
        updateDishImage("CRISPY SWEET AND SOUR PORK", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\sweetsourImage.jpg"));
        updateDishImage("FRIED EGG", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\eggImage.jpg"));
        updateDishImage("CORNEDBEEF WITH EGG", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\cornedbeefImage.jpg"));
        updateDishImage("FRIED TUNA", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\tunaImage.jpg"));
        updateDishImage("CHAO FAN", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\chaofanImage.jpg"));
        updateDishImage("PAKBET", new File("C:\\Users\\USER\\git\\SmartPlate\\SmartPlate\\dishes\\pakbetImage.jpg"));


    }
    
    // Static block or a separate method call to update images at an appropriate time
    static {
        updateAllDishImages();
    }

    // Other DishManager methods...
}
