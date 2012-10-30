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
import fr.paris.lutece.plugins.extend.modules.opengraph.service.OpengraphService;
import fr.paris.lutece.plugins.extend.service.extender.IResourceExtenderService;
import fr.paris.lutece.plugins.extend.service.extender.config.IResourceExtenderConfigService;
import fr.paris.lutece.plugins.extend.service.extender.history.IResourceExtenderHistoryService;
import fr.paris.lutece.plugins.extend.util.ExtendErrorException;
import fr.paris.lutece.plugins.extend.util.JSONUtils;
import fr.paris.lutece.plugins.extend.web.component.AbstractResourceExtenderComponent;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.util.html.HtmlTemplate;

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

    // MARKS
    private static final String MARK_URL = "url";
    private static final String MARK_TITLE = "title";
    private static final String MARK_DESCRIPTION = "description";
    private static final String MARK_BASE_URL = "base_url";

    // TEMPLATES
    private static final String TEMPLATE_SOCIAL_HEADER = "skin/plugins/extend/modules/opengraph/opengraph_header.html";
    private static final String TEMPLATE_MODIFY_OPENGRAPH_CONFIG = "admin/plugins/extend/modules/opengraph/modify_opengraph_config";

    private static final String CONSTANT_QUESTION_MARK = "?";

    // SERVICES
    @Inject
    private IResourceExtenderHistoryService _resourceHistoryService;
    @Inject
    IResourceExtenderService resourceExtenderService;
    @Inject
    @Named( "extend-opengraph.opengraphExtenderConfigService" )
    private IResourceExtenderConfigService _configService;
    private OpengraphService _opengraphService = SpringContextService.getBean( OpengraphService.BEAN_NAME );

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
            Map<String, Object> model = new HashMap<String, Object>( );

            String strUrl = AppPathService.getBaseUrl( request );
            model.put( MARK_BASE_URL, strUrl );
            StringBuffer sbUrl = request.getRequestURL( );
            if ( sbUrl != null )
            {
                strUrl = sbUrl.toString( );
                String strQuery = request.getQueryString( );
                if ( StringUtils.isNotBlank( strQuery ) )
                {
                    sbUrl.append( CONSTANT_QUESTION_MARK );
                    sbUrl.append( strQuery );
                }
            }
            model.put( MARK_URL, sbUrl );

            String strResourceName = resourceExtenderService.getExtendableResourceName( strIdExtendableResource,
                    strExtendableResourceType );
            model.put( MARK_TITLE, strResourceName );
            String strResourceDescription = resourceExtenderService.getExtendableResourceDescription(
                    strIdExtendableResource, strExtendableResourceType );
            model.put( MARK_DESCRIPTION, strResourceDescription );

            HtmlTemplate template = AppTemplateService
                    .getTemplate( TEMPLATE_SOCIAL_HEADER, request.getLocale( ), model );
            return template.getHtml( );
        }
        else
        {

        }
        return StringUtils.EMPTY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfigHtml( ResourceExtenderDTO resourceExtender, Locale locale, HttpServletRequest request )
    {
        List<OpengraphSocialHub> listOpengraphSocialHub = _opengraphService.findAll( );

        if ( listOpengraphSocialHub == null || listOpengraphSocialHub.size( ) <= 0 )
        {
            return null;
        }
        Map<String, Object> model = new HashMap<String, Object>( );


        return null;
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
        // TODO Auto-generated method stub

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
     * @param strParameters the str parameters
     * @return the string
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

}
