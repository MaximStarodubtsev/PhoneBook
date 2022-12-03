CREATE DATABASE PB;

CREATE SCHEMA phonebook;

CREATE TABLE departments
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(136)
);

CREATE TABLE PCs
(
    id BIGSERIAL PRIMARY KEY,
    model VARCHAR(136),
    HDD VARCHAR(136),
    RAM VARCHAR(136),
    inventoryNumber VARCHAR(136)
);

CREATE TABLE employees
(
    id BIGSERIAL PRIMARY KEY,
    firstName VARCHAR(136),
    lastName VARCHAR(136),
    patronymicName VARCHAR(136),
    gender VARCHAR(136),
    phoneNumber VARCHAR(136),
    department_id BIGSERIAL REFERENCES departments(id),
    pc_id BIGSERIAL REFERENCES PCs(id) UNIQUE
);

CREATE TABLE roles
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(136)
);

CREATE TABLE employees_roles
(
    employee_id BIGSERIAL REFERENCES employees(id) NOT NULL ,
    role_id BIGSERIAL REFERENCES roles(id) NOT NULL ,
    PRIMARY KEY (employee_id, role_id)
);

