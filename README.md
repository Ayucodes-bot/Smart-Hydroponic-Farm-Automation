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

