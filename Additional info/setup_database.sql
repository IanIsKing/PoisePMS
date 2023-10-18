
-- Create the database
CREATE DATABASE poised_db;
USE poised_db;

-- Create Customer table
CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255),
    customer_phone VARCHAR(15),
    customer_address VARCHAR(255)
);

-- Create Architect table
CREATE TABLE Architect (
    architect_id INT AUTO_INCREMENT PRIMARY KEY,
    architect_name VARCHAR(255) NOT NULL,
    architect_email VARCHAR(255),
    architect_phone VARCHAR(15),
    architect_address VARCHAR(255)
);

-- Create Contractor table
CREATE TABLE Contractor (
    contractor_id INT AUTO_INCREMENT PRIMARY KEY,
    contractor_name VARCHAR(255) NOT NULL,
    contractor_email VARCHAR(255),
    contractor_phone VARCHAR(15),
    contractor_address VARCHAR(255)
);

-- Create ProjectManager table
CREATE TABLE ProjectManager (
    pm_id INT AUTO_INCREMENT PRIMARY KEY,
    pm_name VARCHAR(255) NOT NULL,
    pm_email VARCHAR(255),
    pm_phone VARCHAR(15),
    pm_address VARCHAR(255)
);

-- Create Project table
CREATE TABLE Project (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    building_type VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    ERF_number VARCHAR(50) NOT NULL,
    total_fee DECIMAL(15, 2) NOT NULL,
    amount_paid DECIMAL(15, 2) NOT NULL,
    deadline_date DATE NOT NULL,
    project_status ENUM('In Progress', 'Completed', 'On Hold') NOT NULL,
    completion_date DATE,
    customer_id INT,
    architect_id INT,
    contractor_id INT,
    pm_id INT,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (architect_id) REFERENCES Architect(architect_id),
    FOREIGN KEY (contractor_id) REFERENCES Contractor(contractor_id),
    FOREIGN KEY (pm_id) REFERENCES ProjectManager(pm_id)
);
