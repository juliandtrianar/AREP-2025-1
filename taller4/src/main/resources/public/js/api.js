function loadGetSinMsg() {
    let nameVar = document.getElementById("sin").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("sinmsg").innerHTML = this.responseText;
    };
    xhttp.open("GET", "/sin?x="+ encodeURIComponent(nameVar));
    xhttp.send();
};



function loadGetCosMsg() {
    let nameVar = document.getElementById("cos").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("cosmsg").innerHTML = this.responseText;
    };
    xhttp.open("GET", "/cos?x="+ encodeURIComponent(nameVar));
    xhttp.send();
};


function loadGetPalMsg() {
    let nameVar = document.getElementById("palindrome").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("palmsg").innerHTML = this.responseText;
    };
    xhttp.open("GET", "/palindrome?x="+ encodeURIComponent(nameVar));
    xhttp.send();
};


function loadGetVectMsg() {
    let vector1 = document.getElementById("vector1").value;
    let vector2 = document.getElementById("vector2").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("vectorpmsg").innerHTML = this.responseText;
    };
    xhttp.open("GET", "/vector?x="+ encodeURIComponent(vector1) + "&" + "y=" + encodeURIComponent(vector2)  ) ;
    xhttp.send();
};