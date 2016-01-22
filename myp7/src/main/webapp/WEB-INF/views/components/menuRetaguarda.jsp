<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<li>
	<myp7:menuComSub label="Cadastro" icon="glyphicon-pencil" />
	<ul>
		<myp7:subMenu label="Usu�rio" link="/retaguarda/ListaUsuario" />
		<myp7:subMenu label="Comprador" link="/retaguarda/Comprador" />
		<myp7:subMenu label="Representante" link="/retaguarda/Representante" />
		<myp7:subMenu label="Embalagem" link="/retaguarda/Embalagem" />
		<myp7:subMenu label="Fornecedor" link="/retaguarda/Fornecedor" />
		<myp7:subMenu label="Parametriza��o Usu�rio" link="/retaguarda/UsuarioHierarquia" />
	</ul>
</li>

<li>
	<myp7:menuComSub label="Aprova��es" icon="glyphicon-check" />
	<ul>
		<myp7:subMenu label="Aprova��o de Produto" link="/retaguarda/ProdutoAprovacao" />
		<myp7:subMenu label="Aprova��o de Custos" link="/retaguarda/CustoAprovacao" />
	</ul>
</li>
<li>
	<myp7:menuComSub label="Relat�rios" icon="glyphicon-file" />
	<ul>
		<myp7:subMenu label="Relat�rio de Estoque" link="/retaguarda/RelatorioEstoque" />
		<myp7:subMenu label="Relat�rio de T�tulos" link="/retaguarda/RelatorioAcordoComercial" />
	</ul>
</li>

<myp7:menu label="Configura��es" link="/retaguarda/Configuracao" icon="glyphicon-cog"/>