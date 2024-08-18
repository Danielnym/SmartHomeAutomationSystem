import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SmartHomeGUI extends JFrame {
    ArrayList<Device> devices = new ArrayList<>();
    JPanel devicePanel;

    public SmartHomeGUI() {


        devices.add(new Light("Living Room Light"));
        devices.add(new Light("Kitchen Light"));
        devices.add(new Thermostat("Main Thermostat"));

        setTitle("Smart Home Automation System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGUI();
    }

     void createGUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each room
        JPanel livingRoom = createRoomPanel("Living Room");
        JPanel kitchen = createRoomPanel("Kitchen");

        tabbedPane.addTab("Living Room", livingRoom);
        tabbedPane.addTab("Kitchen", kitchen);

        add(tabbedPane);
    }

     JPanel createRoomPanel(String roomName) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Device device : devices) {
            if (device.getName().startsWith(roomName)) {
                JPanel devicePanel = createDevicePanel(device);
                panel.add(devicePanel);
            }
        }

        return panel;
    }

    JPanel createDevicePanel(Device device) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(device.getName()));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel statusLabel = new JLabel(device.statusReport());
        panel.add(statusLabel);

        JButton onOffButton = new JButton(device.isOn() ? "Turn Off" : "Turn On");
        onOffButton.addActionListener(e -> {
            if (device.isOn()) {
                device.turnOff();
                onOffButton.setText("Turn On");
            } else {
                device.turnOn();
                onOffButton.setText("Turn Off");
            }
            statusLabel.setText(device.statusReport());
        });
        panel.add(onOffButton);

        if (device instanceof Light) {
            JSlider brightnessSlider = new JSlider(0, 100, 0);
            brightnessSlider.addChangeListener(e -> {
                ((Light) device).setBrightness(brightnessSlider.getValue());
                statusLabel.setText(device.statusReport());
            });
            panel.add(brightnessSlider);
        } else if (device instanceof Thermostat) {
            JSpinner temperatureSpinner = new JSpinner(new SpinnerNumberModel(20, 10, 30, 0.5));
            temperatureSpinner.addChangeListener(e -> {
                ((Thermostat) device).setTemperature((double) temperatureSpinner.getValue());
                statusLabel.setText(device.statusReport());
            });
            panel.add(temperatureSpinner);
        }

        return panel;
    }

}
