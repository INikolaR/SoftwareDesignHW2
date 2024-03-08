--liquibase formatted sql
--changeset nikola:1
create table dish (
    name varchar(1024) not null constraint pk_dish primary key,
    amount int not null,
    price numeric(102, 2) not null,
    cooking_time int not null
);

--changeset nikola:2
insert into dish (name, amount, price, cooking_time)
values
    ('Fired potatoes', 5, 100.00, 60),
    ('Borsch', 3, 200.00, 30),
    ('Steak', 10, 500.00, 10),
    ('Pizza', 12, 300.00, 60);