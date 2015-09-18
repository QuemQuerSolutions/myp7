<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row" id="RepresentanteTabLista" hidden="true">
	<div class="col-md-12">
		<table  class="table table-hover table-bordered table-striped margin0">
			<thead>
				<tr style="text-align: center">
					<th width="90%">Representante</th>
					<th width="10%" class="text-center"><a href="#"><span class="glyphicon glyphicon-plus"></span></a></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${obj.representantes}" var="rep" varStatus="i">
				<tr>
					<td class="text-middle">
						${rep.apelido}
					<form:hidden path="obj.representantes[${i.index}].idRepresentante"/></td>
					<td class="text-center text-middle">
						<a href="#" id="representante_${emp.idRepresentante}" onclick="onRemoveLine(representante_${emp.idRepresentante}, qtdRepresentante)" >
							<span class="glyphicon glyphicon-remove red"></span>
						</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>	
	</div>
</div>