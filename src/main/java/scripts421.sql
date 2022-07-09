create table student
(
    age  Integer check (age > 16),
    age INTEGER default 20,
    name TEXT UNIQUE,
    name TEXT NOT NULL

);

create table faculty
(
    name  TEXT UNIQUE,
    color TEXT UNIQUE
);


