create databse Aquaguardian

CREATE TABLE Sensors (
    sensor_id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    UNIQUE KEY unique_sensor (sensor_name)
);

CREATE TABLE SensorData (
    data_id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_id INT,
    timestamp DATETIME NOT NULL,
    value FLOAT NOT NULL,
    FOREIGN KEY (sensor_id) REFERENCES Sensors(sensor_id)
);


CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'user') NOT NULL
);


INSERT INTO users (user_id, name, password, role) VALUES
    (1, 'John Murphy', 'password123', 'admin'),
    (2, 'Jane Burns', 'userpass456', 'user'),
    (3, 'Alice Johnson', 'securePwd789', 'user'),
    (4, 'Matthew Brady', 'topSecret101', 'admin');
