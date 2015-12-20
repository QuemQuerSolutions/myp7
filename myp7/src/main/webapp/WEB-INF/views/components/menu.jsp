<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%-- <%@page session="true"%> --%>

<div id="side-bar">
	<div> 
		<div id="title-menu" class="">
			<span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>&nbsp;Menu
		</div>
		<div id="close-arrow">
			<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
		</div>
	</div>
	<div id="menu">
		<ul id="menu-nav" class="box">
		
			<sec:authorize access="hasRole('P')">
				<c:import url="/WEB-INF/views/components/menuPortal.jsp" />
			</sec:authorize>
			
			<sec:authorize access="hasRole('R')">
				<c:import url="/WEB-INF/views/components/menuRetaguarda.jsp" />
			</sec:authorize>
			
			<sec:authorize access="hasRole('T')">
				<c:import url="/WEB-INF/views/components/menuTodos.jsp" />
			</sec:authorize>
		</ul>
	</div>
</div>
