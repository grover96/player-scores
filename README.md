# Spring Boot Example

A simple Spring Boot application with a TDD approach, consisting of several endpoints, fetching data from a MySQL database.

### **To build the application:**

    ./gradlew clean build

### **To run the application:**

    ./gradlew bootRun

### **To run unit tests:**

    ./gradlew test --tests <package-name>
  
    ./gradlew test --tests “com.example.demo.unit.*“
  
### **To run integration tests:**

    ./gradlew test --tests <package-name>
  
    ./gradlew test --tests "com.example.demo.integration.*"


