<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<myp7:menu label="Produto" link="Produto" icon="glyphicon-plus" />
<myp7:menu label="Manuten��o de Custos" link="ManutencaoCustos" icon="glyphicon-usd"/>



<li>
	<myp7:menuComSub label="Cadastro" icon="glyphicon-pencil" />
	<ul>
		<myp7:subMenu label="Usu�rio" link="ListaUsuario" />
		<myp7:subMenu label="Comprador" link="Comprador" />
		<myp7:subMenu label="Representante" link="Representante" />
		<myp7:subMenu label="Embalagem" link="Embalagem" />
		<myp7:subMenu label="Fornecedor" link="Fornecedor" />
	</ul>
</li>

<li>
	<myp7:menuComSub label="Aprova��es" icon="glyphicon-check" />
	<ul>
		<myp7:subMenu label="Aprova��o de Produto" link="ProdutoAprovacao" />
		<myp7:subMenu label="Aprova��o de Custos" link="CustoAprovacao" />
	</ul>
</li>


<li>
	<myp7:menuComSub label="Relat�rios" icon="glyphicon-file" />
	<ul>
		<myp7:subMenu label="Relat�rio de Estoque" link="RelatorioEstoque" />
		<myp7:subMenu label="Relat�rio de T�tulos" link="RelatorioAcordoComercial" />
	</ul>
</li>


<myp7:menu label="Configura��es" link="Configuracao" icon="glyphicon-cog"/>