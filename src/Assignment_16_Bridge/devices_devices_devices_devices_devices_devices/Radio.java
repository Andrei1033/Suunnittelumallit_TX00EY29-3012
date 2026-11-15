package Assignment_16_Bridge.devices_devices_devices_devices_devices_devices;


public class Radio implements Device {

    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

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

    @Override
    public void printStatus() {
        System.out.println("\n--- RADIO STATUS ---");
        System.out.println("Power: " + (on ? "ON" : "OFF"));
        System.out.println("Volume: " + volume + "%");
        System.out.println("Channel: " + channel);
    }
}
