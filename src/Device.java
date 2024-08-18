public abstract class Device implements Controllable {

    protected String name;
    protected boolean lightOn;

    public Device(String name) {
        this.name = name;
        this.lightOn = false;
    }

    public abstract void turnOn();
    public abstract void turnOff();
    public abstract String statusReport();

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return lightOn;
    }
}

class Light extends Device {
    private int brightness;

    public Light(String name) {
        super(name);
        this.brightness = 0;
    }

    @Override
    public void turnOn() {
        lightOn = true;
        brightness = 100;
    }

    @Override
    public void turnOff() {
        lightOn = false;
        brightness = 0;
    }

    @Override
    public String statusReport() {
        return name + " is " + (lightOn ? "on" : "off") + " (Brightness: " + brightness + "%)";
    }

    @Override
    public void control() {
        // Implementation for controlling light
    }

    @Override
    public void schedule() {
        // Implementation for scheduling light
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        if (brightness > 0) {
            lightOn = true;
        } else {
            lightOn = false;
        }
    }
}

class Thermostat extends Device {
    private double temperature;

    public Thermostat(String name) {
        super(name);
        this.temperature = 20.0; // Default temperature in Celsius
    }

    @Override
    public void turnOn() {
        lightOn = true;
    }

    @Override
    public void turnOff() {
        lightOn = false;
    }

    @Override
    public String statusReport() {
        return name + " is " + (lightOn ? "on" : "off") + " (Temperature: " + temperature + "°C)";
    }

    @Override
    public void control() {
        // Implementation for controlling thermostat
    }

    @Override
    public void schedule() {
        // Implementation for scheduling thermostat
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        lightOn = true;
    }
}
