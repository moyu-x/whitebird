create table if not exists role
(
    id bigint auto_increment
        primary key,
    name varchar(255) null
);

create table if not exists user
(
    id bigint auto_increment
        primary key,
    password varchar(255) null,
    username varchar(255) null
);

create table if not exists user_authorities
(
    user_id bigint not null,
    authorities_id bigint not null,
    constraint FK1nqvgotmn9fcfarl4rnp6iu7k
        foreign key (authorities_id) references role (id),
    constraint FKmj13d0mnuj4cd8b6htotbf9mm
        foreign key (user_id) references user (id)
);

