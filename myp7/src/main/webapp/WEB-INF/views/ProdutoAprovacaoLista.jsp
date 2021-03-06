<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	
	if($("#filtro").val() !== ""){
		var filtro = $("#filtro").val().split(";");
		
		addLineRepresentanteTab({idRepresentante: filtro[0], idUsuario: filtro[1], apelido: filtro[2]});
	 	$("input[name=situacao]").parent().removeClass("active");
	 	$("input[name=situacao]").attr("checked", false);
		$("#"+filtro[3]).parent().addClass("active");
		$("#"+filtro[3]).prop("checked", true);

		$("#idProduto").val(filtro[4]);
		$("#descricao").val(filtro[5]);
		
		pesquisar();
	}

	$("#cbTodos").click(function(e){
		$('.cbAprovacao').each(function(){
			if($('#cbTodos').prop('checked'))
				$(this).prop('checked', true);
			else
				$(this).prop('checked', false);
		});
	});

	$("#btnAprovar").click(function(){
		pupulaArraySelecionados();
		onClickAprovarVarios(pupulaArraySelecionados());
	});

	$("#btnReprovar").click(function(){
		pupulaArraySelecionados();
		onClickReprovarVarios(pupulaArraySelecionados());
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
	
	$("#pesquisar").click(function(e){
		e.stopPropagation();
		if(!isValidRequired()){
			alerta("Preencha os campos obrigat�rios.", "warning");
			return;
		}
		pesquisar();
	});

	$(".statusPesquisar").change(function(e){
		if(isValidRequired())
			pesquisar();
	});
	
	$("#limpar").click(function(e){
		e.stopPropagation();
		clearAll();
		$(".badge").text(0);
		$("input[name=situacao]").parent().removeClass("active");
		$("#aguardando").parent().addClass("active");
		$("#lstProdutoAprovacao").html("");
	});
	
	$("#buscaRepresentante").click(function(e){
		e.stopPropagation();
		$("#consulta_representante").modal();	
	});
	
});

