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