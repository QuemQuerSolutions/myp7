<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	if($("#mensagem").val() !== ""){
		alerta($("#mensagem").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	}

	$("#btnNovo").click(function(e){
		e.stopPropagation();
		go("editarRepresentante");
	});

	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll("#representante_lista_body");
		$("#idRepresentante").focus();
	});
	
	$("#pesquisar").click(function(e){
		e.stopPropagation();
		if(!hasInformation("#representante_lista_body")){
			alerta("Informe ao menos um filtro para buscar.", "warning");
			return;
		}
		
		go("#frmRepresentante");
	});
});

function onClickLine(id){
	
}


</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 
	
	<div id="content">
		<div id="content-title">
			<h4>Representante</h4>
		</div>
		
		<div id="representante_lista_body">
			<div id="content-header">
				<div class="row">
					<div class="col-md-2">
						<label for="idRepresentante" class="control-label">Código</label>
					</div>
					<div class="col-md-4">
						<label for="razao" class="control-label">Razão Social</label>
					</div>				
					<div class="col-md-2">
						<label for="apelido" class="control-label">Apelido</label>
					</div>
				</div>
				
				<form action="carregaListaRepresentante" name="frmRepresentante" id="frmRepresentante" method="GET">
					<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
					<input type="hidden" id="codMsgem" value="${codMsgem}" />
					
					<div class="row">	
						<div class="col-md-2">
							<div class="form-group">
						    	<input type="text" 
						    		   class="form-control onlyNumber campo-buscar upper" 
						    		   id="idRepresentante" 
						    		   name="idRepresentante" 
						    		   maxlength="11" 
						    		   autofocus="autofocus"
						    		   placeholder="Código"
						    		   value="${idRepresentante}" />
						  	</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group" >
								<input type="text" 
									   class="form-control  campo-buscar upper" 
									   name="razao"
									   placeholder="Razão Social" 
									   id="razao" 
									   maxlength="100" 
									   value="${razao}">
							</div>
						</div>
						
						<div class="col-md-2">
							<div class="form-group" >
								<input type="text" 
									   class="form-control  campo-buscar upper" 
									   name="apelido"
									   placeholder="Apelido" 
									   id="apelido" 
									   maxlength="50" 
									   value="${apelido}">
							</div>
						</div>					
						
						<div class="col-md-4"></div>
						<div class="col-md-1" id="btnpesquisar">
							<div class="form-group">
								<button type="button" class="btn ${theme}" id="pesquisar">Pesquisar</button>
							</div>
						</div>
						
						<div class="col-md-1" id="btnlimpar">
							<div class="form-group">
								<button type="button" class="btn btn-default limpar" id="limpar">Limpar</button>
							</div>
						</div>
						
					</div>
				</form>
			</div>
		</div>
		
		<div id="content-body">
			<table  class="table table-hover table-bordered table-striped mouse-click">
				<thead>
					<tr style="text-align: center">
						<th width="10%">Código</th>
						<th width="30%">Razãzo social</th>	
						<th width="30%">Apelido</th>
						<th width="10%">Status</th>
						<th width="20%">Usuário</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lstRepresentante}" var="repre">
						<tr class="${classLine}" onclick="onClickLine('${repre.idRepresentante}')">
							<td>${repre.idRepresentante}</td>
							<td>${repre.razao}</td>
							<td>${repre.apelido}</td>
							<td>${repre.status}</td>
							<td>${repre.usuario.razaoSocial}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>
	<c:import url="components/footer.jsp">
		<c:param name="novo" value="novo_representante" />
	</c:import>
</body>
</html>