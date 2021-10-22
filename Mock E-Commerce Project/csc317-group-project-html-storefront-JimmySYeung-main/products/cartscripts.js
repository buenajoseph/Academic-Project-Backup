

//This function add items to cart page by clicking buy button.
function totalitems(){
    var items = [];
    var allCookies = document.cookie.split(';');

    for (var i = 0; i < allCookies.length; i++) {
        if (allCookies[i].indexOf("product") != -1) {
            var match = allCookies[i].replace(" ", "");
            items.push(match.substr(match.indexOf("=") + 1));
        }
    }

    var total = document.getElementById("total");
    total.value = items.length;
}

function putItemsToCart() {
    var items = [];
    var subtotal=[];
    var quantity = [];
    var total=0;
    var price;
    
    var allCookies = document.cookie.split(';');

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

    for (var i = 0; i < items.length; i++) {
        var cartRow = document.createElement('div');
        cartRow.classList.add('fullcartrow');
        var cart = document.getElementById('fullcart');
        var name = "";
        var img = "";
        
	

        switch (items[i]) {
            case ('AmericanDream'):
                name = "Chasing the American Dream";
                img = "../books/AmericanDream.jpg";
                price = 9.99;
                break;
            case ('deadpoolpikachu'):
                name = "Deadpool Pikachu";
                img = "../toys/deadpoolpikachu.JPG";
                price = 19.99;
                break;
            case ('doggycoin'):
                name = "Doggy Coin";
                img = "../toys/doggycoin.JPG";
                price = 4.99;
                break;
            case ('heroacademiavol1'):
                name = "My Hero Academia vol. 1";
                img = "../books/heroacademiavol1.jpg";
                price = 9.99;
                break;
            case ('introtojava'):
                name = "Introduction to Java Programming";
                img = "../books/introtojava.jpg";
                price = 29.99;
                break;
            case ('introtophysicalanthropology'):
                name = "Introduction to Physical Anthropology";
                img = "../books/introtophysicalanthropology.jpg";
                price = 149.99;
                break;
            case ('introtosociology'):
                name = "Introduction to Sociology";
                img = "../books/introtosociology.jpg";
                price = 49.99;
                break;
            case ('IzukuMidoriya'):
                name = "Izuku Midoriya Figure";
                img = "../toys/IzukuMidoriya.jpg";
                price = 19.99;
                break;
            case ('McFarlane'):
                name = "Ochaco Uraraka Figure";
                img = "../toys/McFarlane.jpg";
                price = 19.99;
                break;
            case ('python'):
                name = "Python Crash Course";
                img = "../books/python.jpg";
                price = 14.99;
                break;
            case ('websiteplayground'):
                name = "Web Design Playground";
                img = "../books/websiteplayground.jpg";
                price = 29.99;
                break;
            case ('whitecollarcrimebook'):
                name = "Trusted Criminals";
                img = "../books/whitecollarcrimebook.jpg";
                price = 69.99;
                break;
        };

	    subtotal[i]=quantity[i]*price;
        var cartContent = `<img class="cartimg" src="${img}"> \
        <input class="nameinput" value="${name}" name="item${i}" readonly>\
        <input value="${quantity[i]}" name="quantity${i}" readonly>\
        <h2>$${price}</h2>\
        <button type="button" onclick="removeFromCart('${items[i]}')">Remove From Cart</button>`;

        cartRow.innerHTML = cartContent;
        cart.append(cartRow);
	
    }
	for(var i=0;i<subtotal.length;i++){
	   total=total+subtotal[i]
	};
	total=Math.round(total*100)/100
	document.getElementById("carttotal").innerHTML='$'+total;		


}

// This function removes product by clicking remove button.
function removeFromCart(name) {
    var cookies = document.cookie.split(";");
    for (i of cookies) {
        if (i.indexOf(name) != -1) {
            var match = i.replace(" ", "");
            var cName = match.substr(0, match.indexOf("="));
            setCookie(cName, "", -1);
        }
    }

    var cart = document.getElementById("fullcart");
    while (cart.firstChild) {
        cart.removeChild(cart.firstChild);
    }

    var cartRow = document.createElement('div');
    cartRow.classList.add('fullcartrow');
    var cart = document.getElementById('fullcart');
    var cartContent = '<div class="itemtotal">\
    <h2>Items</h2>\
    <input name="total" id="total" readonly>\
    </div>\
    <h2>Quantity</h2>\
    <h2>Price</h2>\
    <button class="removeallbutton" type="button" onclick="removeAll()"><img src="../woodbar/removeall1.png"></button>';

    cartRow.innerHTML = cartContent;
    cart.append(cartRow);

    putItemsToCart();
    totalitems();
    
    
}

// This function removes all items from cart
function removeAll(){
    var items = [];
    var allCookies = document.cookie.split(';');

    for (var i = 0; i < allCookies.length; i++) {
        if (allCookies[i].indexOf("product") != -1) {
            var match = allCookies[i].replace(" ", "");
            items.push(match.substr(match.indexOf("=") + 1));
            setCookie(match.substr(0,match.indexOf("=")),"",-1)
        }
    }

    for (x of items) {
        setCookie(x,"",-1)
    }

    var cart = document.getElementById("fullcart");
    while (cart.firstChild) {
        cart.removeChild(cart.firstChild);
    }

    var cartRow = document.createElement('div');
    cartRow.classList.add('fullcartrow');
    var cart = document.getElementById('fullcart');
    var cartContent = 
    '<div class="itemtotal">\
    <h2>Items</h2>\
    <input name="total" id="total" readonly>\
    </div>\
    <h2>Name</h2>\
    <h2>Quantity</h2>\
    <h2>Price</h2>\
    <button type="button" onclick="removeAll()">Remove All</button>';

    cartRow.innerHTML = cartContent;
    cart.append(cartRow);

    var total=0;
    document.getElementById("carttotal").innerHTML='$'+total;
    totalitems();
    
    
}


window.onload = totalitems();
window.onload = putItemsToCart();

//Cookie functions

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
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
