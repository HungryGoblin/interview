drop table student if exists cascade;
create table student
(
    id     bigint primary key,
    name   varchar(256),
    age    int
);

insert into student (id, name, age)
values (1, 'Вася', 21),
       (2, 'Петя', 18);
