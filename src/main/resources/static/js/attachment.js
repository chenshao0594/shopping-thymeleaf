function readFile() {
	var img = document.getElementById('inp');

	blobUtil.createBlob(img.src).then(function (blob) {
	  // ladies and gents, we have a blob
		alert(blob);
	}).catch(function (err) {
	  // image failed to load
		
		console.log(err);
	});

}


