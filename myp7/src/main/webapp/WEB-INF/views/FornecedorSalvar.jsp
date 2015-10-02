<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function() {
	if($("#mensagemRetorno").val()) alerta($("#mensagemRetorno").val(), $("#codMsgem").val() === "0" ? "success" :"warning");

	$("#btnCancelar").click(function(e){
		e.stopPropagation();
		go("Fornecedor");
	});
	
	$("#btnSalvar").click(function(e){
		e.stopPropagation();
		if(!isValidRequired()){
			alerta("Preencha os campos obrigatórios.", "warning");
			return;
		}
		
		go("#frmSalvarFornecedor");
	});	
	
	$("#clickPessoa").click(function(e){
		e.stopPropagation();
		$("#consulta_pessoa").modal();
	});

});

function addLineRepresentanteTab(representante){
	var line = "", id = representante.idRepresentante;
	if(isExist("linesRepresentante", "idRepresentante", id)) 
		return;
	
	var atual = $("#linesRepresentante").html(), uuid = guid();
	
	line = line.concat("<tr>");
	line = line.concat("<td class='text-middle'>", representante.apelido);
	line = line.concat("	<input type='hidden' name='objFornecedor.representantes[0].idRepresentante' id='pk' value='", id, "'/>");
	line = line.concat("</td>");
	
	line = line.concat("<td class='text-center text-middle'>");
	line = line.concat(		"<a href='#' id='",uuid,"' onclick='onRemoveLine(\"",uuid,"\", 'qtdRepresentante'); reindex(\"#linesRepresentante\", \"representantes\");'>");
	line = line.concat(			"<span class='glyphicon glyphicon-remove red'></span>");
	line = line.concat(		"</a>");
	line = line.concat(	"</td>");
	line = line.concat("</tr>");
	
	$("#linesRepresentante").html(atual + line);
	addContador(qtdRepresentante);
	reindex("#linesRepresentante", "representantes");
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
			<h4>Fornecedor</h4>
		</div>
		
		<div id="content-body">
			<form action="salvarFornecedor" id="frmSalvarFornecedor" method="POST">
				<input type="hidden" id="mensagemRetorno" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				
				<div class="row">
				  	<div class="col-md-6 form-group req">
				   		<label for="nomePessoa">Pessoa</label>
				    	<input type="hidden" id="idPessoa" name="idFornecedor" value="${objFornecedor.idFornecedor}">
				    	<input type="text" class="form-control" id="razao" maxlength="11" value="${objFornecedor.razao}" readonly="readonly">
				  	</div>
				  	<div class="col-md-1 form-group paddingleft0">
				  		<label for="nomePessoa">&nbsp;</label>
				  		<a href="#" target="_self" class="form-control icon-search" id="clickPessoa"><span class="glyphicon glyphicon-search"></span></a>
				  	</div>
				  	<div class="col-md-2 form-group req">
				   		<label for="status">Status</label>
			   			<form:select path="objFornecedor.statusFornecedor" cssClass="form-control">
			   				<form:option value="A" label="Ativo" />
			   				<form:option value="I" label="Inativo" />
			   			</form:select>
				  	</div>
				  	<div class="col-md-2 form-group req">
				   		<label for="status">Utiliza Tabela de Custo</label>
			   			<form:select path="objFornecedor.utilTabCustoFornc" cssClass="form-control">
			   				<form:option value="S" label="SIM" />
			   				<form:option value="N" label="NÃO" />
			   			</form:select>
				  	</div>
				</div>
				<div class="row">&nbsp;</div>
				<table  class="table table-hover table-bordered table-striped margin0" >	
					<thead>
						<tr style="text-align: center">
							<th width="90%"><b>Representante</b><span id="qtdRepresentante" class="badge">${qtdRepresentante}</span></th>
							<th width="10%" class="text-center"><a href="#" onclick="onAddRepresentante()"><span class="glyphicon glyphicon-plus"></span></a></th>
						</tr>
					</thead>
					<tbody id="linesRepresentante">
						<c:forEach items="${objFornecedor.representantes}" var="rep" varStatus="i">
							<tr>
								<td class="text-middle">
									${rep.apelido}
									<form:hidden path="objFornecedor.representantes[${i.index}].idRepresentante" />
								</td>
								<td class="text-center text-middle">
									<a href="#" id="${rep.uuid}" onclick="onRemoveLine('${rep.uuid}', 'qtdRepresentante'); reindex('linesRepresentante', 'representantes');" >
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
	<c:import url="RepresentanteModalLista.jsp"/>
	
	<c:import url="components/footer.jsp">
		<c:param name="salvar" value="cancelar_salvar" />
	</c:import>
</body>
</html>
