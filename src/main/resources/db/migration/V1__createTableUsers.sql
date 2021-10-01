CREATE TABLE users (
    id serial primary key,
    username varchar(50) not null unique,
    email varchar(100) not null,
    password varchar(50) not null,
    profile_pic varchar(999)
);

insert into users (username, email, password) values ('sirlucasm', 'lucasmatheus@teste.com', '12345678');