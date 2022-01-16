create table person (
    id integer not null,
    firstName varchar(200) not null,
    lastName varchar(200) not null,
    birthDate timestamp,
    primary key(id)
);

insert into person (id, firstName, lastName, birthDate)values (1, 'Petrio', 'Nikolo', sysdate());