<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<html>

<c:import url="imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){

	$("#btnCadastrar").click(function(){
		limpaCampos();
		$(this).attr('data-toggle','modal');
		$(this).attr('data-target','#cadastroModal');
	});
	
	$("#entrar").click(function(){
		login();
	});
	
	$(".campo-login").keypress(function(e){
	    if(e.which == 13) {
	        login();
	    }
	});

	$("#email").focus();
	
	if($.getUrlVar('erro') == "true"){
		alerta("Falha na autenticação", "warning");
	}

});

function login(){
	if($.trim($("#j_password").val()) === ""){
		alerta("Favor preencher o campo usuário.", "warning");
	}else if($.trim($("#j_password").val()) === ""){
		alerta("Favor preencher o campo senha.", "warning");
	}else{
		$("#frmLogin").submit();
	}
}

	
</script>
	
<body>

<div class="flex-align">
    <div class="content-center">
        <div class="row">
			<h5 align="center"><b>Bem vindo ao Portal Fornecedor</b></h5><br>
		</div>
			<form id="frmLogin" action="j_spring_security_check" method="POST">
			<div class="row">
  				<div class="col-md-11 margin14px">
					
					<div class="form-group">
						<div class="input-group">
						  <span class="input-group-addon" id="basic-addon1">
						  	<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						  </span>
						  <input type="text" class="form-control campo-login" placeholder="Usuário" aria-describedby="basic-addon1" name="j_username" id="j_username">
						</div>
					</div>
					
					<div class="form-group">
						<div class="input-group">
						  <span class="input-group-addon" id="basic-addon1">
						  	<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
						  </span>
						  <input type="password" class="form-control campo-login" placeholder="Senha" aria-describedby="basic-addon1" name="j_password" id="j_password">
						</div>
					</div>
					
					<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
				</div>
  			</div>
  			
			<div class="col-md-11 margin14px">
				<div class="row">
					<button type="button" class="btn btn-primary btn-lg btn-block btn-warning" id="entrar">Entrar</button>
				</div>
			</div>
			</form>
        </div>
        
    </div>

</body>
</html>