function pesquisar(){
	var filtroBusca = {
			  idUsuario: 	$("#idUsuario").val(),
			  situacao: 	$("input[name=situacao]:checked").attr("id"),
			  idProduto:	$("#idProduto").val(),
			  desProduto:	$("#descricao").val()
			  		};

	$.ajax({
	  type: "GET",
	  url: "obterProdutoAprovacao",
	  data: filtroBusca,
	  contentType: "application/json; charset=ISO-8859-1",
	  dataType: "json",
	  success: function(lista) {
		    var lines = "";
		    
		    if(lista.length == 0){
		    	$("#lstProdutoAprovacao").html("<tr><td colspan='4'>Nenhum registro encontrado</td></tr>");
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

	if(produto.situacao.indexOf("G") > -1)
		line = line.concat("<td class='centralizar-componente'><input type='checkbox' class='cbAprovacao' idLinha=", produto.idProduto ," id='cb", produto.idProduto ,"'></td>");
	else
		line = line.concat("<td class='centralizar-componente'><span class='glyphicon glyphicon-asterisk' title='Esse produto n�o pode ser alterado' aria-hidden='true'></span></td>");
	
	line = line.concat("<td onclick='onClickLine(", produto.idProduto ,")'>", produto.eanDunProduto ,"</td>");
	line = line.concat("<td onclick='onClickLine(", produto.idProduto ,")'>", produto.desProduto ,"</td>");
	line = line.concat("<td onclick='onClickLine(", produto.idProduto ,")'>", produto.descSituacao ,"</td>");
	
	line = line.concat("</tr>");
	
	return line;
}

function onClickLine(idProduto){
	$("#codProduto").val(idProduto);
	$("#allDisabled").val(true);
	$("#actionCancelar").val("ProdutoAprovacao");
	
	var filtro = "";
	filtro = filtro.concat(	$("#idRepresentante").val(), ";",
							$("#idUsuario").val() , ";",
						 	$("#representante").val(), ";",
						 	$("input[name=situacao]:checked").attr("id"), ";",
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

function onClickReprovarVarios(idProdutos){
	if(idProdutos.length > 0){
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
			$.get("reprovarProdutos?idProdutos=".concat(idProdutos), function(){
				pesquisar();
				var qtdAguardando = parseInt($("#qtdAguardando").text());
				var qtdReprovado = parseInt($("#qtdReprovado").text());

				$.each(idProdutos, function() {
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

function onClickAprovarVarios(idProdutos){
	if(idProdutos.length > 0){
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
			$.get("aprovarProdutos?idProdutos=".concat(idProdutos), function(){
				pesquisar();
				var qtdAguardando = parseInt($("#qtdAguardando").text());
				var qtdAprovado = parseInt($("#qtdAprovado").text());

				$.each(idProdutos, function() {
					$("#qtdAguardando").text(--qtdAguardando);
					$("#qtdAprovado").text(++qtdAprovado);
				});
				swal("Produto aprovado com sucesso", "", "success");
			});
		});
	}else{
		swal("Selecione ao menos uma linha", "", "warning");
	}		
}

function onClickBlank(){
	alerta("Esse produto n�o pode ser alterado","warning");
}

function addLineRepresentanteTab(representante){
	var id = representante.idRepresentante;
	$("#idUsuario").val(representante.idUsuario);
	$("#idRepresentante").val(id);
	$("#representante").val(representante.apelido);
	
	$.ajax({
	    type: "GET",
	    url: "obterQtdPorSituacao?idUsuario=".concat(representante.idUsuario),
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
			<h4>Aprova��o de Produtos</h4>
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
					<label for="situacao">Situa��o</label>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 form-group req">
					<input type="hidden" id="idRepresentante">
					<input type="hidden" id="idUsuario">
					<input type="text" class="form-control" readonly="readonly" id="representante"/>
					
				</div>
				<div class="col-md-1 form-group paddingleft0">
				  	<a href="#" target="_self" class="form-control icon-search" id="buscaRepresentante"><span class="glyphicon glyphicon-search"></span></a>
				</div>
				<div class="col-md-7 form-group btn-group" data-toggle="buttons">
					<label class="btn ${theme} active statusPesquisar">
    					<input type="radio" name="situacao" id="aguardando" autocomplete="off" checked> Aguardando Aprova��o 
    					<span id="qtdAguardando" class="badge">0</span>
  					</label>
					
					<label class="btn ${theme} statusPesquisar">
    					<input type="radio" name="situacao" id="aprovado" autocomplete="off"> Aprovado 
    					<span id="qtdAprovado" class="badge">0</span>
  					</label>
  					<label class="btn ${theme} statusPesquisar">
    					<input type="radio" name="situacao" id="reprovado" autocomplete="off"> Reprovado 
    					<span id="qtdReprovado" class="badge">0</span>
  					</label>

					<label class="btn ${theme} statusPesquisar">
					    <input type="radio" name="situacao" id="integrado" autocomplete="off"> Integrado 
					    <span id="qtdIntegrado" class="badge">0</span>
					  </label>
  					
					<label class="btn ${theme} statusPesquisar">
    					<input type="radio" name="situacao" id="todos" autocomplete="off"> Todos 
    					<span id="qtdTodos" class="badge">0</span>
  					</label>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-1">
					<label for="idProduto">C�digo</label>
				</div>
				<div class="col-md-8">
					<label for="descricao">Descri��o</label>
				</div>
			</div>
				
			<div class="row">	
				<div class="col-md-1 form-group">
			    	<input type="text" 
			    			class="form-control" 
			    			id="idProduto" 
			    			name="idProduto" 
			    			maxlength="11"
			    			value="" />
				</div>
				
				<div class="col-md-8 form-group">
			    	<input type="text" 
			    			class="form-control" 
			    			id="descricao" 
			    			name="descricao" 
			    			maxlength="45"
			    			value="" />
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
						<th width="30%">C�digo EAN</th>
						<th width="40%">Descri��o</th>
						<th width="15%">Situa��o</th>
					</tr>
				</thead>
				<tbody id="lstProdutoAprovacao"></tbody>
			</table>
		</div>
	
	</div>
	<c:import url="RepresentanteModalLista.jsp"/>
	
	<c:choose>
		<c:when test="${usuarioLogado.flagAprovProduto eq 1}">
			<c:import url="/WEB-INF/views/components/footer.jsp">
				<c:param name="salvar" value="reprovar_aprovar" />
			</c:import>
		</c:when>
		<c:otherwise>
			<c:import url="/WEB-INF/views/components/footer.jsp" />
		</c:otherwise>
	</c:choose>
</body>
</html>