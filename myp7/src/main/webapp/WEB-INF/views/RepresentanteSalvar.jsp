<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function() {
	if($("#mensagemRetorno").val()) alerta($("#mensagemRetorno").val(), $("#codMsgem").val() === "0" ? "success" :"warning");

	$("#btnCancelar").click(function(e){
		e.stopPropagation();
		go("Representante");
	});
	
	$("#btnSalvar").click(function(e){
		e.stopPropagation();
		if(!isValidRequired()){
			alerta("Preencha os campos obrigatórios.", "warning");
			return;
		}
		go("#frmSalvarRepresentante");
	});	

	$("#clickUsuario").click(function(e){
		e.stopPropagation();
		$(this).addClass("clicked");
		$("#consulta_usuario").modal();
	});	
	
	
	$("#clickPessoa").click(function(e){
		e.stopPropagation();
		$("#consulta_pessoa").modal();
	});

});


function addLineFornecedor(fornecedor){
	var line = "", id = fornecedor.idFornecedor;
	if(isExist("linesFornecedor", "idFornecedor", id)) 
		return;	
	var atual = $("#linesFornecedor").html(), uuid = guid();
	
	line = line.concat("<tr>");
	line = line.concat("<td class='text-middle'>", fornecedor.razao);
	line = line.concat("	<input type='hidden' name='objRepresentante.fornecedores[0].idFornecedor' id='pk' value='", id, "'/>");
	line = line.concat("</td>");
	
	line = line.concat("<td class='text-center text-middle'>");
	line = line.concat(		"<a href='#' id='",uuid,"' onclick='onRemoveLine(\"",uuid,"\", \"qtdFornecedor\"); reindex(\"#linesFornecedor\", \"fornecedores\");'>");
	line = line.concat(			"<span class='glyphicon glyphicon-remove red'></span>");
	line = line.concat(		"</a>");
	line = line.concat(	"</td>");
	line = line.concat("</tr>");
	
	$("#linesFornecedor").html(atual + line);
	addContador(qtdFornecedor);
	reindex("#linesFornecedor", "fornecedores");
}

function onAddFornecedor(){
	$("#consulta_fornecedor").modal();
}

</script>
<body>
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 
	
	<div id="content">	
		<div id="content-title">
			<h4>Representante</h4>
		</div>
		
		<div id="content-body">
			<form action="salvarRepresentante" id="frmSalvarRepresentante" method="POST">
				<input type="hidden" id="mensagemRetorno" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				
				<div class="row">
				  	<div class="col-md-5 form-group req">
				   		<label for="nomePessoa">Pessoa</label>
				    	<input type="hidden" id="idPessoa" name="idRepresentante" value="${objRepresentante.idRepresentante}">
				    	<input type="text" class="form-control" id="razao" maxlength="11" value="${objRepresentante.razao}" readonly="readonly">
				  	</div>
				  	<div class="col-md-1 form-group paddingleft0">
				  		<label for="nomePessoa">&nbsp;</label>
				  		<a href="#" target="_self" class="form-control icon-search" id="clickPessoa"><span class="glyphicon glyphicon-search"></span></a>
				  	</div>
				  	<div class="col-md-6 form-group req">
				   		<label for="apelido">Apelido</label>
				    	<input type="text" class="form-control" id="apelido" name="apelido" maxlength="11" value="${objRepresentante.apelido}">
				  	</div>
				</div>
					
				<div class="row">
				  	<div class="col-md-5 form-group req">
				   		<label for="usuario">Usuário</label>
				   		<input type="hidden" name="usuario.idUsuario" id="idusuario" value="${objRepresentante.usuario.idUsuario}"/>
				    	<input type="text" class="form-control" id="usuario" name="usuario.razaoSocial" value="${objRepresentante.usuario.razaoSocial}" readonly="readonly">
				  	</div>
				  	<div class="col-md-1 form-group paddingleft0">
				  		<label for="buscaUsuario">&nbsp;</label>
				  		<a href="#" target="_self" class="form-control icon-search" id="clickUsuario" name="usuario"><span class="glyphicon glyphicon-search"></span></a>
				  	</div>
				  	<div class="col-md-6 form-group req">
				   		<label for="status">Status</label>
			   			<form:select path="objRepresentante.status" cssClass="form-control">
			   				<form:option value="A" label="Ativo" />
			   				<form:option value="I" label="Inativo" />
			   			</form:select>
				  	</div>
				</div>
				
				<div class="row">&nbsp;</div>
				<table  class="table table-hover table-bordered table-striped margin0" >	
					<thead>
						<tr style="text-align: center">
							<th width="90%"><b>Fornecedor</b>&nbsp;<span id="qtdFornecedor" class="badge">${qtdFornecedor}</span></th>
							<th width="10%" class="text-center"><a href="#" onclick="onAddFornecedor()"><span class="glyphicon glyphicon-plus"></span></a></th>
						</tr>
					</thead>
					<tbody id="linesFornecedor">
						<c:forEach items="${objRepresentante.fornecedores}" var="fornec" varStatus="i">
							<tr>
								<td class="text-middle">
									${fornec.razao}
									<form:hidden path="objRepresentante.fornecedores[${i.index}].idFornecedor" />
								</td>
								<td class="text-center text-middle">
									<a href="#" id="${fornec.uuid}" onclick="onRemoveLine('${fornec.uuid}', 'qtdFornecedor'); reindex('#linesFornecedor', 'fornecedores');" >
										<span class="glyphicon glyphicon-remove red"></span>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>		
		</div>
	</div>
	
	<c:import url="PessoaModalLista.jsp"/>
	<c:import url="UsuarioModalLista.jsp"/>
	<c:import url="FornecedorModalLista.jsp"/>
	
	
	<c:import url="/WEB-INF/views/components/footer.jsp">
		<c:param name="salvar" value="cancelar_salvar" />
	</c:import>
</body>
</html>
