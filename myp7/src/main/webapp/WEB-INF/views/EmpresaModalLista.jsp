<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
$(document).ready(function(){

	$("#limpar").click(function(e){
		e.stopPropagation();
		$("#idEmpresa").val("");
		$("#nomeReduzido").val("");
	});

	

	$("#btnPesquisarEmpresa").click(function(e){
		e.stopPropagation();
		
		var empresa = {idEmpresa: 1, nomeReduzido: null};
		
		$.ajax({
		    type: "GET",
		    url: "obterEmpresaPorParametro",
		    data: empresa,
		    contentType: "application/json; charset=ISO-8859-1",
		    dataType: "json",
		    success: function(lista) {
			    var lines = "";
			    
			    if(lista.length == 0){
			    	$("#lstEmpresaModal").html("<tr><td colspan='15'>Nenhum registro.</td></tr>");
			    	loading(false);
			    	return;
			    }
			    
			    if(lista[0].codRetorno == -1){
			    	alerta(lista[0].msgRetorno, "warning");
			    	$("#lstEmpresaModal").html("");
			    	return;
			    }
			    console.log(lista);
		    },
		    error: function (xhr, textStatus, errorThrown) {
		    	console.log("Erro ao retornar lista: ",errorThrown);
		        alerta("Erro ao retornar lista","warning");
		    }
		});
	});
	
});

</script>

<div class="modal fade bs-example-modal-lg" id="consulta_empresa">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header ${theme}">
				<button type="button" class="close limpar" id="fechar" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Consulta de Empresa</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-2">
						<label for="codigo" class="control-label">Código</label>
					</div>
					<div class="col-md-5">
						<label for="nome" class="control-label">Nome</label>
					</div>
				</div>
				<form>
					<div class="row">
						<div class="col-md-2">
							<div class="form-group">
						    	<input type="text" class="form-control upper" id="codigo"" >
						  	</div>
						</div>
	  					<div class="col-md-5">
							<div class="form-group" id="divNome">
						    	<input type="text" class="form-control" id="nome" maxlength="100">
						  	</div>
	  					</div>
	  					<div class="col-md-2" id="btnPesquisarEmpresa">
							<div class="form-group">
								<button type="button" class="btn ${theme}" id="btnPesquisar">Pesquisar</button>
							</div>
						</div>
						<div class="col-md-2" id="btnlimpar">
							<div class="form-group">
								<button type="button" class="btn btn-default limpar" id="limpar">Limpar</button>
							</div>
						</div>
					</div>
				</form>
				<table  class="table table-hover table-bordered table-striped mouse-click">
					<thead>
						<tr style="text-align: center">
							<th>Código</th>
							<th>Razão Social</th>
						</tr>
					</thead>
					<tbody id="lstEmpresaModal">
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
			</div> 
		</div>
	</div>
</div>