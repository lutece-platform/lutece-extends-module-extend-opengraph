package fr.paris.lutece.plugins.extend.modules.opengraph.web;

import fr.paris.lutece.plugins.extend.modules.opengraph.business.OpengraphSocialHub;
import fr.paris.lutece.plugins.extend.modules.opengraph.service.OpengraphResourceIdService;
import fr.paris.lutece.plugins.extend.modules.opengraph.service.OpengraphService;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.rbac.RBACService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.admin.AdminFeaturesPageJspBean;
import fr.paris.lutece.portal.web.pluginaction.DefaultPluginActionResult;
import fr.paris.lutece.portal.web.pluginaction.IPluginActionResult;
import fr.paris.lutece.util.datatable.DataTableManager;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.url.UrlItem;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


public class OpengraphJspBean extends AdminFeaturesPageJspBean
{
    public static final String MANAGE_OPENGRAPH_SOCIALHUB = "MANAGE_OPENGRAPH_SOCIALHUB";

    // PROPERTIES
    private static final String PROPERTY_DEFAULT_ITEMS_PER_PAGE = "module.extend.opengraph.manage_opengraph_socialhub.itemsPerPage";
    private static final String PROPERTY_MANAGE_OPENGRAPH_PAGE_TITLE = "module.extend.opengraph.manage_opengraph_socialhub.pageTitle";
    private static final String PROPERTY_ADD_OPENGRAPH_PAGE_TITLE = "module.extend.opengraph.add_opengraph_socialhub.pageTitle";
    private static final String PROPERTY_MODIFY_OPENGRAPH_PAGE_TITLE = "module.extend.opengraph.modify_opengraph_socialhub.pageTitle";

    // MESSAGES
    private static final String MESSAGE_LABEL_SOCIALHUB_NAME = "module.extend.opengraph.manage_opengraph_socialhub.socialhub.name";
    private static final String MESSAGE_LABEL_ACTION = "module.extend.opengraph.manage_opengraph_socialhub.socialhub.action";
    private static final String MESSAGE_UNAUTHORIZED_ACTION = "extend.message.unauthorizedAction";
    private static final String MESSAGE_CONFIRM_REMOVE_SOCIALHUB = "module.extend.opengraph.remove_socialhub.confirmRemoveSocialHub";
    private static final String MESSAGE_MANDATORY_FIELDS = "portal.util.message.mandatoryFields";
    private static final String MESSAGE_SOCIALHUB_NOT_FOUND = "module.extend.opengraph.manage_opengraph_socialhub.socialhub.socialhubNotFound";

    // PARAMETERS
    private static final String PARAMETER_SOCIALHUB_NAME = "name";
    private static final String PARAMETER_CANCEL = "cancel";
    private static final String PARAMETER_ID_SOCIALHUB = "id_socialhub";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_CONTENT_HEADER = "content_header";
    private static final String PARAM_CONTENT_BODY = "content_body";
    private static final String PARAM_CONTENT_FOOTER = "content_footer";

    // MARKS
    private static final String MARK_DATA_TABLE_MANAGER = "dataTableManager";
    private static final String MARK_PERMISSIONS = "permissions";
    private static final String MARK_LOCALE = "locale";
    private static final String MARK_WEBAPP_URL = "webapp_url";
    private static final String MARK_SOCIALHUB = "socialhub";

    // TEMPLATES
    private static final String TEMPLATE_MANAGE_OPENGRAPH_SOCIALHUB = "admin/plugins/extend/modules/opengraph/manage_opengraph_socialhub.html";
    private static final String TEMPLATE_ADD_OPENGRAPH_SOCIALHUB = "admin/plugins/extend/modules/opengraph/add_opengraph_socialhub.html";
    private static final String TEMPLATE_MODIFY_OPENGRAPH_SOCIALHUB = "admin/plugins/extend/modules/opengraph/modify_opengraph_socialhub.html";

    //URL
    private static final String JSP_URL_MANAGE_OPENGRAPH_SOCIALHUB = "jsp/admin/plugins/extend/modules/opengraph/GetManageOpengraphSocialHub.jsp";
    private static final String JSP_URL_REMOVE_OPENGRAPH_SOCIALHUB = "jsp/admin/plugins/extend/modules/opengraph/DoRemoveOpengraphSocialHub.jsp";

    // local variables
    private OpengraphService _opengraphService = SpringContextService.getBean( OpengraphService.BEAN_NAME );
    private DataTableManager<OpengraphSocialHub> _dataTableManager = null;


