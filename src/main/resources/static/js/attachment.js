function readFile() {
  
  if (this.files && this.files[0]) {
    var FR= new FileReader();    
    FR.addEventListener("load", function(e) {
//      document.getElementById("img").src       = e.target.result;
//      document.getElementById("b64").value = e.target.result;
      $('#attachmentContent').val(e.target.result);
      $('#attachmentForm').submit();
      
    }); 
   FR.readAsArrayBuffer(this.files[0]);
  }
  

  
}


