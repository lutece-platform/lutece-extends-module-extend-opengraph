<%@ page errorPage="../../../../ErrorPage.jsp" %>
<jsp:useBean id="opengraphJspBean" scope="session" class="fr.paris.lutece.plugins.extend.modules.opengraph.web.OpengraphJspBean" />
<% 
	opengraphJspBean.init( request, opengraphJspBean.MANAGE_OPENGRAPH_SOCIALHUB );
	response.sendRedirect( opengraphJspBean.confirmRemoveOpengraphSocialHub( request ) );
%>
