<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

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
			<form>
				<div class="row">
					<div class="col-md-4">
						<label for="fornecedor" class="control-label">Fornecedor</label>
					</div>
					<div class="col-md-2">
						<label for="uf" class="control-label">-</label>
					</div>
					<div class="col-md-6">
						<label for="empresa" class="control-label">Empresa</label>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-4">
						<select id="fornecedor" name="fornecedor" class="form-control" autofocus="autofocus">
				  			<option value="-1">Selecione um Fornecedor</option>
				  			<c:forEach var="representante" items="${representantes}">
				  				<option value="${representante.idRepresentante}">${representante.apelido}</option>
   							</c:forEach>				  			
				  		</select>
					</div>
					<div class="col-md-2">
						<select id="uf" name="uf" class="form-control" autofocus="autofocus">
				  			<option value="-1">-</option>
				  			<c:forEach var="uf" items="${ufs}">
				  				<option value="${uf}">${uf}</option>
   							</c:forEach>				  			
				  		</select>
					</div>
					<div class="col-md-6">
						<select id="empresa" name="empresa" class="form-control">
				  			<option value="-1">Selecione uma Empresa</option>
				  			<c:forEach var="empresa" items="${empresas}">
				  				<option value="${empresa.idEmpresa}">${empresa.nomeReduzido}</option>
   							</c:forEach>
				  		</select>
					</div>
				</div>
			</form>
		</div>
		
		<div id="content-header">
			<form>
				<div class="row">
					<div class="col-md-2">
						<label for="tipo" class="control-label">Tipo</label>
					</div>
					<div class="col-md-2">
						<label for="idProduto" class="control-label">Código</label>
					</div>
					<div class="col-md-6">
						<label for="desProduto" class="control-label">Descrição</label>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-2">
						<select id="tipo" name="tipo" class="form-control">
							<c:forEach var="filtro" items="${filtros}">
								<option value="${filtro.key}">${filtro.value}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-2">
						<div class="form-group" id="">
					    	<input type="number" 
					    			class="form-control onlyNumber campo-buscar" 
					    			id="idProduto"
					    			name="idProduto"
					    			min="0"
					    			max="99999999999"
					    			value="" />
					  	</div>
					</div>
					<div class="col-md-6">
						<div class="form-group" id="">
					    	<input type="text" 
					    			class="form-control campo-buscar" 
					    			id="desProduto" 
					    			name="desProduto"
					    			maxlength="100" 
					    			value=""/>
					  	</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<button type="button" class="btn ${theme}" id="pesquisar">Pesquisar</button>
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<button type="button" class="btn btn-default limpar" id="limpar">Limpar</button>
						</div>
					</div>
				</div>
			
			</form>
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
						<c:forEach var="lista" items="${fornecedorCusto}">
							<td>${lista.produto.idProduto}</td>
							<td>${lista.produto.desProduto}</td>
							<td>${lista.valorFormatado}</td>
						<td>
					    	<input type="text" class="form-control" id="valorNovo" name="valorNovo" maxlength="10" />
						</td>
						</c:forEach>
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