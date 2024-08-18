import javax.swing.*;

public class SmartHomeAutomationSystem {
    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                SmartHomeGUI gui = new SmartHomeGUI();
                gui.setVisible(true);
            });

    }
}
