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