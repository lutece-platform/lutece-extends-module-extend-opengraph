-- liquibase formatted sql
-- changeset extend-opengraph:create_db_extend_opengraph.sql
-- preconditions onFail:MARK_RAN onError:WARN
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'extend_opengraph_socialhub'
DROP TABLE IF EXISTS extend_opengraph_socialhub;
CREATE TABLE extend_opengraph_socialhub (
	opengraph_socialhub_id INT AUTO_INCREMENT NOT NULL,
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

-- changeset extend-opengraph:create_db_extend_opengraph-rev1.sql dbms:mariadb,mysql
-- preconditions onFail:MARK_RAN onError:WARN
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'extend_opengraph_socialhub' AND column_name = 'opengraph_socialhub_id' AND extra LIKE '%auto_increment%'
SET SESSION sql_mode='NO_AUTO_VALUE_ON_ZERO';
ALTER TABLE extend_opengraph_socialhub MODIFY opengraph_socialhub_id INT AUTO_INCREMENT NOT NULL;
