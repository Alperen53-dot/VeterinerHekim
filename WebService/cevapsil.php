<?php 

include("ayar.php");
$cevapid = $_POST["cevapid"];
$soruid = $_POST["soruid"];

$sil1 = mysqli_query($baglan,"delete from veteriner_cevaplar where id='$cevapid' and soru_id='$soruid'");
$sil2 = mysqli_query($baglan,"delete from veteriner_sorular where id='$soruid'");

class deleteClass{
	public $text;
	public $tf;
}$delete = new deleteClass();

if ($sil1 && $sil2) {
	$delete->text="Silme işlemi başarıyla gerçekleşti.";
	$delete->tf=true;
	echo(json_encode($delete));
}else{
	$delete->text="Bir hata meydana geldi";
	$delete->tf=false;
	echo(json_encode($delete));
}
?>