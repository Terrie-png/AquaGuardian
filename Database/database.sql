create database Aquaguardian;

-- CREATE TABLE Batch(
--     batch_id INT AUTO_INCREMENT PRIMARY KEY,
--     timestamp DATETIME NOT NULL
-- );

CREATE TABLE SensorData (
    data_id INT AUTO_INCREMENT PRIMARY KEY,
    ph_analog_in FLOAT NOT NULL,
    ph_voltage_in FLOAT NOT NULL,
    tds_analog_in FLOAT NOT NULL,
    tds_voltage_in FLOAT NOT NULL,
    turbidity_analog_in FLOAT NOT NULL,
    turbidity_voltage_in FLOAT NOT NULL,
    timestamp DATETIME NOT NULL,

    );

CREATE TABLE Users (
    u_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
    );




-- -- Sample data for the "Sensors" table
-- INSERT INTO Sensors (sensor_id, sensor_name)
-- VALUES
--     (1,'Temperature Sensor'),
--     (2,'TDS Sensor'),
--     (3,'Turbidity Sensor'),
--     (4,'GPS Module');


-- Sample data for the "SensorData" table with data_id
INSERT INTO SensorData (ph_analog_in, ph_voltage_in, tds_analog_in, tds_voltage_in, turbidity_analog_in,
 turbidity_voltage_in, timestamp)
VALUES
(7.2, 2.1, 300.5, 2.8, 150.3, 1.9, '2023-11-24 08:15:00'),
(6.8, 2.0, 310.2, 3.2, 160.8, 2.2, '2023-11-24 08:30:00'),
(7.0, 2.2, 305.7, 3.0, 155.6, 2.0, '2023-11-24 08:45:00'),
(7.5, 2.5, 295.8, 2.5, 145.2, 1.8, '2023-11-24 09:00:00'),
(6.5, 1.8, 320.1, 3.5, 170.4, 2.5, '2023-11-24 09:15:00');


-- Sample data for the "User" table with user_id
INSERT INTO Users (u_id,  username, email, phoneNumber,address) VALUES
    (1,'John Murphy', 'john01@gmail.com', '0894567890', '123 Main St, City'),
    (2,'Jane Burns', 'jane02@yahoo.com', '0856543210', '456 Oak St, Town'),
    (3,'Alice Johnson', 'alice03@yahoo.com', '0872334455', '789 Pine St, Village'),
    (4,'Matthew Brady', 'matthew04@gmail.com', '0838776655', '101 Cedar St, Countryside');
