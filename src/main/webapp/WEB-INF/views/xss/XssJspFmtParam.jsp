<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%-- fmt:message n'échappe ni le message ni ses paramètres --%>
<p><fmt:message key="welcome"><fmt:param value="${param.name}"/></fmt:message></p>
