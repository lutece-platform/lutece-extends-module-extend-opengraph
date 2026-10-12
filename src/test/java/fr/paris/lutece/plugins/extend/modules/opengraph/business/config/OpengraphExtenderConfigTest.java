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
package fr.paris.lutece.plugins.extend.modules.opengraph.business.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Tests the change tracking of the opengraph extender configuration.
 */
public class OpengraphExtenderConfigTest
{
    /**
     * An added then a removed social hub are reported once, then the tracking is reset.
     */
    @Test
    public void testAddAndRemoveTracking( )
    {
        OpengraphExtenderConfig config = new OpengraphExtenderConfig( );
        config.addOpengraphSocialHubId( 1 );
        config.addOpengraphSocialHubId( 2 );
        config.removeOpengraphSocialHubId( 1 );

        assertEquals( List.of( 2 ), config.getListOpengraphSocialHubId( ), "current ids" );
        assertEquals( List.of( 1, 2 ), config.getAndResetListAddedOpengraphSocialHubId( ), "added ids" );
        assertEquals( List.of( 1 ), config.getAndResetListRemovedOpengraphSocialHubId( ), "removed ids" );
        assertTrue( config.getAndResetListAddedOpengraphSocialHubId( ).isEmpty( ), "added ids reset" );
        assertTrue( config.getAndResetListRemovedOpengraphSocialHubId( ).isEmpty( ), "removed ids reset" );
    }
}
