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
  
  $("input[type=number]").keyup(function(e){
	  e.preventDefault();
	  var max = $(this).attr("max").length;
	  var atual = $(this).val().length;
	  if(atual > max){
		  $(this).val($(this).val().substring(0,max));
	  }
  });
  
  $(".onlyNumber").keydown(function (e) {
	  onlyNumber(e);
  });
  
});

function onlyNumber(e){
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
         // Allow: Ctrl+A, Command+A
        (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
         // Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
             // let it happen, don't do anything
             return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
}
function guid() {
    function s4() {
	    return Math.floor((1 + Math.random()) * 0x10000)
	      .toString(16)
	      .substring(1);
	}
	return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
	    s4() + '-' + s4() + s4() + s4();
}