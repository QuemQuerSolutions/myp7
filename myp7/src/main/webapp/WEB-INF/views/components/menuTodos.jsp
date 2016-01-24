<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<myp7:menu label="Produto" link="/admin/Produto" icon="glyphicon-plus" />
<myp7:menu label="Manuten��o de Custos" link="/admin/ManutencaoCustos" icon="glyphicon-usd"/>

<li>
	<myp7:menuComSub label="Cadastro" icon="glyphicon-pencil" />
	<ul>
		<myp7:subMenu label="Usu�rio" link="/admin/ListaUsuario" />
		
		<myp7:subMenu label="Comprador" link="/admin/Comprador" />
		<myp7:subMenu label="Representante" link="/admin/Representante" />
		<myp7:subMenu label="Embalagem" link="/admin/Embalagem" />
		<myp7:subMenu label="Fornecedor" link="/admin/Fornecedor" />
		<myp7:subMenu label="Parametriza��o Usu�rio" link="/admin/UsuarioHierarquia" />
	</ul>
</li>

<li>
	<myp7:menuComSub label="Aprova��es" icon="glyphicon-check" />
	<ul>
		<myp7:subMenu label="Aprova��o de Produto" link="/admin//ProdutoAprovacao" />
		<myp7:subMenu label="Aprova��o de Custos" link="/admin/CustoAprovacao" />
	</ul>
</li>
<li>
	<myp7:menuComSub label="Relat�rios" icon="glyphicon-file" />
	<ul>
		<myp7:subMenu label="Relat�rio de Estoque" link="/admin/RelatorioEstoque" />
		<myp7:subMenu label="Relat�rio de T�tulos" link="/admin/RelatorioAcordoComercial" />
	</ul>
</li>

<myp7:menu label="Configura��es" link="/admin/Configuracao" icon="glyphicon-cog"/>