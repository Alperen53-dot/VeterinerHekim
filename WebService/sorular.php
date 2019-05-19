<?php
include ("ayar.php");
$sql = mysqli_query($baglan,"SELECT a.mus_id AS musid,a.soru,b.kadi,b.telefon,a.id AS soruid FROM `veteriner_sorular` a INNER JOIN veteriner_kullanicilar b on a.mus_id = b.id WHERE a.durum=0");
$count = mysqli_num_rows($sql);

class Soru
{
	public $musid;
	public $soru;
	public $kadi;
	public $telefon;
	public $soruid;
	public $tf;
}

$sor = new Soru();
$sayac= 0;
if($count>0)
{
	echo ("[");
	while ($bilgi=mysqli_fetch_assoc($sql))
	{
		$sayac = $sayac + 1;
		$sor->musid = $bilgi["musid"];
		$sor->soru = $bilgi["soru"];
		$sor->kadi = $bilgi["kadi"];
		$sor->telefon = $bilgi["telefon"];
		$sor->soruid = $bilgi["soruid"];
		$sor->tf = true;
		echo (json_encode($sor));
		if($count>$sayac)
		{
			echo (",");
		}
	}
	echo ("]");

}
else
{
	echo ("[");

	$sor->musid = null;
	$sor->soru = null;
	$sor->kadi = null;
	$sor->telefon = null;
	$sor->soruid = null;
	$sor->tf = false;
	echo (json_encode($sor));

	echo ("]");
}

?>