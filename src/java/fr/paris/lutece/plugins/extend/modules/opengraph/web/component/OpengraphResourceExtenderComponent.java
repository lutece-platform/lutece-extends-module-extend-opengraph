/*
 * Copyright (c) 2002-2012, Mairie de Paris
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
package fr.paris.lutece.plugins.extend.modules.opengraph.web.component;

import fr.paris.lutece.plugins.extend.business.extender.ResourceExtenderDTO;
import fr.paris.lutece.plugins.extend.business.extender.config.IExtenderConfig;
import fr.paris.lutece.plugins.extend.modules.opengraph.business.OpengraphSocialHub;
import fr.paris.lutece.plugins.extend.modules.opengraph.business.config.OpengraphExtenderConfig;
import fr.paris.lutece.plugins.extend.modules.opengraph.service.OpengraphService;
import fr.paris.lutece.plugins.extend.modules.opengraph.service.extender.OpengraphResourceExtender;
import fr.paris.lutece.plugins.extend.service.extender.IResourceExtenderService;
import fr.paris.lutece.plugins.extend.service.extender.config.IResourceExtenderConfigService;
import fr.paris.lutece.plugins.extend.util.ExtendErrorException;
import fr.paris.lutece.plugins.extend.util.JSONUtils;
import fr.paris.lutece.plugins.extend.web.component.AbstractResourceExtenderComponent;
import fr.paris.lutece.portal.service.admin.AdminUserService;
import fr.paris.lutece.portal.service.resource.IExtendableResource;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * OpengraphResourceExtenderComponent
 * 
 */
public class OpengraphResourceExtenderComponent extends AbstractResourceExtenderComponent
{
    private static final String JSON_KEY_HEADER = "header";
    private static final String JSON_KEY_FOOTER = "footer";

    // MARKS
    private static final String MARK_URL = "url";
    private static final String MARK_TITLE = "title";
    private static final String MARK_DESCRIPTION = "description";
    private static final String MARK_IMAGE_URL = "image_url";
    private static final String MARK_SOCIALHUBS = "socialhubs";
    private static final String MARK_CONFIG = "config";

    // PARAMS
    private static final String PARAM_SOCIALHUB = "socialhub";

    // TEMPLATES
    private static final String TEMPLATE_SOCIAL_HEADER = "skin/plugins/extend/modules/opengraph/opengraph_header.html";
    private static final String TEMPLATE_SOCIAL_BODY = "skin/plugins/extend/modules/opengraph/opengraph_body.html";
    private static final String TEMPLATE_SOCIAL_FOOTER = "skin/plugins/extend/modules/opengraph/opengraph_footer.html";
    private static final String TEMPLATE_MODIFY_OPENGRAPH_CONFIG = "admin/plugins/extend/modules/opengraph/modify_opengraph_config.html";

    // PROPERTIES
    private static final String PROPERTY_DEFAULT_IMAGE_URL = "module.extend.opengraph.defaultImageUrl";

    private static final String CONSTANT_QUESTION_MARK = "?";
    private static final String CONSTANT_UNDERSCORE = "_";
    private static final String CONSTANT_HTTP = "http://";

