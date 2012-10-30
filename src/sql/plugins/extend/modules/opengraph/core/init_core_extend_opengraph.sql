--
-- Init  table core_admin_right
--
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url, documentation_url) 
VALUES ('MANAGE_OPENGRAPH_SOCIALHUB', 'module.extend.opengraph.adminFeature.manage_opengraph_socialhub.name', '2', 'jsp/admin/plugins/extend/modules/opengraph/GetManageOpengraphSocialHub.jsp'
, 'module.extend.opengraph.adminFeature.manage_opengraph_socialhub.description', '0', 'extend-opengraph', NULL, NULL, 'jsp/admin/documentation/AdminDocumentation.jsp?doc=admin-extend-opengraph');

--
-- Init  table core_user_right
--
INSERT INTO core_user_right (id_right,id_user) VALUES ('MANAGE_OPENGRAPH_SOCIALHUB',1);
INSERT INTO core_user_right (id_right,id_user) VALUES ('MANAGE_OPENGRAPH_SOCIALHUB',2);
