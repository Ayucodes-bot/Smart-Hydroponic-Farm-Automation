# Smart Hydroponic Farm Automation System

Java-based smart hydroponic simulation demonstrating OOP, JDBC connectivity, DAO pattern, and multithreaded sensor simulation. 

## Features
- Models: Plant, SensorData
- JDBC-based persistence (MySQL)
- DAO classes for DB operations
- Sensor simulator thread that writes readings periodically
- Basic automation rules (demo)

## Setup (local)
1. Install Java 17+, Maven, MySQL.
2. Create DB:
   - `mysql -u root -p < database/schema.sql`
3. Update DB credentials in `src/main/java/com/hydroponic/dao/DBConnection.java`
4. Build:
   - `mvn clean package`
5. Run:
   - `mvn exec:java -Dexec.mainClass="com.hydroponic.App"`

## Files
- `database/schema.sql` — DB schema
- `src/main/java/com/hydroponic/...` — Java source
- `pom.xml` — Maven config

- Add transaction management and logging

## Architecture (MVC)
This project follows the Model–View–Controller (MVC) architecture.
- Controller: Servlets handle HTTP requests and responses
- Service: Business logic, validation, and transaction management
- DAO: Database operations using JDBC
- Model: Plain Java objects representing entities

## Servlets
- PlantServlet: Handles plant-related operations
- ScheduleServlet: Applies watering/nutrient schedules
- SensorServlet: Processes sensor data and analysis

## Validation & Error Handling
- Server-side validation is implemented in servlets
- Invalid inputs are handled gracefully without crashing the system

## Transaction Management
JDBC transaction management is implemented in the service layer using
commit and rollback to ensure data consistency when multiple database
operations are executed.

## Innovation
A basic automation rule is implemented to analyze pH values and suggest
corrective actions for optimal plant growth.
