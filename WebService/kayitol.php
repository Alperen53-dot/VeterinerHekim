<?php 

error_reporting(0);
include("ayar.php");

$mailAdres = $_POST["mailAdres"];  //parametre key get ediyoruz
$kadi = $_POST["kadi"];
$parola = $_POST["parola"];
$dogrulamaKodu = rand(0,10000).rand(0,10000); // iki sayı oluştur birleştir
$durum = 0; //her kullanıcı için 0 ilk olarak

$kontrol = mysqli_query($baglan,"select * from veteriner_kullanicilar where mailAdres ='$mailAdres' or kadi='$kadi'"); 

$count = mysqli_num_rows($kontrol);

class User{
	public $text;
	public $trueFalse;
}
$user = new User();


if ($count > 0) {
	$user->text = "Girmiş olduğunuz bilgilere ait kullanıcı bulunmaktadır!";
	$user->trueFalse = false;
	echo (json_encode($user));
	
}else{
	$addUser = mysqli_query($baglan,"insert into veteriner_kullanicilar(kadi,mailAdres,parola,dogrulamaKodu,durum) values ('$kadi','$mailAdres','$parola','$dogrulamaKodu','$durum')");  //bilgileri databse'e insert etmek için

if ($addUser) {
	$git = "localhost/veterinerservis/aktifet.php?mail=".$mailAdres."dogrulamakodu=".$dogrulamaKodu.""; //linke tıklanıldığında parametre gönderecek
	$to = "mehmetserkanekinci@gmail.com";
	$subject = "Kullanıcı Hesabı Doğrulama";
	$text = "Merhaba Sayın ".$kadi."\n\t Sisteme giriş yapabilmeniz için hesabınızı onaylamanız gereklidir. <a href = '".$git."'> Onayla </a>";
	$from ="From: info@kocveteriner.com";
	$from .="MIME-Version: 1.0\r\n";
	$from .="Content-Type: text/html; charset=UTF-8\r\n";
	mail($to,$subject,$text,$from);

	$user->text = "Kayıt olma işleminizin gerçekleşmesi için mailinizden hesap doğrulamanızı yapınız!";
	$user->trueFalse = true;
	echo (json_encode($user));


	echo "Ekleme işlemi başarılı!";
}else{
	echo "Bir hata var!";
}
}




?>