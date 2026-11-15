package Assignment_16_Bridge;

import Assignment_16_Bridge.devices_devices_devices_devices_devices_devices.Device;
import Assignment_16_Bridge.devices_devices_devices_devices_devices_devices.Radio;
import Assignment_16_Bridge.devices_devices_devices_devices_devices_devices.SmartTV;
import Assignment_16_Bridge.devices_devices_devices_devices_devices_devices.Tv;
import Assignment_16_Bridge.remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes.AdvancedRemote;
import Assignment_16_Bridge.remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes.BasicRemote;
import Assignment_16_Bridge.remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes.Remote;
import Assignment_16_Bridge.remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes_remotes.SmartRemote;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   BRIDGE PATTERN APPLICATION");
        System.out.println("=================================");

        // Step 1: Select device
        Device device = selectDevice();

        // Step 2: Select remote
        Remote remote = selectRemote(device);

        // Step 3: Start interactive menu
        runApplication(device, remote);

        scanner.close();
    }

    private static Device selectDevice() {

        while (true) {
            System.out.println("\nSelect a device:");
            System.out.println("1. TV");
            System.out.println("2. Radio");
            System.out.println("3. Smart TV");

            System.out.print("Your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("TV selected.");
                    return new Tv();

                case "2":
                    System.out.println("Radio selected.");
                    return new Radio();

                case "3":
                    System.out.println("Smart TV selected.");
                    return new SmartTV();

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static Remote selectRemote(Device device) {

        while (true) {
            System.out.println("\nSelect a remote control:");
            System.out.println("1. Basic Remote");
            System.out.println("2. Advanced Remote");
            System.out.println("3. Smart Remote");

            System.out.print("Your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Basic Remote selected.");
                    return new BasicRemote(device);

                case "2":
                    System.out.println("Advanced Remote selected.");
                    return new AdvancedRemote(device);

                case "3":
                    System.out.println("Smart Remote selected.");
                    return new SmartRemote(device);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void runApplication(Device device, Remote remote) {

        boolean running = true;

        while (running) {

            System.out.println("\n========== CONTROL MENU ==========");
            System.out.println("1. Power ON/OFF");
            System.out.println("2. Volume UP");
            System.out.println("3. Volume DOWN");
            System.out.println("4. Channel UP");
            System.out.println("5. Channel DOWN");

            if (remote instanceof AdvancedRemote) {
                System.out.println("6. Mute");
            }

            if (remote instanceof SmartRemote) {
                System.out.println("7. Voice Control");
            }

            System.out.println("8. Show Device Status");
            System.out.println("0. Exit");

            System.out.print("Choose an action: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    remote.power();
                    break;

                case "2":
                    remote.volumeUp();
                    break;

                case "3":
                    remote.volumeDown();
                    break;

                case "4":
                    remote.channelUp();
                    break;

                case "5":
                    remote.channelDown();
                    break;

                case "6":
                    if (remote instanceof AdvancedRemote) {
                        ((AdvancedRemote) remote).mute();
                    } else {
                        System.out.println("Option unavailable.");
                    }
                    break;

                case "7":
                    if (remote instanceof SmartRemote) {
                        System.out.print("Enter voice command: ");
                        String command = scanner.nextLine();

                        ((SmartRemote) remote).voiceControl(command);
                    } else {
                        System.out.println("Option unavailable.");
                    }
                    break;

                case "8":
                    device.printStatus();
                    break;

                case "0":
                    running = false;
                    System.out.println("Application closed.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}