    // SERVICES
    @Inject
    private IResourceExtenderService _resourceExtenderService;
    @Inject
    @Named( "extend-opengraph.opengraphExtenderConfigService" )
    private IResourceExtenderConfigService _configService;
    @Inject
    private OpengraphService _opengraphService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildXmlAddOn( String strIdExtendableResource, String strExtendableResourceType, String strParameters,
        StringBuffer strXml )
    {
        // Nothing yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageAddOn( String strIdExtendableResource, String strExtendableResourceType, String strParameters,
        HttpServletRequest request )
    {
        if ( isHeader( strParameters ) )
        {
            // header
            IExtendableResource extendableResource = _resourceExtenderService.getExtendableResource(
                    strIdExtendableResource, strExtendableResourceType );

            Map<String, Object> model = new HashMap<String, Object>( );

            String strImageUrl = extendableResource.getExtendableResourceImageUrl( );
            if ( StringUtils.isEmpty( strImageUrl ) )
            {
                strImageUrl = AppPropertiesService.getProperty( PROPERTY_DEFAULT_IMAGE_URL );
            }
            if ( StringUtils.isNotEmpty( strImageUrl ) )
            {
                if ( !strImageUrl.startsWith( CONSTANT_HTTP ) )
                {
                    strImageUrl = AppPathService.getBaseUrl( request ) + strImageUrl;
                }
            }
            model.put( MARK_IMAGE_URL, strImageUrl );
            StringBuffer sbUrl = request.getRequestURL( );
            if ( sbUrl != null )
            {
                String strQuery = request.getQueryString( );
                if ( StringUtils.isNotBlank( strQuery ) )
                {
                    sbUrl.append( CONSTANT_QUESTION_MARK );
                    sbUrl.append( strQuery );
                }
            }
            OpengraphExtenderConfig config = _configService.find( OpengraphResourceExtender.EXTENDER_TYPE,
                    strIdExtendableResource, strExtendableResourceType );
            List<Integer> listSocialHubId = config.getListOpengraphSocialHubId( );
            List<String> listSocialHubs = new ArrayList<String>( );
            for ( OpengraphSocialHub socialHub : _opengraphService.findAll( ) )
            {
                if ( listSocialHubId.contains( socialHub.getOpengraphSocialHubId( ) ) )
                {
                    listSocialHubs.add( socialHub.getContentHeader( ) );
                }
            }

            model.put( MARK_SOCIALHUBS, listSocialHubs );
            model.put( MARK_URL, sbUrl );

            model.put( MARK_TITLE, extendableResource.getExtendableResourceName( ) );
            model.put( MARK_DESCRIPTION, extendableResource.getExtendableResourceDescription( ) );

            HtmlTemplate template = AppTemplateService
                    .getTemplate( TEMPLATE_SOCIAL_HEADER, request.getLocale( ), model );
            return template.getHtml( );
        }
        if ( isFooter( strParameters ) )
        {
            // footer
            OpengraphExtenderConfig config = _configService.find( OpengraphResourceExtender.EXTENDER_TYPE,
                    strIdExtendableResource, strExtendableResourceType );
            List<OpengraphSocialHub> listOpengraphSocialHub = _opengraphService.findAll( );
            List<Integer> listSocialHubId = config.getListOpengraphSocialHubId( );
            List<String> listSocialHubs = new ArrayList<String>( );
            for ( OpengraphSocialHub socialHub : listOpengraphSocialHub )
            {
                if ( listSocialHubId.contains( socialHub.getOpengraphSocialHubId( ) ) )
                {
                    listSocialHubs.add( socialHub.getContentFooter( ) );
                }
            }
            Map<String, Object> model = new HashMap<String, Object>( );
            model.put( MARK_SOCIALHUBS, listSocialHubs );
            HtmlTemplate template = AppTemplateService
                    .getTemplate( TEMPLATE_SOCIAL_FOOTER, request.getLocale( ), model );
            return template.getHtml( );
        }
        // body
        OpengraphExtenderConfig config = _configService.find( OpengraphResourceExtender.EXTENDER_TYPE,
                strIdExtendableResource, strExtendableResourceType );
        List<OpengraphSocialHub> listOpengraphSocialHub = _opengraphService.findAll( );
        List<Integer> listSocialHubId = config.getListOpengraphSocialHubId( );
        List<String> listSocialHubs = new ArrayList<String>( );
        for ( OpengraphSocialHub socialHub : listOpengraphSocialHub )
            {
            if ( listSocialHubId.contains( socialHub.getOpengraphSocialHubId( ) ) )
                {
                listSocialHubs.add( socialHub.getContentBody( ) );
                }
            }
        Map<String, Object> model = new HashMap<String, Object>( );
        model.put( MARK_SOCIALHUBS, listSocialHubs );
        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_SOCIAL_BODY, request.getLocale( ), model );
        return template.getHtml( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfigHtml( ResourceExtenderDTO resourceExtender, Locale locale, HttpServletRequest request )
    {
        List<OpengraphSocialHub> listOpengraphSocialHub = _opengraphService.findAll( );

        IExtenderConfig extenderConfig = getConfig( resourceExtender.getIdExtender( ) );

        Map<String, Object> model = new HashMap<String, Object>( );

        model.put( MARK_SOCIALHUBS, listOpengraphSocialHub );
        model.put( MARK_CONFIG, extenderConfig );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_OPENGRAPH_CONFIG,
                AdminUserService.getLocale( request ), model );

        return template.getHtml( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IExtenderConfig getConfig( int nIdExtender )
    {
        return _configService.find( nIdExtender );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSaveConfig( HttpServletRequest request, IExtenderConfig config ) throws ExtendErrorException
    {
        OpengraphExtenderConfig extenderConfig = (OpengraphExtenderConfig) config;
        List<OpengraphSocialHub> listOpengraphSocialHub = _opengraphService.findAll( );
        List<Integer> listSocialHubId = extenderConfig.getListOpengraphSocialHubId( );

        for ( OpengraphSocialHub socialHub : listOpengraphSocialHub )
        {
            if ( StringUtils.isNotBlank( request.getParameter( PARAM_SOCIALHUB + CONSTANT_UNDERSCORE
                    + socialHub.getOpengraphSocialHubId( ) ) ) )
            {
                if ( !listSocialHubId.contains( socialHub.getOpengraphSocialHubId( ) ) )
                {
                    extenderConfig.addOpengraphSocialHubId( socialHub.getOpengraphSocialHubId( ) );
                }
            }
            else
            {
                if ( listSocialHubId.contains( socialHub.getOpengraphSocialHubId( ) ) )
                {
                    extenderConfig.removeOpengraphSocialHubId( socialHub.getOpengraphSocialHubId( ) );
                }
            }
        }
        _configService.update( extenderConfig );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfoHtml( ResourceExtenderDTO resourceExtender, Locale locale, HttpServletRequest request )
    {
        //TODO : implement me
        return StringUtils.EMPTY;
    }

    /**
     * Check if the <i>header</i> parameter is set to true
     * @param strParameters the parameters
     * @return True if the <i>header</i> parameter is set to true, false
     *         otherwise
     */
    private boolean isHeader( String strParameters )
    {
        String strHeaderParameter = StringUtils.EMPTY;
        JSONObject jsonParameters = JSONUtils.parseParameters( strParameters );

        if ( jsonParameters != null )
        {
            try
            {
                strHeaderParameter = jsonParameters.getString( JSON_KEY_HEADER );
            }
            catch ( JSONException je )
            {
                return false;
            }
        }

        return Boolean.parseBoolean( strHeaderParameter );
    }

    /**
     * Check if the <i>footer</i> parameter is set to true
     * @param strParameters the parameters
     * @return True if the <i>footer</i> parameter is set to true, false
     *         otherwise
     */
    private boolean isFooter( String strParameters )
    {
        String strFooterParameter = StringUtils.EMPTY;
        JSONObject jsonParameters = JSONUtils.parseParameters( strParameters );

        if ( jsonParameters != null )
        {
            try
            {
                strFooterParameter = jsonParameters.getString( JSON_KEY_FOOTER );
            }
            catch ( JSONException je )
            {
                return false;
            }
        }

        return Boolean.parseBoolean( strFooterParameter );
    }

}
