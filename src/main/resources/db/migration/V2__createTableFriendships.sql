CREATE TABLE friendships (
    id serial primary key,
    sended_by integer not null,
    to_user_id integer not null,
    accepted boolean default(false),

    foreign key(sended_by) references users(id),
    foreign key(to_user_id) references users(id)
);