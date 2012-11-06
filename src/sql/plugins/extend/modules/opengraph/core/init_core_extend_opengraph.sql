--
-- Init  table core_admin_right
--
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url, documentation_url) 
VALUES ('MANAGE_OPENGRAPH_SOCIALHUB', 'module.extend.opengraph.adminFeature.manage_opengraph_socialhub.name', '2', 'jsp/admin/plugins/extend/modules/opengraph/GetManageOpengraphSocialHub.jsp'
, 'module.extend.opengraph.adminFeature.manage_opengraph_socialhub.description', '0', 'extend-opengraph', 'CONTENT', 'images/admin/skin/plugins/extend/modules/opengraph/extend-opengraph.png', 'jsp/admin/documentation/AdminDocumentation.jsp?doc=admin-extend-opengraph');

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
INSERT INTO core_admin_role_resource (rbac_id,role_key,resource_type,resource_id,permission) VALUES 
 (558,'extend_opengraph_manager','OPENGRAPH_SOCIAL_HUB','*','*');
 
--
-- Init  table core_user_role
--
INSERT INTO core_user_role (role_key,id_user) VALUES ('extend_opengraph_manager',1);
INSERT INTO core_user_role (role_key,id_user) VALUES ('extend_opengraph_manager',2);
