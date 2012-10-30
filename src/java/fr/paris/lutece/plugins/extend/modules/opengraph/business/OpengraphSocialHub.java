package fr.paris.lutece.plugins.extend.modules.opengraph.business;

/**
 * Class to represent a social hub html template
 */
public class OpengraphSocialHub
{
    public static final String RESOURCE_TYPE = "OPENGRAPH_SOCIAL_HUB";

    private int _nOpengraphSocialHubId;
    private String _strName;
    private String _strContent;

    /**
     * Get the id of the OpengraphSocialHub
     * @return The id of the OpengraphSocialHub
     */
    public int getOpengraphSocialHubId( )
    {
        return _nOpengraphSocialHubId;
    }

    /**
     * Set the id of the OpengraphSocialHub
     * @param nOpengraphSocialHubId The id of the OpengraphSocialHub
     */
    public void setOpengraphSocialHubId( int nOpengraphSocialHubId )
    {
        _nOpengraphSocialHubId = nOpengraphSocialHubId;
    }

    /**
     * Get the name of the OpengraphSocialHub
     * @return The name of the OpengraphSocialHub
     */
    public String getName( )
    {
        return _strName;
    }

    /**
     * Set the name of the OpengraphSocialHub
     * @param strName The name of the OpengraphSocialHub
     */
    public void setName( String strName )
    {
        _strName = strName;
    }

    /**
     * Get the html content of the OpengraphSocialHub
     * @return The html content of the OpengraphSocialHub
     */
    public String getContent( )
    {
        return _strContent;
    }

    /**
     * Set the html content of the OpengraphSocialHub
     * @param strContent The html content of the OpengraphSocialHub
     */
    public void setContent( String strContent )
    {
        _strContent = strContent;
    }
}
