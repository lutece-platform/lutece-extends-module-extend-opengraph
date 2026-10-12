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
package fr.paris.lutece.plugins.extend.modules.opengraph.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO to manage OpengraphSocialHub objects
 */
@ApplicationScoped
public class OpengraphSocialHubDAO implements IOpengraphSocialHubDAO
{
    private static final String SQL_QUERY_SELECT = " SELECT opengraph_socialhub_id, name, content_header, content_body, content_footer FROM extend_opengraph_socialhub WHERE opengraph_socialhub_id = ? ";
    private static final String SQL_QUERY_INSERT = " INSERT INTO extend_opengraph_socialhub ( name, content_header, content_body, content_footer ) VALUES( ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_UPDATE = " UPDATE extend_opengraph_socialhub SET name = ?, content_header = ?, content_body = ?, content_footer = ? WHERE opengraph_socialhub_id = ? ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM extend_opengraph_socialhub WHERE opengraph_socialhub_id = ? ";
    private static final String SQL_QUERY_SELECTALL = " SELECT opengraph_socialhub_id, name, content_header, content_body, content_footer FROM extend_opengraph_socialhub ";

    /**
     * {@inheritDoc}
     */
    @Override
    public OpengraphSocialHub findById( int nId, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin ) )
        {
            daoUtil.setInt( 1, nId );
            daoUtil.executeQuery( );

            if ( daoUtil.next( ) )
            {
                return load( daoUtil );
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert( OpengraphSocialHub opengraphSocialHub, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS, plugin ) )
        {
            int nIndex = 1;
            daoUtil.setString( nIndex++, opengraphSocialHub.getName( ) );
            daoUtil.setString( nIndex++, opengraphSocialHub.getContentHeader( ) );
            daoUtil.setString( nIndex++, opengraphSocialHub.getContentBody( ) );
            daoUtil.setString( nIndex, opengraphSocialHub.getContentFooter( ) );
            daoUtil.executeUpdate( );

            if ( daoUtil.nextGeneratedKey( ) )
            {
                opengraphSocialHub.setOpengraphSocialHubId( daoUtil.getGeneratedKeyInt( 1 ) );
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update( OpengraphSocialHub opengraphSocialHub, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin ) )
        {
            int nIndex = 1;
            daoUtil.setString( nIndex++, opengraphSocialHub.getName( ) );
            daoUtil.setString( nIndex++, opengraphSocialHub.getContentHeader( ) );
            daoUtil.setString( nIndex++, opengraphSocialHub.getContentBody( ) );
            daoUtil.setString( nIndex++, opengraphSocialHub.getContentFooter( ) );
            daoUtil.setInt( nIndex, opengraphSocialHub.getOpengraphSocialHubId( ) );
            daoUtil.executeUpdate( );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nId, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin ) )
        {
            daoUtil.setInt( 1, nId );
            daoUtil.executeUpdate( );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OpengraphSocialHub> findAll( Plugin plugin )
    {
        List<OpengraphSocialHub> listOpengraphSocialHub = new ArrayList<>( );

        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin ) )
        {
            daoUtil.executeQuery( );

            while ( daoUtil.next( ) )
            {
                listOpengraphSocialHub.add( load( daoUtil ) );
            }
        }

        return listOpengraphSocialHub;
    }

    /**
     * Builds a social hub from the current row.
     *
     * @param daoUtil
     *            the DAOUtil positioned on a row
     * @return the social hub
     */
    private OpengraphSocialHub load( DAOUtil daoUtil )
    {
        int nIndex = 1;
        OpengraphSocialHub opengraphSocialHub = new OpengraphSocialHub( );
        opengraphSocialHub.setOpengraphSocialHubId( daoUtil.getInt( nIndex++ ) );
        opengraphSocialHub.setName( daoUtil.getString( nIndex++ ) );
        opengraphSocialHub.setContentHeader( daoUtil.getString( nIndex++ ) );
        opengraphSocialHub.setContentBody( daoUtil.getString( nIndex++ ) );
        opengraphSocialHub.setContentFooter( daoUtil.getString( nIndex ) );

        return opengraphSocialHub;
    }
}
