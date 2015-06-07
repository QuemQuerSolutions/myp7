<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<footer id="footer" class="${theme}">

	<c:choose>
		<c:when test="${not empty param.novo}">
			<button type="button" class="btn btn-default navbar-btn" id="btnNovo">Novo</button>
		</c:when>
		<c:when test="${not empty param.salvar}">
			<button type="button" class="btn btn-default navbar-btn ${theme}" id="btnSalvar">Salvar</button>
			<button type="button" class="btn btn-default navbar-btn" id="btnCancelar">Cancelar</button>
		</c:when>
		<c:otherwise>
			<div style="padding-top: 20px">&nbsp;</div>	
		</c:otherwise>
	</c:choose>
	
</footer>