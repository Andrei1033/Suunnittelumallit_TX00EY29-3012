package Assignment_16_Bridge.remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes;


import Assignment_16_Bridge.devices_devices_devices_devices_devices_devices.Device;

public class AdvancedRemote extends BasicRemote {

    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
        System.out.println("Device muted.");
    }
}
