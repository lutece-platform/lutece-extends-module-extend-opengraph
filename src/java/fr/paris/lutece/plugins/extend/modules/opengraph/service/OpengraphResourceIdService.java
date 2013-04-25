package fr.paris.lutece.plugins.extend.modules.opengraph.service;

import fr.paris.lutece.plugins.extend.modules.opengraph.business.OpengraphSocialHub;
import fr.paris.lutece.portal.service.rbac.Permission;
import fr.paris.lutece.portal.service.rbac.ResourceIdService;
import fr.paris.lutece.portal.service.rbac.ResourceType;
import fr.paris.lutece.portal.service.rbac.ResourceTypeManager;
import fr.paris.lutece.util.ReferenceList;

import java.util.Locale;


/**
 * OpengraphResourceIdService
 */
public class OpengraphResourceIdService extends ResourceIdService
{
    /**
     * Permission to add social hubs
     */

    public static final String PERMISSION_ADD_SOCIALHUB = "ADD_SOCIALHUB";
    /**
     * Permission to modify social hubs
     */
    public static final String PERMISSION_MODIFY_SOCIALHUB = "MODIFY_SOCIALHUB";

    /**
     * Permission to remove social hubs
     */
    public static final String PERMISSION_REMOVE_SOCIALHUB = "REMOVE_SOCIALHUB";

    private static final String PROPERTY_LABEL_ADD_SOCIALHUB = "module.extend.opengraph.permission.label.addSocialHub";
    private static final String PROPERTY_LABEL_MODIFY_SOCIALHUB = "module.extend.opengraph.permission.label.modifySocialHub";
    private static final String PROPERTY_LABEL_REMOVE_SOCIALHUB = "module.extend.opengraph.permission.label.removeSocialHub";

    private static final String PROPERTY_LABEL_RESOURCE_TYPE = "module.extend.opengraph.resourceType.label";

    /**
     * Instantiates a new resource type resource id service.
     */
    public OpengraphResourceIdService( )
    {
        setPluginName( OpengraphPlugin.PLUGIN_NAME );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register( )
    {
        ResourceType rt = new ResourceType( );
        rt.setResourceIdServiceClass( OpengraphResourceIdService.class.getName( ) );
        rt.setPluginName( OpengraphPlugin.PLUGIN_NAME );
        rt.setResourceTypeKey( OpengraphSocialHub.RESOURCE_TYPE );
        rt.setResourceTypeLabelKey( PROPERTY_LABEL_RESOURCE_TYPE );

        Permission p = new Permission( );
        p.setPermissionKey( PERMISSION_ADD_SOCIALHUB );
        p.setPermissionTitleKey( PROPERTY_LABEL_ADD_SOCIALHUB );
        rt.registerPermission( p );

        p = new Permission( );
        p.setPermissionKey( PERMISSION_MODIFY_SOCIALHUB );
        p.setPermissionTitleKey( PROPERTY_LABEL_MODIFY_SOCIALHUB );
        rt.registerPermission( p );

        p = new Permission( );
        p.setPermissionKey( PERMISSION_REMOVE_SOCIALHUB );
        p.setPermissionTitleKey( PROPERTY_LABEL_REMOVE_SOCIALHUB );
        rt.registerPermission( p );

        ResourceTypeManager.registerResourceType( rt );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferenceList getResourceIdList( Locale locale )
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle( String strId, Locale locale )
    {
        return null;
    }
}
