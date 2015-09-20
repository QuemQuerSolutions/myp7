<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
$(document).ready(function(){

	$("#limpar").click(function(){
		$("#codigo").val("");
		$("#nome").val("");
	});

	$(".campo-buscar").keypress(function(e){
		e.stopPropagation();
	    if(e.which == 13) {
	    	$("#btnPesquisar").click();
	    }
	});

	
	$("#btnPesquisar").click(function(){
		pesquisarPessoa();
	});

	function pesquisarPessoa(){
		$.ajax({
			url : "consultarPessoa?codPessoa=".concat($("#codigo").val(),"&nomePessoa=",$("#nome").val()),
			type: "GET",
	        contentType: "application/json; charset=ISO-8859-1",
		    dataType: "json",
	        success : function(retornoList) {
		        if(retornoList[0].codRetorno !== 0){
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
		linha = linha.concat("<tr>", "<td>",lista.idPessoa,"</td>", "<td>", lista.razao,"</td>","</tr>");
		return linha;
	}
	function onClickLinePessoa(nome){
		
	}

	
});

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
							<div class="form-group" id="divCodigo">
						    	<input type="text" class="form-control campo-buscar upper" id="codigo" name="codigoPessoa" maxlength="2" placeholder="Insira Código" value="${pessoa.idPessoa}" >
						  	</div>
						</div>
	  					<div class="col-md-5">
							<div class="form-group" id="divNome">
						    	<input type="text" class="form-control campo-buscar" id="nome" name="nomePessoa" maxlength="100" placeholder="Insira nome" value="${pessoa.razao}">
						  	</div>
	  					</div>
	  					<div class="col-md-2" id="btnpesquisar">
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
					<tbody id="resultado">
						<%-- <c:forEach items="${lstPessoa}" var="p">
							<tr onclick="onClickLinePessoa('${p.razao}')">
								<td>${p.idPessoa}</td>
								<td>${p.razao}</td>
							</tr>
							<div id="oculto">
								<input type="hidden" id="mensagemPessoa" value="" />
								<input type="hidden" id="codMsgemPessoa" value="" />
							</div>
						</c:forEach> --%> 
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
			</div> 
		</div>
	</div>
</div>