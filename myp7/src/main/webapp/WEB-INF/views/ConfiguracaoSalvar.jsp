<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<html>
	<c:import url="components/imports.jsp" />
	
<script type="text/javascript">
$(document).ready(function(){
	$("#btnSalvar").click(function(){
		if(!$("#themeselected").val()){
			alerta("Selecione um tema.", "warning");
		}else{
			$("#frmTheme").submit();
		}
	});
});

function onClickTheme(tema){
	$("#themeselected").val("theme-".concat(tema));
}
</script>
<body>
	<c:import url="components/header.jsp" />
	<c:import url="components/menu.jsp" /> 
	
	<div id="content" class="hackrow">
		<div id="content-title">
			<h4>Escolha o tema do sistema</h4>
		</div>
		<form action="SalvarTema" id="frmTheme" method="POST">
			<input type="hidden" id="themeselected" name="themeselected" value="" />
		</form>
		<div class="row">
			<div class="col-md-1">&nbsp;</div>
			<div class="col-md-2"><myp7:optionTheme theme="orange" /></div>
			<div class="col-md-1">&nbsp;</div>
			<div class="col-md-2"><myp7:optionTheme theme="red" /></div>
			<div class="col-md-1">&nbsp;</div>
			<div class="col-md-2"><myp7:optionTheme theme="blue" /></div>
		</div>
		
		<div class="row">&nbsp;</div>
		
		<div class="row">
			<div class="col-md-1">&nbsp;</div>
			<div class="col-md-2"><myp7:optionTheme theme="grey" /></div>
			<div class="col-md-1">&nbsp;</div>
			<div class="col-md-2"><myp7:optionTheme theme="green" /></div>
		</div>
		
		<div class="row">&nbsp;</div>
	</div>
	
	<c:import url="components/footer.jsp">
		<c:param name="salvar" value="salvar" />
	</c:import>
</body>
</html>