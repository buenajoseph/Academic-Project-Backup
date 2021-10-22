<?php
$server = "localhost";
$user = "root";
$password = "";

$connection = new mysqli($server, $user, $password);

$query = "CREATE DATABASE IF NOT EXISTS Store";
$result = $connection->query($query);

$connection->close();
?>