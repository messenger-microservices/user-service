--liquibase formatted sql

--changeset pulsarmn:add-test-users
INSERT INTO users (id, username, phone_number, display_name, birthdate)
VALUES ('01a0a95c-65d7-7612-b453-accb0bebba22', 'pulsarmn', NULL, 'Alex', NULL),
       ('01a0a95e-3705-7441-976e-f49a8a2e8284', 'pulsar', NULL, 'Alex2', NULL),
       ('01a0a95e-3705-79d0-b0fa-794f7cd8a6ef', 'faunly', NULL, 'Kirill', NULL),
       ('01a0a95e-3705-7017-987e-b99c21e8b2ad', 'someuser', NULL, 'SomeUser', NULL),
       ('01a0a95f-acd6-70ed-bb0d-dfa93b2e11e6', 'customuser',NULL, 'CustomUser', NULL),
       ('01a0a95f-acd6-7ebd-8fa6-04cefc1eea38', 'anotheruser', NULL, 'AnotherUser', NULL),
       ('01a0a95f-acd6-7ae2-ac56-2aa4f1221a0c', 'someone', NULL, 'Someone', NULL),
       ('01a0a95f-acd6-7e33-ba33-4dafcd6363d4', 'baduser', NULL, 'BadUser', NULL),
       ('01a0a95f-acd6-77c6-9181-4b693a78f755', 'funnyuser', NULL, 'FunnyUser', NULL),
       ('01a0a95f-acd6-7054-9caa-342318683cb9', 'famoususer', NULL, 'FamousUser', NULL);
