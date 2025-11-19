package com.hydroponic.models;

public class Plant {
    private int id;
    private String name;
    private String type;
    private String growthStage;

    public Plant() {}

    public Plant(int id, String name, String type, String growthStage) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.growthStage = growthStage;
    }

    public Plant(String name, String type, String growthStage) {
        this.name = name;
        this.type = type;
        this.growthStage = growthStage;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getGrowthStage() { return growthStage; }
    public void setGrowthStage(String growthStage) { this.growthStage = growthStage; }
}

