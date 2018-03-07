create table SYS_ATTRS (
   id_ varchar(50) not null,
   name_ varchar(50) not null,
   desc_ varchar(200),
   type_ varchar(50),
   value_ varchar(200),
   source_ varchar(50),
   options_ text,
   primary key(id_)
);

create table SYS_DATA_OBJECTS (
   id_ varchar(50) not null,
   name_ varchar(50) not null,
   desc_ varchar(200),
   primary key(id_)
);

create table SYS_DATA_OBJECT_ATTRS (
   id_ varchar(50) not null,
   object_ varchar(50) not null,
   attr_ varchar(200) not null,
   primary key(id_)
);

create table SAMPLE_USER_INFO (
   id_ varchar(50) not null ,
   name_ varchar(80),
   age_  int,
   primary key(id_)
);

create table CORE_CONFIG (
    id_ varchar(50) not null ,
    config_ text,
    primary key(id_)
)