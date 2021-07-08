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

