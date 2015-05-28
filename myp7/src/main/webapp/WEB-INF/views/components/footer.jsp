<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<footer id="footer" class="theme-orange">

	<c:choose>
		<c:when test="${not empty param.novo}">
			<button type="button" class="btn btn-default navbar-btn" id="btnNovo">Novo</button>
		</c:when>
		<c:otherwise>
			<div style="padding-top: 20px">&nbsp;</div>	
		</c:otherwise>
	</c:choose>
	
</footer>