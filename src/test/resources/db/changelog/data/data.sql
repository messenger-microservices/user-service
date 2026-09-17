--liquibase formatted sql

--changeset pulsarmn:add-test-users
INSERT INTO users (id, username, password_hash, phone_number, display_name, birthdate)
VALUES ('01a0a95c-65d7-7612-b453-accb0bebba22', 'pulsarmn', 'password-hash', NULL, 'Alex', NULL),
       ('01a0a95e-3705-7441-976e-f49a8a2e8284', 'pulsar', 'password-hash', NULL, 'Alex2', NULL),
       ('01a0a95e-3705-79d0-b0fa-794f7cd8a6ef', 'faunly', 'password-hash', '', 'Kirill', NULL),
       ('01a0a95e-3705-7017-987e-b99c21e8b2ad', 'someuser', 'password-hash', NULL, 'SomeUser', NULL),
       ('01a0a95f-acd6-70ed-bb0d-dfa93b2e11e6', 'customuser', 'password-hash', NULL, 'CustomUser', NULL),
       ('01a0a95f-acd6-7ebd-8fa6-04cefc1eea38', 'anotheruser', 'password-hash', NULL, 'AnotherUser', NULL),
       ('01a0a95f-acd6-7ae2-ac56-2aa4f1221a0c', 'someone', 'password-hash', NULL, 'Someone', NULL),
       ('01a0a95f-acd6-7e33-ba33-4dafcd6363d4', 'baduser', 'password-hash', NULL, 'BadUser', NULL),
       ('01a0a95f-acd6-77c6-9181-4b693a78f755', 'funnyuser', 'password-hash', NULL, 'FunnyUser', NULL),
       ('01a0a95f-acd6-7054-9caa-342318683cb9', 'famoususer', 'password-hash', NULL, 'FamousUser', NULL);
