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
package fr.paris.lutece.plugins.extend.modules.opengraph.service;

import fr.paris.lutece.plugins.extend.modules.opengraph.business.OpengraphSocialHub;
import fr.paris.lutece.portal.service.rbac.Permission;
import fr.paris.lutece.portal.service.rbac.ResourceIdService;
import fr.paris.lutece.portal.service.rbac.ResourceType;
import fr.paris.lutece.portal.service.rbac.ResourceTypeManager;
import fr.paris.lutece.util.ReferenceList;

import java.util.Locale;


/**
 * OpengraphResourceIdService
 */
public class OpengraphResourceIdService extends ResourceIdService
{
    /**
     * Permission to add social hubs
     */
    public static final String PERMISSION_ADD_SOCIALHUB = "ADD_SOCIALHUB";

    /**
     * Permission to modify social hubs
     */
    public static final String PERMISSION_MODIFY_SOCIALHUB = "MODIFY_SOCIALHUB";

    /**
     * Permission to remove social hubs
     */
    public static final String PERMISSION_REMOVE_SOCIALHUB = "REMOVE_SOCIALHUB";
    private static final String PROPERTY_LABEL_ADD_SOCIALHUB = "module.extend.opengraph.permission.label.addSocialHub";
    private static final String PROPERTY_LABEL_MODIFY_SOCIALHUB = "module.extend.opengraph.permission.label.modifySocialHub";
    private static final String PROPERTY_LABEL_REMOVE_SOCIALHUB = "module.extend.opengraph.permission.label.removeSocialHub";
    private static final String PROPERTY_LABEL_RESOURCE_TYPE = "module.extend.opengraph.resourceType.label";

    /**
     * Instantiates a new resource type resource id service.
     */
    public OpengraphResourceIdService(  )
    {
        setPluginName( OpengraphPlugin.PLUGIN_NAME );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(  )
    {
        ResourceType rt = new ResourceType(  );
        rt.setResourceIdServiceClass( OpengraphResourceIdService.class.getName(  ) );
        rt.setPluginName( OpengraphPlugin.PLUGIN_NAME );
        rt.setResourceTypeKey( OpengraphSocialHub.RESOURCE_TYPE );
        rt.setResourceTypeLabelKey( PROPERTY_LABEL_RESOURCE_TYPE );

        Permission p = new Permission(  );
        p.setPermissionKey( PERMISSION_ADD_SOCIALHUB );
        p.setPermissionTitleKey( PROPERTY_LABEL_ADD_SOCIALHUB );
        rt.registerPermission( p );

        p = new Permission(  );
        p.setPermissionKey( PERMISSION_MODIFY_SOCIALHUB );
        p.setPermissionTitleKey( PROPERTY_LABEL_MODIFY_SOCIALHUB );
        rt.registerPermission( p );

        p = new Permission(  );
        p.setPermissionKey( PERMISSION_REMOVE_SOCIALHUB );
        p.setPermissionTitleKey( PROPERTY_LABEL_REMOVE_SOCIALHUB );
        rt.registerPermission( p );

        ResourceTypeManager.registerResourceType( rt );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferenceList getResourceIdList( Locale locale )
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle( String strId, Locale locale )
    {
        return null;
    }
}
