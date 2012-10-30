<%@ page errorPage="../../../../ErrorPage.jsp" %>
<jsp:include page="../../../../AdminHeader.jsp"  flush="true" />

<jsp:useBean id="opengraphJspBean" scope="session" class="fr.paris.lutece.plugins.extend.modules.opengraph.web.OpengraphJspBean" />

<% opengraphJspBean.init( request, opengraphJspBean.MANAGE_OPENGRAPH_SOCIALHUB ) ; %>
<%= opengraphJspBean.getManageOpengraphSocialHub( request ) %>

<%@ include file="../../../../AdminFooter.jsp" %>