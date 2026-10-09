OTP1_inclass1_assignment_AndreiTsizikov - README

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
- All required sources, build files and test reports are inside this assignment folder. No .gitignore required for you copying — repository contains the full contents under OTP1_inclass1_assignment_AndreiTsizikov.
- For coverage HTML open target\site\jacoco\index.html in a browser.

Contact
- Author: Andrei Tsizikov

(End of README)