package fr.paris.lutece.plugins.extend.modules.opengraph.business.config;

import fr.paris.lutece.plugins.extend.business.extender.config.ExtenderConfig;

import java.util.ArrayList;
import java.util.List;


/**
 * Opengraph extender configuration
 */
public class OpengraphExtenderConfig extends ExtenderConfig
{
    List<Integer> _listOpengraphSocialHubId = new ArrayList<Integer>( );
    List<Integer> _listAddedOpengraphSocialHubId = new ArrayList<Integer>( );
    List<Integer> _listRemovedOpengraphSocialHubId = new ArrayList<Integer>( );

    /**
     * Get the list of id OpengraphSocialHub associated to this configuration
     * @return The list of id OpengraphSocialHub associated to this
     *         configuration
     */
    public List<Integer> getListOpengraphSocialHubId( )
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
    public List<Integer> getAndResetListAddedOpengraphSocialHubId( )
    {
        List<Integer> listAdded = _listAddedOpengraphSocialHubId;
        _listAddedOpengraphSocialHubId = new ArrayList<Integer>( );
        return listAdded;
    }

    /**
     * Get the list of removed OpengraphSocialHub ids, and reset it.
     * @return The list of removed OpengraphSocialHub ids.
     */
    public List<Integer> getAndResetListRemovedOpengraphSocialHubId( )
    {
        List<Integer> listRemoved = _listRemovedOpengraphSocialHubId;
        _listRemovedOpengraphSocialHubId = new ArrayList<Integer>( );
        return listRemoved;
    }

}
