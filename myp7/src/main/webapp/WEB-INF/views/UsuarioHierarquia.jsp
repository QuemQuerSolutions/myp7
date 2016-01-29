<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<c:import url="/WEB-INF/views/components/imports.jsp" />
	
<script type="text/javascript">
var historicoAlcada;

$(document).ready(function() {
	if($("#mensagemRetorno").val()) alerta($("#mensagemRetorno").val(), $("#codMsgem").val() === "0" ? "success" :"warning");

	$("#btnCancelar").click(function(e){
		e.stopPropagation();
		go("UsuarioHierarquia");
	});
	
	$("#btnSalvar").click(function(e){
		e.stopPropagation();
		var salvar = true;
		
		if(!isValidRequired()){
			alerta("Preencha os campos obrigatórios.", "warning");
			return;
		}

		$("#linesSubordinado tr").each(function(){
			var id = $(this).attr('id').substr(5);
			var aprProd = $("#aprProd" + id).hasClass("glyphicon-ok-sign");
			var aprCusto = $("#aprCusto" + id).hasClass("glyphicon-ok-sign");
			var valorAlcada = $("#aprCusto" + id + "Alcada").val();

			if(($("#aprCusto" + id + "Alcada").val() == null || $.trim($("#aprCusto" + id + "Alcada").val()) == "") &&
					$("#aprCusto" + id).hasClass("glyphicon-ok-sign")){
				
				alerta("Favor preencher as alçadas dos usuários com permissão.", "warning");
				$("#idsUsuarioParametrosSubordinados").val("");
				salvar = false;
			}

			if(salvar){
				if($("#idsUsuarioParametrosSubordinados").val() == "")
					$("#idsUsuarioParametrosSubordinados").val(id + "," + aprProd + "," + aprCusto + "," + valorAlcada);
				else
					$("#idsUsuarioParametrosSubordinados").val($("#idsUsuarioParametrosSubordinados").val()
							+ ";" + id + "," + aprProd + "," + aprCusto + "," + valorAlcada);
			}
		});

		validaSubordinadoAntesDeSalvar();

		if(salvar){
			

		}
	});	

	$("#clickUsuario").click(function(e){
		var tabelaPopulada = true;
		$("#linesSubordinado tr").each(function(){
			tabelaPopulada = false;		
		});

		if(tabelaPopulada){
			e.stopPropagation();
			$(this).addClass("clicked");
			$("#consulta_usuario").modal();
		}
	});

});

function carregarUsuariosSubordinados(superior){
	var usuario = {idSuperior : superior}
	$.ajax({
		type: "GET",
		data :usuario,
        contentType: "application/json; charset=ISO-8859-1",
	    dataType: "json",
        url : 'pesquisarUsuarioAJAX',
        success : function(retornoList) {
        	if(retornoList.length > 0){
        		retornoList.forEach(function(usuario){
        			addLineSubordinadoTab(usuario);
        		});
        	}
        },
        error: function (xhr, textStatus, errorThrown) {
	    	console.log("Erro ao retornar lista: ",errorThrown)	;
	        alerta("Erro ao retornar lista","warning");
        }
    });
}

