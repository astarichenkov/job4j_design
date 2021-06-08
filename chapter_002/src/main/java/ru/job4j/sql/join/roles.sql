-- drop table roles cascade ;
-- drop table users cascade ;

create table roles
(
    id   serial primary key,
    name varchar(255)
);

create table users
(
    id          serial primary key,
    name        varchar(255),
    roles_id int references roles (id)
);

insert into roles(name) values ('creator');
insert into roles(name) values ('moderator');
insert into roles(name) values ('administrator');

insert into users(name, roles_id) values('admin', 3);
insert into users(name, roles_id) values('Ivan', 1);
insert into users(name, roles_id) values('zhopakita', 2);
insert into users(name, roles_id) values('Anton', 3);
insert into users(name, roles_id) values('nikolya', 1);
insert into users(name, roles_id) values('Irina', 2);

insert into users(name) values('incognito');

select u.name, r.id from users as u join roles as r on u.roles_id = r.id

select u.name as Имя, r.id as Роль from users as u join roles as r on u.roles_id = r.id

select u.name as Имя, r.name as Роль, r.id as role_id from users as u join roles as r on u.roles_id = r.id

