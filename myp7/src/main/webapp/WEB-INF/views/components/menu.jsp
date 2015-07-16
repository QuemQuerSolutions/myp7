<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

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
			<myp7:itemMenu label="Produto" link="Produto" icon="glyphicon-plus" />
			<myp7:itemMenu label="Embalagem" link="Embalagem" icon="glyphicon-barcode" />
			<myp7:itemMenu label="Manutenção de Custos" link="ManutencaoCustos" icon="glyphicon-usd"/>
			<myp7:itemMenu label="Configurações" link="Configuracao" icon="glyphicon-cog"/>
		</ul>
	</div>
</div>