document.getElementsByClassName("remove").item(0).addEventListener("click",
    ()=>{
    document.getElementsByClassName("no-image").item(0)
        .innerHTML="<div class='profile-head'></div><div class='profile-body'></div>";

    document.getElementsByClassName("input-img").item(0).value="";
})

function validateName(index){
    let name = document.getElementsByClassName('text-input').item(index).value;
    if (name===""){
        document.getElementsByClassName("fas fa-user fa-xs").item(index).style.color="#e53e3e";
    } else {
        document.getElementsByClassName("fas fa-user fa-xs").item(index).style.color="#71cc35";
    }
}

function validatePhone(){
    const pattern = "[0-9]{10}"
    let phone = document.getElementsByClassName('text-input').item(2).value;
    if (!phone.match(pattern)){
        document.getElementsByClassName("fas fa-phone fa-xs fa-flip-horizontal").item(0).style.color="#e53e3e";
    } else {
        document.getElementsByClassName("fas fa-phone fa-xs fa-flip-horizontal").item(0).style.color="#71cc35";
    }
}

function validateAddress(){
    let address = document.getElementsByClassName('text-input').item(3).value;
    if (address===""){
        document.getElementsByClassName("fa fa-map-marker-alt fa-xs").item(0).style.color="#e53e3e";
    } else {
        document.getElementsByClassName("fa fa-map-marker-alt fa-xs").item(0).style.color="#71cc35";
    }
}

function addImage(){
    let fileInput = document.getElementsByClassName("input-img").item(0);
    let filePath = fileInput.value;

    const allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;

    if (!allowedExtensions.exec(filePath)) {
        document.getElementsByClassName('picture').item(1).style.border="2px solid #e94d4d";
    } else {
        document.getElementsByClassName('picture').item(1).style.border="2px solid white"
    }

    document.getElementsByClassName("no-image").item(0)
        .innerHTML = "<img src='" + URL.createObjectURL(event.target.files[0]) + "' alt='Profile Picture'>"
}

function documentsCheck(id, index){
    console.log(index)

    let fileInput = document.getElementById(id);
    let filePath = fileInput.value;

    const allowedExtensions = /(\.jpg|\.jpeg|\.png|\.pdf)$/i;

    if (filePath===""){
        if (index===0) document.getElementsByClassName("document-name").item(index).innerHTML="<i class='fa fa-address-card fa-xs'></i>Citizenship (Front)";
        if (index===1) document.getElementsByClassName("document-name").item(index).innerHTML="<i class='fa fa-address-card fa-xs'></i>Citizenship (Back)";
        if (index===2) document.getElementsByClassName("document-name").item(index).innerHTML="<i class='fa fa-address-card fa-xs'></i>License";
        return;
    }

    if (!allowedExtensions.exec(filePath)) {
        document.getElementsByClassName("document-name").item(index).innerHTML="<i class='fa fa-address-card fa-xs'></i>Invalid File Format!";
        document.getElementsByClassName("fa fa-address-card fa-xs").item(index).style.color="#e53e3e";
    } else {
        document.getElementsByClassName("document-name").item(index).innerHTML="<i class='fa fa-address-card fa-xs'></i>Uploaded!";
        document.getElementsByClassName("fa fa-address-card fa-xs").item(index).style.color="#71cc35";
    }
}

let status="Submitted"
let statusClass=document.getElementsByClassName("text-field-status").item(0);
let heading=document.createElement("h2");
heading.innerText="Status: "+status;
statusClass.appendChild(heading);

let para = document.createElement("p");
if (status==="Not Submitted"){
    para.innerText="You have not uploaded any of your documents to be reviewed yet." +
        "\n\nPlease note that your name, phone number, address and image are also taken into consideration in the review process.";
    statusClass.style.color="white";
    statusClass.style.backgroundColor="#e94d4d";
    statusClass.style.border="solid 2px red"
    para.style.fontSize="12px";
} else if (status==="Rejected"){
    para.innerText="Your documents were rejected. Please check your documents and try again."  +
        "\n\nPlease note that your name, phone number, address and image are also taken into consideration in the review process.";
    statusClass.style.color="white";
    statusClass.style.backgroundColor="#e94d4d";
    statusClass.style.border="solid 2px red"
    para.style.fontSize="12px";
} else if (status==="Submitted"){
    para.innerText="Your documents have been submitted for review. Come back later to check the results.";
    statusClass.style.color="black";
    statusClass.style.backgroundColor="#fff36d";
    statusClass.style.border="solid 2px #fedd00"

    fields = document.getElementById("citizen-front");
    fields.disabled=true;

    fields = document.getElementById("citizen-back");
    fields.disabled=true;

    fields = document.getElementById("license");
    fields.disabled=true;

    let button = document.getElementsByClassName("submit").item(1);
    button.disabled=true;

} else if (status==="Approved"){
    para.innerText="Your documents are approved. You can always update your documents and submit again to keep us updated.";
    statusClass.style.color="black";
    statusClass.style.backgroundColor="#71cc35";
    statusClass.style.border="solid 2px green"
}
statusClass.appendChild(para);