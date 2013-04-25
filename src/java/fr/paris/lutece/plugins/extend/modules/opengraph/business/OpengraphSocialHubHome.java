package fr.paris.lutece.plugins.extend.modules.opengraph.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;


/**
 * Home of OpengraphSocialHubDAO
 */
public final class OpengraphSocialHubHome
{
    private static IOpengraphSocialHubDAO _dao = SpringContextService
            .getBean( "extend-opengraph.opengraphSocialHubDAO" );

    /**
     * Private constructor
     */
    private OpengraphSocialHubHome( )
    {

    }

    /**
     * Find an OpengraphSocialHub by id
     * @param nId Id
     * @param plugin The plugin
     * @return The OpengraphSocialHub with the given Id
     */
    public static OpengraphSocialHub findById( int nId, Plugin plugin )
    {
        return _dao.findById( nId, plugin );
    }

    /**
     * Insert a new OpengraphSocialHub into the database
     * @param opengraphSocialHub The OpengraphSocialHub to save
     * @param plugin The plugin
     */
    public static void insert( OpengraphSocialHub opengraphSocialHub, Plugin plugin )
    {
        _dao.insert( opengraphSocialHub, plugin );
    }

    /**
     * Update an OpengraphSocialHub
     * @param opengraphSocialHub The OpengraphSocialHub tu update
     * @param plugin The plugin
     */
    public static void update( OpengraphSocialHub opengraphSocialHub, Plugin plugin )
    {
        _dao.update( opengraphSocialHub, plugin );
    }

    /**
     * Remove an OpengraphSocialHub from the database
     * @param nId The id of the OpengraphSocialHub to remove
     * @param plugin The plugin
     */
    public static void delete( int nId, Plugin plugin )
    {
        _dao.delete( nId, plugin );
    }

    /**
     * Get every OpengraphSocialHub.
     * @param plugin The plugin
     * @return The list of every OpengraphSocialHub
     */
    public static List<OpengraphSocialHub> findAll( Plugin plugin )
    {
        return _dao.findAll( plugin );
    }
}
