package com.hydroponic.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.hydroponic.dao.PlantDAO;
import com.hydroponic.dao.SensorDAO;
import com.hydroponic.util.DBConnection;
import com.hydroponic.model.Plant;

public class HydroponicService {

    private PlantDAO plantDAO;
    private SensorDAO sensorDAO;

    public HydroponicService() {
        this.plantDAO = new PlantDAO();
        this.sensorDAO = new SensorDAO();
    }

    // Example: Add or update plant with transaction handling
    public void addOrUpdatePlant(Plant plant) throws Exception {

        // Server-side validation
        if (plant == null) {
            throw new IllegalArgumentException("Plant data cannot be null");
        }

        if (plant.getPhLevel() < 0 || plant.getPhLevel() > 14) {
            throw new IllegalArgumentException("Invalid pH value");
        }

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // START TRANSACTION

            plantDAO.insertOrUpdatePlant(conn, plant);
            sensorDAO.logInitialSensorData(conn, plant.getId());

            conn.commit(); // COMMIT
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback(); // ROLLBACK
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
