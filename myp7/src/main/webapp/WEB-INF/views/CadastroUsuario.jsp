<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>


<script type="text/javascript">
$(document).ready(function() {

	$("#inputCnpj").change(function(){
		$("#inputCnpj").val(FormatarCnpj($.trim($("#inputCnpj").val())));
	});

	$("#fechar").click(function(){
		removeClass();
		limpaCampos();
	});

	$("#cadastrar").click(function(){
		removeClass();
		if(!validaCamposObrigatorios()){
			alerta("Favor preencher os campos obrigatórios.", "warning");
		}else{
			
			if(!validarCNPJ($.trim($("#inputCnpj").val()))){
				alerta("CNPJ inválido", "warning");
				$("#divCnpj").attr("class","form-group has-error");
			}else{
				$("#inputCnpj").val($("#inputCnpj").val().replace(/[^\d]+/g,'')); //retirar a formatação do cnpj
				$("#frmCadastroModal").submit();
				$(this).attr('data-dismiss','modal');
				limpaCampos();
				
			}
		}	
	});

	if($("#mensagemCadastro").val() != ""){
		alerta($("#mensagemCadastro").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	}
	function limpaCampos(){
		$("#inputRzSocial").val("");
		$("#inputCnpj").val("");
		$("#inputEmail").val("");
		$("#inputSenha").val("");
	}
	
	function validaCamposObrigatorios(){
		var isValid = true;
		
		if($.trim($("#inputRzSocial").val()) == ""){
			$("#divRzSocial").attr("class","form-group has-error");
			isValid = false;
		}
		
		if($.trim($("#inputCnpj").val()) == ""){
			$("#divCnpj").attr("class","form-group has-error");
			isValid = false;
		}
		
		if($.trim($("#inputEmail").val()) == ""){	
			$("#divEmail").attr("class","form-group has-error");
			isValid = false;
		}
		
		if($.trim($("#inputSenha").val()) == ""){
			$("#divSenha").attr("class","form-group has-error"); 
			isValid = false;
		}
		return isValid;
	}

	function removeClass(){
		$("#divRzSocial").attr("class","form-group");
		$("#divCnpj").attr("class","form-group");
		$("#divEmail").attr("class","form-group");
		$("#divSenha").attr("class","form-group");
		
	}
});

</script>
<div class="modal fade bs-example-modal-lg" id="cadastroModal" >
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header theme-orange">
				<button type="button" class="close" data-dismiss="modal" id="fechar" >&times;</button>
				<h4 class="modal-title">Cadastro no Portal Forncedor</h4>
			</div>
			<div class="modal-body">
				<form action="cadastroUsuario" id="frmCadastroModal" method="POST">
					<div class="form-horizontal" >
						<input type="hidden" id="mensagemCadastro" value="${mensagemRetorno}" />
						<input type="hidden" id="codMsgem" value="${codMsgem}" />
						<div class="form-group" id="divRzSocial">
							<label for="inputRzSocial" class="col-sm-3 control-label">Razão	Social</label>
							<div class="col-sm-6">
								<input type="text" class="form-control campo-salvar" id="inputRzSocial" 
								maxlength="200" name ="razaoSocial" placeholder="Razão Social" value="${usuario.razaoSocial}">	
							</div>
						</div>
						<div class="form-group" id="divCnpj">
							<label for="inputCnpj" class="col-sm-3 control-label">CNPJ</label>
							<div class="col-sm-4">
								<input type="text" class="form-control campo-salvar" name="nDocumento"
									placeholder="00.000.000/0000-00" id="inputCnpj" maxlength="14" value="${usuario.nDocumento}">
							</div>
						</div>
						<div class="form-group" id="divEmail">
							<label for="inputEmail" class="col-sm-3 control-label">E-mail</label>
							<div class="col-sm-4">
								<input type="email" class="form-control campo-salvar" id="inputEmail" name="email"
									maxlength="100" placeholder="Email" value="${usuario.email}">
							</div>
						</div>
						<div class="form-group" id="divSenha">
							<label for="inputSenha" class="col-sm-3 control-label">Senha</label>
							<div class="col-sm-4">
								<input type="password" class="form-control campo-salvar" id="inputSenha" name="senha"
									placeholder="********" value="${usuario.senha}">
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<div class="col-sm-offset-2 col-sm-5">
					<button type="button" id="cadastrar" class="btn btn-default btn-lg btn-warning">Cadastrar</button>
				</div>
			</div>
		</div>
	</div>
</div>
</html>