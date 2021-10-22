<?php
require 'producttable.php';
$total = 0;
$names = array();
$quantities = array();
$itemnumber = $_GET['total'];
for($i=0;$i<$itemnumber;$i++){
    array_push($names, $_GET['item'.$i]);
    array_push($quantities, $_GET['quantity'.$i]);
}

if($result === TRUE){
    for($i=0;$i<$itemnumber;$i++){
        $query = "SELECT productname, price FROM products";
        $result = $connection->query($query);
	    while($products = $result->fetch_assoc()){
		    if($names[$i] == $products["productname"]){
                $total += ($products["price"]*$quantities[$i]);
            }
	    }
    }
} else { //If unable to connect to database, use switch statement for total price
    for($i=0;$i<$itemnumber;$i++){
        $price = 0;
        switch ($names[$i]) {
            case ('Chasing the American Dream'):
                $price = 9.99;
                break;
            case ('Deadpool Pikachu'):
                $price = 19.99;
                break;
            case ('Doggy Coin'):
                $price = 4.99;
                break;
            case ('My Hero Academia vol. 1'):
                $price = 9.99;
                break;
            case ('Introduction to Java Programming'):
                $price = 29.99;
                break;
            case ('Introduction to Physical Anthropology'):
                $price = 149.99;
                break;
            case ('Introduction to Sociology'):
                $price = 49.99;
                break;
            case ('Izuku Midoriya Figure'):
                $price = 19.99;
                break;
            case ('Ochaco Uraraka Figure'):
                $price = 19.99;
                break;
            case ('Python Crash Course'):
                $price = 14.99;
                break;
            case ('Web Design Playground'):
                $price = 29.99;
                break;
            case ('Trusted Criminals'):
                $price = 69.99;
                break;
        };
        $total += $price*$quantities[$i];
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../storestyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
</head>
<body class="empty">
    <div class="checkout">
        <form class="payment">
        <fieldset>
            <legend class="checkouttitle">Payment Method</legend>
            <div class="paymentmethod">
                <p class="checkouttext1">Payment method</p>
                <select class="checkoutinput1" name="method" require>
                    <option value="Visa" selected>Visa</option>
                    <option value="Master Card">Master Card</option>
                    <option value="American Express">American Express</option>
                    <option value="Discover">Discover</option>
                    <option value="JCB">JCB</option>
                </select>

                <p class="checkouttext2">Card number</p>
                <input class="checkoutinput2" type="number" id="cardnumber" name="cardnumber" placeholder="Card number" onblur="checkccnum()">

                <p class="checkouttext3">Expiration date (month, year) and security code</p>
                <div class="securitycode">
                    <select class="checkoutinput3" name="expmonth" id="expmonth" onblur="checkmonth()">
                        <option></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>
                    <input class="checkoutinput4" type="number" name="expyear" id="expyear" placeholder="Year" min="2020" onblur="checkyear()">
                    <input class="checkoutinput5" type="text" id="securitycode" placeholder="Code" onblur="checksecurity()">
                </div>
            </div>
        </fieldset>

        <fieldset>
            <legend class="checkouttitle">Billing Information</legend>
            <div class="billing">
                <p class="checkouttext4">First name</p>
                <input class="checkoutinput6" type="text" name="fname" id="fname" placeholder="First name" onblur="checkletters('fname')">

                <p class="checkouttext5">Last name</p>
                <input class="checkoutinput7" type="text" name="lname" id="lname" placeholder="Last name" onblur="checkletters('lname')">

                <p class="checkouttext6">City</p>
                <input class="checkoutinput8" type="text" name="city" id="city" placeholder="City" onblur="checkletters('city')">

                <p class="checkouttext7">Billing address</p>
                <input class="checkoutinput9" type="text" name="address" id="address" placeholder="Billing address" onblur="checkaddress()">

                <p class="checkouttext8">State/Province</p>
                <input class="checkoutinput10" type="text" name="state" id="state" placeholder="State" onblur="checkletters('state')">

                <p class="checkouttext9">Zip or postal code</p>
                <input class="checkoutinput11" type="number" name="zip" id="zip" placeholder="Zip code" onblur="checkzip()">

                <p class="checkouttext10">Country</p>
                <input class="checkoutinput12" type="text" name="country" id="country" placeholder="Country" onblur="checkletters('country')">

                <p class="checkouttext11">Phone number</p>
                <input class="checkoutinput13" type="text" name="phone" id="phone" placeholder="Phone number" onblur="checkphone()">
            </div>
        </fieldset>
    </form>

        <div class="displaycontainer">
        <div id="checkoutdisplay">
            <div class="checkoutrow">
                <h1>Product</h1>
                <h1>Quantity</h1>
                <h1>Price</h1>
            </div>
        </div>
        <div class="completetransaction">
            <h2>Total</h2>
            <?php
                echo "<h2>$$total</h2>"
            ?>
            <button class="paymentbtn" onclick="pay()">Finish Payment</button>
        </div>
        </div>
    </div>
    <script src="checkoutscripts.js"></script>
</body>
</html>
