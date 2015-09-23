<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
$(document).ready(function() {
	$("#pesquisarModal").click(function(){
		alert("Teste1");
		pesquisar($("#razao_social").val(),$("#email").val());
	});

	$(".campo-pesquisa").keypress(function(e){
	    if(e.which == 13) {
	    	alert("Teste2");
	    	pesquisar($("#razao_social").val(),$("#email").val());
	    }
	});
});

function pesquisar(razaoSocial, email){
	alert("Teste3");
	removeClass();
	if(!validaCamposObrigatorios()){
		alerta("Favor preencher os campos obrigatórios.", "warning");
	}else{
		$.ajax({
			type: "POST",
	        data: { razaoSocial:razaoSocial, email:email },
	        url : 'pesquisarUsuarioAJAX',
	        success : function(data) {
	        	$("#resultado").html(data);
	        }
	    });
	}
}

function validaCamposObrigatorios(){
	var isValid = true;
	if($.trim($("#razao_social").val()) == "" && $.trim($("#email").val()) == ""){
		$("#divrazaosocial").addClass("has-error");
		$("#divemail").addClass("has-error");
		isValid = false;
	}
	
	return isValid;
}

function removeClass(){
	$("#divemail").removeClass("has-error");
	$("#divrazaosocial").removeClass("has-error");
}

</script>

<div class="modal fade" id="usuario_selecionar">
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
				<form action="SelecionarUsuario" id="frmSelecionarUsuario" method="POST">
					<div class="row">
						<div class="col-md-5">
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
	  					<div class="col-md-3">
	  						<div class="form-group">
								<button type="button" class="btn" id="pesquisarModal" style="margin-top: 25px;">Pesquisar</button>
							</div>
	  					</div>
					</div>
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
										<tbody id="resultado"></tbody>
									</table>
								</div>
						  	</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default limpar" data-dismiss="modal">Cancelar</button>
				<button type="button" class="btn ${theme}" id="salvar">Salvar</button>
			</div>
		</div>
	</div>
</div>