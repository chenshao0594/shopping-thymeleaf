(function() {
		
	toggle = function toggle (item) {
			var idx = selectedOptions.indexOf(item);
			if (idx > -1) {
				selectedOptions.splice(idx, 1);
			}else {
				selectedOptions.push(item);
			}
			alert();
		};

})();
