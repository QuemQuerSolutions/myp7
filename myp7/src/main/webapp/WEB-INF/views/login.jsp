<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
	function onLogin(){	
		if(document.getElementById("email").value.trim() == ""){
			humane.log("Favor preencher o campo Usuário.");
		}else if(document.getElementById("senha").value.trim() == ""){
			humane.log("Favor preencher o campo Senha.");
		}else{
			document.getElementById("frmLogin").submit(); 
		}
	}
</script>
	
<body>

<div class="flex-align">
    <div class="content-center">
        <div class="row">
			<h5 align="center"><b>Bem vindo ao Portal Fornecedor</b></h5><br>
		</div>
			<form id="frmLogin" action="efetuaLogin" method="POST">
			<div class="row">
  				<div class="col-md-11 margin14px">
					
					<div class="form-group">
						<div class="input-group">
						  <span class="input-group-addon" id="basic-addon1">
						  	<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						  </span>
						  <input type="text" class="form-control" placeholder="Usuário" aria-describedby="basic-addon1" name="email" id="email">
						</div>
					</div>
					
					<div class="form-group">
						<div class="input-group">
						  <span class="input-group-addon" id="basic-addon1">
						  	<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
						  </span>
						  <input type="password" class="form-control" placeholder="Senha" aria-describedby="basic-addon1" name="senha" id="senha">
						</div>
					</div>
					<div style="text-align: center; color: red;">
						<label>${mensagemRetorno}</label>
					</div>
				</div>
  			</div>
  			
			<div class="col-md-11 margin14px">
				<div class="row">
					<button type="button" 
							class="btn btn-primary btn-lg btn-block btn-warning"
							onclick="onLogin();">Entrar</button>
				</div>
			</div>
			</form>
        </div>
    </div>

</body>
</html>
