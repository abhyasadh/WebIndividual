document.getElementsByClassName('signup-form').item(0).style.display="none";

document.getElementsByClassName("login").item(0).addEventListener("click", ()=>{displayLogin()})
document.getElementsByClassName("signup").item(0).addEventListener("click", ()=>{displaySignup()})

function displayLogin(){
    document.getElementsByClassName('signup-form').item(0).style.display="none";
    document.getElementsByClassName('login-form').item(0).style.display="flex";
}

function displaySignup(){
    document.getElementsByClassName('login-form').item(0).style.display="none";
    document.getElementsByClassName('signup-form').item(0).style.display="flex";
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