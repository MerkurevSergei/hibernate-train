create table pet
(
    id   integer      not null auto_increment,
    name varchar(200) not null,
    primary key(id)
);

create table pet_owner
(
    id   integer      not null,
    name varchar(200) not null,
    primary key(id)
);