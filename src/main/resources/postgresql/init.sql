--Заявление
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
