<?php
$email = "postmaster@mymail.com";
$fromemail = "postmaster@mymail.com";
$subject = $_POST['subject'];
$msg = "Bu bir denemedir";
$headers = "From: ".$fromemail;
if( !mail($email, $subject, $msg, $headers) ){
echo "Eposta gönderiminde hata oluştu.";
}else{
echo "Eposta başarıyla gönderildi.";
}
?>