<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
	$(document).ready(function() {
		$("#salvar").click(function(){
			salvar();
		});

		$(".campo-salvar").keypress(function(e){
		    if(e.which == 13) {
		        salvar();
		    }
		});

		$("#sigla").focus();
		
		if($("#mensagem").val() != ""){
			alerta($("#mensagem").val(), "warning");
		}		
	});

	function salvar(){
		if($.trim($("#sigla").val()) == ""){
			alerta("Favor preencher o campo sigla.", "warning");
		}else if($.trim($("#descricao").val()) == ""){
			alerta("Favor preencher o campo descrição.", "warning");
		}else if($.trim($("#quantidade").val()) == ""){
			alerta("Favor preencher o campo quantidade.", "warning");
		}else{
			$("#frmInserirEmbalagem").submit();
		}
	}
</script>

<div class="modal fade" id="nova_embalagem">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header theme-orange">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Inserir Embalagem</h4>
			</div>
			<div class="modal-body">
				<form action="InserirEmbalagem" id="frmInserirEmbalagem">
					<input type="hidden" id="mensagem" value="${mensagemRetorno}" />
					<div class="row">
						<div class="col-md-3">
							<div class="form-group">
						   		<label for="sigla">Sigla</label>
						    	<input type="text" class="form-control campo-salvar" id="sigla" name="siglaEmbalagem" maxlength="2" placeholder="Insira sigla">
						  	</div>
						</div>
	  					<div class="col-md-8">
							<div class="form-group">
						   		<label for="descricao">Descrição</label>
						    	<input type="text" class="form-control campo-salvar" id="descricao" name="nomeEmbalagem" maxlength="100" placeholder="Insira descrição">
						  	</div>
	  					</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<div class="form-group">
						   		<label for="quantidade">Quantidade</label>
						    	<input type="text" class="form-control campo-salvar" id="quantidade" name="qtdEmbalagem" maxlength="2" placeholder="Quantidade">
						  	</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-warning" id="salvar">Salvar</button>
			</div>
		</div>
	</div>
</div>