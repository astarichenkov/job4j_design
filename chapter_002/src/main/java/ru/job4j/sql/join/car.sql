drop table body cascade;
drop table engine cascade;
drop table transmission cascade;
drop table car cascade;

create table body
(
    id   serial primary key,
    name varchar(255)
);

create table engine
(
    id   serial primary key,
    name varchar(255)
);

create table transmission
(
    id   serial primary key,
    name varchar(255)
);

create table car
(
    id              serial primary key,
    name            varchar(255),
    body_id         int,
    engine_id       int,
    transmission_id int
);

insert into body(name) values ('седан');
insert into body(name) values ('хетчбек');
insert into body(name) values ('универсал');
insert into body(name) values ('купе');
insert into body(name) values ('кроссовер');
insert into engine(name) values ('1.6');
insert into engine(name) values ('2.0');
insert into engine(name) values ('2.4');
insert into engine(name) values ('3.5');
insert into transmission(name) values ('механика');
insert into transmission(name) values ('автомат');
insert into transmission(name) values ('робот');
insert into transmission(name) values ('вариатор');
insert into car(name, body_id, engine_id, transmission_id) values ('Веста', 3, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('Камри', 1, 4, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('Фокус', 2, 2, 3);

--1) Вывести список всех машин и все привязанные к ним детали.
select c.name, b.name, e.name, t.name
from car c
         join body b on c.body_id = b.id
         join engine e on c.engine_id = e.id
         join transmission t on c.transmission_id = t.id;

--2) Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, кузова, двигатели, коробки передач.
select b.id, b.name
from body b
         left join car c on b.id = c.body_id
where c.body_id is null;

select e.id, e.name
from engine e
         left join car c on e.id = c.engine_id
where c.body_id is null;

select t.id, t.name
from transmission t
         left join car c on t.id = c.transmission_id
where c.body_id is null;



