create table users(
	id int not null auto_increment primary key,
	name varchar(50),
	password varchar(50),
	birthday varchar(50),
	current_liability int,
	month_salary int,
	phone varchar(50));
