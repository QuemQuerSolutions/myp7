<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<footer id="footer" class="${theme}">

	<c:choose>
		<c:when test="${not empty param.novo}">
			<button type="button" class="btn btn-default navbar-btn" id="btnNovo">Novo</button>
		</c:when>
		
		<c:when test="${param.salvar eq 'salvar'}">
			<button type="button" class="btn btn-default navbar-btn ${theme}" id="btnSalvar">Salvar</button>
		</c:when>
		
		<c:when test="${param.salvar eq 'cancelar_salvar'}">
			<button type="button" class="btn btn-default navbar-btn ${theme}" id="btnSalvar">Salvar</button>
			<button type="button" class="btn btn-default navbar-btn" id="btnCancelar">Cancelar</button>
		</c:when>
		
		<c:when test="${param.salvar eq 'limpar_salvar'}">
			<button type="button" class="btn btn-default navbar-btn ${theme}" id="btnSalvar">Salvar</button>
			<button type="button" class="btn btn-default navbar-btn" id="btnLimpar">Limpar</button>
		</c:when>
		
		<c:when test="${param.salvar eq 'reprovar_aprovar'}">
			<button type="button" class="btn btn-default navbar-btn ${theme}" id="btnAprovar">Aprovar</button>
			<button type="button" class="btn btn-default navbar-btn" id="btnReprovar">Reprovar</button>
		</c:when>		
		
		<c:otherwise>
			<div style="padding-top: 20px">&nbsp;</div>	
		</c:otherwise>
	</c:choose>
	
</footer>