<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	$("#btnNovo").click(function(){
		window.open('NovoProduto','_self');
	});

	$("#limpar").click(function(){
		$("#idProduto").val("");
		$("#desProduto").val("");
		emptyTable("#tblProduto");
	});

	$(".campo-buscar").keypress(function(e){
	    if(e.which == 13) {
	    	$("#pesquisar").click();
	    }
	});

	if($("#mensagem").val() !== ""){
		alerta($("#mensagem").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	}
	
	$("#pesquisar").click(function(){
		$("#frmProduto").submit();
	});

	
});

function onClickLineProduto(id){
	$("#codProduto").val(id);
	$("#frmEditarProdutos").submit();
}
</script>
<body>
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 
	
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
			
			<form action="EditarProduto" id="frmEditarProdutos" method="POST">
				<input type="hidden" id="codProduto" name="codProduto" value="" />
			</form>
			
			<form action="carregaProdutos" id="frmProduto" method="GET">
				<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				<div class="row">	
					<div class="col-md-2">
						<div class="form-group" id="">
					    	<input type="number" 
					    			class="form-control onlyNumber campo-buscar" 
					    			id="idProduto"
					    			name="idProduto"
					    			min="0"
					    			max="99999999999"
					    			autofocus="autofocus"
					    			value="${produto.idProduto}" />
					  	</div>
					</div>
					<div class="col-md-8">
						<div class="form-group" id="">
					    	<input type="text" 
					    			class="form-control campo-buscar" 
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
		<div id="content-body">
			<table class="table table-hover table-bordered table-striped mouse-click" id="tblProduto">
				<thead>
					<tr>
						<th width="10%">Código</th>
						<th width="90%">Descrição</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${produtos}" var="e">
						<tr onclick="onClickLineProduto('${e.idProduto}')">
							<td>${e.idProduto}</td>
							<td>${e.desProduto}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>		
	</div>
	
	<c:import url="/WEB-INF/views/components/footer.jsp">
		<c:param name="novo" value="novo_produto" />
	</c:import>
</body>
</html>