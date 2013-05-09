/*
 * Copyright (c) 2002-2012, Mairie de Paris
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
    public int getOpengraphSocialHubId(  )
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
    public String getName(  )
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
    public String getContentHeader(  )
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
    public String getContentBody(  )
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
    public String getContentFooter(  )
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
