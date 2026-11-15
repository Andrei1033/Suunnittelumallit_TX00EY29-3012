-- H2 / MariaDB-compatible core schema for database localization.
CREATE TABLE IF NOT EXISTS temperature_unit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    symbol VARCHAR(10) NOT NULL,
    category VARCHAR(30) NOT NULL DEFAULT 'temperature'
    );

CREATE TABLE IF NOT EXISTS unit_translation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    unit_id INT NOT NULL,
    lang VARCHAR(5) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT fk_unit_translation_unit FOREIGN KEY (unit_id) REFERENCES temperature_unit(id),
    CONSTRAINT uq_unit_translation UNIQUE (unit_id, lang)
    );

CREATE TABLE IF NOT EXISTS conversion_record (
    id INT AUTO_INCREMENT PRIMARY KEY,
    source_unit_id INT NOT NULL,
    target_unit_id INT NOT NULL,
    input_value DOUBLE NOT NULL,
    output_value DOUBLE NOT NULL,
    language VARCHAR(5) DEFAULT 'en',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (source_unit_id) REFERENCES temperature_unit(id),
    FOREIGN KEY (target_unit_id) REFERENCES temperature_unit(id)
    );

-- Starter rows and translations are inserted idempotently by DBConnection.initialize().
