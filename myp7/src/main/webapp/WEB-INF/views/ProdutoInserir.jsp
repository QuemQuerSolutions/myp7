<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function() {
	$("#salvar").click(function(){
		salvar();
	});

	$(".campo-salvar").keypress(function(e){
	    if(e.which == 13) {
	        salvar();
	    }
	});

	if($("#mensagem").val() != ""){
		alerta($("#mensagem").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	}

	$('#codProduto').focus();
});

function salvar(){
	removeClass();
	if(!validaCamposObrigatorios()){
		alerta("Favor preencher os campos obrigatórios.", "warning");
	}else if(!validaCamposNumericos()){
		alerta("Os campos selecionados aceitam apenas números.", "warning");
	}else{
		$("#frmInserirProduto").submit();
	}
}

function validaCamposObrigatorios(){
	var isValid = true;
	if($.trim($("#codProduto").val()) == ""){
		$("#divcodigomodal").attr("class","form-group has-error");
		isValid = false;
	}
	if($.trim($("#codIndustria").val()) == ""){
		$("#divcodigoindustriamodal").attr("class","form-group has-error");
		isValid = false;
	}
	if($.trim($("#eanDunProduto").val()) == ""){	
		$("#diveandunmodal").attr("class","form-group has-error");
		isValid = false;
	}
	if($.trim($("#desProduto").val()) == ""){	
		$("#divdescricaomodal").attr("class","form-group has-error");
		isValid = false;
	}
	if($.trim($("#ncmProdutoST").val()) == ""){	
		$("#divncmmodal").attr("class","form-group has-error");
		isValid = false;
	}	
	if($.trim($("#pesoBruto").val()) == ""){	
		$("#divpesobrutomodal").attr("class","form-group has-error");
		isValid = false;
	}	
	if($.trim($("#pesoLiquido").val()) == ""){	
		$("#divpesoliquidomodal").attr("class","form-group has-error");
		isValid = false;
	}		
	if($.trim($("#embalagemST").val()) == "-1"){	
		$("#divembalagemmodal").attr("class","form-group has-error");
		isValid = false;
	}			
	if($.trim($("#qtdEmbalagem").val()) == ""){	
		$("#divqtdmodal").attr("class","form-group has-error");
		isValid = false;
	}	
	if($.trim($("#alturaProduto").val()) == ""){	
		$("#divalturamodal").attr("class","form-group has-error");
		isValid = false;
	}	
	if($.trim($("#larguraProduto").val()) == ""){	
		$("#divlarguramodal").attr("class","form-group has-error");
		isValid = false;
	}	
	if($.trim($("#profunProduto").val()) == ""){	
		$("#divprofundidademodal").attr("class","form-group has-error");
		isValid = false;
	}							
	
	return isValid;
}

function validaCamposNumericos(){
	var isValid = true;	
	if(!$.isNumeric($("#pesoBruto").val())){	
		$("#divpesobrutomodal").attr("class","form-group has-error");
		isValid = false;
	}	
	if(!$.isNumeric($("#pesoLiquido").val())){	
		$("#divpesoliquidomodal").attr("class","form-group has-error");
		isValid = false;
	}					
	if(!$.isNumeric($("#qtdEmbalagem").val())){	
		$("#divqtdmodal").attr("class","form-group has-error");
		isValid = false;
	}	
	if(!$.isNumeric($("#alturaProduto").val())){	
		$("#divalturamodal").attr("class","form-group has-error");
		isValid = false;
	}	
	if(!$.isNumeric($("#larguraProduto").val())){	
		$("#divlarguramodal").attr("class","form-group has-error");
		isValid = false;
	}	
	if(!$.isNumeric($("#profunProduto").val())){	
		$("#divprofundidademodal").attr("class","form-group has-error");
		isValid = false;
	}	

	return isValid;	
}

function removeClass(){
	$("#divcodigomodal").attr("class","form-group");
	$("#divcodigoindustriamodal").attr("class","form-group");
	$("#diveandunmodal").attr("class","form-group");
	$("#divdescricaomodal").attr("class","form-group");
	$("#divncmmodal").attr("class","form-group");
	$("#divpesobrutomodal").attr("class","form-group");
	$("#divpesoliquidomodal").attr("class","form-group");
	$("#divembalagemmodal").attr("class","form-group");
	$("#divqtdmodal").attr("class","form-group");
	$("#divalturamodal").attr("class","form-group");
	$("#divlarguramodal").attr("class","form-group");
	$("#divprofundidademodal").attr("class","form-group");		
}

