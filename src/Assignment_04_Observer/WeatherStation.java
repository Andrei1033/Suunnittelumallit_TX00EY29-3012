package Assignment_04_Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherStation extends Thread {

    private static final int MinTemperature = -50;
    private static final int MaxTemperature = 50;

    private int temperature;
    private final List<WeatherObserver> observers = new ArrayList<>();
    private final Random random = new Random();

    public WeatherStation() {
        this.temperature = random.nextInt(MaxTemperature - MinTemperature + 1) + MinTemperature;
    }

    public void registerObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperature);
        }
    }

    private void changeTemperature() {
        int change = random.nextBoolean() ? 1 : -1;

        if (temperature + change >= MinTemperature && temperature + change <= MaxTemperature) {
            temperature += change;
            notifyObservers();
        }
    }

    @Override
    public void run() {
        while (true) {
            changeTemperature();
            try {
                int delay = random.nextInt(5) + 1;
                Thread.sleep(delay * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
