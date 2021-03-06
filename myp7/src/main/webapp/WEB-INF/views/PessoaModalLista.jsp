<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll("#filtroModalPessoa");
		$("#resultado").html("");
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
		pesquisarPessoa();
	});
	$("#btnSelecionar").click(function(e){
		e.stopPropagation();
			var pessoaRetorno = getValueLineModal("#resultado");
			$("#razao").val(pessoaRetorno.nome);
			$("#idPessoa").val(pessoaRetorno.id);
			$("#limpar").click();
			$('#consulta_pessoa').modal("hide");
			
	});
});

function pesquisarPessoa(){
	var pessoa = {idPessoa : $("#codigo").val(),  razao : $("#nome").val()};
	$.ajax({
		url : "consultarPessoa",
		type : "GET",
        contentType: "application/json; charset=ISO-8859-1",
	    dataType: "json",
		data :pessoa,
        success : function(retornoList) {
        	if(retornoList.length == 0){
        		$("#resultado").html("<tr><td colspan='2'>Nenhum registro encontrado</td></tr>");
        	}
	        if(retornoList[0].codRetorno == -1){
		        alerta(retornoList[0].msgRetorno,"warning");
				$("#resultado").html("");
				return;
	        }
	        
	        $("#resultado").html(loadTable(retornoList));
        },
        error: function (xhr, textStatus, errorThrown) {
	    	console.log("Erro ao retornar lista: ",errorThrown)	;
	        alerta("Erro ao retornar lista","warning");
        }
    });
	
}

function loadTable(lista){
	var linha = "";
	lista.forEach(function(item){
		linha = linha.concat("<tr onclick=\"onClickLineModal('resultado',", item.idPessoa,")\"","'>", 
									"<td data-id>",item.idPessoa,"</td>", 
									"<td data-nome>", item.razao,"</td>",
							  "</tr>");
	});
	
	return linha;
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
			<div class="modal-body" id="filtroModalPessoa">
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
								<div class="form-group">
							    	<input type="text" 
							    		   class="form-control onlyNumber campo-buscar upper" 
							    		   id="codigo" 
							    		   maxlength="8"
							    		   placeholder="C�digo"
							    		   autofocus="autofocus" 
							    		   value="" 
							    		   />
							    		   
							  	</div>
							</div>
		  					<div class="col-md-5">
								<div class="form-group">
							    	<input type="text" 
							    		   class="form-control campo-buscar upper" 
							    		   id="nome" 
							    		   maxlength="100" 
							    		   placeholder="Nome pessoa" 
							    		   value=""/>
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