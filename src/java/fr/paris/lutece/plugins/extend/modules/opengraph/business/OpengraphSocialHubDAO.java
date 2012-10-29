package fr.paris.lutece.plugins.extend.modules.opengraph.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


public class OpengraphSocialHubDAO implements IOpengraphSocialHubDAO
{
    private static final String SQL_QUERY_SELECT = " SELECT opengraph_social_id, name, content FROM opengraph_socialhub WHERE opengraph_social_id = ? ";
    private static final String SQL_INSERT = " INSERT INTO opengraph_socialhub ( opengraph_socialhub_id, name, content ) VALUES( ?, ?, ? ) ";
    private static final String SQL_UPDATE = " UPDATE opengraph_socialhub SET name = ?, content = ? WHERE opengraph_social_id = ? ";
    private static final String SQL_DELETE = " DELETE FROM opengraph_socialhub WHERE opengraph_socialhub_id = ? ";
    private static final String SQL_QUERY_FIND_ALL = " SELECT opengraph_social_id, name, content FROM opengraph_socialhub ";

    private static final String SQL_QUERY_NEW_PRIMARY_KEY = " SELECT MAX(opengraph_social_id) FROM opengraph_social ";

    private int getNewPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PRIMARY_KEY, plugin );
        int nId = 1;
        daoUtil.executeQuery( );
        if ( daoUtil.next( ) )
        {
            nId = daoUtil.getInt( 1 );
            nId++;
        }
        daoUtil.free( );
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

        daoUtil.executeQuery( );
        if ( daoUtil.next( ) )
        {
            opengraphSocialHub = new OpengraphSocialHub( );
            opengraphSocialHub.setOpengraphSocialHubId( daoUtil.getInt( 1 ) );
            opengraphSocialHub.setName( daoUtil.getString( 2 ) );
            opengraphSocialHub.setContent( daoUtil.getString( 3 ) );
        }
        daoUtil.free( );
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
        daoUtil.setString( 2, opengraphSocialHub.getName( ) );
        daoUtil.setString( 3, opengraphSocialHub.getContent( ) );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update( OpengraphSocialHub opengraphSocialHub, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_UPDATE, plugin );
        daoUtil.setString( 1, opengraphSocialHub.getName( ) );
        daoUtil.setString( 2, opengraphSocialHub.getContent( ) );
        daoUtil.setInt( 3, opengraphSocialHub.getOpengraphSocialHubId( ) );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nId, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_DELETE, plugin );
        daoUtil.setInt( 1, nId );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OpengraphSocialHub> findAll( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_FIND_ALL, plugin );
        
        List<OpengraphSocialHub> listOpengraphSocialHub = new ArrayList<OpengraphSocialHub>( );
        OpengraphSocialHub opengraphSocialHub = null;

        daoUtil.executeQuery( );
        while ( daoUtil.next( ) )
        {
            opengraphSocialHub = new OpengraphSocialHub( );
            opengraphSocialHub.setOpengraphSocialHubId( daoUtil.getInt( 1 ) );
            opengraphSocialHub.setName( daoUtil.getString( 2 ) );
            opengraphSocialHub.setContent( daoUtil.getString( 3 ) );
            listOpengraphSocialHub.add( opengraphSocialHub );
        }
        daoUtil.free( );
        return listOpengraphSocialHub;
    }

}
