<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<myp7:menu label="Produto" link="Produto" icon="glyphicon-plus" />
<myp7:menu label="Manutenção de Custos" link="ManutencaoCustos" icon="glyphicon-usd"/>



<li>
	<myp7:menuComSub label="Cadastro" icon="glyphicon-pencil" />
	<ul>
		<myp7:subMenu label="Usuário" link="ListaUsuario" />
		<myp7:subMenu label="Comprador" link="Comprador" />
		<myp7:subMenu label="Representante" link="Representante" />
		<myp7:subMenu label="Embalagem" link="Embalagem" />
		<myp7:subMenu label="Fornecedor" link="Fornecedor" />
	</ul>
</li>

<li>
	<myp7:menuComSub label="Aprovações" icon="glyphicon-check" />
	<ul>
		<myp7:subMenu label="Aprovação de Produto" link="ProdutoAprovacao" />
		<myp7:subMenu label="Aprovação de Custos" link="CustoAprovacao" />
	</ul>
</li>


<li>
	<myp7:menuComSub label="Relatórios" icon="glyphicon-file" />
	<ul>
		<myp7:subMenu label="Relatório de Estoque" link="RelatorioEstoque" />
		<myp7:subMenu label="Relatório de Títulos" link="RelatorioAcordoComercial" />
	</ul>
</li>


<myp7:menu label="Configurações" link="Configuracao" icon="glyphicon-cog"/>