function validaSubordinadoAntesDeSalvar(){
	var idEAlcadaUsuarios = "";
	var usuarios = new Array();
	
	$("#linesSubordinado tr").each(function(){
		var idNum = $(this).attr('id').substr(5);
		var idAlcada = "aprCusto" + idNum + "Alcada";
		var idAlcadaHidden = "aprCusto" + idNum + "AlcadaHidden";
		var idAprCusto = "aprCusto" + idNum;
		var idAprCustoHidden = "aprCusto" + idNum + "Hidden";
		var idAprProd = "aprProd" + idNum;
		var idAprProdHidden = "aprProd" + idNum + "Hidden";

		var valorAlterado = parseInt($("#"+idAlcada).val());

		if($("#"+idAprProd).hasClass("glyphicon-ok-sign green"))
			var aprProd = 1;
		else
			var aprProd = 0;

		if($("#"+idAprCusto).hasClass("glyphicon-ok-sign green"))
			var aprCusto = 1;
		else
			var aprCusto = 0;		

		if(idEAlcadaUsuarios == "")
			idEAlcadaUsuarios = idNum + "," + valorAlterado + "," + aprProd + "," + aprCusto;
		else
			idEAlcadaUsuarios = idEAlcadaUsuarios + ";" + idNum + "," + valorAlterado + "," + aprProd;
			
		var usuario = {idNum : idNum, 
						idAlcadaHidden : idAlcadaHidden, 
						idAlcada : idAlcada, 
						idAprCustoHidden : idAprCustoHidden, 
						idAprCusto : idAprCusto, 
						idAprProdHidden : idAprProdHidden, 
						idAprProd : idAprProd};
		
		usuarios.push(usuario);
	});

	if(idEAlcadaUsuarios != ""){
		$.ajax({
			type: "GET",
	        contentType: "application/json; charset=ISO-8859-1",
	        url : 'verificaSubordinadosAJAX?idEAlcadaUsuarios='+idEAlcadaUsuarios,
	        success : function(retorno) {
	        	if(retorno){
	        		salvar();
	        	}else{
		        	alerta("Não é permitido alterar um subordinado com subordinados.","warning");
		        	usuarios.forEach(function(usu){
		    			$("#"+usu.idAlcada).val($("#"+usu.idAlcadaHidden).val());
		    			$("#"+usu.idAprCusto).removeClass().addClass($("#"+usu.idAprCustoHidden).val());
		    			$("#"+usu.idAprProd).removeClass().addClass($("#"+usu.idAprProdHidden).val());

		    			if($("#"+usu.idAprCustoHidden).val() == "glyphicon glyphicon-ok-sign green")
		    				$("#aprCusto"+usu.idNum+"Alcada").attr("disabled", false);
			        });
	        	}
	        },
	        error: function (xhr, textStatus, errorThrown) {
		    	console.log("Erro ao realizar validação: ",errorThrown)	;
		        alerta("Erro ao realizar validação","warning");
	        }
	    });		
	}else{
		salvar();
	}
}

function salvar(){
	if($("#aprProd").hasClass("glyphicon-ok-sign"))
		$("#aprProdBoo").val(1);
	else
		$("#aprProdBoo").val(0);
	$("#aprCustoBoo").val($("#aprCusto").hasClass("glyphicon-ok-sign"));
	
		go("#frmSalvarHierarquia");
}

