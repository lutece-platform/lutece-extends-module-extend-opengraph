DROP TABLE IF EXISTS opengraph_socialhub;
CREATE TABLE opengraph_socialhub (
	opengraph_socialhub_id INT NOT NULL,
	name VARCHAR(255) DEFAULT '' NOT NULL,
	content LONG VARCHAR NOT NULL,
	PRIMARY KEY (opengraph_socialhub_id)
);

DROP TABLE IF EXISTS opengraph_config;
CREATE TABLE opengraph_config (
	id_extender INT NOT NULL,
	id_socialhub INT NOT NULL,
	PRIMARY KEY (opengraph_socialhub_id, id_socialhub)
);