document.getElementsByClassName('signup-container').item(0).style.display="none";

document.getElementsByClassName("login").item(0).addEventListener("click", ()=>{displayLogin()})
document.getElementsByClassName("signup").item(0).addEventListener("click", ()=>{displaySignup()})

let login = document.getElementsByClassName('login').item(0);
let signup = document.getElementsByClassName('signup').item(0);
function displayLogin(){
    document.getElementsByClassName('signup-container').item(0).style.display="none";
    document.getElementsByClassName('login-container').item(0).style.display="flex";

    signup.style.color="#f17300";
    signup.style.background="white";
    login.style.scale="1";

    signup.style.scale="0.8";
    login.style.color="white";
    login.style.background="#f17300"
}

function displaySignup(){
    document.getElementsByClassName('login-container').item(0).style.display="none";
    document.getElementsByClassName('signup-container').item(0).style.display="flex";

    login.style.color="#f17300";
    login.style.background="white";
    signup.style.scale="1";

    login.style.scale="0.8";
    signup.style.color="white";
    signup.style.background="#f17300"
}

function showPassword(n) {
    let x = document.getElementsByClassName("password-input").item(n);
    let buttonImage = document.getElementsByClassName("show-password-button").item(n);
    if (x.type === "password") {
        x.type = "text";
        buttonImage.innerHTML="<i class=\"fa fa-eye-slash\"></i>";
        buttonImage.style.marginRight="13px"
    } else {
        x.type = "password";
        buttonImage.innerHTML="<i class=\"fa fa-eye\"></i>";
        buttonImage.style.marginRight="12px"
    }
}