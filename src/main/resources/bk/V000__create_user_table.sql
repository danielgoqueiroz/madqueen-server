CREATE TABLE `user` (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username varchar(100) NOT NULL,
	email varchar(1000) NOT NULL,
	password varchar(255) NOT NULL,
	cpf varchar(255) NOT NULL
)