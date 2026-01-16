create table sentiments (
id bigint not null auto_increment,
texto varchar(2000) not null,
sentimiento varchar(100) not null,
probabilidad double not null,
fecha datetime not null,

primary key(id)
);