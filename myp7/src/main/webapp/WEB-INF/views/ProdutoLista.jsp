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
		$("#codigoProduto").val("");
		$("#descricaoProduto").val("");
	});

	$("#pesquisar").click(function(){
		if($("#codigoProduto").val() == "" &&  $("#descricaoProduto").val() == ""){
			alerta("Preencha um dos campos de pesquisa.", "warning");
		}else{
			$("frmProduto").submit();
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
				<div class="col-md-1">
					<label for="codigo" class="control-label">Código</label>
				</div>
				<div class="col-md-8">
					<label for="descricao" class="control-label">Descrição</label>
				</div>
			</div>
			
			<form action="" name="frmProduto">
				<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				<input type="hidden" id="outraPagina" value="${outraPagina}" />
				<div class="row">	
					<div class="col-md-1">
						<div class="form-group" id="">
					    	<input type="text" 
					    			class="form-control upper" 
					    			id="codigoProduto" 
					    			maxlength="10"
					    			autofocus="autofocus"
					    			value="${emb.siglaEmbalagem}" />
					  	</div>
					</div>
					<div class="col-md-7">
						<div class="form-group" id="">
					    	<input type="text" 
					    			class="form-control" 
					    			id="descricaoProduto" 
					    			maxlength="100" 
					    			value="${emb.nomeEmbalagem}"/>
					  	</div>
					</div>
					<div class="col-md-1" id="btnpesquisar">
						<div class="form-group">
							<button type="button" class="btn btn-warning" id="pesquisar">Pesquisar</button>
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
			<table class="table table-hover table-bordered table-striped mouse-click">
				<thead>
					<tr>
						<th>Código</th>
						<th>Descrição</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="" var="e">
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	<c:import url="components/footer.jsp">
		<c:param name="novo" value="novo_produto" />
	</c:import>
</body>
</html>