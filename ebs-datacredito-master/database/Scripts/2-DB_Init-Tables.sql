SET search_path TO ebs;

create table ebs."param" (
   id int8 not null,
    description varchar(255) not null,
    value varchar(255) not null,
    primary key (id)
);

create table ebs."user" (
   id int8 not null,
    active boolean not null,
    create_date timestamp,
    edit_date timestamp,
    email varchar(255),
    password varchar(255),
    primary key (id)
);

alter table ebs."user" add constraint email_constraint unique (email);

create table ebs.log (
       id int8 not null,
        action varchar(255) not null,
        end_request timestamp not null,
        remoteip varchar(255) not null,
        star_request timestamp not null,
        primary key (id)
    );