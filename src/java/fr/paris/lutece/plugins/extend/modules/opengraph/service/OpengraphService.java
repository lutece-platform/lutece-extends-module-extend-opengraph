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
     * Default constructor
     */
    public OpengraphService( )
    {
        _plugin = PluginService.getPlugin( OpengraphPlugin.PLUGIN_NAME );
    }

    /**
     * Get the list of every OpengraphSocialHub
     * @return The list of every OpengraphSocialHub
     */
    public List<OpengraphSocialHub> findAll( )
    {
        return OpengraphSocialHubHome.findAll( _plugin );
    }

    /**
     * Create an OpengraphSocialHub
     * @param opengraphSocialHub The OpengraphSocialHub to create
     */
    public void createOpengraphSocialHub( OpengraphSocialHub opengraphSocialHub )
    {
        OpengraphSocialHubHome.insert( opengraphSocialHub, _plugin );
    }

    /**
     * Get an OpengraphSocialHub from the database
     * @param nIdOpengraphSocialHub The id of the OpengraphSocialHub to find
     * @return The OpengraphSocialHub with the given id, or null if it could not
     *         be found.
     */
    public OpengraphSocialHub getOpengraphSocialHub( int nIdOpengraphSocialHub )
    {
        return OpengraphSocialHubHome.findById( nIdOpengraphSocialHub, _plugin );
    }

    /**
     * Update an OpengraphSocialHub
     * @param opengraphSocialHub The OpengraphSocialHub to update
     */
    public void updateOpengraphSocialHub( OpengraphSocialHub opengraphSocialHub )
    {
        OpengraphSocialHubHome.update( opengraphSocialHub, _plugin );
    }

    /**
     * Remove an OpengraphSocialHub
     * @param nIdOpengraphSocialHub The id of the OpengraphSocialHub to remove
     */
    public void removeOpengraphSocialHub( int nIdOpengraphSocialHub )
    {
        OpengraphSocialHubHome.delete( nIdOpengraphSocialHub, _plugin );
    }
}
