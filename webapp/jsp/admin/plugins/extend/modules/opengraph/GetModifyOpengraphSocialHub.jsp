<%@page import="fr.paris.lutece.portal.web.pluginaction.IPluginActionResult"%>

<jsp:useBean id="opengraphJspBean" scope="session" class="fr.paris.lutece.plugins.extend.modules.opengraph.web.OpengraphJspBean" />

<% 
opengraphJspBean.init( request, opengraphJspBean.MANAGE_OPENGRAPH_SOCIALHUB );
	IPluginActionResult result = opengraphJspBean.getModifyOpengraphSocialHub( request );
	if ( result.getRedirect(  ) != null )
	{
		response.sendRedirect( result.getRedirect(  ) );
	}
	else if ( result.getHtmlContent(  ) != null )
	{
%>
		<%@ page errorPage="../../../../ErrorPage.jsp" %>
		<jsp:include page="../../../../AdminHeader.jsp" />

		<%= result.getHtmlContent(  ) %>

		<%@ include file="../../../../AdminFooter.jsp" %>
<%
	}
%>
