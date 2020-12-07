-- user - role = many-to-one
-- role - rules = many-to-many
-- item - user = many-to-one
-- item - comments = one-to-many
-- item - attachs = one-to-many
-- item - category = many-to-one
-- item - state = many-to-one

create table role
(
    id        serial primary key,
    role_name varchar(1000)
);

create table username
(
    id       serial primary key,
    login    varchar(100),
    password varchar(100),
    role_id  int references role (id)
);

create table rules
(
    id           serial primary key,
    access_level varchar(1000)
);

create table role_rules
(
    id       serial primary key,
    role_id  int references role (id),
    rules_id int references rules (id)

);

create table category
(
    id   serial primary key,
    cat_name varchar(1000)
);

create table state
(
    id   serial primary key,
    stat_name varchar(100)
);

create table item
(
    id          serial primary key,
    item_name   varchar(100),
    username_id integer references username (id),
    category_id integer references category (id),
    state_id    integer references state (id)
);

create table comments
(
    id          serial primary key,
    description text,
    item_id     integer references item (id)
);

create table attachs
(
    id      serial primary key,
    name    varchar(1000),
    file    bytea,
    item_id integer references item (id)
);

