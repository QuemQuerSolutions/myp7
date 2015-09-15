function clearAll(){
	$(":text").each(function () {
		$(this).val("");
    });
 
    $(":radio").each(function () {
    	$(this).prop({ checked: false })
    });
 
//    $("select").each(function () {
//    	$(this).val("");
//    });	
}

function isValidRequired(){
	clearRequired();
	var isValid = true;
	$(".req > input").each(function(){
		if($(this).val() === "")
			isValid = setRequired($(this).parent());
	});
	
	return isValid;
}


function clearRequired(){
	$("div").removeClass("has-error");
}

function setRequired(div){
	$(div).addClass("has-error");
	return false;
}


function refreshDisabled(){
	$("input").each(function(){ $(this).attr("readonly", false); });
	$(".disabled").each(function(){ $(this).attr("readonly", true);	});
}

function alerta(msg, type) {
	humane.log(msg, { baseCls: 'humane-jackedup', addnCls: 'humane-jackedup-'+type });
}

function isEmpty(value){
	value = $.trim($(value).val());
	return (!value ? true : false);
}

function addRequired(div){
	$(div).attr("class","form-group has-error");
	return false;
}

function getEmptyValidation(){
	var qtd = arguments.length;
	var isValid = true;
	for (var i = 0; i < qtd; i++){
		var div = arguments[i];
		if(isEmpty("#".concat($(div.concat(" > input")).attr("id"))))
			isValid = addRequired(div);
	}
	return isValid;
}

function validarCNPJ(cnpj) {
	 
    cnpj = cnpj.replace(/[^\d]+/g,'');
 
    if(cnpj == '') return false;
     
    if (cnpj.length != 14)
        return false;
 
    // Elimina CNPJs invalidos conhecidos
    if (cnpj == "00000000000000" || 
        cnpj == "11111111111111" || 
        cnpj == "22222222222222" || 
        cnpj == "33333333333333" || 
        cnpj == "44444444444444" || 
        cnpj == "55555555555555" || 
        cnpj == "66666666666666" || 
        cnpj == "77777777777777" || 
        cnpj == "88888888888888" || 
        cnpj == "99999999999999")
        return false;
         
    // Valida DVs
    tamanho = cnpj.length - 2
    numeros = cnpj.substring(0,tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0))
        return false;
         
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0,tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1))
          return false;
           
    return true;
    
}

function FormatarCnpj(cnpj) {
    cnpj = cnpj.replace( /\D/g , ""); //Remove tudo o cnpj que não é dígito
    cnpj = cnpj.replace( /^(\d{2})(\d)/ , "$1.$2"); //Coloca ponto entre o segundo e o terceiro dígitos
    cnpj = cnpj.replace( /^(\d{2})\.(\d{3})(\d)/ , "$1.$2.$3"); //Coloca ponto entre o quinto e o sexto dígitos
    cnpj = cnpj.replace( /\.(\d{3})(\d)/ , ".$1/$2"); //Coloca uma barra entre o oitavo e o nono dígitos
    cnpj = cnpj.replace( /(\d{4})(\d)/ , "$1-$2"); //Coloca um hífen depois do bloco de quatro dígitos
    return cnpj;
}


function validaEmail(email){
	var validar = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if (!validar.test(email))
		return false;
	return true;
}

function go(destino){
	if(destino.indexOf("#") > -1)
		$(destino).submit();
	else
		window.location = destino;
}

function getRadioButton(radioChecked){
	var tamanho = radioChecked.length;
	for(var i=0;i< tamanho;i++) {		
		if(radioChecked[i].checked) {			
			return radioChecked;		
		}	
	}
}

function controlTabs(tab){
	$(".nav-tabs li").each(function(){
		$(this).removeClass("active");
	});
	
	$(tab).parent().addClass("active");
	$("#content-tabs > div").attr("hidden", true);
	$("#"+$(tab).attr("contextmenu")).attr("hidden", false);
}