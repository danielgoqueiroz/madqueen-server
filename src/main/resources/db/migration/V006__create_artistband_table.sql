CREATE TABLE `artistband` (
	artist_id BIGINT NOT NULL,
	band_id BIGINT NOT NULL,
	FOREIGN KEY (artist_id) REFERENCES artist(id),
	FOREIGN KEY (band_id) REFERENCES band(id),
	PRIMARY KEY (artist_id, band_id)
)