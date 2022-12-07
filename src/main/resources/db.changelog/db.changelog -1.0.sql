--liquibase formatted sql
-- changeSet Maksim Starodubtsev:1
SET search_path = "phonebook";

CREATE TABLE IF NOT EXISTS departments
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(136) UNIQUE
);

CREATE TABLE IF NOT EXISTS PCs
(
    id BIGSERIAL PRIMARY KEY,
    model VARCHAR(136),
    HDD VARCHAR(136),
    RAM VARCHAR(136),
    inventoryNumber VARCHAR(136) UNIQUE
);

CREATE TABLE IF NOT EXISTS employees
(
    id BIGSERIAL PRIMARY KEY,
    firstName VARCHAR(136),
    lastName VARCHAR(136),
    patronymicName VARCHAR(136),
    gender VARCHAR(136),
    phoneNumber VARCHAR(136) UNIQUE,
    department_id INT REFERENCES departments(id),
    pc_id INT REFERENCES PCs(id) UNIQUE
);

CREATE TABLE IF NOT EXISTS roles
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(136) UNIQUE
);

CREATE TABLE IF NOT EXISTS employees_roles
(
    employee_id BIGSERIAL REFERENCES employees(id) NOT NULL ,
    role_id BIGSERIAL REFERENCES roles(id) NOT NULL ,
    PRIMARY KEY (employee_id, role_id)
);

-- changeSet Maksim Starodubtsev:2
INSERT INTO PCs(model, HDD, RAM, inventoryNumber) VALUES
    ('Acer', null, null, 1),
    ('Lenovo', null, null, 2);

INSERT INTO roles (name) VALUES
                             ('Admin'),
                             ('User');

INSERT INTO departments(name) VALUES
                                  ('HRDepartment'),
                                  ('ProgrammerDepartment');

INSERT INTO employees(firstName, phoneNumber, department_id, pc_id)
SELECT 'Ivan', '+375297777777', a.id, b.id
FROM departments a, PCs b
WHERE a.name = 'HRDepartment' AND b.inventoryNumber = '1';

INSERT INTO employees(firstName, phoneNumber, department_id, pc_id)
SELECT 'Alex', '+375296666666', a.id, b.id
FROM departments a, PCs b
WHERE a.name = 'ProgrammerDepartment' AND b.inventoryNumber = '2';

INSERT INTO employees_roles(employee_id,role_id)
SElECT employee.id, role.id
    FROM employees employee, roles role
        WHERE employee.phoneNumber = '+375297777777' AND role.name = 'User';

INSERT INTO employees_roles(employee_id,role_id)
SElECT employee.id, role.id
FROM employees employee, roles role
WHERE employee.phoneNumber = '+375296666666' AND role.name = 'Admin';