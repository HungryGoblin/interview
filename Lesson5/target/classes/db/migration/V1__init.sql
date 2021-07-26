drop table Student if exists cascade;
create table Student
(
    id   bigserial primary key,
    name varchar(128),
    mark int
);

