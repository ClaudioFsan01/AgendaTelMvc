create database agenda;

use agenda;

create table agenda ( nome varchar(40) not null, telefone varchar(40) not null);

alter table agenda add primary key (telefone);
