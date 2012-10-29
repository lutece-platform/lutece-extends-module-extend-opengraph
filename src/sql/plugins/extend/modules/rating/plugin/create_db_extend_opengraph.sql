--
-- Structure for table extend_rating
--
DROP TABLE IF EXISTS opengraph_socialhub;
CREATE TABLE opengraph_socialhub (
	opengraph_social_id INT DEFAULT 0 NOT NULL,
	name VARCHAR(255) DEFAULT '' NOT NULL,
	content VARCHAR DEFAULT '' NOT NULL,
	PRIMARY KEY (opengraph_social_id)
);
