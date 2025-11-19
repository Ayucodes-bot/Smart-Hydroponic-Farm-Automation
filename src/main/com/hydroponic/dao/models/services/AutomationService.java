package com.hydroponic.services;

import com.hydroponic.models.SensorData;

public class AutomationService {

    public void evaluateAndAct(SensorData data) {
        // Basic rule-based actions (demo). Replace with real control logic later.
        if (data.getPh() < 5.5) {
            System.out.println("[ACTION] pH low (" + data.getPh() + ") -> add base / reduce acid");
        } else if (data.getPh() > 6.8) {
            System.out.println("[ACTION] pH high (" + data.getPh() + ") -> add acid / reduce base");
        }

        if (data.getNutrientLevel() < 300) {
            System.out.println("[ACTION] Nutrient low (" + data.getNutrientLevel() + ") -> schedule nutrient mix");
        }
    }
}

