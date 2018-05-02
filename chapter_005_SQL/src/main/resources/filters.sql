--1. Написать запрос получение всех продуктов с типом "СЫР"
select *
from product as p join type as t on p.type_id = t.id
where t.name = 'СЫР';
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select *
from product
where name like '%мороженное%';
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select *
from product
where date_trunc('month', expired_date) = date_trunc('month', now()) + ('1 month')::interval;
--4. Написать запрос, который вывод самый дорогой продукт.
select *
from product
where price = (select max(price) from product);
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select count(p.id)
from product as p join type as t on p.type_id = t.id
where t.name = 'СЫР';
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select *
from product as p join type as t on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select name
from type
where id in (select type_id from product
              group by type_id
              having count(name) < 10);
--Вывести все продукты и их тип
select p.name, t.name as type
from product as p join type as t on p.type_id = t.id;