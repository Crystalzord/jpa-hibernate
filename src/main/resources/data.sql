insert into course (id, name, created_date, last_updated_date)
values (10001, 'JPA in 50 steps', sysdate(), sysdate());
insert into course (id, name, created_date, last_updated_date)
values (10002, 'Spring in 50 steps', sysdate(), sysdate());
insert into course (id, name, created_date, last_updated_date)
values (10003, 'Spring Boot in 100 steps', sysdate(), sysdate());

insert into passport (id, number)
values (40001, 'E123456');
insert into passport (id, number)
values (40002, 'E234825');
insert into passport (id, number)
values (40003, 'L938425');

insert into student (id, name, passport_id)
values (20001, 'Lukasz', 40001);
insert into student (id, name, passport_id)
values (20002, 'Adam', 40002);
insert into student (id, name, passport_id)
values (20003, 'Marta', 40003);

insert into review (id, rating, description)
values (50001, '5', 'Great course!');
insert into review (id, rating, description)
values (50002, '4', 'Wonderful course but...');
insert into review (id, rating, description)
values (50003, '5', 'Awesome course!');