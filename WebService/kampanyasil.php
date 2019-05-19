<?php
include ("ayar.php");
$id = $_post["id"];
$sil = mysqli_query($baglan,"DELETE FROM `veteriner_kampanyalar` WHERE id = '$id'");


class Result
{
	public $text;
	public $tf;
}
$result = new Result();
if ($sil) 
{
	$result->text = "Kampanya başarıyla silindi.";
	$result->tf = true;
	echo(json_encode($result));
}
else
{
	$result->text = "Kampanya silinemedi.";
	$result->tf = false;
	echo(json_encode($result));
}


?>