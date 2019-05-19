<?php
include ("ayar.php");
$id = $_POST["id"];
$onayla = mysqli_query($baglan," DELETE `veteriner_takipasi` WHERE id = '$id'");


class Result
{
	public $text;
	public $tf;
}
$result = new Result();
$result->text = "Aşı yapılmadı.";
$result->tf = true;
echo(json_encode($result));




?>