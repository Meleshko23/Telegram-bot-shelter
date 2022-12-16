-- liquebase formatted sql
-- changeset abdullinru:16
DROP table users;
-- changeset abdullinru:17
Create TABLE users (
        chat_id     bigint,
        shelter     varchar,
        name        varchar,
        phone       varchar,
        mail        varchar
);