package com.hydroponic.dao;

import com.hydroponic.models.SensorData;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SensorDataDAO {

    public void insert(SensorData data) {
        String sql = "INSERT INTO sensor_data(temperature, humidity, ph, nutrient_level) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, data.getTemperature());
            ps.setDouble(2, data.getHumidity());
            ps.setDouble(3, data.getPh());
            ps.setDouble(4, data.getNutrientLevel());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("insert sensor_data error: " + e.getMessage());
        }
    }

    public List<SensorData> fetchLatest(int limit) {
        List<SensorData> list = new ArrayList<>();
        String sql = "SELECT id, temperature, humidity, ph, nutrient_level, recorded_at FROM sensor_data ORDER BY recorded_at DESC LIMIT ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SensorData d = new SensorData();
                    d.setId(rs.getInt("id"));
                    d.setTemperature(rs.getDouble("temperature"));
                    d.setHumidity(rs.getDouble("humidity"));
                    d.setPh(rs.getDouble("ph"));
                    d.setNutrientLevel(rs.getDouble("nutrient_level"));
                    // convert timestamp to LocalDateTime
                    Timestamp ts = rs.getTimestamp("recorded_at");
                    if (ts != null) d.setRecordedAt(ts.toLocalDateTime());
                    list.add(d);
                }
            }
        } catch (SQLException e) {
            System.err.println("fetchLatest error: " + e.getMessage());
        }
        return list;
    }
}

