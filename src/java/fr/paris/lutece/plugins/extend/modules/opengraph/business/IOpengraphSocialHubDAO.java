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
