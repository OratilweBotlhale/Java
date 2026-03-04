
    public class Driver {
        private int driverId;
        private String firstName;
        private String lastName;
        private String licenseNumber;
        private String licenseType;
        private String phoneNumber;
        private String email;
        private String status; // Active, Suspended, Inactive
        
        public Driver() {}
        
        public Driver(String firstName, String lastName, String licenseNumber,
                      String licenseType, String phoneNumber, String email, String status) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.licenseNumber = licenseNumber;
            this.licenseType = licenseType;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.status = status;
        }
        
        // Getters and Setters
        public int getDriverId() { return driverId; }
        public void setDriverId(int driverId) { this.driverId = driverId; }
        
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        
        public String getLicenseNumber() { return licenseNumber; }
        public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
        
        public String getLicenseType() { return licenseType; }
        public void setLicenseType(String licenseType) { this.licenseType = licenseType; }
        
        public String getPhoneNumber() { return phoneNumber; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public String getFullName() { return firstName + " " + lastName; }
        
        @Override
        public String toString() {
            return String.format("Driver[id=%d, name=%s, license=%s, status=%s]",
                    driverId, getFullName(), licenseNumber, status);
        }
    }

