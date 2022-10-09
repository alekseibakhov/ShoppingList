--Заявление
create schema if not exists public;
create table if not exists buyer
(
    id serial PRIMARY KEY NOT NULL,
    name     varchar(50) not null,
    lastname varchar(50) not null,
    age      integer     not null
);

create table if not exists goods
(
    id serial PRIMARY KEY NOT NULL,
    name  varchar(200),
    price numeric not null
);

create table if not exists shopping_list
(
    id serial     primary key,
    buyer_id      bigint not null,
    goods_id      bigint not null,
    registered_at date,
    name          varchar,
    cost          numeric
);

comment on column shopping_list.buyer_id is 'id покупателя';

comment on column shopping_list.goods_id is 'id товара';

comment on column shopping_list.registered_at is 'дата покупки';

comment on column shopping_list.name is 'имя покупки';

comment on column shopping_list.cost is 'общая стоимость покупки';

INSERT INTO public.buyer (id, name, lastname, age) VALUES (1, 'Alexey', 'Bakhov', 18);
INSERT INTO public.buyer (id, name, lastname, age) VALUES (2, 'Sergey', 'Sergun', 18);
INSERT INTO public.buyer (id, name, lastname, age) VALUES (3, 'Vladimir', 'Ryzkov', 24);

INSERT INTO public.goods (id, name, price) VALUES (1, 'Наушники', 24);
INSERT INTO public.goods (id, name, price) VALUES (2, 'Телевизор ', 13);

INSERT INTO public.shopping_list (id, buyer_id, goods_id, registered_at, name, cost) VALUES (1, 1, 1, '2022-10-10', 'Покупка1', 232);
INSERT INTO public.shopping_list (id, buyer_id, goods_id, registered_at, name, cost) VALUES (2, 1, 2, '2022-10-10', 'Покупка1', 1555);
INSERT INTO public.shopping_list (id, buyer_id, goods_id, registered_at, name, cost) VALUES (3, 2, 1, '2022-01-11', 'Покупка2', 23132);
INSERT INTO public.shopping_list (id, buyer_id, goods_id, registered_at, name, cost) VALUES (4, 3, 2, '2022-01-11', 'Покупка3', 12313);
INSERT INTO public.shopping_list (id, buyer_id, goods_id, registered_at, name, cost) VALUES (5, 3, 1, '2022-09-23', 'Покупка4', 313);
INSERT INTO public.shopping_list (id, buyer_id, goods_id, registered_at, name, cost) VALUES (6, 1, 2, '2022-10-10', 'Покупка1', 1555);
INSERT INTO public.shopping_list (id, buyer_id, goods_id, registered_at, name, cost) VALUES (7, 1, 2, '2022-10-10', 'Покупка1', 1555);
