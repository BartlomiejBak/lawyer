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

--changeset bbak:9

CREATE TABLE PAYMENT (
                         payment_id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                         payment_value DECIMAL(12,2),
                         payment_date DATE,
                         paid BOOLEAN,
                         paid_date DATE,
                         comment VARCHAR(255),
                         us BOOLEAN,
                         incoming BOOLEAN
);

--changeset bbak:10

CREATE TABLE POA (
                     poa_id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                     type VARCHAR(50),
                     payment VARCHAR(150),
                     kpc BOOLEAN,
                     termination BOOLEAN,
                     start_date DATE,
                     end_date DATE,
                     notification_duty BOOLEAN,
                     duty_completed BOOLEAN
);

--changeset bbak:11

CREATE TABLE CONTACT_ADDRESS (
                                 id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                                 address INT REFERENCES ADDRESS(address_id),
                                 contact INT REFERENCES CONTACT(contact_id)
);

--changeset bbak:12

CREATE TABLE COURT_ADDRESS (
                               id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                               address INT REFERENCES ADDRESS(address_id),
                               contact INT REFERENCES COURT(court_id)
);


--changeset bbak:13

ALTER TABLE IF EXISTS LAWSUIT RENAME case_id TO lawsuit_id;

CREATE TABLE EVENT_LAWSUIT (
                               id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                               event INT REFERENCES EVENT(event_id),
                               lawsuit INT REFERENCES LAWSUIT(lawsuit_id)
);

--changeset bbak:14

CREATE TABLE TASK_CONTACT (
                              id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                              task INT REFERENCES TASK(task_id),
                              contact INT REFERENCES CONTACT(contact_id)
);

--changeset bbak:15

ALTER TABLE COURT ADD COLUMN address INT REFERENCES ADDRESS(address_id);
ALTER TABLE COURT ADD COLUMN phone VARCHAR(16);

--changeset bbak:16

ALTER TABLE CONTACT ADD COLUMN secondary_address INT REFERENCES ADDRESS(address_id);

--changeset bbak:17

CREATE TABLE LAWSUIT_TASK (
                              id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                              task INT REFERENCES TASK(task_id),
                              lawsuit INT REFERENCES LAWSUIT(lawsuit_id)
);

--changeset bbak:18

CREATE TABLE CONTACT_ROLE (
                                 id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                                 contact INT REFERENCES CONTACT(contact_id),
                                 role VARCHAR(25)
);

CREATE TABLE CONTACT_ROLE_LAWSUIT (
                              id INT PRIMARY KEY NOT NULL DEFAULT nextval('table_id_seq'),
                              lawsuit INT REFERENCES LAWSUIT(lawsuit_id),
                              contact_role INT REFERENCES CONTACT_ROLE(id),
                              role VARCHAR(25)
);

--changeset bbak:19

ALTER TABLE IF EXISTS contact ALTER COLUMN date_created TYPE TIMESTAMP;
ALTER TABLE IF EXISTS lawsuit ALTER COLUMN claim_amount TYPE DOUBLE PRECISION;
ALTER TABLE IF EXISTS payment ALTER COLUMN payment_value TYPE DOUBLE PRECISION;

--changeset bbak:20

ALTER TABLE IF EXISTS ADDRESS rename to DB_ADDRESS;
ALTER TABLE IF EXISTS CONTACT rename to DB_CONTACT;
ALTER TABLE IF EXISTS CONTACT_ADDRESS rename to DB_CONTACT_ADDRESS;
ALTER TABLE IF EXISTS CONTACT_ROLE rename to DB_CONTACT_ROLE;
ALTER TABLE IF EXISTS CONTACT_ROLE_LAWSUIT rename to DB_CONTACT_ROLE_LAWSUIT;
ALTER TABLE IF EXISTS COURT rename to DB_COURT;
ALTER TABLE IF EXISTS COURT_ADDRESS rename to DB_COURT_ADDRESS;
ALTER TABLE IF EXISTS EVENT rename to DB_EVENT;
ALTER TABLE IF EXISTS EVENT_LAWSUIT rename to DB_EVENT_LAWSUIT;
ALTER TABLE IF EXISTS LAWSUIT rename to DB_LAWSUIT;
ALTER TABLE IF EXISTS LAWSUIT_CONTACT rename to DB_LAWSUIT_CONTACT;
ALTER TABLE IF EXISTS LAWSUIT_TASK rename to DB_LAWSUIT_TASK;
ALTER TABLE IF EXISTS NOTE rename to DB_NOTE;
ALTER TABLE IF EXISTS PAYMENT rename to DB_PAYMENT;
ALTER TABLE IF EXISTS POA rename to DB_POA;
ALTER TABLE IF EXISTS TAG rename to DB_TAG;
ALTER TABLE IF EXISTS TASK rename to DB_TASK;
ALTER TABLE IF EXISTS TASK_CONTACT rename to DB_TASK_CONTACT;

