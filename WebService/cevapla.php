 <?php
include ("ayar.php");
$musid = $_POST["musid"];
$soruid = $_POST["soruid"];
$text = $_POST["text"];
$ekle = mysqli_query($baglan,"INSERT INTO veteriner_cevaplar(mus_id,soru_id,cevap) VALUES('$musid','$soruid','$text') ");
class Result
{
	public $text;
	public $tf;
}
$res = new Result();

if ($ekle) {
	$guncelle = mysqli_query($baglan,"UPDATE veteriner_sorular SET durum='1' WHERE id = '$soruid'");
	$res->text = "Soru Cevaplandı";
	$res->tf = true;
	echo(json_encode($res));
}
else
{
	$res->text = "Soru Cevaplanamadı";
	$res->tf = false;
	echo(json_encode($res));
}






?>