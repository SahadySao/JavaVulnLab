<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="query" value="${param.q}"/>
<h1>Recherche</h1>
<input type="text" name="q" value="${query}">