    /**
     * Get the ManageOpengraphSocialHub page
     * @param request The request
     * @return The html of the content to display
     */
    public String getManageOpengraphSocialHub( HttpServletRequest request )
    {

        setPageTitleProperty( PROPERTY_MANAGE_OPENGRAPH_PAGE_TITLE );

        if ( _dataTableManager == null )
        {
            _dataTableManager = new DataTableManager<OpengraphSocialHub>( JSP_URL_MANAGE_OPENGRAPH_SOCIALHUB,
                    JSP_URL_MANAGE_OPENGRAPH_SOCIALHUB, AppPropertiesService.getPropertyInt(
                            PROPERTY_DEFAULT_ITEMS_PER_PAGE, 50 ), true );
            _dataTableManager.addColumn( MESSAGE_LABEL_SOCIALHUB_NAME, PARAMETER_SOCIALHUB_NAME, true );
            _dataTableManager.addActionColumn( MESSAGE_LABEL_ACTION );
        }
        _dataTableManager.filterSortAndPaginate( request, _opengraphService.findAll( ) );
        
        Map<String, Object> model = new HashMap<String, Object>( );
        model.put( MARK_DATA_TABLE_MANAGER, _dataTableManager );
        
        AdminUser user = AdminUserService.getAdminUser( request );

        Map<String, Boolean> mapPermissions = new HashMap<String, Boolean>( );
        mapPermissions.put( OpengraphResourceIdService.PERMISSION_MODIFY_SOCIALHUB, RBACService.isAuthorized(
                OpengraphSocialHub.RESOURCE_TYPE, null, OpengraphResourceIdService.PERMISSION_MODIFY_SOCIALHUB, user ) );
        mapPermissions.put( OpengraphResourceIdService.PERMISSION_ADD_SOCIALHUB, RBACService.isAuthorized(
                OpengraphSocialHub.RESOURCE_TYPE, null, OpengraphResourceIdService.PERMISSION_ADD_SOCIALHUB, user ) );
        mapPermissions
                .put( OpengraphResourceIdService.PERMISSION_REMOVE_SOCIALHUB, RBACService.isAuthorized(
                OpengraphSocialHub.RESOURCE_TYPE, null, OpengraphResourceIdService.PERMISSION_REMOVE_SOCIALHUB, user ) );

        model.put( MARK_PERMISSIONS, mapPermissions );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MANAGE_OPENGRAPH_SOCIALHUB,
                AdminUserService.getLocale( request ), model );
        String strContent = getAdminPage( template.getHtml( ) );