--changeset bbak:21
ALTER TABLE IF EXISTS DB_CONTACT DROP COLUMN ADDRESS;
ALTER TABLE IF EXISTS DB_CONTACT DROP COLUMN SECONDARY_ADDRESS;

--changeset bbak:22
ALTER TABLE IF EXISTS DB_COURT DROP COLUMN ADDRESS;

--changeset bbak:23
ALTER TABLE IF EXISTS DB_COURT_ADDRESS RENAME COLUMN contact TO court;

--changeset bbak:24
ALTER TABLE IF EXISTS DB_LAWSUIT RENAME COLUMN cas_side TO case_side;
ALTER TABLE IF EXISTS DB_LAWSUIT RENAME COLUMN add_info TO additional_info;

--changeset bbak:25
ALTER TABLE IF EXISTS DB_CONTACT_ADDRESS ADD COLUMN type VARCHAR(32) NOT NULL default 'primary';

--changeset bbak:26
ALTER TABLE IF EXISTS DB_CONTACT_ROLE_LAWSUIT DROP COLUMN role;

--changeset bbak:27
ALTER TABLE IF EXISTS DB_CONTACT_ADDRESS DROP CONSTRAINT IF EXISTS contact_address_address_fkey;
ALTER TABLE IF EXISTS DB_CONTACT_ADDRESS ADD CONSTRAINT contact_address_address_fkey FOREIGN KEY (address) REFERENCES DB_ADDRESS (address_id) ON DELETE CASCADE;
ALTER TABLE IF EXISTS DB_CONTACT_ADDRESS DROP CONSTRAINT IF EXISTS contact_address_contact_fkey;
ALTER TABLE IF EXISTS DB_CONTACT_ADDRESS ADD CONSTRAINT contact_address_contact_fkey FOREIGN KEY (contact) REFERENCES DB_CONTACT (contact_id) ON DELETE CASCADE;

ALTER TABLE IF EXISTS DB_CONTACT_ROLE DROP CONSTRAINT IF EXISTS contact_role_contact_fkey;
ALTER TABLE IF EXISTS DB_CONTACT_ROLE ADD CONSTRAINT contact_role_contact_fkey FOREIGN KEY (contact) REFERENCES DB_CONTACT (contact_id) ON DELETE CASCADE;

ALTER TABLE IF EXISTS DB_CONTACT_ROLE_LAWSUIT DROP CONSTRAINT IF EXISTS contact_role_lawsuit_contact_role_fkey;
ALTER TABLE IF EXISTS DB_CONTACT_ROLE_LAWSUIT ADD CONSTRAINT contact_role_lawsuit_contact_role_fkey FOREIGN KEY (contact_role) REFERENCES DB_CONTACT_ROLE (id) ON DELETE CASCADE;
ALTER TABLE IF EXISTS DB_CONTACT_ROLE_LAWSUIT DROP CONSTRAINT IF EXISTS contact_role_lawsuit_lawsuit_fkey;
ALTER TABLE IF EXISTS DB_CONTACT_ROLE_LAWSUIT ADD CONSTRAINT contact_role_lawsuit_lawsuit_fkey FOREIGN KEY (lawsuit) REFERENCES DB_LAWSUIT (lawsuit_id) ON DELETE CASCADE;

