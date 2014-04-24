/*
 * Copyright (c) 2002-2014, Mairie de Paris
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
package fr.paris.lutece.plugins.extend.modules.opengraph.business;

import fr.paris.lutece.portal.service.plugin.Plugin;

import java.util.List;


/**
 * Interface of OpengraphSocialHubDAO
 */
public interface IOpengraphSocialHubDAO
{
    /**
     * Find an OpengraphSocialHub by id
     * @param nId Id
     * @param plugin The plugin
     * @return The OpengraphSocialHub with the given Id
     */
    OpengraphSocialHub findById( int nId, Plugin plugin );

    /**
     * Insert a new OpengraphSocialHub into the database
     * @param opengraphSocialHub The OpengraphSocialHub to save
     * @param plugin The plugin
     */
    void insert( OpengraphSocialHub opengraphSocialHub, Plugin plugin );

    /**
     * Update an OpengraphSocialHub
     * @param opengraphSocialHub The OpengraphSocialHub tu update
     * @param plugin The plugin
     */
    void update( OpengraphSocialHub opengraphSocialHub, Plugin plugin );

    /**
     * Remove an OpengraphSocialHub from the database
     * @param nId The id of the OpengraphSocialHub to remove
     * @param plugin The plugin
     */
    void delete( int nId, Plugin plugin );

    /**
     * Get every OpengraphSocialHub.
     * @param plugin The plugin
     * @return The list of every OpengraphSocialHub
     */
    List<OpengraphSocialHub> findAll( Plugin plugin );
}
