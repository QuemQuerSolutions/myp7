<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	$("#btnNovo").click(function(){
		$(this).attr('data-toggle','modal');
		$(this).attr('data-target','#nova_embalagem');
	});
});
</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 
		<table style="margin-left: 60px; margin-top: 10px;" border="1" width="95%">
			<tr>
				<td colspan="3">
					Embalagem
				</td>
			</tr>
			<tr>
				<td  colspan="3">
					<form action="CarregaListaEmbalagem" name="frmEmbalagem">
						<table width="100%">
							<tr>
								<td>Sigla <br /><input type="text" name="sigla"></td>
								<td>Descri��o <br /><input type="text" name="descricao"></td>
								<td><input type="submit" value="Pesquisar" /></td>
								<td><input type="button" value="Limpar" /></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr style="text-align: center">
				<th>Sigla</th>
				<th>Descri��o</th>
				<th>Quantidade</th>
			</tr>
			<tr>
				<c:forEach items="${lista}" var="embalagem">
					<td>${embalagem.siglaEmbalagem}</td>
					<td>${embalagem.nomeEmbalagem}</td>
					<td>${embalagem.qtdEmbalagem}</td>
				</c:forEach>
			</tr>
		</table>
	<c:import url="EmbalagemInserir.jsp" />
	<c:import url="components/footer.jsp">
		<c:param name="novo" value="nova_embalagem" />
	</c:import>
</body>
</html>