<%@ page errorPage="../../../../ErrorPage.jsp" %>
<jsp:include page="../../../../AdminHeader.jsp" />

<%@page import="fr.paris.lutece.plugins.extend.modules.opengraph.web.OpengraphJspBean"%>

${ opengraphJspBean.init( pageContext.request, OpengraphJspBean.MANAGE_OPENGRAPH_SOCIALHUB ) }
${ opengraphJspBean.getManageOpengraphSocialHub( pageContext.request ) }

<%@ include file="../../../../AdminFooter.jsp" %>
