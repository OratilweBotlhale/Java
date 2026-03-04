
    

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseManager.java - Handles all SQL operations
 * Demonstrates: JDBC, CRUD operations, SQL queries, Connection pooling concepts
 */
public class databaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fleet_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password"; // Change in production
    
    private Connection connection;
    
    public databaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("✓ Database connection established");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
    
    // ==================== VEHICLE OPERATIONS ====================
    
    public void addVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicles (registration_number, make, model, year, status, " +
                     "fuel_capacity, current_mileage) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, vehicle.getRegistrationNumber());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setString(5, vehicle.getStatus());
            stmt.setDouble(6, vehicle.getFuelCapacity());
            stmt.setInt(7, vehicle.getCurrentMileage());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    vehicle.setVehicleId(generatedKeys.getInt(1));
                }
            }
            System.out.println("✓ Vehicle added: " + vehicle.getRegistrationNumber());
        }
    }
    
    public Vehicle getVehicleById(int vehicleId) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE vehicle_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractVehicleFromResultSet(rs);
            }
        }
        return null;
    }
    
    public List<Vehicle> getAllVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles ORDER BY vehicle_id";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                vehicles.add(extractVehicleFromResultSet(rs));
            }
        }
        return vehicles;
    }
    
    public List<Vehicle> getVehiclesByStatus(String status) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE status = ? ORDER BY vehicle_id";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                vehicles.add(extractVehicleFromResultSet(rs));
            }
        }
        return vehicles;
    }
    
    public void updateVehicleMileage(int vehicleId, int newMileage) throws SQLException {
        String sql = "UPDATE vehicles SET current_mileage = ? WHERE vehicle_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newMileage);
            stmt.setInt(2, vehicleId);
            stmt.executeUpdate();
            System.out.println("✓ Vehicle mileage updated: " + newMileage + " km");
        }
    }
    
    public void deleteVehicle(int vehicleId) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE vehicle_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            stmt.executeUpdate();
            System.out.println("✓ Vehicle deleted: ID " + vehicleId);
        }
    }
    
    // ==================== DRIVER OPERATIONS ====================
    
    public void addDriver(Driver driver) throws SQLException {
        String sql = "INSERT INTO drivers (first_name, last_name, license_number, " +
                     "license_type, phone_number, email, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, driver.getFirstName());
            stmt.setString(2, driver.getLastName());
            stmt.setString(3, driver.getLicenseNumber());
            stmt.setString(4, driver.getLicenseType());
            stmt.setString(5, driver.getPhoneNumber());
            stmt.setString(6, driver.getEmail());
            stmt.setString(7, driver.getStatus());
            
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                driver.setDriverId(generatedKeys.getInt(1));
            }
            System.out.println("✓ Driver added: " + driver.getFullName());
        }
    }
    
    public List<Driver> getAllDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers ORDER BY driver_id";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                drivers.add(extractDriverFromResultSet(rs));
            }
        }
        return drivers;
    }
    
    // ==================== TRIP OPERATIONS ====================
    
    public void addTrip(Trip trip) throws SQLException {
        String sql = "INSERT INTO trips (vehicle_id, driver_id, start_location, end_location, " +
                     "start_time, distance_km, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, trip.getVehicleId());
            stmt.setInt(2, trip.getDriverId());
            stmt.setString(3, trip.getStartLocation());
            stmt.setString(4, trip.getEndLocation());
            stmt.setString(5, trip.getStartTime());
            stmt.setDouble(6, trip.getDistanceKm());
            stmt.setString(7, trip.getStatus());
            
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                trip.setTripId(generatedKeys.getInt(1));
            }
            System.out.println("✓ Trip added: " + trip.getStartLocation() + " -> " + trip.getEndLocation());
        }
    }
    
    public void completeTrip(int tripId, String endTime, double fuelUsed) throws SQLException {
        String sql = "UPDATE trips SET end_time = ?, fuel_used = ?, status = 'Completed' WHERE trip_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, endTime);
            stmt.setDouble(2, fuelUsed);
            stmt.setInt(3, tripId);
            stmt.executeUpdate();
            System.out.println("✓ Trip completed: ID " + tripId);
        }
    }
    
    public List<Trip> getTripsByVehicle(int vehicleId) throws SQLException {
        List<Trip> trips = new ArrayList<>();
        String sql = "SELECT * FROM trips WHERE vehicle_id = ? ORDER BY start_time DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                trips.add(extractTripFromResultSet(rs));
            }
        }
        return trips;
    }
    
    // ==================== ANALYTICS & REPORTING ====================
    
    public double getTotalDistanceByVehicle(int vehicleId) throws SQLException {
        String sql = "SELECT SUM(distance_km) FROM trips WHERE vehicle_id = ? AND status = 'Completed'";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0.0;
    }
    
    public double getTotalFuelConsumed(int vehicleId) throws SQLException {
        String sql = "SELECT SUM(fuel_used) FROM trips WHERE vehicle_id = ? AND status = 'Completed'";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0.0;
    }
    
    public double getAverageFuelEfficiency(int vehicleId) throws SQLException {
        String sql = "SELECT SUM(distance_km) / SUM(fuel_used) as efficiency " +
                     "FROM trips WHERE vehicle_id = ? AND status = 'Completed' AND fuel_used > 0";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("efficiency");
            }
        }
        return 0.0;
    }
    
    public int getActiveVehicleCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM vehicles WHERE status = 'Active'";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    
    // ==================== HELPER METHODS ====================
    
    private Vehicle extractVehicleFromResultSet(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(rs.getInt("vehicle_id"));
        vehicle.setRegistrationNumber(rs.getString("registration_number"));
        vehicle.setMake(rs.getString("make"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setYear(rs.getInt("year"));
        vehicle.setStatus(rs.getString("status"));
        vehicle.setFuelCapacity(rs.getDouble("fuel_capacity"));
        vehicle.setCurrentMileage(rs.getInt("current_mileage"));
        return vehicle;
    }
    
    private Driver extractDriverFromResultSet(ResultSet rs) throws SQLException {
        Driver driver = new Driver();
        driver.setDriverId(rs.getInt("driver_id"));
        driver.setFirstName(rs.getString("first_name"));
        driver.setLastName(rs.getString("last_name"));
        driver.setLicenseNumber(rs.getString("license_number"));
        driver.setLicenseType(rs.getString("license_type"));
        driver.setPhoneNumber(rs.getString("phone_number"));
        driver.setEmail(rs.getString("email"));
        driver.setStatus(rs.getString("status"));
        return driver;
    }
    
    private Trip extractTripFromResultSet(ResultSet rs) throws SQLException {
        Trip trip = new Trip(0, 0, null, null, null, 0);
        trip.setTripId(rs.getInt("trip_id"));
        trip.setVehicleId(rs.getInt("vehicle_id"));
        trip.setDriverId(rs.getInt("driver_id"));
        trip.setStartLocation(rs.getString("start_location"));
        trip.setEndLocation(rs.getString("end_location"));
        trip.setStartTime(rs.getString("start_time"));
        trip.setEndTime(rs.getString("end_time"));
        trip.setDistanceKm(rs.getDouble("distance_km"));
        trip.setFuelUsed(rs.getDouble("fuel_used"));
        trip.setStatus(rs.getString("status"));
        return trip;
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

}
