package com.hydroponic.services;

import com.hydroponic.dao.SensorDataDAO;
import com.hydroponic.models.SensorData;

import java.util.List;

public class MonitoringService {
    private final SensorDataDAO dao = new SensorDataDAO();

    public void saveReading(SensorData data) {
        dao.insert(data);
    }

    public List<SensorData> getRecentReadings(int n) {
        return dao.fetchLatest(n);
    }
}

