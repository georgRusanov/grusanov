create table transmission (
  id serial primary key,
  name varchar(100)
);
create table gearbox (
  id serial primary key,
  name varchar(100)
);
create table engine (
  id serial primary key,
  name varchar(100)
);
create table car (
  id serial primary key,
  name varchar(100),
  id_engine int references engine(id),
  id_gearbox int references gearbox(id),
  id_transmission int references transmission(id)
);

select name from car;

select e.name
from engine as e left outer join car
    on car.id_engine = e.id
where car.name is null
union
select g.name
from gearbox as g left outer join car
    on car.id_gearbox = g.id
where car.name is null
union
select t.name
from transmission as t left outer join car
    on car.id_transmission = t.id
where car.name is null;





