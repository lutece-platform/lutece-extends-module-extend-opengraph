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
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * DAO to manage OpengraphSocialHub objects
 */
public class OpengraphSocialHubDAO implements IOpengraphSocialHubDAO
{
    private static final String SQL_QUERY_SELECT = " SELECT opengraph_socialhub_id, name, content_header, content_body, content_footer FROM extend_opengraph_socialhub WHERE opengraph_socialhub_id = ? ";
    private static final String SQL_INSERT = " INSERT INTO extend_opengraph_socialhub ( opengraph_socialhub_id, name, content_header, content_body, content_footer ) VALUES( ?, ?, ?, ?, ? ) ";
    private static final String SQL_UPDATE = " UPDATE extend_opengraph_socialhub SET name = ?, content_header = ?, content_body = ?, content_footer = ? WHERE opengraph_socialhub_id = ? ";
    private static final String SQL_DELETE = " DELETE FROM extend_opengraph_socialhub WHERE opengraph_socialhub_id = ? ";
    private static final String SQL_QUERY_FIND_ALL = " SELECT opengraph_socialhub_id, name, content_header, content_body, content_footer FROM extend_opengraph_socialhub ";
    private static final String SQL_QUERY_NEW_PRIMARY_KEY = " SELECT MAX(opengraph_socialhub_id) FROM extend_opengraph_socialhub ";

    /**
     * Get a new primary key
     * @param plugin The plugin
     * @return The new primary key
     */
    private int getNewPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PRIMARY_KEY, plugin );
        int nId = 1;
        daoUtil.executeQuery(  );

        if ( daoUtil.next(  ) )
        {
            nId = daoUtil.getInt( 1 );
            nId++;
        }

        daoUtil.free(  );

        return nId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpengraphSocialHub findById( int nId, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nId );

        OpengraphSocialHub opengraphSocialHub = null;

        daoUtil.executeQuery(  );

        if ( daoUtil.next(  ) )
        {
            opengraphSocialHub = new OpengraphSocialHub(  );
            opengraphSocialHub.setOpengraphSocialHubId( daoUtil.getInt( 1 ) );
            opengraphSocialHub.setName( daoUtil.getString( 2 ) );
            opengraphSocialHub.setContentHeader( daoUtil.getString( 3 ) );
            opengraphSocialHub.setContentBody( daoUtil.getString( 4 ) );
            opengraphSocialHub.setContentFooter( daoUtil.getString( 5 ) );
        }

        daoUtil.free(  );

        return opengraphSocialHub;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert( OpengraphSocialHub opengraphSocialHub, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_INSERT, plugin );
        daoUtil.setInt( 1, getNewPrimaryKey( plugin ) );
        daoUtil.setString( 2, opengraphSocialHub.getName(  ) );
        daoUtil.setString( 3, opengraphSocialHub.getContentHeader(  ) );
        daoUtil.setString( 4, opengraphSocialHub.getContentBody(  ) );
        daoUtil.setString( 5, opengraphSocialHub.getContentFooter(  ) );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update( OpengraphSocialHub opengraphSocialHub, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_UPDATE, plugin );
        daoUtil.setString( 1, opengraphSocialHub.getName(  ) );
        daoUtil.setString( 2, opengraphSocialHub.getContentHeader(  ) );
        daoUtil.setString( 3, opengraphSocialHub.getContentBody(  ) );
        daoUtil.setString( 4, opengraphSocialHub.getContentFooter(  ) );
        daoUtil.setInt( 5, opengraphSocialHub.getOpengraphSocialHubId(  ) );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nId, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_DELETE, plugin );
        daoUtil.setInt( 1, nId );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OpengraphSocialHub> findAll( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_ALL, plugin );

        List<OpengraphSocialHub> listOpengraphSocialHub = new ArrayList<OpengraphSocialHub>(  );
        OpengraphSocialHub opengraphSocialHub = null;

        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            opengraphSocialHub = new OpengraphSocialHub(  );
            opengraphSocialHub.setOpengraphSocialHubId( daoUtil.getInt( 1 ) );
            opengraphSocialHub.setName( daoUtil.getString( 2 ) );
            opengraphSocialHub.setContentHeader( daoUtil.getString( 3 ) );
            opengraphSocialHub.setContentBody( daoUtil.getString( 4 ) );
            opengraphSocialHub.setContentFooter( daoUtil.getString( 5 ) );
            listOpengraphSocialHub.add( opengraphSocialHub );
        }

        daoUtil.free(  );

        return listOpengraphSocialHub;
    }
}
