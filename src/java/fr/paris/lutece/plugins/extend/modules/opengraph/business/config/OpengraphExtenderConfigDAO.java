package fr.paris.lutece.plugins.extend.modules.opengraph.business.config;

import fr.paris.lutece.plugins.extend.business.extender.config.IExtenderConfigDAO;
import fr.paris.lutece.plugins.extend.modules.opengraph.service.OpengraphPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * DAO to manage OpengraphExtenderConfig objects.
 */
public class OpengraphExtenderConfigDAO implements IExtenderConfigDAO<OpengraphExtenderConfig>
{
    private static final String SQL_QUERY_SELECT = " SELECT id_socialhub FROM extend_opengraph_config WHERE id_extender = ? ";
    private static final String SQL_INSERT_SOCIAL_HUB = " INSERT INTO extend_opengraph_config( id_extender, id_socialhub) VALUES ";
    private static final String SQL_REMOVE_SOCIAL_HUB = " DELETE FROM extend_opengraph_config WHERE id_extender = ? AND id_socialhub IN ";

    private static final String SQL_DELETE_CONFIG = " DELETE FROM extend_opengraph_config WHERE id_extender = ? ";

    private static final String SQL_VALUE_SOCIAL_HUB = " (?,?) ";

    private static final String CONSTANT_OPEN_PARENTHESIS = " ( ";
    private static final String CONSTANT_CLOSE_PARENTHESIS = " ) ";
    private static final String CONSTANT_COMMA = ",";
    private static final String CONSTANT_QUESTION_MARK = "?";

    /**
     * Associate a list of social hub to an extender config
     * @param nIdExtender The id of the extender
     * @param listIdSocialHub The list of social hub ids
     * @param plugin The plugin
     */
    private void insertSocialHub( int nIdExtender, List<Integer> listIdSocialHub, Plugin plugin )
    {
        if ( listIdSocialHub == null || listIdSocialHub.size( ) <= 0 )
        {
            return;
        }
        StringBuilder sbSql = new StringBuilder( SQL_INSERT_SOCIAL_HUB );
        for ( int i = 0; i < listIdSocialHub.size( ) - 1; i++ )
        {
            sbSql.append( SQL_VALUE_SOCIAL_HUB );
            sbSql.append( CONSTANT_COMMA );
        }
        sbSql.append( SQL_VALUE_SOCIAL_HUB );

        DAOUtil daoUtil = new DAOUtil( sbSql.toString( ), OpengraphPlugin.getPlugin( ) );
        int nIndex = 0;
        for ( Integer nId : listIdSocialHub )
        {
            daoUtil.setInt( ++nIndex, nIdExtender );
            daoUtil.setInt( ++nIndex, nId );
        }
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * Remove associations between a list of social hub and an extender config
     * @param nIdExtender The id of the extender
     * @param listIdSocialHub The list of social hub ids
     * @param plugin The plugin
     */
    private void removeSocialHub( int nIdExtender, List<Integer> listIdSocialHub, Plugin plugin )
    {
        if ( listIdSocialHub == null || listIdSocialHub.size( ) <= 0 )
        {
            return;
        }
        StringBuilder sbSql = new StringBuilder( SQL_REMOVE_SOCIAL_HUB );
        sbSql.append( CONSTANT_OPEN_PARENTHESIS );
        for ( int i = 0; i < listIdSocialHub.size( ) - 1; i++ )
        {
            sbSql.append( CONSTANT_QUESTION_MARK );
            sbSql.append( CONSTANT_COMMA );
        }
        sbSql.append( CONSTANT_QUESTION_MARK );
        sbSql.append( CONSTANT_CLOSE_PARENTHESIS );

        DAOUtil daoUtil = new DAOUtil( sbSql.toString( ), OpengraphPlugin.getPlugin( ) );
        int nIndex = 0;
        daoUtil.setInt( ++nIndex, nIdExtender );

        for ( Integer nId : listIdSocialHub )
        {
            daoUtil.setInt( ++nIndex, nId );
        }
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert( OpengraphExtenderConfig config )
    {
        insertSocialHub( config.getIdExtender( ), config.getListOpengraphSocialHubId( ), OpengraphPlugin.getPlugin( ) );
        insertSocialHub( config.getIdExtender( ), config.getAndResetListAddedOpengraphSocialHubId( ),
                OpengraphPlugin.getPlugin( ) );
        removeSocialHub( config.getIdExtender( ), config.getAndResetListRemovedOpengraphSocialHubId( ),
                OpengraphPlugin.getPlugin( ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store( OpengraphExtenderConfig config )
    {
        insertSocialHub( config.getIdExtender( ), config.getAndResetListAddedOpengraphSocialHubId( ),
                OpengraphPlugin.getPlugin( ) );
        removeSocialHub( config.getIdExtender( ), config.getAndResetListRemovedOpengraphSocialHubId( ),
                OpengraphPlugin.getPlugin( ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpengraphExtenderConfig load( int nIdExtender )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, OpengraphPlugin.getPlugin( ) );
        daoUtil.setInt( 1, nIdExtender );
        daoUtil.executeQuery( );

        OpengraphExtenderConfig opengraphExtenderConfig = new OpengraphExtenderConfig( );
        opengraphExtenderConfig.setIdExtender( nIdExtender );

        List<Integer> listSocialHubIds = new ArrayList<Integer>( );
        while ( daoUtil.next( ) )
        {
            listSocialHubIds.add( daoUtil.getInt( 1 ) );
        }
        opengraphExtenderConfig.setListOpengraphSocialHubId( listSocialHubIds );
        daoUtil.free( );
        return opengraphExtenderConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nIdExtender )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_DELETE_CONFIG, OpengraphPlugin.getPlugin( ) );
        daoUtil.setInt( 1, nIdExtender );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

}
