
        window.onload = display();
        function display(){
                var items = [];
                var quantity = [];
    
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

                var display = document.getElementById('checkoutdisplay');
                for (var i = 0; i < items.length; i++) {

                switch (items[i]) {
                    case ('AmericanDream'):
                        var content = `<p>Chasing the American Dream</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$9.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                    case ('deadpoolpikachu'):
                        var content = `<p>Deadpool Pikachu</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$19.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                    case ('doggycoin'):
                        var content = `<p>Doggy Coin</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$4.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                    case ('heroacademiavol1'):
                        var content = `<p>My Hero Academia vol. 1</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$9.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                    case ('introtojava'):
                        var content = `<p>Introduction to Java Programming</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$29.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                    case ('introtophysicalanthropology'):
                        var content = `<p>Introduction to Physical Anthropology</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$149.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                    break;
                    case ('introtosociology'):
                        var content = `<p>Introduction to Sociology</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$49.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                    case ('IzukuMidoriya'):
                        var content = `<p>Izuku Midoriya Figure</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$19.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                    case ('McFarlane'):
                        var content = `<p>Ochaco Uraraka Figure</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$19.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                    case ('python'):
                        var content = `<p>Python Crash Course</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$19.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                    case ('websiteplayground'):
                        var content = `<p>Web Design Playground</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$29.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                    case ('whitecollarcrimebook'):
                        var content = `<p>Trusted Criminals</p>
                        <input value="${quantity[i]}" id="checkoutquantity${i}" readonly></input>
                        <p>$69.99</p>`;
                        var row = document.createElement('div');
                        row.classList.add('checkoutrow');
                        row.innerHTML = content;
                        display.append(row);
                        break;
                };
            }
        }

function checkccnum(){
    var check = document.getElementById("cardnumber").value;
    if (!check.match(/\d{13,19}/)|| check.match(/\d{20}/) || check.match(/\D/)){
        document.getElementById("cardnumber").value = "";
        document.getElementById("cardnumber").style.border = "solid red 1px";
        alert("Card number must be 13-19 digits!");
        return false;
    }
    document.getElementById("cardnumber").style.border = "";
    return true;
}

function checkmonth(){
    var check = document.getElementById("expmonth").value;
    if(check == ""){
        document.getElementById("expmonth").style.border = "solid red 1px";
        alert("Month of expiration date is blank!");
        return false;
    }
    document.getElementById("expmonth").style.border = "";
    return true;
}

function checkyear(){
    var check = document.getElementById("expyear").value;
    if (!check.match(/\d{4}/)|| check.match(/\d{5}/) || check.match(/\D/)){
        document.getElementById("expyear").value = "";
        document.getElementById("expyear").style.border = "solid red 1px";
        alert("Year of expiration data must be 4 digits!");
        return false;
    }
    document.getElementById("expyear").style.border = "";
    return true;
}

function checksecurity(){
    var check = document.getElementById("securitycode").value;
    if (!check.match(/\d{3,4}/) || check.match(/\d{5}/) || check.match(/\D/)){
        document.getElementById("securitycode").value = "";
        document.getElementById("securitycode").style.border = "solid red 1px";
        alert("Security code must be 3-4 digits!");
        return false;
    }
    document.getElementById("securitycode").style.border = "";
    return true;
}

function checkletters(id){
    var check = document.getElementById(id).value;
    if (check.match(/[^a-zA-Z\-\' ]/)){
        document.getElementById(id).value = "";
        document.getElementById(id).style.border = "solid red 1px";
        alert(document.getElementById(id).placeholder+" has invalid characters!");
        return false;
    }
    if (check==""){
        document.getElementById(id).value = "";
        document.getElementById(id).style.border = "solid red 1px";
        alert(document.getElementById(id).placeholder+" is blank!");
        return false;
    }
    document.getElementById(id).style.border = "";
    return true;
}

function checkaddress(){
    var check = document.getElementById("address").value;
    if (check.match(/[^a-zA-Z\-\' 0-9]/)){
        document.getElementById("address").value = "";
        document.getElementById("address").style.border = "solid red 1px";
        alert("Billing address has invalid characters!");
        return false;
    }
    if (check==""){
        document.getElementById("address").value = "";
        document.getElementById("address").style.border = "solid red 1px";
        alert("Billing address is blank!");
        return false;
    }
    document.getElementById("address").style.border = "";
    return true;
}

function checkzip(){
    var check = document.getElementById("zip").value;
    if (!check.match(/\d{5}/)|| check.match(/\d{6}/) || check.match(/\D/)){
        document.getElementById("zip").value = "";
        document.getElementById("zip").style.border = "solid red 1px";
        alert("Zip code must be 5 digits!");
        return false;
    }
    document.getElementById("zip").style.border = "";
    return true;
}

function checkphone(){
    var check = document.getElementById("phone").value;
    if (check.match(/^([0-9]?(-)?[0-9]{3}(-)?[0-9]{3}(-)?[0-9]{4})$/)){
        document.getElementById("phone").style.border = "";
        return true;
    }
    document.getElementById("phone").value = "";
    document.getElementById("phone").style.border = "solid red 1px";
    alert("That is not a valid phone number!");
    return false;
}

function pay(){
    if(checkccnum() && checkmonth() && checkyear() && checksecurity() && 
    checkletters('fname') && checkletters('lname')&& checkletters('city') && checkaddress()
    && checkletters('state') && checkzip() && checkletters('country') && checkphone()){
        location.href="../index.html";
    }
}