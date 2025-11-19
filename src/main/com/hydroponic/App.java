package com.hydroponic;

import com.hydroponic.dao.PlantDAO;
import com.hydroponic.models.Plant;
import com.hydroponic.sim.SensorSimulator;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting Smart Hydroponic Farm Automation (Review-1 demo)");

        // Create sample plant
        PlantDAO plantDAO = new PlantDAO();
        Plant p = new Plant("Lettuce", "Leafy", "Seedling");
        plantDAO.addPlant(p);
        System.out.println("Inserted plant with id: " + p.getId());

        // Start sensor simulator thread
        SensorSimulator simulator = new SensorSimulator(5000); // 5s interval
        Thread t = new Thread(simulator);
        t.setDaemon(true);
        t.start();

        // Keep main alive for demo (10 readings)
        try {
            Thread.sleep(30000); // run 30 seconds then exit
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Demo finished. Check DB for sensor_data entries.");
    }
}

