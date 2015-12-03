<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){

	$("#clickEmpresa").click(function(e){
		e.stopPropagation();
		$("#consulta_empresa").modal();
	});
	
	$("#pesquisar").click(function(e){
		e.stopPropagation();
		go("#frmUsuario");
	});
	
	$("input").keypress(function(e){
		e.stopPropagation();
		if(e.which == 13) go("#frmUsuario");
	});


	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll();
		$("#id").focus();
	});
	
});

</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Relatório de Estoque</h4>
		</div>
		
		<div id="content-header">
			<div class="row">
				<div class="col-md-5">
					<label for="empresa" class="control-label">Empresa</label>
				</div>
				<div class="col-md-4">
					<label for="produto" class="control-label">Produto</label>
				</div>
			</div>
			
			<form action="CarregaListaUsuario" id="frmUsuario" method="GET">
				
				<div class="row">	
					<div class="col-md-4">
						<div class="form-group">
					    	<input type="hidden" id="idEmpresa" name="idEmpresa" value="">
					    	<input type="text" class="form-control" id="nomeReduzido" maxlength="11" value="" readonly="readonly">
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<a href="#" target="_self" class="form-control icon-search" id="clickEmpresa"><span class="glyphicon glyphicon-search"></span></a>
						</div>
					</div>
					
					<div class="col-md-3">
						<div class="form-group">
					    	<input type="hidden" id="idProduto" name="idProduto" value="">
					    	<input type="text" class="form-control" id="nomeProduto" maxlength="11" value="" readonly="readonly">
					  	</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<a href="#" target="_self" class="form-control icon-search" id="clickProduto"><span class="glyphicon glyphicon-search"></span></a>
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
			<table  class="table table-hover table-bordered table-striped">
				<thead>
					<tr>
						<th width="10%" style="vertical-align: middle !important; text-align: center !important;">Empresa</th>
						<th width="10%"  style="vertical-align: middle !important; text-align: center !important;">Produto</th>
						<th width="10%"  style="vertical-align: middle !important; text-align: center !important;">Qtd Estoque</th>
						<th width="10%"  style="vertical-align: middle !important; text-align: center !important;">Qtd Estoque Troca</th>
						<th width="10%"  style="vertical-align: middle !important; text-align: center !important;">Qtd Pendente Compras</th>	
						<th width="10%"  style="vertical-align: middle !important; text-align: center !important;">Qtd em Trânsito</th>
						<th width="10%"  style="vertical-align: middle !important; text-align: center !important;">Qtd Pendente Expedir</th>
						<th width="10%"  style="vertical-align: middle !important; text-align: center !important;">Média Venda Dia</th>
						<th width="10%"  style="vertical-align: middle !important; text-align: center !important;">Dias de Estoque</th>
						<th width="10%"  style="vertical-align: middle !important; text-align: center !important;">Dias Ultima Entrada</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lstUsuario}" var="u">
						<tr class="${classLine}">
							<td>${u.idUsuario}</td>
							<td>${u.razaoSocial}</td>
							<td>${u.email}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>
	
	<c:import url="EmpresaModalLista.jsp"/>
	
	<c:import url="components/footer.jsp"/>
</body>
</html>