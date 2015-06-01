<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
$(document).ready(function() {
	$("#salvar").click(function(){
		salvar();
	});

	$(".limpar").click(function(){
		$("#sigla").val("");
		$("#descricao").val("");
		$("#quantidade").val("");
	});

	$(".campo-salvar").keypress(function(e){
	    if(e.which == 13) {
	        salvar();
	    }
	});

	if($("#mensagem").val() !== ""){
		alerta($("#mensagem").val(), "warning");
	}

	$('#nova_embalagem').on('shown.bs.modal', function () {
		  $('#sigla').focus();
	});
});

function salvar(){
	removeClass();
	if(!validaCamposObrigatorios()){
		alerta("Favor preencher os campos obrigatórios.", "warning");
	}else{
		$("#frmInserirEmbalagem").submit();
	}
}

function validaCamposObrigatorios(){
	var isValid = true;
	if($.trim($("#sigla").val()) == ""){
		$("#divsigla").attr("class","form-group has-error");
		isValid = false;
	}
	if($.trim($("#descricao").val()) == ""){
		$("#divdescricao").attr("class","form-group has-error");
		isValid = false;
	}
	if($.trim($("#quantidade").val()) == ""){	
		$("#divquantidade").attr("class","form-group has-error");
		isValid = false;
	}
	
	return isValid;
}

function removeClass(){
	$("#divsigla").attr("class","form-group");
	$("#divdescricao").attr("class","form-group");
	$("#divquantidade").attr("class","form-group");
}

</script>

<div class="modal fade" id="nova_embalagem">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header theme-orange">
				<button type="button" class="close limpar" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Inserir Embalagem</h4>
			</div>
			<div class="modal-body">
				<form action="InserirEmbalagem" id="frmInserirEmbalagem">
					<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
					<div class="row">
						<div class="col-md-3">
							<div class="form-group" id="divsigla">
						   		<label for="sigla" class="control-label">Sigla</label>
						    	<input type="text" class="form-control campo-salvar" id="sigla" name="siglaEmbalagem" maxlength="2" placeholder="Insira sigla" value="${embalagem.siglaEmbalagem}" >
						  	</div>
						</div>
	  					<div class="col-md-8">
							<div class="form-group" id="divdescricao">
						   		<label for="descricao" class="control-label">Descrição</label>
						    	<input type="text" class="form-control campo-salvar" id="descricao" name="nomeEmbalagem" maxlength="100" placeholder="Insira descrição" value="${embalagem.nomeEmbalagem}">
						  	</div>
	  					</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<div class="form-group" id="divquantidade">
						   		<label for="quantidade" class="control-label">Quantidade</label>
						    	<input type="text" class="form-control campo-salvar" id="quantidade" name="qtdEmbalagem" maxlength="2" placeholder="Quantidade" value="${embalagem.qtdEmbalagem}">
						  	</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default limpar" data-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-warning" id="salvar">Salvar</button>
			</div>
		</div>
	</div>
</div>