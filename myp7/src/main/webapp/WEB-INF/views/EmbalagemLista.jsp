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

		if($("#mensagem").val() != ""){
			alerta($("#mensagem").val(), "warning");
		}
	});
</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 

	<div id="content">
		<div id="content-title">
			<h4>Inserir Embalagem</h4>
		</div>
		
		<table border="1">
			<tr>
				<td colspan="4">
					Embalagem
				</td>
			</tr>
			<tr>
				<td  colspan="4">
					<form action="CarregaListaEmbalagem" name="frmEmbalagem">
						<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
						
						<table width="100%">
							<tr>
								<td>Sigla <br /><input type="text" name="sigla"></td>
								<td>Descrição <br /><input type="text" name="descricao"></td>
								<td><input type="submit" value="Pesquisar" /></td>
								<td><input type="button" value="Limpar" /></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr style="text-align: center">
				<th>Sigla</th>
				<th>Descrição</th>
				<th>Quantidade</th>
				<th>Funções</th>
			</tr>
			<tr>
				<c:forEach items="${lista}" var="embalagem">
					<td>${embalagem.siglaEmbalagem}</td>
					<td>${embalagem.nomeEmbalagem}</td>
					<td>${embalagem.qtdEmbalagem}</td>
					<td><a href="editar?id=${embalagem.idEmbalagem}">Editar</a></td>
				</c:forEach>
			</tr>
		</table>
		
		<c:import url="EmbalagemInserir.jsp" />
	
	</div>
	<c:import url="components/footer.jsp">
		<c:param name="novo" value="nova_embalagem" />
	</c:import>
</body>
</html>