spanNumber = document.getElementsByTagName("span");
idNumber = document.getElementsByClassName("position");
for (let i=1; i<5; i++){
    spanNumber[i].innerText="#"+i;
    idNumber[i-1].setAttribute("id","pos"+i);
}