<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function() {

	$("#inputCnpj").change(function(){
		$("#inputCnpj").val(FormatarCnpj($.trim($("#inputCnpj").val())));
	});
	
	$("#btnCancelar").click(function(){
		removeClass();
		limpaCampos();
	});

	if($("#mensagemCadastro").val() != ""){
		alerta($("#mensagemCadastro").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
		limpaCampos();
	}

	$("#btnSalvar").click(function(){
		removeClass();
		if(!validaCamposObrigatorios()){
			alerta("Favor preencher os campos obrigatórios.", "warning");
		}else{
			
			
			if(!validarCNPJ($.trim($("#inputCnpj").val()))){
				alerta("CNPJ inválido", "warning");
				$("#divCnpj").attr("class","form-group has-error");
			}else if(!validaEmail($("#inputEmailUsuario").val())){
				alerta("Não é um endereço de e-mail válido.", "warning");
				$("#divEmail").attr("class","form-group has-error");
			}else{
				$("#inputCnpj").val($("#inputCnpj").val().replace(/[^\d]+/g,'')); //retirar a formatação do cnpj
				$("#frmCadastro").submit();
				
			}
		}	
	});

	function limpaCampos(){
		$("#inputRzSocial").val("");
		$("#inputCnpj").val("");
		$("#inputEmailUsuario").val("");
		$("#inputSenhaUsuario").val("");
	}

	function validaCamposObrigatorios(){
		var isValid = true;
		isValid = getEmptyValidation("#divRzSocial", 
									 "#divCnpj",
									 "#divEmail",
									 "#divSenha");
		return isValid;
	}

	function removeClass(){
		$(".has-error").each(function(){ $(this).attr("class","form-group"); });
	}

	function onClickTipoUsuario(){
		$("#tpUsuarioRetorno").val(getRadioButton($(".tpUsuario")));
	}
	
});

</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 

	<div id="content">	
		<div id="content-title">
			<h4>Usuário</h4>
		</div>
		
		<div id="content-body">
			<form action="cadastroUsuario" id="frmCadastro" method="POST" enctype="multipart/form-data">
				<input type="hidden" id="mensagemCadastro" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				<input type="hidden" id=tpUsuarioRetorno name="tpUsuarioRetorno" value="" />
				<div class="row">
					<div class="row">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-6">
							<div class="form-group" id="divRzSocial">
								<label for="inputRzSocial" class="control-label">Razão	Social</label>
									<input type="text" class="form-control campo-salvar" id="inputRzSocial" 
									maxlength="200" name="razaoSocial" placeholder="razão social" value="${usuario.razaoSocial}">	
							</div>
						</div>
					</div>
						
					<div class="row">
					<div class="col-md-3">&nbsp;</div>
						<div class="col-md-3">
							<div class="form-group" id="divCnpj">
								<label for="inputCnpj" class="control-label">CNPJ</label>
									<input type="text" class="form-control campo-salvar" name="nDocumento"
										placeholder="00.000.000/0000-00" id="inputCnpj" maxlength="14" value="${usuario.nDocumento}">
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-3">
							<div class="form-group" id="divEmail">
								<label for="inputEmailUsuario" class="control-label">E-mail</label>
									<input type="email" class="form-control campo-salvar" id="inputEmailUsuario" name="email"
										maxlength="100" placeholder="e-mail" value="${usuario.email}">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-3">
							<div class="form-group" id="divSenha">
								<label for="inputSenhaUsuario" class="control-label">Senha</label>
									<input type="password" class="form-control campo-salvar" id="inputSenhaUsuario" name="senha"
										placeholder="********" value="${usuario.senha}">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">&nbsp;</div>
						<div class="col-md-3">
							<div class="form-group" id="divTipoUsuario">
								<input type="radio" name="tpUsuario" value="P" id="inputRadioPortal" checked="checked" onclick="onClickTipoUsuario();"/>
								<label for="inputRadioPortal" class="control-label">Portal</label>
								<input type="radio" name="tpUsuario" id="inputRadioRetaguarda" value="R" onclick="onClickTipoUsuario();" />
								<label for="inputRadioRetaguarda" class="control-label">Retaguarda</label>
							</div>
						</div>
					</div>								
					<div class="col-md-3">&nbsp;</div>
				</div>
			</form>
		</div>
	</div>
	<c:import url="components/footer.jsp">
		<c:param name="salvar" value="salvar" />
	</c:import>
</body>
</html>