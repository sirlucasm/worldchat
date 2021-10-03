CREATE TABLE rooms (
    id serial primary key,
    created_by integer not null,
    name varchar(80) not null,
    room_pic varchar(999),
    max_users integer not null,

    foreign key(created_by) references users(id)
);