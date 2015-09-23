<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
function addLineRepresentanteTab(representante){
	var line = "", id = representante.idRepresentante;
	if(isExist("linesRepresentanteTab", "idRepresentante", id)) return;
	
	var atual = $("#linesRepresentanteTab").html(), uuid = guid();
	
	line = line.concat("<tr>");
	line = line.concat("<td class='text-middle'>", representante.apelido);
		line = line.concat("<input type='hidden' name='obj.representantes[0].idRepresentante' id='pk' value='", id, "'/>");
	line = line.concat("</td>");
	
	line = line.concat("<td class='text-center text-middle'>");
		line = line.concat("<a href='#' id='",uuid,"' onclick='onRemoveLine(\"",uuid,"\", qtdRepresentante)' >");
			line = line.concat("<span class='glyphicon glyphicon-remove red'></span>");
		line = line.concat("</a>");
	line = line.concat("</td>");

	line = line.concat("</tr>");
	
	$("#linesRepresentanteTab").html(atual + line);
	addContador(qtdRepresentante);
	reindex("#linesRepresentanteTab", "representantes");
}
</script>
<div class="row" id="RepresentanteTabLista" hidden="true">
	<div class="col-md-12">
		<table  class="table table-hover table-bordered table-striped margin0">
			<thead>
				<tr style="text-align: center">
					<th width="90%">Representante</th>
					<th width="10%" class="text-center"><a href="#" onclick="onAddRepresentante()"><span class="glyphicon glyphicon-plus"></span></a></th>
				</tr>
			</thead>
			<tbody id="linesRepresentanteTab">
			<c:forEach items="${obj.representantes}" var="rep" varStatus="i">
				<tr>
					<td class="text-middle">
						${rep.apelido}
						<form:hidden path="obj.representantes[${i.index}].idRepresentante"/>
					</td>
					<td class="text-center text-middle">
						<a href="#" id="${rep.uuid}" onclick="onRemoveLine('${rep.uuid}', qtdRepresentante)" >
							<span class="glyphicon glyphicon-remove red"></span>
						</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>	
	</div>
</div>