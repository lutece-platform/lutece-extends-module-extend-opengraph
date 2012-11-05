package fr.paris.lutece.plugins.extend.modules.opengraph.service;

import fr.paris.lutece.plugins.extend.modules.opengraph.business.OpengraphSocialHub;
import fr.paris.lutece.plugins.extend.modules.opengraph.business.OpengraphSocialHubHome;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;

import java.util.List;


/**
 * OpengraphService
 */
public class OpengraphService
{
    public static final String BEAN_NAME = "extend-opengraph.opengraphService";

    private Plugin _plugin;

    /**
     * Get the list of every OpengraphSocialHub
     * @return The list of every OpengraphSocialHub
     */
    public List<OpengraphSocialHub> findAll( )
    {
        return OpengraphSocialHubHome.findAll( getPlugin( ) );
    }

    /**
     * Create an OpengraphSocialHub
     * @param opengraphSocialHub The OpengraphSocialHub to create
     */
    public void createOpengraphSocialHub( OpengraphSocialHub opengraphSocialHub )
    {
        OpengraphSocialHubHome.insert( opengraphSocialHub, getPlugin( ) );
    }

    /**
     * Get an OpengraphSocialHub from the database
     * @param nIdOpengraphSocialHub The id of the OpengraphSocialHub to find
     * @return The OpengraphSocialHub with the given id, or null if it could not
     *         be found.
     */
    public OpengraphSocialHub getOpengraphSocialHub( int nIdOpengraphSocialHub )
    {
        return OpengraphSocialHubHome.findById( nIdOpengraphSocialHub, getPlugin( ) );
    }

    /**
     * Update an OpengraphSocialHub
     * @param opengraphSocialHub The OpengraphSocialHub to update
     */
    public void updateOpengraphSocialHub( OpengraphSocialHub opengraphSocialHub )
    {
        OpengraphSocialHubHome.update( opengraphSocialHub, getPlugin( ) );
    }

    /**
     * Remove an OpengraphSocialHub
     * @param nIdOpengraphSocialHub The id of the OpengraphSocialHub to remove
     */
    public void removeOpengraphSocialHub( int nIdOpengraphSocialHub )
    {
        OpengraphSocialHubHome.delete( nIdOpengraphSocialHub, getPlugin( ) );
    }

    /**
     * Get the plugin associated to this service
     * @return The plugin associated to this service
     */
    private Plugin getPlugin( )
    {
        if ( _plugin == null )
        {
            _plugin = PluginService.getPlugin( OpengraphPlugin.PLUGIN_NAME );
        }
        return _plugin;
    }
}
