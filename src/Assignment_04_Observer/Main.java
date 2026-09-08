package Assignment_04_Observer;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        WeatherStation station = new WeatherStation();

        TemperatureDisplay display1 =
                new TemperatureDisplay("Display 1");

        TemperatureDisplay display2 =
                new TemperatureDisplay("Display 2");

        WeatherAlert alert = new WeatherAlert();

        station.registerObserver(display1);
        station.registerObserver(display2);
        station.registerObserver(alert);

        station.start();

        Thread.sleep(15000);

        System.out.println("\n--- Removing Display 2 ---\n");

        station.removeObserver(display2);

        Thread.sleep(15000);

        System.out.println("\n--- Simulation finished ---");

        station.interrupt();
    }
}