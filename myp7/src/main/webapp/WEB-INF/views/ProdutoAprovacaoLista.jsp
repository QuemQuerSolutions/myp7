<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	
	if($("#filtro").val() !== ""){
		var filtro = $("#filtro").val().split(";");
		
		addLineRepresentanteTab({idRepresentante: filtro[0], apelido: filtro[1]});
		
	 	$("input[name=situacoes]").parent().removeClass("active");
	 	$("input[name=situacoes]").attr("checked", false);
		$("#"+filtro[2]).parent().addClass("active");
		$("#"+filtro[2]).attr("checked", true);

		$("#idProduto").val(filtro[3]);
		$("#descricao").val(filtro[4]);
		
		pesquisar();
	}
		
	
	$("#pesquisar").click(function(e){
		e.stopPropagation();
		if(!isValidRequired()){
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
		$("#lstProdutoAprovacao").html("");
	});
	
	$("#buscaRepresentante").click(function(e){
		e.stopPropagation();
		$("#consulta_representante").modal();	
	});
	
});

function pesquisar(){
	var filtroBusca = {idUsuario: 	$("#idRepresentante").val(),
			  situacao: 	$("input[name=situacoes]:checked").attr("id"),
			  idProduto:	$("#idProduto").val(),
			  desProduto:	$("#descricao").val()};

	$.ajax({
	  type: "GET",
	  url: "obterProdutoAprovacao",
	  data: filtroBusca,
	  contentType: "application/json; charset=ISO-8859-1",
	  dataType: "json",
	  success: function(lista) {
		    var lines = "";
		    
		    if(lista.length == 0){
		    	$("#lstProdutoAprovacao").html("<tr><td colspan='15'>Nenhum registro encontrado</td></tr>");
		    	return;
		    }
		    
		    if(lista[0].codRetorno == -1){
		    	alerta(lista[0].msgRetorno, "warning");
		    	$("#lstProdutoAprovacao").html("");
		    	return;
		    }
		    
		    lista.forEach(function(produto){
		    	lines += getLineAprovacao(produto);
		    });
		    
		    $("#lstProdutoAprovacao").html(lines);
	  },
	  error: function (xhr, textStatus, errorThrown) {
	  	console.log("Erro ao retornar lista: ",errorThrown);
	      alerta("Erro ao retornar lista","warning");
	  }
	});	
}

function getLineAprovacao(produto){
	var line = "";

	line = line.concat("<tr>");
	line = line.concat("<td onclick='onClickLine(", produto.idProduto ,")'>", produto.eanDunProduto ,"</td>");
// 	line = line.concat("<td>", produto.eanDunProduto ,"</td>");
	line = line.concat("<td onclick='onClickLine(", produto.idProduto ,")'>", produto.desProduto ,"</td>");
	line = line.concat("<td onclick='onClickLine(", produto.idProduto ,")'>", produto.descSituacao ,"</td>");
	
	line = line.concat("<td align='center'>");
	//Se a situacao for aguardando
	if(produto.situacao === "G"){
		line = line.concat( "<a href='#' onclick=\"onClickAprovar('", produto.idProduto , "')\">",
								"<span class='glyphicon glyphicon-ok' title='Aprovar' aria-hidden='true'></span>",
							"</a>&nbsp;&nbsp;&nbsp;");
		line = line.concat( "<a href='#' class='red' onclick=\"onClickReprovar('", produto.idProduto , "')\">",
								"<span class='glyphicon glyphicon-remove' title='Reprovar' aria-hidden='true'></span>",
							"</a>");
	}else
		line = line.concat( "<a href='#' class='preto' onclick='onClickBlank()'>",
								"<span class='glyphicon glyphicon-asterisk' title='Esse produto não pode ser alterado' aria-hidden='true'></span>",
							"</a>");
	line = line.concat("</td>");
	
	line = line.concat("</tr>");
	
	return line;
}

function onClickLine(idProduto){
	$("#codProduto").val(idProduto);
	$("#allDisabled").val(true);
	$("#actionCancelar").val("ProdutoAprovacao");
	
	var filtro = "";
	filtro = filtro.concat(	$("#idRepresentante").val(), ";",
						 	$("#representante").val(), ";",
						 	$("input[name=situacoes]:checked").attr("id"), ";",
						 	$("#idProduto").val(), ";",
						 	$("#descricao").val());
	
	$("#filtroAnterior").val(filtro);
	
	go("#frmEditarProduto");
}

