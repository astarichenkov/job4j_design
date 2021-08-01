drop table departments cascade;
drop table employees cascade;

create table departments
(
    id   serial primary key,
    name varchar(255)
);

create table employees
(
    id             serial primary key,
    name           varchar(255),
    departments_id int references departments (id)
);

insert into departments(name)
values ('Приемка');
insert into departments(name)
values ('Выдача');
insert into departments(name)
values ('Администрация');

insert into employees(name, departments_id)
values ('Вася', 1);
insert into employees(name, departments_id)
values ('Вован', 1);
insert into employees(name, departments_id)
values ('Лера', 2);
insert into employees(name, departments_id)
values ('Денис', 2);
insert into employees(name, departments_id)
values ('Миша', null);
insert into employees(name, departments_id)
values ('Саня', null);
insert into employees(name, departments_id)
values ('Антон', 3);


--2. Выполнить запросы с left, rigth, full, cross соединениями
select *
from employees
         left join departments d on d.id = employees.departments_id;

select *
from employees
         right join departments d on d.id = employees.departments_id;

select *
from employees
         full join departments d on d.id = employees.departments_id;

select *
from employees
         cross join departments d;

--3. Используя left join найти департаменты, у которых нет работников
select *
from employees e
         left join departments d on d.id = e.departments_id
where d.id is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select *
from employees
         left join departments d on d.id = employees.departments_id;

select *
from departments
         right join employees e on departments.id = e.departments_id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары

drop table teens cascade;

create table teens
(
    name varchar(255),
    gender varchar(255)
);

insert into teens values ('Irina', 'female');
insert into teens values ('Anton', 'male');
insert into teens values ('Ivan', 'male');
insert into teens values ('Anna', 'female');
insert into teens values ('Olga', 'female');

select t1.name n, t2.name from teens t1 cross join teens t2
where t1.gender != t2.gender;
