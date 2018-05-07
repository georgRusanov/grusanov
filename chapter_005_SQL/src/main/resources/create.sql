CREATE DATABASE "Items";

create table State (
  name varchar(100) primary key
);

create table Category (
  name varchar(100) primary key
);

create table Attaches (
  id serial primary key
);

create table Role (
  name varchar(100) primary key
);

create table Comments (
  id serial primary key,
  description text
);

create table Rules (
  id serial,
  role varchar(100) references Role(name),
  primary key (id, role),
  description text
);

create table Users (
  id serial primary key,
  name varchar(100),
  role varchar(100) references Role(name)
);

create table Item (
  id serial primary key,
  category varchar(100) references Category(name),
  state varchar(100) references State(name),
  user_id int references Users(id),
  comment_id int references Comments(id),
  ataches_id int references Attaches(id)
);

insert into Category values ('first');

insert into Role values ('new');

insert into Rules values (1, 'new', 'asdfg');


