<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row" id="EmpresaTabLista">
	<div class="col-md-12">
		<table  class="table table-hover table-bordered table-striped margin0">
			<thead>
				<tr style="text-align: center">
					<th width="70%">Empresa</th>
					<th width="20%">Alçada</th>
					<th width="10%" class="text-center"><a href="#"><span class="glyphicon glyphicon-plus"></span></a></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${obj.empresa}" var="emp" varStatus="i">
				<tr>
					<td class="text-middle">${emp.nomeReduzido}</td>
					<td>
						<form:hidden path="obj.empresa[${i.index}].idEmpresa"/>
						<form:input path="obj.empresa[${i.index}].alcada" cssClass="form-control" />
					</td>
					<td class="text-center text-middle">
						<a href="#" id="empresa_${emp.idEmpresa}" onclick="onRemoveLine(empresa_${emp.idEmpresa}, qtdEmpresa)" >
							<span class="glyphicon glyphicon-remove red"></span>
						</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>	
	</div>	
</div>

