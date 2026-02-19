<%@ page errorPage="../../../../ErrorPage.jsp" %>

<%@page import="fr.paris.lutece.plugins.extend.modules.opengraph.web.OpengraphJspBean"%>

${ opengraphJspBean.init( pageContext.request, OpengraphJspBean.MANAGE_OPENGRAPH_SOCIALHUB ) }
${ pageContext.setAttribute( 'pluginActionResult', opengraphJspBean.getAddOpengraphSocialHub( pageContext.request ) ) }
${ not empty pageContext.getAttribute( 'pluginActionResult' ).redirect ? pageContext.response.sendRedirect( pageContext.getAttribute( 'pluginActionResult' ).redirect ) : '' }

<jsp:include page="../../../../AdminHeader.jsp" />

${ not empty pageContext.getAttribute( 'pluginActionResult' ).htmlContent ? pageContext.getAttribute( 'pluginActionResult' ).htmlContent : '' }

<%@ include file="../../../../AdminFooter.jsp" %>
