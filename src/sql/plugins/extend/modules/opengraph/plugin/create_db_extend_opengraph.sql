DROP TABLE IF EXISTS extend_opengraph_socialhub;
CREATE TABLE extend_opengraph_socialhub (
	opengraph_socialhub_id INT NOT NULL,
	name VARCHAR(255) DEFAULT '' NOT NULL,
	content_header LONG VARCHAR NULL,
	content_body LONG VARCHAR NOT NULL,
	content_footer LONG VARCHAR NULL,
	PRIMARY KEY (opengraph_socialhub_id)
);

DROP TABLE IF EXISTS extend_opengraph_config;
CREATE TABLE extend_opengraph_config (
	id_extender INT NOT NULL,
	id_socialhub INT NOT NULL,
	PRIMARY KEY (id_extender, id_socialhub)
);