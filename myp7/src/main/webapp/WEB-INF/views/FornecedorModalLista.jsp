<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
$(document).ready(function(){

	$('#consulta_fornecedor').on('hidden.bs.modal', function (e) {
		$("#lstFornecedorModal").html("");
		clearAll("#filtroModalFornecedor");
	});
	
	$("#btnLimparFornecedor").click(function(e){
		e.stopPropagation();
		clearAll("#filtroModalFornecedor");
		$("#lstFornecedorModal").html("");
	});

	$("#btnSelecionarFornecedor").click(function(e){
		e.stopPropagation();
		var fornecedor;
		$("#lstFornecedorModal tr").each(function(){
			if($(this).hasClass($("#theme").val())){
				fornecedor = {idFornecedor	: $(this).find('td[data-id]').text(),
						   		 razao			: $(this).find('td[data-razao]').text(),}
			}
		});	
		alert(fornecedor.idFornecedor);
		alert(fornecedor.razao);
		addLineFornecedor(fornecedor);
		$('#consulta_fornecedor').modal("hide");
	});
	
	$("#btnPesquisarFornecedor").click(function(e){
		e.stopPropagation();
		
		if(!hasInformation("#filtroModalFornecedor")){
			alerta("Informe ao menos um filtro para continuar", "warning");
			return;
		}
		
		var fornecedor = {idFornecedor	: $("#idFornecedorBusca").val(), 
							 razao			: $("#razaoBusca").val()};
		
		$.ajax({
		    type: "GET",
		    url: "consultarFornecedor",
		    data: fornecedor,
		    contentType: "application/json; charset=ISO-8859-1",
		    dataType: "json",
		    success: function(lista) {
			    var lines = "";
			    
			    if(lista.length == 0){
			    	$("#lstFornecedorModal").html("<tr><td colspan='15'>Nenhum registro encontrado</td></tr>");
			    	return;
			    }
			    
			    if(lista[0].codRetorno == -1){
			    	alerta(lista[0].msgRetorno, "warning");
			    	$("#lstFornecedorModal").html("");
			    	return;
			    }
			    
			    lista.forEach(function(fornecedor){
			    	lines += getLineFornecedor(fornecedor);
			    });
			    
			    $("#lstFornecedorModal").html(lines);
		    },
		    error: function (xhr, textStatus, errorThrown) {
		    	console.log("Erro ao retornar lista: ",errorThrown);
		        alerta("Erro ao retornar lista","warning");
		    }
		});
	});
	
});


function getLineFornecedor(fornecedor){
	var line = "";
	
	line = line.concat("<tr onclick='onClickLineModal(\"lstFornecedorModal\", ", fornecedor.idFornecedor,")'>");
		line = line.concat("<td data-id>", fornecedor.idFornecedor, "</td>");
		line = line.concat("<td data-razao>", fornecedor.razao, "</td>");
	line = line.concat("</tr>");
	
	return line;
}

</script>
<input type="hidden" id="theme" value="${theme}" />
<div class="modal fade bs-example-modal-lg" id="consulta_fornecedor">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header ${theme}">
				<button type="button" class="close limpar" id="fechar" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Consulta de Fornecedor</h4>
			</div>
			<div class="modal-body" id="filtroModalFornecedor">
				<div id="content-header">
					<div class="row">
						<div class="col-md-2">
							<label for="idFornecedorBusca" class="control-label">Código</label>
						</div>
						<div class="col-md-5">
							<label for="razaoBusca" class="control-label">Razão Social</label>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 form-group">
					    	<input type="text" class="form-control onlyNumber" id="idFornecedorBusca" maxlength="11">
						</div>
	  					<div class="col-md-5 form-group">
					    	<input type="text" class="form-control" id="razaoBusca" maxlength="100">
	  					</div>
	  					<div class="col-md-2 form-group margin-right-50px">
							<button type="button" class="btn ${theme} btn-large" id="btnPesquisarFornecedor">Pesquisar</button>
						</div>
						<div class="col-md-2 form-group">
							<button type="button" class="btn btn-default btn-large" id="btnLimparFornecedor">Limpar</button>
						</div>
					</div>
				</div>
				<div id="content-body">
					<table class="table table-hover table-bordered table-striped mouse-click">
						<thead>
							<tr>
								<th width="10%">Código</th>
								<th>Razão Social</th>
							</tr>
						</thead>
						<tbody id="lstFornecedorModal"></tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn ${theme}" id="btnSelecionarFornecedor">Selecionar</button>
			</div> 
		</div>
	</div>
</div>