<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<li>
	<myp7:menuComSub label="Cadastro" icon="glyphicon-pencil" />
	<ul>
		<myp7:subMenu label="Usuário" link="/retaguarda/ListaUsuario" />
		<myp7:subMenu label="Comprador" link="/retaguarda/Comprador" />
		<myp7:subMenu label="Representante" link="/retaguarda/Representante" />
		<myp7:subMenu label="Embalagem" link="/retaguarda/Embalagem" />
		<myp7:subMenu label="Fornecedor" link="/retaguarda/Fornecedor" />
		<myp7:subMenu label="Parametrização Usuário" link="/retaguarda/UsuarioHierarquia" />
	</ul>
</li>

<li>
	<myp7:menuComSub label="Aprovações" icon="glyphicon-check" />
	<ul>
		<myp7:subMenu label="Aprovação de Produto" link="/retaguarda/ProdutoAprovacao" />
		<myp7:subMenu label="Aprovação de Custos" link="/retaguarda/CustoAprovacao" />
	</ul>
</li>
<li>
	<myp7:menuComSub label="Relatórios" icon="glyphicon-file" />
	<ul>
		<myp7:subMenu label="Relatório de Estoque" link="/retaguarda/RelatorioEstoque" />
		<myp7:subMenu label="Relatório de Títulos" link="/retaguarda/RelatorioAcordoComercial" />
	</ul>
</li>

<myp7:menu label="Configurações" link="/retaguarda/Configuracao" icon="glyphicon-cog"/>