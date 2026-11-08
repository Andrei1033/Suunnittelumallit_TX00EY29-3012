package org.example.DaoElements;

import org.example.DBConnectors.DBConnection;
import org.example.DBConnectors.TemperatureUnit;
import java.sql.*;
import java.util.*;

/** Reads unit labels from unit_translation, falling back to English when needed. */
public class TemperatureUnitDAO {
    public List<TemperatureUnit> findAll() throws SQLException { return findAll("en"); }

    public List<TemperatureUnit> findAll(String language) throws SQLException {
        String sql = """
            SELECT u.id, u.name, u.symbol,
                   COALESCE(t.name, en.name, u.name) AS localized_name,
                   COALESCE(t.description, en.description, '') AS description
            FROM temperature_unit u
            LEFT JOIN unit_translation t ON t.unit_id = u.id AND t.lang = ?
            LEFT JOIN unit_translation en ON en.unit_id = u.id AND en.lang = 'en'
            WHERE u.category = 'temperature'
            ORDER BY u.id
            """;
        List<TemperatureUnit> result = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, normalizeLanguage(language));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) result.add(mapRow(rs));
            }
        }
        return result;
    }

    public Optional<TemperatureUnit> findByName(String name) throws SQLException {
        return findAll("en").stream().filter(u -> u.getName().equals(name)).findFirst();
    }

    public TemperatureUnit insert(TemperatureUnit unit) throws SQLException {
        String sql = "INSERT INTO temperature_unit (name, symbol) VALUES (?, ?)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, unit.getName()); ps.setString(2, unit.getSymbol()); ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) { if (keys.next()) unit.setId(keys.getInt(1)); }
        }
        return unit;
    }

    private String normalizeLanguage(String language) {
        if (language == null || language.isBlank()) return "en";
        String code = language.toLowerCase(Locale.ROOT);
        return Set.of("en", "fi", "sv", "ja", "ar").contains(code) ? code : "en";
    }
    private TemperatureUnit mapRow(ResultSet rs) throws SQLException {
        return new TemperatureUnit(rs.getInt("id"), rs.getString("name"), rs.getString("symbol"),
                rs.getString("localized_name"), rs.getString("description"));
    }
}
