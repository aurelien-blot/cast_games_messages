CREATE TABLE setting (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         modification_time TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                         short_name VARCHAR(100) NOT NULL,
                         label VARCHAR(255) NOT NULL,
                         value VARCHAR(255) NULL,
                         date_value TIMESTAMP NULL,
                         description TEXT
);