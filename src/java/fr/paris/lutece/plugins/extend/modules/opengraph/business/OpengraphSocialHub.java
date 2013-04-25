package fr.paris.lutece.plugins.extend.modules.opengraph.business;

/**
 * Class to represent a social hub html template
 */
public class OpengraphSocialHub
{
    /**
     * Opengraph social hub resource type
     */
    public static final String RESOURCE_TYPE = "OPENGRAPH_SOCIAL_HUB";

    private int _nOpengraphSocialHubId;
    private String _strName;
    private String _strContentHeader;
    private String _strContentBody;
    private String _strContentFooter;

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
     * Get the html header content of the OpengraphSocialHub
     * @return The html header content of the OpengraphSocialHub
     */
    public String getContentHeader( )
    {
        return _strContentHeader;
    }

    /**
     * Set the html header content of the OpengraphSocialHub
     * @param strContentHeader The html header content of the OpengraphSocialHub
     */
    public void setContentHeader( String strContentHeader )
    {
        _strContentHeader = strContentHeader;
    }

    /**
     * Get the html body content of the OpengraphSocialHub
     * @return The html body content of the OpengraphSocialHub
     */
    public String getContentBody( )
    {
        return _strContentBody;
    }

    /**
     * Set the html body content of the OpengraphSocialHub
     * @param strContentBody The html body content of the OpengraphSocialHub
     */
    public void setContentBody( String strContentBody )
    {
        _strContentBody = strContentBody;
    }

    /**
     * Get the html footer content of the OpengraphSocialHub
     * @return The html footer content of the OpengraphSocialHub
     */
    public String getContentFooter( )
    {
        return _strContentFooter;
    }

    /**
     * Set the html footer content of the OpengraphSocialHub
     * @param strContentFooter The html footer content of the OpengraphSocialHub
     */
    public void setContentFooter( String strContentFooter )
    {
        _strContentFooter = strContentFooter;
    }
}
