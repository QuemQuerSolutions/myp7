<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<c:forEach items="${obj.empresa}" var="emp">
				<tr>
					<td>${emp.nomeReduzido}</td>
					<td>${emp.alcada}</td>
					<td class="text-center"><a href="#"><span class="glyphicon glyphicon-remove red"></span></a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>	
	</div>	
</div>

