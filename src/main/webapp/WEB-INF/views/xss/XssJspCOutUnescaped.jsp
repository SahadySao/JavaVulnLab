<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- escapeXml="false" désactive l'échappement --%>
<p><c:out value="${param.q}" escapeXml="false"/></p>
