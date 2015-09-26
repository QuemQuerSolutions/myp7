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
		clearAll("#razao_social");
		clearAll("#email");
	});

	$("#limparUsuario").click(function(e){
		e.stopPropagation();
		$("#listaUsuario").html("");
		$("#razao_social").val("");
		$("#email").val("");
		limparVar();
	});
		
	$("#pesquisarUsuarioModal").click(function(){
		pesquisarUsuario($("#razao_social").val(),$("#email").val());
	});

	$(".campo-pesquisa").keypress(function(e){
	    if(e.which == 13) {
	    	pesquisar($("#razao_social").val(),$("#email").val());
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
		if(nomeUsu == ""){
			alerta("Selecione um usuário.", "warning");
		}else{
			$("#usuario").val($.trim(nomeUsu));
			$("#idUsuario").val($.trim(idUsu));
			$("#limparUsuario").click();
			$('#consulta_usuario').modal("hide");
		}
	});	
});

function limparVar(){
	idUsu = "";
	nomeUsu = "";
}

function pesquisarUsuario(razaoSocial, email){
	if($.trim($("#razao_social").val()) == "" && $.trim($("#email").val()) == ""){
		alerta("Informe ao menos um filtro para continuar", "warning");
	}else{
		$.ajax({
			type: "POST",
	        data: { razaoSocial:razaoSocial, email:email },
	        url : 'pesquisarUsuarioAJAX',
	        success : function(data) {
	        	$("#listaUsuario").html(data);
	        }
	    });
	}
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
			<div class="modal-body">
				<div id="content-header">
					<form action="SelecionarUsuario" id="frmSelecionarUsuario" method="POST">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group" id="divrazaosocial">
							   		<label for="sigla" class="control-label">Razão Social</label>
							    	<input type="text" class="form-control campo-pesquisa" id="razao_social" name="razaoSocial" maxlength="50" placeholder="Razão Social">
							  	</div>
							</div>
		  					<div class="col-md-4">
								<div class="form-group" id="divemail">
							   		<label for="descricao" class="control-label">Email</label>
							    	<input type="text" class="form-control campo-pesquisa" id="email" name="email" maxlength="50" placeholder="Email">
							  	</div>
		  					</div>
		  					<div class="col-md-2" id="btnpesquisarUsuarioModal">
								<div class="form-group">
									<button type="button" class="btn ${theme} btn-large" id="pesquisarUsuarioModal" style="margin-top: 25px;">Pesquisar</button>
								</div>
							</div>
							<div class="col-md-2" id="btnlimparUsuarioModal">
								<div class="form-group">
									<button type="button" class="btn btn-default btn-large" id="limparUsuario" style="margin-top: 25px;">Limpar</button>
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
									<table class="table table-hover table-bordered table-striped tabela-usuario">
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