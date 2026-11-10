<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String name = request.getParameter("name");
    String greeting = "Bonjour " + name;
%>
<p><%= greeting %></p>
