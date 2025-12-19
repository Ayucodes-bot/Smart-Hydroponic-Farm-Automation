package com.hydroponic.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.hydroponic.dao.PlantDAO;
import com.hydroponic.dao.SensorDAO;
import com.hydroponic.util.DBConnection;
import com.hydroponic.model.Plant;
import com.hydroponic.model.SensorData;

public class HydroponicService {

    private PlantDAO plantDAO;
    private SensorDAO sensorDAO;

    public HydroponicService() {
        this.plantDAO = new PlantDAO();
        this.sensorDAO = new SensorDAO();
    }

    // Core business operation with transaction management
    public void addPlantWithInitialSensorData(Plant plant, SensorData sensorData) throws Exception {

        // -------- Server-side validation --------
        if (plant == null) {
            throw new IllegalArgumentException("Plant data cannot be null");
        }

        if (sensorData.getPh() < 0 || sensorData.getPh() > 14) {
            throw new IllegalArgumentException("Invalid pH value");
        }

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // START TRANSACTION

            plantDAO.insertPlant(conn, plant);
            sensorDAO.insertSensorData(conn, sensorData);

            conn.commit(); // COMMIT if all succeed

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback(); // ROLLBACK on failure
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
