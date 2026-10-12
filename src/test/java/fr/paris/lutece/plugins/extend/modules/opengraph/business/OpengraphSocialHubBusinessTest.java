/*
 * Copyright (c) 2002-2026, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.extend.modules.opengraph.business;

import fr.paris.lutece.plugins.extend.modules.opengraph.service.OpengraphPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.test.LuteceTestCase;

import org.junit.jupiter.api.Test;

/**
 * Tests the persistence of the social hubs.
 */
public class OpengraphSocialHubBusinessTest extends LuteceTestCase
{
    /**
     * Creates, reads, updates and removes a social hub, the database giving its id.
     */
    @Test
    public void testBusiness( )
    {
        Plugin plugin = OpengraphPlugin.getPlugin( );
        OpengraphSocialHub socialHub = new OpengraphSocialHub( );
        socialHub.setName( "Hub" );
        socialHub.setContentHeader( "<meta name=\"a\">" );
        socialHub.setContentBody( "<div>body</div>" );
        socialHub.setContentFooter( "" );

        OpengraphSocialHubHome.insert( socialHub, plugin );
        int nId = socialHub.getOpengraphSocialHubId( );
        assertTrue( nId > 0, "generated id" );

        OpengraphSocialHub stored = OpengraphSocialHubHome.findById( nId, plugin );
        assertEquals( "Hub", stored.getName( ), "name" );
        assertEquals( "<div>body</div>", stored.getContentBody( ), "body" );

        OpengraphSocialHub other = new OpengraphSocialHub( );
        other.setName( "Other" );
        other.setContentBody( "x" );
        OpengraphSocialHubHome.insert( other, plugin );
        assertTrue( other.getOpengraphSocialHubId( ) != nId, "distinct ids" );

        socialHub.setName( "Hub 2" );
        OpengraphSocialHubHome.update( socialHub, plugin );
        assertEquals( "Hub 2", OpengraphSocialHubHome.findById( nId, plugin ).getName( ), "updated name" );
        assertTrue( OpengraphSocialHubHome.findAll( plugin ).stream( ).anyMatch( s -> s.getOpengraphSocialHubId( ) == nId ), "listed" );

        OpengraphSocialHubHome.delete( nId, plugin );
        OpengraphSocialHubHome.delete( other.getOpengraphSocialHubId( ), plugin );
        assertNull( OpengraphSocialHubHome.findById( nId, plugin ), "removed" );
    }
}
