insert into department(department_name) values ('Management');
insert into department(department_name) values ('Accounting');
insert into department(department_name) values ('Sales');
insert into department(department_name) values ('Human Resources');
insert into department(department_name) values ('Customer Service');
insert into department(department_name) values ('Quality Assurance');
insert into department(department_name) values ('Logistics');

insert into employee(first_name, last_name, title, department_id, email, contact) values ('David', 'Wallace','CFO', 1, 'david.wallce@gmail.com', '1-123-123-1234');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Jan', 'Levinson','VP', 1, 'jan.levinson@gmail.com', '1-113-113-1131');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Michael', 'Scott','Regional Manager', 1, 'michael.scott@gmail.com', '1-007-007-4200');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Angela', 'Martin','Accountant', 2, 'angela.martin@gmail.com', '1-910-910-9200');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Kelly', 'Kapoor','Customer Service Executive', 5, 'kelly.kapoor@gmail.com', '1-178-178-1931');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Stanley', 'Hudson','Sales Representative', 3, 'stanley.hudson@gmail.com', '1-121-121-1241');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Andrew', 'Bernard','Sales Representative', 3, 'andrew.bernard@gmail.com', '1-187-197-1891');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Toby', 'Flenderson','Human Resources Officer', 4, 'toby.flenderson@gmail.com', '1-861-861-2531');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Oscar', 'Martinez','Accountant', 2, 'oscar.martinez@gmail.com', '1-321-321-0102');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Daryl', 'Philbin','Warehouse Forman', 7, 'daryl.philbin@gmail.com', '1-471-420-6908');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Dwight', 'Shrute','Sales Represemtative', 3, 'dwight.shrute@gmail.com', '1-541-590-0071');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Jim', 'Halpert','Sales Represemtative', 3, 'jim.halpert@gmail.com', '1-632-506-0201');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Pam', 'Beesly','Customer Service', 5, 'pam.beesly@gmail.com', '1-851-087-3240');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Kevin', 'Malone','Accountant', 2, 'kevin.malone@gmail.com', '1-091-741-6172');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Phyllis', 'Lapin','Sales Represemtative', 3, 'phyllis.lapin@gmail.com', '1-196-091-1491');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Creed', 'Bratton','Quality Assurance Office', 6, 'creed.bratton@gmail.com', '1-175-192-4201');
insert into employee(first_name, last_name, title, department_id, email, contact) values ('Meredith', 'Palmer','Supplier Relations Representative', 5, 'meredith.palmer@gmail.com', '1-184-121-9138');

insert into role(role) values ('role.ADMIN');
insert into role(role) values ('role.USER');

insert into app_user(username, password, non_expired, non_locked, credentials_non_expired, enabled) values ('admin', '$2a$12$cqOkw0faE43ycT.239ZQlu8X7fUGejY7asj.nBChSujXXHNA99AIm', 1, 1, 1, 1);
insert into app_user(username, password, non_expired, non_locked, credentials_non_expired, enabled) values ('user', '$2a$12$cqOkw0faE43ycT.239ZQlu8X7fUGejY7asj.nBChSujXXHNA99AIm', 1, 1, 1, 1);

insert into role_data(user_id, role_id) values (1, 1);
insert into role_data(user_id, role_id) values (1, 2);
insert into role_data(user_id, role_id) values (2, 2);