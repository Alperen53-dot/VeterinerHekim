<?php
include ("ayar.php");
$id = $_POST["id"];
$onayla = mysqli_query($baglan,"UPDATE `veteriner_takipasi` SET asi_durum='1' WHERE id = '$id'");


class Result
{
	public $text;
	public $tf;
}
$result = new Result();
$result->text = "Aşı yapılmıştır.";
$result->tf = true;
echo(json_encode($result));




?>