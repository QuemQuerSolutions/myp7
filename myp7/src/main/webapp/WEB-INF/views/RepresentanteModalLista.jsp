<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
$(document).ready(function(){

	$('#consulta_representante').on('hidden.bs.modal', function (e) {
		$("#lstRepresentanteModal").html("");
		clearAll("#filtroModalRepresentante");
	});
	
	$("#btnLimparRepresentante").click(function(e){
		e.stopPropagation();
		clearAll("#filtroModalRepresentante");
		$("#lstRepresentanteModal").html("");
	});

	$("#btnSelecionarRepresentante").click(function(e){
		e.stopPropagation();
		var representante;
		$("#lstRepresentanteModal tr").each(function(){
			if($(this).hasClass($("#theme").val())){
				representante = {idRepresentante	: $(this).find('td[data-id]').text(),
						   		 apelido			: $(this).find('td[data-apelido]').text(),}
			}
		});	
		addLineRepresentanteTab(representante);
		$('#consulta_representante').modal("hide");
	});
	
	$("#btnPesquisarRepresentante").click(function(e){
		e.stopPropagation();
				
		var representante = {idRepresentante	: $("#idRepresentanteBusca").val(),
							 razao				: $("#razaoSocialBusca").val(),
							 apelido			: $("#apelidoBusca").val()};
		
		$.ajax({
		    type: "GET",
		    url: "obterRepresentantePorParametro",
		    data: representante,
		    contentType: "application/json; charset=ISO-8859-1",
		    dataType: "json",
		    success: function(lista) {
			    var lines = "";
			    
			    if(lista.length == 0){
			    	$("#lstRepresentanteModal").html("<tr><td colspan='15'>Nenhum registro encontrado</td></tr>");
			    	return;
			    }
			    
			    if(lista[0].codRetorno == -1){
			    	alerta(lista[0].msgRetorno, "warning");
			    	$("#lstRepresentanteModal").html("");
			    	return;
			    }
			    
			    lista.forEach(function(representante){
			    	lines += getLineRepresentante(representante);
			    });
			    
			    $("#lstRepresentanteModal").html(lines);
		    },
		    error: function (xhr, textStatus, errorThrown) {
		    	console.log("Erro ao retornar lista: ",errorThrown);
		        alerta("Erro ao retornar lista","warning");
		    }
		});
	});
	
});

function onClickLineRepresentante(id){
	$("#lstRepresentanteModal tr").each(function(){
		$(this).removeClass($("#theme").val());
		if($(this).children().html() == id){
			$(this).addClass($("#theme").val());
		}
	});	
}

function getLineRepresentante(representante){
	var line = "";
	
	line = line.concat("<tr onclick='onClickLineRepresentante(", representante.idRepresentante,")'>");
	line = line.concat("<td>", representante.idRepresentante, "</td>");
	line = line.concat("<td data-id>", representante.usuario.idUsuario, "</td>");
		line = line.concat("<td data-apelido>", representante.apelido, "</td>");
	line = line.concat("</tr>");
	
	return line;
}

</script>
<input type="hidden" id="theme" value="${theme}" />
<div class="modal fade bs-example-modal-lg" id="consulta_representante">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header ${theme}">
				<button type="button" class="close limpar" id="fechar" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Consulta de Representante</h4>
			</div>
			<div class="modal-body" id="filtroModalRepresentante">
				<div id="content-header">
					<div class="row">
						<div class="col-md-2">
							<label for="idRepresentanteBusca" class="control-label">Código</label>
						</div>
						<div class="col-md-3">
							<label for="razaoSocialBusca" class="control-label">Razão Social</label>
						</div>
						<div class="col-md-3">
							<label for="apelidoBusca" class="control-label">Apelido</label>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 form-group">
					    	<input type="text" class="form-control onlyNumber" id="idRepresentanteBusca" maxlength="11">
						</div>
						<div class="col-md-3 form-group">
					    	<input type="text" class="form-control" id="razaoSocialBusca" maxlength="100">
	  					</div>
	  					<div class="col-md-3 form-group">
					    	<input type="text" class="form-control" id="apelidoBusca" maxlength="100">
	  					</div>
	  					<div class="col-md-2 form-group">
							<button type="button" class="btn ${theme}" id="btnPesquisarRepresentante">Pesquisar</button>
						</div>
						<div class="col-md-2 form-group">
							<button type="button" class="btn btn-default" id="btnLimparRepresentante">Limpar</button>
						</div>
					</div>
				</div>
				<div id="content-body">
					<table class="table table-hover table-bordered table-striped mouse-click">
						<thead>
							<tr>
								<th width="10%">Código</th>
								<th width="25%">Código Usuário</th>
								<th>Razão Social</th>
							</tr>
						</thead>
						<tbody id="lstRepresentanteModal"></tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn ${theme}" id="btnSelecionarRepresentante">Selecionar</button>
			</div> 
		</div>
	</div>
</div>