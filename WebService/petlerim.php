<?php
include("ayar.php");
$mus_id=$_POST["musid"];
$sorgula=mysqli_query($baglan,"select * from veteriner_pet_listesi where mus_id='$mus_id'");
$count=mysqli_num_rows($sorgula);




class petClass
{
	public $petid;
	public $petresim;
	public $petisim;
	public $pettur;
	public $petcins;
	public $tf;
}

$pet=new petClass();
$sayac= 0;
if($count>0)
{
	echo ("[");
	while ($bilgi=mysqli_fetch_assoc($sorgula))
	{
		$sayac = $sayac + 1;
		$pet->petid = $bilgi["id"];
		$pet->petresim = $bilgi["pet_resim"];
		$pet->petisim = $bilgi["pet_isim"];
		$pet->pettur = $bilgi["pet_turu"];
		$pet->petcins = $bilgi["pet_cins"];
		$pet->tf = true;
		echo (json_encode($pet));
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

	$pet->petid = null;
	$pet->petresim = null;
	$pet->petisim = null;
	$pet->pettur = null;
	$pet->petcins = null;
	$pet->tf = false;
	echo (json_encode($pet));

	echo ("]");
}


?>