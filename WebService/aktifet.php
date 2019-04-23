<?php 

error_reporting(0);
include("ayar.php");

$mailAdres = $_GET["mail"];  //parametre key get ediyoruz
$dogrulamaKodu = $_GET["dogrulamaKodu"];

//iki get fonksiyonu gelecek onları alıp update methoduyla durum 1 yapılacak

$guncelle = mysqli_query($baglan,"update veteriner_kullanicilar set durum = '1' where mailAdres='$mailAdres' and $dogrulamaKodu='$dogrulamaKodu'");

if ($guncelle) {
	?>
	<h1> TEBRİKLER HESABINIZ DOĞRULANDI...</h1>

	<?php
}


?>