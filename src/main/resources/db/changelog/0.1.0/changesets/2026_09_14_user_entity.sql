--liquibase formatted sql

--changeset pulsarmn:create_users_table
CREATE TABLE users
(
    id            UUID PRIMARY KEY,
    username      VARCHAR(32)  NOT NULL UNIQUE,
    password_hash VARCHAR(256) NOT NULL,
    phone_number  VARCHAR(50)  NULL,
    display_name  VARCHAR(64)  NOT NULL,
    birthdate     DATE         NULL,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);
