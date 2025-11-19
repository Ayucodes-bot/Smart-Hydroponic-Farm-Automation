CREATE DATABASE IF NOT EXISTS hydroponic_farm;
USE hydroponic_farm;

CREATE TABLE IF NOT EXISTS plants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    type VARCHAR(50),
    growth_stage VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS sensor_data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    temperature DOUBLE,
    humidity DOUBLE,
    ph DOUBLE,
    nutrient_level DOUBLE,
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

