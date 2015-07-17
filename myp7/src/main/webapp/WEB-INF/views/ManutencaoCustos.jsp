<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
	
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Manutenção de Custos</h4>
		</div>
		
		<div id="content-header">
		</div>
		
		<div id="content-body">
			<table class="table table-hover table-bordered table-striped">
				<thead>
					<tr style="text-align: center">
						<th>Código</th>
						<th>Descrição</th>
						<th>Valor Anterior</th>
						<th>Valor Novo</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<c:import url="components/footer.jsp">
		<c:param name="salvar" value="salvar" />
	</c:import>
</body>
</html>