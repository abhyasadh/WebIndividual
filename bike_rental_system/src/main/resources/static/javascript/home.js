spanNumber = document.getElementsByTagName("span");
idNumber = document.getElementsByClassName("position");
for (let i=1; i<6; i++){
    spanNumber[i].innerText="#"+i;
    idNumber[i-1].setAttribute("id","pos"+i);
}