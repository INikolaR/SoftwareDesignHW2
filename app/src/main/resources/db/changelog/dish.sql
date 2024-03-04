--liquibase formatted sql
--changeset nikola:1
create table dish (
    id int not null constraint pk_dish primary key,
    name varchar(1024) not null,
    amount int not null,
    price numeric(102, 2) not null,
    cooking_time int not null
);

--changeset nikola:2
insert into dish (id, name, amount, price, cooking_time)
values
    (1, 'Fired potatoes', 5, 100.00, 60),
    (2, 'Borsch', 3, 200.00, 30),
    (3, 'Steak', 10, 500.00, 10),
    (4, 'Pizza', 12, 300.00, 60);