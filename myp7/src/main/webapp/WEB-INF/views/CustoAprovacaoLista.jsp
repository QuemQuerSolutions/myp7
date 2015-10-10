<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	$("#idEmpresa").attr("disabled", true);

	$("#uf").change(function(){
		if($("#uf").val() != "-1"){
			$("#idEmpresa").removeAttr("disabled");
			atualizarComboEmpresa();
		}else{
			$("#idEmpresa").html("<option value='-1'>Selecione uma Empresa</option>");
			$("#idEmpresa").attr("disabled", true);
		}
	});

	function atualizarComboEmpresa(){
		$.ajax({
			type: "POST",
	        data: { uf:$("#uf").val() },
	        url : 'consultaEmpresaPorUF',
	        success : function(data) {
	        	$("#idEmpresa").html(data);
	        }
	    });
	}	
	
	$("#pesquisar").click(function(e){
		e.stopPropagation();
		if(!isValidRequired() || !isValidSelectRequired()){
			alerta("Preencha os campos obrigatórios.", "warning");
			return;
		}
		pesquisar();
	});
	
	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll();
		$(".badge").text(0);
		$("input[name=situacoes]").parent().removeClass("active");
		$("#aguardando").parent().addClass("active");
		$("#lstCustoAprovacao").html("");
	});
	
	$("#buscaFornecedor").click(function(e){
		e.stopPropagation();
		$("#consulta_fornecedor").modal();	
	});
	
});

function pesquisar(){
	var fornecedorCusto = {
				situacao: 		$("input[name=situacoes]:checked").attr("id"),
				codigo:			$("#codigo").val(),
				idEmpresa:		$("#idEmpresa").val(),
				tipo:			$("#tipo").val(),
				desProduto:		$("#desProduto").val(),
				idFornecedor: 	$("#idFornecedor").val()};

	$.ajax({
	  type: "GET",
	  url: "obterFornecedorCustoAprovacao",
	  data: fornecedorCusto,
	  contentType: "application/json; charset=ISO-8859-1",
	  dataType: "json",
	  success: function(lista) {
		    var lines = "";
		    
		    if(lista.length == 0){
		    	$("#lstCustoAprovacao").html("<tr><td colspan='15'>Nenhum registro encontrado</td></tr>");
		    	return;
		    }
		    
		    if(lista[0].codRetorno == -1){
		    	alerta(lista[0].msgRetorno, "warning");
		    	$("#lstCustoAprovacao").html("");
		    	return;
		    }
		    
		    lista.forEach(function(custo){
		    	lines += getLineAprovacao(custo);
		    });
		    
		    $("#lstCustoAprovacao").html(lines);
	  },
	  error: function (xhr, textStatus, errorThrown) {
	  	console.log("Erro ao retornar lista: ",errorThrown);
	      alerta("Erro ao retornar lista","warning");
	  }
	});	
}

function getLineAprovacao(custo){
	var line = "";

	line = line.concat("<tr>");
	line = line.concat("<td>", custo.produto.idProduto ,"</td>");
	line = line.concat("<td>", custo.desProduto ,"</td>");
	line = line.concat("<td>", custo.valorFormatado ,"</td>");
	line = line.concat("<td>", custo.situacao ,"</td>");
	
	line = line.concat("<td align='center'>");
	//Se a situacao for aguardando
	if(custo.situacao === "G")
		line = line.concat( "<a href='#' class='preto' onclick=\"onClickAprovar('", custo.produto.idProduto , "')\">",
								"<span class='glyphicon glyphicon-ok' title='Aprovar' aria-hidden='true'></span>",
							"</a>");
	else
		line = line.concat( "<a href='#' class='preto' onclick='onClickBlank()'>",
								"<span class='glyphicon glyphicon-asterisk' title='Esse custo não pode ser alterado' aria-hidden='true'></span>",
							"</a>");
	line = line.concat("</td>");
	
	line = line.concat("</tr>");
	
	return line;
}

function onClickAprovar(idProduto){
	
	swal({
		title : "",
		text : "Tem certeza que deseja Aprovar?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#F0AD4E",
		confirmButtonText : "Aprovar",
		cancelButtonText: "Cancelar",
		closeOnConfirm : false,
		html: false
	},
	function() {
		$.get("aprovarFornecedorCusto?idProduto=".concat(idProduto), function(){
			pesquisar();
			var qtdAguardando = parseInt($("#qtdAguardando").text());
			var qtdAprovado = parseInt($("#qtdAprovado").text());
			
			$("#qtdAguardando").text(--qtdAguardando);
			$("#qtdAprovado").text(++qtdAprovado);
			swal("Custo aprovado com sucesso", "", "success");
		});
	});
}

function onClickBlank(){
	alerta("Esse custo não pode ser alterado","warning");
}

function addLineRepresentanteTab(representante){
	var id = representante.idRepresentante;
	$("#idFornecedor").val(id);
	$("#fornecedor").val(representante.apelido);
	
	$.ajax({
	    type: "GET",
	    url: "obterQtdFornecedorCustoPorSituacao?idUsuario=".concat(id),
	    contentType: "application/json; charset=ISO-8859-1",
	    dataType: "json",
	    success: function(data) {
	    	var total = 0;
	    	data.forEach(function(item){
	    		total += item.qtdPorSituacao;
	    		switch (item.situacao) {
					case "I": $("#qtdIntegrado").text(item.qtdPorSituacao); break;
					case "A": $("#qtdAprovado").text(item.qtdPorSituacao); break;
					case "R": $("#qtdReprovado").text(item.qtdPorSituacao); break;
					case "G": $("#qtdAguardando").text(item.qtdPorSituacao); break;
					default: break;
				}
	    		$("#qtdTodos").text(total);
	    	});
	    }
	});
}

