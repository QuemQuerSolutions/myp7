<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<myp7:menu label="Produto" link="/portal/Produto" icon="glyphicon-plus" />
<myp7:menu label="Manutenção de Custos" link="/portal/ManutencaoCustos" icon="glyphicon-usd"/>

<li>
	<myp7:menuComSub label="Relatórios" icon="glyphicon-file" />
	<ul>
		<myp7:subMenu label="Relatório de Estoque" link="/portal/RelatorioEstoque" />
		<myp7:subMenu label="Relatório de Títulos" link="/portal/RelatorioAcordoComercial" />
	</ul>
</li>

<myp7:menu label="Configurações" link="/portal/Configuracao" icon="glyphicon-cog"/>