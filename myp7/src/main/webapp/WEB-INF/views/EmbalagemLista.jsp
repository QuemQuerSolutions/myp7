<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	$("#btnNovo").click(function(){
		$(this).attr('data-toggle','modal');
		$(this).attr('data-target','#nova_embalagem');
	});

	if($("#mensagem").val() !== ""){
		alerta($("#mensagem").val(), $("#codMsgem").val() == "0" ? "success" :"warning");

		if($("#outraPagina").val() !== ""){
			$("#btnNovo").click();
		}
	}

	$("#limpar").click(function(){
		$("#siglaEmbalagem").val("");
		$("#nomeEmbalagem").val("");
	});
	
});

function onClickLine(id,sigla,nome,qtd){
	$("#btnNovo").click();
	
	$("#idEmbalagem").val(id);
	$("#sigla").val(sigla);
	$("#descricao").val(nome);
	$("#quantidade").val(qtd);
}


</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Embalagem</h4>
		</div>
		
		<div id="content-header">
			<div class="row">
				<div class="col-md-1">
					<label for="sigla" class="control-label">Sigla</label>
				</div>
				<div class="col-md-8">
					<label for="descricao" class="control-label">Descrição</label>
				</div>
			</div>
			
			<form action="CarregaListaEmbalagem" name="frmEmbalagem" method="GET">
				<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				<input type="hidden" id="outraPagina" value="${outraPagina}" />
				
				<div class="row">	
					<div class="col-md-1">
						<div class="form-group" id="divsigla">
					    	<input type="text" 
					    			class="form-control upper" 
					    			id="siglaEmbalagem" 
					    			name="siglaEmbalagem" 
					    			maxlength="2" 
					    			autofocus="autofocus"
					    			value="${emb.siglaEmbalagem}" />
					  	</div>
					</div>
					
					<div class="col-md-7">
						<div class="form-group" id="divdescricao">
					    	<input type="text" 
					    			class="form-control" 
					    			id="nomeEmbalagem" 
					    			name="nomeEmbalagem" 
					    			maxlength="100" 
					    			value="${emb.nomeEmbalagem}"/>
					  	</div>
					</div>
					
					<div class="col-md-1" id="btnpesquisar">
						<div class="form-group">
							<button type="submit" class="btn btn-warning" id="pesquisar">Pesquisar</button>
						</div>
					</div>
					
					<div class="col-md-1" id="btnlimpar">
						<div class="form-group">
							<button type="button" class="btn btn-default limpar" data-dismiss="modal" id="limpar">Limpar</button>
						</div>
					</div>
					
				</div>
			</form>
		</div>
		
		<div id="content-body">
			<table  class="table table-hover table-bordered table-striped mouse-click">
				<thead>
					<tr style="text-align: center">
						<th>Sigla</th>
						<th>Descrição</th>
						<th>Quantidade</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lista}" var="e">
						<tr onclick="onClickLine('${e.idEmbalagem}','${e.siglaEmbalagem}','${e.nomeEmbalagem}','${e.qtdEmbalagem}')">
							<td>${e.siglaEmbalagem}</td>
							<td>${e.nomeEmbalagem}</td>
							<td>${e.qtdEmbalagem}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:import url="EmbalagemSalvar.jsp" />
	
	</div>
	<c:import url="components/footer.jsp">
		<c:param name="novo" value="nova_embalagem" />
	</c:import>
</body>
</html>