# ----- Этап 1: сборка -----
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ----- Этап 2: запуск -----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/temperature_converter.jar app.jar
CMD ["java", "-jar", "app.jar"]