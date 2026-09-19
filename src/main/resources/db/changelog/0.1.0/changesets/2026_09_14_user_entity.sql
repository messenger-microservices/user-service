--liquibase formatted sql

--changeset pulsarmn:create_users_table
CREATE TABLE users
(
    id            UUID PRIMARY KEY,
    username      VARCHAR(32)  NOT NULL UNIQUE,
    phone_number  VARCHAR(50)  NULL,
    display_name  VARCHAR(64)  NOT NULL,
    birthdate     DATE         NULL,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);

--rollback DROP TABLE users;
