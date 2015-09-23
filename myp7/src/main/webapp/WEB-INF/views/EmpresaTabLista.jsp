<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
function addLineEmpresaTab(empresa){
	var line = "", id = empresa.idEmpresa;
	if(isExist("linesEmpresaTab", "idEmpresa", id)) return;
	
	var atual = $("#linesEmpresaTab").html(), uuid = guid();
	
	line = line.concat("<tr>");
	line = line.concat("<td class='text-middle'>", empresa.nomeReduzido, "</td>");

	line = line.concat("<td class='req'>");
		line = line.concat("<input type='hidden' name='obj.empresa[0].idEmpresa' id='pk' value='", id, "'/>");
		line = line.concat("<input type='text' name='obj.empresa[0].alcada' id='empresa0.alcada' class='form-control'>");
	line = line.concat("</td>");
	
	line = line.concat("<td class='text-center text-middle'>");
		line = line.concat("<a href='#' id='",uuid,"' onclick='onRemoveLine(\"",uuid,"\", qtdEmpresa)' >");
			line = line.concat("<span class='glyphicon glyphicon-remove red'></span>");
		line = line.concat("</a>");
	line = line.concat("</td>");
	
	line = line.concat("</tr>");
	
	$("#linesEmpresaTab").html(atual + line);
	addContador(qtdEmpresa);
	reindex("#linesEmpresaTab", "empresa");
}
//var uuid=guid();
</script>
<div class="row" id="EmpresaTabLista">
	<div class="col-md-12">
		<table  class="table table-hover table-bordered table-striped margin0">
			<thead>
				<tr>
					<th width="70%">Empresa</th>
					<th width="20%" class="req">Alçada</th>
					<th width="10%" class="text-center"><a href="#" onclick="onAddEmpresa()"><span class="glyphicon glyphicon-plus"></span></a></th>
				</tr>
			</thead>
			<tbody id="linesEmpresaTab">
			<c:forEach items="${obj.empresa}" var="emp" varStatus="i">
				<tr>
					<td class="text-middle">${emp.nomeReduzido}</td>
					<td class="req">
						<form:hidden path="obj.empresa[${i.index}].idEmpresa"/>
						<form:input path="obj.empresa[${i.index}].alcada" cssClass="form-control onlyNumber" />
					</td>
					<td class="text-center text-middle">
						<a href="#" id="${emp.uuid}"  onclick="onRemoveLine('${emp.uuid}', qtdEmpresa)" >
							<span class="glyphicon glyphicon-remove red"></span>
						</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>	
	</div>	
</div>

