import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.xml.validation.Schema;
    
    /**
     * FleetManagementSystem.java - Main Application
     * Console-based fleet management system demonstrating all features
     */
public class FleetManagementSystem {
        
    private databaseManager dbManager;
        private Scanner scanner;
        
        public FleetManagementSystem() {
            this.dbManager = new databaseManager();
            this.scanner = new Scanner(System.in);
        }
        
        public void start() {
            System.out.println("\\n========================================");
            System.out.println("  FLEET MANAGEMENT SYSTEM v1.0");
            System.out.println("  TSS Technology Solutions Demo");
            System.out.println("========================================\\n");
            
            boolean running = true;
            while (running) {
                showMenu();
                int choice = getIntInput("Enter choice: ");
                
                switch (choice) {
                    case 1: addVehicle(); break;
                    case 2: viewAllVehicles(); break;
                    case 3: addDriver(); break;
                    case 4: viewAllDrivers(); break;
                    case 5: createTrip(); break;
                    case 6: completeTrip(); break;
                    case 7: viewVehicleTrips(); break;
                    case 8: generateVehicleReport(); break;
                    case 9: showFleetStatistics(); break;
                    case 0: 
                        running = false;
                        System.out.println("\\nThank you for using Fleet Management System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            
            dbManager.closeConnection();
            scanner.close();
        }
        
        private void showMenu() {
            System.out.println("\\n--- MAIN MENU ---");
            System.out.println("1. Add New Vehicle");
            System.out.println("2. View All Vehicles");
            System.out.println("3. Add New Driver");
            System.out.println("4. View All Drivers");
            System.out.println("5. Create New Trip");
            System.out.println("6. Complete Trip");
            System.out.println("7. View Vehicle Trip History");
            System.out.println("8. Generate Vehicle Report");
            System.out.println("9. Fleet Statistics");
            System.out.println("0. Exit");
            System.out.println("-----------------");
        }
        
        private void addVehicle() {
            System.out.println("\\n--- ADD NEW VEHICLE ---");
            
            String regNumber = getStringInput("Registration Number: ");
            String make = getStringInput("Make (e.g., Toyota): ");
            String model = getStringInput("Model (e.g., Hilux): ");
            int year = getIntInput("Year: ");
            double fuelCapacity = getDoubleInput("Fuel Capacity (liters): ");
            int mileage = getIntInput("Current Mileage (km): ");
            
            Vehicle vehicle = new Vehicle(regNumber, make, model, year, "Active", fuelCapacity, mileage);
            
            try {
                dbManager.addVehicle(vehicle);
                System.out.println("\\n✓ Vehicle added successfully!");
                System.out.println("  " + vehicle);
            } catch (SQLException e) {
                System.err.println("Error adding vehicle: " + e.getMessage());
            }
        }
        
        private void viewAllVehicles() {
            System.out.println("\\n--- ALL VEHICLES ---");
            
            try {
                List<Vehicle> vehicles = dbManager.getAllVehicles();
                if (vehicles.isEmpty()) {
                    System.out.println("No vehicles found.");
                } else {
                    System.out.printf("%-5s %-15s %-10s %-15s %-6s %-10s %-10s%n",
                        "ID", "Reg Number", "Make", "Model", "Year", "Status", "Mileage");
                    System.out.println(new String(new char[80]).replace("\0", "-"));
                    
                    for (Vehicle v : vehicles) {
                        System.out.printf("%-5d %-15s %-10s %-15s %-6d %-10s %-10d%n",
                            v.getVehicleId(), v.getRegistrationNumber(), v.getMake(),
                            v.getModel(), v.getYear(), v.getStatus(), v.getCurrentMileage());
                    }
                    System.out.println("\\nTotal vehicles: " + vehicles.size());
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving vehicles: " + e.getMessage());
            }
        }
        
        private void addDriver() {
            System.out.println("\\n--- ADD NEW DRIVER ---");
            
            String firstName = getStringInput("First Name: ");
            String lastName = getStringInput("Last Name: ");
            String license = getStringInput("License Number: ");
            String licenseType = getStringInput("License Type (e.g., C1, C): ");
            String phone = getStringInput("Phone Number: ");
            String email = getStringInput("Email: ");
            
            Driver driver = new Driver(firstName, lastName, license, licenseType, phone, email, "Active");
            
            try {
                dbManager.addDriver(driver);
                System.out.println("\\n✓ Driver added successfully!");
                System.out.println("  " + driver);
            } catch (SQLException e) {
                System.err.println("Error adding driver: " + e.getMessage());
            }
        }
        
        private void viewAllDrivers() {
            System.out.println("\\n--- ALL DRIVERS ---");
            
            try {
                List<Driver> drivers = dbManager.getAllDrivers();
                if (drivers.isEmpty()) {
                    System.out.println("No drivers found.");
                } else {
                    for (Driver d : drivers) {
                        System.out.println(d);
                    }
                    System.out.println("\\nTotal drivers: " + drivers.size());
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving drivers: " + e.getMessage());
            }
        }
        
        private void createTrip() {
            System.out.println("\\n--- CREATE NEW TRIP ---");
            
            int vehicleId = getIntInput("Vehicle ID: ");
            int driverId = getIntInput("Driver ID: ");
            String startLoc = getStringInput("Start Location: ");
            String endLoc = getStringInput("End Location: ");
            String startTime = getStringInput("Start Time (YYYY-MM-DD HH:MM): ");
            double distance = getDoubleInput("Estimated Distance (km): ");
            
            Trip trip = new Trip(vehicleId, driverId, startLoc, endLoc, startTime, distance);
            
            try {
                dbManager.addTrip(trip);
                System.out.println("\\n✓ Trip created successfully!");
                System.out.println("  Trip ID: " + trip.getTripId());
            } catch (SQLException e) {
                System.err.println("Error creating trip: " + e.getMessage());
            }
        }
        
        private void completeTrip() {
            System.out.println("\\n--- COMPLETE TRIP ---");
            
            int tripId = getIntInput("Trip ID: ");
            String endTime = getStringInput("End Time (YYYY-MM-DD HH:MM): ");
            double fuelUsed = getDoubleInput("Fuel Used (liters): ");
            
            try {
                dbManager.completeTrip(tripId, endTime, fuelUsed);
                System.out.println("\\n✓ Trip completed successfully!");
            } catch (SQLException e) {
                System.err.println("Error completing trip: " + e.getMessage());
            }
        }
        
        private void viewVehicleTrips() {
            System.out.println("\\n--- VEHICLE TRIP HISTORY ---");
            
            int vehicleId = getIntInput("Enter Vehicle ID: ");
            
            try {
                List<Trip> trips = dbManager.getTripsByVehicle(vehicleId);
                if (trips.isEmpty()) {
                    System.out.println("No trips found for this vehicle.");
                } else {
                    System.out.println("\\nTrip History for Vehicle ID: " + vehicleId);
                    System.out.println(new String(new char[80]).replace("\0", "-"));
                    for (Trip t : trips) {
                        System.out.println(t);
                    }
                    System.out.println("\\nTotal trips: " + trips.size());
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving trips: " + e.getMessage());
            }
        }
        
        private void generateVehicleReport() {
            System.out.println("\\n--- VEHICLE PERFORMANCE REPORT ---");
            
            int vehicleId = getIntInput("Enter Vehicle ID: ");
            
            try {
                Vehicle vehicle = dbManager.getVehicleById(vehicleId);
                if (vehicle == null) {
                    System.out.println("Vehicle not found.");
                    return;
                }
                
                double totalDistance = dbManager.getTotalDistanceByVehicle(vehicleId);
                double totalFuel = dbManager.getTotalFuelConsumed(vehicleId);
                double efficiency = dbManager.getAverageFuelEfficiency(vehicleId);
                
                System.out.println("\\n" + new String(new char[50]).replace("\0", "="));
                System.out.println("  VEHICLE PERFORMANCE REPORT");
                System.out.println(new String(new char[50]).replace("\0", "="));
                System.out.println("Vehicle: " + vehicle.getMake() + " " + vehicle.getModel());
                System.out.println("Registration: " + vehicle.getRegistrationNumber());
                System.out.println("Current Mileage: " + vehicle.getCurrentMileage() + " km");
                System.out.println(new String(new char[50]).replace("\0", "-"));
                System.out.println("Total Distance Traveled: " + String.format("%.1f", totalDistance) + " km");
                System.out.println("Total Fuel Consumed: " + String.format("%.1f", totalFuel) + " liters");
                System.out.println("Average Fuel Efficiency: " + String.format("%.2f", efficiency) + " km/l");
                System.out.println("  FLEET OVERVIEW");
                System.out.println(new String(new char[50]).replace("\0", "="));
                
            } catch (SQLException e) {
                System.err.println("Error generating report: " + e.getMessage());
            }
        }
        
        private void showFleetStatistics() {
            System.out.println("\\n--- FLEET STATISTICS ---");
            
            try {
                int activeVehicles = dbManager.getActiveVehicleCount();
                List<Vehicle> allVehicles = dbManager.getAllVehicles();
                List<Driver> allDrivers = dbManager.getAllDrivers();
                
                System.out.println("\\n" + new String(new char[40]).replace("\0", "="));
                System.out.println("  FLEET OVERVIEW");
                System.out.println(new String(new char[40]).replace("\0", "="));
                System.out.println("Total Vehicles: " + allVehicles.size());
                System.out.println("Active Vehicles: " + activeVehicles);
                System.out.println("Total Drivers: " + allDrivers.size());
                System.out.println("  FLEET OVERVIEW");
                System.out.println(new String(new char[40]).replace("\0", "="))
                
            } catch (SQLException e) {
                System.err.println("Error retrieving statistics: " + e.getMessage());
            }
        }
        
        // Helper methods for input
        private String getStringInput(String prompt) {
            System.out.print(prompt);
            return scanner.nextLine().trim();
        }
        
        private int getIntInput(String prompt) {
            while (true) {
                try {
                    System.out.print(prompt);
                    return Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
        }
        
        private double getDoubleInput(String prompt) {
            while (true) {
                try {
                    System.out.print(prompt);
                    return Double.parseDouble(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
        }
        
        public static void main(String[] args) {
            FleetManagementSystem system = new FleetManagementSystem();
            system.start();
        }
    }
    