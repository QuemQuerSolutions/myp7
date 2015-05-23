<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
	$(document).ready(function(){
		$("#entrar").click(function(){
			if($.trim($("#email").val()) == ""){
				humane.log("Favor preencher o campo Usuário.");
			}else if($.trim($("#senha").val()) == ""){
				humane.log("Favor preencher o campo Senha.");
			}else{
				$("#frmLogin").submit();
			}
		});
	
		if($("#mensagem").val() != ""){
			humane.log($("#mensagem").val());
		}
	});
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
					<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
				</div>
  			</div>
  			
			<div class="col-md-11 margin14px">
				<div class="row">
					<button type="button" class="btn btn-link" data-toggle="modal" data-target="#cadastroModal">Criar uma conta</button>
					<button type="button" class="btn btn-primary btn-lg btn-block btn-warning" id="entrar">Entrar</button>
				</div>
			</div>
			</form>
			<div class="modal fade bs-example-modal-lg" id="cadastroModal" role="dialog">
				<div class="modal-dialog modal-lg" >

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Cadastro de Usuário</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="inputRzSocial" class="col-sm-3 control-label">Razao Social</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="inputRzSocial">
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail" class="col-sm-3 control-label">Email</label>
									<div class="col-sm-4">
										<input type="email" class="form-control" id="inputEmail">
									</div>
								</div>
								<div class="form-group">
									<label for="inputSenha" class="col-sm-3 control-label">Senha</label>
									<div class="col-sm-4">
										<input type="password" class="form-control"
											id="inputSenha" >
									</div>
								</div>
								<div class="form-group">
									<label for="inputCnpj" class="col-sm-3 control-label">CNPJ</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" id="inputCnpj">
									</div>
								</div>
								
							</form>
						</div>
						<div class="modal-footer" >
							<button type="submit" class="btn btn-default">Adicionar</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
							
						</div>
					</div>

				</div>
			</div>
        </div>
    </div>

</body>
</html>
