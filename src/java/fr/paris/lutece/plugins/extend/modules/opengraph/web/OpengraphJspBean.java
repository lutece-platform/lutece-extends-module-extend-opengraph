/*
 * Copyright (c) 2002-2021, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.extend.modules.opengraph.web;

import fr.paris.lutece.api.user.User;
import fr.paris.lutece.plugins.extend.modules.opengraph.business.OpengraphSocialHub;
import fr.paris.lutece.plugins.extend.modules.opengraph.service.OpengraphResourceIdService;
import fr.paris.lutece.plugins.extend.modules.opengraph.service.OpengraphService;
import fr.paris.lutece.portal.business.rbac.RBAC;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.rbac.RBACService;
import fr.paris.lutece.portal.util.mvc.admin.MVCAdminJspBean;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.utils.MVCUtils;
import fr.paris.lutece.portal.web.cdi.mvc.Models;
import fr.paris.lutece.portal.web.constants.Messages;
import fr.paris.lutece.portal.web.util.IPager;
import fr.paris.lutece.portal.web.util.Pager;
import fr.paris.lutece.util.url.UrlItem;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Back office controller managing the opengraph social hubs.
 */
@RequestScoped
@Named
@Controller( controllerJsp = OpengraphJspBean.CONTROLLER_JSP, controllerPath = OpengraphJspBean.CONTROLLER_PATH, right = OpengraphJspBean.MANAGE_OPENGRAPH_SOCIALHUB, securityTokenEnabled = true )
public class OpengraphJspBean extends MVCAdminJspBean
{
    /**
     * Right to manage opengraph social hub
     */
    public static final String MANAGE_OPENGRAPH_SOCIALHUB = "MANAGE_OPENGRAPH_SOCIALHUB";

    /**
     * JSP of this controller
     */
    public static final String CONTROLLER_JSP = "ManageOpengraphSocialHubs.jsp";

    /**
     * Path of the JSP of this controller
     */
    public static final String CONTROLLER_PATH = "jsp/admin/plugins/extend/modules/opengraph/";

    private static final long serialVersionUID = -7731456994777583575L;

    // PROPERTIES
    private static final String PROPERTY_ITEMS_PER_PAGE = "module.extend.opengraph.manage_opengraph_socialhub.itemsPerPage";
    private static final String PROPERTY_MANAGE_OPENGRAPH_PAGE_TITLE = "module.extend.opengraph.manage_opengraph_socialhub.pageTitle";
    private static final String PROPERTY_ADD_OPENGRAPH_PAGE_TITLE = "module.extend.opengraph.add_opengraph_socialhub.pageTitle";
    private static final String PROPERTY_MODIFY_OPENGRAPH_PAGE_TITLE = "module.extend.opengraph.modify_opengraph_socialhub.pageTitle";

    // MESSAGES
    private static final String MESSAGE_CONFIRM_REMOVE_SOCIALHUB = "module.extend.opengraph.remove_socialhub.confirmRemoveSocialHub";
    private static final String MESSAGE_SOCIALHUB_NOT_FOUND = "module.extend.opengraph.manage_opengraph_socialhub.socialhub.socialhubNotFound";

    // VIEWS
    private static final String VIEW_MANAGE_SOCIALHUBS = "manageSocialHubs";
    private static final String VIEW_CREATE_SOCIALHUB = "createSocialHub";
    private static final String VIEW_MODIFY_SOCIALHUB = "modifySocialHub";
    private static final String VIEW_CONFIRM_REMOVE_SOCIALHUB = "confirmRemoveSocialHub";

    // ACTIONS
    private static final String ACTION_CREATE_SOCIALHUB = VIEW_CREATE_SOCIALHUB;
    private static final String ACTION_MODIFY_SOCIALHUB = VIEW_MODIFY_SOCIALHUB;
    private static final String ACTION_REMOVE_SOCIALHUB = "removeSocialHub";

    // PARAMETERS
    private static final String PARAMETER_ID_SOCIALHUB = "id_socialhub";
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_CONTENT_HEADER = "content_header";
    private static final String PARAMETER_CONTENT_BODY = "content_body";
    private static final String PARAMETER_CONTENT_FOOTER = "content_footer";

    // MARKS
    private static final String MARK_SOCIALHUB_LIST = "socialhub_list";
    private static final String MARK_PERMISSIONS = "permissions";
    private static final String MARK_SOCIALHUB = "socialhub";

    // TEMPLATES
    private static final String TEMPLATE_MANAGE_OPENGRAPH_SOCIALHUB = "admin/plugins/extend/modules/opengraph/manage_opengraph_socialhub.html";
    private static final String TEMPLATE_ADD_OPENGRAPH_SOCIALHUB = "admin/plugins/extend/modules/opengraph/add_opengraph_socialhub.html";
    private static final String TEMPLATE_MODIFY_OPENGRAPH_SOCIALHUB = "admin/plugins/extend/modules/opengraph/modify_opengraph_socialhub.html";

