CREATE table car(
                       id integer unique primary key,
                       brand char(80) unique not null,
                       model char(80) unique not null,
                       price integer constraint not null

);
CREATE table person(
    id integer unique primary key ,
    name char(80) unique not null ,
    age integer constraint not null check ( age > 16 ),
    driveLicense boolean,
    carOwner_id integer references car (id)
)