<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function() {
	if($("#mensagemRetorno").val()) alerta($("#mensagemRetorno").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	
	$("#btnCancelar").click(function(e){
		e.stopPropagation();
		go("Comprador");
	});
	
	$("#btnSalvar").click(function(e){
		e.stopPropagation();
		if(!isValidRequired())
			alerta("Preencha os campos obrigatórios.", "warning");
	});	
	
	$(".nav-tabs > li > a").click(function(e){
		e.stopPropagation();
		controlTabs($(this));
	});

});

</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 
	
	<div id="content">	
		<div id="content-title">
			<h4>Comprador</h4>
		</div>
		
		<div id="content-body">
			<form action="salvarComprador" id="frmSalvarComprador" method="POST">
				<input type="hidden" id="mensagemRetorno" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				
				<div class="row">
				  	<div class="col-md-5 form-group req">
				   		<label for="nomePessoa">Pessoa</label>
				    	<input type="text" class="form-control" id="nomePessoa" name="nomePessoa" maxlength="11" value="${obj.id}">
				  	</div>
				  	<div class="col-md-1 form-group paddingleft0">
				  		<label for="nomePessoa">&nbsp;</label>
				  		<a href="#" class="form-control icon-search"><span class="glyphicon glyphicon-search"></span></a>
				  	</div>
				  	<div class="col-md-6 form-group req">
				   		<label for="apelido">Apelido</label>
				    	<input type="text" class="form-control" id="apelido" name="apelido" maxlength="11" value="${obj.apelido}">
				  	</div>
				</div>
				
				<div class="row">
				  	<div class="col-md-6 form-group">
				   		<label for="ediCodigo">Integração ERP</label>
				    	<input type="text" class="form-control" id="ediCodigo" name="ediCodigo" maxlength="11" value="${obj.ediCodigo}" readonly="readonly">
				  	</div>
				  	<div class="col-md-6 form-group req">
				   		<label for="status">Status</label>
				    	<select id="status" class="form-control">
				    		<option value="A">Ativo</option>
				    		<option value="I">Inativo</option>
				    	</select>
				  	</div>
				</div>
				
				<div class="row">
				  	<div class="col-md-6 form-group">
				   		<label for="usuario">Usuário</label>
				    	<input type="text" class="form-control" id="ediCodigo" name="ediCodigo" maxlength="11" value="${obj.ediCodigo}" readonly="readonly">
				  	</div>
				  	<div class="col-md-6 form-group req">
				   		<label for="apelido">Gerente</label>
				    	<input type="text" class="form-control" id="ediCodigo" name="ediCodigo" maxlength="11" value="${obj.ediCodigo}" readonly="readonly">
				  	</div>
				</div>
				
				<div class="row">&nbsp;</div>
				
				<ul class="nav nav-tabs nav-justified">
					<li role="presentation" class="active"><a href="#" id="tabEmpresaLista" contextmenu="EmpresaTabLista"><b>Empresa</b> <span class="badge">${qtdEmpresa}</span></a></li>
  					<li role="presentation">
  						<a href="#" id="tabRepresentanteLista" contextmenu="RepresentanteTabLista">
  							<b>Representante</b> <span class="badge">${qtdRepresentante}</span>
  						</a>
  					</li>
				</ul>
				
				<div id="content-tabs">
					<c:import url="EmpresaTabLista.jsp" />
					<c:import url="RepresentanteTabLista.jsp" />				
				</div>
				
			</form>		
		</div>
	</div>
	

	<c:import url="components/footer.jsp">
		<c:param name="salvar" value="cancelar_salvar" />
	</c:import>
</body>
</html>