        _dataTableManager.clearItems( );
        return strContent;
    }

    public IPluginActionResult getAddOpengraphSocialHub( HttpServletRequest request )
    {
        if ( !RBACService.isAuthorized( OpengraphSocialHub.RESOURCE_TYPE, null,
                OpengraphResourceIdService.PERMISSION_ADD_SOCIALHUB, AdminUserService.getAdminUser( request ) ) )
        {
            IPluginActionResult result = new DefaultPluginActionResult( );
            result.setRedirect( AdminMessageService.getMessageUrl( request, MESSAGE_UNAUTHORIZED_ACTION,
                    AdminMessage.TYPE_STOP ) );

            return result;
        }

        setPageTitleProperty( PROPERTY_ADD_OPENGRAPH_PAGE_TITLE );

        Map<String, Object> model = new HashMap<String, Object>( );
        model.put( MARK_LOCALE, AdminUserService.getLocale( request ) );
        model.put( MARK_WEBAPP_URL, AppPathService.getBaseUrl( request ) );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_ADD_OPENGRAPH_SOCIALHUB,
                AdminUserService.getLocale( request ), model );

        IPluginActionResult result = new DefaultPluginActionResult( );
        result.setHtmlContent( template.getHtml( ) );
        return result;
    }

    public String doAddOpengraphSocialHub( HttpServletRequest request )
    {
        if ( request.getParameter( PARAMETER_CANCEL ) != null )
        {
            return AppPathService.getBaseUrl( request ) + JSP_URL_MANAGE_OPENGRAPH_SOCIALHUB;
        }

        if ( !RBACService.isAuthorized( OpengraphSocialHub.RESOURCE_TYPE, null,
                OpengraphResourceIdService.PERMISSION_ADD_SOCIALHUB, AdminUserService.getAdminUser( request ) ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_UNAUTHORIZED_ACTION, AdminMessage.TYPE_STOP );
        }

        String strName = request.getParameter( PARAM_NAME );
        if ( StringUtils.isBlank( strName ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        String strContentHeader = request.getParameter( PARAM_CONTENT_HEADER );
        String strContentBody = request.getParameter( PARAM_CONTENT_BODY );
        if ( StringUtils.isBlank( strContentBody ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }
        String strContentFooter = request.getParameter( PARAM_CONTENT_FOOTER );

        OpengraphSocialHub opengraphSocialHub = new OpengraphSocialHub( );
        opengraphSocialHub.setName( strName );
        opengraphSocialHub.setContentHeader( strContentHeader );
        opengraphSocialHub.setContentBody( strContentBody );
        opengraphSocialHub.setContentFooter( strContentFooter );

        _opengraphService.createOpengraphSocialHub( opengraphSocialHub );

        return AppPathService.getBaseUrl( request ) + JSP_URL_MANAGE_OPENGRAPH_SOCIALHUB;
    }

    public IPluginActionResult getModifyOpengraphSocialHub( HttpServletRequest request )
    {
        if ( !RBACService.isAuthorized( OpengraphSocialHub.RESOURCE_TYPE, null,
                OpengraphResourceIdService.PERMISSION_MODIFY_SOCIALHUB, AdminUserService.getAdminUser( request ) ) )
        {
            IPluginActionResult result = new DefaultPluginActionResult( );
            result.setRedirect( AdminMessageService.getMessageUrl( request, MESSAGE_UNAUTHORIZED_ACTION,
                    AdminMessage.TYPE_STOP ) );

            return result;
        }

        setPageTitleProperty( PROPERTY_MODIFY_OPENGRAPH_PAGE_TITLE );

        int nOpengraphSocialHubId = 0;
        try
        {
            nOpengraphSocialHubId = Integer.parseInt( request.getParameter( PARAMETER_ID_SOCIALHUB ) );
        }
        catch ( NumberFormatException e )
        {
            IPluginActionResult result = new DefaultPluginActionResult( );
            result.setRedirect( AdminMessageService.getMessageUrl( request, MESSAGE_SOCIALHUB_NOT_FOUND,
                    AdminMessage.TYPE_STOP ) );
            return result;
        }

        OpengraphSocialHub opengraphSocialHub = _opengraphService.getOpengraphSocialHub( nOpengraphSocialHubId );
        if ( opengraphSocialHub == null )
        {
            IPluginActionResult result = new DefaultPluginActionResult( );
            result.setRedirect( AdminMessageService.getMessageUrl( request, MESSAGE_SOCIALHUB_NOT_FOUND,
                    AdminMessage.TYPE_STOP ) );
            return result;
        }

        Map<String, Object> model = new HashMap<String, Object>( );
        model.put( MARK_LOCALE, AdminUserService.getLocale( request ) );
        model.put( MARK_WEBAPP_URL, AppPathService.getBaseUrl( request ) );
        model.put( MARK_SOCIALHUB, opengraphSocialHub );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_OPENGRAPH_SOCIALHUB,
                AdminUserService.getLocale( request ), model );

        IPluginActionResult result = new DefaultPluginActionResult( );
        result.setHtmlContent( template.getHtml( ) );
        return result;
    }

    public String doModifyOpengraphSocialHub( HttpServletRequest request )
    {
        if ( request.getParameter( PARAMETER_CANCEL ) != null )
        {
            return AppPathService.getBaseUrl( request ) + JSP_URL_MANAGE_OPENGRAPH_SOCIALHUB;
        }

        if ( !RBACService.isAuthorized( OpengraphSocialHub.RESOURCE_TYPE, null,
                OpengraphResourceIdService.PERMISSION_MODIFY_SOCIALHUB, AdminUserService.getAdminUser( request ) ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_UNAUTHORIZED_ACTION, AdminMessage.TYPE_STOP );
        }

        String strName = request.getParameter( PARAM_NAME );
        if ( StringUtils.isBlank( strName ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        String strContentHeader = request.getParameter( PARAM_CONTENT_HEADER );
        String strContentBody = request.getParameter( PARAM_CONTENT_BODY );
        if ( StringUtils.isBlank( strContentBody ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }
        String strContentFooter = request.getParameter( PARAM_CONTENT_FOOTER );

        int nOpengraphSocialHubId = 0;
        try
        {
            nOpengraphSocialHubId = Integer.parseInt( request.getParameter( PARAMETER_ID_SOCIALHUB ) );
        }
        catch ( NumberFormatException e )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        OpengraphSocialHub opengraphSocialHub = new OpengraphSocialHub( );
        opengraphSocialHub.setName( strName );
        opengraphSocialHub.setContentHeader( strContentHeader );
        opengraphSocialHub.setContentBody( strContentBody );
        opengraphSocialHub.setContentFooter( strContentFooter );
        opengraphSocialHub.setOpengraphSocialHubId( nOpengraphSocialHubId );

        _opengraphService.updateOpengraphSocialHub( opengraphSocialHub );

        return AppPathService.getBaseUrl( request ) + JSP_URL_MANAGE_OPENGRAPH_SOCIALHUB;
    }

    public String confirmRemoveOpengraphSocialHub( HttpServletRequest request )
    {
        String strId = request.getParameter( PARAMETER_ID_SOCIALHUB );
        if ( StringUtils.isBlank( strId ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        UrlItem url = new UrlItem( JSP_URL_REMOVE_OPENGRAPH_SOCIALHUB );
        url.addParameter( PARAMETER_ID_SOCIALHUB, strId );
        
        return AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_SOCIALHUB, url.getUrl( ),
                AdminMessage.TYPE_CONFIRMATION );
    }

    public String doRemoveOpengraphSocialHub( HttpServletRequest request )
    {
        String strId = request.getParameter( PARAMETER_ID_SOCIALHUB );
        if ( StringUtils.isBlank( strId ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }
        int nId = 0;
        try
        {
            nId = Integer.parseInt( strId );
        }
        catch ( NumberFormatException e )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        _opengraphService.removeOpengraphSocialHub( nId );
        return AppPathService.getBaseUrl( request ) + JSP_URL_MANAGE_OPENGRAPH_SOCIALHUB;
    }
}
