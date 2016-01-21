<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	
	$("#pesquisar").click(function(e){
		e.stopPropagation();
		pesquisar();
	});
	
	$("input").keypress(function(e){
		e.stopPropagation();
		if(e.which == 13) pesquisar();
	});

	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll();
		$("#idPessoa").val(-1);
		$("#id").focus();
	});

	function pesquisar(){
		$.ajax({
		  type: "GET",
		  url: "pesquisaRelatorioEstoque",
		  data: {idProduto: $("#idProduto").val(), descProduto: $("#descProduto").val(), idPessoa: $("#idPessoa").val()},
		  contentType: "application/json; charset=ISO-8859-1",
		  dataType: "json",
		  success: function(lista) {
			    var lines = "";
			    
			    if(lista.length == 0){
			    	$("#lstRelatorioEstoque").html("<tr><td colspan='7'>Nenhum registro encontrado</td></tr>");
			    	return;
			    }
			    
			    if(lista[0].codRetorno == -1){
			    	alerta(lista[0].msgRetorno, "warning");
			    	$("#lstRelatorioEstoque").html("");
			    	return;
			    }
			    
			    lista.forEach(function(relatEstoque){
			    	lines += getLine(relatEstoque);
			    });
			    
			    $("#lstRelatorioEstoque").html(lines);
		  },
		  error: function (xhr, textStatus, errorThrown) {
		  	console.log("Erro ao retornar lista: ",errorThrown);
		      alerta("Erro ao retornar lista","warning");
		  }
		});	
	}	

	function getLine(relatEstoque){
		var line = "";
		line = line.concat("<tr>");
		
		line = line.concat("<td>", relatEstoque.produto ,	"</td>");
		line = line.concat("<td>", relatEstoque.qtdEstoque ,"</td>");
		line = line.concat("<td>", relatEstoque.mediaVendaDia ,"</td>");
		line = line.concat("<td>", relatEstoque.dataUltimaCompra,"</td>");
		line = line.concat("<td>", relatEstoque.comprador ,"</td>");	
		line = line.concat("<td>", relatEstoque.representante ,"</td>");
		line = line.concat("<td>", relatEstoque.fornecedor ,"</td>");
		
		line = line.concat("</tr>");
		
		return line;
	}

	$("#gerarPDF").click(function(e){
		e.stopPropagation();
		$("#titulo-modal").val("Relatório de Estoque");
		var link = "rpdEstoque?".concat("idProduto=", $("#idProduto").val(),
												"&produto=", $("#descProduto").val(),
												"&representante=", $("#idPessoa option:selected").val());
		$("#link-modal").val(link);
		$('#relatorioModalPDF').modal();
	});
	
});

</script>
<body>
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Relatório de Estoque <span class="glyphicon glyphicon-file btn-pdf" title="Gerar PDF" id="gerarPDF"><label>PDF</label></span></h4>
		</div>
		
		<div id="content-header">
			<div class="row">
				<div class="col-md-3">
					<label for="empresa" class="control-label">Código Produto</label>
				</div>
				<div class="col-md-3">
					<label for="produto" class="control-label">Descrição Produto</label>
				</div>
				<div class="col-md-3">
					<label for="produto" class="control-label" >${tituloPessoa}</label>
				</div>
			</div>
			
			<form id="frmRelatorioEstoque" method="GET">
				
				<div class="row">	
					<div class="col-md-3">
						<div class="form-group">
					    	<input type="number" class="form-control" id="idProduto" maxlength="11" value="">
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
					    	<input type="text" class="form-control" id="descProduto" maxlength="11" value="">
					  	</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<select id="idPessoa" name="idPessoa" class="form-control" autofocus="autofocus">
					  			<option value="-1">--Selecione--</option>
					  			<c:forEach var="pessoa" items="${pessoas}">
					  				<option value="${pessoa.idCombo}">${pessoa.descricaoCombo}</option>
	   							</c:forEach>				  			
					  		</select>
					  	</div>
					</div>					
					
					<div class="col-md-1" id="btnpesquisar">
						<div class="form-group">
							<button type="button" class="btn ${theme}" id="pesquisar">Pesquisar</button>
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
			<table  class="table table-hover table-bordered table-striped relatorio">
				<thead>
					<tr>
						<th width="10%">Produto</th>
						<th width="10%">Qtd Estoque</th>
						<th width="10%">Média Venda Dia</th>
						<th width="10%">Data Última Compra</th>
						<th width="20%">Comprador</th>
						<th width="20%">Representante</th>
						<th width="20%">Fornecedor</th>
					</tr>
				</thead>
				<tbody id="lstRelatorioEstoque"></tbody>
			</table>
		</div>
	
	</div>
	<c:import url="RelatorioModalPDF.jsp"/>
	<c:import url="/WEB-INF/views/components/footer.jsp"/>
</body>
</html>