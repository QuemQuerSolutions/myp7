<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<html>
	<c:import url="components/imports.jsp" />
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#empresa").attr("disabled", true);
			
			$("#btnPesquisar").click(function(){
				pesquisarManutencaoCusto(true);
			});

			$("#fornecedor").change(function(){
				pesquisarManutencaoCusto(false);
			});
			
			$("#empresa").change(function(){
				pesquisarManutencaoCusto(false);
			});		

			$("#btnLimpar").click(function(){
				$("#idProduto").val("");
				$("#desProduto").val("");
			});

			$("#uf").change(function(){
				if($("#uf").val() != "-1"){
					$("#empresa").removeAttr("disabled");
					atualizarComboEmpresa();
				}else{
					$("#empresa").html("<option value='-1'>Selecione uma Empresa</option>");
					$("#empresa").attr("disabled", true);
				}
				
			});
			
			$("#btnSalvar").click(function(){
				$(".valorNovo").each(function() {
					if($.trim($(this).val()) != "" ){
				    	salvarManutencaoCusto($(this).prop("id").substring(9), $.trim($(this).val()), $("#valorAtual"+$(this).prop("id").substring(9)).html());
					}
				});
			});
		});

		function salvarManutencaoCusto(id, novoValor, valorAnterior){
			$.ajax({
				type: "POST",
		        data: { id:id, novoValor:novoValor, valorAnterior:valorAnterior },
		        url : 'atuaizaManutencaoCusto',
		        success : function(data) {
			        if(data != "false"){
			        	var valores = data.split("$");
		        		$("#valorAnterior"+id).html(valores[0]);
		        		$("#valorAtual"+id).html(valores[1]);
		        		$("#valorNovo"+id).val("");

		        		alerta("Valores salvos com sucesso!", "success");
			        }else{
			        	alerta("Erro ao salvar novos valores.", "warning");
					}
		        }
		    });
		}
		
		function pesquisarManutencaoCusto(alertar){
			if(validaCamposObrigatorios(alertar)){
				$.ajax({
					type: "POST",
			        data: { fornecedor:$("#fornecedor").val(), empresa:$("#empresa").val(), tipo:$("#tipo").val(), codigo:$("#idProduto").val(), descricao:$("#desProduto").val() },
			        url : 'consultaManutencaoCusto',
			        success : function(data) {
			        	$("#resultado").html(data);
			        }
			    });
			}
		}

		function atualizarComboEmpresa(){
			$.ajax({
				type: "POST",
		        data: { uf:$("#uf").val() },
		        url : 'consultaEmpresaPorUF',
		        success : function(data) {
		        	$("#empresa").html(data);
		        }
		    });
		}

		function validaCamposObrigatorios(alertar){
			var isValid = true;
			var texto;

			if($.trim($("#idProduto").val()) == "" && $.trim($("#desProduto").val()) == ""){
				isValid = addRequired("#idProdutoDiv");
				addRequired("#desProdutoDiv");
				texto = "Preencha o campo código ou descrição.";
			}else if($("#tipo").val() == 1 && $.trim($("#idProduto").val()) != "" && !$.isNumeric($.trim($("#idProduto").val()))){
				isValid = addRequired("#idProdutoDiv");
				texto = "A opção código só permite números no campo código.";
			}
			
			if($("#uf").val() != "-1"){
				if($("#empresa").val() == "-1"){
					isValid = addRequired("#empresaDiv");
					$("#empresaDiv").addClass("col-md-6");
					texto = "Selecione uma Empresa.";
				}
			}else{
				isValid = addRequired("#ufDiv");
				$("#ufDiv").addClass("col-md-2");
				texto = "Selecione uma UF.";
			}
			
			if($("#fornecedor").val() == "-1"){
				isValid = addRequired("#fornecedorDiv");
				$("#fornecedorDiv").addClass("col-md-4");
				texto = "Selecione um Fornecedor.";
			}

			if(!isValid && alertar){
				alerta(texto, "warning");
			}else{
				removeClass();
			}

			return isValid;
		}

		function removeClass(){
			$(".has-error").each(function(){ 
				if($(this).prop("id") == "empresaDiv"){
					$(this).attr("class","col-md-6 form-group"); 
				}else if($(this).prop("id") == "fornecedorDiv"){
					$(this).attr("class","col-md-4 form-group"); 
				}else if($(this).prop("id") == "ufDiv"){
					$(this).attr("class","col-md-2 form-group"); 
				}else{
					$(this).attr("class","form-group"); 
				}
			});
		}
		
	</script>
	
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
						<label for="uf" class="control-label">UF</label>
					</div>
					<div class="col-md-6">
						<label for="empresa" class="control-label">Empresa</label>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-4 form-group" id="fornecedorDiv">
						<select id="fornecedor" name="fornecedor" class="form-control" autofocus="autofocus">
				  			<option value="-1">Selecione um Fornecedor</option>
				  			<c:forEach var="representante" items="${representantes}">
				  				<option value="${representante.idRepresentante}">${representante.apelido}</option>
   							</c:forEach>				  			
				  		</select>
					</div>
					<div class="col-md-2 form-group" id="ufDiv">
						<select id="uf" name="uf" class="form-control" autofocus="autofocus">
				  			<option value="-1">-</option>
				  			<c:forEach var="uf" items="${ufs}">
				  				<option value="${uf}">${uf}</option>
   							</c:forEach>				  			
				  		</select>
					</div>
					<div class="col-md-6 form-group" id="empresaDiv">
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
					<div class="col-md-2" id="tipoDiv">
						<select id="tipo" name="tipo" class="form-control">
							<c:forEach var="filtro" items="${filtros}">
								<option value="${filtro.key}">${filtro.value}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-2">
						<div class="form-group" id="idProdutoDiv">
					    	<input type="text" 
					    			class="form-control campo-buscar" 
					    			id="idProduto"
					    			name="idProduto"
					    			maxlength="20"
					    			value="" />
					  	</div>
					</div>
					<div class="col-md-6">
						<div class="form-group" id="desProdutoDiv">
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
							<button type="button" id="btnPesquisar" class="btn ${theme}" id="pesquisar">Pesquisar</button>
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<button type="button" id="btnLimpar" class="btn btn-default limpar" id="limpar">Limpar</button>
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
						<th>Valor Atual</th>
						<th>Valor Novo</th>
					</tr>
				</thead>
				<tbody id="resultado">
					<c:forEach var="lista" items="${fornecedorCusto}" varStatus="i">
						<tr>
							<td>${lista.produto.idProduto}</td>
							<td>${lista.produto.desProduto}</td>
							<td id="valorAnterior${i.count}">${lista.valorAnteriorFormatado}</td>
							<td id="valorAtual${i.count}">${lista.valorFormatado}</td>
							<td>
						    	<input type="text" class="form-control valorNovo" id="valorNovo${i.count}" name="valorNovo${i.count}" maxlength="10" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<c:import url="components/footer.jsp">
		<c:param name="salvar" value="salvar" />
	</c:import>
</body>
</html>