-- data store 定义表
create table my_unit_test_tbl (id bigint not null auto_increment, name varchar(50), locked bit, primary key(id));
insert into my_unit_test_tbl(id, name, locked) values (1, 'Hello', 0);