drop table if exists department;
drop table if exists employee;

create table department (
	department_id int auto_increment,
	department_name varchar(50) not null,
	constraint PK_Department primary key (department_id)
);


create table employee (
	employee_id int auto_increment,
	first_name varchar(20) not null, 
	last_name varchar(20) not null, 
	title varchar(50) not null,
	department_id int not null,
	email varchar(60) not null,
	contact varchar(20) not null,
	constraint PK_Employee primary key (employee_id),
	constraint FK_EmployeeDepartment foreign key (department_id) references department
);


create table app_user (
	user_id int auto_increment,
	username varchar(20) not null,
	password varchar(100) not null,
	non_expired boolean not null,
	non_locked boolean not null,
	credentials_non_expired boolean not null,
	enabled boolean not null,
	constraint PK_AppUser primary key (user_id)
);

create table role (
	role_id int auto_increment,
	role varchar(20) not null,
	constraint PK_Role primary key (role_id)
);

create table role_data (
	role_data_id int auto_increment,
	user_id int not null,
	role_id int not null,
	constraint FK_RoleDataUser foreign key (user_id) references app_user,
	constraint FK_RoleDataRole foreign key (role_id) references role
);