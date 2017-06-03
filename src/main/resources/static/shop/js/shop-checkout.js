$(function(){
		initBindings();
		initState();
});

function initBindings() {
	/*   country  */
	$(".field").change(function(){
	    $(this).css("background-color","#FFFFCC");
	  });

	
	$("#open-cart").click(function(e) {
		//nothing required
	});
	
}

