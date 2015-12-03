<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ attribute name="link" required="true" %>
<%@ attribute name="label" required="true" %>

<li class="submenu">
	<a href="${link}" target="_self">${label}</a>
</li>