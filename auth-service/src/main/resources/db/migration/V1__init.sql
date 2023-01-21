create table users
(
id bigserial primary key,
username        varchar(36) not null,
password        varchar(80) not null,
address         varchar(255),
phone           varchar(255),
created_at      timestamp default current_timestamp,
updated_at      timestamp default current_timestamp
);

create table roles
(
id              bigserial primary key,
name            varchar(50) not null,
created_at      timestamp default current_timestamp,
updated_at      timestamp default current_timestamp
);

create table users_roles
(
user_id         bigint not null references users (id),
role_id         bigint not null references roles (id),
primary key (user_id, role_id),
created_at      timestamp default current_timestamp,
updated_at      timestamp default current_timestamp
);

insert into roles (name) values
('ROLE_USER'),
('ROLE_ADMIN'),
('ROLE_SUPERADMIN');

insert into users
(username, password) values
('User','$2a$12$WZ/iYUZjSs.gfhJK0U7jEe1aKVjQUYVmRg.Jxy9ciKB6xoxatLxc6'),
('Admin','$2a$12$WZ/iYUZjSs.gfhJK0U7jEe1aKVjQUYVmRg.Jxy9ciKB6xoxatLxc6'),
('SA','$2a$12$WZ/iYUZjSs.gfhJK0U7jEe1aKVjQUYVmRg.Jxy9ciKB6xoxatLxc6')
       ;

insert into users_roles
(user_id, role_id) values
(1,1),
(2,2),
(3,3);

