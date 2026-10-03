package org.example.DBConnectors;

import java.io.*;

import java.sql.*;
import java.util.Properties;

public class DBConnection {

    private static final String PROPERTIES_FILE = "database.properties";

    private static final String DEFAULT_URL = "jdbc:h2:./data/temperature_db;AUTO_SERVER=TRUE";
    private static final String DEFAULT_USER = "sa";
    private static final String DEFAULT_PASSWORD = "";

    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("H2 driver not found. Check pom.xml.", e);
        }
        loadConfig();
    }

    private DBConnection() {
    }

    private static void loadConfig() {
        Properties props = new Properties();

        try (InputStream in = DBConnection.class
                .getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE)) {

            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            System.err.println("Could not load " + PROPERTIES_FILE + ": " + e.getMessage());
        }

        // Prioriteetti: ympäristömuuttuja > properties-tiedosto > oletus
        url = getEnvOrProp("DB_URL", props, "db.url", DEFAULT_URL);
        user = getEnvOrProp("DB_USER", props, "db.user", DEFAULT_USER);
        password = getEnvOrProp("DB_PASSWORD", props, "db.password", DEFAULT_PASSWORD);
    }

    private static String getEnvOrProp(String envKey, Properties props, String propKey, String fallback) {
        String env = System.getenv(envKey);
        if (env != null && !env.isBlank()) {
            return env;
        }
        String prop = props.getProperty(propKey);
        if (prop != null && !prop.isBlank()) {
            return prop;
        }
        return fallback;
    }

    public static void setUrl(String newUrl) {
        url = newUrl;
    }

    public static void setCredentials(String newUser, String newPassword) {
        user = newUser;
        password = newPassword;
    }

    public static void resetToDefault() {
        loadConfig();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void initialize() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS temperature_unit (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50) NOT NULL UNIQUE,
                    symbol VARCHAR(10) NOT NULL
                )
                """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS conversion_record (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    source_unit_id INT NOT NULL,
                    target_unit_id INT NOT NULL,
                    input_value DOUBLE NOT NULL,
                    output_value DOUBLE NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (source_unit_id) REFERENCES temperature_unit(id),
                    FOREIGN KEY (target_unit_id) REFERENCES temperature_unit(id)
                )
                """);
        }

        seedUnits();
    }

    private static void seedUnits() throws SQLException {
        insertUnitIfMissing("Celsius", "°C");
        insertUnitIfMissing("Fahrenheit", "°F");
        insertUnitIfMissing("Kelvin", "K");
    }

    private static void insertUnitIfMissing(String name, String symbol) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM temperature_unit WHERE name = ?";
        String insertSql = "INSERT INTO temperature_unit (name, symbol) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement check = conn.prepareStatement(checkSql);
             PreparedStatement insert = conn.prepareStatement(insertSql)) {

            check.setString(1, name);

            try (ResultSet rs = check.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    insert.setString(1, name);
                    insert.setString(2, symbol);
                    insert.executeUpdate();
                }
            }
        }
    }

    public static void clearAll() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM conversion_record");
            stmt.execute("DELETE FROM temperature_unit");
        }
    }
}