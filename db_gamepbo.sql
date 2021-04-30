-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 30 Apr 2021 pada 11.50
-- Versi server: 10.4.6-MariaDB
-- Versi PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_gamepbo`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `highscore`
--

CREATE TABLE `highscore` (
  `id` int(11) NOT NULL,
  `Username` varchar(200) NOT NULL,
  `Score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `highscore`
--

INSERT INTO `highscore` (`id`, `Username`, `Score`) VALUES
(1, 'MasProxy', 350),
(2, 'Vii', 370),
(3, 'YourMajesty', 320),
(4, 'aldi', 110),
(5, 'aldo', 62),
(6, 'dio', 289),
(7, 'Ricard', 35),
(8, 'ruben', 540),
(9, 'victor', 68),
(10, 'rido', 35),
(11, 'manu', 113),
(12, 'rudi', 49),
(13, 'rico', 64),
(14, 'paulo', 324),
(15, 'ridwan', 19);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `highscore`
--
ALTER TABLE `highscore`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `highscore`
--
ALTER TABLE `highscore`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
