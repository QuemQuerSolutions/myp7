<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	if($("#mensagem").val() !== ""){
		alerta($("#mensagem").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	}

	$("#btnNovo").click(function(e){
		e.stopPropagation();
		go("editarFornecedor");
	});

	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll();
		emptyTable("#tblFornecedor");
		$("#idFornecedor").focus();
	});

	$("#cnpjFornecedor").change(function(){
		$("#cnpjFornecedor").val(FormatarCnpjCPF($.trim($("#cnpjFornecedor").val())));
	});
	
	$("#pesquisar").click(function(e){
		e.stopPropagation();
		
		if( $("#cnpjFornecedor").val() !== "" &&!validarCNPJ($("#cnpjFornecedor").val())){
			alerta("CNPJ inv�lido.", "warning");
			return;
		}

		if($("#cnpjFornecedor").val() !== "")
			$("#cnpjFornecedor").val($("#cnpjFornecedor").val().replace(/[^\d]+/g,'')); //retirar a formataacao do cnpj
			
		go("#frmFornecedor");
	});
});

function onClickLine(id){
	go("editarFornecedor?id="+id);
}



</script>
<body>
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 
	
	<div id="content">
		<div id="content-title">
			<h4>Fornecedor</h4>
		</div>
		
		<div id="content-header">
			<div class="row">
				<div class="col-md-2">
					<label for="idFornecedor" class="control-label">C�digo</label>
				</div>
				<div class="col-md-2">
					<label for="cnpjFornecedor" class="control-label">CNPJ</label>
				</div>
				<div class="col-md-4">
					<label for="razao" class="control-label">Raz�o Social</label>
				</div>
			</div>
			
			<form action="carregaListaFornecedor" name="frmFornecedor" id="frmFornecedor" method="GET">
				<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				
				<div class="row">	
					<div class="col-md-2">
						<div class="form-group">
					    	<input type="text" 
					    		   class="form-control onlyNumber campo-buscar upper" 
					    		   id="idFornecedor" 
					    		   name="idFornecedor" 
					    		   maxlength="11" 
					    		   autofocus="autofocus"
					    		   placeholder="C�digo"
					    		   value="${idFornecedor}" />
					  	</div>
					</div>
					
					<div class="col-md-2">
						<div class="form-group" >
							<input type="text" 
								   class="form-control  campo-buscar upper" 
								   name="cnpjFornecedor"
								   placeholder="00.000.000/0000-00" 
								   id="cnpjFornecedor" 
								   maxlength="14" 
								   value="${cnpj}">
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="form-group" >
							<input type="text" 
								   class="form-control  campo-buscar upper" 
								   name="razao"
								   placeholder="Raz�o Social" 
								   id="razao" 
								   maxlength="100" 
								   value="${razao}">
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
			<table  class="table table-hover table-bordered table-striped mouse-click" id="tblFornecedor">
				<thead>
					<tr style="text-align: center">
						<th width="10%">C�digo</th>
						<th width="60%">Raz�o social</th>	
						<th width="15%">CNPJ</th>
						<th>Util. Tab. de Custo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lstFornecedor}" var="fornec">
						<tr class="${fornec.statusFornecedor eq 'A' ? 'registroAtivo' : 'registroInativo'}" 
							onclick="onClickLine('${fornec.idFornecedor}')">
							<td>${fornec.idFornecedor}</td>
							<td>${fornec.razao}</td>
							<td>${fornec.cnpjFormatado}</td>
							<td align="center">${fornec.utilTabCustoFornc eq 'S' ? 'Sim' : 'N�o'}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>
	<c:import url="/WEB-INF/views/components/footer.jsp">
		<c:param name="novo" value="novo_fornecedor" />
	</c:import>
</body>
</html>