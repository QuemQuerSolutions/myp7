$(document).ready(function () {
  $('#close-arrow').attr('style', 'display:none');
  $('#menu-nav > li > a').click(function(e){
    if ($(this).attr('class') != 'active'){
      $('#menu-nav li ul').slideUp();
      $(this).next().slideToggle();
      $('#menu-nav li a').removeClass('active');
      $('#menu-nav li a changeIcon').removeClass('glyphicon glyphicon-menu-down');
      $('#menu-nav li a changeIcon').addClass('glyphicon glyphicon-menu-left');
      $(this).addClass('active');
      $(this).find('changeIcon').removeClass('glyphicon glyphicon-menu-left');
      $(this).find('changeIcon').addClass('glyphicon glyphicon-menu-down');
    }
    
    fecharMenu(e);
  });

  $('#side-bar').click(function(){
      $(this).addClass('active');
      $('#close-arrow').removeAttr('style');
  });

  $( '#close-arrow' ).click(function(e) {
	  fecharMenu(e);
  });
  
  $('#side-bar').on('mouseleave', function(e){
	  fecharMenu(e);
  });
  
  $( "#target" ).blur(function() {
	  alert( "Handler for .blur() called." );
	});
  
  var fecharMenu = function(e){
      e.stopPropagation();
      $('#close-arrow').attr('style', 'display:none');
      $('#side-bar').removeClass('active');
  };
  
});


function alerta(msg, type) {
	humane.log(msg, { baseCls: 'humane-jackedup', addnCls: 'humane-jackedup-'+type });
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