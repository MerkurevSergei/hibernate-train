insert into course (id, name, created, updated) values (1, 'Hibernate course', current_timestamp(0), current_timestamp(0));
insert into course (id, name, created, updated) values (2, 'Spring course', current_timestamp(0), current_timestamp(0));
insert into course (id, name, created, updated) values (3, 'Any mega course', current_timestamp(0), current_timestamp(0));

insert into student (id, name) values (1, 'Peter');
insert into student (id, name) values (2, 'Vasia');
insert into student (id, name) values (3, 'Masha');

insert into passport (id, number, student_id) values (1, '123-456', 3);
insert into passport (id, number, student_id) values (2, '9807 123456', 2);
insert into passport (id, number, student_id) values (3, 'Q696 987411', 1);

insert into review (id, description, rating, course_id) values (1, 'Good course', '4', 3);
insert into review (id, description, rating, course_id) values (2, 'Great course', '4.3', 1);
insert into review (id, description, rating, course_id) values (3, 'Best course', '5', 1);
insert into review (id, description, rating, course_id) values (4, 'Best course', null, 1);
insert into review (id, description, rating, course_id) values (5, 'Best course', '', 1);

insert into student_course (student_id, course_id) values (2, 1);
insert into student_course (student_id, course_id) values (1, 3);
insert into student_course (student_id, course_id) values (3, 1);