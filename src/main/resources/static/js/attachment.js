function readFile() {
	var fileName = this.files[0].name;
	var fileSize = this.files[0].size;
	var contentType = this.files[0].type;
  if (this.files && this.files[0]) {
    var FR= new FileReader();    
    FR.addEventListener("load", function(e) {
      $('#attachment_content').val(e.target.result);
      $('#attachment_size').val(fileSize);
      $('#attachment_name').val(fileName);
      $('#attachment_contentType').val(contentType);
      $('#attachmentForm').submit();
     
    }); 
   FR.readAsArrayBuffer(this.files[0]);
  }
  

  
}


