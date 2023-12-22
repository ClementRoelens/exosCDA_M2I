DROP DATABASE exo_billetterie;

CREATE DATABASE IF NOT EXISTS exo_billetterie;

USE exo_billetterie;

CREATE TABLE IF NOT EXISTS event_location(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    address VARCHAR(250) NOT NULL,
    capacity INT NOT NULL
);


CREATE TABLE IF NOT EXISTS event(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    date DATE NOT NULL,
    event_location_id INT NOT NULL,
    price DOUBLE NOT NULL,
    tickets_sold_number INT NOT NULL,
    customer_event_id INT NOT NULL,
    FOREIGN KEY (event_location_id) REFERENCES event_location (id)
);

CREATE TABLE IF NOT EXISTS customer(
	id INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    customer_event_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS customer_event(
	customer_id INT NOT NULL,
    event_id INT NOT NULL,
    PRIMARY KEY (customer_id, event_id),
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (event_id) REFERENCES event (id)
);