    @Inject
    private OpengraphService _opengraphService;

    @Inject
    @Pager( listBookmark = MARK_SOCIALHUB_LIST, defaultItemsPerPage = PROPERTY_ITEMS_PER_PAGE, baseUrl = CONTROLLER_PATH + CONTROLLER_JSP )
    private IPager<OpengraphSocialHub, OpengraphSocialHub> _pager;

    /**
     * Displays the list of social hubs; a request that names no view is redirected to this view by name, so that it issues its token.
     *
     * @param request
     *            The request
     * @param model
     *            The model
     * @return The page
     */
    @View( value = VIEW_MANAGE_SOCIALHUBS, defaultView = true )
    public String getManageSocialHubs( HttpServletRequest request, Models model )
    {
        if ( request.getParameter( MVCUtils.PARAMETER_VIEW ) == null )
        {
            return redirectView( request, VIEW_MANAGE_SOCIALHUBS );
        }

        UrlItem urlPager = new UrlItem( CONTROLLER_PATH + CONTROLLER_JSP );
        urlPager.addParameter( MVCUtils.PARAMETER_VIEW, VIEW_MANAGE_SOCIALHUBS );
        _pager.withBaseUrl( urlPager.getUrl( ) ).withListItem( _opengraphService.findAll( ) ).populateModels( request, model, getLocale( ) );

        Map<String, Boolean> mapPermissions = new HashMap<>( );
        mapPermissions.put( OpengraphResourceIdService.PERMISSION_ADD_SOCIALHUB, isAuthorized( OpengraphResourceIdService.PERMISSION_ADD_SOCIALHUB ) );
        mapPermissions.put( OpengraphResourceIdService.PERMISSION_MODIFY_SOCIALHUB, isAuthorized( OpengraphResourceIdService.PERMISSION_MODIFY_SOCIALHUB ) );
        mapPermissions.put( OpengraphResourceIdService.PERMISSION_REMOVE_SOCIALHUB, isAuthorized( OpengraphResourceIdService.PERMISSION_REMOVE_SOCIALHUB ) );
        model.put( MARK_PERMISSIONS, mapPermissions );

        return getPage( PROPERTY_MANAGE_OPENGRAPH_PAGE_TITLE, TEMPLATE_MANAGE_OPENGRAPH_SOCIALHUB, model );
    }

    /**
     * Displays the creation form of a social hub.
     *
     * @param request
     *            The request
     * @param model
     *            The model
     * @return The page
     */
    @View( VIEW_CREATE_SOCIALHUB )
    public String getCreateSocialHub( HttpServletRequest request, Models model )
    {
        if ( !isAuthorized( OpengraphResourceIdService.PERMISSION_ADD_SOCIALHUB ) )
        {
            return redirectAccessDenied( request );
        }

        return getPage( PROPERTY_ADD_OPENGRAPH_PAGE_TITLE, TEMPLATE_ADD_OPENGRAPH_SOCIALHUB, model );
    }

    /**
     * Creates a social hub.
     *
     * @param request
     *            The request
     * @return The URL of the next page
     */
    @Action( ACTION_CREATE_SOCIALHUB )
    public String doCreateSocialHub( HttpServletRequest request )
    {
        if ( !isAuthorized( OpengraphResourceIdService.PERMISSION_ADD_SOCIALHUB ) )
        {
            return redirectAccessDenied( request );
        }

        OpengraphSocialHub socialHub = new OpengraphSocialHub( );

        if ( !populateSocialHub( socialHub, request ) )
        {
            addError( Messages.MANDATORY_FIELDS, getLocale( ) );

            return redirectView( request, VIEW_CREATE_SOCIALHUB );
        }

        _opengraphService.createOpengraphSocialHub( socialHub );

        return redirectView( request, VIEW_MANAGE_SOCIALHUBS );
    }

    /**
     * Displays the modification form of a social hub.
     *
     * @param request
     *            The request
     * @param model
     *            The model
     * @return The page
     */
    @View( VIEW_MODIFY_SOCIALHUB )
    public String getModifySocialHub( HttpServletRequest request, Models model )
    {
        if ( !isAuthorized( OpengraphResourceIdService.PERMISSION_MODIFY_SOCIALHUB ) )
        {
            return redirectAccessDenied( request );
        }

        OpengraphSocialHub socialHub = getSocialHub( request );

        if ( socialHub == null )
        {
            return redirectNotFound( request );
        }

        model.put( MARK_SOCIALHUB, socialHub );

        return getPage( PROPERTY_MODIFY_OPENGRAPH_PAGE_TITLE, TEMPLATE_MODIFY_OPENGRAPH_SOCIALHUB, model );
    }

