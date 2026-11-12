<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%-- attribut onclick : les entités sont décodées avant l'exécution, l'échappement HTML ne protège pas. Attendu dans aVerifier. --%>
<button onclick="openInvoice('${fn:escapeXml(param.id)}')">Ouvrir</button>