function onClickReprovar(idProduto){
	
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
		$.get("reprovarProduto?idProduto=".concat(idProduto), function(){
			pesquisar();
			var qtdAguardando = parseInt($("#qtdAguardando").text());
			var qtdReprovado = parseInt($("#qtdReprovado").text());
			
			$("#qtdAguardando").text(--qtdAguardando);
			$("#qtdReprovado").text(++qtdReprovado);
			swal("Produto reprovado com sucesso", "", "success");
		});
	});
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
		$.get("aprovarProduto?idProduto=".concat(idProduto), function(){
			pesquisar();
			var qtdAguardando = parseInt($("#qtdAguardando").text());
			var qtdAprovado = parseInt($("#qtdAprovado").text());
			
			$("#qtdAguardando").text(--qtdAguardando);
			$("#qtdAprovado").text(++qtdAprovado);
			swal("Produto aprovado com sucesso", "", "success");
		});
	});
}

function onClickBlank(){
	alerta("Esse produto não pode ser alterado","warning");
}

function addLineRepresentanteTab(representante){
	var id = representante.idRepresentante;
	$("#idRepresentante").val(id);
	$("#representante").val(representante.apelido);
	
	$.ajax({
	    type: "GET",
	    url: "obterQtdPorSituacao?idUsuario=".concat(id),
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
			<h4>Aprovação de Produtos</h4>
		</div>
		
		<form action="EditarProduto" id="frmEditarProduto" method="post" >
			<input type="hidden" id="codProduto" name="codProduto" />
			<input type="hidden" id="allDisabled" name="allDisabled" />
			<input type="hidden" id="actionCancelar" name="actionCancelar" />
			<input type="hidden" id="filtroAnterior" name="filtroAnterior" />	
		</form>
		
		<form action="CarregaListaProdutoAprovacao" id="frmAprovacaoProduto" method="GET">
			<input type="hidden" id="mensagemRetorno" value="${mensagemRetorno}" />
			<input type="hidden" id="codMsgem" value="${codMsgem}" />
			<input type="hidden" id="theme" value="${theme}" />
			<input type="hidden" id="filtro" value="${filtro}" />
		
		<div id="content-header">
			<div class="row">
				<div class="col-md-4 req">
					<label for="representante" >Representante</label>
				</div>
				<div class="col-md-1">
					<label for="buscaRepresentante">&nbsp;</label>
				</div>
				<div class="col-md-7">
					<label for="situacao">Situação</label>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 form-group req">
					<input type="hidden" id="idRepresentante">
					<input type="text" class="form-control" readonly="readonly" id="representante"/>
				</div>
				<div class="col-md-1 form-group paddingleft0">
				  	<a href="#" target="_self" class="form-control icon-search" id="buscaRepresentante"><span class="glyphicon glyphicon-search"></span></a>
				</div>
				<div class="col-md-7 form-group btn-group" data-toggle="buttons">
					<label class="btn ${theme} active">
    					<input type="radio" name="situacoes" id="aguardando" autocomplete="off" checked> Aguardando Aprovação 
    					<span id="qtdAguardando" class="badge">0</span>
  					</label>
					
					<label class="btn ${theme}">
    					<input type="radio" name="situacoes" id="aprovado" autocomplete="off"> Aprovado 
    					<span id="qtdAprovado" class="badge">0</span>
  					</label>
  					<label class="btn ${theme}">
    					<input type="radio" name="situacoes" id="reprovado" autocomplete="off"> Reprovado 
    					<span id="qtdReprovado" class="badge">0</span>
  					</label>

					<label class="btn ${theme}">
					    <input type="radio" name="situacoes" id="integrado" autocomplete="off"> Integrado 
					    <span id="qtdIntegrado" class="badge">0</span>
					  </label>
  					
					<label class="btn ${theme}">
    					<input type="radio" name="situacoes" id="todos" autocomplete="off"> Todos 
    					<span id="qtdTodos" class="badge">0</span>
  					</label>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-1">
					<label for="idProduto">Código</label>
				</div>
				<div class="col-md-8">
					<label for="descricao">Descrição</label>
				</div>
			</div>
				
			<div class="row">	
				<div class="col-md-1 form-group">
			    	<input type="text" 
			    			class="form-control" 
			    			id="idProduto" 
			    			name="idProduto" 
			    			maxlength="11" />
				</div>
				
				<div class="col-md-8 form-group">
			    	<input type="text" 
			    			class="form-control" 
			    			id="descricao" 
			    			name="descricao" 
			    			maxlength="45"/>
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
						<th width="15%">Código EAN</th>
<!-- 						<th width="15%">Código Import</th> -->
						<th width="60%">Descrição</th>
						<th width="15%">Situação</th>
						<th width="10%" class="text-center">Ação</th>
					</tr>
				</thead>
				<tbody id="lstProdutoAprovacao"></tbody>
			</table>
		</div>
	
	</div>
	<c:import url="RepresentanteModalLista.jsp"/>
	<c:import url="/WEB-INF/views/components/footer.jsp" />
</body>
</html>