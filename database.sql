    -- ==================== SQL DATABASE SCHEMA ====================
    -- Run this in MySQL to create the database and tables
    
    -- Create database
    CREATE DATABASE IF NOT EXISTS fleet_management;
    USE fleet_management;
    
    -- Vehicles table
    CREATE TABLE IF NOT EXISTS vehicles (
        vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
        registration_number VARCHAR(20) NOT NULL UNIQUE,
        make VARCHAR(50) NOT NULL,
        model VARCHAR(50) NOT NULL,
        year INT NOT NULL,
        status ENUM('Active', 'Maintenance', 'Retired') DEFAULT 'Active',
        fuel_capacity DECIMAL(10,2) NOT NULL,
        current_mileage INT DEFAULT 0,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );
    
    -- Drivers table
    CREATE TABLE IF NOT EXISTS drivers (
        driver_id INT AUTO_INCREMENT PRIMARY KEY,
        first_name VARCHAR(50) NOT NULL,
        last_name VARCHAR(50) NOT NULL,
        license_number VARCHAR(50) NOT NULL UNIQUE,
        license_type VARCHAR(20) NOT NULL,
        phone_number VARCHAR(20),
        email VARCHAR(100),
        status ENUM('Active', 'Suspended', 'Inactive') DEFAULT 'Active',
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    
    -- Trips table
    CREATE TABLE IF NOT EXISTS trips (
        trip_id INT AUTO_INCREMENT PRIMARY KEY,
        vehicle_id INT NOT NULL,
        driver_id INT NOT NULL,
        start_location VARCHAR(100) NOT NULL,
        end_location VARCHAR(100) NOT NULL,
        start_time DATETIME NOT NULL,
        end_time DATETIME,
        distance_km DECIMAL(10,2) NOT NULL,
        fuel_used DECIMAL(10,2) DEFAULT 0,
        status ENUM('Planned', 'In Progress', 'Completed', 'Cancelled') DEFAULT 'Planned',
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id),
        FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
    );
    
    -- Sample data insertion
    INSERT INTO vehicles (registration_number, make, model, year, status, fuel_capacity, current_mileage) VALUES
    ('ABC123GP', 'Toyota', 'Hilux', 2022, 'Active', 80.0, 45000),
    ('DEF456GP', 'Ford', 'Ranger', 2021, 'Active', 75.0, 62000),
    ('GHI789GP', 'Isuzu', 'D-Max', 2023, 'Active', 70.0, 15000);
    
    INSERT INTO drivers (first_name, last_name, license_number, license_type, phone_number, email, status) VALUES
    ('John', 'Smith', 'DL123456', 'C1', '0821234567', 'john.smith@email.com', 'Active'),
    ('Sarah', 'Johnson', 'DL789012', 'C', '0839876543', 'sarah.j@email.com', 'Active'),
    ('Michael', 'Brown', 'DL345678', 'C1', '0844567890', 'mbrown@email.com', 'Active');
