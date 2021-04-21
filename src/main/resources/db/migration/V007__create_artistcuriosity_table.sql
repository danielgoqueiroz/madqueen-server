CREATE TABLE `artistcuriosity` (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	description varchar(1000) NOT NULL,
	artist_id BIGINT NOT NULL,
	FOREIGN KEY (artist_id) REFERENCES artist(id)
)