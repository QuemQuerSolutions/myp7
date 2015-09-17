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
	
	if($("#mensagem").val() !== "")	alerta($("#mensagem").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	

});

</script>

<div class="modal fade bs-example-modal-lg" id="consulta_pessoa">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header ${theme}">
				<button type="button" class="close limpar" data-dismiss="modal"
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
				<form action="consultarPessoa" id="frmPessoa" method="GET">
					<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
					<input type="hidden" id="codMsgem" value="${codMsgem}" />
					<div class="row">
						<div class="col-md-2">
							<div class="form-group" id="divCodigo">
						    	<input type="text" class="form-control campo-salvar upper" id="codigo" name="codigoPessoa" maxlength="2" placeholder="Insira Código" value="${pessoa.idPessoa}" >
						  	</div>
						</div>
	  					<div class="col-md-5">
							<div class="form-group" id="divNome">
						    	<input type="text" class="form-control campo-salvar" id="nome" name="nomePessoa" maxlength="100" placeholder="Insira nome" value="${pessoa.razao}">
						  	</div>
	  					</div>
	  					<div class="col-md-1" id="btnpesquisar">
							<div class="form-group">
								<button type="submit" class="btn ${theme}" id="pesquisar">Pesquisar</button>
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
			<div id="modal-body">
			<table  class="table table-hover table-bordered table-striped mouse-click">
				<thead>
					<tr style="text-align: center">
						<th>Código</th>
						<th>Razão Social</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lstPessoa}" var="p">
						<tr onclick="onClickLine('${p.idPessoa}')">
							<td>${p.idPessoa}</td>
							<td>${p.razao}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div> 
		</div>
	</div>
</div>