create table whitebird_account_service.role
(
    id bigint auto_increment
        primary key,
    created_date datetime(6) null,
    last_modified_date datetime(6) null,
    code varchar(255) null,
    name varchar(255) null
);

create table whitebird_account_service.user
(
    id bigint auto_increment
        primary key,
    created_date datetime(6) null,
    last_modified_date datetime(6) null,
    account_non_expired bit not null,
    account_non_locked bit not null,
    credentials_non_expired bit not null,
    email varchar(255) null,
    enabled bit not null,
    head_img_url varchar(255) null,
    nickname varchar(255) null,
    password varchar(255) null,
    username varchar(255) null
);

create table whitebird_account_service.user_roles
(
    user_id bigint not null,
    roles_id bigint not null,
    primary key (user_id, roles_id),
    constraint FK55itppkw3i07do3h7qoclqd4k
        foreign key (user_id) references whitebird_account_service.user (id),
    constraint FKj9553ass9uctjrmh0gkqsmv0d
        foreign key (roles_id) references whitebird_account_service.role (id)
);

