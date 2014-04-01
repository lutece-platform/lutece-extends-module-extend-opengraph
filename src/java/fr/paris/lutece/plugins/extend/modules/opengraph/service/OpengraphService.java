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
import fr.paris.lutece.plugins.extend.modules.opengraph.business.OpengraphSocialHubHome;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;

import java.io.Serializable;
import java.util.List;


/**
 * OpengraphService
 */
public class OpengraphService implements Serializable
{
    /**
     * Name of the bean of this service.
     */
    public static final String BEAN_NAME = "extend-opengraph.opengraphService";

    private static final long serialVersionUID = -3513538664657153158L;

    private transient Plugin _plugin;

    /**
     * Get the list of every OpengraphSocialHub
     * @return The list of every OpengraphSocialHub
     */
    public List<OpengraphSocialHub> findAll(  )
    {
        return OpengraphSocialHubHome.findAll( getPlugin(  ) );
    }

    /**
     * Create an OpengraphSocialHub
     * @param opengraphSocialHub The OpengraphSocialHub to create
     */
    public void createOpengraphSocialHub( OpengraphSocialHub opengraphSocialHub )
    {
        OpengraphSocialHubHome.insert( opengraphSocialHub, getPlugin(  ) );
    }

    /**
     * Get an OpengraphSocialHub from the database
     * @param nIdOpengraphSocialHub The id of the OpengraphSocialHub to find
     * @return The OpengraphSocialHub with the given id, or null if it could not
     *         be found.
     */
    public OpengraphSocialHub getOpengraphSocialHub( int nIdOpengraphSocialHub )
    {
        return OpengraphSocialHubHome.findById( nIdOpengraphSocialHub, getPlugin(  ) );
    }

    /**
     * Update an OpengraphSocialHub
     * @param opengraphSocialHub The OpengraphSocialHub to update
     */
    public void updateOpengraphSocialHub( OpengraphSocialHub opengraphSocialHub )
    {
        OpengraphSocialHubHome.update( opengraphSocialHub, getPlugin(  ) );
    }

    /**
     * Remove an OpengraphSocialHub
     * @param nIdOpengraphSocialHub The id of the OpengraphSocialHub to remove
     */
    public void removeOpengraphSocialHub( int nIdOpengraphSocialHub )
    {
        OpengraphSocialHubHome.delete( nIdOpengraphSocialHub, getPlugin(  ) );
    }

    /**
     * Get the plugin associated to this service
     * @return The plugin associated to this service
     */
    private Plugin getPlugin(  )
    {
        if ( _plugin == null )
        {
            _plugin = PluginService.getPlugin( OpengraphPlugin.PLUGIN_NAME );
        }

        return _plugin;
    }
}
