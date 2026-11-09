## OTP1_inclass1_assignment_AndreiTsizikov - README

1. Assignment Description
This repository contains a Temperature Converter assignment implemented in Java with a JavaFX GUI, H2 persistence, and unit tests. Deliverables in this folder: source (src/), build (target/), pom.xml, Dockerfile, Jenkinsfile, and test reports. You can copy the OTP1_inclass1_assignment_AndreiTsizikov folder and run the project as instructed below.

2. Technologies & Tools Used
- Java 21 (maven.compiler.source/target = 21)
- JavaFX 21.0.5 (org.openjfx)
- Maven (build system)
- JUnit 5.11.4 (unit tests)
- H2 Database (embedded)
- JaCoCo (coverage)
- Maven Shade, Surefire plugins

3. Design Approach & Implementation Method
- Architecture: simple MVC-like split.
  - org.example.Main: application entrypoint and launcher.
  - view.TemperatureApp: JavaFX UI (input, unit selection, convert/save buttons).
  - controller.TemperatureController: handles UI events and coordinates model/DAO.
  - TemperatureConverter: core conversion logic (Celsius/Fahrenheit/Kelvin conversions).
  - DBConnectors and DaoElements: H2 connection, TempRecord and TemperatureUnit DAOs for persistence.
- Decisions: used JavaFX for a lightweight desktop GUI and H2 for zero-configuration persistence so you can run without external DB setup.

4. Testing & Quality Assurance Steps
- Automated: mvn test runs JUnit tests (tests located in src/test/java). The included run shows: "Tests run: 27, Failures: 0, Errors: 0, Skipped: 0" (see target/surefire-reports).
- Coverage: JaCoCo reports generated under target/site/jacoco/.
- Manual: launched the app (via mvn javafx:run or the packaged JAR) and exercised conversion flows and saving records.

5. How to Run (prerequisites & commands)
Prerequisites:
- JDK 21 installed and JAVA_HOME set
- Maven installed
- (Optional, for running GUI from Docker on Windows) an X server such as Xming or VcXsrv installed and running, and display forwarding enabled

Commands (run inside the OTP1_inclass1_assignment_AndreiTsizikov folder):
- Build & tests: mvn clean package
- Run tests only: mvn test
- Run with JavaFX plugin: mvn javafx:run
- Run packaged jar: java -jar target\temperature_converter.jar

Docker (alternative run method):
- Pull image from Docker Hub: docker pull andrei1033/temperature_converter:latest
- Run GUI container on Windows with X server available:
  docker run --rm -e DISPLAY=host.docker.internal:0.0 andrei1033/temperature_converter:latest

Notes for Docker+GUI on Windows:
- Ensure Xming or VcXsrv is running and configured to accept connections. Start Xming/VcXsrv before running the container.
- The DISPLAY environment above uses host.docker.internal:0.0 to forward the display from the container to the host X server on Windows.
- If firewall or display access issues occur, allow connections from the local network or configure X server security settings accordingly.

Notes
- All required sources, build files and test reports are inside this assignment folder. No .gitignore used here. You just copy repository that contains the full contents under OTP1_inclass1_assignment_AndreiTsizikov.
- For coverage HTML open target\site\jacoco\index.html in a browser.

Contact
- Author: Andrei Tsizikov

## Database localization (Assignment 6)

This project uses the **translation table** approach. `temperature_unit` stores the language-neutral unit key, symbol and category once. `unit_translation` stores one row per unit and language (`unit_id`, `lang`, `name`, `description`) and references `temperature_unit(id)` with a foreign key. The unique constraint on `(unit_id, lang)` prevents duplicate translations.

`DBConnection.initialize()` creates the tables and idempotently seeds unit labels and descriptions in English (`en`), Finnish (`fi`), Swedish (`sv`), Japanese (`ja`) and Arabic (`ar`). The catalogue includes temperature, speed, distance and time units. `TemperatureUnitDAO.findAll(language)` obtains the selected translation with an English fallback using `COALESCE`; the temperature GUI filters the catalogue to temperature units. The language selector changes the ResourceBundle for static interface labels and the language passed to the DAO for database labels. Unit names used by conversion calculations remain language-independent.

**Why this approach:** translations are separated from measurement data, non-text attributes are not duplicated for every language, and adding a language requires inserting translation rows rather than altering the schema. The trade-off is the additional JOIN in localized queries.

`init.sql` contains the database schema. The application also creates/migrates the tables and inserts the starter data automatically when it starts.

(End of README)