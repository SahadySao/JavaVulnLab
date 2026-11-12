<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- spring:message : pas d'échappement par défaut (defaultHtmlEscape non configuré) --%>
<p><spring:message code="welcome" arguments="${param.name}"/></p>
