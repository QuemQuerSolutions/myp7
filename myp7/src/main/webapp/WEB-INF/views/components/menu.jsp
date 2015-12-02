<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<c:if test="${tipoUsuarioRetorno eq 'P'}">
				<c:import url="components/menuPortal.jsp" />
			</c:if>
			<c:if test="${tipoUsuarioRetorno eq 'R'}">
				<c:import url="components/menuRetaguarda.jsp" />
			</c:if>
			<c:if test="${tipoUsuarioRetorno eq 'T'}">
				<c:import url="components/menuPortal.jsp" />
				<c:import url="components/menuRetaguarda.jsp" />
			</c:if>
		</ul>
	</div>
</div>