<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ attribute name="label" required="true" %>
<%@ attribute name="icon" required="true" %>

<a href="#" target="_self">
	<span class="glyphicon ${icon}"></span>
	 &nbsp;${label}<span class="changeIcon glyphicon glyphicon-menu-down menu-down"></span> 
</a>