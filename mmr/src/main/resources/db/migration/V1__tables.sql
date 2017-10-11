create table SYS_ATTRS (
   id_ varchar(50) not null,
   name_ varchar(50) not null,
   desc_ varchar(200),
   type_ varchar(50),
   value_ varchar(200),
   source_ varchar(50),
   options_ text,
   store_editor_ varchar(200),
   primary key(id_);
);

create table SYS_DATA_OBJECTS (
   id_ varchar(50) not null,
   name_ varchar(50) not null,
   desc_ varchar(200),
   primary key(id_);
);

create table SYS_DATA_OBJECT_ATTRS (
   id_ varchar(50) not null,
   object_ varchar(50) not null,
   attr_ varchar(200) not null,
   primary key(id_);
);