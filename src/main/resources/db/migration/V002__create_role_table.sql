CREATE TABLE `role` (
	id  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	description varchar(100) NOT NULL,
	CONSTRAINT role_unique unique (description)
);

insert INTO role (description) values ('ROLE_ADMIN');
insert INTO role (description) values ('ROLE_USER');