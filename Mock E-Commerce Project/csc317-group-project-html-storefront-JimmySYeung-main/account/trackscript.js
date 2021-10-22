// This function put items to Track.
function putItemsToTrack() {
    var items = [];
    var quantity = [];
    var time = [];
    var allCookies = document.cookie.split(';');

    // The cookies take new product1 being added. 
    for (var i = 0; i < allCookies.length; i++) {
        if (allCookies[i].indexOf("product") != -1) {
            var match = allCookies[i].replace(" ", "");
            items.push(match.substr(match.indexOf("=") + 1));
        }
    }


    for (x of items) {
        for (var i = 0; i < allCookies.length; i++) {
            if ((allCookies[i].indexOf(x) != -1) && (allCookies[i].indexOf("product") == -1)) {
                match = allCookies[i].replace(" ", "");
                quantity.push(match.substr(match.indexOf("=") + 1));
            }
        }
    }

    // This function takes the string
    for (var i = 0; i < items.length; i++) {
        var TrackRow = document.createElement('div');
        TrackRow.classList.add('fullTrackrow');
        var Track = document.getElementById('fullTrack');
        var time = "";
        var name = "";
        var img = "";
        var price = "";

        switch (items[i]) {
            case ('AmericanDream'):
                time = new Date();
                name = "Chasing the American Dream";
                img = "../books/AmericanDream.jpg";
                price = "$9.99";
                break;
            case ('deadpoolpikachu'):
                time = new Date();
                name = "Deadpool Pikachu";
                img = "../toys/deadpoolpikachu.JPG";
                price = "$19.99";
                break;
            case ('doggycoin'):
                time = new Date();
                name = "Doggy Coin";
                img = "../toys/doggycoin.JPG";
                price = "$4.99";
                break;
            case ('heroacademiavol1'):
                time = new Date();
                name = "My Hero Academia vol. 1";
                img = "../books/heroacademiavol1.jpg";
                price = "$9.99";
                break;
            case ('introtojava'):
                time = new Date();
                name = "Introduction to Java Programming";
                img = "../books/introtojava.jpg";
                price = "$29.99";
                break;
            case ('introtophysicalanthropology'):
                time = new Date();
                name = "Introduction to Physical Anthropology";
                img = "../books/introtophysicalanthropology.jpg";
                price = "$149.99";
                break;
            case ('introtosociology'):
                time = new Date();
                name = "Introduction to Sociology";
                img = "../books/introtosociology.jpg";
                price = "$49.99";
                break;
            case ('IzukuMidoriya'):
                time = new Date();
                name = "Izuku Midoriya Figure";
                img = "../toys/IzukuMidoriya.jpg";
                price = "$19.99";
                break;
            case ('McFarlane'):
                time = new Date();
                name = "Ochaco Uraraka Figure";
                img = "../toys/McFarlane.jpg";
                price = "$19.99";
                break;
            case ('python'):
                time = new Date();
                name = "Python Crash Course";
                img = "../books/python.jpg";
                price = "$14.99";
                break;
            case ('websiteplayground'):
                time = new Date();
                name = "Web Design Playground";
                img = "../books/websiteplayground.jpg";
                price = "$29.99";
                break;
            case ('whitecollarcrimebook'):
                time = new Date();
                name = "Trusted Criminals";
                img = "../books/whitecollarcrimebook.jpg";
                price = "$69.99";
                break;
        };

        var TrackContent = `<img class="trackimg" src="${img}"> \
        <h2>${time}</h2>\
        <h2>${name}</h2>\
        <h2>${quantity[i]}</h2>\
        <h2>${price}</h2>\
        <button type="button" onclick="removeFromTrack('${items[i]}')">Remove From Track</button>`;

        TrackRow.innerHTML = TrackContent;
        Track.append(TrackRow);
    }

}

//This function removes product1 from track.
function removeFromTrack(name) {
    var cookies = document.cookie.split(";");
    for (i of cookies) {
        if (i.indexOf(name) != -1) {
            var match = i.replace(" ", "");
            var tName = match.substr(0, match.indexOf("="));
            setCookie(tName, "", -1);
        }
    }

    var Track = document.getElementById("fullTrack");
    while (Track.firstChild) {
        Track.removeChild(Track.firstChild);
    }

    var TrackRow = document.createElement('div');
    TrackRow.classList.add('fullTrackrow');
    var Track = document.getElementById('fullTrack');
    var TrackContent = '<h2>Item</h2>\
    <h2> Time </h2>\
    <h2>Name</h2>\
    <h2>Quantity</h2>\
    <h2>Price</h2>';

    TrackRow.innerHTML = TrackContent;
    Track.append(TrackRow);

    putItemsToTrack();
}

window.onload = putItemsToTrack();

//Cookie functions

function setCookie(tname, tvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 23 * 60 * 60 * 1000));
    var expires1 = "expires=" + d.toGMTString();
    document.cookie = tname + "=" + tvalue + ";" + expires1 + ";path=/";
}

function deleteCookies() {
    var cookies = document.cookie.split(";");
    for (i of cookies) {
        i.replace(" ", "");
        var name = i.substr(0, i.indexOf("="));
        setCookie(name, "", -1)
    }
}

function checkCookie() {
    alert(document.cookie)
}