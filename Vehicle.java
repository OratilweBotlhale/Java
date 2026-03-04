
/**
 * Vehicle Fleet Management System
 * A complete Java application with SQL database for managing vehicle fleets
 * Perfect for demonstrating skills to TSS Technology
 */
// Vehicle.java - Model Class
public class Vehicle {
    private int vehicleId;
    private String registrationNumber;
    private String make;
    private String model;
    private int year;
    private String status; // Active, Maintenance, Retired
    private double fuelCapacity;
    private int currentMileage;
    
    public Vehicle() {}
    
    public Vehicle(String registrationNumber, String make, String model, 
                   int year, String status, double fuelCapacity, int currentMileage) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.year = year;
        this.status = status;
        this.fuelCapacity = fuelCapacity;
        this.currentMileage = currentMileage;
    }
    
    // Getters and Setters
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { 
        this.registrationNumber = registrationNumber; 
    }
    
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public double getFuelCapacity() { return fuelCapacity; }
    public void setFuelCapacity(double fuelCapacity) { this.fuelCapacity = fuelCapacity; }
    
    public int getCurrentMileage() { return currentMileage; }
    public void setCurrentMileage(int currentMileage) { this.currentMileage = currentMileage; }
    
    @Override
    public String toString() {
        return String.format("Vehicle[id=%d, reg=%s, %s %s (%d), status=%s, mileage=%d]",
                vehicleId, registrationNumber, make, model, year, status, currentMileage);
    }
}

// Driver.java - Model Class
// Removed the Trip class. It is now in its own file named Trip.java.