ALTER TABLE IF EXISTS DB_COURT_ADDRESS DROP CONSTRAINT IF EXISTS court_address_address_fkey;
ALTER TABLE IF EXISTS DB_COURT_ADDRESS ADD CONSTRAINT court_address_address_fkey FOREIGN KEY (address) REFERENCES DB_ADDRESS (address_id) ON DELETE CASCADE;
ALTER TABLE IF EXISTS DB_COURT_ADDRESS DROP CONSTRAINT IF EXISTS court_address_contact_fkey;
ALTER TABLE IF EXISTS DB_COURT_ADDRESS ADD CONSTRAINT court_address_court_fkey FOREIGN KEY (court) REFERENCES DB_COURT (court_id) ON DELETE CASCADE;

ALTER TABLE IF EXISTS DB_EVENT_LAWSUIT DROP CONSTRAINT IF EXISTS event_lawsuit_event_fkey;
ALTER TABLE IF EXISTS DB_EVENT_LAWSUIT ADD CONSTRAINT event_lawsuit_event_fkey FOREIGN KEY (event) REFERENCES DB_EVENT (event_id) ON DELETE CASCADE;
ALTER TABLE IF EXISTS DB_EVENT_LAWSUIT DROP CONSTRAINT IF EXISTS event_lawsuit_lawsuit_fkey;
ALTER TABLE IF EXISTS DB_EVENT_LAWSUIT ADD CONSTRAINT event_lawsuit_lawsuit_fkey FOREIGN KEY (lawsuit) REFERENCES DB_LAWSUIT (lawsuit_id) ON DELETE CASCADE;

ALTER TABLE IF EXISTS DB_LAWSUIT_CONTACT DROP CONSTRAINT IF EXISTS lawsuit_contact_contact_fkey;
ALTER TABLE IF EXISTS DB_LAWSUIT_CONTACT ADD CONSTRAINT lawsuit_contact_contact_fkey FOREIGN KEY (contact) REFERENCES DB_CONTACT (contact_id) ON DELETE CASCADE;
ALTER TABLE IF EXISTS DB_LAWSUIT_CONTACT DROP CONSTRAINT IF EXISTS lawsuit_contact_lawsuit_fkey;
ALTER TABLE IF EXISTS DB_LAWSUIT_CONTACT ADD CONSTRAINT lawsuit_contact_lawsuit_fkey FOREIGN KEY (lawsuit) REFERENCES DB_LAWSUIT (lawsuit_id) ON DELETE CASCADE;

ALTER TABLE IF EXISTS DB_LAWSUIT_TASK DROP CONSTRAINT IF EXISTS lawsuit_task_lawsuit_fkey;
ALTER TABLE IF EXISTS DB_LAWSUIT_TASK ADD CONSTRAINT lawsuit_task_lawsuit_fkey FOREIGN KEY (lawsuit) REFERENCES DB_LAWSUIT (lawsuit_id) ON DELETE CASCADE;
ALTER TABLE IF EXISTS DB_LAWSUIT_TASK DROP CONSTRAINT IF EXISTS lawsuit_task_task_fkey;
ALTER TABLE IF EXISTS DB_LAWSUIT_TASK ADD CONSTRAINT lawsuit_task_task_fkey FOREIGN KEY (task) REFERENCES DB_TASK (task_id) ON DELETE CASCADE;

ALTER TABLE IF EXISTS DB_TASK_CONTACT DROP CONSTRAINT IF EXISTS task_contact_contact_fkey;
ALTER TABLE IF EXISTS DB_TASK_CONTACT ADD CONSTRAINT task_contact_contact_fkey FOREIGN KEY (contact) REFERENCES DB_CONTACT (contact_id) ON DELETE CASCADE;
ALTER TABLE IF EXISTS DB_TASK_CONTACT DROP CONSTRAINT IF EXISTS task_contact_task_fkey;
ALTER TABLE IF EXISTS DB_TASK_CONTACT ADD CONSTRAINT task_contact_task_fkey FOREIGN KEY (task) REFERENCES DB_TASK (task_id) ON DELETE CASCADE;