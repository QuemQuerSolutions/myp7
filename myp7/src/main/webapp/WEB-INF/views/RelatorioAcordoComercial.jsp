<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){

	$("#pesquisar").click(function(e){
		e.stopPropagation();
		go("#frmRelatorioEstoque");
	});
	
	$("input").keypress(function(e){
		e.stopPropagation();
		if(e.which == 13) go("#frmRelatorioEstoque");
	});

	$("#gerarPDF").click(function(e){
		e.stopPropagation();
		$("#titulo-modal").val("Relatório de Títulos");
		var link = "rptAcordoComercial?".concat("nroTitulo=", $("#nroTitulo").val(),
												"&situacao=", $("#situacao option:selected").val(),
												"&dataInclusaoDe=", $("#dataInclusaoDe").val(),
												"&dataInclusaoAte=", $("#dataInclusaoAte").val(),
												"&dataVencimentoDe=", $("#dataVencimentoDe").val(),
												"&dataVencimentoAte=", $("#dataVencimentoAte").val());
		$("#link-modal").val(link);
		$('#relatorioModalPDF').modal();
	});

	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll();
	});
	
	
	$("select#situacao option").each(function(){
		$(this).attr("selected", ($("#cmbSituacao").val() === $(this).val()));
	});
});

</script>
<body>
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Relatório de Títulos <span class="glyphicon glyphicon-file btn-pdf" title="Gerar PDF" id="gerarPDF"><label>PDF</label></span></h4>
		</div>
		
		<div id="content-header">
			<div class="row">
			</div>
			
			<form action="pesquisaRelatorioTitulo" id="frmRelatorioEstoque" method="POST">
				<div class="row">
					<div class="col-md-2 form-group">
						<label for="dataInclusaoDe">Data de Inclusão &nbsp;De&nbsp;</label>
				    	<input type="date" class="form-control" id="dataInclusaoDe" name="dataInclusaoDe" value="${obj.dataInclusaoDe}">
					</div>
					<div class="col-md-2 form-group">
						<label for="dataInclusaoAte">&nbsp;Até&nbsp;</label>
				    	<input type="date" class="form-control" id="dataInclusaoAte" name="dataInclusaoAte" value="${obj.dataInclusaoAte}">
				  	</div>
					<div class="col-md-2 form-group">
						<label for="dataVencimentoDe">Data de Vencimento &nbsp;De&nbsp;</label>
				    	<input type="date" class="form-control" id="dataVencimentoDe" name="dataVencimentoDe" value="${obj.dataVencimentoDe}">
					</div>
					<div class="col-md-2 form-group">
						<label for="dataVencimentoAte">&nbsp;Até&nbsp;</label>
				    	<input type="date" class="form-control" id="dataVencimentoAte" name="dataVencimentoAte" value="${obj.dataVencimentoAte}" >
				  	</div>
				</div>
				<div class="row">
					<div class="col-md-2 form-group">
						<label for="nroTitulo">Nro Título</label>
				    	<input type="number" class="form-control" id="nroTitulo" name="nroTitulo" min="0" max="99999" value="${obj.nroTitulo}">
				  	</div>
					<div class="col-md-2 form-group">
						<label for="situacao">Situação</label>
						<input type="hidden" id="cmbSituacao" value="${obj.situacao}">
						<select id="situacao" name="situacao" class="form-control">
				  			<option value="A">Aberto</option>
				  			<option value="Q">Quitado</option>
				  			<option value="P">Parcial Quitado</option>	
				  		</select>
				  	</div>
				  	<div class="col-md-4 form-group"></div>
					<div class="col-md-1 form-group" id="btnpesquisar">
						<label>&nbsp;</label>
						<button type="button" class="btn ${theme}" id="pesquisar">Pesquisar</button>
					</div>
					
					<div class="col-md-1 form-group" id="btnlimpar">
						<label>&nbsp;</label>
						<button type="button" class="btn btn-default limpar" data-dismiss="modal" id="limpar">Limpar</button>
					</div>
				</div>
			</form>
		</div>
		
		<div id="content-body">
			<table  class="table table-hover table-bordered table-striped relatorio">
				<thead>
					<tr>
						<th width="10%">Nro Título</th>
						<th width="10%">Espécie</th>
						<th width="10%">Comprador</th>
						<th width="20%">Representante</th>
						<th width="10%">Fornecedor</th>	
						<th width="10%">Data Inclusão</th>
						<th width="10%">Data Vencimento</th>
						<th width="10%">Valor</th>
						<th width="10%">Situação</th>
					</tr>
				</thead>
				<tbody>
					<fmt:setLocale value="pt_BR"/>
					<c:forEach items="${lstRelatorio}" var="r">
						<tr>
							<fmt:formatDate value="${r.dataInclusao}" pattern="dd/MM/yyyy" var="dataInclusao"/>
							<fmt:formatDate value="${r.dataVencimento}" pattern="dd/MM/yyyy" var="dataVencimento"/>
							<fmt:formatNumber value="${r.valor}" type="currency" var="valor"/>
							
							<td>${r.nroTitulo}</td>
							<td>${r.especie}</td>
							<td class="truncText">${r.comprador}</td>
							<td class="truncText">${r.representante}</td>
							<td class="truncText">${r.fornecedor}</td>
							<td><c:out value="${dataInclusao}" /></td>
							<td><c:out value="${dataVencimento}" /></td>
							<td>${valor}</td>
							<td>${r.situacao}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>
	<c:import url="RelatorioModalPDF.jsp"/>
	<c:import url="/WEB-INF/views/components/footer.jsp"/>
</body>
</html>