</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Aprovação de Custos</h4>
		</div>
		
		<form action="CarregaListaCustoAprovacao" id="frmAprovacaoCusto" method="GET">
			<input type="hidden" id="mensagemRetorno" value="${mensagemRetorno}" />
			<input type="hidden" id="codMsgem" value="${codMsgem}" />
			<input type="hidden" id="theme" value="${theme}" />
		
			<div id="content-header">
				<div class="row">
					<div class="col-md-4">
						<label for="fornecedor" >Fornecedor</label>
					</div>
					<div class="col-md-1">
						<label for="buscaFornecedor">&nbsp;</label>
					</div>
					<div class="col-md-2">
						<label for="uf">UF</label>
					</div>
					<div class="col-md-5">
						<label for="idEmpresa">Empresa</label>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 form-group req">
						<input type="hidden" id="idFornecedor" value=1>
						<input type="text" class="form-control" readonly="readonly" id="fornecedor" value="Teste"/>
					</div>
					<div class="col-md-1 form-group paddingleft0">
					  	<a href="#" target="_self" class="form-control icon-search" id="buscaFornecedor"><span class="glyphicon glyphicon-search"></span></a>
					</div>
					<div class="col-md-2 form-group req">
						<select id="uf" name="uf" class="form-control" autofocus="autofocus">
				  			<option value="-1">-</option>
				  			<c:forEach var="uf" items="${ufs}">
				  				<option value="${uf}">${uf}</option>
   							</c:forEach>				  			
				  		</select>
					</div>
					<div class="col-md-5 form-group req">
						<select id="idEmpresa" name="empresa" class="form-control" autofocus="autofocus">
				  			<option value="-1">Selecione uma Empresa</option>
				  		</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<label for="tipo">Tipo</label>
					</div>			
					<div class="col-md-1">
						<label for="codigo">Código</label>
					</div>
					<div class="col-md-5">
						<label for="desProduto">Descrição</label>
					</div>
				</div>
					
				<div class="row">	
					<div class="col-md-3 form-group">
						<select id="tipo" name="tipo" class="form-control">
							<c:forEach var="filtro" items="${filtros}">
								<option value="${filtro.key}">${filtro.value}</option>
							</c:forEach>
						</select>				  		
			   		</div>
				
					<div class="col-md-1 form-group">
				    	<input type="text" 
				    			class="form-control" 
				    			id="codigo" 
				    			name="codigo" 
				    			maxlength="11" />
					</div>
					
					<div class="col-md-8 form-group">
				    	<input type="text" 
				    			class="form-control" 
				    			id="desProduto" 
				    			name="desProduto" 
				    			maxlength="45"/>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-7">
						<label for="situacao">Situação</label>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-9 form-group btn-group" data-toggle="buttons">
						<label class="btn ${theme}">
						    <input type="radio" name="situacoes" id="integrado" autocomplete="off"> Integrado 
						    <span id="qtdIntegrado" class="badge">0</span>
						  </label>
						<label class="btn ${theme}">
	    					<input type="radio" name="situacoes" id="aprovado" autocomplete="off"> Aprovado 
	    					<span id="qtdAprovado" class="badge">0</span>
	  					</label>
	  					<label class="btn ${theme}">
	    					<input type="radio" name="situacoes" id="reprovado" autocomplete="off"> Reprovado 
	    					<span id="qtdReprovado" class="badge">0</span>
	  					</label>
	  					<label class="btn ${theme} active">
	    					<input type="radio" name="situacoes" id="aguardando" autocomplete="off" checked> Aguardando Aprovação 
	    					<span id="qtdAguardando" class="badge">0</span>
	  					</label>
						<label class="btn ${theme}">
	    					<input type="radio" name="situacoes" id="todos" autocomplete="off"> Todos 
	    					<span id="qtdTodos" class="badge">0</span>
	  					</label>
					</div>			
				
					<div class="col-md-1 form-group" id="btnpesquisar">
						<button type="button" class="btn ${theme}" id="pesquisar">Pesquisar</button>
					</div>
					
					<div class="col-md-1 form-group" id="btnlimpar">
						<button type="button" class="btn btn-default limpar" id="limpar">Limpar</button>
					</div>
				</div>
			</div>
		</form>
		
		<div id="content-body">
			<table  class="table table-hover table-bordered table-striped mouse-click">
				<thead>
					<tr>
						<th width="15%">Código Produto</th>
						<th width="45%">Nome Produto</th>
						<th width="15%">Valor</th>
						<th width="15%">Situação</th>
						<th width="10%" class="text-center">Aprovar</th>
					</tr>
				</thead>
				<tbody id="lstCustoAprovacao"></tbody>
			</table>
		</div>
	
	</div>
	<c:import url="RepresentanteModalLista.jsp"/>
	<c:import url="components/footer.jsp" />
</body>
</html>