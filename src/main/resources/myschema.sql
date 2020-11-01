
DROP TABLE IF EXISTS CASES;
DROP TABLE IF EXISTS TASK;
DROP TABLE IF EXISTS CONTACT;
DROP TABLE IF EXISTS ADDRESS;
DROP TABLE IF EXISTS TAG;
DROP TABLE IF EXISTS COURT;

CREATE TABLE ADDRESS (
    address_id INT AUTO_INCREMENT PRIMARY KEY ,
    street VARCHAR(50),
    zip_code VARCHAR(10),
    city VARCHAR(50),
    country VARCHAR(20)
);

CREATE TABLE CASES (
    case_id INT AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(50),
    cas_side VARCHAR(50),
    input_date DATE,
    deadline DATE,
    signature VARCHAR(20),
    claim_amount DECIMAL(12,2),
    add_info VARCHAR(500)
);

CREATE TABLE CONTACT (
    contact_id INT AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(45),
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    email VARCHAR(45),
    alt_email VARCHAR(45),
    phone VARCHAR(20),
    alt_phone VARCHAR(20),
    pesel VARCHAR(15),
    address INT REFERENCES ADDRESS(address_id),
    company_name VARCHAR(45),
    nip VARCHAR(20),
    regon VARCHAR(20),
    krs VARCHAR(20),
    date_created DATE,
    modified DATETIME
);

CREATE TABLE TASK (
    task_id INT AUTO_INCREMENT PRIMARY KEY ,
    priority BOOLEAN,
    deadline DATETIME,
    description VARCHAR(500)
);

CREATE TABLE TAG (
    tag_id INT AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(40)
);

CREATE TABLE COURT (
    court_id INT AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(55),
    description VARCHAR(500)
);



