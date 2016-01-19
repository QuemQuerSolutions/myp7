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
	
	$("#clickUsuario").click(function(e){
		e.stopPropagation();
		$(this).addClass("clicked");
		$("#consulta_usuario").modal();
	});

});

function addLineSubordinadoTab(subordinado){
	var line = "", id = subordinado.idUsuario;
	if(isExist("linesSubordinado", "idUsuarioSub", id)) 
		return;
	
	var atual = $("#linesSubordinado").html(), uuid = guid();
	
	line = line.concat("<tr id='linha",id,"'>");
	line = line.concat("<td class='text-middle'>", subordinado.razao);
	line = line.concat("	<input class=\"listaSubordinados\" type='hidden' name='objFornecedor.representantes[0].idUsuarioSub' id='pk' value='", id, "'/>");
	line = line.concat("</td>");

	line = line.concat("<td class='text-center text-middle'>");
	line = line.concat(		"<a href='#' onclick='alterarPermissaoAprovacao(\"aprProd",id,"\");'>");
	line = line.concat(			"<span id=\"aprProd",id,"\" class='glyphicon glyphicon-remove-sign red'></span>&nbsp; Aprovar Produto");
	line = line.concat(		"</a>");
	line = line.concat("</td>");

	line = line.concat("<td class='text-center text-middle'>");
	line = line.concat(		"<a href='#' onclick='alterarPermissaoAprovacao(\"aprCusto",id,"\");'>");
	line = line.concat(			"<span id=\"aprCusto",id,"\" class='glyphicon glyphicon-remove-sign red'></span>&nbsp; Aprovar Custo");
	line = line.concat(		"</a>");
	line = line.concat(		"&nbsp;&nbsp;<input type=\"number\" id=\"aprCusto",id,"Alcada\" placeholder=\"Alçada\" disabled>");
	line = line.concat("</td>");
	
	line = line.concat("<td class='text-center text-middle'>");
	line = line.concat(		"<a href='#' id='",uuid,"' onclick='removerLinha(\"",id,"\");'>");
	line = line.concat(			"<span class='glyphicon glyphicon-remove red'></span>");
	line = line.concat(		"</a>");
	line = line.concat(	"</td>");
	line = line.concat("</tr>");
	
	$("#linesSubordinado").html(atual + line);
}

function onAddSubordinado(){
	$("#subordinado").addClass("clicked");
	$("#consulta_usuario").modal();
}

function alterarPermissaoAprovacao(id){
	if($("#"+id).hasClass("glyphicon-ok-sign")){

		$("#"+id).removeClass("glyphicon glyphicon-ok-sign green").addClass("glyphicon glyphicon-remove-sign red");

		if(id.indexOf("aprCusto") >= 0)
			$("#"+id+"Alcada").attr("disabled", true);
	}else{
		$("#"+id).removeClass("glyphicon glyphicon-remove-sign red").addClass("glyphicon glyphicon-ok-sign green");

		if(id.indexOf("aprCusto") >= 0)
			$("#"+id+"Alcada").removeAttr("disabled");
	}
}

function removerLinha(id){
	$("#linha"+id).remove();
}

</script>
<body>
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 
	
	<div id="content">	
		<div id="content-title">
			<h4>Hierarquia Usuário</h4>
		</div>
		
		<div id="content-body">
			<form action="salvarFornecedor" id="frmSalvarFornecedor" method="POST">
				<input type="hidden" id="mensagemRetorno" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				<div class="row">
				  	<div class="col-md-6 form-group req">
				   		<label for="nomeUsuario">Usuario</label>
				    	<input type="hidden" id="idusuario" name="idUsuario" class="idAdministrador" value="1">
				    	<input type="text" class="form-control" id="usuario" maxlength="11" value="${obj.usuario.razaoSocial}" readonly="readonly">
				  	</div>
				  	<div class="col-md-1 form-group paddingleft0">
				  		<label for="nomeUsuario">&nbsp;</label>
				  		<a href="#" target="_self" class="form-control icon-search" name="usuario" id="clickUsuario"><span class="glyphicon glyphicon-search"></span></a>
				  	</div>
				</div>
				<div class="row">&nbsp;</div>
				<table  class="table table-hover table-bordered table-striped margin0" >	
					<thead>
						<tr style="text-align: center">
							<th width="90%" colspan="3"><b>Usuário Subordinado</b>&nbsp;<span id="qtdSubordinado" class="badge">${qtdSubordinado}</span></th>
							<th width="10%" class="text-center"><a href="#" name="subordinado" id="subordinado" onclick="onAddSubordinado()"><span class="glyphicon glyphicon-plus"></span></a></th>
						</tr>
					</thead>
					<tbody id="linesSubordinado">
						<c:forEach items="${objFornecedor.representantes}" var="rep" varStatus="i">
							<tr>
								<td class="text-middle">
									${rep.apelido}
									<form:hidden path="objFornecedor.representantes[${i.index}].idUsuarioSub" />
								</td>
								<td class="text-center text-middle">
									<a href="#" id="${rep.uuid}" onclick="verificaFornecedor('${rep.idUsuarioSub}','${rep.uuid}', 'qtdSubordinado','linesSubordinado', 'representantes');">
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
	
	<c:import url="UsuarioModalLista.jsp"/>
	<c:import url="RepresentanteModalLista.jsp"/>
	
	<c:import url="/WEB-INF/views/components/footer.jsp">
		<c:param name="salvar" value="cancelar_salvar" />
	</c:import>
</body>
</html>
