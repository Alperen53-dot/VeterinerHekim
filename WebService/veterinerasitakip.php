<?php
include("ayar.php");
$tarih = $_POST["tarih"];

$sql =  mysqli_query($baglan, "SELECT a.pet_isim,a.pet_tur,a.pet_cins,a.pet_resim,b.asi_isim,b.asi_tarih,b.id AS asiId ,c.kadi,c.telefon FROM veteriner_pet_listesi a INNER JOIN veteriner_takipasi b ON a.id=b.pet_id INNER JOIN veteriner_kullanicilar c ON c.id=b.mus_id WHERE b.asi_durum=0 AND b.asi_tarih = '$tarih'");

class Takip
{
	public $petisim ;
	public $pettur ;
	public $petcins ;
	public $petresim ;
	public $asiisim ;
	public $asitarih ;
	public $asiid ;
	public $kadi ;
	public $telefon ;
	public $tf ;
}
$tkp = new Takip();

$count = mysqli_num_rows($sql);

$sayac = 0;

if ($count > 0)
{
	echo ("[");
	while($kayit = mysqli_fetch_assoc($sql))
	{
		$sayac = $sayac + 1;
		$tkp->petisim = $kayit["pet_isim"];
		$tkp->pettur = $kayit["pet_tur"];
		$tkp->petcins = $kayit["pet_cins"];
		$tkp->petresim = $kayit["pet_resim"];
		$tkp->asiisim = $kayit["asi_isim"];
		$tkp->asitarih = $kayit["asi_tarih"];
		$tkp->asiid = $kayit["asiId"];
		$tkp->kadi = $kayit["kadi"];
		$tkp->telefon = $kayit["telefon"];
		$tkp->tf = true;
		echo (json_encode($tkp));
		if ($count>$sayac) 
		{
			echo(",");
		}
	}
	echo("]");

}
else 
{
		echo ("[");

		$tkp->petisim = null;
		$tkp->pettur = null;
		$tkp->petcins = null;
		$tkp->petresim = null;
		$tkp->asiisim = null;
		$tkp->asitarih = null;
		$tkp->asiid = null;
		$tkp->kadi = null;
		$tkp->telefon = null;
		$tkp->tf = false;
		echo (json_encode($tkp));

		echo("]");
	
}


?>