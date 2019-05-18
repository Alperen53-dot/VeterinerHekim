<?php

include("ayar.php");
$mus_id=$_POST["mus_id"];

$sor = mysqli_query($baglan,"SELECT a.id as soruid,a.soru,b.cevap,b.id as cevapid,b.soru_id
	FROM veteriner_sorular a
	INNER JOIN veteriner_cevaplar b ON a.id = b.soru_id WHERE a.durum='1' and a.mus_id = '$mus_id'");

$count=mysqli_num_rows($sor);

class soruClass
{
	public $soru;
	public $cevap;
	public $soruid;
	public $cevapid;
	public $tf;
}

$soru=new soruClass();
$sayac= 0;
if($count>0)
{
	echo ("[");
	while ($bilgi=mysqli_fetch_assoc($sor))
	{
		$sayac = $sayac + 1;
		$soru->soru = $bilgi["soru"];
		$soru->cevap = $bilgi["cevap"];
		$soru->soruid = $bilgi["soruid"];
		$soru->cevapid = $bilgi["cevapid"];
		$soru->tf = true;
		echo (json_encode($soru));
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

	$soru->soru = null;
		$soru->cevap = null;
		$soru->soruid = null;
		$soru->cevapid = null;
		$soru->tf = false;
	echo (json_encode($soru));

	echo ("]");
}

?>