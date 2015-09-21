<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
$(document).ready(function(){

	$("#btnlimparEmpresa").click(function(e){
		e.stopPropagation();
		$("#idEmpresaBusca").val("");
		$("#nomeReduzidoBusca").val("");
	});

	$("#btnPesquisarEmpresa").click(function(e){
		e.stopPropagation();
		
		if(!hasInformation("#filtroModalEmpresa")){
			alerta("Informe ao menos um filtro para continuar", "warning");
			return;
		}
		
		var empresa = {idEmpresa	: $("#idEmpresaBusca").val(), 
					   nomeReduzido	: $("#nomeReduzidoBusca").val()};
		
		$.ajax({
		    type: "GET",
		    url: "obterEmpresaPorParametro",
		    data: empresa,
		    contentType: "application/json; charset=ISO-8859-1",
		    dataType: "json",
		    success: function(lista) {
			    var lines = "";
			    
			    if(lista.length == 0){
			    	$("#lstEmpresaModal").html("<tr><td colspan='15'>Nenhum registro encontrado</td></tr>");
			    	return;
			    }
			    
			    if(lista[0].codRetorno == -1){
			    	alerta(lista[0].msgRetorno, "warning");
			    	$("#lstEmpresaModal").html("");
			    	return;
			    }
			    
			    lista.forEach(function(empresa){
			    	lines += getLineEmpresa(empresa);
			    });
			    
			    $("#lstEmpresaModal").html(lines);
		    },
		    error: function (xhr, textStatus, errorThrown) {
		    	console.log("Erro ao retornar lista: ",errorThrown);
		        alerta("Erro ao retornar lista","warning");
		    }
		});
	});
	
});

function getLineEmpresa(empresa){
	var line = "";
	
	line = line.concat("<tr>");
		line = line.concat("<td>", empresa.idEmpresa, "</td>");
		line = line.concat("<td>", empresa.nomeReduzido, "</td>");
	line = line.concat("</tr>");
	
	return line;
}

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
			<div class="modal-body" id="filtroModalEmpresa">
				<div class="row">
					<div class="col-md-2">
						<label for="codigo" class="control-label">Código</label>
					</div>
					<div class="col-md-5">
						<label for="nome" class="control-label">Nome</label>
					</div>
				</div>
					<div class="row">
						<div class="col-md-2 form-group">
					    	<input type="text" class="form-control" id="idEmpresaBusca" maxlength="11">
						</div>
	  					<div class="col-md-5 form-group">
					    	<input type="text" class="form-control" id="nomeReduzidoBusca" maxlength="100">
	  					</div>
	  					<div class="col-md-2 form-group">
							<button type="button" class="btn ${theme}" id="btnPesquisarEmpresa">Pesquisar</button>
						</div>
						<div class="col-md-2 form-group">
							<button type="button" class="btn btn-default limpar" id="btnlimparEmpresa">Limpar</button>
						</div>
					</div>
				<table class="table table-hover table-bordered table-striped mouse-click">
					<thead>
						<tr>
							<th width="10%">Código</th>
							<th>Razão Social</th>
						</tr>
					</thead>
					<tbody id="lstEmpresaModal"></tbody>
				</table>
			</div>
			<div class="modal-footer">
			</div> 
		</div>
	</div>
</div>