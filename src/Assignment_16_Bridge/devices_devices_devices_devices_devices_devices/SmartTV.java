package Assignment_16_Bridge.devices_devices_devices_devices_devices_devices;


public class SmartTV implements Device {

    private boolean on = false;
    private int volume = 30;
    private int channel = 1;
    private boolean browserOpen = false;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
    }

    @Override
    public void disable() {
        on = false;
        browserOpen = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = Math.max(1, channel);
    }

    public void browseInternet() {
        if (!on) {
            System.out.println("Turn on SmartTV first!");
            return;
        }

        browserOpen = true;
        System.out.println("Internet browser opened!");
        System.out.println("Welcome to the SmartTV browser.");
    }

    @Override
    public void printStatus() {
        System.out.println("\n--- SMART TV STATUS ---");
        System.out.println("Power: " + (on ? "ON" : "OFF"));
        System.out.println("Volume: " + volume + "%");
        System.out.println("Channel: " + channel);
        System.out.println("Browser: " +
                (browserOpen ? "OPEN" : "CLOSED"));
    }
}

