insert into course (id, name, created, updated) values (1, 'Bobbic', current_timestamp(0), current_timestamp(0));
insert into course (id, name, created, updated) values (2, 'Drugok', current_timestamp(0), current_timestamp(0));
insert into course (id, name, created, updated) values (3, 'Murka', current_timestamp(0), current_timestamp(0));

insert into passport (id, number) values (1, '123-456');
insert into passport (id, number) values (2, '9807 123456');
insert into passport (id, number) values (3, 'Q696 987411');

insert into student (id, name, passport_id) values (1, 'Peter', 3);
insert into student (id, name, passport_id) values (2, 'Vasia', 2);
insert into student (id, name, passport_id) values (3, 'Masha', 1);

insert into review (id, description, rating) values (1, 'Good course', '4');
insert into review (id, description, rating) values (2, 'Great course', '4.3');
insert into review (id, description, rating) values (3, 'Best course', '5');