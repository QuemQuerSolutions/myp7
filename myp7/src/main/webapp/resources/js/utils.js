function clearAll(escopo){
	if(escopo == undefined) escopo = "";
	$(escopo + " :text").each(function () {
		$(this).val("");
    });
	
	$(escopo + " input[type=date]").each(function () {
		$(this).val("");
    });
	
	$(escopo + " input[type=number]").each(function () {
		$(this).val("");
    });
 
    $(escopo + " :radio").each(function () {
    	$(this).prop({ checked: false })
    });
 
    $(escopo + " select").each(function(){
    	$(this).children("option:selected").removeAttr("selected").end()
    	.children("option:first").attr("selected", "selected");
	});
}

function disableAll(){

	$(":text").each(function () {
		$(this).attr("disabled", true);
    });
 
    $(":radio").each(function () {
    	$(this).attr("disabled", true);
    });
 
    $("select").each(function () {
    	$(this).attr("disabled", true);
    });	
    
    $("#btnSalvar").hide();
}


function hasInformation(escopo){
	var hasValue = false;
	$(escopo + " input[type=text]").each(function(){
		if($(this).val() !== "")
			hasValue = true;
    });
	return hasValue;
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

function isValidSelectRequired(){
	clearRequired();
	var isValid = true;
	$(".req > select").each(function(){
		if($(this).val() == "-1")
			isValid = setRequired($(this).parent());
	});
	
	return isValid;
}

function isNumeric(num){
	var result = true;
	var array = num.split(',');
	
	if(array.length <= 0)
		return $.isNumeric(num);
	
	$.each(array , function(i, val) {
		result = result && $.isNumeric($.trim(val));
	});
	return result;
}

function clearRequired(){
	$(".has-error").removeClass("has-error");
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


function validarCPF(cpf) {  
    cpf = cpf.replace(/[^\d]+/g,'');    
    if(cpf == '') return false; 
    // Elimina CPFs invalidos conhecidos    
    if (cpf.length != 11 || 
        cpf == "00000000000" || 
        cpf == "11111111111" || 
        cpf == "22222222222" || 
        cpf == "33333333333" || 
        cpf == "44444444444" || 
        cpf == "55555555555" || 
        cpf == "66666666666" || 
        cpf == "77777777777" || 
        cpf == "88888888888" || 
        cpf == "99999999999")
            return false;       
    // Valida 1o digito 
    add = 0;    
    for (i=0; i < 9; i ++)       
        add += parseInt(cpf.charAt(i)) * (10 - i);  
        rev = 11 - (add % 11);  
        if (rev == 10 || rev == 11)     
            rev = 0;    
        if (rev != parseInt(cpf.charAt(9)))     
            return false;       
    // Valida 2o digito 
    add = 0;    
    for (i = 0; i < 10; i ++)        
        add += parseInt(cpf.charAt(i)) * (11 - i);  
    rev = 11 - (add % 11);  
    if (rev == 10 || rev == 11) 
        rev = 0;    
    if (rev != parseInt(cpf.charAt(10)))
        return false;       
    return true;   
}

function validaCNPJCPF(numDocumento){
	numDocumento = numDocumento.replace( /\D/g , "");
	return numDocumento.length < 14? validarCPF(numDocumento) : validarCNPJ(numDocumento);
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
    var tamanho = cnpj.length - 2
    var numeros = cnpj.substring(0,tamanho);
    var digitos = cnpj.substring(tamanho);
    var soma = 0;
    var pos = tamanho - 7;
    for (var i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    var resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
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

function FormatarCnpjCPF(numDocumento) {
	numDocumento = numDocumento.replace( /\D/g , ""); //Remove tudo o que não é dígito
    
    if (numDocumento.length < 14){//CPF
		numDocumento=numDocumento.replace(/(\d{3})(\d)/,"$1.$2")  //Coloca um ponto entre o terceiro e o quarto dígitos
		numDocumento=numDocumento.replace(/(\d{3})(\d)/,"$1.$2") //Coloca um ponto entre o terceiro e o quarto dígitos
		numDocumento=numDocumento.replace(/(\d{3})(\d{1,2})$/,"$1-$2") //Coloca um hífen entre o terceiro e o quarto dígitos
    	
    }else{//CPNPJ
    	
       	numDocumento = numDocumento.replace( /^(\d{2})(\d)/ , "$1.$2"); //Coloca ponto entre o segundo e o terceiro dígitos
    	numDocumento = numDocumento.replace( /^(\d{2})\.(\d{3})(\d)/ , "$1.$2.$3"); //Coloca ponto entre o quinto e o sexto dígitos
    	numDocumento = numDocumento.replace( /\.(\d{3})(\d)/ , ".$1/$2"); //Coloca uma barra entre o oitavo e o nono dígitos
    	numDocumento = numDocumento.replace( /(\d{4})(\d)/ , "$1-$2"); //Coloca um hífen depois do bloco de quatro dígitos
    }
    return numDocumento;
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

function onRemoveLine(idLine, contador){
	var cont = parseInt($("#".concat(contador)).text());
	$("#".concat(idLine)).parent().parent("tr").remove();
	$("#".concat(contador)).text(parseInt(--cont));
}

function addContador(contador){
	var cont = parseInt($(contador).text());
	$(contador).html(++cont);
}

function reindex(tabela, nomeObjeto){
	var cont = 0, name, nomeCampo = "";
	$(tabela + " tr").each(function(){
		$(this).children().children("input").each(function(){
			name = $(this).attr("name");
			nomeCampo = name.substring(name.lastIndexOf("]")+1, name.length);
			name = nomeObjeto.concat("[", cont, "]", nomeCampo);
			$(this).attr("name", name);
			$(this).attr("id", nomeObjeto.concat(cont, nomeCampo));
		});
		
		cont++;
	});
}

function isExist(tabBody, nomeID, id){
	var existente = false, inputHidden;
	$("#"+tabBody+" tr").each(function(){
		inputHidden = $(this).find('input[type=hidden]');
		if(inputHidden.attr("id").indexOf(nomeID) > -1){
			if(inputHidden.val() == id)
				existente = true;		
		} 
	});
	return existente;
}

function onClickLineModal(tBody,id){
	$("#".concat(tBody," tr")).each(function(){
		$(this).removeClass($("#theme").val());
		if($(this).children().html() == id){
			$(this).addClass($("#theme").val());
			return;
		}
	});	
}

function getValueLineModal(tBody){
	var data;
	$(tBody.concat(" tr")).each(function(){
		if($(this).hasClass($("#theme").val())){
			data = {id	: $(this).find('td[data-id]').text(),
					nome: $(this).find('td[data-nome]').text(),}
			return; 
		}
		
	});
	return data;
}
