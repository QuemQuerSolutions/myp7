<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){

	if($("#mensagemRetorno").val() !== ""){
		alerta($("#mensagemRetorno").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	}

	$("#pesquisar").click(function(e){
		e.stopPropagation();
		go("#frmUsuario");
	});
	
	$("#btnNovo").click(function(e){
		e.stopPropagation();
		go("editarUsuario");
	});
	
	$("input").keypress(function(e){
		e.stopPropagation();
		if(e.which == 13) go("#frmUsuario");
	});


	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll();
		emptyTable("#tblUsuario");
		$("#id").focus();
	});
	
});


function onClickLine(id){
	go("editarUsuario?id=".concat(id));
}
</script>
<body>
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Usuário</h4>
		</div>
		
		<div id="content-header">
			<div class="row">
				<div class="col-md-5">
					<label for="razaoSocial" class="control-label">Razão Social</label>
				</div>
				<div class="col-md-4">
					<label for="email" class="control-label">E-mail</label>
				</div>
			</div>
			
			<form action="CarregaListaUsuario" id="frmUsuario" method="GET">
				<input type="hidden" id="mensagemRetorno" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				<div class="row">	
					<div class="col-md-5">
						<div class="form-group">
					    	<input type="text" 
					    			class="form-control" 
					    			id="razaoSocial" 
					    			name="razaoSocial" 
					    			maxlength="200" 
					    			autofocus="autofocus"
					    			value="${usuario.razaoSocial}" />
					  	</div>
					</div>
					
					<div class="col-md-4">
						<div class="form-group">
					    	<input type="text" 
					    			class="form-control" 
					    			id="email" 
					    			name="email" 
					    			maxlength="100"
					    			value="${usuario.email}" />
					  	</div>
					</div>
					
					<div class="col-md-1" id="btnpesquisar">
						<div class="form-group">
							<button type="button" class="btn ${theme}" id="pesquisar">Pesquisar</button>
						</div>
					</div>
					
					<div class="col-md-1" id="btnlimpar">
						<div class="form-group">
							<button type="button" class="btn btn-default limpar" data-dismiss="modal" id="limpar">Limpar</button>
						</div>
					</div>
					
				</div>
			</form>
		</div>
		
		<div id="content-body">
			<table  class="table table-hover table-bordered table-striped mouse-click" id="tblUsuario">
				<thead>
					<tr>
						<th width="10%">ID</th>
						<th width="50%">Razão</th>
						<th width="40%">E-mail</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lstUsuario}" var="u">
						<tr class="${u.ativo eq 1 ? 'registroAtivo' : 'registroInativo'}" 
							onclick="onClickLine('${u.idUsuario}');">
							<td>${u.idUsuario}</td>
							<td>${u.razaoSocial}</td>
							<td>${u.email}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>
	<c:import url="/WEB-INF/views/components/footer.jsp">
		<c:param name="novo" value="novo_usuario" />
	</c:import>
</body>
</html>