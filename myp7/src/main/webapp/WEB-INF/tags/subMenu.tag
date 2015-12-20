<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="link" required="true" %>
<%@ attribute name="label" required="true" %>

<li class="submenu">
	<a href=<c:url value="${link}" /> target="_self">${label}</a>
</li>