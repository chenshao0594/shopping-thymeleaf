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


//if ($file) {
//    DataUtils.toBase64($file, function(base64Data) {
//        $scope.$apply(function() {
//            attachment.image = base64Data;
//            attachment.imageContentType = $file.type;
//        });
//    });
//}
//
//
//
