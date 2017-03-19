function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
     //document.getElementById("demo").innerHTML = this.responseText;
    	console.log(this.responseText);
    }
  };
  var login =
  xhttp.open("POST", "http://localhost:8080/Totalizator/controller", true);
  xhttp.withCredentials = true;
  xhttp.crossDomain = true,
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send("command=signIn&one=1&two=2");
}