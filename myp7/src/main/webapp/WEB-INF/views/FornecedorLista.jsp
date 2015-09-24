<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	if($("#mensagem").val() !== ""){
		alerta($("#mensagem").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	}

	$("#limpar").click(function(){
		$("#idFornecedor").val("");
		$("#cnpjFornecedor").val("");
	});

	$("#cnpjFornecedor").change(function(){
		$("#cnpjFornecedor").val(FormatarCnpj($.trim($("#cnpjFornecedor").val())));
	});
	
	$("#pesquisar").click(function(e){
		e.stopPropagation();
		if($("#idFornecedor").val() === "" && $("#cnpjFornecedor").val() === ""){
			alerta("Informe ao menos um filtro para buscar.", "warning");
			return;
		}

		if(($("#idFornecedor").val() !== "" && !$.isNumeric($.trim($("#idFornecedor").val())))){
			alerta("Somente números no filtro código..", "warning");
			return;
		}
				
		if( $("#cnpjFornecedor").val() !== "" &&!validarCNPJ($.trim($("#cnpjFornecedor").val()))){
			alerta("CNPJ inválido.", "warning");
			return;
		}
		go("#frmFornecedor");
	});
});

function onClickLine(id){
	
}


</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Fornecedor</h4>
		</div>
		
		<div id="content-header">
			<div class="row">
				<div class="col-md-2">
					<label for="idFornecedor" class="control-label">Código</label>
				</div>
				<div class="col-md-8">
					<label for="cnpjFornecedor" class="control-label">CNPJ</label>
				</div>
			</div>
			
			<form action="carregaListaFornecedor" name="frmFornecedor" id="frmFornecedor" method="GET">
				<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				<div class="row">	
					<div class="col-md-2">
						<div class="form-group" id="divCodFornecedor">
					    	<input type="text" 
					    		   class="form-control upper" 
					    		   id="idFornecedor" 
					    		   name="idFornecedor" 
					    		   maxlength="11" 
					    		   autofocus="autofocus"
					    		   placeholder="Código"
					    		   value="" />
					  	</div>
					</div>
					
					<div class="col-md-3">
						<div class="form-group" id="divCnpj">
							<input type="text" 
								   class="form-control" 
								   name="cnpjFornecedor"
								   placeholder="00.000.000/0000-00" 
								   id="cnpjFornecedor" 
								   maxlength="18" 
								   value="">
						</div>
					</div>
					<div class="col-md-4"></div>
					<div class="col-md-1" id="btnpesquisar">
						<div class="form-group">
							<button type="button" class="btn ${theme}" id="pesquisar">Pesquisar</button>
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
		
		<div id="content-body">
			<table  class="table table-hover table-bordered table-striped mouse-click">
				<thead>
					<tr style="text-align: center">
						<th width="10%">Código</th>
						<th width="60%">Razão social</th>	
						<th width="15%">CNPJ</th>
						<th>Utiliza Tabela de Custo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lstFornecedor}" var="fornec">
					
						<c:choose>
							<c:when test="${fornec.statusFornecedor eq 'A'}">
								<c:set var="classLine" value="registroAtivo" />
							</c:when>
							<c:otherwise>
								<c:set var="classLine" value="registroInativo" />
							</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${fornec.utilTabCustoFornc eq 'S'}">
								<c:set var="valorTD" value="SIM" />
							</c:when>
							<c:otherwise>
								<c:set var="valorTD" value="NÃO" />
							</c:otherwise>
						</c:choose>
						
						<tr class="${classLine}" onclick="onClickLine('${fornec.idFornecedor}')">
							<td>${fornec.idFornecedor}</td>
							<td>${fornec.razao}</td>
							<td></td>
							<td align="center">${valorTD}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>
	<c:import url="components/footer.jsp">
		<c:param name="novo" value="novo_fornecedor" />
	</c:import>
</body>
</html>