<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	
	if($("#mensagemRetorno").val() !== ""){
		alerta($("#mensagemRetorno").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	}

	$("#pesquisar").click(function(e){
		e.stopPropagation();
	});
	
	$("#btnNovo").click(function(e){
		e.stopPropagation();
	});

	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll();
	});
	
});


</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Aprovação de Produtos</h4>
		</div>
		
		<form action="CarregaListaProdutoAprovacao" id="frmAprovacaoProduto" method="GET">
			<input type="hidden" id="mensagemRetorno" value="${mensagemRetorno}" />
			<input type="hidden" id="codMsgem" value="${codMsgem}" />
		
		<div id="content-header">
			<div class="row">
				<div class="col-md-5">
					<label for="representante" >Representante</label>
				</div>
				<div class="col-md-1">
					<label for="buscaRepresentante">&nbsp;</label>
				</div>
				<div class="col-md-6">
					<label for="situacao">Situação</label>
				</div>
			</div>
			<div class="row">
				<div class="col-md-5 form-group">
					<input type="hidden" id="idRepresentante">
					<input type="text" class="form-control" readonly="readonly" id="representante"/>
				</div>
				<div class="col-md-1 form-group paddingleft0">
				  	<a href="#" target="_self" class="form-control icon-search" id="buscaRepresentante"><span class="glyphicon glyphicon-search"></span></a>
				</div>
				<div class="col-md-6 form-group btn-group" data-toggle="buttons">
					<label class="btn ${theme} active">
					    <input type="radio" name="situacoes" id="integrado" autocomplete="off" checked> Integrado 
					    <span id="qtdIntegrado" class="badge">0</span>
					  </label>
					<label class="btn ${theme}">
    					<input type="radio" name="situacoes" id="aprovado" autocomplete="off"> Aprovado 
    					<span id="qtdAprovado" class="badge">0</span>
  					</label>
  					<label class="btn ${theme}">
    					<input type="radio" name="situacoes" id="reprovado" autocomplete="off"> Reprovado 
    					<span id="qtdReprovado" class="badge">0</span>
  					</label>
  					<label class="btn ${theme}">
    					<input type="radio" name="situacoes" id="aguardando" autocomplete="off"> Aguardando Aprovação 
    					<span id="qtdAguardando" class="badge">0</span>
  					</label>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-1">
					<label for="idProduto">Código</label>
				</div>
				<div class="col-md-8">
					<label for="descricao">Descrição</label>
				</div>
			</div>
				
			<div class="row">	
				<div class="col-md-1 form-group">
			    	<input type="text" 
			    			class="form-control" 
			    			id="idProduto" 
			    			name="idProduto" 
			    			maxlength="11" 
			    			autofocus="autofocus"
			    			value="${produto.idProduto}" />
				</div>
				
				<div class="col-md-8 form-group">
			    	<input type="text" 
			    			class="form-control" 
			    			id="descricao" 
			    			name="descricao" 
			    			maxlength="45"
			    			value="${produto.descricao}" />
				</div>
				
				<div class="col-md-1 form-group" id="btnpesquisar">
					<button type="button" class="btn ${theme}" id="pesquisar">Pesquisar</button>
				</div>
				
				<div class="col-md-1 form-group" id="btnlimpar">
					<button type="button" class="btn btn-default limpar" id="limpar">Limpar</button>
				</div>
				
			</div>
		
		</div>
		</form>
		
		<div id="content-body">
			<table  class="table table-hover table-bordered table-striped mouse-click">
				<thead>
					<tr>
						<th width="15%">Código EAN</th>
						<th width="15%">Código Import</th>
						<th width="60%">Descrição</th>
						<th width="10%" class="text-center">Situação</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lstProduto}" var="p">
						<tr onclick="onClickLine(${p.idProduto})">
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>
	<c:import url="components/footer.jsp" />
</body>
</html>