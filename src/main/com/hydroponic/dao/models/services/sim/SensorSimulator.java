package com.hydroponic.sim;

import com.hydroponic.models.SensorData;
import com.hydroponic.services.MonitoringService;
import com.hydroponic.services.AutomationService;

import java.util.Random;

public class SensorSimulator implements Runnable {
    private final MonitoringService monitor = new MonitoringService();
    private final AutomationService automation = new AutomationService();
    private final Random rnd = new Random();
    private final int intervalMs;

    public SensorSimulator(int intervalMs) {
        this.intervalMs = intervalMs;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SensorData s = new SensorData();
                s.setTemperature(20 + rnd.nextDouble() * 10);       // 20 - 30 C
                s.setHumidity(40 + rnd.nextDouble() * 30);          // 40 - 70 %
                s.setPh(5.5 + rnd.nextDouble() * 1.6);              // 5.5 - 7.1
                s.setNutrientLevel(250 + rnd.nextDouble() * 300);  // 250 - 550 ppm
                monitor.saveReading(s);
                automation.evaluateAndAct(s);
                Thread.sleep(intervalMs);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Simulator interrupted");
        }
    }
}