function addLineSubordinadoTab(subordinado){
	var line = "", id = subordinado.idUsuario;
	if(isExist("linesSubordinado", "idUsuarioSub", id)) 
		return;
	
	var atual = $("#linesSubordinado").html(), uuid = guid();
	
	line = line.concat("<tr id='linha",id,"'>");
	
	if(subordinado.razaoSocial != null)
		line = line.concat("<td class='text-middle'>", subordinado.razaoSocial);
	else
		line = line.concat("<td class='text-middle'>", subordinado.razao);

	line = line.concat("	<input class=\"listaSubordinados\" type='hidden' name='objFornecedor.representantes[0].idUsuarioSub' id='pk' value='", id, "'/>");
	line = line.concat("</td>");

	line = line.concat("<td class='text-center text-middle'>");
	line = line.concat(		"<a href='#' onclick='alterarPermissaoAprovacao(\"aprProd",id,"\");'>");

	if(subordinado.flagAprovProduto != null && subordinado.flagAprovProduto == 1){
		line = line.concat(			"<span id=\"aprProd",id,"\" class='glyphicon glyphicon-ok-sign green'></span>&nbsp; Aprovar Produto");
		line = line.concat(			"<input type=\"hidden\" id=\"aprProd",id,"Hidden\" value=\"glyphicon glyphicon-ok-sign green\">");
	}else{
		line = line.concat(			"<span id=\"aprProd",id,"\" class='glyphicon glyphicon-remove-sign red'></span>&nbsp; Aprovar Produto");
		line = line.concat(			"<input type=\"hidden\" id=\"aprProd",id,"Hidden\" value=\"glyphicon glyphicon-remove-sign red\">");
	}
		
	line = line.concat(		"</a>");
	line = line.concat("</td>");

	line = line.concat("<td class='text-center text-middle'>");
	line = line.concat(		"<a href='#' onclick='alterarPermissaoAprovacao(\"aprCusto",id,"\");'>");
	
	if(subordinado.alcada != null && subordinado.alcada > 0){
		line = line.concat(			"<input type=\"hidden\" id=\"aprCusto",id,"Hidden\" value=\"glyphicon glyphicon-ok-sign green\">");
		line = line.concat(			"<span id=\"aprCusto",id,"\" class='glyphicon glyphicon-ok-sign green'></span>&nbsp; Aprovar Custo");
		line = line.concat(		"</a>");
		line = line.concat(		"&nbsp;&nbsp;<input type=\"number\" onkeyup='validarNumero(\"aprCusto",id,"Alcada\", ",$("#aprCustoAlcada").val(),")' id=\"aprCusto",id,"Alcada\" placeholder=\"Alçada\" value=\"",subordinado.alcada,"\">");
		line = line.concat(		"<input type=\"hidden\" id=\"aprCusto",id,"AlcadaHidden\" value=\"",subordinado.alcada,"\">");
	}else{
		line = line.concat(			"<input type=\"hidden\" id=\"aprCusto",id,"Hidden\" value=\"glyphicon glyphicon-remove-sign red\">");
		line = line.concat(			"<span id=\"aprCusto",id,"\" class='glyphicon glyphicon-remove-sign red'></span>&nbsp; Aprovar Custo");
		line = line.concat(		"</a>");
		line = line.concat(		"&nbsp;&nbsp;<input type=\"number\" onkeyup='validarNumero(\"aprCusto",id,"Alcada\", ",$("#aprCustoAlcada").val(),")' id=\"aprCusto",id,"Alcada\" placeholder=\"Alçada\" disabled>");
	}
	
	line = line.concat("</td>");
	
	line = line.concat("<td class='text-center text-middle'>");
	line = line.concat(		"<a href='#' id='",uuid,"' onclick='removerLinha(\"",id,"\");'>");
	line = line.concat(			"<span class='glyphicon glyphicon-remove red'></span>");
	line = line.concat(		"</a>");
	line = line.concat(	"</td>");
	line = line.concat("</tr>");
	
	$("#linesSubordinado").html(atual + line);
}

function onAddSubordinado(){
	if($.trim($("#usuarioSuperior").val()) != ""){
		if(($("#aprCusto").hasClass("glyphicon-ok-sign") && $.trim($("#aprCustoAlcada").val()) != "") || 
			$("#aprCusto").hasClass("glyphicon glyphicon-remove-sign red")){
			
			$("#subordinado").addClass("clicked");
			$("#consulta_usuario").modal();
		}else{
			alerta("Preencha o valor da alçada.", "warning");
		}
	}else{
		alerta("Selecione o usuário principal.", "warning");
	}
}

function validarNumero(id, max){
	var isSuperior = false;
	if(id == "aprCustoAlcada"){
		isSuperior = true;
		
		var tabelaPopulada = false;
		$("#linesSubordinado tr").each(function(){
			tabelaPopulada = true;		
		});

		if(tabelaPopulada || ($("#idSuperiorDoUsuarioSuperior").val() != null && $("#idSuperiorDoUsuarioSuperior").val() != "" )){
			$("#aprCustoAlcada").val(historicoAlcada);
			return;
		}
	}

	if($("#"+id).val() > max)
		$("#"+id).val(max);
	else if($("#"+id).val() < 0)
		$("#"+id).val(0);

	if(isSuperior)
		historicoAlcada = $("#"+id).val();
}

