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
		goComprador();
	});
	
	$("#btnNovo").click(function(e){
		e.stopPropagation();
		go("editarComprador");
	});
	
	$("input").keypress(function(e){
		e.stopPropagation();
		if(e.which == 13) goComprador();
	});


	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll();
		emptyTable("#tblComprador");
		$("#id").focus();
	});
	
});

function onClickLine(id){
	go("editarComprador?id="+id);
}

function goComprador(){
// 	if(!hasInformation("#frmComprador")){
// 		alerta("Informe ao menos um filtro para buscar", "warning");
// 		return;
// 	}
	go("#frmComprador");
}


</script>
<body>
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Comprador</h4>
		</div>
		
		<div id="content-header">
			<div class="row">
				<div class="col-md-1">
					<label for="sigla" class="control-label">C�digo</label>
				</div>
				<div class="col-md-8">
					<label for="descricao" class="control-label">Nome</label>
				</div>
			</div>
			
			<form action="CarregaListaComprador" id="frmComprador" method="GET">
				<input type="hidden" id="mensagemRetorno" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				
				<div class="row">	
					<div class="col-md-1">
						<div class="form-group">
					    	<input type="text" 
					    			class="form-control upper" 
					    			id="id" 
					    			name="id" 
					    			maxlength="11" 
					    			autofocus="autofocus"
					    			value="${comprador.id}" />
					  	</div>
					</div>
					
					<div class="col-md-7">
						<div class="form-group">
					    	<input type="text" 
					    			class="form-control" 
					    			id="apelido" 
					    			name="apelido" 
					    			maxlength="45"
					    			value="${comprador.apelido}" />
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
			<table  class="table table-hover table-bordered table-striped mouse-click" id="tblComprador">
				<thead>
					<tr style="text-align: center">
						<th width="10%">C�digo</th>
						<th width="30%">Apelido</th>
						<th width="50%">Nome</th>
						<th width="10%">C�d ERP</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lstComprador}" var="c">
						<c:choose>
							<c:when test="${c.status eq 'A'}">
								<c:set var="classLine" value="registroAtivo" />
							</c:when>
							<c:otherwise>
								<c:set var="classLine" value="registroInativo" />
							</c:otherwise>
						</c:choose>
						
						<tr class="${classLine}" onclick="onClickLine(${c.id})">
							<td>${c.id}</td>
							<td>${c.apelido}</td>
							<td>${c.razao}</td>
							<td>${c.ediCodigo}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>
	<c:import url="/WEB-INF/views/components/footer.jsp">
		<c:param name="novo" value="novo_comprador" />
	</c:import>
</body>
</html>