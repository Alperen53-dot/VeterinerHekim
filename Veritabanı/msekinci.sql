-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 30 Nis 2019, 00:42:42
-- Sunucu sürümü: 10.1.37-MariaDB
-- PHP Sürümü: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `msekinci`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `veteriner_kullanicilar`
--

CREATE TABLE `veteriner_kullanicilar` (
  `id` int(11) NOT NULL,
  `kadi` varchar(255) NOT NULL,
  `mailAdres` varchar(255) NOT NULL,
  `parola` varchar(255) NOT NULL,
  `dogrulamaKodu` varchar(255) NOT NULL,
  `durum` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `veteriner_kullanicilar`
--

INSERT INTO `veteriner_kullanicilar` (`id`, `kadi`, `mailAdres`, `parola`, `dogrulamaKodu`, `durum`) VALUES
(6, '', '', '', '81384032', '1'),
(7, 'msekinci', 'mehmetserkanekinci@gmail.com', '1996', '15832283', '1'),
(8, 'duman', 'tolga387@hotmail.com', '123456', '3919525', '0'),
(9, 'aslihan', 'asli@gmail.com', '1234', '70537808', '0');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `veteriner_pet_listesi`
--

CREATE TABLE `veteriner_pet_listesi` (
  `id` int(11) UNSIGNED NOT NULL,
  `mus_id` varchar(255) COLLATE utf8_turkish_ci NOT NULL,
  `pet_isim` varchar(255) COLLATE utf8_turkish_ci NOT NULL,
  `pet_turu` varchar(255) COLLATE utf8_turkish_ci NOT NULL,
  `pet_cins` varchar(255) COLLATE utf8_turkish_ci NOT NULL,
  `pet_resim` varchar(255) COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `veteriner_pet_listesi`
--

INSERT INTO `veteriner_pet_listesi` (`id`, `mus_id`, `pet_isim`, `pet_turu`, `pet_cins`, `pet_resim`) VALUES
(1, '30', 'Karabaş', 'Köpek', 'Sibirya', 'http://localhost/veterinerservis/Resimler/kurt.jpg'),
(2, '30', 'Pamuk', 'Kedi', 'Van Kedisi', 'http://localhost/veterinerservis/Resimler/vankedisi.jpg'),
(3, '25', 'Boncuk', 'Köpek', 'Kangal', '');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `veteriner_kullanicilar`
--
ALTER TABLE `veteriner_kullanicilar`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `veteriner_pet_listesi`
--
ALTER TABLE `veteriner_pet_listesi`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `veteriner_kullanicilar`
--
ALTER TABLE `veteriner_kullanicilar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `veteriner_pet_listesi`
--
ALTER TABLE `veteriner_pet_listesi`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
