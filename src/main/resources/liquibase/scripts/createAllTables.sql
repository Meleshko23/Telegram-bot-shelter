-- liquebase formatted sql
-- changeset abdullinru:1

create table pets(
        id      BIGSERIAL primary key,
        name    varchar not null,
        age     integer check (age > 0),
        type    integer,
        breed   varchar,
        health_restrictions BOOLEAN
);
create table photo_pet(
        id           BIGSERIAL primary key,
        file_path    varchar not null,
        file_size    BIGINT not null,
        media_type   varchar not null,
        pet_id       bigint REFERENCES pets (id)
);
create table cat_owners(
        id                  BIGSERIAL primary key,
        chat_id             bigint not null,
        name                varchar not null,
        mail                varchar not null,
        phone               varchar not null,
        start_trial_period  date,
        end_trial_period    date,
        status_trial        integer,
        pet_id              bigint REFERENCES pets (id)
);
create table dog_owners(
        id                  BIGSERIAL primary key,
        chat_id             bigint not null,
        name                varchar not null,
        mail                varchar not null,
        phone               varchar not null,
        start_trial_period  date,
        end_trial_period    date,
        status_trial        integer,
        pet_id              bigint REFERENCES pets (id)
);
create table keeping_pet(
        id              BIGSERIAL primary key,
        chat_id         bigint    not null,
        info_pet        text      not null,
        date_time       Timestamp not null,
        quality         Boolean,
        photo_pet_id    bigint REFERENCES photo_pet (id),
        dog_owner_id    bigint REFERENCES dog_owners (id),
        cat_owner_id    bigint REFERENCES cat_owners (id)
);
Create TABLE users (
        chat_id     BIGSERIAL primary key,
        shelter     varchar,
        name        varchar,
        phone       varchar,
        mail        varchar
);
create table volunteers(
        id      BIGSERIAL primary key,
        chat_id bigint    not null,
        name    varchar   not null
);