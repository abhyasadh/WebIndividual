document.getElementsByClassName('signup-form').item(0).style.display="none";

document.getElementsByClassName("login").item(0).addEventListener("click", ()=>{displayLogin()})
document.getElementsByClassName("signup").item(0).addEventListener("click", ()=>{displaySignup()})

let login = document.getElementsByClassName('login').item(0);
let signup = document.getElementsByClassName('signup').item(0);
function displayLogin(){
    document.getElementsByClassName('signup-form').item(0).style.display="none";
    document.getElementsByClassName('login-form').item(0).style.display="flex";

    signup.style.color="#586ba4";
    signup.style.background="white";
    login.style.scale="1";

    signup.style.scale="0.8";
    login.style.color="white";
    login.style.background="#586ba4"
}

function displaySignup(){
    document.getElementsByClassName('login-form').item(0).style.display="none";
    document.getElementsByClassName('signup-form').item(0).style.display="flex";

    login.style.color="#586ba4";
    login.style.background="white";
    signup.style.scale="1";

    login.style.scale="0.8";
    signup.style.color="white";
    signup.style.background="#586ba4"
}

function showPassword(n) {
    let x = document.getElementsByClassName("password-input").item(n);
    let buttonImage = document.getElementsByClassName("show-password-image").item(n);
    if (x.type === "password") {
        x.type = "text";
        buttonImage.src="../static/images/logos/hide.png";
    } else {
        x.type = "password";
        buttonImage.src="../static/images/logos/show.png";
    }
}