<%@ taglib tagdir="/WEB-INF/tags" prefix="myp7"%>

<myp7:itemMenu label="Usu�rio" link="ListaUsuario" icon="glyphicon glyphicon-user"/>
<myp7:itemMenu label="Comprador" link="Comprador" icon="glyphicon-shopping-cart" />
<myp7:itemMenu label="Representante" link="Representante" icon="glyphicon-briefcase" />
<myp7:itemMenu label="Embalagem" link="Embalagem" icon="glyphicon-barcode" />
<myp7:itemMenu label="Fornecedor" link="Fornecedor" icon="glyphicon-bed" />
<myp7:itemMenu label="Aprova��o de Produto" link="ProdutoAprovacao" icon="glyphicon-check" />
<myp7:itemMenu label="Aprova��o de Custos" link="CustoAprovacao" icon="glyphicon-check" />

<li>
	<a href="#" target="_self">
		<span class="glyphicon glyphicon-file"></span>
		 &nbsp;Relat�rios<span class="changeIcon glyphicon glyphicon-menu-down menu-down"></span> 
	</a>
	<ul>
		<li class="submenu">
			<a href="#" target="_self">Relat�rio de Estoque</a>
		</li>
		<li class="submenu">
			<a href="#" target="_self">Relat�rio de T�tulos</a>
		</li>
	</ul>
</li>

<myp7:itemMenu label="Configura��es" link="Configuracao" icon="glyphicon-cog"/>