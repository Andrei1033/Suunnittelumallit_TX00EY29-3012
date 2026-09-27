FROM maven:3.9.6-eclipse-temurin-21
WORKDIR /app
COPY pom.xml .
RUN mvn package
CMD ["java", "-jar", "target/main.jar"]