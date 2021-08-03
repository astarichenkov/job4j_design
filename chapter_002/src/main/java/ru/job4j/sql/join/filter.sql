drop table product cascade;
drop table type cascade;

create table product
(
    id           serial primary key,
    name         varchar(255),
    type_id      int,
    expired_date date,
    price        float
);

create table type
(
    id   serial primary key,
    name varchar(255)
);

insert into product(name, type_id, expired_date, price)
values ('сыр Гауда', '1', '2021-08-15', '300');

insert into product(name, type_id, expired_date, price)
values ('сыр Плавленный', '1', '2021-09-20', '30');

insert into product(name, type_id, expired_date, price)
values ('сыр Ламбер', '1', '2021-08-16', '450');

insert into product(name, type_id, expired_date, price)
values ('мороженое Пломбир', '2', '2021-10-01', '30');

insert into product(name, type_id, expired_date, price)
values ('мороженое Эскимо', '2', '2021-09-25', '50');

insert into product(name, type_id, expired_date, price)
values ('молоко Княгининское 3.2%', '3', '2021-07-30', '45');


insert into type(name)
values ('Сыр');
insert into type(name)
values ('Мороженое');
insert into type(name)
values ('Молоко');

--1. Написать запрос получение всех продуктов с типом "СЫР"
select *
from product as p
         join type t on t.id = p.type_id
where t.name = 'Сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select *
from product as p
         join type t on t.id = p.type_id
where p.name like '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select *
from product as p
where p.expired_date < current_date;

--4. Написать запрос, который выводит самый дорогой продукт.
select *
from product
where price = (select max(p.price) from product as p);

--5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих.
--В виде имя_типа, количество
select t.name, count(*)
from type t
         join product p on t.id = p.type_id
group by t.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select *
from product as p
         join type t on t.id = p.type_id
where t.name = 'Сыр'
   or t.name = 'Молоко';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
--Под количеством подразумевается количество продуктов определенного типа.
--Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла",
--которые ему принадлежат, то количество продуктов типа "СЫР" будет 2.
select t.name, count(*)
from type t
         join product p on t.id = p.type_id
group by t.name
having count(*) < 10;

--8. Вывести все продукты и их тип.
select p.name, t.name
from product as p
         join type t on t.id = p.type_id;





