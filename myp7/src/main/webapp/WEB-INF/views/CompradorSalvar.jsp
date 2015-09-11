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
	
	$("#tabEmpresaLista").click(function(e){
		e.stopPropagation();
		removeActive();
		$(this).parent().addClass("active");
		
		$("#EmpresaTabLista").attr("hidden", false);
		$("#RepresentanteTabLista").attr("hidden", true);
	});
	
	$("#tabRepresentanteLista").click(function(e){
		e.stopPropagation();
		removeActive();
		$(this).parent().addClass("active");
		$("#EmpresaTabLista").attr("hidden", true);
		$("#RepresentanteTabLista").attr("hidden", false);
	});
	
});

function removeActive(){
	$(".nav-tabs li").each(function(){
		$(this).removeClass("active");
	});
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
				<div class="row">
				  	<div class="col-md-5 form-group req">
				   		<label for="nomePessoa">Pessoa</label>
				    	<input type="text" class="form-control" id="nomePessoa" name="nomePessoa" maxlength="11" value="${comprador.id}">
				  	</div>
				  	<div class="col-md-1 form-group paddingleft0 width-initial">
				  		<label for="nomePessoa">&nbsp;</label>
				  		<a href="#" class="form-control icon-search"><span class="glyphicon glyphicon-search"></span></a>
				  	</div>
				  	<div class="col-md-6 form-group req">
				   		<label for="nomePessoa">Apelido</label>
				    	<input type="text" class="form-control" id="nomePessoa" name="nomePessoa" maxlength="11" value="${comprador.id}">
				  	</div>
				</div>
				
				<div class="row">&nbsp;</div>
				
				<ul class="nav nav-tabs nav-justified">
					<li role="presentation" class="active"><a href="#" id="tabEmpresaLista">Empresa</a></li>
  					<li role="presentation"><a href="#" id="tabRepresentanteLista">Representante</a></li>
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
