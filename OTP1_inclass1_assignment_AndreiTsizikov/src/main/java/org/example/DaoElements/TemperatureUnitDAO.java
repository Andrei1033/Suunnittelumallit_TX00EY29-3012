package org.example.DaoElements;

import org.example.DBConnectors.DBConnection;
import org.example.DBConnectors.TemperatureUnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TemperatureUnitDAO {

    public List<TemperatureUnit> findAll() throws SQLException {
        String sql = "SELECT id, name, symbol FROM temperature_unit ORDER BY id";
        List<TemperatureUnit> result = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                result.add(mapRow(rs));
            }
        }
        return result;
    }

    public Optional<TemperatureUnit> findByName(String name) throws SQLException {
        String sql = "SELECT id, name, symbol FROM temperature_unit WHERE name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    public TemperatureUnit insert(TemperatureUnit unit) throws SQLException {
        String sql = "INSERT INTO temperature_unit (name, symbol) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, unit.getName());
            ps.setString(2, unit.getSymbol());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    unit.setId(keys.getInt(1));
                }
            }
        }
        return unit;
    }

    private TemperatureUnit mapRow(ResultSet rs) throws SQLException {
        return new TemperatureUnit(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("symbol")
        );
    }
}