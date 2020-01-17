create table whitebird_auth_service.client
(
    id bigint auto_increment
        primary key,
    created_date datetime(6) null,
    last_modified_date datetime(6) null,
    access_token_validity_seconds int null,
    authorities varchar(255) null,
    authorized_grant_types varchar(255) null,
    auto_approve bit not null,
    client_id varchar(255) null,
    client_name varchar(255) null,
    client_secret varchar(255) null,
    refresh_token_validity_seconds int null,
    scope varchar(255) null,
    web_server_redirect_uri varchar(255) null
);
