<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function() {
	if($("#mensagemRetorno").val()) alerta($("#mensagemRetorno").val(), $("#codMsgem").val() == "0" ? "success" :"warning");
	
	$("#btnCancelar").click(function(){
		go("Comprador");
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
					<div class="col-md-1">&nbsp;</div>
						<div class="col-md-10">
						
							<div class="row">
								<div class="form-group req">
							   		<label for="idPessoa">Pessoa</label>
							    	<input type="text" class="form-control" id="idPessoa" name="idPessoa" maxlength="11" value="${comprador.id}">
							  	</div>
							</div>
						
						</div>
					<div class="col-md-1">&nbsp;</div>
				</div>
			</form>		
		</div>
	</div>
	
	<c:import url="components/footer.jsp">
		<c:param name="salvar" value="cancelar_salvar" />
	</c:import>
</body>
</html>
