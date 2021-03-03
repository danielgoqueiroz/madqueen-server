ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_unicode_ci;

CREATE TABLE `artist` (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(100) NOT NULL,
	description varchar(1000)
)

select * from artist;



CREATE TABLE `artistcuriosity` (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	description varchar(1000) NOT NULL,
	artist_id BIGINT NOT NULL,
	FOREIGN KEY (artist_id) REFERENCES artist(id)
)

CREATE TABLE `band` (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(100) NOT NULL,
	description varchar(1000) NOT NULL,
	crated int NOT NULL
)

CREATE TABLE `music` (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title varchar(100),
	crated_year int NOT NULL, 
	letter_original varchar(2500),
	letter_translation varchar(2500),
	youtube_link varchar(250),
	image_link varchar(250),
	artist_id BIGINT NOT NULL,
	band_id BIGINT NOT NULL,
	FOREIGN KEY (artist_id) REFERENCES artist(id),
	FOREIGN KEY (band_id) REFERENCES band(id)
)

CREATE TABLE `music_curiosity` (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	description varchar(1000) NOT NULL,
	music_id BIGINT NOT NULL,
	FOREIGN KEY (music_id) REFERENCES music(id)
)