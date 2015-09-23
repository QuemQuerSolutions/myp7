<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#limpar").click(function(e){
		e.stopPropagation();
		$("#resultado").html("");
		$("#codigo").val("");
		$("#nome").val("");
	});

	$(".campo-buscar").keypress(function(e){
		e.stopPropagation();
	    if(e.which == 13) {
	    	$("#btnPesquisar").click();
	    }
	});

	$("#fechar").click(function(){
		$("#limpar").click();
	});
	
	$("#btnPesquisar").click(function(e){
		e.stopPropagation();
		if(validaCamposObrigatorios(true))
			pesquisarPessoa();
	});

	$("#btnSelecionar").click(function(e){
		e.stopPropagation();
		if(!$("#codPessoa".concat(idAnterior)).hasClass($("#theme").val())){
			alerta("Selecione uma pessoa.", "warning");
		}else{
			$("#razao").val(nomePessoa);
			$("#idPessoa").val(idAnterior);
			$("#limpar").click();
			$('#consulta_pessoa').modal("hide");
		}
	});
});
var idAnterior;
var nomePessoa="";

function pesquisarPessoa(){
	$.ajax({
		url : "consultarPessoa?codPessoa=".concat($("#codigo").val(),"&nomePessoa=",$("#nome").val()),
		type: "GET",
        contentType: "application/json; charset=ISO-8859-1",
	    dataType: "json",
        success : function(retornoList) {
	        if(retornoList[0].codRetorno == -1){
				alerta(retornoList[0].msgRetorno, "warning");
				$("#resultado").html("");
				return;
	        }
	        
	        $("#resultado").html(montaTable(retornoList));
        },
        error: function (xhr, textStatus, errorThrown) {
	    	console.log("Erro ao retornar lista: ",errorThrown)	;
	        alerta("Erro ao retornar lista","warning");
        }
    });
	
}

function montaTable(lista){
	var linha = "";
	lista.forEach(function(item){
		linha = linha.concat("<tr onclick='onClickLine(", item.idPessoa,")' id='codPessoa",item.idPessoa,"'>", 
									"<td>",item.idPessoa,"</td>", 
									"<td id='nomePessoa", item.idPessoa,"'>", item.razao,"</td>",
							  "</tr>");
	});
	return linha;
}

function validaCamposObrigatorios(alert){
	var isValid=true;
	var texto="";
	if($("#codigo").val() === "" && $("#nome").val() === ""){
		texto="Preencha um dos campos de pesquisa.";
		isValid=false;
	}
	if($("#codigo").val() !== "" && !$.isNumeric($.trim($("#codigo").val()))){
		isValid =false;
		texto = "Somente n�meros no filtro c�digo.";
	}
	
	if(!isValid && alert){
		alerta(texto,"warning");
	}
	return isValid;
}

function onClickLine(id){
	if($("#codPessoa".concat(idAnterior)).hasClass($("#theme").val())){
		$("#codPessoa".concat(idAnterior)).removeClass($("#theme").val());
	}
	$("#codPessoa".concat(id)).addClass($("#theme").val());
	idAnterior = id;
	nomePessoa = $("#nomePessoa".concat(id)).text();
}

</script>

<div class="modal fade bs-example-modal-lg" id="consulta_pessoa">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header ${theme}">
				<button type="button" class="close limpar" id="fechar" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Consulta de Pessoa</h4>
			</div>
			<div class="modal-body">
				<div id="content-header">
					<div class="row">
						<div class="col-md-2">
							<label for="codigo" class="control-label">C�digo</label>
						</div>
						<div class="col-md-5">
							<label for="nome" class="control-label">Nome</label>
						</div>
					</div>
					<form>
						<input type="hidden" id="theme" value="${theme}" />
						<div class="row">
							<div class="col-md-2">
								<div class="form-group" id="divCodigo">
							    	<input type="text" 
							    		   class="form-control campo-buscar upper" 
							    		   id="codigo" 
							    		   name="codigoPessoa" 
							    		   maxlength="8" 
							    		   placeholder="C�digo"
							    		   autofocus="autofocus" 
							    		   value="${pessoa.idPessoa}" >
							  	</div>
							</div>
		  					<div class="col-md-5">
								<div class="form-group" id="divNome">
							    	<input type="text" 
							    		   class="form-control campo-buscar" 
							    		   id="nome" 
							    		   name="nomePessoa" 
							    		   maxlength="100" 
							    		   placeholder="Nome pessoa" 
							    		   value="${pessoa.razao}">
							  	</div>
		  					</div>
		  					<div class="col-md-2" id="btnpesquisar">
								<div class="form-group">
									<button type="button" class="btn ${theme} btn-large" id="btnPesquisar">Pesquisar</button>
								</div>
							</div>
							<div class="col-md-2" id="btnlimpar">
								<div class="form-group">
									<button type="button" class="btn btn-default btn-large" id="limpar">Limpar</button>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div id="content-body">
					<table  class="table table-hover table-bordered table-striped mouse-click">
						<thead>
							<tr>
								<th width="20%">C�digo</th>
								<th>Raz�o Social</th>
							</tr>
						</thead>
						<tbody id="resultado">
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn ${theme}" id="btnSelecionar">Selecionar</button>
			</div> 
		</div>
	</div>
</div>