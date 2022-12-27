document.title=localStorage.getItem("topic")+" || BikeSapati";

itemsContainer = document.createElement("div");
itemsContainer.className = "items-container";

topic = document.createElement("div");
topic.className = "topic";

topicText = document.createElement("h3");
topicText.innerHTML += sessionStorage.getItem("topic");

seeLess = document.createElement("div");
seeLess.className = "see-more";
seeLess.innerHTML += "<a href='#'>&#11164; <u>Back</u></a>";
seeLess.addEventListener("click",()=>history.back())

topic.appendChild(seeLess);

description = document.createElement("span");
description.className="desc";
description.innerText+="Description of Bike Category";

topic.appendChild(topicText);
itemsContainer.appendChild(topic);

itemsContainer.appendChild(description);

items = document.createElement("div");
items.className = "items";

for (let i = 0; i < 15; i++) {
    item = document.createElement("div");
    item.className = "item";

    itemImage = document.createElement("div");
    itemImage.className = "item-image";

    imageDetails = document.createElement("div");
    imageDetails.className = "image-details";
    imageDetails.innerHTML += "<h4 style='margin-bottom:4px; margin-top:0'>Choose a Plan: </h4>";

    time = ["Day", "Month", "Year"]
    for (let k = 0; k < time.length; k++) {
        prices = document.createElement("button");
        prices.className = "image-prices";
        prices.innerHTML += "Rs. XX,XXX / " + time[k];

        imageDetails.appendChild(prices);
    }

    itemImage.appendChild(imageDetails);

    bikeName = document.createElement("div");
    bikeName.className = "bike-name";
    bikeName.innerHTML += "BIKE NAME";

    bikeBrand = document.createElement("div");
    bikeBrand.className = "bike-brand";
    bikeBrand.innerHTML += "Brand"

    item.appendChild(itemImage);

    horizontal = document.createElement("hr");
    horizontal.setAttribute("style", "width:90%");
    item.appendChild(horizontal)

    item.appendChild(bikeBrand);
    item.appendChild(bikeName);

    items.appendChild(item);
}

itemsContainer.appendChild(items);
document.getElementsByClassName("display-items").item(0).appendChild(itemsContainer);