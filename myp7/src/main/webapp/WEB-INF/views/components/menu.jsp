<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="side-bar">
	<div> 
		<div id="title-menu" class="">
			<span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>&nbsp;Menu
		</div>
		<div id="close-arrow">
			<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
		</div>
	</div>
	<div id="menu">
		<ul id="menu-nav" class="box">
			<c:if test="${tipoUsuarioRetorno eq 'P'}">
				<myp7:itemMenu label="Produto" link="Produto" icon="glyphicon-plus" />
				<myp7:itemMenu label="Manutenção de Custos" link="ManutencaoCustos" icon="glyphicon-usd"/>
			</c:if>
			<c:if test="${tipoUsuarioRetorno eq 'R'}">
				<myp7:itemMenu label="Usuário" link="Usuario" icon="glyphicon glyphicon-user"/>
				<myp7:itemMenu label="Comprador" link="Comprador" icon="glyphicon-shopping-cart" />
				<myp7:itemMenu label="Embalagem" link="Embalagem" icon="glyphicon-barcode" />
				<myp7:itemMenu label="Fornecedor" link="Fornecedor" icon="glyphicon-bed" />
				<myp7:itemMenu label="Aprovação de Produto" link="ProdutoAprovacao" icon="glyphicon-check" />
				<myp7:itemMenu label="Configurações" link="Configuracao" icon="glyphicon-cog"/>
			</c:if>
		</ul>
	</div>
</div>