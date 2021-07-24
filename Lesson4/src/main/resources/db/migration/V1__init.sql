drop table session if exists cascade;
create table session
(
    id     bigint primary key,
    length int
);

drop table movie if exists cascade;
create table movie
(
    id         bigint primary key,
    name       varchar(1256),
    session_id bigint,
    foreign key (session_id) references session (id)
);

drop table schedule if exists cascade;
create table schedule
(
    id       bigint primary key,
    since    timestamp,
    price    decimal(5, 2),
    movie_id bigint,
    foreign key (movie_id) references movie (id)
);

drop table ticket if exists cascade;
create table ticket
(
    id          bigint primary key,
    schedule_id bigint,
    foreign key (schedule_id) references schedule (id)
);

insert into session (id, length)
values (1, 60),
       (2, 90),
       (3, 120);

insert into movie (id, name, session_id)
values (1, 'Snatch', 3),
       (2, 'Lock, Stock, Two smocking barrels', 3),
       (3, 'Pulp fiction', 3),
       (4, 'Kill Bill', 2),
       (5, 'GentleMen', 2),
       (6, 'Once Upon a Time... in Hollywood', 1);

insert into schedule (id, since, price, movie_id)
values (1, '2021-07-23 10:00:00.00', 200.00, 1),
       (2, '2021-07-23 13:00:00.00', 250.00, 2),
       (3, '2021-07-23 16:00:00.00', 300.00, 3),
       (4, '2021-07-23 16:30:00.00', 300.00, 4),
       (5, '2021-07-23 18:00:00.00', 350.00, 5),
       (6, '2021-07-23 18:50:00.00', 400.00, 6),
       (7, '2021-07-23 21:00:00.00', 400.00, 6),
       (8, '2021-07-23 22:00:00.00', 250.00, 1);

insert into ticket (id, schedule_id)
values (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 3),
       (7, 4),
       (8, 5),
       (9, 5),
       (10, 6),
       (11, 6),
       (12, 7),
       (13, 7),
       (14, 8),
       (15, 8);
