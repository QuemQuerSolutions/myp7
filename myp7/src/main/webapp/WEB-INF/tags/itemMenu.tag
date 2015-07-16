<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ attribute name="link" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="icon" required="true" %>

<li ><a href="${link}" target="_self"><span class="glyphicon ${icon}"></span> &nbsp;${label}</a></li>