create table t_user
(
    id int auto_increment primary key,
    account_id   varchar(100) null,
    name         varchar(50)  null,
    token        varchar(100) null,
    gmt_create   bigint       null,
    gmt_modified bigint       null
);