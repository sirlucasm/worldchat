CREATE TABLE room_users (
    id serial primary key,
    room_id integer not null,
    user_id integer not null,

    foreign key(room_id) references rooms(id),
    foreign key(user_id) references users(id)
);