    /**
     * Modifies a social hub.
     *
     * @param request
     *            The request
     * @return The URL of the next page
     */
    @Action( ACTION_MODIFY_SOCIALHUB )
    public String doModifySocialHub( HttpServletRequest request )
    {
        if ( !isAuthorized( OpengraphResourceIdService.PERMISSION_MODIFY_SOCIALHUB ) )
        {
            return redirectAccessDenied( request );
        }

        OpengraphSocialHub socialHub = getSocialHub( request );

        if ( socialHub == null )
        {
            return redirectNotFound( request );
        }

        if ( !populateSocialHub( socialHub, request ) )
        {
            addError( Messages.MANDATORY_FIELDS, getLocale( ) );

            return redirect( request, VIEW_MODIFY_SOCIALHUB, PARAMETER_ID_SOCIALHUB, socialHub.getOpengraphSocialHubId( ) );
        }

        _opengraphService.updateOpengraphSocialHub( socialHub );

        return redirectView( request, VIEW_MANAGE_SOCIALHUBS );
    }

    /**
     * Asks the user to confirm the removal of a social hub.
     *
     * @param request
     *            The request
     * @return The URL of the confirmation message
     */
    @View( value = VIEW_CONFIRM_REMOVE_SOCIALHUB, securityTokenAction = ACTION_REMOVE_SOCIALHUB )
    public String getConfirmRemoveSocialHub( HttpServletRequest request )
    {
        if ( !isAuthorized( OpengraphResourceIdService.PERMISSION_REMOVE_SOCIALHUB ) )
        {
            return redirectAccessDenied( request );
        }

        OpengraphSocialHub socialHub = getSocialHub( request );

        if ( socialHub == null )
        {
            return redirectNotFound( request );
        }

        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_SOCIALHUB ) );
        url.addParameter( PARAMETER_ID_SOCIALHUB, socialHub.getOpengraphSocialHubId( ) );

        return redirect( request,
                AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_SOCIALHUB, url.getUrl( ), AdminMessage.TYPE_CONFIRMATION ) );
    }

    /**
     * Removes a social hub.
     *
     * @param request
     *            The request
     * @return The URL of the next page
     */
    @Action( ACTION_REMOVE_SOCIALHUB )
    public String doRemoveSocialHub( HttpServletRequest request )
    {
        if ( !isAuthorized( OpengraphResourceIdService.PERMISSION_REMOVE_SOCIALHUB ) )
        {
            return redirectAccessDenied( request );
        }

        OpengraphSocialHub socialHub = getSocialHub( request );

        if ( socialHub == null )
        {
            return redirectNotFound( request );
        }

        _opengraphService.removeOpengraphSocialHub( socialHub.getOpengraphSocialHubId( ) );

        return redirectView( request, VIEW_MANAGE_SOCIALHUBS );
    }

    /**
     * Fills a social hub from the request.
     *
     * @param socialHub
     *            The social hub to fill
     * @param request
     *            The request
     * @return false when a mandatory field is missing
     */
    private boolean populateSocialHub( OpengraphSocialHub socialHub, HttpServletRequest request )
    {
        socialHub.setName( request.getParameter( PARAMETER_NAME ) );
        socialHub.setContentHeader( request.getParameter( PARAMETER_CONTENT_HEADER ) );
        socialHub.setContentBody( request.getParameter( PARAMETER_CONTENT_BODY ) );
        socialHub.setContentFooter( request.getParameter( PARAMETER_CONTENT_FOOTER ) );

        return StringUtils.isNotBlank( socialHub.getName( ) ) && StringUtils.isNotBlank( socialHub.getContentBody( ) );
    }

    /**
     * Loads the social hub named by the request.
     *
     * @param request
     *            The request
     * @return The social hub, or null when the id is missing or unknown
     */
    private OpengraphSocialHub getSocialHub( HttpServletRequest request )
    {
        int nId = NumberUtils.toInt( request.getParameter( PARAMETER_ID_SOCIALHUB ), -1 );

        return ( nId < 0 ) ? null : _opengraphService.getOpengraphSocialHub( nId );
    }

    /**
     * Tells whether the current user holds a permission on the social hubs.
     *
     * @param strPermission
     *            The permission
     * @return true when the user holds it
     */
    private boolean isAuthorized( String strPermission )
    {
        return RBACService.isAuthorized( OpengraphSocialHub.RESOURCE_TYPE, RBAC.WILDCARD_RESOURCES_ID, strPermission, (User) getUser( ) );
    }

    /**
     * Redirects to the access denied message.
     *
     * @param request
     *            The request
     * @return The URL of the message
     */
    private String redirectAccessDenied( HttpServletRequest request )
    {
        return redirect( request, AdminMessageService.getMessageUrl( request, Messages.USER_ACCESS_DENIED, AdminMessage.TYPE_STOP ) );
    }

    /**
     * Redirects to the social hub not found message.
     *
     * @param request
     *            The request
     * @return The URL of the message
     */
    private String redirectNotFound( HttpServletRequest request )
    {
        return redirect( request, AdminMessageService.getMessageUrl( request, MESSAGE_SOCIALHUB_NOT_FOUND, AdminMessage.TYPE_STOP ) );
    }
}
