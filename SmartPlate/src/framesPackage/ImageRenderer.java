package framesPackage;

import java.awt.Component;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            JLabel label = new JLabel((Icon) value);
            label.setHorizontalAlignment(JLabel.CENTER);
            // Resize the icon to fit the cell size
            ImageIcon icon = (ImageIcon) value;
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(newImg));
            return label;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}