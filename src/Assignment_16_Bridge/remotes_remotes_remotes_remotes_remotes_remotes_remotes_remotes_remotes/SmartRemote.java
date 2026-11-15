package Assignment_16_Bridge.remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes;

import Assignment_16_Bridge.devices_devices_devices_devices_devices_devices.Device;
import Assignment_16_Bridge.devices_devices_devices_devices_devices_devices.SmartTV;

public class SmartRemote extends AdvancedRemote {

    public SmartRemote(Device device) {
        super(device);
    }

    public void voiceControl(String command) {

        if (command == null) {
            System.out.println("Invalid command.");
            return;
        }

        switch (command.trim().toLowerCase()) {

            case "turn on":
                if (!device.isEnabled()) {
                    power();
                } else {
                    System.out.println("Device is already ON.");
                }
                break;

            case "turn off":
                if (device.isEnabled()) {
                    power();
                } else {
                    System.out.println("Device is already OFF.");
                }
                break;

            case "volume up":
                volumeUp();
                break;

            case "volume down":
                volumeDown();
                break;

            case "channel up":
                channelUp();
                break;

            case "channel down":
                channelDown();
                break;

            case "mute":
                mute();
                break;

            case "browse internet":

                if (device instanceof SmartTV) {
                    ((SmartTV) device).browseInternet();
                } else {
                    System.out.println(
                            "Internet browsing is only available on SmartTV."
                    );
                }
                break;

            default:
                System.out.println("Unknown voice command.");
                System.out.println("Available commands:");
                System.out.println(
                        "turn on, turn off, volume up, volume down,"
                );
                System.out.println(
                        "channel up, channel down, mute, browse internet"
                );
        }
    }
}

