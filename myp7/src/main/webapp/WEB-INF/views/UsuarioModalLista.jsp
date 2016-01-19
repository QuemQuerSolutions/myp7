<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
var idUsu;
var nomeUsu;

$(document).ready(function() {
	$('#consulta_usuario').on('hidden.bs.modal', function (e) {
		$("#listaUsuario").html("");
		clearAll("#usaurio_modal_body");
	});

	$("#limparUsuario").click(function(e){
		e.stopPropagation();
		clearAll("#usaurio_modal_body");
		limparVar();
	});
		
	$("#pesquisarUsuarioModal").click(function(){
		pesquisarUsuario($("#razao_social").val(),$("#email").val());
	});

	$(".campo-pesquisa").keypress(function(e){
	    if(e.which == 13) {
	    	pesquisarUsuario($("#razao_social").val(),$("#email").val());
	    }
	});

	$(document).on("click", ".tabela-usuario tbody tr", function(){
		$(".linha-selecionada").removeClass($("#theme").val());
		limparVar();
		
		$(this).addClass($("#theme").val());
		$(this).addClass("linha-selecionada");
		idUsu = $(this).find(".idUsu").text();
		nomeUsu = $(this).find(".razaoUsu").text();
	});

	$("#btnSelecionarUsuario").click(function(e){
		e.stopPropagation();
		if($(".clicked").attr('name') == "subordinado"){
			var subordinado;
			$("#listaUsuario tr").each(function(){
				if($(this).hasClass($("#theme").val())){
					subordinado = {idUsuario	: $(this).find('td[data-id]').text(),
							   	   razao		: $(this).find('td[data-razao]').text()}
				}
			});	
			addLineSubordinadoTab(subordinado);
		}else{
			if(nomeUsu == ""){
				alerta("Selecione um usuário.", "warning");
			}else{
				$("#"+$(".clicked").attr('name')).val($.trim(nomeUsu));
				$("#id"+$(".clicked").attr('name')).val($.trim(idUsu));
				
				$("#limparUsuario").click();
				removerClasseClicked();
			}
		}
		$('#consulta_usuario').modal("hide");
	});	
});

function limparVar(){
	idUsu = "";
	nomeUsu = "";
}

function removerClasseClicked(){
	$(".clicked").removeClass("clicked");
}

function pesquisarUsuario(razaoSocial, email){
//	if(!hasInformation("#usaurio_modal_body")){
//		alerta("Informe ao menos um filtro para continuar", "warning");
//	}else { 
		if($(".clicked").attr('name') != "subordinado"){
			var usuario = {razaoSocial : $.trim($("#razao_social").val()), email : $.trim($("#email").val())}
		}else{
			var usuario = {idsUsuarioRemoverLista : obterIdsUsuarioRemoverLista(), razaoSocial : $.trim($("#razao_social").val()), email : $.trim($("#email").val())}
		}
		$.ajax({
			type: "GET",
			data :usuario,
	        contentType: "application/json; charset=ISO-8859-1",
		    dataType: "json",
	        url : 'pesquisarUsuarioAJAX',
	        success : function(retornoList) {
	        	if(retornoList.length == 0){
	        		$("#listaUsuario").html("<tr><td colspan='15'>Nenhum registro encontrado</td></tr>");
	        	}
		        if(retornoList[0].codRetorno == -1){
			        alerta(retornoList[0].msgRetorno,"warning");
					$("#listaUsuario").html("");
					return;
		        }
		        
		        $("#listaUsuario").html(montaTable(retornoList));
	        },
	        error: function (xhr, textStatus, errorThrown) {
		    	console.log("Erro ao retornar lista: ",errorThrown)	;
		        alerta("Erro ao retornar lista","warning");
	        }
	    });
//	}
}

function obterIdsUsuarioRemoverLista(){
	var idsUsuarioRemoverLista = $(".idAdministrador").val();

	$(".listaSubordinados").each(function(){
		idsUsuarioRemoverLista = idsUsuarioRemoverLista.concat(",");
		idsUsuarioRemoverLista = idsUsuarioRemoverLista.concat($(this).val());
	});
	
	return idsUsuarioRemoverLista;
}

function montaTable(lista){
	var linha = "";
	lista.forEach(function(item){
		linha = linha.concat("<tr>", 
								"<td class=\"idUsu\" data-id>",item.idUsuario,"</td>", 
								"<td class=\"razaoUsu\" data-razao>",item.razaoSocial,"</td>",
								"<td>",item.email,"</td>",
							 "</tr>");
	});
	return linha;
}

</script>
<input type="hidden" id="theme" value="${theme}" />
<div class="modal fade" id="consulta_usuario">
	<div class="modal-dialog modal-usuario-selecionar">
		<div class="modal-content">
			<div class="modal-header ${theme}">
				<button type="button" class="close limpar" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Selecionar Usuário</h4>
			</div>
			<div class="modal-body" id="usaurio_modal_body">
				<div id="content-header">
					<form action="SelecionarUsuario" id="frmSelecionarUsuario" method="POST">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
							   		<label for="sigla" class="control-label">Razão Social</label>
							    	<input type="text" class="form-control campo-pesquisa" id="razao_social" name="razaoSocial" maxlength="200" placeholder="Razão Social">
							  	</div>
							</div>
		  					<div class="col-md-4">
								<div class="form-group">
							   		<label for="descricao" class="control-label">Email</label>
							    	<input type="text" class="form-control campo-pesquisa" id="email" name="email" maxlength="100" placeholder="Email">
							  	</div>
		  					</div>
		  					<div class="col-md-2">
								<div class="form-group">
									<button type="button" class="btn ${theme} btn-large btn-align-top" id="pesquisarUsuarioModal">Pesquisar</button>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<button type="button" class="btn btn-default btn-large btn-align-top" id="limparUsuario">Limpar</button>
								</div>
							</div>						
						</div>
					</form>		
				</div>			
				<div id="content-body">
					<div class="row">
						<div class="col-md-13">
							<div class="form-group" id="divquantidademodal">
						   		<div id="content-body">
									<table class="table table-hover table-bordered table-striped tabela-usuario mouse-click">
										<thead>
											<tr style="text-align: center">
												<th class="col-id">ID</th>
												<th class="col-name">Razão</th>
												<th class="col-email">Email</th>
											</tr>
										</thead>
										<tbody id="listaUsuario"></tbody>
									</table>
								</div>
						  	</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn ${theme}" id="btnSelecionarUsuario">Selecionar</button>
			</div>
		</div>
	</div>
</div>