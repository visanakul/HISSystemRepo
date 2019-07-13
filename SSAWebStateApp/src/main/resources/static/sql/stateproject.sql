create table ROLE_MASTER(
role_id number(2) primary key,
role_name varchar2(15)
);
insert all
	into ROLE_MASTER values(1,'Admin')
	into ROLE_MASTER values(2,'Case Worker')
select * from dual;
