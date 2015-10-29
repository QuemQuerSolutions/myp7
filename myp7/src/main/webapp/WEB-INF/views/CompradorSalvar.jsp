<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		if(!isValidRequired()){
			alerta("Preencha os campos obrigatórios.", "warning");
			return;
		}
		
		go("#frmSalvarComprador");
	});	
	
	$(".nav-tabs > li > a").click(function(e){
		e.stopPropagation();
		controlTabs($(this));
	});

	$("#clickPessoa").click(function(e){
		e.stopPropagation();
		$("#consulta_pessoa").modal();
	});

	$("#clickUsuario").click(function(e){
		e.stopPropagation();
		$(this).addClass("clicked");
		$("#consulta_usuario").modal();
	});	

	$("#clickGerente").click(function(e){
		e.stopPropagation();
		$(this).addClass("clicked");
		$("#consulta_usuario").modal();
	});		

});

function onAddEmpresa() {
	$("#consulta_empresa").modal();
}

function onAddRepresentante(){
	$("#consulta_representante").modal();
}

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
				<input type="hidden" id="id" name="id" value="${obj.id}">
				
				<div class="row">
				  	<div class="col-md-5 form-group req">
				   		<label for="nomePessoa">Pessoa</label>
				    	<input type="hidden" id="idPessoa" name="idPessoa" value="${obj.idPessoa}">
				    	<input type="text" class="form-control" id="razao" maxlength="11" value="${obj.razao}" readonly="readonly">
				  	</div>
				  	<div class="col-md-1 form-group paddingleft0">
				  		<label for="nomePessoa">&nbsp;</label>
				  		<a href="#" target="_self" class="form-control icon-search" id="clickPessoa"><span class="glyphicon glyphicon-search"></span></a>
				  	</div>
				  	<div class="col-md-6 form-group req">
				   		<label for="apelido">Apelido</label>
				    	<input type="text" class="form-control" id="apelido" name="apelido" maxlength="11" value="${obj.apelido}">
				  	</div>
				</div>
				
				<div class="row">
				  	<div class="col-md-5 form-group req">
				   		<label for="usuario">Usuário</label>
				   		<input type="hidden" name="usuario.idUsuario" id="idusuario" value="${obj.usuario.idUsuario}"/>
				    	<input type="text" class="form-control" id="usuario" value="${obj.usuario.razaoSocial}" readonly="readonly">
				  	</div>
				  	<div class="col-md-1 form-group paddingleft0">
				  		<label for="buscaUsuario">&nbsp;</label>
				  		<a href="#" target="_self" class="form-control icon-search" id="clickUsuario" name="usuario"><span class="glyphicon glyphicon-search"></span></a>
				  	</div>
				  	<div class="col-md-6 form-group req">
				   		<label for="status">Status</label>
			   			<form:select path="obj.status" cssClass="form-control">
			   				<form:option value="A" label="Ativo" />
			   				<form:option value="I" label="Inativo" />
			   			</form:select>
				  	</div>
				</div>
				
				<div class="row">
				  	<div class="col-md-5 form-group req">
				   		<label for="apelido">Usuário do Gerente</label>
				   		<input type="hidden" name="gerente.idUsuario" id="idgerente" value="${obj.gerente.idUsuario}"/>
				    	<input type="text" class="form-control" id="gerente" maxlength="11" value="${obj.gerente.razaoSocial}" readonly="readonly">
				  	</div>
				  	<div class="col-md-1 form-group paddingleft0">
				  		<label for="buscaGerente">&nbsp;</label>
				  		<a href="#" target="_self" class="form-control icon-search" id="clickGerente" name="gerente"><span class="glyphicon glyphicon-search"></span></a>
				  	</div>
				  	<div class="col-md-6 form-group">
				   		<label for="ediCodigo">Código da Integração ERP</label>
				    	<input type="text" class="form-control" id="ediCodigo" name="ediCodigo" maxlength="11" value="${obj.ediCodigo}" readonly="readonly">
				  	</div>
				</div>
				
				<div class="row">&nbsp;</div>
				
				<ul class="nav nav-tabs nav-justified">
					<li role="presentation" class="active">
						<a href="#" id="tabEmpresaLista" contextmenu="EmpresaTabLista">
							<b>Empresa</b> <span id="qtdEmpresa" class="badge">${qtdEmpresa}</span>
						</a>
					</li>
  					<li role="presentation">
  						<a href="#" id="tabRepresentanteLista" contextmenu="RepresentanteTabLista">
  							<b>Representante</b> <span id="qtdRepresentante" class="badge">${qtdRepresentante}</span>
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
	
	<c:import url="PessoaModalLista.jsp"/>
	<c:import url="EmpresaModalLista.jsp"/>
	<c:import url="RepresentanteModalLista.jsp"/>
	<c:import url="UsuarioModalLista.jsp" />
	
	<c:import url="components/footer.jsp">
		<c:param name="salvar" value="cancelar_salvar" />
	</c:import>
</body>
</html>
