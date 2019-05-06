<?php 
error_reporting(0);
include("ayar.php");
 
$kadi = $_POST["kadi"];//parametre key get ediyoruz
$parola = $_POST["parola"];

$kontrol = mysqli_query($baglan,"select * from veteriner_kullanicilar where kadi ='$kadi' and parola='$parola'"); 
$count = mysqli_num_rows($kontrol);

class UserLogin{
	public $id;
	public $kadi;
	public $mailAdres;
	public $parola;
	public $durum;
	public $text;
	public $trueFalse;
}
$user = new UserLogin();


if ($count > 0) {
	$parse = mysqli_fetch_assoc($kontrol); //sorgu sonucu gelen verileri bir degiskene atıyoruz.
	$durum = $parse["durum"];
	$id = $parse["id"];
	$kadi = $parse["kadi"];
	$mailAdres = $parse["mailAdres"];
	$parola = $parse["parola"];

	if ($durum == 1) {
		$user->id=$id;
		$user->kadi=$kadi;
		$user->mailAdres=$mailAdres;
		$user->parola=$parola;
		$user->durum=$durum;
		$user->text="Giriş işlemi başarılı";
		$user->trueFalse=true;
		echo (json_encode($user));
	}else{
		$user->id=null;
		$user->kadi=null;
		$user->mailAdres=null;
		$user->parola=null;
		$user->durum=$durum;
		$user->text="Doğrulama işleminizi gerçekleştirmediniz. Mail adresinizi kontrol ediniz!";
		$user->trueFalse=false;
	echo (json_encode($user));
	}

	
}else{
	$user->id=null;
	$user->kadi=null;
	$user->mailAdres=null;
	$user->parola=null;
	$user->durum=null;
	$user->text="Girmiş olduğunuz bilgilere ait bir kullanıcı bulunamadı";
	$user->trueFalse=false;
	echo (json_encode($user));
}


?>