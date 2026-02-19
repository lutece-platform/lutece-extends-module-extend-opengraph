<%@ page errorPage="../../../../ErrorPage.jsp" %>

<%@page import="fr.paris.lutece.plugins.extend.modules.opengraph.web.OpengraphJspBean"%>

${ opengraphJspBean.init( pageContext.request, OpengraphJspBean.MANAGE_OPENGRAPH_SOCIALHUB ) }
${ pageContext.response.sendRedirect( opengraphJspBean.doAddOpengraphSocialHub( pageContext.request )) }
