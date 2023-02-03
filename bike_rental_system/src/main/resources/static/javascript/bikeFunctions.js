function bookingForm(time){
    let blur = document.getElementsByClassName("middle").item(0);
    blur.classList.toggle('active');
    let footer = document.getElementsByClassName("footer").item(0);
    footer.classList.toggle('active');

    let popup = document.getElementsByClassName("payment-popup").item(0);
    popup.classList.toggle('active');

    let price = document.getElementsByClassName("priceInvisible").item(0).value;
    dateSetter(time, price);
}

function dateSetter(time, price) {
    const date = new Date();
    date.setDate(date.getDate() + 1);

    let datePicker = document.getElementsByClassName("date-input");
    datePicker.item(0).valueAsDate = date;

    date.setDate(date.getDate() + time)
    datePicker.item(1).valueAsDate = date;

    priceSetter(price);
}

function priceSetter(price){
    let datePicker = document.getElementsByClassName("date-input");

    let date1 = new Date(datePicker[0].value);
    let date2 = new Date(datePicker[1].value);

    const diffTime = Math.abs(date2.getTime()-date1.getTime());
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

    document.getElementById("priceField").value=priceCalculator(diffDays, price);
}

function priceCalculator(days, price){
    price=parseInt(price);

    let calculatedPrice;

    if (days===1){
        calculatedPrice = price;
    } else if (days > 1 && days <= 7){
        calculatedPrice = (price + (0.8*price*(days-1)));
    } else if (days > 7 && days <= 30){
        calculatedPrice = (price + (0.8*price*6) + (0.6*price*(days-7)));
    } else if (days > 30 && days <= 122){
        calculatedPrice = (price + (0.8*price*6) + (0.6*price*23) + (0.5*price*(days-30)));
    } else if (days > 122 && days <= 365){
        calculatedPrice = (price + (0.8*price*6)) + (0.6*price*23) + (0.5*price*92) + (0.4*price*(days-122));
    }

    let i = new Intl.NumberFormat('en-IN').format(calculatedPrice);
    return "Rs. "+i+" / -";
}

let time = ['Day', 'Month', 'Year'];
let index = 0;
$('.image-prices').each(function() {
    let monetary_value = $(this).text();
    let i = new Intl.NumberFormat('en-IN').format(monetary_value);
    $(this).text("Rs. "+i+" / "+time[index]);
    index++;
    if (index===3) index=0;
});

$('.price').each(function() {
    let monetary_value = $(this).text();
    let i = new Intl.NumberFormat('en-IN').format(monetary_value);
    $(this).text("Rs. "+i+" / "+time[index]);
    index++;
    if (index===3) index=0;
});