<?php
include("ayar.php");
$musid = $_POST["id"];

$sor =  mysqli_query($baglan, "SELECT a.pet_isim, a.pet_tur, a.pet_cins, a.pet_resim, b.asi_tarih, b.asi_isim FROM veteriner_pet_listesi a INNER JOIN veteriner_takipasi b ON a.id = b.pet_id WHERE a.mus_id ='$musid' AND b.mus_id = '$musid' AND b.asi_durum = '0'");

$count = mysqli_num_rows($sor);

class asiTakip
{
	public $petisim;
	public $pettur;
	public $petcins;
	public $petresim;
	public $asitarih;
	public $asiisim;
	public $tf;

}

$asi = new asiTakip();
$sayac = 0;

if ($count > 0) 
{
	echo("["); 
	while($bilgi = mysqli_fetch_assoc($sor))
	{
		$sayac = $sayac + 1;
		$asi->petisim = $bilgi ["pet_isim"];
		$asi->pettur = $bilgi ["pet_tur"];
		$asi->petcins = $bilgi ["pet_cins"];
		$asi->petresim = $bilgi ["pet_resim"];
		$asi->asitarih = $bilgi ["asi_tarih"];
		$asi->asiisim = $bilgi ["asi_isim"];
	
		$asi->tf = true;
		echo (json_encode($asi));
		if ($count>$sayac) {
			echo ",";
		}

	}
	echo ("]");
}
else 
{
		echo("[");

		$asi->petisim = null;
		$asi->pettur = null;
		$asi->petcins = null;
		$asi->petresim = null;
		$asi->asitarih = null;
		$asi->asiisim = null;
		$asi->tf = false;
		echo (json_encode($asi));

		echo ("]");
	
}


?>