function alterarPermissaoAprovacao(id){

	var itemTabela = false;
	if(id == "aprProd" || id == "aprCusto"){

		if($("#idSuperiorDoUsuarioSuperior").val() != null && $("#idSuperiorDoUsuarioSuperior").val() != "" )
			return;
		
		var existente = false;
		$("#linesSubordinado tr").each(function(){
			existente = true;		
		});
		
		if(existente)
			return;
	}else{
		itemTabela = true;
	}
		
	if($("#"+id).hasClass("glyphicon-ok-sign")){

		$("#"+id).removeClass("glyphicon glyphicon-ok-sign green").addClass("glyphicon glyphicon-remove-sign red");

		if(id.indexOf("aprCusto") >= 0)
			$("#"+id+"Alcada").attr("disabled", true);
	}else{
		if(itemTabela){
			if(id.substr(0, 7) == "aprProd" && $("#aprProd").hasClass("glyphicon-remove-sign red")){
				return;
			}else if(id.substr(0, 8) == "aprCusto" && $("#aprCusto").hasClass("glyphicon-remove-sign red")){
				return;
			}
		}

		$("#"+id).removeClass("glyphicon glyphicon-remove-sign red").addClass("glyphicon glyphicon-ok-sign green");

		if(id.indexOf("aprCusto") >= 0)
			$("#"+id+"Alcada").removeAttr("disabled");
	}
}

function removerLinha(id){
	$("#linha"+id).remove();
}

</script>
<body>
	<c:import url="/WEB-INF/views/components/header.jsp" />
	<c:import url="/WEB-INF/views/components/menu.jsp" /> 
	
	<div id="content">	
		<div id="content-title">
			<h4>Hierarquia Usuário</h4>
		</div>
		
		<div id="content-body">
			<form action="salvarHierarquia" id="frmSalvarHierarquia" method="POST">
				<input type="hidden" id="mensagemRetorno" value="${mensagemRetorno}" />
				<input type="hidden" id="codMsgem" value="${codMsgem}" />
				<div class="row">
				  	<div class="col-md-6 form-group req">
				   		<label for="nomeUsuario">Usuário</label>
				    	<input type="hidden" id="idusuarioSuperior" name="idUsuario" class="idAdministrador" />
				    	<input type="text" class="form-control" id="usuarioSuperior" maxlength="11" readonly="readonly">
				  	</div>
				  	<div class="col-md-1 form-group paddingleft0">
				  		<input type="hidden" id="idsUsuarioParametrosSubordinados" name="idsUsuarioParametrosSubordinados" />
				  		<input type="hidden" id="idSuperiorDoUsuarioSuperior" name="idSuperior" />
				  		<label for="nomeUsuario">&nbsp;</label>
				  		<a href="#" target="_self" class="form-control icon-search" name="usuarioSuperior" id="clickUsuario"><span class="glyphicon glyphicon-search"></span></a>
				  	</div>
					<div class="col-md-2 form-group req">
						<br/><br/>
				  		<a href='#' onclick='alterarPermissaoAprovacao("aprProd");'>
							<span id="aprProd" class='glyphicon glyphicon-remove-sign red'></span>&nbsp; Aprovar Produto
							<input type="hidden" id="aprProdBoo" name="aprProd" />
						</a>
					</div>
					<div class="col-md-3 form-group req">
						<br/><br/>
						<a href='#' onclick='alterarPermissaoAprovacao("aprCusto");'>
							<span id="aprCusto" class='glyphicon glyphicon-remove-sign red'></span>&nbsp; Aprovar Custo
							<input type="hidden" id="aprCustoBoo" name="aprCustoBoo" />
						</a>
						&nbsp;&nbsp;<input type="number" id="aprCustoAlcada" name="aprCustoAlcada" onkeyup='validarNumero("aprCustoAlcada",100)' placeholder="Alçada" disabled>
				  	</div>
				</div>
				<div class="row">&nbsp;</div>
				<table  class="table table-hover table-bordered table-striped margin0" >	
					<thead>
						<tr style="text-align: center">
							<th width="90%" colspan="3"><b>Usuário Subordinado</b>&nbsp;<span id="qtdSubordinado" class="badge"></span></th>
							<th width="10%" class="text-center"><a href="#" name="subordinado" id="subordinado" onclick="onAddSubordinado()"><span class="glyphicon glyphicon-plus"></span></a></th>
						</tr>
					</thead>
					<tbody id="linesSubordinado"> </tbody>
				</table>
			</form>		
		</div>
	</div>
	
	<c:import url="UsuarioModalLista.jsp"/>
	<c:import url="RepresentanteModalLista.jsp"/>
	
	<c:import url="/WEB-INF/views/components/footer.jsp">
		<c:param name="salvar" value="cancelar_salvar" />
	</c:import>
</body>
</html>
