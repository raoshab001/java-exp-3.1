CREATE DATABASE webappdb;
USE webappdb;

CREATE TABLE Users (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(50)
);
INSERT INTO Users VALUES ('admin', 'admin123');

CREATE TABLE Employee (
  EmpID INT PRIMARY KEY,
  Name VARCHAR(100),
  Salary DOUBLE
);
INSERT INTO Employee VALUES
(1, 'Ayush', 45000),
(2, 'Riya', 55000),
(3, 'Ravi', 40000);

CREATE TABLE Attendance (
  StudentID INT,
  Date DATE,
  Status VARCHAR(10)
);
