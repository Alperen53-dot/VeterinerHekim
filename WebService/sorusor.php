<?php 
include("ayar.php");
$mus_id = $_POST["id"];
$soru = $_POST["soru"];
$ekle = mysqli_query($baglan,"insert into veteriner_sorular(mus_id,soru,durum) values ('$mus_id','$soru','0')");


class ekleme{
	public $text;
	public $tf;
}$ekle = new ekleme();

if($ekle){
	$ekle->text = "Sorunuz ilgili veterinere ulaşmıştır! Hekiminiz cevap yazdığında Cevaplar bölümünden ulaşabilirsiniz.";
	$ekle->tf = true;
}else{
	$ekle->text = "Sorunuz gönderilemedi. Daha sonra tekrar deneyiniz";
	$ekle->tf = false;
}

echo(json_encode($ekle));


?>