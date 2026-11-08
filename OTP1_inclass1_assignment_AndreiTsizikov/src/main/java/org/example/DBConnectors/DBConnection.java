package org.example.DBConnectors;

import java.io.*;

import java.sql.*;
import java.util.Properties;

public class DBConnection {

    private static final String PROPERTIES_FILE = "database.properties";

    private static final String DEFAULT_URL = "jdbc:h2:./OTP1_inclass1_assignment_AndreiTsizikov/data/temperature_db;AUTO_SERVER=TRUE";
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
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS temperature_unit (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50) NOT NULL UNIQUE,
                    symbol VARCHAR(10) NOT NULL,
                    category VARCHAR(30) NOT NULL DEFAULT 'temperature'
                )
                """);
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS unit_translation (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    unit_id INT NOT NULL,
                    lang VARCHAR(5) NOT NULL,
                    name VARCHAR(100) NOT NULL,
                    description VARCHAR(255) NOT NULL,
                    CONSTRAINT fk_unit_translation_unit FOREIGN KEY (unit_id) REFERENCES temperature_unit(id),
                    CONSTRAINT uq_unit_translation UNIQUE (unit_id, lang)
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
            stmt.execute("ALTER TABLE temperature_unit ADD COLUMN IF NOT EXISTS category VARCHAR(30) DEFAULT 'temperature' NOT NULL");
            stmt.execute("ALTER TABLE conversion_record ADD COLUMN IF NOT EXISTS language VARCHAR(5) DEFAULT 'en'");
        }
        seedUnitsAndTranslations();
    }

    private static void seedUnitsAndTranslations() throws SQLException {
        String[][] units = {
                {"Celsius", "°C", "temperature"}, {"Fahrenheit", "°F", "temperature"}, {"Kelvin", "K", "temperature"}
        };
        String[][][] translations = {
                {
                        {"en", "Celsius", "Temperature in degrees Celsius"},
                        {"fi", "Celsius", "Lämpötila celsiusasteina"},
                        {"sv", "Celsius", "Temperatur i grader Celsius"},
                        {"ja", "摂氏", "セルシウス度で表した温度"},
                        {"ar", "سلسيوس", "درجة الحرارة بالسلسيوس"}
                },
                {
                        {"en", "Fahrenheit", "Temperature in degrees Fahrenheit"},
                        {"fi", "Fahrenheit", "Lämpötila fahrenheitasteina"},
                        {"sv", "Fahrenheit", "Temperatur i grader Fahrenheit"},
                        {"ja", "華氏", "華氏度で表した温度"},
                        {"ar", "فهرنهايت", "درجة الحرارة بالفهرنهايت"}
                },
                {
                        {"en", "Kelvin", "Temperature in kelvin"}, {"fi", "Kelvin", "Lämpötila kelvineinä"},
                        {"sv", "Kelvin", "Temperatur i kelvin"}, {"ja", "ケルビン", "ケルビンで表した温度"},
                        {"ar", "كلفن", "درجة الحرارة بالكلفن"}
                }
        };
        for (int i = 0; i < units.length; i++) {
            int unitId = ensureUnit(units[i][0], units[i][1], units[i][2]);
            for (String[] tr : translations[i]) ensureTranslation(unitId, tr[0], tr[1], tr[2]);
        }
    }

    private static int ensureUnit(String name, String symbol, String category) throws SQLException {
        try (Connection c = getConnection()) {
            try (PreparedStatement q = c.prepareStatement("SELECT id FROM temperature_unit WHERE name = ?")) {
                q.setString(1, name);
                try (ResultSet rs = q.executeQuery()) { if (rs.next()) return rs.getInt(1); }
            }
            try (PreparedStatement ins = c.prepareStatement("INSERT INTO temperature_unit(name, symbol, category) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                ins.setString(1, name); ins.setString(2, symbol); ins.setString(3, category); ins.executeUpdate();
                try (ResultSet keys = ins.getGeneratedKeys()) { if (keys.next()) return keys.getInt(1); }
            }
        }
        throw new SQLException("Could not create unit " + name);
    }

    private static void ensureTranslation(int unitId, String lang, String name, String description) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement q = c.prepareStatement("SELECT id FROM unit_translation WHERE unit_id = ? AND lang = ?")) {
            q.setInt(1, unitId); q.setString(2, lang);
            try (ResultSet rs = q.executeQuery()) { if (rs.next()) return; }
            try (PreparedStatement ins = c.prepareStatement("INSERT INTO unit_translation(unit_id, lang, name, description) VALUES (?, ?, ?, ?)")) {
                ins.setInt(1, unitId); ins.setString(2, lang); ins.setString(3, name); ins.setString(4, description); ins.executeUpdate();
            }
        }
    }

    public static void clearAll() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM conversion_record");
            stmt.execute("DELETE FROM unit_translation");
            stmt.execute("DELETE FROM temperature_unit");
        }
    }
}