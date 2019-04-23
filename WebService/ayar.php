<?php

$server_name = "localhost";
$db_userName = "root";
$db_pass = "";
$dbName = "msekinci"; //database name 

$baglan =mysqli_connect($server_name,$db_userName,$db_pass,$dbName);

mysqli_set_charset($baglan,"UTF-8"); //Türkçe karakter sıkıntısı olmaması için
mysqli_query($baglan,"SET NAMES UTF8");

?>