<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	$("#btnNovo").click(function(){
		window.open('NovoProduto','_self');
	});

	$("#limpar").click(function(){
		$("#idProduto").val("");
		$("#desProduto").val("");
	});

	if($("#mensagem").val() !== ""){
		alerta($("#mensagem").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	}
	
	$("#pesquisar").click(function(){
		if($("#desProduto").val() == "" &&  $("#idProduto").val() == ""){
			alerta("Preencha um dos campos de pesquisa.", "warning");
		}else{
			$("#frmProduto").submit();
		}
	});

	  

});
</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 
	
	<div id="content">
		<div id="content-title">
			<h4>Produto</h4>
		</div>
		<div id="content-header">
			<div class="row">
				<div class="col-md-2">
					<label for="idProduto" class="control-label">Código</label>
				</div>
				<div class="col-md-10">
					<label for="desProduto" class="control-label">Descrição</label>
				</div>
			</div>
			<form action="carregaProdutos" id="frmProduto" method="GET">
				<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				<div class="row">	
					<div class="col-md-2">
						<div class="form-group" id="">
					    	<input type="text" 
					    			class="form-control" 
					    			id="idProduto"
					    			name="idProduto"
					    			maxlength="11"
					    			autofocus="autofocus"
					    			value="${produto.idProduto}" />
					  	</div>
					</div>
					<div class="col-md-8">
						<div class="form-group" id="">
					    	<input type="text" 
					    			class="form-control" 
					    			id="desProduto" 
					    			name="desProduto"
					    			maxlength="100" 
					    			value="${produto.desProduto}"/>
					  	</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<button type="button" class="btn ${theme}" id="pesquisar">Pesquisar</button>
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<button type="button" class="btn btn-default limpar" id="limpar">Limpar</button>
						</div>
					</div>
					
				</div>
			</form>
		</div>	
	</div>
	
	<div id="content">
		<table class="table table-hover table-bordered table-striped mouse-click">
			<thead>
				<tr>
					<th>Código</th>
					<th>Descrição</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${produtos}" var="e">
					<tr>
						<td>${e.idProduto}</td>
						<td>${e.desProduto}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<c:import url="components/footer.jsp">
		<c:param name="novo" value="novo_produto" />
	</c:import>
</body>
</html>