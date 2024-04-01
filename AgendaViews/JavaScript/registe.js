function loginButton(){
    document.getElementById("registeButton").addEventListener("click", function(event){
        event.preventDefault();
        var name = document.getElementById("inputName").value;
        var email = document.getElementById("inputEmail").value;
        var repeatPassword = document.getElementById("inputRepeatPassword").value;
        var password = document.getElementById("inputPassword").value;
       
        console.log(name, email, password, repeatPassword);
        
        enviar(name, email, password, repeatPassword);
    });
}

function enviar(name, email, password, repeatPassword){
    fetch("http://localhost:8080/user/register", {
        method: "POST",
        headers:{
            "content-Type": "application/json",
        },
        body: JSON.stringify({
            name:name,
            email: email,
            password:  password,
            repeatPassword: repeatPassword
        })
    })
    .then(response => response.json())
    .then(dados=> {console.log(dados)})
    .catch((error)=> console.error("Erro:", error));
 }