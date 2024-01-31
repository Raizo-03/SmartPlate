package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonSlider {
    private JFrame frame;
    private JPanel buttonPanel;
    private List<JButton> buttons;
    private static final int BUTTON_COUNT = 10; // Number of buttons

    public ButtonSlider() {
        frame = new JFrame("Button Slider");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Create buttons and add them to a list
        buttons = new ArrayList<>();
        for (int i = 0; i < BUTTON_COUNT; i++) {
            JButton button = new JButton("Button " + (i + 1));
            buttons.add(button);
        }

        // Panel to hold the buttons
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        refreshButtons();

        // Scroll buttons
        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");

        // Add action listeners
        prevButton.addActionListener(e -> scrollLeft());
        nextButton.addActionListener(e -> scrollRight());

        // Add components to frame
        frame.add(prevButton, BorderLayout.WEST);
        frame.add(nextButton, BorderLayout.EAST);
        frame.add(new JScrollPane(buttonPanel), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void refreshButtons() {
        buttonPanel.removeAll();
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void scrollRight() {
        if (!buttons.isEmpty()) {
            JButton firstButton = buttons.remove(0);
            buttons.add(firstButton);
            refreshButtons();
        }
    }

    private void scrollLeft() {
        if (!buttons.isEmpty()) {
            JButton lastButton = buttons.remove(buttons.size() - 1);
            buttons.add(0, lastButton);
            refreshButtons();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ButtonSlider());
    }
}
