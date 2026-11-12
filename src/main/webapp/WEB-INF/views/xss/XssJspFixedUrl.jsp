<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%-- partie fixe au début de l'URL, valeur échappée : sûr --%>
<a href="/factures/${fn:escapeXml(param.id)}">Voir</a>
