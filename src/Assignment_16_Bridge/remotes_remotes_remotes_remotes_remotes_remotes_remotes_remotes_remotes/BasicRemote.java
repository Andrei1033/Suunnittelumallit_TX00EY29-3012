package Assignment_16_Bridge.remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes;

import Assignment_16_Bridge.devices_devices_devices_devices_devices_devices.Device;

public class BasicRemote implements Remote {

    protected Device device;

    public BasicRemote(Device device) {
        this.device = device;
    }

    @Override
    public void power() {
        if (device.isEnabled()) {
            device.disable();
            System.out.println("Device turned OFF.");
        } else {
            device.enable();
            System.out.println("Device turned ON.");
        }
    }

    @Override
    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
        System.out.println("Volume decreased.");
    }

    @Override
    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
        System.out.println("Volume increased.");
    }

    @Override
    public void channelDown() {
        device.setChannel(device.getChannel() - 1);
        System.out.println("Channel decreased.");
    }

    @Override
    public void channelUp() {
        device.setChannel(device.getChannel() + 1);
        System.out.println("Channel increased.");
    }
}
