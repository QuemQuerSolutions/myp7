<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<myp7:menu label="Produto" link="/portal/Produto" icon="glyphicon-plus" />
<myp7:menu label="Manuten��o de Custos" link="/portal/ManutencaoCustos" icon="glyphicon-usd"/>

<li>
	<myp7:menuComSub label="Relat�rios" icon="glyphicon-file" />
	<ul>
		<myp7:subMenu label="Relat�rio de Estoque" link="/portal/RelatorioEstoque" />
		<myp7:subMenu label="Relat�rio de T�tulos" link="/portal/RelatorioAcordoComercial" />
	</ul>
</li>

<myp7:menu label="Configura��es" link="/portal/Configuracao" icon="glyphicon-cog"/>