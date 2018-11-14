-- data store 定义表
create table data_store (
    id varchar(32),
    name varchar(50),
    description varchar(200),
    initialized bit,
    createAt bigint,
    primary key(id)
);

insert into data_store (id, name, description, initialized)
values ('dataobject_id','my_unit_test_tbl','单元测试创建表', 0);