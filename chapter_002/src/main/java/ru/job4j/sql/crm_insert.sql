-- RULES
insert into rules(access_level) values('full');
insert into rules(access_level) values('read');
insert into rules(access_level) values('write');

-- ROLE
insert into role(role_name) values('admin');
insert into role(role_name) values('user');
insert into role(role_name) values('guest');

-- ROLE_RULES
insert into role_rules (role_id, rules_id) VALUES (1, 1);
insert into role_rules (role_id, rules_id) VALUES (2, 1);
insert into role_rules (role_id, rules_id) VALUES (2, 2);
insert into role_rules (role_id, rules_id) VALUES (3, 2);

-- USERNAME
insert into username(login, password, role_id) values('admin', 'passw001', 1);
insert into username(login, password, role_id) values('user', 'passwd002', 2);
insert into username(login, password, role_id) values('guest', '', 3);

-- COMMENTS
insert into comments(description, item_id) values('это', 1);
insert into comments(description, item_id) values('не работает', 1);
insert into comments(description, item_id) values('нормально', 2);

-- ATTACHS
insert into attachs(name, file, item_id) values('one.jpg', '010101010', 1);
insert into attachs(name, file, item_id) values('two.jpg', '000000000', 2);

-- STATE
insert into state(stat_name) values('to do');
insert into state(stat_name) values('in progress');
insert into state(stat_name) values('done');
insert into state(stat_name) values('closed');

-- CATEGORY
insert into category(cat_name) values('bug');
insert into category(cat_name) values('update');
insert into category(cat_name) values('task');

-- ITEMS
insert into item(username_id, item_name, category_id, state_id) values(1, 'item1', 1, 1);
insert into item(username_id, item_name, category_id, state_id) values(2,'item2', 2, 2);
insert into item(username_id, item_name, category_id, state_id) values(3,'item3', 3, 3);


