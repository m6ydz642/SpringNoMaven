create table users (
  id int auto_increment primary key,
  name varchar(20) not null,
  password varchar(20) not null
);
insert into users(name,password) values('joon','1234');
