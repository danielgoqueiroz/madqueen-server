CREATE TABLE `musiccuriosity` (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	description varchar(1000) NOT NULL,
	music_id BIGINT NOT NULL,
	FOREIGN KEY (music_id) REFERENCES music(id)
)