</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 
	
	<div id="content">	
		<div id="content-title">
			<h4>Produto</h4>
		</div>
		
		<div id="content-body">
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
					    	<input type="text" class="form-control campo-salvar" id="codProduto" name="codProduto" maxlength="100" placeholder="Insira código" value="${produto.codProduto}">
					  	</div>
 					</div>
 					<div class="col-md-3">
						<div class="form-group" id="divcodigoindustriamodal">
					   		<label for="codIndustria" class="control-label">Código da Indústria</label>
					    	<input type="text" class="form-control campo-salvar" id="codIndustria" name="codIndustria" maxlength="100" placeholder="Insira código da indústria" value="${produto.codIndustria}">
					  	</div>
 					</div>
 					<div class="col-md-3">
						<div class="form-group" id="diveandunmodal">
					   		<label for="eanDunProduto" class="control-label">EAN/DUN</label>
					    	<input type="text" class="form-control campo-salvar" id="eanDunProduto" name="eanDunProduto" maxlength="100" placeholder="Insira EAN/DUN" value="${produto.eanDunProduto}">
					  	</div>
 					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group" id="divdescricaomodal">
					   		<label for="desProduto" class="control-label">Descrição</label>
					    	<input type="text" class="form-control campo-salvar" id="desProduto" name="desProduto" maxlength="100" placeholder="Insira a descrição do produto" value="${produto.desProduto}">
					  	</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group" id="divncmmodal">
					   		<label for="ncmProdutoST" class="control-label">NCM</label>
					    	<input type="text" class="form-control campo-salvar" id="ncmProdutoST" name="ncmProdutoST" maxlength="100" placeholder="Insira o NCM do produto" value="${produto.ncmProduto.codNcm}">
					  	</div>
					</div>
					<div class="col-md-3">
						<div class="form-group" id="divpesobrutomodal">
					   		<label for="pesoBruto" class="control-label">Peso Bruto</label>
					    	<input type="text" class="form-control campo-salvar" id="pesoBruto" name="pesoBruto" maxlength="100" placeholder="Insira o peso bruto" value="${produto.pesoBruto}">
					  	</div>
					</div>
					<div class="col-md-3">
						<div class="form-group" id="divpesoliquidomodal">
					   		<label for="pesoLiquido" class="control-label">Peso Líquido</label>
					    	<input type="text" class="form-control campo-salvar" id="pesoLiquido" name="pesoLiquido" maxlength="100" placeholder="Insira o peso líquido" value="${produto.pesoLiquido}">
					  	</div>
					</div>												
				</div>			
				<div class="row">
					<div class="col-md-4">
						<div class="form-group" id="divembalagemmodal">
					   		<label for="embalagemST" class="control-label">Embalagem Compra</label>
					  		<select id="embalagemST" name="embalagemST" class="form-control">
					  			<option value="-1">Selecione uma Embalagem</option>
					  			<c:forEach var="emabalagem" items="${embalagens}">
					  				<c:choose>
  										<c:when test="${emabalagem.idEmbalagem == produto.embalagem.idEmbalagem}">
											<option value="${emabalagem.idEmbalagem}" selected="selected">${emabalagem.siglaEmbalagem}</option>
										</c:when>
					  					<c:otherwise>
					  						<option value="${emabalagem.idEmbalagem}">${emabalagem.siglaEmbalagem}</option>
					  					</c:otherwise>
					  				</c:choose>
    							</c:forEach>
					  		</select>
					  	</div>
					</div>
					<div class="col-md-2">
						<div class="form-group" id="divqtdmodal">
					   		<label for="qtdEmbalagem" class="control-label">Qtd. Emb.</label>
					    	<input type="text" class="form-control campo-salvar" id="qtdEmbalagem" name="qtdEmbalagem" maxlength="100" placeholder="Qtd" value="${produto.embalagem.qtdEmbalagem}">
					  	</div>
					</div>
					<div class="col-md-2">
						<div class="form-group" id="divalturamodal">
					   		<label for="alturaProduto" class="control-label">Altura</label>
					    	<input type="text" class="form-control campo-salvar" id="alturaProduto" name="alturaProduto" maxlength="100" placeholder="Altura" value="${produto.alturaProduto}">
					  	</div>
					</div>
					<div class="col-md-2">
						<div class="form-group" id="divlarguramodal">
					   		<label for="larguraProduto" class="control-label">Largura</label>
					    	<input type="text" class="form-control campo-salvar" id="larguraProduto" name="larguraProduto" maxlength="100" placeholder="Largura" value="${produto.larguraProduto}">
					  	</div>
					</div>	
					<div class="col-md-2">
						<div class="form-group" id="divprofundidademodal">
					   		<label for="profunProduto" class="control-label">Profundidade</label>
					    	<input type="text" class="form-control campo-salvar" id="profunProduto" name="profunProduto" maxlength="100" placeholder="Profundidade" value="${produto.profunProduto}">
					  	</div>
					</div>	
				</div>
				<div class="row">
					<div class="col-md-1" id="btnpesquisar">
						<div class="form-group">
							<button type="button" class="btn btn-warning" id="salvar">Salvar</button>
	
							<button type="button" class="btn btn-default" id="cancelar">Cancelar</button>
						</div>
					</div>		
				</div>
			</form>		
		</div>
	</div>
	
	<c:import url="components/footer.jsp">
		<c:param name="novo" value="novo_produto" />
	</c:import>
</body>
</html>