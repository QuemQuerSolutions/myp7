<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
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

	$("#btnAprovar").click(function(){
		pupulaArraySelecionados();
		onClickAprovar(pupulaArraySelecionados());
	});

	$("#btnReprovar").click(function(){
		pupulaArraySelecionados();
		onClickReprovar(pupulaArraySelecionados());
	});

	function pupulaArraySelecionados(){
		var arraySelecionados = new Array();
		
		$('.cbAprovacao').each(function(){
			if($(this).prop('checked'))
				arraySelecionados.push($(this).attr("idLinha"));
		});

		$("#cbTodos").prop('checked', false);

		return arraySelecionados;
	}

	$(".pesquisar").change(function(e){
		if(isValidRequired())
			pesquisar();
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

	$("#cbTodos").click(function(e){
		$('.cbAprovacao').each(function(){
			if($('#cbTodos').prop('checked'))
				$(this).prop('checked', true);
			else
				$(this).prop('checked', false);
		});
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
	
	if(custo.situacao.indexOf("Aguard.") > -1)
		line = line.concat("<td class='centralizar-componente'><input type='checkbox' class='cbAprovacao' idLinha=", custo.idTabCustoFornecedor ," id='cb", custo.idTabCustoFornecedor ,"'></td>");
	else
		line = line.concat("<td class='centralizar-componente'><span class='glyphicon glyphicon-asterisk' title='Esse custo não pode ser alterado' aria-hidden='true'></span></td>");
	
	line = line.concat("<td>", custo.produto.eanDunProduto ,"</td>");
	line = line.concat("<td>", custo.produto.desProduto ,"</td>");
	line = line.concat("<td>", custo.valorAnteriorFormatado ,"</td>");
	line = line.concat("<td>", custo.valorFormatado ,"</td>");
	line = line.concat("<td align='center'>", custo.situacao,"</td>");
	
	line = line.concat("</tr>");
	
	return line;
}

function onClickAprovar(idTabCustoFornecedor){
	if(idTabCustoFornecedor.length > 0){
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
			$.get("aprovarFornecedorCusto?idFornecedorCusto=".concat(idTabCustoFornecedor), function(){
				pesquisar();
				var qtdAguardando = parseInt($("#qtdAguardando").text());
				var qtdAprovado = parseInt($("#qtdAprovado").text());
	
				$.each(idTabCustoFornecedor, function() {
					$("#qtdAguardando").text(--qtdAguardando);
					$("#qtdAprovado").text(++qtdAprovado);
				});
				swal("Custo aprovado com sucesso", "", "success");
			});
		});
	}else{
		swal("Selecione ao menos uma linha", "", "warning");
	}
}


function onClickReprovar(idTabCustoFornecedor){
	if(idTabCustoFornecedor.length > 0){
		swal({
			title : "",
			text : "Tem certeza que deseja Reprovar?",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#F0AD4E",
			confirmButtonText : "Reprovar",
			cancelButtonText: "Cancelar",
			closeOnConfirm : false,
			html: false
		},
		function() {
			$.get("reprovarFornecedorCusto?idFornecedorCusto=".concat(idTabCustoFornecedor), function(){
				pesquisar();
				var qtdAguardando = parseInt($("#qtdAguardando").text());
				var qtdReprovado = parseInt($("#qtdReprovado").text());
	
				$.each(idTabCustoFornecedor, function() {
					$("#qtdAguardando").text(--qtdAguardando);
					$("#qtdReprovado").text(++qtdReprovado);
				});
				swal("Produto reprovado com sucesso", "", "success");
			});
		});
	}else{
		swal("Selecione ao menos uma linha", "", "warning");
	}
}

function onClickBlank(){
	alerta("Esse custo não pode ser alterado","warning");
}

function addLineFornecedor(fornecedor){
	var id = fornecedor.idFornecedor;
	$("#idFornecedor").val(id);
	$("#razao").val(fornecedor.razao);
	
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
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 

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
					<div class="col-md-4 req">
						<label for="fornecedor" >Fornecedor</label>
					</div>
					<div class="col-md-1">
						<label for="buscaFornecedor">&nbsp;</label>
					</div>
					<div class="col-md-2 req">
						<label for="uf">UF</label>
					</div>
					<div class="col-md-5 req">
						<label for="idEmpresa">Empresa</label>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 form-group req">
						<input type="hidden" id="idFornecedor" name="idFornecedor"value="">
						<input type="text" class="form-control" readonly="readonly" id="razao" value=""/>
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
	  					<label class="btn ${theme} active pesquisar">
	    					<input type="radio" name="situacoes" id="aguardando" autocomplete="off" checked> Aguardando Aprovação 
	    					<span id="qtdAguardando" class="badge">0</span>
	  					</label>

						<label class="btn ${theme} pesquisar">
	    					<input type="radio" name="situacoes" id="aprovado" autocomplete="off"> Aprovado 
	    					<span id="qtdAprovado" class="badge">0</span>
	  					</label>
	  					<label class="btn ${theme} pesquisar">
	    					<input type="radio" name="situacoes" id="reprovado" autocomplete="off"> Reprovado 
	    					<span id="qtdReprovado" class="badge">0</span>
	  					</label>
						<label class="btn ${theme} pesquisar">
						    <input type="radio" name="situacoes" id="integrado" autocomplete="off"> Integrado 
						    <span id="qtdIntegrado" class="badge">0</span>
						  </label>

						<label class="btn ${theme} pesquisar">
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
						<th width="5%" class="centralizar-componente">
							<input type="checkbox" id="cbTodos">
						</th>
						<th width="25%">Código EAN</th>
						<th width="30%">Descrição</th>
						<th width="10%">Valor Anterior</th>
						<th width="10%">Valor novo</th>
						<th width="20%" class="text-center">Situação</th>
					</tr>
				</thead>
				<tbody id="lstCustoAprovacao"></tbody>
			</table>
		</div>
	
	</div>
	
	<c:import url="FornecedorModalLista.jsp"/>
	
	<c:import url="/WEB-INF/views/components/footer.jsp">
		<c:param name="salvar" value="reprovar_aprovar" />
	</c:import>
</body>
</html>