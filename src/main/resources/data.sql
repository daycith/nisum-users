drop table if exists phones cascade;
drop table if exists users cascade;
create table phones (user_id varchar(255) not null, city_code varchar(255), country_code varchar(255), number varchar(255));
create table users (id varchar(255) not null, created timestamp(6), email varchar(255), isactive boolean, last_login timestamp(6), modified timestamp(6), name varchar(255), password varchar(255), token varchar(255), primary key (id));