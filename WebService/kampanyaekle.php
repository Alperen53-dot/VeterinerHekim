<?php 
include("ayar.php");

$baslik = "asd"; // $_POST["baslik"];
$text = "add"; //$_POST["text"];
$resim ="asd";//$_POST["resim"];

class Result{
	public $text;
	public $tf;
}$result = new Result();

//resim ekleme işlemi

$isim = rand(0,100000).rand(0,100000).rand(0,100000);

$yol = "resimler/$isim.jpg";
$resimpet = "http://192.168.1.107/veterinerservis/resimler/$isim.jpg";

$add = mysqli_query($baglan,"insert into veteriner_kampanyalar(text,resim,baslik) values ('$text','$resimpet','$baslik')");


if ($add) {
	file_put_contents($yol, base64_decode($resim));
	$result->text = "Kampanya eklendi";
	$result->tf = true;
	echo (json_encode($result));
}else{
	$result->text = "Kampanya ekleme işlemi başarısız!";
	$result->tf = false;
	echo (json_encode($result));
}

?>