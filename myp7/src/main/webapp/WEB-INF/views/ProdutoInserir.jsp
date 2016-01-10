<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function() {
	
	$("#pesoBruto").mask("9999999.999");
	$("#pesoLiquido").mask("9999999.999");
	$("#alturaProduto").mask("9999.999999");
	$("#larguraProduto").mask("9999.999999");
	$("#profunProduto").mask("9999.999999");
	
	if($("#allDisabled").val()){
		disableAll();
	}
	
	$("#btnSalvar").click(function(){
		salvar();
	});

	$("#btnCancelar").click(function(){
		if($("#allDisabled").val()){
			go($("#actionCancelar").val());
			return;
		}
		
		go("Produto");
	});

	$(".campo-salvar").keypress(function(e){
	    if(e.which == 13) {
	        salvar();
	    }
	});
	
	if($("#mensagem").val() != ""){
		alerta($("#mensagem").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	}

	$("#imagem-product-ft").click(function(){
 		$("#imagem").click();
	});

	$("#imagem").change(function(){
		enviar_imagem(this);
	});

	$('#codIndustria').focus();
	
	$("#idProduto").addClass("disabled");
	refreshDisabled();
});

function enviar_imagem(input) {
  	if (input.files && input.files[0]) {
     	var reader = new FileReader();

        reader.onload = function (e) {
    		$('#imagem-product-ft').attr('src', e.target.result);
    	}

		reader.readAsDataURL(input.files[0]);
	}
}
	
function salvar(){
	removeClass();
	validaNcm();
}

function validaNcm(){
	$.ajax({
		type: "POST",
        data: { ncm:$("#ncmProdutoST").val() },
        url : 'validaNcm',
        success : function(data) {
        	if(data == "false"){
        		alerta("NCM não encontrado.", "warning");
        		addRequired("#divncm");
        	}else{
	       	 	if(!validaCamposObrigatorios()){
	        		alerta("Favor preencher os campos obrigatórios.", "warning");
	        	
	        	}else{
	        		$("#frmInserirProduto").submit();
	        	}
            }
        }
    });
}

function validaCamposObrigatorios(){
	var isValid = true;

	if($.trim($("#embalagemST").val()) == "-1")	
		isValid = addRequired("#divembalagem");

	isValid = getEmptyValidation("#divcodigoindustria",
								 "#diveandun",
								 "#divdescricao",
								 "#divncm",
								 "#divpesobruto",
								 "#divpesoliquido",
								 "#divqtd",
								 "#divaltura",
								 "#divlargura",
								 "#divprofundidade");
	 return isValid;
}

function removeClass(){
	$(".has-error").each(function(){ $(this).attr("class","form-group"); });
}

function format(){
	$("#pesoBruto").val(formatNum($("#pesoBruto").val));
	$("#pesoLiquido").val(formatNum($("#pesoLiquido").val));
	$("#alturaProduto").val(formatNum($("#alturaProduto").val));
	$("#larguraProduto").val(formatNum($("#larguraProduto").val));
	$("#profunProduto").val(formatNum($("#profunProduto").val));
}
</script>
<body>
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 

