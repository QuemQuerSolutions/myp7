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

	$('#nova_embalagem').on('shown.bs.modal', function () {
		if($("#codMsgem").val() == "0"){
			$("#sigla").val("");
			$("#descricao").val("");
			$("#quantidade").val("");
		}
		$('#sigla').focus();
	});
});

function salvar(){
	removeClass();
// 	if(!validaCamposObrigatorios()){
// 		alerta("Favor preencher os campos obrigatórios.", "warning");
// 	}else{
		$("#frmInserirProduto").submit();
// 	}
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
	$("#divquantidademodal").attr("class","form-group");
}

</script>

<div class="modal fade" id="novo_produto">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header theme-orange">
				<button type="button" class="close limpar" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Inserir Produto</h4>
			</div>
			<div class="modal-body">
				<form action="InserirProduto" id="frmInserirProduto" method="POST">
					<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
					<input type="hidden" id="codMsgem" value="${codMsgem}" />
					<div class="row">
						<div class="col-md-3">
							<div class="form-group" id="divimagemmodal">
						    	<input type="text" class="form-control campo-salvar upper" id="imagem" name="imagem" placeholder="Imagem">
						  	</div>
						</div>
	  					<div class="col-md-3">
							<div class="form-group" id="divcodigomodal">
						   		<label for="codProduto" class="control-label">Código</label>
						    	<input type="text" class="form-control campo-salvar" id="codProduto" name="codProduto" maxlength="100" placeholder="Insira código" value="${produto}">
						  	</div>
	  					</div>
	  					<div class="col-md-3">
							<div class="form-group" id="divcodigoindustriamodal">
						   		<label for="codIndustria" class="control-label">Código da Indústria</label>
						    	<input type="text" class="form-control campo-salvar" id="codIndustria" name="codIndustria" maxlength="100" placeholder="Insira código da indústria" value="${produto}">
						  	</div>
	  					</div>
	  					<div class="col-md-3">
							<div class="form-group" id="diveandunmodal">
						   		<label for="eanDunProduto" class="control-label">EAN/DUN</label>
						    	<input type="text" class="form-control campo-salvar" id="eanDunProduto" name="eanDunProduto" maxlength="100" placeholder="Insira EAN/DUN" value="${produto}">
						  	</div>
	  					</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group" id="divdescricaomodal">
						   		<label for="desProduto" class="control-label">Descrição</label>
						    	<input type="text" class="form-control campo-salvar" id="desProduto" name="desProduto" maxlength="100" placeholder="Insira a descrição do produto" value="${produto}">
						  	</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group" id="divncmmodal">
						   		<label for="ncmProdutoST" class="control-label">NCM</label>
						    	<input type="text" class="form-control campo-salvar" id="ncmProdutoST" name="ncmProdutoST" maxlength="100" placeholder="Insira o NCM do produto" value="${produto}">
						  	</div>
						</div>
						<div class="col-md-3">
							<div class="form-group" id="divpesobrutomodal">
						   		<label for="pesoBruto" class="control-label">Peso Bruto</label>
						    	<input type="text" class="form-control campo-salvar" id="pesoBruto" name="pesoBruto" maxlength="100" placeholder="Insira o peso bruto" value="${produto}">
						  	</div>
						</div>
						<div class="col-md-3">
							<div class="form-group" id="divpesoliquidomodal">
						   		<label for="pesoLiquido" class="control-label">Peso Líquido</label>
						    	<input type="text" class="form-control campo-salvar" id="pesoLiquido" name="pesoLiquido" maxlength="100" placeholder="Insira o peso líquido" value="${produto}">
						  	</div>
						</div>												
					</div>			
					<div class="row">
						<div class="col-md-4">
							<div class="form-group" id="divembalagemmodal">
						   		<label for="embalagemST" class="control-label">Embalagem Compra</label>
						    	<input type="text" class="form-control campo-salvar" id="embalagemST" name="embalagemST" maxlength="100" value="${produto}">
						  	</div>
						</div>
						<div class="col-md-2">
							<div class="form-group" id="divqtdmodal">
						   		<label for="qtdProduto" class="control-label">Qtd. Emb.</label>
						    	<input type="text" class="form-control campo-salvar" id="qtdProduto" name="qtdProduto" maxlength="100" placeholder="Qtd" value="${produto}">
						  	</div>
						</div>
						<div class="col-md-2">
							<div class="form-group" id="divalturamodal">
						   		<label for="alturaProduto" class="control-label">Altura</label>
						    	<input type="text" class="form-control campo-salvar" id="alturaProduto" name="alturaProduto" maxlength="100" placeholder="Altura" value="${produto}">
						  	</div>
						</div>
						<div class="col-md-2">
							<div class="form-group" id="divlarguramodal">
						   		<label for="larguraProduto" class="control-label">Largura</label>
						    	<input type="text" class="form-control campo-salvar" id="larguraProduto" name="larguraProduto" maxlength="100" placeholder="Largura" value="${produto}">
						  	</div>
						</div>	
						<div class="col-md-2">
							<div class="form-group" id="divprofundidademodal">
						   		<label for="profunProduto" class="control-label">Profundidade</label>
						    	<input type="text" class="form-control campo-salvar" id="profunProduto" name="profunProduto" maxlength="100" placeholder="Profundidade" value="${produto}">
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