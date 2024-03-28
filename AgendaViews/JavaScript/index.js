

    

function loginButton(){
    document.getElementById("loginButton").addEventListener("click", function(event){
        event.preventDefault();
        var email = document.getElementById("inputEmail").value;
        var password = document.getElementById("inputPassword").value;
       
        console.log(email,password);
        enviar(email,password);
    });
}



 function enviar(emailLogin,passwordLogin){
    fetch("http://localhost:8080/employee/login", {
        method: "POST",
        headers:{
            "content-Type": "application/json",
        },
        body: JSON.stringify({
            email: emailLogin,
            password:  passwordLogin})
    })
    .then(response => response.status)
    .then(data => console.log("Sucesso:", data))
    .catch((error)=> console.error("Erro:", error));
 }

 

 