<input type="hidden" id="allDisabled" value="${allDisabled}" />
<input type="hidden" id="actionCancelar" value="${actionCancelar}" />
	
	<div id="content">	
		<div id="content-title">
			<h4>Produto</h4>
		</div>
		
		<div id="content-body">
			<form action="InserirProduto" id="frmInserirProduto" method="POST" enctype="multipart/form-data">
				<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				<div class="row">
					<div class="col-md-1">&nbsp;</div>
					<div class="col-md-10">
						<div class="row">
							<div class="col-md-3">
								<div class="form-group" id="divimagem">
							    	<div id="image-product">
							    		<img src="${produto.caminhoImagem}" height="100%" width="100%" id="imagem-product-ft" />
							    		<input type ="file" id="imagem" name ="imagem" style="visibility:hidden;" />
							    	</div>
							  	</div>
							</div>
							<div class="col-md-9">
								<div class="row">
				 					<div class="col-md-3">
										<div class="form-group" id="divcodigo">
									   		<label for="idProduto" class="control-label">Código</label>
									    	<input type="text" class="form-control campo-salvar" id="idProduto" name="idProduto" maxlength="100" value="${produto.idProduto}">
									  	</div>
				 					</div>
				 					<div class="col-md-3">
										<div class="form-group req" id="divcodigoindustria">
									   		<label for="codIndustria" class="control-label">Código da Indústria</label>
									    	<input type="text" class="form-control campo-salvar" id="codIndustria" name="codIndustria" maxlength="30" placeholder="Cód da indústria" value="${produto.codIndustria}">
									  	</div>
				 					</div>
				 					<div class="col-md-6">
										<div class="form-group req" id="diveandun">
									   		<label for="eanDunProduto" class="control-label">EAN/DUN</label>
									    	<input type="text" class="form-control campo-salvar" id="eanDunProduto" name="eanDunProduto" maxlength="50" placeholder="Insira EAN/DUN" value="${produto.eanDunProduto}">
									  	</div>
				 					</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group req" id="divdescricao">
									   		<label for="desProduto" class="control-label">Descrição</label>
									    	<input type="text" class="form-control campo-salvar" id="desProduto" name="desProduto" maxlength="100" placeholder="Insira a descrição do produto" value="${produto.desProduto}">
									  	</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group req" id="divncm">
							   		<label for="ncmProdutoST" class="control-label">NCM</label>
							    	<input type="text" class="form-control onlyNumber campo-salvar" id="ncmProdutoST" name="ncmProdutoST" maxlength="45" placeholder="Insira o NCM do produto" value="${produto.ncmProduto.codNcm}">
							  	</div>
							</div>
							<div class="col-md-3">
								<div class="form-group req" id="divpesobruto">
							   		<label for="pesoBruto" class="control-label">Peso Bruto</label>
							    	<input type="text" class="form-control onlyNumber campo-salvar" id="pesoBruto" name="pesoBruto" maxlength="11" placeholder="Insira o peso bruto" value="${produto.pesoBruto}">
							  	</div>
							</div>
							<div class="col-md-3">
								<div class="form-group req" id="divpesoliquido">
							   		<label for="pesoLiquido" class="control-label">Peso Líquido</label>
							    	<input type="text" class="form-control onlyNumber campo-salvar" id="pesoLiquido" name="pesoLiquido" maxlength="11" placeholder="Insira o peso líquido" value="${produto.pesoLiquido}">
							  	</div>
							</div>												
						</div>			
						<div class="row">
							<div class="col-md-4">
								<div class="form-group req" id="divembalagem">
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
								<div class="form-group req" id="divqtd">
							   		<label for="qtdEmbalagem" class="control-label">Qtd. Emb.</label>
							    	<input type="text" class="form-control onlyNumber campo-salvar" id="qtdEmbalagem" name="qtdEmbalagem" maxlength="11" placeholder="Qtd" value="${produto.qtdEmbalagem}">
							  	</div>
							</div>
							<div class="col-md-2">
								<div class="form-group req" id="divaltura">
							   		<label for="alturaProduto" class="control-label">Altura</label>
							    	<input type="text" class="form-control onlyNumber campo-salvar" id="alturaProduto" name="alturaProduto" maxlength="11" placeholder="Altura" value="${produto.alturaProduto}">
							  	</div>
							</div>
							<div class="col-md-2">
								<div class="form-group req" id="divlargura">
							   		<label for="larguraProduto" class="control-label">Largura</label>
							    	<input type="text" class="form-control onlyNumber campo-salvar" id="larguraProduto" name="larguraProduto" maxlength="11" placeholder="Largura" value="${produto.larguraProduto}">
							  	</div>
							</div>	
							<div class="col-md-2">
								<div class="form-group req" id="divprofundidade">
							   		<label for="profunProduto" class="control-label">Profundidade</label>
							    	<input type="text" class="form-control onlyNumber campo-salvar" id="profunProduto" name="profunProduto" maxlength="11" placeholder="Profundidade" value="${produto.profunProduto}">
							  	</div>
							</div>	
						</div>
						<div class="col-md-1">&nbsp;</div>
					</div>
				</div>
			</form>		
		</div>
	</div>
	
	<c:import url="/WEB-INF/views/components/footer.jsp">
		<c:param name="salvar" value="cancelar_salvar" />
	</c:import>
</body>
</html>
