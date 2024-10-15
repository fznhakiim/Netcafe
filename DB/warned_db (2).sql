-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 04, 2024 at 07:25 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warned_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_pc`
--

CREATE TABLE `tb_pc` (
  `id_pc` int(11) NOT NULL,
  `kode_pc` varchar(255) NOT NULL,
  `duration` time DEFAULT NULL,
  `status` enum('Available','Occupied') CHARACTER SET armscii8 COLLATE armscii8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_pc`
--

INSERT INTO `tb_pc` (`id_pc`, `kode_pc`, `duration`, `status`) VALUES
(1, '1', '10:10:50', 'Occupied'),
(3, '3', '16:40:40', 'Occupied'),
(4, '4', '18:00:00', 'Occupied'),
(5, '5', '08:00:09', 'Occupied'),
(16, '6', '08:00:00', 'Occupied'),
(18, '7', '08:00:00', 'Occupied'),
(19, '9', '18:01:39', 'Occupied'),
(21, '15', '18:00:50', 'Occupied'),
(22, '10', '22:00:00', 'Occupied'),
(23, '11', '22:00:00', 'Occupied');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pembayaran`
--

CREATE TABLE `tb_pembayaran` (
  `id_pembayaran` int(11) NOT NULL,
  `nama_pelanggan` varchar(255) NOT NULL,
  `harga_pembayaran` double NOT NULL,
  `type_pembayaran` enum('CASH','QRIS') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_pembayaran`
--

INSERT INTO `tb_pembayaran` (`id_pembayaran`, `nama_pelanggan`, `harga_pembayaran`, `type_pembayaran`) VALUES
(1, 'Arya', 20000, 'CASH'),
(2, 'Yaya', 30000, 'QRIS'),
(3, 'Zuki', 900000, 'QRIS'),
(4, 'Zaki', 900000, 'CASH'),
(5, 'Riki', 9000000, 'QRIS'),
(6, 'uci', 455555, 'CASH');

-- --------------------------------------------------------

--
-- Table structure for table `tb_username`
--

CREATE TABLE `tb_username` (
  `ID` int(11) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Status` enum('admin super','admin regular') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_username`
--

INSERT INTO `tb_username` (`ID`, `Username`, `Password`, `Status`) VALUES
(1, 'Admin', '1234', 'admin super'),
(2, 'Mamadolay', 'Super', 'admin super'),
(5, 'Rizki', '2345', 'admin regular');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_pc`
--
ALTER TABLE `tb_pc`
  ADD PRIMARY KEY (`id_pc`);

--
-- Indexes for table `tb_pembayaran`
--
ALTER TABLE `tb_pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`);

--
-- Indexes for table `tb_username`
--
ALTER TABLE `tb_username`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_pc`
--
ALTER TABLE `tb_pc`
  MODIFY `id_pc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `tb_pembayaran`
--
ALTER TABLE `tb_pembayaran`
  MODIFY `id_pembayaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tb_username`
--
ALTER TABLE `tb_username`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
