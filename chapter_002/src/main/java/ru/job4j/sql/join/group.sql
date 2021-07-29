drop table devices cascade;
drop table people cascade;
drop table devices_people cascade;

create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price)
values ('laptop', '7000.00');
insert into devices(name, price)
values ('desktop', '5000.00');
insert into devices(name, price)
values ('iphone', '800.00');
insert into devices(name, price)
values ('keyboard', '60.00');
insert into devices(name, price)
values ('mouse', '20.00');
insert into devices(name, price)
values ('monitor', '200.00');

insert into people(name)
values ('Ivan');
insert into people(name)
values ('Anton');
insert into people(name)
values ('Oleg');

insert into devices_people(device_id, people_id)
values (1, 1);
insert into devices_people(device_id, people_id)
values (2, 1);

insert into devices_people(device_id, people_id)
values (2, 2);
insert into devices_people(device_id, people_id)
values (6, 2);
insert into devices_people(device_id, people_id)
values (3, 2);
insert into devices_people(device_id, people_id)
values (5, 2);
insert into devices_people(device_id, people_id)
values (1, 2);

insert into devices_people(device_id, people_id)
values (1, 3);
insert into devices_people(device_id, people_id)
values (3, 3);


select avg(price) from devices;


select p.name, avg(d.price)
from devices_people as dp
         join people p on p.id = dp.people_id
         join devices d on d.id = dp.device_id
group by p.name
having avg(d.price) > 5000;

