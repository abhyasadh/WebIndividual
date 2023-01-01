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

function validateEmail(element, index){
    let mail = document.getElementsByClassName(element).item(index).value;
    const mailReg=/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if (!mail.match(mailReg) || mail === "") {
        if (index!==0) {
            document.getElementsByClassName("fas fa-envelope fa-xs").item(index - 2).style.color = "#e53e3e";
        } else {
            document.getElementsByClassName("fas fa-envelope fa-xs").item(index).style.color = "#e53e3e";
        }
    } else {
        if (index!==0) {
            document.getElementsByClassName("fas fa-envelope fa-xs").item(index - 2).style.color = "#71cc35";
        } else {
            document.getElementsByClassName("fas fa-envelope fa-xs").item(index).style.color = "#71cc35";
        }
    }
}

function validateName(element, index){
    let name = document.getElementsByClassName(element).item(index).value;
    if (name===""){
        document.getElementsByClassName("fas fa-user fa-xs").item(index-1).style.color="#e53e3e";
    } else {
        document.getElementsByClassName("fas fa-user fa-xs").item(index-1).style.color="#71cc35";
    }
}

function validatePassword(element, index){
    let password = document.getElementsByClassName(element).item(index).value;
    const passwordReg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
    if (index===0){
        if (password.length>=8){
            document.getElementsByClassName("fas fa-lock fa-xs").item(index).style.color = "#71cc35";
        } else {
            document.getElementsByClassName("fas fa-lock fa-xs").item(index).style.color = "#e53e3e";
        }
    } else if (index===1) {
        if (!password.match(passwordReg)) {
            document.getElementsByClassName("fas fa-lock fa-xs").item(index).style.color = "#e53e3e";
        } else {
            document.getElementsByClassName("fas fa-lock fa-xs").item(index).style.color = "#71cc35";
        }
    } else if (index===2){
        if (password!==document.getElementsByClassName(element).item(1).value){
            document.getElementsByClassName("fas fa-lock fa-xs").item(index).style.color = "#e53e3e";
        } else {
            document.getElementsByClassName("fas fa-lock fa-xs").item(index).style.color = "#71cc35";
        }
    }
}