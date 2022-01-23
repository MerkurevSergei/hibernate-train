create table pet
(
    id   integer      not null auto_increment,
    name varchar(200) not null,
    primary key (id)
);

create table owner
(
    id   integer      not null auto_increment,
    name varchar(200) not null,
    created timestamp,
    updated timestamp,
    primary key (id)
);