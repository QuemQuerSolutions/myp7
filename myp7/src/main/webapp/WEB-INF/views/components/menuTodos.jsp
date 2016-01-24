<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<myp7:menu label="Produto" link="/admin/Produto" icon="glyphicon-plus" />
<myp7:menu label="Manutenção de Custos" link="/admin/ManutencaoCustos" icon="glyphicon-usd"/>

<li>
	<myp7:menuComSub label="Cadastro" icon="glyphicon-pencil" />
	<ul>
		<myp7:subMenu label="Usuário" link="/admin/ListaUsuario" />
		
		<myp7:subMenu label="Comprador" link="/admin/Comprador" />
		<myp7:subMenu label="Representante" link="/admin/Representante" />
		<myp7:subMenu label="Embalagem" link="/admin/Embalagem" />
		<myp7:subMenu label="Fornecedor" link="/admin/Fornecedor" />
		<myp7:subMenu label="Parametrização Usuário" link="/admin/UsuarioHierarquia" />
	</ul>
</li>

<li>
	<myp7:menuComSub label="Aprovações" icon="glyphicon-check" />
	<ul>
		<myp7:subMenu label="Aprovação de Produto" link="/admin//ProdutoAprovacao" />
		<myp7:subMenu label="Aprovação de Custos" link="/admin/CustoAprovacao" />
	</ul>
</li>
<li>
	<myp7:menuComSub label="Relatórios" icon="glyphicon-file" />
	<ul>
		<myp7:subMenu label="Relatório de Estoque" link="/admin/RelatorioEstoque" />
		<myp7:subMenu label="Relatório de Títulos" link="/admin/RelatorioAcordoComercial" />
	</ul>
</li>

<myp7:menu label="Configurações" link="/admin/Configuracao" icon="glyphicon-cog"/>