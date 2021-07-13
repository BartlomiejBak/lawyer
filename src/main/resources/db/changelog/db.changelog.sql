--liquibase formatted sql

--changeset bbak:1

CREATE SEQUENCE table_id_seq;

CREATE TABLE IF NOT EXISTS ADDRESS (
                         address_id int PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                         street VARCHAR(50),
                         zip_code VARCHAR(10),
                         city VARCHAR(50),
                         country VARCHAR(20)
);

--changeset bbak:2

CREATE TABLE LAWSUIT (
                         case_id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                         name VARCHAR(50),
                         cas_side VARCHAR(50),
                         input_date DATE,
                         deadline DATE,
                         signature VARCHAR(20),
                         claim_amount DECIMAL(12,2),
                         add_info VARCHAR(500)
);

--changeset bbak:3

CREATE TABLE CONTACT (
                         contact_id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
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
                         modified TIMESTAMP
);

--changeset bbak:4

CREATE TABLE TASK (
                      task_id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                      priority BOOLEAN,
                      deadline TIMESTAMP,
                      description VARCHAR(500)
);

--changeset bbak:5

CREATE TABLE TAG (
                     tag_id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                     name VARCHAR(40)
);

--changeset bbak:6

CREATE TABLE COURT (
                       court_id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                       name VARCHAR(55),
                       description VARCHAR(500)
);

--changeset bbak:7

CREATE TABLE NOTE (
                      note_id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                      title VARCHAR(55),
                      text VARCHAR(5000)
);

--changeset bbak:8

CREATE TABLE EVENT (
                       event_id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                       title VARCHAR(55),
                       date_time TIMESTAMP,
                       description VARCHAR(1500)
);