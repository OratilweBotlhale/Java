
public class Trip {
 
    public int vehicleId;
    public int driverId;
    public String endLocation;
    public String startLocation;
    public String startTime;
    public double distanceKm;
    public String status;
    private int tripId;
    
    
    public Trip(int vehicleId, int driverId, String startLocation, String endLocation,
        String startTime, double distanceKm) {
this.vehicleId = vehicleId;
this.driverId = driverId;
this.startLocation = startLocation;
this.endLocation = endLocation;
this.startTime = startTime;
this.distanceKm = distanceKm;
this.status = "Planned";
}

// Getters and Setters
public int getTripId() { return getTripId(); }
public void setTripId(int tripId) { this.tripId = tripId; }

public int getVehicleId() { return vehicleId; }
public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

public int getDriverId() { return driverId; }
public void setDriverId(int driverId) { this.driverId = driverId; }

public String getStartLocation() { return startLocation; }
public void setStartLocation(String startLocation) { this.startLocation = startLocation; }

public String getEndLocation() { return endLocation; }
public void setEndLocation(String endLocation) { this.endLocation = endLocation; }

public String getStartTime() { return startTime; }
public void setStartTime(String startTime) { this.startTime = startTime; }

public String getEndTime() { return getEndTime(); }
public void setEndTime(String endTime) { }

public double getDistanceKm() { return distanceKm; }
public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

public double getFuelUsed() { return getFuelUsed(); }
public void setFuelUsed(double fuelUsed) { }

public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }

@Override
public String toString() {
return String.format("Trip[id=%d, vehicle=%d, driver=%d, %s -> %s, %.1fkm, status=%s]",
        tripId, vehicleId, driverId, startLocation, endLocation, distanceKm, status);
}
}
