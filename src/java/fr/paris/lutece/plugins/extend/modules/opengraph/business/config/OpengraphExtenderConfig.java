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
package fr.paris.lutece.plugins.extend.modules.opengraph.business.config;

import fr.paris.lutece.plugins.extend.business.extender.config.ExtenderConfig;

import java.util.ArrayList;
import java.util.List;


/**
 * Opengraph extender configuration
 */
public class OpengraphExtenderConfig extends ExtenderConfig
{
    private List<Integer> _listOpengraphSocialHubId = new ArrayList<Integer>(  );
    private List<Integer> _listAddedOpengraphSocialHubId = new ArrayList<Integer>(  );
    private List<Integer> _listRemovedOpengraphSocialHubId = new ArrayList<Integer>(  );

    /**
     * Get the list of id OpengraphSocialHub associated to this configuration
     * @return The list of id OpengraphSocialHub associated to this
     *         configuration
     */
    public List<Integer> getListOpengraphSocialHubId(  )
    {
        return new ArrayList<Integer>( _listOpengraphSocialHubId );
    }

    /**
     * Add an OpengraphSocialHub id to the list of OpengraphSocialHub id
     * associated to this config
     * @param nOpengraphSocialHubId The id to add
     */
    public void addOpengraphSocialHubId( Integer nOpengraphSocialHubId )
    {
        _listAddedOpengraphSocialHubId.add( nOpengraphSocialHubId );
        _listOpengraphSocialHubId.add( nOpengraphSocialHubId );
    }

    /**
     * Remove an OpengraphSocialHub id from the list of OpengraphSocialHub id
     * associated to this config
     * @param nOpengraphSocialHubId The id to remove
     */
    public void removeOpengraphSocialHubId( Integer nOpengraphSocialHubId )
    {
        _listRemovedOpengraphSocialHubId.add( nOpengraphSocialHubId );
        _listOpengraphSocialHubId.remove( nOpengraphSocialHubId );
    }

    /**
     * Get the list of id OpengraphSocialHub associated to this configuration
     * @param listOpengraphSocialHubId The list of id OpengraphSocialHub
     *            associated to this configuration
     */
    public void setListOpengraphSocialHubId( List<Integer> listOpengraphSocialHubId )
    {
        _listOpengraphSocialHubId = listOpengraphSocialHubId;
    }

    /**
     * Get the list of added OpengraphSocialHub ids, and reset it.
     * @return The list of added OpengraphSocialHub ids.
     */
    public List<Integer> getAndResetListAddedOpengraphSocialHubId(  )
    {
        List<Integer> listAdded = _listAddedOpengraphSocialHubId;
        _listAddedOpengraphSocialHubId = new ArrayList<Integer>(  );

        return listAdded;
    }

    /**
     * Get the list of removed OpengraphSocialHub ids, and reset it.
     * @return The list of removed OpengraphSocialHub ids.
     */
    public List<Integer> getAndResetListRemovedOpengraphSocialHubId(  )
    {
        List<Integer> listRemoved = _listRemovedOpengraphSocialHubId;
        _listRemovedOpengraphSocialHubId = new ArrayList<Integer>(  );

        return listRemoved;
    }
}
