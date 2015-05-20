<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<c:import url="aImports.jsp" />
	
<body>
<div class="flex-align">
    <div class="content-center">
    
        <div class="row">
			<h2 align="center">Bem vindo ao Portal Fornecedor</h2>
		</div>
<!-- 			<form action="efetuaLogin" method="POST"> -->
			<div class="row">
  				<div class="col-md-6">
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">
					  	<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					  </span>
					  <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
					</div>
				</div>
  			</div>
  			

<!-- 				  	<span class="glyphicon glyphicon-user" aria-hidden="true"></span> -->
				<label>Senha
					<input type="password" />
				</label>
			<div class="row">
				<input type="submit" value="OK">
			</div>
<!-- 			</form> -->
        </div>
    </div>

</body>
</html>
