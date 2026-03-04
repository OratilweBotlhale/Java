# Java

# Vehicle Fleet Management System


## 🎯 Project Overview

This system demonstrates enterprise-level Java development skills including:
- Object-Oriented Programming (OOP)
- JDBC Database Connectivity
- SQL Database Design & Normalization
- CRUD Operations
- Data Analytics & Reporting
- Input Validation & Error Handling


## 🚀 Features

### Vehicle Management
- Add, view, update, and delete vehicles
- Track registration, make, model, year, mileage
- Monitor fuel capacity and vehicle status
- Vehicle performance analytics

### Driver Management
- Driver registration and license tracking
- Contact information management
- Driver status monitoring

### Trip Management
- Create and manage trips
- Track start/end locations and times
- Calculate distance and fuel consumption
- Trip completion workflow

### Analytics & Reporting
- Total distance traveled by vehicle
- Fuel consumption tracking
- Fuel efficiency calculations (km/l)
- Fleet statistics dashboard

## 🛠️ Technologies Used

- **Java 8+** - Core programming language
- **JDBC** - Database connectivity
- **MySQL** - Relational database
- **Object-Oriented Design** - Model classes (Vehicle, Driver, Trip)
- **SQL** - Database queries and schema design

## 📋 Prerequisites

- Java JDK 8 or higher
- MySQL Server 5.7 or higher
- MySQL Connector/J (JDBC driver)


## 📊 Database Schema

### Vehicles Table
- `vehicle_id` (PK, Auto Increment)
- `registration_number` (Unique)
- `make`, `model`, `year`
- `status` (Active/Maintenance/Retired)
- `fuel_capacity`, `current_mileage`
- Timestamps

### Drivers Table
- `driver_id` (PK, Auto Increment)
- `first_name`, `last_name`
- `license_number` (Unique), `license_type`
- `phone_number`, `email`
- `status` (Active/Suspended/Inactive)

### Trips Table
- `trip_id` (PK, Auto Increment)
- `vehicle_id` (FK), `driver_id` (FK)
- `start_location`, `end_location`
- `start_time`, `end_time`
- `distance_km`, `fuel_used`
- `status` (Planned/In Progress/Completed/Cancelled)

## 💡 Key Skills Demonstrated

1. **Java Fundamentals**
   - Classes and Objects
   - Encapsulation & Data Hiding
   - Collections (ArrayList)
   - Exception Handling

2. **Database Skills**
   - JDBC Connection Management
   - Prepared Statements (SQL Injection Prevention)
   - CRUD Operations
   - Foreign Key Relationships
   - Aggregation Queries (SUM, AVG)

3. **Software Engineering**
   - Separation of Concerns (Model, Database, UI)
   - Resource Management (try-with-resources)
   - Input Validation
   - Clean Code Practices

4. **Domain Knowledge**
   - Fleet Management Concepts
   - Vehicle Tracking
   - Fuel Efficiency Calculations
   - Logistics Operations

## 📝 Sample Usage

```
========================================
  FLEET MANAGEMENT SYSTEM v1.0
  TSS Technology Solutions Demo
========================================

--- MAIN MENU ---
1. Add New Vehicle
2. View All Vehicles
3. Add New Driver
4. View All Drivers
5. Create New Trip
6. Complete Trip
7. View Vehicle Trip History
8. Generate Vehicle Report
9. Fleet Statistics
0. Exit
-----------------
Enter choice: 1

--- ADD NEW VEHICLE ---
Registration Number: XYZ123GP
Make (e.g., Toyota): Toyota
Model (e.g., Hilux): Hilux
Year: 2023
Fuel Capacity (liters): 80
Current Mileage (km): 0

✓ Vehicle added successfully!
  Vehicle[id=4, reg=XYZ123GP, Toyota Hilux (2023), status=Active, mileage=0]
```

## 🔧 Future Enhancements

- [ ] GUI interface using JavaFX or Swing
- [ ] REST API with Spring Boot
- [ ] Android mobile app for drivers
- [ ] Real-time GPS tracking integration
- [ ] Fuel price monitoring
- [ ] Maintenance scheduling
- [ ] Driver performance analytics
- [ ] Cloud deployment (AWS/Azure)

## 👨‍💻 Author

**Oratilwe Botlhale Radikeledi**
- Junior Java Developer
- Diploma in IT: Software Development (76% average, 16 distinctions)
- Contact: botlhaleradikeledi@gmail.com

