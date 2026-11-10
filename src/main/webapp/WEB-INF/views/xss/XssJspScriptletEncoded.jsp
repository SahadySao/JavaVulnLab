<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.owasp.encoder.Encode" %>
<p>Bonjour <%= Encode.forHtml(request.getParameter("name")) %></p>
