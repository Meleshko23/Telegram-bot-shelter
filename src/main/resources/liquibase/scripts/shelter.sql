-- liquebase formatted sql
-- changeset abdullinru:1
create table Volunteer(
        id      integer primary key,
        chartId integer not null,
        name    varchar not null
);
-- changeset abdullinru:2
create table Pet(
        id      integer primary key,
        name    varchar not null,
        age     integer check (age > 0)
);
-- changeset abdullinru:3
create table PetOwner(
        id      integer primary key,
        chartId integer not null,
        name    varchar not null,
        mail    varchar not null,
        phone   varchar not null,
        pet_id  integer REFERENCES Pet (id)
);
-- changeset abdullinru:4
create table PhotoPet(
        id          integer primary key,
        filePath    varchar not null,
        fileSize    integer not null,
        mediaType   varchar not null,
        data        oid     not null,
        Pet_id      integer REFERENCES Pet (id)
);
-- changeset abdullinru:5
create table KeepingPet(
        id              integer primary key,
        chatId          integer     not null,
        infoPet         text        not null,
        date_time       TIMESTAMP   not null,
        photoPet_id     integer REFERENCES PhotoPet (id),
        PetOwner_id     integer REFERENCES PetOwner (id)
);


