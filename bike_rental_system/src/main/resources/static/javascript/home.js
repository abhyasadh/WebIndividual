categories = ["Most Rented", "Commuter", "Scooter", "Cruiser", "Sports", "Off-Road", "Electric"];

let itemsContainer, topic, seeMore, topicText, items, item, itemImage, imageDetails, time, prices, bikeName, bikeBrand, position, horizontal;

for (let j = 0; j < categories.length; j++) {
    itemsContainer = document.createElement("div");
    itemsContainer.className = "items-container";

    topic = document.createElement("div");
    topic.className = "topic";

    if (j !== 0) {
        seeMore = document.createElement("div");
        seeMore.className = "see-more";
        seeMore.innerHTML += "<a href='#'><u>See All</u> &#11166;</a>";

        topic.appendChild(seeMore);
    }

    topicText = document.createElement("h3");
    topicText.innerHTML += categories[j];

    topic.appendChild(topicText);

    itemsContainer.appendChild(topic);

    items = document.createElement("div");
    items.className = "items";

    for (let i = 0; i < 10; i++) {
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

        if (j === 0) {
            position = document.createElement("div");
            position.className = "position";
            position.innerHTML += "<span>" + "#" + (i + 1) + "</span>";

            if (i === 5) {
                break;
            }

            position.appendChild(item);
            items.appendChild(position);

            if (i < 3) {
                position.setAttribute("id", "pos" + (i + 1));
            }
        }

        item.appendChild(itemImage);

        horizontal = document.createElement("hr");
        horizontal.setAttribute("style", "width:90%");
        item.appendChild(horizontal)

        item.appendChild(bikeBrand);
        item.appendChild(bikeName);

        if (j !== 0) items.appendChild(item);
    }

    itemsContainer.appendChild(items);
    document.getElementsByClassName("display-items").item(0).appendChild(itemsContainer);

    if (j !== categories.length - 1) document.getElementsByClassName("display-items").item(0).appendChild(document.createElement("hr"))
}

let length = document.getElementsByClassName("see-more").length;
for(let i=0; i<length; i++) {
    document.getElementsByClassName("see-more").item(i).addEventListener("click", function openCategory() {
            localStorage.setItem("topic", document.getElementsByClassName("see-more").item(i).parentElement.innerText.slice(10,))
            window.location.href = ("category.html")
        }
    )
}