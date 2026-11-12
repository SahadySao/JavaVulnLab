<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%-- bloc script, valeur échappée ENTRE guillemets : ne peut pas sortir de la chaîne --%>
<script>var query = '${fn:escapeXml(param.q)}';</script>
