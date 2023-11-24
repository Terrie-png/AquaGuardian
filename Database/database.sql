create database Aquaguardian;

CREATE TABLE Batch(
    batch_id INT AUTO_INCREMENT PRIMARY KEY,
    timestamp DATETIME NOT NULL
);

CREATE TABLE SensorData (
    data_id INT AUTO_INCREMENT PRIMARY KEY,
    batch_id INT NOT NULL,
    analog_in FLOAT NOT NULL,
    voltage_in FLOAT NOT NULL,
    data_type VARCHAR(255) NOT NULL,
    FOREIGN KEY (batch_id) REFERENCES Batch(batch_id)
);


CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    remember_token VARCHAR(255),
    role ENUM('admin', 'user') NOT NULL
);






-- Sample data for the "Sensors" table
INSERT INTO Sensors (sensor_id, sensor_name)
VALUES
    (1,'Temperature Sensor'),
    (2,'TDS Sensor'),
    (3,'Turbidity Sensor'),
    (4,'GPS Module');


-- Sample data for the "SensorData" table with data_id
INSERT INTO SensorData (data_id, sensor_id, timestamp, analog_in,  voltage_in)
VALUES
    (1, 1, '2023-10-26 08:00:00', 25.5),
    (2, 2, '2023-10-26 09:30:00', 60.2),
    (3, 3, '2023-10-26 11:15:00', 1005.3),
    (4, 4, '2023-10-26 12:45:00', 800),
    (5, 1, '2023-10-26 14:00:00', 26.0),
    (6, 2, '2023-10-26 15:30:00', 59.8),
    (7, 3, '2023-10-26 17:15:00', 1004.9),
    (8, 4, '2023-10-26 18:45:00', 810);

INSERT INTO Users (user_id, name, username, email, password, role) VALUES
    (1, 'John Murphy', 'john_m', 'john01@gmail.com', 'password123', 'admin'),
    (2, 'Jane Burns', 'jane_b', 'jane02@yahoo.com', 'userpass456', 'user'),
    (3, 'Alice Johnson', 'alice_j', 'alice03@yahoo.com', 'securePwd789', 'user'),
    (4, 'Matthew Brady', 'matthew_b', 'matthew04@gmail.com', 'topSecret101', 'admin');

