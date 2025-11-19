package com.hydroponic.dao;

import com.hydroponic.models.Plant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantDAO {

    public void addPlant(Plant plant) {
        String sql = "INSERT INTO plants(name, type, growth_stage) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, plant.getName());
            ps.setString(2, plant.getType());
            ps.setString(3, plant.getGrowthStage());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) plant.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("addPlant error: " + e.getMessage());
        }
    }

    public List<Plant> getAllPlants() {
        List<Plant> list = new ArrayList<>();
        String sql = "SELECT id, name, type, growth_stage FROM plants";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Plant(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("growth_stage")
                ));
            }
        } catch (SQLException e) {
            System.err.println("getAllPlants error: " + e.getMessage());
        }
        return list;
    }
}

