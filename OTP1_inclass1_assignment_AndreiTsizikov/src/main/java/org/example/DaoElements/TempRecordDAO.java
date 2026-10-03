package org.example.DaoElements;

import org.example.DBConnectors.DBConnection;
import org.example.DBConnectors.TempRecord;
import org.example.DBConnectors.TemperatureUnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TempRecordDAO {

    private final TemperatureUnitDAO unitDAO = new TemperatureUnitDAO();

    public TempRecord save(TempRecord record) throws SQLException {
        String sql = """
                INSERT INTO conversion_record
                    (source_unit_id, target_unit_id, input_value, output_value)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, record.getSourceUnit().getId());
            ps.setInt(2, record.getTargetUnit().getId());
            ps.setDouble(3, record.getInputValue());
            ps.setDouble(4, record.getOutputValue());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    record.setId(keys.getInt(1));
                }
            }
        }
        return record;
    }

    public List<TempRecord> findAll() throws SQLException {
        String sql = """
                SELECT r.id,
                       r.input_value,
                       r.output_value,
                       r.created_at,
                       su.id   AS src_id,
                       su.name AS src_name,
                       su.symbol AS src_symbol,
                       tu.id   AS tgt_id,
                       tu.name AS tgt_name,
                       tu.symbol AS tgt_symbol
                FROM conversion_record r
                JOIN temperature_unit su ON r.source_unit_id = su.id
                JOIN temperature_unit tu ON r.target_unit_id = tu.id
                ORDER BY r.created_at DESC, r.id DESC
                """;

        List<TempRecord> result = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TemperatureUnit source = new TemperatureUnit(
                        rs.getInt("src_id"),
                        rs.getString("src_name"),
                        rs.getString("src_symbol")
                );
                TemperatureUnit target = new TemperatureUnit(
                        rs.getInt("tgt_id"),
                        rs.getString("tgt_name"),
                        rs.getString("tgt_symbol")
                );

                Timestamp ts = rs.getTimestamp("created_at");

                TempRecord record = new TempRecord(
                        rs.getInt("id"),
                        source,
                        target,
                        rs.getDouble("input_value"),
                        rs.getDouble("output_value"),
                        ts != null ? ts.toLocalDateTime() : null
                );
                result.add(record);
            }
        }
        return result;
    }
}