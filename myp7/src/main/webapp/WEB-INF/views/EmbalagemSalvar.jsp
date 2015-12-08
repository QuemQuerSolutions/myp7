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
		$("#idEmbalagem").val("");
		$("#sigla").val("");
		$("#descricao").val("");
	});

	$(".campo-salvar").keypress(function(e){
	    if(e.which == 13) {
	        salvar();
	    }
	});

	$('#nova_embalagem').on('shown.bs.modal', function () {
		if(($("#codMsgem").val() == "0" && $("#idEmbalagem").val() == "0") ||
			($("#codMsgem").val() == "" && $("#idEmbalagem").val() == "")){
			$("#sigla").val("");
			$("#descricao").val("");
			$("#sigla").removeClass("disabled");
		}

		if($("#idEmbalagem").val() !== "0" && $("#idEmbalagem").val() !== ""){
			$("#sigla").addClass("disabled");
		}
		$('#sigla').focus();
		refreshDisabled();
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
		$("#divsiglamodal").attr("class","form-group has-error");
		isValid = false;
	}
	if($.trim($("#descricao").val()) == ""){
		$("#divdescricaomodal").attr("class","form-group has-error");
		isValid = false;
	}
	if($.trim($("#quantidade").val()) == ""){	
		$("#divquantidademodal").attr("class","form-group has-error");
		isValid = false;
	}
	
	return isValid;
}

function removeClass(){
	$("#divsiglamodal").attr("class","form-group");
	$("#divdescricaomodal").attr("class","form-group");
}

</script>

<div class="modal fade" id="nova_embalagem">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header ${theme}">
				<button type="button" class="close limpar" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Embalagem</h4>
			</div>
			<div class="modal-body">
				<form action="SalvarEmbalagem" id="frmInserirEmbalagem" method="POST">
					<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
					<input type="hidden" id="codMsgem" value="${codMsgem}" />
					<input type="hidden" id="idEmbalagem" name="idEmbalagem" value="0" />
					<div class="row">
						<div class="col-md-3">
							<div class="form-group" id="divsiglamodal">
						   		<label for="sigla" class="control-label">Sigla</label>
						    	<input type="text" class="form-control campo-salvar upper" id="sigla" name="siglaEmbalagem" maxlength="2" placeholder="Insira sigla" value="${embalagem.siglaEmbalagem}" >
						  	</div>
						</div>
	  					<div class="col-md-8">
							<div class="form-group" id="divdescricaomodal">
						   		<label for="descricao" class="control-label">Descrição</label>
						    	<input type="text" class="form-control campo-salvar" id="descricao" name="nomeEmbalagem" maxlength="100" placeholder="Insira descrição" value="${embalagem.nomeEmbalagem}">
						  	</div>
	  					</div>
					</div>
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default limpar" data-dismiss="modal">Cancelar</button>
				<button type="button" class="btn ${theme}" id="salvar">Salvar</button>
			</div>
		</div>
	</div>
</div>