CREATE DATABASE  IF NOT EXISTS `logistics` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE logistics;

create table locations (
       id bigint not null auto_increment,
        latitude double precision,
        longitude double precision,
        name varchar(255),
        create_date datetime not null default current_timestamp,
        last_modified_date datetime not null default current_timestamp on update current_timestamp,
        state varchar(255),
        primary key (id)
    );


create table users (
       id bigint not null auto_increment,
        email varchar(255),
        name varchar(255),
        password varchar(255),
        create_date datetime not null default current_timestamp,
        last_modified_date datetime not null default current_timestamp on update current_timestamp,
        state varchar(255),
        type varchar(255),
        primary key (id)
    );
