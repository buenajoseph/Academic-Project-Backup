<?php
require 'productdatabase.php';
$database = 'store';
$connection = new mysqli($server, $user, $password, $database);

$query = 'CREATE TABLE IF NOT EXISTS products(
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
productname varchar(50) NOT NULL,
price FLOAT(6,2) NOT NULL
)';

$result = $connection->query($query);

if($result === TRUE)
{
	// Works
}
else
{
	// Error
	$connection->error;
}

$names = array("My Hero Academia vol. 1","Introduction to Java Programming",
"Introduction to Physical Anthropology","Introduction to Sociology",
"Python Crash Course","Chasing the American Dream",
"Web Design Playground","Trusted Criminals",
"Deadpool Pikachu","Doggy Coin",
"Izuku Midoriya Figure","Ochaco Uraraka Figure");

$prices = array(9.99,29.99,149.99,49.99,14.99,9.99,
29.99,69.99,19.99,4.99,19.99,19.99);


$sql = "";

for($i = 0; $i < 12; $i++) {
	$finddupes = "SELECT productname, price FROM products";
	$checkdupes = $connection->query($finddupes);
	$skip = false;
	if ($checkdupes->num_rows > 0) {
		while($row = $checkdupes->fetch_assoc()) {
			if (($row["productname"] == $names[$i]) && ($row["price"] == $prices[$i])){
				$skip = true;
			}
		}
	}
	if($skip == false){
		$sql .= 'INSERT INTO products (productname, price)
		VALUES ("'.$names[$i].'",'.$prices[$i].');';
	}
}

if (($connection->multi_query($sql) === TRUE) || $sql == "") {
	// Works
} else {
	// Error
	$connection->error;
}

?>