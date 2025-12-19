package com.hydroponic.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.hydroponic.dao.PlantDAO;
import com.hydroponic.dao.SensorDAO;
import com.hydroponic.dao.ScheduleDAO;
import com.hydroponic.util.DBConnection;
import com.hydroponic.model.Plant;
import com.hydroponic.model.Schedule;

/**
 * Service layer for Smart Hydroponic Farm Automation
 * Handles business logic, validation, and JDBC transactions
 */
public class HydroponicService {

    private PlantDAO plantDAO;
    private SensorDAO sensorDAO;
    private ScheduleDAO scheduleDAO;

    public HydroponicService() {
        this.plantDAO = new PlantDAO();
        this.sensorDAO = new SensorDAO();
        this.scheduleDAO = new ScheduleDAO();
    }

    /**
     * Adds a new plant with validation
     */
    public void addPlant(Plant plant) throws Exception {
        validatePlant(plant);
        plantDAO.insertPlant(plant);
    }

    /**
     * Applies a watering/nutrient schedule using JDBC transaction management
     */
    public void applySchedule(Schedule schedule) throws Exception {
        validateSchedule(schedule);

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // START TRANSACTION

            scheduleDAO.insertSchedule(conn, schedule);
            plantDAO.updateLastScheduled(conn, schedule.getPlantId());

            conn.commit(); // COMMIT TRANSACTION

        } catch (Exception e) {
            if (conn != null) {
                conn.rollback(); // ROLLBACK on failure
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    /**
     * Basic automation rule (innovation)
     */
    public String analyzePH(double ph) {
        if (ph < 5.5) {
            return "pH too low. Increase nutrient concentration.";
        } else if (ph > 6.5) {
            return "pH too high. Add fresh water.";
        }
        return "pH level optimal.";
    }

    // ---------------- VALIDATION ----------------

    private void validatePlant(Plant plant) {
        if (plant.getName() == null || plant.getName().isEmpty()) {
            throw new IllegalArgumentException("Plant name is required");
        }
        if (plant.getOptimalPH() < 0 || plant.getOptimalPH() > 14) {
            throw new IllegalArgumentException("Invalid pH value");
        }
    }

    private void validateSchedule(Schedule schedule) {
        if (schedule.getDurationMinutes() <= 0) {
            throw new IllegalArgumentException("Schedule duration must be positive");
        }
    }
}
