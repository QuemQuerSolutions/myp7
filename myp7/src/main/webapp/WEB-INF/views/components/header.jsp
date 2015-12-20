<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="header" class="page-header ${theme}">
	<h1>
		<small>Plataforma de Fornecedores</small>
		<a href=<c:url value="/logout" />>
			<small id="logout">
				<span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp; sair
			</small>
		</a>
		<label class="user-header">${usuarioLogado.email}</label>
	</h1>
</div>