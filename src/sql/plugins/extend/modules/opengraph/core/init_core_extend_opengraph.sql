-- liquibase formatted sql
-- changeset extend-opengraph:init_core_extend_opengraph.sql
-- preconditions onFail:MARK_RAN onError:WARN
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM core_admin_right WHERE id_right = 'MANAGE_OPENGRAPH_SOCIALHUB'
--
-- Init  table core_admin_right
--
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url, documentation_url) 
VALUES ('MANAGE_OPENGRAPH_SOCIALHUB', 'module.extend.opengraph.adminFeature.manage_opengraph_socialhub.name', '2', 'jsp/admin/plugins/extend/modules/opengraph/ManageOpengraphSocialHubs.jsp'
, 'module.extend.opengraph.adminFeature.manage_opengraph_socialhub.description', '0', 'extend-opengraph', 'CONTENT', 'ti ti-share', 'jsp/admin/documentation/AdminDocumentation.jsp?doc=admin-extend-opengraph');

--
-- Init  table core_user_right
--
INSERT INTO core_user_right (id_right,id_user) VALUES ('MANAGE_OPENGRAPH_SOCIALHUB',1);
INSERT INTO core_user_right (id_right,id_user) VALUES ('MANAGE_OPENGRAPH_SOCIALHUB',2);

--
-- Init  table core_admin_role
--
INSERT INTO core_admin_role (role_key,role_description) VALUES ('extend_opengraph_manager','Gestion des réseaux sociaux');

--
-- Init  table core_admin_role_resource
--
INSERT INTO core_admin_role_resource (role_key,resource_type,resource_id,permission) VALUES 
 ('extend_opengraph_manager','OPENGRAPH_SOCIAL_HUB','*','*');
 
--
-- Init  table core_user_role
--
INSERT INTO core_user_role (role_key,id_user) VALUES ('extend_opengraph_manager',1);
INSERT INTO core_user_role (role_key,id_user) VALUES ('extend_opengraph_manager',2);

-- changeset extend-opengraph:init_core_extend_opengraph-rev1.sql
-- preconditions onFail:MARK_RAN onError:WARN
UPDATE core_admin_right SET admin_url = 'jsp/admin/plugins/extend/modules/opengraph/ManageOpengraphSocialHubs.jsp', icon_url = 'ti ti-share' WHERE id_right = 'MANAGE_OPENGRAPH_SOCIALHUB';
