<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- échappé, mais l'URL entière est choisie : javascript:alert(1) passe --%>
<a href="<c:out value='${param.next}'/>">Continuer</a>
